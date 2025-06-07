package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.trace.ITracable;
import eai.devass.commun.appli.converter.AConverter;

/**
 * Value Object de MvtComplementCNRA
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.mouvements.MvtComplementCNRA")
public class MvtComplementCNRAVO extends MvtConsignCNRAVO implements ITracable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * jugementFinal
	 */
	@AConverter(propertyDist = "jugementFinal")
	private String jugementFinal;
	/**
	 * complementRente
	 */
	@AConverter(propertyDist = "complementRente")
	private String complementRente;
	/**
	 * mntComplementCalcule
	 */
	@AConverter(propertyDist = "mntComplementCalcule")
	private String mntComplementCalcule;
	/**
	 * arreragesCalcules
	 */
	@AConverter(propertyDist = "arreragesCalcules")
	private String arreragesCalcules;
	/**
	 * arreragesARegler
	 */
	@AConverter(propertyDist = "arrerageARegler")
	private String arrerageARegler;
	/**
	 * datDepartAugmentation
	 */
	@AConverter(propertyDist = "datDepartAugmentation", pattern = "yyyyMMdd")
	private String datDepartAugmentation;
	/**
	 * datePriseEnCharge
	 */
	@AConverter(propertyDist = "datePriseEnCharge", pattern = "yyyyMMdd")
	private String datePriseEnCharge;
	/**
	 * mntComplementCNRA
	 */
	@AConverter(propertyDist = "mntComplementCNRA")
	private String mntComplementCNRA;
	/**
	 * mntTotalComplementCNRA
	 */
	@AConverter(propertyDist = "mntTotalComplementCNRA")
	private String mntTotalComplementCNRA;
	/**
	 * parentDecede
	 */
	@AConverter(propertyDist = "parentDecede")
	private String parentDecede;
	/**
	 * refMotifComplementCNRA
	 */
	@AConverter(propertyDist = "refMotifComplementCNRA.id")
	private String refMotifComplementCNRA;
	@AConverter(propertyDist = "refMotifComplementCNRA.libelle")
	private String refMotifComplementCNRALibelle;

	public String getJugementFinal() {
		return jugementFinal;
	}

	public void setJugementFinal(String jugementFinal) {
		this.jugementFinal = jugementFinal;
	}

	public String getComplementRente() {
		return complementRente;
	}

	public void setComplementRente(String complementRente) {
		this.complementRente = complementRente;
	}

	public String getMntComplementCalcule() {
		return mntComplementCalcule;
	}

	public void setMntComplementCalcule(String mntComplementCalcule) {
		this.mntComplementCalcule = mntComplementCalcule;
	}

	public String getArreragesCalcules() {
		return arreragesCalcules;
	}

	public void setArreragesCalcules(String arreragesCalcules) {
		this.arreragesCalcules = arreragesCalcules;
	}

	public String getDatDepartAugmentation() {
		return datDepartAugmentation;
	}

	public void setDatDepartAugmentation(String datDepartAugmentation) {
		this.datDepartAugmentation = datDepartAugmentation;
	}

	public String getDatePriseEnCharge() {
		return datePriseEnCharge;
	}

	public void setDatePriseEnCharge(String datePriseEnCharge) {
		this.datePriseEnCharge = datePriseEnCharge;
	}

	public String getMntComplementCNRA() {
		return mntComplementCNRA;
	}

	public void setMntComplementCNRA(String mntComplementCNRA) {
		this.mntComplementCNRA = mntComplementCNRA;
	}

	public String getParentDecede() {
		return parentDecede;
	}

	public void setParentDecede(String parentDecede) {
		this.parentDecede = parentDecede;
	}

	public String getRefMotifComplementCNRA() {
		return refMotifComplementCNRA;
	}

	public void setRefMotifComplementCNRA(String refMotifComplementCNRA) {
		this.refMotifComplementCNRA = refMotifComplementCNRA;
	}

	public void setRefMotifComplementCNRALibelle(
			String refMotifComplementCNRALibelle) {
		this.refMotifComplementCNRALibelle = refMotifComplementCNRALibelle;
	}

	public String getRefMotifComplementCNRALibelle() {
		return refMotifComplementCNRALibelle;
	}

	public String getArrerageARegler() {
		return arrerageARegler;
	}

	public void setArrerageARegler(String arrerageARegler) {
		this.arrerageARegler = arrerageARegler;
	}
	
	/**
	 * @return the mntTotalComplementCNRA
	 */
	public String getMntTotalComplementCNRA() {
		return mntTotalComplementCNRA;
	}

	/**
	 * @param mntTotalComplementCNRA the mntTotalComplementCNRA to set
	 */
	public void setMntTotalComplementCNRA(String mntTotalComplementCNRA) {
		this.mntTotalComplementCNRA = mntTotalComplementCNRA;
	}

}
