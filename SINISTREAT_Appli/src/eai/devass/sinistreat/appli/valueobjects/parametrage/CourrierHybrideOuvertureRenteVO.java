package eai.devass.sinistreat.appli.valueobjects.parametrage;

import java.util.Date;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class CourrierHybrideOuvertureRenteVO implements IValueObject{
	private String idLRH;
	private String ville;
	private String lienParente;
	private String userSasCreateur;
	private String dateAccident;
	private String dateConstitution;
	private String dateLimiteConsignation;
	private String numeroRente;
	private String dateCreation;
	private String adresse;
	private String nomClient;
	private String compagnieRente;
	private String gsm;
	private String dateGeneration;
	private String numeroSinistre;

	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}

	public String getDateGeneration() {
		return dateGeneration;
	}
	public void setDateGeneration(String dateGeneration) {
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
	public String getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}
	public String getDateConstitution() {
		return dateConstitution;
	}
	public void setDateConstitution(String dateConstitution) {
		this.dateConstitution = dateConstitution;
	}
	public String getDateLimiteConsignation() {
		return dateLimiteConsignation;
	}
	public void setDateLimiteConsignation(String dateLimiteConsignation) {
		this.dateLimiteConsignation = dateLimiteConsignation;
	}
	public String getNumeroRente() {
		return numeroRente;
	}
	public void setNumeroRente(String numeroRente) {
		this.numeroRente = numeroRente;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
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
}
