package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC;
import eai.devass.gsr.appli.valueobjects.parametrage.BeanDTOVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

@SuppressWarnings("all")
public class InitMvtSuppressionUC extends InitAbstractMouvementUC {

	//private Date dateDerniereEcheance = null;

	public void executeUC(IValueObject entite, HashMap params) throws Exception {

		// Récupérer la class de l'objet BO correspondant à l'objet VO
//		TransverseManager transverseManager = (TransverseManager) ServicesFactory
//				.getService(TransverseManager.class);

		try {

			BeanDTOVO beanDTOVO = (BeanDTOVO) entite;
			String idRentier = beanDTOVO.getIdRentier();
			if (!commonUtils.isNumeric(idRentier)) {
				throw new ExceptionMetier("Le rentier ne peut être vide (null)");
			}

			// Recuperer la date de la dernier echeance regle
			//Rentier rentier = new Rentier(Long.valueOf(idRentier));
//			Calendar calLastEcheance = transverseManager.getDateDerniereEcheanceReglee(Long.valueOf(idRentier));
//			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//			paramToConverter.put(
//					"dateDerniereEcheance",
//					(calLastEcheance == null) ? "" : dateFormat
//							.format(calLastEcheance.getTime()));
			// Run UC du mouvement en question
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		}
	}

	public boolean isTransactionnal() {
		return false;
	}
}