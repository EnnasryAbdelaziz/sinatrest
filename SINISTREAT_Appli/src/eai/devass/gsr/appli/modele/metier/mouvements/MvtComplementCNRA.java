package eai.devass.gsr.appli.modele.metier.mouvements;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtComplementCNRAFactory;
import eai.devass.gsr.appli.modele.parametrage.MotifComplementCNRA;

/**
 * MvtComplementCNRA:
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtComplementCNRAVO")
public class MvtComplementCNRA extends MvtConsignCNRA implements IEntite,
		ILockable {
	private static final long serialVersionUID = 1L;
	/**
	 * jugementFinal
	 */
	private Boolean jugementFinal;
	/**
	 * complementRente
	 */
	private Double complementRente;
	/**
	 * mntComplementCalcule
	 */
	private Double mntComplementCalcule;
	/**
	 * arreragesCalcules
	 */
	private Double arreragesCalcules;
	/**
	 * arreragesARegler
	 */
	private Double arrerageARegler;
	/**
	 * datDepartAugmentation
	 */
	private Calendar datDepartAugmentation;
	/**
	 * datePriseEnCharge
	 */
	private Calendar datePriseEnCharge;
	
	/**
	 * mntComplementCNRA
	 */
	private Double mntComplementCNRA;

	/**
	 * parentDecede
	 */
	private String parentDecede;

	private MotifComplementCNRA refMotifComplementCNRA;
	
	
	
	/**
	 * Montant total du complément CNRA
	 **/
	private Double mntTotalComplementCNRA;
	
	public String toString() {
		return super.toString();
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entitÃ©
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new MvtComplementCNRAFactory();
	}

	public Double getMntComplementCalcule() {
		return this.mntComplementCalcule;
	}

	public void setMntComplementCalcule(Double mntComplementCalcule) {
		this.mntComplementCalcule = mntComplementCalcule;
	}

	public Calendar getDatDepartAugmentation() {
		return this.datDepartAugmentation;
	}

	public void setDatDepartAugmentation(Calendar datDepartAugmentation) {
		this.datDepartAugmentation = datDepartAugmentation;
	}

	public Double getMntComplementCNRA() {
		return this.mntComplementCNRA;
	}

	public void setMntComplementCNRA(Double mntComplementCNRA) {
		this.mntComplementCNRA = mntComplementCNRA;
	}
	public String getParentDecede() {
		return this.parentDecede;
	}

	public void setParentDecede(String parentDecede) {
		this.parentDecede = parentDecede;
	}
	public MotifComplementCNRA getRefMotifComplementCNRA() {
		return this.refMotifComplementCNRA;
	}

	public void setRefMotifComplementCNRA(
			MotifComplementCNRA refMotifComplementCNRA) {
		this.refMotifComplementCNRA = refMotifComplementCNRA;
	}
	public void setDatePriseEnCharge(Calendar datePriseEnCharge) {
		this.datePriseEnCharge = datePriseEnCharge;
	}

	public Calendar getDatePriseEnCharge() {
		return datePriseEnCharge;
	}

	public void setComplementRente(Double complementRente) {
		this.complementRente = complementRente;
	}

	public Double getComplementRente() {
		return complementRente;
	}

	public void setJugementFinal(Boolean jugementFinal) {
		this.jugementFinal = jugementFinal;
	}

	public Boolean getJugementFinal() {
		return jugementFinal;
	}

	public void setArreragesCalcules(Double arreragesCalcules) {
		this.arreragesCalcules = arreragesCalcules;
	}

	public Double getArreragesCalcules() {
		return arreragesCalcules;
	}

	public Double getArrerageARegler() {
		return arrerageARegler;
	}

	public void setArrerageARegler(Double arrerageARegler) {
		this.arrerageARegler = arrerageARegler;
	}
	
	/**
	 * @return the mntTotalComplementCNRA
	 */
	public Double getMntTotalComplementCNRA() {
		return mntTotalComplementCNRA;
	}

	/**
	 * @param mntTotalComplementCNRA the mntTotalComplementCNRA to set
	 */
	public void setMntTotalComplementCNRA(Double mntTotalComplementCNRA) {
		this.mntTotalComplementCNRA = mntTotalComplementCNRA;
	}

}
