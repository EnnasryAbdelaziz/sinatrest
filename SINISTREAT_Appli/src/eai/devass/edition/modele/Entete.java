package eai.devass.edition.modele;

import java.util.List;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import ma.co.omnidata.framework.services.lock.ILockable;

public class Entete implements IEntite, ILockable {
	
	private long id;
	private String sousTitre;
	private List<LigneTitre> refsLignesTitre;
	private List<Parametre> refsParametres;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSousTitre() {
		return sousTitre;
	}
	public void setSousTitre(String sousTitre) {
		this.sousTitre = sousTitre;
	}
	public List<LigneTitre> getRefsLignesTitre() {
		return refsLignesTitre;
	}
	public void setRefsLignesTitre(List<LigneTitre> refsLignesTitre) {
		this.refsLignesTitre = refsLignesTitre;
	}
	public List<Parametre> getRefsParametres() {
		return refsParametres;
	}
	public void setRefsParametres(List<Parametre> refsParametres) {
		this.refsParametres = refsParametres;
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
