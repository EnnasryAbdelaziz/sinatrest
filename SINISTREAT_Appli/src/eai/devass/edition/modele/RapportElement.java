package eai.devass.edition.modele;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import ma.co.omnidata.framework.services.lock.ILockable;

public class RapportElement implements IEntite, ILockable {
	
	private long id;
	private String name;
	private String type;
	private String libelle;
	private String pattern;
	private String textAlignment;
	private Integer ordreAffichage;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getTextAlignment() {
		return textAlignment;
	}
	public void setTextAlignment(String textAlignment) {
		this.textAlignment = textAlignment;
	}
	public Integer getOrdreAffichage() {
		return ordreAffichage;
	}
	public void setOrdreAffichage(Integer ordreAffichage) {
		this.ordreAffichage = ordreAffichage;
	}
	public String getIdLockable() {
		return Long.toString(getId());
	}
	public String getLockableType() {
		return this.getClass().getName();
	}
	public IEntiteFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}
}
