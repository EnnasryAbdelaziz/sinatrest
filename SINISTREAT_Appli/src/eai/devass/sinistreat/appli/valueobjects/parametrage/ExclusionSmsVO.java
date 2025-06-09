/***********************************************************************
 * Module:  TypeEchange.java
 * Author:  Administrateur
 * Purpose: Defines the Class TypeEchange
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;


public class ExclusionSmsVO implements IValueObject {
	
	@Validate(obligatoire=true)
	private String code;
	private String numeropolice;
	
	public ExclusionSmsVO() {
		
	}
	public ExclusionSmsVO(String code) {
		this.code = code;
	}
	public ExclusionSmsVO(String code, String libelle) {
		this.code = code;
		this.numeropolice=numeropolice;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNumeropolice() {
		return numeropolice;
	}
	public void setNumeropolice(String numeropolice) {
		this.numeropolice = numeropolice;
	}
}