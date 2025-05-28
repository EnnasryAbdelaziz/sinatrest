package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;

public class CanalConciliationVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	
	@Validate(obligatoire=true)
	private String code;
	private String libelle;
	
	public CanalConciliationVO() {
		
	}
	public CanalConciliationVO(String code) {
		this.code = code;
	}
	public CanalConciliationVO(String code, String libelle) {
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
