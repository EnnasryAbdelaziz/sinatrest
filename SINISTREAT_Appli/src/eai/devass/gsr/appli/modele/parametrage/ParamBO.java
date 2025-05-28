package eai.devass.gsr.appli.modele.parametrage;

import java.io.Serializable;
import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import eai.devass.commun.appli.converter.AConverter;

public abstract class ParamBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@AConverter(propertyDist="id")
	private Long id;
	
	@AConverter(propertyDist="code")
	private String code;
	
	@AConverter(propertyDist="libelle")
	private String libelle;
	
	@AConverter(propertyDist="dateCreation")
	private Calendar dateCreation;
	
	public abstract IEntiteFactory getFactory();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Calendar getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public void setId(long arg0) {
		
		
	}
	
	
	
	
}
