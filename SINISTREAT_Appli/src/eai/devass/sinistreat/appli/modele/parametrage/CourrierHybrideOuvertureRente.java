package eai.devass.sinistreat.appli.modele.parametrage;

import java.util.Date;

public class CourrierHybrideOuvertureRente implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String idLRH;
	private String ville;
	private String numeroSinistre;

	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}

	private String lienParente;
	private String userSasCreateur;
	private Date dateAccident;
	private Date dateConstitution;
	private Date dateLimiteConsignation;
	private String numeroRente;
	private Date dateCreation;
	private String adresse;
	private String nomClient;
	private String compagnieRente;
	private String gsm;
	private Date dateGeneration;
	public Date getDateGeneration() {
		return dateGeneration;
	}
	public void setDateGeneration(Date dateGeneration) {
		this.dateGeneration = dateGeneration;
	}
	public String getIdLRH() {
		return idLRH;
	}
	public void setIdLRH(String idLRH) {
		this.idLRH = idLRH;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getLienParente() {
		return lienParente;
	}
	public void setLienParente(String lienParente) {
		this.lienParente = lienParente;
	}
	public String getUserSasCreateur() {
		return userSasCreateur;
	}
	public void setUserSasCreateur(String userSasCreateur) {
		this.userSasCreateur = userSasCreateur;
	}
	public Date getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(Date dateAccident) {
		this.dateAccident = dateAccident;
	}
	public Date getDateConstitution() {
		return dateConstitution;
	}
	public void setDateConstitution(Date dateConstitution) {
		this.dateConstitution = dateConstitution;
	}
	public Date getDateLimiteConsignation() {
		return dateLimiteConsignation;
	}
	public void setDateLimiteConsignation(Date dateLimiteConsignation) {
		this.dateLimiteConsignation = dateLimiteConsignation;
	}
	public String getNumeroRente() {
		return numeroRente;
	}
	public void setNumeroRente(String numeroRente) {
		this.numeroRente = numeroRente;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getCompagnieRente() {
		return compagnieRente;
	}
	public void setCompagnieRente(String compagnieRente) {
		this.compagnieRente = compagnieRente;
	}
	public String getGsm() {
		return gsm;
	}
	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
