package eai.devass.gsr.appli.modele.parametrage;
 
import eai.devass.commun.appli.converter.AConverter;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import ma.co.omnidata.framework.services.lock.ILockable;

/**
 * @author aelhacham
*/
@AConverter(entiteDist="eai.devass.gsr.appli.modele.parametrage.TypeRevisionVO")
public class TypeRevision implements IEntite, ILockable          {

	private long id;
private static final long serialVersionUID = 1L;

/**
     * libelle
*/ 
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

/**
     * Methode qui retourne l' Id du lockable
     * 
     * @returns id du locakble
*/
	public String getIdLockable() {
		return Long.toString(getId());
	}

/**
     * Methode qui retourne le type du lockable
     * 
     * @returns type du locakble
*/
	public String getLockableType() {
		return this.getClass().getName();
	}

public IEntiteFactory getFactory() {
	return null;
}

}
