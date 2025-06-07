package eai.devass.edition.modele;

import java.util.List;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import ma.co.omnidata.framework.services.lock.ILockable;

public class Recap implements IEntite, ILockable {
	
	private long id;
	private String description;
	private List<Variable> refsVariables;
	private List<RecapColonne> refsRecapColonnes;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Variable> getRefsVariables() {
		return refsVariables;
	}
	public void setRefsVariables(List<Variable> refsVariables) {
		this.refsVariables = refsVariables;
	}
	public List<RecapColonne> getRefsRecapColonnes() {
		return refsRecapColonnes;
	}
	public void setRefsRecapColonnes(List<RecapColonne> refsRecapColonnes) {
		this.refsRecapColonnes = refsRecapColonnes;
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
