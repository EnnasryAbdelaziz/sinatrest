package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC;
import eai.devass.gsr.appli.valueobjects.parametrage.BeanDTOVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;


@SuppressWarnings("all")
public class InitMvtProrataCNRAUC extends InitAbstractMouvementUC {

	//private int nbrQtcAnnule = 0;
	//private Date dateDebut = null;
	//private Date dateFin = null;
	//private Double totalMntQtc = 0D;
	private Date calLastEcheance;
	private Mouvement mvt;
	public void executeUC(IValueObject entite, HashMap params) throws Exception {

		TransverseManager transverseManager = (TransverseManager) ServicesFactory.getService(TransverseManager.class);
		try {
			BeanDTOVO beanDTOVO = (BeanDTOVO) entite;
			String idRentier = beanDTOVO.getIdRentier();
			if(!commonUtils.isNumeric(idRentier)) {
				throw new ExceptionMetier("Le rentier ne peut être vide (null)");
			}
			// Recuperer la date de la dernier echeance regle
			List<Rentier> listRentier = transverseManager.getListRentierByRentier(idRentier);
			if (listRentier!=null && listRentier.size()>0)
			{
				for (Rentier curRentier : listRentier) {

					calLastEcheance = transverseManager
							.getDateDerniereEcheanceReglee(Long
									.valueOf(curRentier.getId()));
					if (calLastEcheance != null) {
						break;
					}

				}
			}
			if (calLastEcheance != null)
			{
				Date trimestre = commonGsrUtils.getDateFinCurrentTrimestre(calLastEcheance);
				paramToConverter.put("dateDerniereEcheance", (trimestre == null) ? ""
						: dateFormat.format(trimestre));
			}
			
			Calendar calLastQuittanceEmise = transverseManager.getDateDerniereEcheanceEmiseParDossier(Long.valueOf(idRentier));
			
			if (calLastQuittanceEmise != null)
			{
				Date trimestre = commonGsrUtils.getDateFinCurrentTrimestre(calLastQuittanceEmise.getTime());
				paramToConverter.put("dateDerniereQuittanceEmise", (trimestre == null) ? ""
						: dateFormat.format(trimestre));
			}
			//paramToConverter.put("dateDerniereQuittanceEmise", (calLastQuittanceEmise == null) ? "" : dateFormat.format(calLastQuittanceEmise.getTime()));
			List<Long> listRentiers = transverseManager.getListRentierIdsRentiers(idRentier);
			Double  sommeCapitalCalcule = transverseManager.sommeCapitalCalcule(listRentiers);
			paramToConverter.put("sommeCapitalCalcule", sommeCapitalCalcule);
			Double  sommeMntCNRA = transverseManager.sommeMntCNRA(listRentiers);
			paramToConverter.put("sommeMntCNRA", sommeMntCNRA);
			List<Rentier> listeRentiers = transverseManager.getListRentierByRentier(idRentier);
			paramToConverter.put("listeRentier", listeRentiers);
			
			if (listRentier!=null && listRentier.size()>0)
			{
				for (Rentier curRentier : listRentier) {
					
					mvt = transverseManager.getDernierMvtValiderConsignationCNRA(Long.valueOf(curRentier.getId()));
					if (mvt != null) {
						break;
					}
				}
			}
			paramToConverter.put("mouvementVO", mvt);
			if(mvt == null) {
				throw new ExceptionMetier("Merci de valider ou créer le mouvement consignation CNRA!");
			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		}
	}
	
	public boolean isTransactionnal() {
		return false;
	}
}