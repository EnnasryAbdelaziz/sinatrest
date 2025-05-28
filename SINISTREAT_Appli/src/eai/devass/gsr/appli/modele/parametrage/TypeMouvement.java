package eai.devass.gsr.appli.modele.parametrage;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.parametrage.TypeMouvementFactory;

/**
 * type mouvement GSR:
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.parametrage.TypeMouvementVO")
public class TypeMouvement implements IEntite, ILockable {

	private static final long serialVersionUID = 1L;
	private long id;

	/**
	 * code
	 */
	@Indexation(label = "code", analyzed = false)
	private String code;
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

	public TypeMouvement() {
		super();
	}
	
	

	public TypeMouvement(long id) {
		super();
		this.id = id;
	}



	/**
	 * Methode qui retourne l' instance de la factory d'une entité
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new TypeMouvementFactory();
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

}
