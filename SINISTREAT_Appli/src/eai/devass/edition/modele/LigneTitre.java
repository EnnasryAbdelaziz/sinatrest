package eai.devass.edition.modele;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import ma.co.omnidata.framework.services.lock.ILockable;

public class LigneTitre implements IEntite, ILockable {
	
	private long id;
	private String libelle;
	private Boolean isDynamqiue;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Boolean getIsDynamqiue() {
		return isDynamqiue;
	}
	public void setIsDynamqiue(Boolean isDynamqiue) {
		this.isDynamqiue = isDynamqiue;
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
