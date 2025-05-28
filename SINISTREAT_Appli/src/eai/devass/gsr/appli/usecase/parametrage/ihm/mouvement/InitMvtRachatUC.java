/**
 * 
 */
package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC;
import eai.devass.gsr.appli.valueobjects.parametrage.BeanDTOVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

/**
 * @author elfaismo
 *
 */
public class InitMvtRachatUC extends InitAbstractMouvementUC {

	/* (non-Javadoc)
	 * @see eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC#executeUC(ma.co.omnidata.framework.services.businessInterface.IValueObject, java.util.HashMap)
	 */
	@Override
	public void executeUC(IValueObject entite, HashMap params)
			throws Exception {
		// Récupérer la class de l'objet BO correspondant à l'objet VO
//		TransverseManager transverseManager = (TransverseManager) ServicesFactory
//				.getService(TransverseManager.class);
		RentierManager rentierManager = (RentierManager) ServicesFactory
		.getService(RentierManager.class);

		try {
			
			BeanDTOVO beanDTOVO = (BeanDTOVO) entite;
			String idRentier = beanDTOVO.getIdRentier();
			if(!commonUtils.isNumeric(idRentier)) {
				throw new ExceptionMetier("Le rentier ne peut être vide (null)");
			}
			
			
			
			// Recuperer la date de la dernier echeance regle
			Rentier rentierBD = rentierManager.getRentierByID(Long.valueOf(idRentier));
			Long classeRentier = rentierBD.getLienParente();
			if(!classeRentier.equals(new Long("0"))){
				throw new ExceptionMetier("Ce mouvement ne peut être appliqué qu'un rentier de type victime");
			}
	
			
//			Calendar calLastEcheance = transverseManager
//					.getDateDerniereEcheanceReglee(Long.valueOf(idRentier));
			
//			if(calLastEcheance==null || calLastEcheance.equals("")){
//				
//				if(params.get("outMapMessage")!=null){
//				
//				((Map) params.get("outMapMessage")).put(IMessage.INFO,IMessage.MSG_AUCUN_REGLEMENT );
//				}else{
//					Map<Object, Object> messageMap = new HashMap();
//					messageMap.put(IMessage.INFO, IMessage.MSG_AUCUN_REGLEMENT);
//					params.put("outMapMessage",messageMap );
//				}
//
//			}
			
			Calendar calDepartRente = rentierBD.getDateDepartRente();
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//			paramToConverter.put("dateDerniereEcheance", (calLastEcheance == null) ? "" 
//					: dateFormat.format(calLastEcheance.getTime()));
			paramToConverter.put("dateDepartRente", (calDepartRente == null) ? "" 
					: dateFormat.format(calDepartRente.getTime()));
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		}

	}
	public boolean isTransactionnal() {
		return false;
	}

}
