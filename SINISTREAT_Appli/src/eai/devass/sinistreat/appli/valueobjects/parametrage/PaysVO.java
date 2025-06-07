package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import eai.devass.sinistreat.appli.utils.validation.Validate;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PaysVO extends ParamATVO implements IValueObject {
	
	@Validate(obligatoire=true)
	private String code;
	private String libelle;
	
	public PaysVO() {
		
	}
	public PaysVO(String code) {
		this.code = code;
	}
	public PaysVO(String code, String libelle) {
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