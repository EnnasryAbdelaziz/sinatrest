package eai.devass.gsr.appli.modele.parametrage;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;

/**
 * StatutHeritier:
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.parametrage.StatutHeritierVO")
public class StatutHeritier implements IEntite, ILockable {

	private long id;
	private static final long serialVersionUID = 1L;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * libelle
	 */
	@Indexation(label = "libelle", analyzed = false)
	private String libelle;

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the dateCreation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * dateCreation
	 */
	@Indexation(label = "dateCreation", analyzed = false)
	private Calendar dateCreation;

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

