package eai.devass.edition.beans;

public class SinistreBean {
	
	//Evo 13/08/2015
	private String numeroGrave="";
	private String dateAccident="";
	private String nomClient="";
	private String victime="";
	private String dateITT="";
	private String dateReprise="";
	private String salaireJournalier="";
	private String totalEnLettre="";
	private String nombreJour="";
	private String total="";
	private String numeroSinistre="";
	private String montant="";
	
	
	public SinistreBean(String numeroGrave, String dateAccident,
			String nomClient, String victime, String dateITT,
			String dateReprise, String salaireJournalier, String totalEnLettre,
			String nombreJour,String total,String numeroSinistre,String montant) {
		super();
		this.numeroGrave = numeroGrave;
		this.dateAccident = dateAccident;
		this.nomClient = nomClient;
		this.victime = victime;
		this.dateITT = dateITT;
		this.dateReprise = dateReprise;
		this.salaireJournalier = salaireJournalier;
		this.totalEnLettre = totalEnLettre;
		this.nombreJour = nombreJour;
		this.total = total;
		this.numeroSinistre = numeroSinistre;
		this.montant = montant;
		}

	

	public SinistreBean() {
		super();
	}



	public String getNumeroGrave() {
		return numeroGrave;
	}



	public void setNumeroGrave(String numeroGrave) {
		this.numeroGrave = numeroGrave;
	}



	public String getDateAccident() {
		return dateAccident;
	}



	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}



	public String getNomClient() {
		return nomClient;
	}



	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}



	public String getVictime() {
		return victime;
	}



	public void setVictime(String victime) {
		this.victime = victime;
	}



	public String getDateITT() {
		return dateITT;
	}



	public void setDateITT(String dateITT) {
		this.dateITT = dateITT;
	}



	public String getDateReprise() {
		return dateReprise;
	}



	public void setDateReprise(String dateReprise) {
		this.dateReprise = dateReprise;
	}



	public String getSalaireJournalier() {
		return salaireJournalier;
	}



	public void setSalaireJournalier(String salaireJournalier) {
		this.salaireJournalier = salaireJournalier;
	}



	public void setTotalEnLettre(String totalEnLettre) {
		this.totalEnLettre = totalEnLettre;
	}



	public String getTotalEnLettre() {
		return totalEnLettre;
	}



	public void setNombreJour(String nombreJour) {
		this.nombreJour = nombreJour;
	}



	public String getNombreJour() {
		return nombreJour;
	}



	public void setTotal(String total) {
		this.total = total;
	}



	public String getTotal() {
		return total;
	}



	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}



	public String getNumeroSinistre() {
		return numeroSinistre;
	}



	public void setMontant(String montant) {
		this.montant = montant;
	}



	public String getMontant() {
		return montant;
	}



	
}
