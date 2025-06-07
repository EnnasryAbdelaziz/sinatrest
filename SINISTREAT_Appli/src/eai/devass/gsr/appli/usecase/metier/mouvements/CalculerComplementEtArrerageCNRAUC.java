package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.Date;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.utile.DateUtile;

import org.apache.commons.lang.StringUtils;

import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtComplementCNRA;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtComplementCNRAVO;

/**
 * Service d' ajout d' une entitÃ©
 * 
 * @author Nom Prenom (email)
 */
public class CalculerComplementEtArrerageCNRAUC extends FacadeServiceUseCase {

	/**
	 * Methode qui execute le Use case d' ajout d' une entite
	 * 
	 * @param entite
	 *            L' objet Ã  ajouter
	 * @param params
	 *            paramatere additionel qu 'on peut passer au Use Case
	 * @throws Exception
	 *             Probleme lors de l' execution du Use case
	 */
	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		
		MvtComplementCNRAVO mouvemenVO = (MvtComplementCNRAVO) entite;
		MvtComplementCNRA mouvement = new MvtComplementCNRA();
		if (mouvemenVO != null && StringUtils.trimToNull(mouvemenVO.getRefRentier()) != null) {
			RentierManager rentierManager = (RentierManager) ServicesFactory.getService(RentierManager.class);
			Rentier rentier = rentierManager.getRentierByID(Long.valueOf(mouvemenVO.getRefRentier()));
			TransverseManager transverseManager = (TransverseManager) ServicesFactory.getService(TransverseManager.class);
			
			if(mouvemenVO.getCodeSituationMotif() != null){
				Date datePriseEnCharge = DateUtile.getDate("dd/MM/yyyy", mouvemenVO.getDatePriseEnCharge());
				if(mouvemenVO.getCodeSituationMotif().equals("14")){//decision judiciaire
					//Calcul montant complement CNRA
					double coefficientAge = transverseManager.getCoefficientAge(rentier, datePriseEnCharge);
					//mntComplementCNRA = (Nouvelle rente – Ancienne rente)*Coefficient d’âge à la date de prise en charge de la CNRA
					mouvement.setMntComplementCalcule((Double.valueOf(mouvemenVO.getNouvMntRente()) - rentier.getMontantRenteTrimestrielle()) * coefficientAge);
					
					//Calcul montant arrerage
					Date datDepartAugmentation = DateUtile.getDate("dd/MM/yyyy", mouvemenVO.getDatDepartAugmentation());
					Date dateEvenement = datDepartAugmentation.after(rentier.getDateConstitution().getTime()) ? datDepartAugmentation : rentier.getDateConstitution().getTime();
					Integer nbreJours= transverseManager.getDaysBetweenTwoDates(dateEvenement, datePriseEnCharge);
					//mntArrerageCalcule = (Nouvelle rente trimestrielle- ancienne rente trimestrielle)* nombre de jour entre la date événement et l’échéance du trimestre en cours/90
					mouvement.setArreragesCalcules((Double.valueOf(mouvemenVO.getNouvMntRente()) - rentier.getMontantRenteTrimestrielle()) * nbreJours / 90);

				}else if(mouvemenVO.getCodeSituationMotif().equals("15")){//Conjoint atteint 60 ans
					//Calcul montant complement CNRA
					String dateNaissnace = DateUtile.calendarToString("dd/MM/yyyy", rentier.getDateNaissance());
					int annee = Integer.valueOf(dateNaissnace.substring(6)) + 60;
					Date anniversaire60Ans = DateUtile.getDate("dd/MM/yyyy", dateNaissnace.substring(0, 6) + String.valueOf(annee));
					
					double coefficientAge = transverseManager.getCoefficientAge(rentier, anniversaire60Ans);
					//mntComplementCNRA = (Rente annuelle * 20%)*Coefficient d’âge d’anniversaire de 60 ans
					mouvement.setMntComplementCalcule(0.2 * rentier.getMontantRenteTrimestrielle() * 4 * coefficientAge);
					
					//Calcul montant arrerage
					Integer nbreJours = transverseManager.getDaysBetweenTwoDates(anniversaire60Ans,datePriseEnCharge);
					//mntArrerageCalcule = (Nouvelle rente trimestrielle – ancienne rente trimestrielle)* nombre de jour entre la date d’anniversaire 60 ans et l’échéance du trimestre en cours/90
					mouvement.setArreragesCalcules((Double.valueOf(mouvemenVO.getNouvMntRente()) - rentier.getMontantRenteTrimestrielle()) * nbreJours / 90);
				}else if(mouvemenVO.getCodeSituationMotif().equals("16") || mouvemenVO.getCodeSituationMotif().equals("17")){//Autres cas (Descendant scolarisé + Descendant orpholin)
				    //MFBO Evo@229
					//Arrérages calculés= Rente trimestrielle * nombre de jour entre la date de départ de l'augmentation
			        //et la date de prise en charge/ 90
					
					//Calcul montant complement CNRA
					Date datDepartAugmentation = DateUtile.getDate("dd/MM/yyyy", mouvemenVO.getDatDepartAugmentation());
					double coefficientAge = transverseManager.getCoefficientAge(rentier, datDepartAugmentation);
					//mntComplementCNRA = Rente annuelle * Coefficient d’âge à la date de départ de l’augmentation
					mouvement.setMntComplementCalcule(rentier.getMontantRenteTrimestrielle() * 4 * coefficientAge);
					
					//Calcul montant arrerage
					Integer nbreJoursBetweenDeparAugmAndPriEnCharge = transverseManager.getDaysBetweenTwoDates(datDepartAugmentation, datePriseEnCharge);
					//mntArrerageCalcule = Rente trimestrielle * nombre de jour entre la date de départ de l’augmentation et la date de prise en charge/ 90
					mouvement.setArreragesCalcules(rentier.getMontantRenteTrimestrielle() * nbreJoursBetweenDeparAugmAndPriEnCharge / 90);
				}
			}
		}
		
		this.addResultItem(mouvement);
	}

	/**
	 * Methode pour activer le service de transaction
	 * 
	 * @returns soit true pour activer le service de transaction ou false pour
	 *          le desactiver
	 */
	public boolean isTransactionnal() {
		return true;
	}

	/**
	 * Methode pour activer le service de trace
	 * 
	 * @returns soit true pour activer le service de trace ou false pour le
	 *          desactiver
	 */
	public boolean isTracable() {
		return false;
	}

}