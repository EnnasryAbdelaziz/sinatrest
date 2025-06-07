package eai.devass.gsr.appli.modele.metier.dossierRente;
 
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.historique.HistoryItem;
import ma.co.omnidata.framework.services.historique.IHistorisable;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.gsr.appli.manager.metier.dossierRente.TuteurFactory;





/**
 Tuteur:  
@author Nom Prenom (email)
*/
public class Tuteur implements IEntite, ILockable , IHistorisable         {

	private long id;
private static final long serialVersionUID = 1L;
private List versionsChildren;


/**
idTuteur   Identification Tuteur
*/ 
	@Indexation(label="idTuteur",analyzed=false)
	private Long idTuteur;
/**
nom   Nom tuteur
*/ 
	@Indexation(label="nom",analyzed=false)
	private String nom;
/**
numeroCIN   Numéro CIN
*/ 
	@Indexation(label="numeroCIN",analyzed=false)
	private String numeroCIN;
/**
lienParente   Lien Parenté
*/ 
	@Indexation(label="lienParente",analyzed=false)
	private Double lienParente;
/**
adresse   Adresse tuteur
*/ 
	@Indexation(label="adresse",analyzed=false)
	private String adresse;
/**
codePostale   Code Postale Tuteur
*/ 
	@Indexation(label="codePostale",analyzed=false)
	private Double codePostale;
/**
ville   Ville Tuteur
*/ 
	@Indexation(label="ville",analyzed=false)
	private String ville;
/**
pays   Pays Tuteur
*/ 
	@Indexation(label="pays",analyzed=false)
	private String pays;
/**
etat   Etat Tuteur
*/ 
	@Indexation(label="etat",analyzed=false)
	private Double etat;
/**
dateEtat   Date Etat Tuteur
*/ 
	@Indexation(label="dateEtat",analyzed=false)
	private Calendar dateEtat;
/**
validation   Validation Tuteur
*/ 
	@Indexation(label="validation",analyzed=false)
	private Boolean validation;
/**
dateValidation   Date Validation Tuteur
*/ 
	@Indexation(label="dateValidation",analyzed=false)
	private Calendar dateValidation;
/**
prenom   Prénom Tuteur
*/ 
	@Indexation(label="prenom",analyzed=false)
	private String prenom;
/**
sexe   Sexe Tuteur
*/ 
	@Indexation(label="sexe",analyzed=false)
	private String sexe;
/**
dateCreation   
*/ 
	@Indexation(label="dateCreation",analyzed=false)
	private Calendar dateCreation;

	private String deleted;
	private String newTuteur;
	


	public String toString(){
		return String.valueOf(getId()) ;
	}

/**
Methode qui retourne l' instance de la factory d'une entité
@returns L' entite Factory
*/
	public EntiteFactory getFactory() {
		return new TuteurFactory();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Long getIdTuteur() {
		return this.idTuteur;
	}

	public void setIdTuteur(Long idTuteur) {
		this.idTuteur = idTuteur;
	}
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNumeroCIN() {
		return this.numeroCIN;
	}

	public void setNumeroCIN(String numeroCIN) {
		this.numeroCIN = numeroCIN;
	}
	public Double getLienParente() {
		return this.lienParente;
	}

	public void setLienParente(Double lienParente) {
		this.lienParente = lienParente;
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
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
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

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getNewTuteur() {
		return newTuteur;
	}

	public void setNewTuteur(String newTuteur) {
		this.newTuteur = newTuteur;
	}
	
	
}

