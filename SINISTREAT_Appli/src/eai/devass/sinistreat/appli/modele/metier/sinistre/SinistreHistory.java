package eai.devass.sinistreat.appli.modele.metier.sinistre;

import java.util.Date;
import java.util.List;

import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;

public class SinistreHistory   {

	private int id;

	private String numeroSinistre ;
	private Date dateAccident ;
	private String numeroPolice  ;           
	private String codeClient      ;         
	private String codeIntermediaire  ;      
	private String nomIntermediaire   ;      
	private String nomClient      ;          
	private boolean isRecours     ;           
	private int idRecours     ;           
	private String codeGarantie    ;           
	private String codeTypeMaladie ;           
	private String diagnostique  ;           
	private String observation   ;           
	private int idTransaction ;           
	private String userCreateur  ;           
	private Date dateCreation  ;           
	                                        
	private Evenement refEvenement    ;                
	private Victime refVictime        ;
	private  List<Reglement> listReglement   ;
	
	public SinistreHistory() {
		super();
	}
	public SinistreHistory(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
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
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public boolean getIsRecours() {
		return isRecours;
	}
	public void setIsRecours(boolean isRecours) {
		this.isRecours = isRecours;
	}
	public int getIdRecours() {
		return idRecours;
	}
	public void setIdRecours(int idRecours) {
		this.idRecours = idRecours;
	}
	public String getCodeGarantie() {
		return codeGarantie;
	}
	public void setCodeGarantie(String codeGarantie) {
		this.codeGarantie = codeGarantie;
	}
	public String getCodeTypeMaladie() {
		return codeTypeMaladie;
	}
	public void setCodeTypeMaladie(String codeTypeMaladie) {
		this.codeTypeMaladie = codeTypeMaladie;
	}
	public String getDiagnostique() {
		return diagnostique;
	}
	public void setDiagnostique(String diagnostique) {
		this.diagnostique = diagnostique;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public int getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}
	public String getUserCreateur() {
		return userCreateur;
	}
	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Evenement getRefEvenement() {
		return refEvenement;
	}
	public void setRefEvenement(Evenement refEvenement) {
		this.refEvenement = refEvenement;
	}
	public Victime getRefVictime() {
		return refVictime;
	}
	public void setRefVictime(Victime refVictime) {
		this.refVictime = refVictime;
	}
	public List<Reglement> getListReglement() {
		return listReglement;
	}
	public void setListReglement(List<Reglement> listReglement) {
		this.listReglement = listReglement;
	}
	public Date getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(Date dateAccident) {
		this.dateAccident = dateAccident;
	}

	
	
}