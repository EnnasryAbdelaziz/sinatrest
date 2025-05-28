/**
 * 
 */
package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

/**
 * @author elfaismo
 *
 */
public class InitMvtProtheseUC extends FacadeServiceUseCase { 
	
	//private CommonUtils commonUtils = CommonUtils.getInstance();	
	
	/* (non-Javadoc)
	 * @see eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC#executeUC(ma.co.omnidata.framework.services.businessInterface.IValueObject, java.util.HashMap)
	 */
	@Override
	protected void doExecuter(IValueObject valueObject, HashMap params) throws Exception {
		RentierManager rentierManager = (RentierManager) ServicesFactory.getService(RentierManager.class);
		try {
			RentierVO rentier = (RentierVO) valueObject;
//			String idRentier = beanDTOVO.getIdRentier();
//			if(!commonUtils.isNumeric(idRentier)) {
//				throw new ExceptionMetier("Le rentier ne peut être vide (null)");
//			}
			Rentier rentierBD = rentierManager.getRentierByID(Long.valueOf(rentier.getId()));
			
			List<Prothese> listProthese = rentierBD.getRefsProtheses();
			
			Long classeRentier = rentierBD.getLienParente();
			if(!classeRentier.equals(new Long("0"))){
				throw new ExceptionMetier("Ce mouvement ne peut être appliqué qu'un rentier de type victime");
			}
			
		//	paramToConverter.put("listProtheses", listProthese);
			/*
			Double cumulReserve =0D;
			
			for(Prothese prothese:listProthese)
			{		
				if(prothese.getRefEtatProthese().getId()== EtatProthese.Supprimee.getId())
				{			
					cumulReserve = cumulReserve + prothese.getReserveProthese();	
				}
			}
			*/
			this.addResultItem(listProthese);
			//this.addResultItem(cumulReserve);
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		}
	}
	public boolean isTransactionnal() {
		return false;
	}
}
