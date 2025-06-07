package eai.devass.gsr.appli.modele.metier.reglement;
 
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.historique.HistoryItem;
import ma.co.omnidata.framework.services.historique.IHistorisable;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.gsr.appli.manager.metier.reglement.ModePayementFactory;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;





/**
 Mode payement:  
@author Nom Prenom (email)
*/
public class ModePayement implements IEntite, ILockable , IHistorisable         {

	private long id;
private static final long serialVersionUID = 1L;
private List versionsChildren;


/**
idModePayement   Mode Payement
*/ 
	@Indexation(label="idModePayement",analyzed=false)
	private long idModePayement;
/**
description   Description Mode Payement
*/ 
	@Indexation(label="description",analyzed=false)
	private String description;
/**
beneficiaire   beneficiaire  Mode Payement
*/ 
	@Indexation(label="beneficiaire",analyzed=false)
	private String beneficiaire;
/**
numeroCIN   Numéro CIN  Bénéf. Mode Payement
*/ 
	@Indexation(label="numeroCIN",analyzed=false)
	private String numeroCIN;
/**
adresse   Adresse Bénéf.
*/ 
	@Indexation(label="adresse",analyzed=false)
	private String adresse;
/**
codePostale   Code Postale
*/ 
	@Indexation(label="codePostale",analyzed=false)
	private Double codePostale;
/**
ville   Ville
*/ 
	@Indexation(label="ville",analyzed=false)
	private String ville;
/**
pays   Pays
*/ 
	@Indexation(label="pays",analyzed=false)
	private String pays;
/**
numeroRIB   Numéro RIB
*/ 
	@Indexation(label="numeroRIB",analyzed=false)
	private String numeroRIB;
/**
banque   Banque Virment
*/ 
	@Indexation(label="banque",analyzed=false)
	private String banque;
/**
agenceBancaire   Agence Bancaire Virement
*/ 
	@Indexation(label="agenceBancaire",analyzed=false)
	private String agenceBancaire;
/**
etat   Etat
*/ 
	@Indexation(label="etat",analyzed=false)
	private Double etat;
/**
dateEtat   Date Etat
*/ 
	@Indexation(label="dateEtat",analyzed=false)
	private Calendar dateEtat;
/**
validation   Validation
*/ 
	@Indexation(label="validation",analyzed=false)
	private Boolean validation;
/**
dateValidation   Date Validation
*/ 
	@Indexation(label="dateValidation",analyzed=false)
	private Calendar dateValidation;
/**
dateCreation   
*/ 
	@Indexation(label="dateCreation",analyzed=false)
	private Calendar dateCreation;

	private Rentier refRentier;
	
	@Indexation(label="virmentOMC",analyzed=false)
	private String virmentOMC;


	public String toString(){
		return String.valueOf(getId()) ;
	}

/**
Methode qui retourne l' instance de la factory d'une entité
@returns L' entite Factory
*/
	public EntiteFactory getFactory() {
		return new ModePayementFactory();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public long getIdModePayement() {
		return this.idModePayement;
	}

	public void setIdModePayement(long idModePayement) {
		this.idModePayement = idModePayement;
	}
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getBeneficiaire() {
		return this.beneficiaire;
	}

	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}
	public String getNumeroCIN() {
		return this.numeroCIN;
	}

	public void setNumeroCIN(String numeroCIN) {
		this.numeroCIN = numeroCIN;
	}
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Double getCodePostale() {
		return this.codePostale;
	}

	public void setCodePostale(Double codePostale) {
		this.codePostale = codePostale;
	}
	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return this.pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getNumeroRIB() {
		return this.numeroRIB;
	}

	public void setNumeroRIB(String numeroRIB) {
		this.numeroRIB = numeroRIB;
	}
	public String getBanque() {
		return this.banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}
	public String getAgenceBancaire() {
		return this.agenceBancaire;
	}

	public void setAgenceBancaire(String agenceBancaire) {
		this.agenceBancaire = agenceBancaire;
	}
	public Double getEtat() {
		return this.etat;
	}

	public void setEtat(Double etat) {
		this.etat = etat;
	}
	public Calendar getDateEtat() {
		return this.dateEtat;
	}

	public void setDateEtat(Calendar dateEtat) {
		this.dateEtat = dateEtat;
	}
	public Boolean getValidation() {
		return this.validation;
	}

	public void setValidation(Boolean validation) {
		this.validation = validation;
	}
	public Calendar getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(Calendar dateValidation) {
		this.dateValidation = dateValidation;
	}
	public Calendar getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}




/**
Methode qui retourne l' Id du lockable
@returns id du locakble
*/
	public String getIdLockable() {
		return Long.toString(getId());
	}

/**
Methode qui retourne le type du lockable
@returns type du locakble
*/
	public String getLockableType() {
		return this.getClass().getName();
	}
	

/**
Methode qui retourne la liste des entités parentes d' un historisable
@returns La liste des entités parentes
*/
	public List getParents() {
		return null;
	}

	
/**
Methode qui retourne la liste des entités fils d' un historisable
@returns La liste des entités fils
*/
	public Map recupererChildren ()  {
		return null;
	}
		

/**
Methode qui ajoute les differentes versions des fils relatives à un une version d' un  historisable 
@param children versions des fils d' une version  l' historisable parent
*/
		
	public void addChildren (List children){
			this.versionsChildren = children;
	}

/**
Methode qui recupere les differentes versions des fils relatives à un une version d' un  historisable 
@returns children versions des fils  d' une version
*/	
	public List recupererVersionChildren(){
		return versionsChildren;
		
	}
    
    public List getHistory() {
		return null;
	}

	public HistoryItem getLastHistoryItem() {
		return null;
	}

	public void historiser() {
		
	}

	public void historiser(Object arg0) {
		
	}

	public void setRefRentier(Rentier refRentier) {
		this.refRentier = refRentier;
	}

	public Rentier getRefRentier() {
		return refRentier;
	}

	public String getVirmentOMC() {
		return virmentOMC;
	}

	public void setVirmentOMC(String virmentOMC) {
		this.virmentOMC = virmentOMC;
	}
	

}

