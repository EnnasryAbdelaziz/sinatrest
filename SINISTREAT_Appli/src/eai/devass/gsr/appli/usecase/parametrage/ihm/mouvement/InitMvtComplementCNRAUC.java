package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.mouvements.MouvementManager;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.parametrage.MotifComplementCNRA;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC;
import eai.devass.gsr.appli.valueobjects.parametrage.BeanDTOVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

@SuppressWarnings("all")
public class InitMvtComplementCNRAUC extends InitAbstractMouvementUC {

	//private int nbrQtcAnnule = 0;
	//private Date dateDebut = null;
	//private Date dateFin = null;
	//private Double totalMntQtc = 0D;

	public void executeUC(IValueObject entite, HashMap params) throws Exception {

		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);
		MouvementManager mouvementManager = (MouvementManager) ServicesFactory
		.getService(MouvementManager.class);
		try {
			
			BeanDTOVO beanDTOVO = (BeanDTOVO) entite;
			String idRentier = beanDTOVO.getIdRentier();
			if(!commonUtils.isNumeric(idRentier)) {
				throw new ExceptionMetier("Le rentier ne peut être vide (null)");
			}
			
			// Recuperer la date de la dernier echeance regle
//			Calendar calLastEcheance = transverseManager
//					.getDateDerniereEcheanceReglee(Long.valueOf(idRentier));
			//suppression sonar Dead store to local variable 
//			paramToConverter.put("dateDerniereEcheance", (calLastEcheance == null) ? "" 
//					: dateFormat.format(calLastEcheance.getTime()));
			//Liste motifComplement
			List listeMotifs = transverseManager.getSimilarObject(new MotifComplementCNRA());
			paramToConverter.put("listMotifComplementCNRARVO", listeMotifs);
			//Somme capitalCNRA + tous les complement CNRA
			Double capitalVerseCNRA =  mouvementManager.getCapitalVerseCNRA(idRentier);
			paramToConverter.put("capitalVerseCNRA", capitalVerseCNRA);
			//Dernier mouvement
			Mouvement mvt = mouvementManager.getDernierMvtComplementCNRA(idRentier);
			paramToConverter.put("MouvementVO", mvt);
			
			if(mvt != null){
				List<SituationMouvement> refSituationMouvement = mvt.getRefSituationMouvement();
				if(refSituationMouvement != null && refSituationMouvement.size() > 0){
					SituationMouvement situationEtat = refSituationMouvement.get(0);
					if(situationEtat != null && situationEtat.getRefMotifSituationEtat() != null){
						paramToConverter.put("motifComplementCNRALabel", situationEtat.getRefMotifSituationEtat().getLibelle());
					}
				}
			}
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		}
	}
	public boolean isTransactionnal() {
		return false;
	}

}