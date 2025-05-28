package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class CourrierHybrideQuittanceVO implements IValueObject{
	
	private String idLRH;
	private String numeroSinistre;
	private String numeroGrave;
	private String nomClient;
	private String dateAccident;
	private String victime;
	private String numeroQuittance;
	private String initiales;
	private String nomUserCreateur;
	private String nomBeneficiaireLettre;
	private String adresseBeneficiaireLettre;
	private String villeBeneficiaireLettre;
	private String numeroDossierTribunal;
	private String lblJuridiction;
	private String dateDecision;
	private String libellePrestation;
	private String montant;
	private String ipp;
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
	public String getVictime() {
		return victime;
	}
	public void setVictime(String victime) {
		this.victime = victime;
	}
	public String getNumeroQuittance() {
		return numeroQuittance;
	}
	public void setNumeroQuittance(String numeroQuittance) {
		this.numeroQuittance = numeroQuittance;
	}
	public String getInitiales() {
		return initiales;
	}
	public void setInitiales(String initiales) {
		this.initiales = initiales;
	}
	public String getNomUserCreateur() {
		return nomUserCreateur;
	}
	public void setNomUserCreateur(String nomUserCreateur) {
		this.nomUserCreateur = nomUserCreateur;
	}
	public String getNomBeneficiaireLettre() {
		return nomBeneficiaireLettre;
	}
	public void setNomBeneficiaireLettre(String nomBeneficiaireLettre) {
		this.nomBeneficiaireLettre = nomBeneficiaireLettre;
	}
	public String getAdresseBeneficiaireLettre() {
		return adresseBeneficiaireLettre;
	}
	public void setAdresseBeneficiaireLettre(String adresseBeneficiaireLettre) {
		this.adresseBeneficiaireLettre = adresseBeneficiaireLettre;
	}
	public String getVilleBeneficiaireLettre() {
		return villeBeneficiaireLettre;
	}
	public void setVilleBeneficiaireLettre(String villeBeneficiaireLettre) {
		this.villeBeneficiaireLettre = villeBeneficiaireLettre;
	}
	public String getNumeroDossierTribunal() {
		return numeroDossierTribunal;
	}
	public void setNumeroDossierTribunal(String numeroDossierTribunal) {
		this.numeroDossierTribunal = numeroDossierTribunal;
	}
	public String getLblJuridiction() {
		return lblJuridiction;
	}
	public void setLblJuridiction(String lblJuridiction) {
		this.lblJuridiction = lblJuridiction;
	}
	public String getDateDecision() {
		return dateDecision;
	}
	public void setDateDecision(String dateDecision) {
		this.dateDecision = dateDecision;
	}
	public String getLibellePrestation() {
		return libellePrestation;
	}
	public void setLibellePrestation(String libellePrestation) {
		this.libellePrestation = libellePrestation;
	}
	public String getMontant() {
		return montant;
	}
	public void setMontant(String montant) {
		this.montant = montant;
	}
	public String getIpp() {
		return ipp;
	}
	public void setIpp(String ipp) {
		this.ipp = ipp;
	}
}