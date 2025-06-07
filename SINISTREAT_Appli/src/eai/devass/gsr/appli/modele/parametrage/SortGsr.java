package eai.devass.gsr.appli.modele.parametrage;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import ma.co.omnidata.framework.services.lock.ILockable;

/**
 * SortGsr
 * 
 * @author Mossab wassim
 */

public class SortGsr implements IEntite, ILockable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String libelle;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getIdLockable() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLockableType() {
		// TODO Auto-generated method stub
		return null;
	}

	public IEntiteFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}


}
