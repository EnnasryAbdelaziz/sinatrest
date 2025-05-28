package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;


public class IntermediaireVO implements IValueObject {
	
	@Validate(obligatoire=true)
	private String code;
	private String codeGuichet;
	private String libelle;
	private String adresse;
	private String ville;
	private String emailInterm;
	public String getEmailInterm() {
		return emailInterm;
	}
	public void setEmailInterm(String emailInterm) {
		this.emailInterm = emailInterm;
	}
	public String getVille() {
		return ville;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	private String codeTypeIntermediaire;
	public IntermediaireVO() {
		
	}
	public IntermediaireVO(String code) {
		this.code = code;
	}
	public IntermediaireVO(String code, String libelle) {
		this.code = code;
		this.libelle=libelle;
	}	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getCodeGuichet() {
		return codeGuichet;
	}
	public void setCodeGuichet(String codeGuichet) {
		this.codeGuichet = codeGuichet;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public void setCodeTypeIntermediaire(String codeTypeIntermediaire) {
		this.codeTypeIntermediaire = codeTypeIntermediaire;
	}
	public String getCodeTypeIntermediaire() {
		return codeTypeIntermediaire;
	}
}