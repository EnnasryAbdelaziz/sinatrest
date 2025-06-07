/***********************************************************************
 * Module:  TypeEchange.java
 * Author:  Administrateur
 * Purpose: Defines the Class TypeEchange
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.commun.appli.converter.AConverter;


@AConverter(entiteDist="eai.devass.sinistreat.appli.modele.parametrage.TypeJuridiction")
public class TypeJuridictionVO extends ParamATVO implements IValueObject {


	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	
	private static final long serialVersionUID = 1L;
	@Validate(obligatoire=true)
	private String code;
	private String libelle;
	
	public TypeJuridictionVO() {
		
	}
	public TypeJuridictionVO(String code) {
		this.code = code;
	}
	public TypeJuridictionVO(String code, String libelle) {
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
	} */
}