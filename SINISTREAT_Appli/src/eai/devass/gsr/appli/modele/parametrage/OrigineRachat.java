package eai.devass.gsr.appli.modele.parametrage;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;

/**
 * OrigineRachat:
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteMapping="eai.devass.gsr.appli.valueobjects.parametrage.OrigineRachatVO")
public class OrigineRachat implements IEntite, ILockable {

	private long id;
	private static final long serialVersionUID = 1L;

	/**
	 * libelle
	 */
	@Indexation(label = "libelle", analyzed = false)
	private String libelle;
	/**
	 * dateCreation
	 */
	@Indexation(label = "dateCreation", analyzed = false)
	private Calendar dateCreation;

	public String toString() {
		return String.valueOf(libelle);
	}

	public OrigineRachat() {
		super();

	}
	

	public OrigineRachat(long id) {
		super();
		this.id = id;
	}

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

	public Calendar getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
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
		// TODO Auto-generated method stub
		return null;
	}

}
