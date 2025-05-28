package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.DegreParente;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC;
import eai.devass.gsr.appli.valueobjects.parametrage.BeanDTOVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;


@SuppressWarnings("all")
public class InitMvtRemariageUC extends InitAbstractMouvementUC {

	private int nbrQtcAnnule = 0;
	private Date dateDebut = null;
	private Date dateFin = null;
	private Double totalMntQtc = 0D;
	
	public void executeUC(IValueObject entite, HashMap params) throws Exception {

		// Récupérer la class de l'objet BO correspondant à l'objet VO
		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);
		
		try {
			
			BeanDTOVO beanDTOVO = (BeanDTOVO) entite;
			String idRentier = beanDTOVO.getIdRentier();
			if(!commonUtils.isNumeric(idRentier)) {
				throw new ExceptionMetier("Le rentier ne peut être vide (null)");
			}
			
			// Recuperer la date de la dernier echeance regle
			Rentier rentier = (Rentier) getSession().get(Rentier.class,
					Long.valueOf(idRentier));
			
			// Verifier si le rentier est decede
			Long lientParente = rentier.getLienParente();
			if(lientParente == null) {
				throw new ExceptionMetier("Le rentier doit être un conjoint !!");
			}
			
			if (lientParente < Long.valueOf(DegreParente.Conjoint.getCode()) 
					&& lientParente > 13 ) {
				throw new ExceptionMetier("Le rentier doit être un conjoint !!");
			}
			
			// Recuperer la liste des ayant droit (rentier)
			List<Rentier> listRentier = transverseManager
					.getListRentierByRentier(idRentier);
			paramToConverter.put("listRentier", listRentier);
			
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		}

	}
	
	private void infoQuittanceAnnuler(List<QuittanceGsr> listQtc) throws ExceptionMetier {
		
		
		List<SituationQuittanceGsr> listSitQtc = null;
		SituationQuittanceGsr firstSituationQuittanceGsr = null;
		for(QuittanceGsr cuQuittanceGsr : listQtc) {
			listSitQtc = cuQuittanceGsr.getRefSituationQuittanceGsr();
			if(commonUtils.isEmpty(listSitQtc)) {
				throw new ExceptionMetier(
						"La liste des etats de la quittance ne peut être vide !!!");
			}
			firstSituationQuittanceGsr = listSitQtc.get(0);
			if (EtatQuittance.Annulee.getId() == firstSituationQuittanceGsr
					.getRefEtatQtc().getId()) {
				
				nbrQtcAnnule ++;
				totalMntQtc += cuQuittanceGsr.getMontant();
				dateDebut = cuQuittanceGsr.getDateCreation().getTime();
				if(dateFin == null) {
					dateFin = dateDebut;
				}
				
			} else {
				break;
			}
		}
 		
	}

	public boolean isTransactionnal() {
		return false;
	}

}