package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import java.text.SimpleDateFormat;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.entites.IDate;

public class PoliceVO implements IValueObject  {
	      private SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	    private String id="";
	    private int num=0;
	
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		private String codeClassification=""  ;
		public String getCodeClassification() {
			return codeClassification;
		}
		public void setCodeClassification(String codeClassification) {
			this.codeClassification = codeClassification;
		}
		public String getAdresseAssure() {
			return adresseAssure;
		}
		public void setAdresseAssure(String adresseAssure) {
			this.adresseAssure = adresseAssure;
		}
		public String getCodeActivite() {
			return codeActivite;
		}
		public void setCodeActivite(String codeActivite) {
			this.codeActivite = codeActivite;
		}
		private String adresseAssure=""  ;
		private String codeActivite=""  ;
	
		
	private String forfaitOurevis=""  ;	
	private String numeroPolice=""  ;
	private String numeroPoliceExRMA="";
	private String codeClient=""      ;   
	private String typeIntermediaire=""  ;
	private String codeIntermediaire=""  ;      
	private String nomIntermediaire=""   ;      
	private String nomClient=""      ;      
	private String numeroSouscription=""  ;
	private String userCreateur=""  ;           
	private String dateCreation=""  ;  
	private String etat="";
	private String dateEtat="";
	private String dateExpiration="";
	private String dateEcheance="";
	private String dateEffet="";
	private String codeEtatOuverture=""   ;
	private String dateEtatOuverture=""   ;
	protected String dateOccurence=""   ;
	private String typeContrat=""   ;
	private String codeEntrepriseParticulier="" ;
	// etat a l'ouverture et a la survenance 
	private String etatSurvenance = "";
	private String dateSurvenance = "";
	private String dateOuvertuteSinistre="";
	private String dateSurvenanceSinistre="";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumeroPolice() {
		return numeroPolice;
	}
	public void setNumeroPolice(String numeroPolice) {
		this.numeroPolice = numeroPolice;
	}
	public String getCodeClient() {
		return codeClient;
	}
	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}
	public String getCodeIntermediaire() {
		return codeIntermediaire;
	}
	public void setCodeIntermediaire(String codeIntermediaire) {
		this.codeIntermediaire = codeIntermediaire;
	}
	public String getNomIntermediaire() {
		return nomIntermediaire;
	}
	public void setNomIntermediaire(String nomIntermediaire) {
		this.nomIntermediaire = nomIntermediaire;
	}
	public String getNomClient() {
		return nomClient;
	}
	public String getForfaitOurevis() {
		return forfaitOurevis;
	}
	public void setForfaitOurevis(String forfaitOurevis) {
		
		if("O".equals(forfaitOurevis)){
			this.forfaitOurevis="Forfaitaire";
		}
		else{
			this.forfaitOurevis="Revisable";
		}
		
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getUserCreateur() {
		return userCreateur;
	}
	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setNumeroPoliceExRMA(String numeroPoliceExRMA) {
		this.numeroPoliceExRMA = numeroPoliceExRMA;
	}
	public String getNumeroPoliceExRMA() {
		return numeroPoliceExRMA;
	}

	public String getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	public String getDateEcheance() {
		return dateEcheance;
	}
	public void setDateEcheance(String dateEcheance) {
		this.dateEcheance = dateEcheance;
	}
	public String getDateEffet() {
		return dateEffet;
	}
	public void setDateEffet(String dateEffet) {
		this.dateEffet = dateEffet;
	}
	public String getCodeEtatOuverture() {
		return codeEtatOuverture;
	}
	public void setCodeEtatOuverture(String codeEtatOuverture) {
		this.codeEtatOuverture = codeEtatOuverture;
	}
	public String getDateEtatOuverture() {
		return dateEtatOuverture;
	}
	public void setDateEtatOuverture(String dateEtatOuverture) {
		this.dateEtatOuverture = dateEtatOuverture;
	}
	public String getTypeContrat() {
		return typeContrat;
	}
	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getDateEtat() {
		return dateEtat;
	}
	public void setDateEtat(String dateEtat) {
		this.dateEtat = dateEtat;
	}
	public String getNumeroSouscription() {
		return numeroSouscription;
	}
	public void setNumeroSouscription(String numeroSouscription) {
		this.numeroSouscription = numeroSouscription;
	}
	public String getTypeIntermediaire() {
		return typeIntermediaire;
	}
	public void setTypeIntermediaire(String typeIntermediaire) {
		this.typeIntermediaire = typeIntermediaire;
	}
	public String getDateOccurence() {
		return dateOccurence;
	}
	public void setDateOccurence(String dateOccurence) {
		this.dateOccurence = dateOccurence;
	}
	/**
	 * @return the codeEntrepriseParticulier
	 */
	public String getCodeEntrepriseParticulier() {
		return codeEntrepriseParticulier;
	}
	/**
	 * @param codeEntrepriseParticulier the codeEntrepriseParticulier to set
	 */
	public void setCodeEntrepriseParticulier(String codeEntrepriseParticulier) {
		
		
		if("null".equals(codeEntrepriseParticulier)){
			this.codeEntrepriseParticulier="";
		}
		else
		{
		this.codeEntrepriseParticulier = codeEntrepriseParticulier;
		}
	}
	
	

	public String getEtatSurvenance() {
		return etatSurvenance;
	}
	public void setEtatSurvenance(String etatSurvenance) {
		this.etatSurvenance = etatSurvenance;
	}
	
	public String getDateSurvenance() {
		return dateSurvenance;
	}
	public void setDateSurvenance(String dateSurvenance) {
		this.dateSurvenance = dateSurvenance;
	}
	public String getDateOuvertuteSinistre() {
		return dateOuvertuteSinistre;
	}
	public void setDateOuvertuteSinistre(String dateOuvertuteSinistre) {
		this.dateOuvertuteSinistre = dateOuvertuteSinistre;
	}
	public String getDateSurvenanceSinistre() {
		return dateSurvenanceSinistre;
	}
	public void setDateSurvenanceSinistre(String dateSurvenanceSinistre) {
		this.dateSurvenanceSinistre = dateSurvenanceSinistre;
	}
	
	
	
		
	
}