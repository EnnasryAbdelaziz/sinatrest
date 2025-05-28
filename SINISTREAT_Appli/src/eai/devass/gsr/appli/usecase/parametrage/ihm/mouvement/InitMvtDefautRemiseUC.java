package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.reglement.QuittanceGsrManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC;
import eai.devass.gsr.appli.valueobjects.parametrage.BeanDTOVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;


@SuppressWarnings("all")
public class InitMvtDefautRemiseUC extends InitAbstractMouvementUC {

	private int nbrQtcAnnule = 0;
	private Date dateDebut = null;
	private Date dateFin = null;
	private Double totalMntQtc = 0D;
	
	public void executeUC(IValueObject entite, HashMap params) throws Exception {

		// Récupérer la class de l'objet BO correspondant à l'objet VO
		QuittanceGsrManager quittanceGsrManager = (QuittanceGsrManager) ServicesFactory
			.getService(QuittanceGsrManager.class);
		
		try {
			
			BeanDTOVO beanDTOVO = (BeanDTOVO) entite;
			String idRentier = beanDTOVO.getIdRentier();
			if(!commonUtils.isNumeric(idRentier)) {
				throw new ExceptionMetier("Le rentier ne peut être vide (null)");
			}
			
			// Recuperer la date de la dernier echeance regle
			Rentier rentier = new Rentier(Long.valueOf(idRentier));		
						
			// Recuperer les 4 last quittances
			List<QuittanceGsr> listQtc = quittanceGsrManager.getListQuittancesDefautRemise(rentier);
			if(commonUtils.isEmpty(listQtc)) {
				addMessageItem(new ErrorMessageItem("Aucune quittances trouvées concernées par un defaut de " +
						"remise pour le rentier sélectionné !!"));
				return;
			}
			
			// Nbr des quittances annulées
			infoQuittanceAnnuler(listQtc);
			paramToConverter.put("nbrQtcAnnule", nbrQtcAnnule);
			paramToConverter.put("totalMntQtc", totalMntQtc);
			paramToConverter.put("dateDebut", dateFormat.format(dateDebut));
			paramToConverter.put("dateFin", dateFormat.format(dateFin));
			
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		}

	}
	
	private void infoQuittanceAnnuler(List<QuittanceGsr> listQtc) throws ExceptionMetier {
		
		
		EtatQtc etatQtc = null;
		for(QuittanceGsr cuQuittanceGsr : listQtc) {
//			etatQtc = cuQuittanceGsr.getRefEtatQtc();
//			if(etatQtc == null) {
//				throw new ExceptionMetier("L'etat de la quittance : " 
//						+ cuQuittanceGsr.getNumeroQuittance() + " est null");
//			}
			
			nbrQtcAnnule ++;
			totalMntQtc += cuQuittanceGsr.getMontant();
			dateDebut = cuQuittanceGsr.getDateCreation().getTime();
			if(dateFin == null) {
				dateFin = cuQuittanceGsr.getDateCreation().getTime();
			}
//			if (EtatQuittance.Annulee.getId() == etatQtc.getId()) {
//				
//				
//			} else {
//				break;
//			}
		}
 		
	}

	public boolean isTransactionnal() {
		return false;
	}

}