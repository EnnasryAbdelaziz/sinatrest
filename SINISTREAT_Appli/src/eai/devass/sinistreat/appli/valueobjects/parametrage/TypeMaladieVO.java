/***********************************************************************
 * Module:  TypeEchange.java
 * Author:  Administrateur
 * Purpose: Defines the Class TypeEchange
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.parametrage;

import org.apache.commons.lang.StringUtils;

public class TypeMaladieVO extends ParamATVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// @Validate(obligatoire=true)

	private String code;

	private String libelle;

	public TypeMaladieVO() {

	}

	public TypeMaladieVO(String code) {
		this.code = code;
	}

	public TypeMaladieVO(String code, String libelle) {
		this.code = code;
		this.libelle = libelle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (StringUtils.isEmpty(code)) {
			code = "99";
		}
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}