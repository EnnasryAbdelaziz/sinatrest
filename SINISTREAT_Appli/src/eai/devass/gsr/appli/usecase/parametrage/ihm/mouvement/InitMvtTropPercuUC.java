package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.util.Date;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtTropPercuManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC;
import eai.devass.gsr.appli.valueobjects.parametrage.BeanDTOVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;


@SuppressWarnings("all")
public class InitMvtTropPercuUC extends InitAbstractMouvementUC {

	//private int nbrQtcAnnule = 0;
	//private Date dateDebut = null;
	//private Date dateFin = null;
	//private Double totalMntQtc = 0D;
	
	public void executeUC(IValueObject entite, HashMap params) throws Exception {

		try {
			BeanDTOVO beanDTOVO = (BeanDTOVO) entite;
			String idRentier = beanDTOVO.getIdRentier();
			if(!commonUtils.isNumeric(idRentier)) {
				throw new ExceptionMetier("Le rentier ne peut être vide (null)");
			}
			
			// Recuperer la date de la dernier echeance regle
			Rentier rentier = new Rentier(Long.valueOf(idRentier));		
						
			// Recuperer le montant de la rente
			MvtTropPercuManager tropPercuManager = new MvtTropPercuManager();
//			Double mntRenteTrim =  tropPercuManager.getRenteTrimestrielle(rentier);
//			paramToConverter.put("montantRente", (mntRenteTrim != null) ? mntRenteTrim : 0D);
			
			// REcuperer la date constatation TP, total recuperer et totale a recuperer			
			Object[] infoTP = tropPercuManager.getInfoTropPercu(rentier);
			Object[] infoTPRecuperer = tropPercuManager.getInfoTropPercuRecuperer(rentier);
			Double mntTropPercu = 0D;
			Double mntTropPercuRecuperer = 0D;
			Double mntTropPercuARecuperer = 0D;
			Date dateDebutConstatationTP = null;
			Date dateFinConstatationTP = null;
			
			// Info trop percu
			if(infoTP != null) {
				dateDebutConstatationTP = (infoTP[0] != null) ? (Date) infoTP[0] : null;
				mntTropPercu = (infoTP[1] != null) ? (Double) infoTP[1] : 0D;
			}
			
			// INfo trop percu a recuperer
			if(infoTPRecuperer != null) {
				dateFinConstatationTP = (infoTPRecuperer[0] != null) ? (Date) infoTPRecuperer[0] : null;
				mntTropPercuRecuperer = (infoTPRecuperer[1] != null) ? (Double) infoTPRecuperer[1] : 0D;
			}
			
			// Trop percu a recuperer
			mntTropPercuARecuperer = mntTropPercu - mntTropPercuRecuperer;
			paramToConverter.put("dateDebutConstatation", (dateDebutConstatationTP != null) ? dateFormat
							.format(dateDebutConstatationTP) : null);
			paramToConverter.put("dateFinConstatation", dateFinConstatationTP);
			paramToConverter.put("totalTropPercu", mntTropPercu);
			paramToConverter.put("totalTropPercuRecupere", mntTropPercuRecuperer);
			paramToConverter.put("totalTropPercuARecupere", mntTropPercuARecuperer);
			
			
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		}

	}
	
	

	

}