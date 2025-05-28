package eai.devass.sinistreat.appli.modele.parametrage;

import java.util.Date;

public class CourrierHybrideReclamation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String idLRH;
	private String numeroSinistre;
	private String numeroGrave;
	private String nomClient;
	private Date dateAccident;
	private Date dateCreation;
	private String nomUserCreateur;

	private String nomVictime;

	private String nomBeneficiaireLettre;

	private String adressBeneficiaireLettre;

	private String villeBeneficiaireLettre;

	private String vRef;

	private String piece;

	private String gsm;
	private Date dateGeneration;
	
	public String getGsm() {
		return gsm;
	}
	public void setGsm(String gsm) {
		this.gsm = gsm;
	}
	public Date getDateGeneration() {
		return dateGeneration;
	}
	public void setDateGeneration(Date dateGeneration) {
		this.dateGeneration = dateGeneration;
	}
	public String getAdressBeneficiaireLettre() {
		return adressBeneficiaireLettre;
	}
	public Date getDateAccident() {
		return dateAccident;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public String getIdLRH() {
		return idLRH;
	}

	public String getNomBeneficiaireLettre() {
		return nomBeneficiaireLettre;
	}

	public String getNomClient() {
		return nomClient;
	}

	public String getNomUserCreateur() {
		return nomUserCreateur;
	}

	public String getNomVictime() {
		return nomVictime;
	}

	public String getNumeroGrave() {
		return numeroGrave;
	}

	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public String getPiece() {
		return piece;
	}

	public String getVilleBeneficiaireLettre() {
		return villeBeneficiaireLettre;
	}
	public String getvRef() {
		return vRef;
	}

	public void setAdressBeneficiaireLettre(String adressBeneficiaireLettre) {
		this.adressBeneficiaireLettre = adressBeneficiaireLettre;
	}

	public void setDateAccident(Date dateAccident) {
		this.dateAccident = dateAccident;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setIdLRH(String idLRH) {
		this.idLRH = idLRH;
	}

	public void setNomBeneficiaireLettre(String nomBeneficiaireLettre) {
		this.nomBeneficiaireLettre = nomBeneficiaireLettre;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public void setNomUserCreateur(String nomUserCreateur) {
		this.nomUserCreateur = nomUserCreateur;
	}

	public void setNomVictime(String nomVictime) {
		this.nomVictime = nomVictime;
	}

	public void setNumeroGrave(String numeroGrave) {
		this.numeroGrave = numeroGrave;
	}

	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public void setVilleBeneficiaireLettre(String villeBeneficiaireLettre) {
		this.villeBeneficiaireLettre = villeBeneficiaireLettre;
	}

	public void setvRef(String vRef) {
		this.vRef = vRef;
	}

}