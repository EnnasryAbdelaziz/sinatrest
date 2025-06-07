package eai.devass.gsr.appli.modele.metier.mouvements;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.mouvements.HeritierFactory;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.StatutHeritier;

/**
 * Heritier:
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.mouvements.HeritierVO")
public class Heritier implements IEntite, ILockable,Comparable<Heritier> {

	private long id;
	private static final long serialVersionUID = 1L;

	/**
	 * Nom bénéficiaire
	 */
	@Indexation(label = "beneficiaire", analyzed = false)
	private String beneficiaire;

	/**
	 * Nom bénéficiaire
	 */
	@Indexation(label = "nomBeneficiaire", analyzed = false)
	private String nomBeneficiaire;

	/**
	 * @return the beneficiaire
	 */
	public String getBeneficiaire() {
		return beneficiaire;
	}

	/**
	 * @param beneficiaire
	 *            the beneficiaire to set
	 */
	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}

	/**
	 * Prénom bénéficiaire
	 */
	@Indexation(label = "prenomBeneficiaire", analyzed = false)
	private String prenomBeneficiaire;

	/**
	 * numCIN
	 */
	@Indexation(label = "numCIN", analyzed = false)
	private String numCIN;
	/**
	 * quotePart
	 */
	@Indexation(label = "quotePart", analyzed = false)
	private Double quotePart;
	/**
	 * dateCreation
	 */
	@Indexation(label = "dateCreation", analyzed = false)
	private Calendar dateCreation;
	/**
	 * montant
	 */
	@Indexation(label = "montant", analyzed = false)
	private Double montant;
	
	private MvtDecesRentier refMvtDecesRentier;

	private QuittanceGsr refQuittanceGSR;
	
	private StatutHeritier refStatutHeritier ;
	
	private transient Long ordreQtc;
	
	/**
	 * @return the montant
	 */
	public Double getMontant() {
		return montant;
	}

	/**
	 * @param montant
	 *            the montant to set
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	
	

	/**
	 * @return the refQuittanceGSR
	 */
	public QuittanceGsr getRefQuittanceGSR() {
		return refQuittanceGSR;
	}

	/**
	 * @param refQuittanceGSR
	 *            the refQuittanceGSR to set
	 */
	public void setRefQuittanceGSR(QuittanceGsr refQuittanceGSR) {
		this.refQuittanceGSR = refQuittanceGSR;
	}
	
	
	/**
	 * @return the refStatutHeritier
	 */
	public StatutHeritier getRefStatutHeritier() {
		return refStatutHeritier;
	}

	/**
	 * @param refStatutHeritier the refStatutHeritier to set
	 */
	public void setRefStatutHeritier(StatutHeritier refStatutHeritier) {
		this.refStatutHeritier = refStatutHeritier;
	}

	public String toString() {
		return String.valueOf(getId());
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entitÃ©
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new HeritierFactory();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the nomBeneficiaire
	 */
	public String getNomBeneficiaire() {
		return nomBeneficiaire;
	}

	/**
	 * @param nomBeneficiaire
	 *            the nomBeneficiaire to set
	 */
	public void setNomBeneficiaire(String nomBeneficiaire) {
		this.nomBeneficiaire = nomBeneficiaire;
	}

	/**
	 * @return the prenomBeneficiaire
	 */
	public String getPrenomBeneficiaire() {
		return prenomBeneficiaire;
	}

	/**
	 * @param prenomBeneficiaire
	 *            the prenomBeneficiaire to set
	 */
	public void setPrenomBeneficiaire(String prenomBeneficiaire) {
		this.prenomBeneficiaire = prenomBeneficiaire;
	}

	public String getNumCIN() {
		return this.numCIN;
	}

	public void setNumCIN(String numCIN) {
		this.numCIN = numCIN;
	}

	public Double getQuotePart() {
		return this.quotePart;
	}

	public void setQuotePart(Double quotePart) {
		this.quotePart = quotePart;
	}

	public Calendar getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public MvtDecesRentier getRefMvtDecesRentier() {
		return this.refMvtDecesRentier;
	}

	public void setRefMvtDecesRentier(MvtDecesRentier refMvtDecesRentier) {
		this.refMvtDecesRentier = refMvtDecesRentier;
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
	
	/**
	 * @return the ordreQtc
	 */
	public Long getOrdreQtc() {
		return ordreQtc;
	}

	/**
	 * @param ordreQtc the ordreQtc to set
	 */
	public void setOrdreQtc(Long ordreQtc) {
		this.ordreQtc = ordreQtc;
	}

	public int compareTo(Heritier compareHeritier) {
		 
		//ascending order
		return (int)(this.id - compareHeritier.getId());

	}

	// wmos: correction sonar 26/09/2014
	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32)) ;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Heritier))
			return false;
		Heritier other = (Heritier) obj;
		if (id != other.id)
			return false;
		return true;
	}	
	// wmos: correction sonar 26/09/2014

}

