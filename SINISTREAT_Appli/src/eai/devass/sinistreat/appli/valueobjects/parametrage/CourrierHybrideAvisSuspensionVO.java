package eai.devass.sinistreat.appli.valueobjects.parametrage;

import java.util.Date;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class CourrierHybrideAvisSuspensionVO implements IValueObject{
	private String idLRH;
	private String numeroSinistre;

	private String dateAccident;
	private String dateContreVisiteFirst;
	private String dateContreVisiteSecond;

	public String getDateContreVisiteSecond() {
		return dateContreVisiteSecond;
	}
	public void setDateContreVisiteSecond(String dateContreVisiteSecond) {
		this.dateContreVisiteSecond = dateContreVisiteSecond;
	}
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
	
	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}

	public String getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}
	public String getDateContreVisiteFirst() {
		return dateContreVisiteFirst;
	}
	public void setDateContreVisiteFirst(String dateContreVisiteFirst) {
		this.dateContreVisiteFirst = dateContreVisiteFirst;
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
