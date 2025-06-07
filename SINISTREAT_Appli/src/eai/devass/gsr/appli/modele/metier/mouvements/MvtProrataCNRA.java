package eai.devass.gsr.appli.modele.metier.mouvements;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtProrataCNRAFactory;

/**
 * MvtProrataCNRA:
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtProrataCNRAVO")
public class MvtProrataCNRA extends MvtConsignCNRA implements IEntite,
		ILockable {

	private static final long serialVersionUID = 1L;

	/**
	 * mntProrataCNRA
	 */
	@Indexation(label = "mntProrataCNRA", analyzed = false)
	private Double mntProrataCNRA;
	/**
	 * datePriseEnCharge
	 */
	@Indexation(label = "datePriseEnCharge", analyzed = false)
	private Calendar datePriseEnCharge;

	/**
	 * dateCreation
	 */
	// @Indexation(label="dateCreation",analyzed=false)
	// private Calendar dateCreation;

	public String toString() {
		return super.toString();
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entité
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new MvtProrataCNRAFactory();
	}

	public Double getMntProrataCNRA() {
		return this.mntProrataCNRA;
	}

	public void setMntProrataCNRA(Double mntProrataCNRA) {
		this.mntProrataCNRA = mntProrataCNRA;
	}

	public void setDatePriseEnCharge(Calendar datePriseEnCharge) {
		this.datePriseEnCharge = datePriseEnCharge;
	}

	public Calendar getDatePriseEnCharge() {
		return datePriseEnCharge;
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
