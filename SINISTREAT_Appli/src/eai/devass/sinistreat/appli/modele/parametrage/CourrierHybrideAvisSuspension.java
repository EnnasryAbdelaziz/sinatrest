package eai.devass.sinistreat.appli.modele.parametrage;

import java.util.Date;

public class CourrierHybrideAvisSuspension implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String idLRH;
	private String numeroSinistre;
	private Date dateAccident;
	private Date dateContreVisiteFirst;
	private Date dateContreVisiteSecond;
	private String nomVictime;
	private Date dateCreation;
	private String adresseVictime;
	private String nomClient;
	private String typeLettre;

	
	
	public String getAdresseVictime() {
		return adresseVictime;
	}
	public Date getDateAccident() {
		return dateAccident;
	}
	public Date getDateContreVisiteFirst() {
		return dateContreVisiteFirst;
	}
	public Date getDateCreation() {
		return dateCreation;
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

	public void setAdresseVictime(String adresseVictime) {
		this.adresseVictime = adresseVictime;
	}
	public void setDateAccident(Date dateAccident) {
		this.dateAccident = dateAccident;
	}
	public void setDateContreVisiteFirst(Date dateContreVisiteFirst) {
		this.dateContreVisiteFirst = dateContreVisiteFirst;
	}
	public Date getDateContreVisiteSecond() {
		return dateContreVisiteSecond;
	}
	public void setDateContreVisiteSecond(Date dateContreVisiteSecond) {
		this.dateContreVisiteSecond = dateContreVisiteSecond;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
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

	
}
