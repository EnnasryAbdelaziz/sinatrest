package eai.devass.gsr.appli.modele.parametrage;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.gsr.appli.manager.parametrage.SocieteFactory;

/**
 * 
 * 
 * @author Nom Prenom (email)
 */
public class Societe implements IEntite, ILockable {

	private long id;
	private static final long serialVersionUID = 1L;

	
	
	public Societe(long id) {
		this.id = id;
	}
	

	public Societe() {
		
	}
	
	/**
	 * code code 
	 */
	@Indexation(label = "code", analyzed = false)
	private Long code;
	/**
	 * libelle libelle 
	 */
	@Indexation(label = "libelle", analyzed = false)
	private String libelle;
	/**
	 * dateCreation
	 */
	@Indexation(label = "dateCreation", analyzed = false)
	private Calendar dateCreation;

	private String refSociete;

	public String toString() {
		return String.valueOf(libelle);
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entité
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new SocieteFactory();
	}

	public long getId() {
		return id;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Long getCode() {
		return code;
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
	public String getRefSociete() {
		return refSociete;
	}

	public void setRefSociete(String refSociete) {
		this.refSociete = refSociete;
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



}
