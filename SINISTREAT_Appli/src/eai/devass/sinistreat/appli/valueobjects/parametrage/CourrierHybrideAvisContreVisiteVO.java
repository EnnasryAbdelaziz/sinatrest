package eai.devass.sinistreat.appli.valueobjects.parametrage;

import java.util.Date;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class CourrierHybrideAvisContreVisiteVO implements IValueObject{
	private String idLRH;
	private String villePrestataire;

	private String numeroSinistre;
	private String adressePrestataire;
	private String nomPrestataire;
	public String getNomPrestataire() {
		return nomPrestataire;
	}
	public void setNomPrestataire(String nomPrestataire) {
		this.nomPrestataire = nomPrestataire;
	}
	private String dateAccident;
	private String dateContreVisite;
	private String heureContreVisite;
	private String nomVictime;
	private String dateCreation;
	private String adresseVictime;
	private String nomClient;
	private String typeLettre;
	public String getIdLRH() {
		return idLRH;
	}
	public void setIdLRH(String idLRH) {
		this.idLRH = idLRH;
	}
	public String getVillePrestataire() {
		return villePrestataire;
	}
	public void setVillePrestataire(String villePrestataire) {
		this.villePrestataire = villePrestataire;
	}
	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}
	public String getAdressePrestataire() {
		return adressePrestataire;
	}
	public void setAdressePrestataire(String adressePrestataire) {
		this.adressePrestataire = adressePrestataire;
	}
	public String getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}
	public String getDateContreVisite() {
		return dateContreVisite;
	}
	public void setDateContreVisite(String dateContreVisite) {
		this.dateContreVisite = dateContreVisite;
	}
	public String getHeureContreVisite() {
		return heureContreVisite;
	}
	public void setHeureContreVisite(String heureContreVisite) {
		this.heureContreVisite = heureContreVisite;
	}
	public String getNomVictime() {
		return nomVictime;
	}
	public void setNomVictime(String nomVictime) {
		this.nomVictime = nomVictime;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getAdresseVictime() {
		return adresseVictime;
	}
	public void setAdresseVictime(String adresseVictime) {
		this.adresseVictime = adresseVictime;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getTypeLettre() {
		return typeLettre;
	}
	public void setTypeLettre(String typeLettre) {
		this.typeLettre = typeLettre;
	}

	
}
