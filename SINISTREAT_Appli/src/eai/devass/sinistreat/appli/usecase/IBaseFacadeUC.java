package eai.devass.sinistreat.appli.usecase;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;


/**
 * @author Administrateur
 * 
 */
public interface IBaseFacadeUC {
	
	public IResult executerBase(IValueObject entite, HashMap params) throws Exception;
	
	
	
}
