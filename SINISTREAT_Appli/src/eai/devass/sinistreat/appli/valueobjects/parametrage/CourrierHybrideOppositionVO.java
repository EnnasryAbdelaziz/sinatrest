package eai.devass.sinistreat.appli.valueobjects.parametrage;

import java.util.Date;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class CourrierHybrideOppositionVO implements IValueObject{
	
	private String idLRH;
	private String nomClient;

	private String numeroGrave;

	private String numeroSinistre;
	private String dateAccident;
	private String idCourrier;
	private String nomResponsableCivile;
	private String dateCreation;
	private String nomConducteur;
	private String nomOperateur;
	public String getNomOperateur() {
		return nomOperateur;
	}

	public void setNomOperateur(String nomOperateur) {
		this.nomOperateur = nomOperateur;
	}

	private String villeCompagnieAdverse;
	private String policeCompagnieAdverse;
	private String gsm;
	private String dateGeneration;
	private String nomCompletVictime;
	private String marque;
	private String immatriculation;
	private String nomCompagnieAdverse;

	private String adresseCompagnieAdverse;

	public String getAdresseCompagnieAdverse() {
		return adresseCompagnieAdverse;
	}

	public String getDateAccident() {
		return dateAccident;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public String getDateGeneration() {
		return dateGeneration;
	}

	public String getGsm() {
		return gsm;
	}

	public String getIdCourrier() {
		return idCourrier;
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

	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setDateGeneration(String dateGeneration) {
		this.dateGeneration = dateGeneration;
	}



	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public void setIdCourrier(String idCourrier) {
		this.idCourrier = idCourrier;
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