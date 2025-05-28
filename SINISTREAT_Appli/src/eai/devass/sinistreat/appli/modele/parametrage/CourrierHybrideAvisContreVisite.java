package eai.devass.sinistreat.appli.modele.parametrage;

import java.util.Date;

public class CourrierHybrideAvisContreVisite implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
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
	private Date dateAccident;
	private Date dateContreVisite;
	private String heureContreVisite;
	private String nomVictime;
	private Date dateCreation;
	private String adresseVictime;
	private String nomClient;
	private String typeLettre;
	public String getAdressePrestataire() {
		return adressePrestataire;
	}
	
	
	public String getAdresseVictime() {
		return adresseVictime;
	}
	public Date getDateAccident() {
		return dateAccident;
	}
	
	
	
	
	public Date getDateContreVisite() {
		return dateContreVisite;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	

	public String getHeureContreVisite() {
		return heureContreVisite;
	}
	public String getIdLRH() {
		return idLRH;
	}

	public String getNomClient() {
		return nomClient;
	}
	public String getNomVictime() {
		return nomVictime;
	}



	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public String getTypeLettre() {
		return typeLettre;
	}
	public String getVillePrestataire() {
		return villePrestataire;
	}
	public void setAdressePrestataire(String adressePrestataire) {
		this.adressePrestataire = adressePrestataire;
	}
	public void setAdresseVictime(String adresseVictime) {
		this.adresseVictime = adresseVictime;
	}
	public void setDateAccident(Date dateAccident) {
		this.dateAccident = dateAccident;
	}
	public void setDateContreVisite(Date dateContreVisite) {
		this.dateContreVisite = dateContreVisite;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public void setHeureContreVisite(String heureContreVisite) {
		this.heureContreVisite = heureContreVisite;
	}
	public void setIdLRH(String idLRH) {
		this.idLRH = idLRH;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public void setNomVictime(String nomVictime) {
		this.nomVictime = nomVictime;
	}
	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}
	public void setTypeLettre(String typeLettre) {
		this.typeLettre = typeLettre;
	}
	public void setVillePrestataire(String villePrestataire) {
		this.villePrestataire = villePrestataire;
	}
	
}
