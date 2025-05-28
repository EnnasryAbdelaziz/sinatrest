package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;

public class GestionnaireRelanceVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	
	@Validate(obligatoire=true)
	private String code;
	private String nomComplet;
	private String mail;
	
	public GestionnaireRelanceVO() {
		
	}
	public GestionnaireRelanceVO(String code) {
		this.code = code;
	}
	public GestionnaireRelanceVO(String code, String nom, String mail) {
		this.code = code;
		this.nomComplet=nomComplet;
		this.mail=mail;
	}	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getNomComplet() {
		return nomComplet;
	}
	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

