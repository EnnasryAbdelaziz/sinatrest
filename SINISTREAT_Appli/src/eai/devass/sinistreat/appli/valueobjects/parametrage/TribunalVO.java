package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;


public class TribunalVO implements IValueObject {
	
	@Validate(obligatoire=true)
	private String id;
	private String code;
	private String libelle;
	private TypeTribunalVO refType;
	public TribunalVO() {
		
	}
	public TribunalVO(String code) {
		this.code = code;
	}
	public TribunalVO(String code, String libelle) {
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
	public void setRefType(TypeTribunalVO refType) {
		this.refType = refType;
	}
	public TypeTribunalVO getRefType() {
		return refType;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
}