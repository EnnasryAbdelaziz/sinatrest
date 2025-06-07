package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;


public class TypeGarantieVO implements IValueObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Validate(obligatoire=true)
	private String code;
	private String libelle;
	
	
	public TypeGarantieVO() {
		
	}
	public TypeGarantieVO(String code) {
		this.code = code;
	}
	public TypeGarantieVO(String code, String libelle) {
		this.code = code;
		this.libelle=libelle;
	}	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		if(StringUtils.isEmpty(code)) {
			code=null;
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