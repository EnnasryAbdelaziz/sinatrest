package eai.devass.sinistreat.appli.valueobjects.parametrage;

import java.util.Date;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class CourrierHybrideReclamationVO implements IValueObject{
	
	private String idLRH;
	private String numeroSinistre;
	private String numeroGrave;
	private String nomClient;
	private String dateAccident;
	private String dateCreation;
	private String nomBeneficiaireLettre;
	private String nomUserCreateur;
	private String nomVictime;
	private String gsm;
	private String dateGeneration;
	public String getGsm() {
		return gsm;
	}
	public void setGsm(String gsm) {
		this.gsm = gsm;
	}
	public String getDateGeneration() {
		return dateGeneration;
	}
	public void setDateGeneration(String dateGeneration) {
		this.dateGeneration = dateGeneration;
	}
	public String getNomUserCreateur() {
		return nomUserCreateur;
	}
	public void setNomUserCreateur(String nomUserCreateur) {
		this.nomUserCreateur = nomUserCreateur;
	}
	public String getNomVictime() {
		return nomVictime;
	}
	public void setNomVictime(String nomVictime) {
		this.nomVictime = nomVictime;
	}
	public String getNomBeneficiaireLettre() {
		return nomBeneficiaireLettre;
	}
	public void setNomBeneficiaireLettre(String nomBeneficiaireLettre) {
		this.nomBeneficiaireLettre = nomBeneficiaireLettre;
	}
	public String getAdressBeneficiaireLettre() {
		return adressBeneficiaireLettre;
	}
	public void setAdressBeneficiaireLettre(String adressBeneficiaireLettre) {
		this.adressBeneficiaireLettre = adressBeneficiaireLettre;
	}
	public String getVilleBeneficiaireLettre() {
		return villeBeneficiaireLettre;
	}
	public void setVilleBeneficiaireLettre(String villeBeneficiaireLettre) {
		this.villeBeneficiaireLettre = villeBeneficiaireLettre;
	}
	public String getvRef() {
		return vRef;
	}
	public void setvRef(String vRef) {
		this.vRef = vRef;
	}
	public String getPiece() {
		return piece;
	}
	public void setPiece(String piece) {
		this.piece = piece;
	}
	
	

	private String adressBeneficiaireLettre;
	private String villeBeneficiaireLettre;

	private String vRef;
	private String piece;

	



	public String getIdLRH() {
		return idLRH;
	}
	public void setIdLRH(String idLRH) {
		this.idLRH = idLRH;
	}
	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}
	public String getNumeroGrave() {
		return numeroGrave;
	}
	public void setNumeroGrave(String numeroGrave) {
		this.numeroGrave = numeroGrave;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}

	
	
	
	

	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
}