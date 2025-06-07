package eai.devass.sinistreat.appli.usecase;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;


/**
 * @author Administrateur
 * 
 */
public interface IBaseUC {
	
	public IResult doExecuter(IValueObject entite, HashMap params) throws ValidationException;
	
	
	
	
}
