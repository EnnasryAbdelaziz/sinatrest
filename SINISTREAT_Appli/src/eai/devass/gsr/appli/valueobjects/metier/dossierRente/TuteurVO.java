package eai.devass.gsr.appli.valueobjects.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;

/**
 * Value Object de Tuteur
 * 
 * @author Nom Prenom (email)
 */
public class TuteurVO extends RechListeVO implements ITracable {

	private String id;
	private static final long serialVersionUID = 1L;

	// Historique
	@Indexation(label = "idHistorisable", analyzed = false)
	private long idHistorisable;
	@Indexation(label = "numVersion", analyzed = false)
	private long numVersion;
	@Indexation(label = "utilisateurVersion", analyzed = false)
	private String utilisateurVersion;
	@Indexation(label = "dateVersion", analyzed = false)
	private String dateVersion;

	@Indexation(label = "idTuteur", analyzed = false)
	private String idTuteur;
	@Indexation(label = "nom", analyzed = false)
	private String nom;
	@Indexation(label = "numeroCIN", analyzed = false)
	private String numeroCIN;
	@Indexation(label = "lienParente", analyzed = false)
	private String lienParente;
	@Indexation(label = "adresse", analyzed = false)
	private String adresse;
	@Indexation(label = "codePostale", analyzed = false)
	private String codePostale;
	@Indexation(label = "ville", analyzed = false)
	private String ville;
	@Indexation(label = "pays", analyzed = false)
	private String pays;
	@Indexation(label = "etat", analyzed = false)
	private String etat;
	@Indexation(label = "dateEtat", analyzed = false)
	private String dateEtat;
	@Indexation(label = "validation", analyzed = false)
	private String validation;
	@Indexation(label = "dateValidation", analyzed = false)
	private String dateValidation;
	@Indexation(label = "prenom", analyzed = false)
	private String prenom;
	@Indexation(label = "sexe", analyzed = false)
	private String sexe;
	@Indexation(label = "dateCreation", analyzed = false)
	private String dateCreation;
	private String typeParentA;
	private String typeParentB;
	private String deleted;
	private String newTuteur;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdTuteur() {
		return this.idTuteur;
	}

	public void setIdTuteur(String idTuteur) {
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

	public String getLienParente() {
		return this.lienParente;
	}

	public void setLienParente(String lienParente) {
		this.lienParente = lienParente;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostale() {
		return this.codePostale;
	}

	public void setCodePostale(String codePostale) {
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

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDateEtat() {
		return this.dateEtat;
	}

	public void setDateEtat(String dateEtat) {
		this.dateEtat = dateEtat;
	}

	public String getValidation() {
		return this.validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(String dateValidation) {
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

	public String getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("idTuteur");
		listAttributes.add("nom");
		listAttributes.add("numeroCIN");
		listAttributes.add("lienParente");
		listAttributes.add("adresse");
		listAttributes.add("codePostale");
		listAttributes.add("ville");
		listAttributes.add("pays");
		listAttributes.add("etat");
		listAttributes.add("dateEtat");
		listAttributes.add("validation");
		listAttributes.add("dateValidation");
		listAttributes.add("prenom");
		listAttributes.add("sexe");
		listAttributes.add("dateCreation");
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this, listAttributes);
		} catch (Exception e) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e.getMessage());

		}
		return rslt;
	}

	public String getTypeParentA() {
		return typeParentA;
	}

	public void setTypeParentA(String typeParentA) {
		this.typeParentA = typeParentA;
		this.typeParentB = typeParentA;
	}

	public String getTypeParentB() {
		return typeParentB;
	}

	public void setTypeParentB(String typeParentB) {
		this.typeParentB = typeParentB;
	}

	// Historique
	public long getNumVersion() {
		return numVersion;
	}

	public void setNumVersion(long numVersion) {
		this.numVersion = numVersion;
	}

	public String getUtilisateurVersion() {
		return utilisateurVersion;
	}

	public void setUtilisateurVersion(String utilisateurVersion) {
		this.utilisateurVersion = utilisateurVersion;
	}

	public String getDateVersion() {
		return dateVersion;
	}

	public void setDateVersion(String dateVersion) {
		this.dateVersion = dateVersion;
	}

	public long getIdHistorisable() {
		return idHistorisable;
	}

	public void setIdHistorisable(long idHistorisable) {
		this.idHistorisable = idHistorisable;
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
