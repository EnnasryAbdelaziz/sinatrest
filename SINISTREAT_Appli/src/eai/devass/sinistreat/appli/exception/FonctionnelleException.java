package eai.devass.sinistreat.appli.exception;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.utils.exception.ExceptionTools;

/* @author kchakib : 4 oct. 10 */
public class FonctionnelleException extends ValidationException {

	//private final static String SUFIX_EXP = "EXP_";
	
	public FonctionnelleException() {
		super();
	}

	public FonctionnelleException(String message, Throwable cause) {
		super(message, cause);
	}

	public FonctionnelleException(String key, String ... params) {
		super(key, params);
	}

	public FonctionnelleException(Throwable cause) {
		super(cause.getMessage(), cause);
	}
	
	/**
	 * Personnalisation des exceptions ORACLE : Constraints Violation
	 * @param ConstraintViolationException
	 */
	public FonctionnelleException(ConstraintViolationException e) {
		super(ExceptionTools.getInstance().getCodeError(e), ExceptionTools
				.getInstance().getValuesMessage(e));
	}
	
}


