/**
 * 
 */
package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC;
import eai.devass.gsr.appli.valueobjects.parametrage.BeanDTOVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

/**
 * @author elfaismo
 *
 */
public class InitMvtDecesUC extends InitAbstractMouvementUC {

	/* (non-Javadoc)
	 * @see eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC#executeUC(ma.co.omnidata.framework.services.businessInterface.IValueObject, java.util.HashMap)
	 */
	@Override
	public void executeUC(IValueObject entite, HashMap params)
			throws Exception {
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		//correction sonar Dead store to local variable.
		//TransverseManager transverseManager = (TransverseManager) ServicesFactory
			//	.getService(TransverseManager.class);

		try {
			
			BeanDTOVO beanDTOVO = (BeanDTOVO) entite;
			String idRentier = beanDTOVO.getIdRentier();
			if(!commonUtils.isNumeric(idRentier)) {
				throw new ExceptionMetier("Le rentier ne peut être vide (null)");
			}
			
			// Recuperer la date de la dernier echeance regle
//			Calendar calLastEcheance = transverseManager.getDateDerniereEcheanceReglee(Long.valueOf(idRentier));
			
//			if(calLastEcheance==null || calLastEcheance.equals("")){
//				
//				throw new ExceptionMetier("Ce mouvement ne peut être appliqué que s'il y'a au moins un réglement.  !!");
//				
//			}
			
//			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//			paramToConverter.put("dateDerniereEcheance", (calLastEcheance == null) ? "" 
//					: dateFormat.format(calLastEcheance.getTime()));
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		}

	}

}
