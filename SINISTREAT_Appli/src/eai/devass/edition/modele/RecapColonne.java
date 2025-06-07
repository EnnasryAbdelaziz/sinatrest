package eai.devass.edition.modele;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import ma.co.omnidata.framework.services.lock.ILockable;

public class RecapColonne implements IEntite, ILockable {
	
	private long id;
	private String name;
	private String calculation;
	private Champ refChamp;
	
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
	public String getCalculation() {
		return calculation;
	}
	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}
	public Champ getRefChamp() {
		return refChamp;
	}
	public void setRefChamp(Champ refChamp) {
		this.refChamp = refChamp;
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
