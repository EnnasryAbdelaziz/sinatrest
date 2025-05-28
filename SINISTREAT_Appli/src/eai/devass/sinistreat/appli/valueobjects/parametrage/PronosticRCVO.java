/***********************************************************************
 * Module:  TypeEchange.java
 * Author:  Administrateur
 * Purpose: Defines the Class TypeEchange
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.parametrage;

import eai.devass.sinistreat.appli.utils.validation.Validate;


public class PronosticRCVO extends ParamATVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Validate(obligatoire=true)
	private String code;
	private String libelle;
	
	public PronosticRCVO() {
		
	}
	public PronosticRCVO(String code) {
		this.code = code;
	}
	public PronosticRCVO(String code, String libelle) {
		this.code = code;
		this.libelle=libelle;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {	
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}