package eai.devass.sinistreat.appli.modele.parametrage;

import java.util.Date;

public class CourrierHybrideOpposition implements java.io.Serializable {
	
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
	private String nomResponsableCivile;
	private String nomConducteur;
	private String nomOperateur;
	public String getNomOperateur() {
		return nomOperateur;
	}

	public void setNomOperateur(String nomOperateur) {
		this.nomOperateur = nomOperateur;
	}

	private String gsm;
	private Date dateGeneration;
	private String policeCompagnieAdverse;
	private String villeCompagnieAdverse;
	private String marque;
	private String immatriculation;
	private String nomCompagnieAdverse;
	private String adresseCompagnieAdverse;
	private String nomCompletVictime;
	public String getAdresseCompagnieAdverse() {
		return adresseCompagnieAdverse;
	}
	public Date getDateAccident() {
		return dateAccident;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public Date getDateGeneration() {
		return dateGeneration;
	}
	public String getGsm() {
		return gsm;
	}
	public String getIdLRH() {
		return idLRH;
	}
	public String getImmatriculation() {
		return immatriculation;
	}
	public String getMarque() {
		return marque;
	}
	public String getNomClient() {
		return nomClient;
	}
	public String getNomCompagnieAdverse() {
		return nomCompagnieAdverse;
	}
	public String getNomCompletVictime() {
		return nomCompletVictime;
	}



	public String getNomConducteur() {
		return nomConducteur;
	}
	public String getNomResponsableCivile() {
		return nomResponsableCivile;
	}
	public String getNumeroGrave() {
		return numeroGrave;
	}
	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public String getPoliceCompagnieAdverse() {
		return policeCompagnieAdverse;
	}
	public String getVilleCompagnieAdverse() {
		return villeCompagnieAdverse;
	}
	public void setAdresseCompagnieAdverse(String adresseCompagnieAdverse) {
		this.adresseCompagnieAdverse = adresseCompagnieAdverse;
	}
	public void setDateAccident(Date dateAccident) {
		this.dateAccident = dateAccident;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public void setDateGeneration(Date dateGeneration) {
		this.dateGeneration = dateGeneration;
	}
	public void setGsm(String gsm) {
		this.gsm = gsm;
	}
	public void setIdLRH(String idLRH) {
		this.idLRH = idLRH;
	}

	
	
	
	

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public void setNomCompagnieAdverse(String nomCompagnieAdverse) {
		this.nomCompagnieAdverse = nomCompagnieAdverse;
	}
	public void setNomCompletVictime(String nomCompletVictime) {
		this.nomCompletVictime = nomCompletVictime;
	}
	public void setNomConducteur(String nomConducteur) {
		this.nomConducteur = nomConducteur;
	}
	public void setNomResponsableCivile(String nomResponsableCivile) {
		this.nomResponsableCivile = nomResponsableCivile;
	}
	public void setNumeroGrave(String numeroGrave) {
		this.numeroGrave = numeroGrave;
	}
	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}
	public void setPoliceCompagnieAdverse(String policeCompagnieAdverse) {
		this.policeCompagnieAdverse = policeCompagnieAdverse;
	}
	public void setVilleCompagnieAdverse(String villeCompagnieAdverse) {
		this.villeCompagnieAdverse = villeCompagnieAdverse;
	}
	
	
}