package eai.devass.sinistreat.appli.usecase;
//Jdk
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IMessageItem;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.SeviceAutorisationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.businessInterface.impl.Result;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.securite.IActionSecurise;
import ma.co.omnidata.framework.services.securite.IAutorisationManager;
import ma.co.omnidata.framework.services.securite.IUtilisateur;

/**
 * 
 */
public class BaseFacade {

	public final IResult invokeService(IUtilisateur utilisateur, 
								IActionSecurise action, 
								IValueObject valueObject,
								HashMap params) throws Exception{
		try {
			//Aucune transaction ni connexion 
			
			IAutorisationManager manager = (IAutorisationManager) ServicesFactory.getService(IAutorisationManager.class);
			action = manager.getAction(action);
			
			if (action==null){
				throw new Exception("FWK.SECURITY.FWK.ACTION.INEXISTANTE");
			}
			//Activate Service
			IBaseFacadeUC facadeUseCase = BaseFacade.getInstance(action);	
			//Execution
			IResult lRes = facadeUseCase.executerBase(valueObject, params);

			return lRes;
			
		}catch (Exception e){
			((ILog)ServicesFactory.getService(ILog.class)).error("",e);
			Result res = new Result();
			IMessageItem erreurMessage = new ErrorMessageItem(e.getMessage());
			res.addMessageItem(erreurMessage);
			return res;
		}		
	}
	
	public static IBaseFacadeUC getInstance(IActionSecurise action) throws SeviceAutorisationException{

		try {
			((ILog)ServicesFactory.getService(ILog.class)).debug("Instantiation de la classe : " + action.getActionName());
			Class facadeClass = Class.forName( action.getActionClassName());
			IBaseFacadeUC facadeUseCase = (IBaseFacadeUC) facadeClass.newInstance();
			if (facadeUseCase instanceof BaseUC){
				((FacadeServiceUseCase) facadeUseCase).setActionDescription(action.getActionDescription());
				((FacadeServiceUseCase) facadeUseCase).setActionId(action.getActionId());
			}
			return facadeUseCase;
		}catch (Exception e){
			((ILog)ServicesFactory.getService(ILog.class)).error("",e);
			return null;
		}
		
	}
}
