package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC;
import eai.devass.gsr.appli.valueobjects.parametrage.BeanDTOVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;


public class InitMvtCalcProrataCNRAUC extends InitAbstractMouvementUC {
	
	@Override
	public void executeUC(IValueObject entite, HashMap params)
			throws Exception {
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
			Date calLastEcheance = transverseManager
					.getDateDerniereEcheanceReglee(Long.valueOf(idRentier));
			
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if (calLastEcheance != null)
			{
				Date trimestre = commonGsrUtils.getDateFinCurrentTrimestre(calLastEcheance);
				paramToConverter.put("dateDerniereEcheance", (trimestre == null) ? "" 
						: dateFormat.format(calLastEcheance));
				}
			// Recuperer la date de la dernier echeance emise
			
			Date calLastQuittanceEmise = transverseManager.getDateDerniereEcheanceEmise(Long.valueOf(idRentier));
			
			if (calLastQuittanceEmise != null)
			{
				Date trimestre = commonGsrUtils.getDateFinCurrentTrimestre(calLastQuittanceEmise);
				paramToConverter.put("dateDerniereEcheanceEmise", (trimestre == null) ? ""
						: dateFormat.format(trimestre));
				}
			
			
			
			
			
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		}

	}

	
//	@Override
//	protected void execute(IValueObject valueObject, HashMap params) throws Exception {
//		RentierManager rentierManager = (RentierManager) ServicesFactory.getService(RentierManager.class);
//		TransverseManager transverseManager = (TransverseManager) ServicesFactory.getService(TransverseManager.class);
//		Date trimestre = null;
//		try {
//			RentierVO rentier = (RentierVO) valueObject;
//			Rentier rentierBD = rentierManager.getRentierByID(Long.valueOf(rentier.getId()));
//			// Recuperer la date de la dernier echeance regle
//			Date calLastEcheance = transverseManager.getDateDerniereEcheanceReglee(Long.valueOf(rentier.getId()));
//			
//			if (calLastEcheance != null)
//			{
//				trimestre = commonGsrUtils.getDateFinCurrentTrimestre(calLastEcheance);
//				paramToConverter.put("dateDerniereEcheance", (trimestre == null) ? ""
//						: dateFormat.format(trimestre));
//			}
//		} catch (ConstraintViolationException e) {
//			throw new FonctionnelleException(e);
//		}
//		this.addResultItem(trimestre);
//	}
	
	public boolean isTransactionnal() {
		return false;
	}

}