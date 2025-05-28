package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class TypeDossierBnejVO implements IValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String libelle;
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
	@Override
	public String toString() {
		return "TypeDossierBnejVO [code=" + code + ", libelle=" + libelle + "]";
	}
	

}
