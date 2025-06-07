/***********************************************************************
 * Module:  SituationRentier.java
 * Author:  WMOS
 * Purpose: Defines the Class SituationRentier
 ***********************************************************************/

package eai.devass.gsr.appli.modele.parametrage;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.gsr.appli.manager.metier.dossierRente.NatureProtheseFactory;

/** WMOS */
public class SituationRentier implements IEntite, ILockable {

	private long id;
	private static final long serialVersionUID = 1L;
	private Long code;
	private String libelle;

	public SituationRentier() {
		
	}
	
	public SituationRentier(long id) {
		this.id = id;
	}


	public String toString() {
		return String.valueOf(libelle);
	}

	public EntiteFactory getFactory() {
		return new NatureProtheseFactory();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getCode() {
		return this.code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	// public Calendar getDateCreation() {
	// return this.dateCreation;
	// }
	//
	// public void setDateCreation(Calendar dateCreation) {
	// this.dateCreation = dateCreation;
	// }

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
