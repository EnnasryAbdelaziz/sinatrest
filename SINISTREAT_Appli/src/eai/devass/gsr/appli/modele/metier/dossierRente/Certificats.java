package eai.devass.gsr.appli.modele.metier.dossierRente;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.gsr.appli.manager.metier.dossierRente.CertificatsFactory;

/**
 * Certificats: Certificats
 * 
 * @author Nom Prenom (email)
 */
public class Certificats implements IEntite, ILockable {

	private long id;
	private static final long serialVersionUID = 1L;

	/**
	 * Date certificat Date Réception Certificat
	 */
	@Indexation(label = "Date certificat", analyzed = false)
	private Calendar dateReception;
	/**
	 * Nom médecin Observation Certificat
	 */
	@Indexation(label = "Nom médecin", analyzed = false)
	private String observation;
	/**
	 * Type certificat Type Certificat
	 */
	@Indexation(label = "Type certificat", analyzed = false)
	private Double typeCertificat;
	/**
	 * idCertificat Identification Certificat
	 */
	@Indexation(label = "idCertificat", analyzed = false)
	private Double idCertificat;
	/**
	 * numeroCertificat Numéro Certificat
	 */
	@Indexation(label = "numeroCertificat", analyzed = false)
	private Double numeroCertificat;
	/**
	 * periodeDU Période du
	 */
	@Indexation(label = "periodeDU", analyzed = false)
	private Calendar periodeDU;
	/**
	 * periodeAU Période AU
	 */
	@Indexation(label = "periodeAU", analyzed = false)
	private Calendar periodeAU;
	/**
	 * etat Etat
	 */
	@Indexation(label = "etat", analyzed = false)
	private Double etat;
	/**
	 * dateEtat Date Etat
	 */
	@Indexation(label = "dateEtat", analyzed = false)
	private Calendar dateEtat;
	/**
	 * validation Validation
	 */
	@Indexation(label = "validation", analyzed = false)
	private Boolean validation;
	/**
	 * dateValidation Date Validation
	 */
	@Indexation(label = "dateValidation", analyzed = false)
	private Calendar dateValidation;
	/**
	 * dateCreation
	 */
	@Indexation(label = "dateCreation", analyzed = false)
	private Calendar dateCreation;
	private Rentier refRentier;
	public String toString() {
		return String.valueOf(getId());
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entité
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new CertificatsFactory();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getDateReception() {
		return this.dateReception;
	}

	public void setDateReception(Calendar dateReception) {
		this.dateReception = dateReception;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Double getTypeCertificat() {
		return this.typeCertificat;
	}

	public void setTypeCertificat(Double typeCertificat) {
		this.typeCertificat = typeCertificat;
	}

	public Double getIdCertificat() {
		return this.idCertificat;
	}

	public void setIdCertificat(Double idCertificat) {
		this.idCertificat = idCertificat;
	}

	public Double getNumeroCertificat() {
		return this.numeroCertificat;
	}

	public void setNumeroCertificat(Double numeroCertificat) {
		this.numeroCertificat = numeroCertificat;
	}

	public Calendar getPeriodeDU() {
		return this.periodeDU;
	}

	public void setPeriodeDU(Calendar periodeDU) {
		this.periodeDU = periodeDU;
	}

	public Calendar getPeriodeAU() {
		return this.periodeAU;
	}

	public void setPeriodeAU(Calendar periodeAU) {
		this.periodeAU = periodeAU;
	}

	public Double getEtat() {
		return this.etat;
	}

	public void setEtat(Double etat) {
		this.etat = etat;
	}

	public Calendar getDateEtat() {
		return this.dateEtat;
	}

	public void setDateEtat(Calendar dateEtat) {
		this.dateEtat = dateEtat;
	}

	public Boolean getValidation() {
		return this.validation;
	}

	public void setValidation(Boolean validation) {
		this.validation = validation;
	}

	public Calendar getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(Calendar dateValidation) {
		this.dateValidation = dateValidation;
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

	public void setRefRentier(Rentier refRentier) {
		this.refRentier = refRentier;
	}

	public Rentier getRefRentier() {
		return refRentier;
	}

}
