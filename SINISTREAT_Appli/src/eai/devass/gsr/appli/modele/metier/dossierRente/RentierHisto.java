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
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierHistoFactory;

/**
 * Rentier: Rentier;Rentier
 * 
 * @author Nom Prenom (email)
 */
public class RentierHisto implements IEntite, ILockable, IHistorisable {

	private long id;
	private static final long serialVersionUID = 1L;
	private List versionsChildren;

	/**
	 * lienParente Lien Parenté avec la victime
	 */
	@Indexation(label = "lienParente", analyzed = false)
	private Long lienParente;
	/**
	 * numeroCIN Numéro CIN Rentier
	 */
	@Indexation(label = "numeroCIN", analyzed = false)
	private String numeroCIN;
	/**
	 * dateNaissance Date Naissance Rentier
	 */
	@Indexation(label = "dateNaissance", analyzed = false)
	private Calendar dateNaissance;
	/**
	 * nationalite Nationalité Rentier
	 */
	@Indexation(label = "nationalite", analyzed = false)
	private long nationalite;
	/**
	 * numeroTelephone Numéro Téléphone Rentier
	 */
	@Indexation(label = "numeroTelephone", analyzed = false)
	private String numeroTelephone;
	/**
	 * numeroGSM Numéro GSM Rentier
	 */
	@Indexation(label = "numeroGSM", analyzed = false)
	private String numeroGSM;
	/**
	 * email Email Rentier
	 */
	@Indexation(label = "email", analyzed = false)
	private String email;
	/**
	 * adresse Adresse Rentier
	 */
	@Indexation(label = "adresse", analyzed = false)
	private String adresse;
	/**
	 * codePostale Code Postale Rentier
	 */
	@Indexation(label = "codePostale", analyzed = false)
	private Double codePostale;
	/**
	 * ville Ville Rentier
	 */
	@Indexation(label = "ville", analyzed = false)
	private String ville;
	/**
	 * pays
	 */
	@Indexation(label = "pays", analyzed = false)
	private String pays;
	/**
	 * prothese Prothèse Rentier
	 */
	@Indexation(label = "prothese", analyzed = false)
	private Boolean prothese;
	/**
	 * situationRentier Situation Rentier
	 */
	@Indexation(label = "situationRentier", analyzed = false)
	private Long situationRentier;
	/**
	 * rentierARisque Rentier a Risque
	 */
	@Indexation(label = "rentierARisque", analyzed = false)
	private Boolean rentierARisque;
	/**
	 * renteConforme Rente Conforme au jugement
	 */
	@Indexation(label = "renteConforme", analyzed = false)
	private Boolean renteConforme;

	/**
	 * dateEtat Date Etat Rentier
	 */
	@Indexation(label = "dateEtat", analyzed = false)
	private Calendar dateEtat;
	/**
	 * validation Validation Rente
	 */
	@Indexation(label = "validation", analyzed = false)
	private Boolean validation;
	/**
	 * Capital constitutif Capital Constitutif de la Rente
	 */
	@Indexation(label = "Capital constitutif", analyzed = false)
	private Double capitalConstitutif;
	/**
	 * Date constitution Date Constitution Rente
	 */
	@Indexation(label = "Date constitution", analyzed = false)
	private Calendar dateConstitution;
	/**
	 * Date départ Date Départ Rente
	 */
	@Indexation(label = "Date départ", analyzed = false)
	private Calendar dateDepartRente;
	/**
	 * IPP IPP ou Taux Rente
	 */
	@Indexation(label = "IPP", analyzed = false)
	private Double ippTauxRente;

	/**
	 * Reserve mathématique Réserve Mathématique Rente
	 */
	@Indexation(label = "Reserve mathématique", analyzed = false)
	private Double reserveMathematique;
	/**
	 * dateValidation Date Validation
	 */
	@Indexation(label = "dateValidation", analyzed = false)
	private Calendar dateValidation;
	/**
	 * Salaire utile Salaire Utile
	 */
	@Indexation(label = "Salaire utile", analyzed = false)
	private Double salaireUtile;
	/**
	 * Approbation quittance Approbation Quittance
	 */
	@Indexation(label = "Approbation quittance", analyzed = false)
	private Boolean approbationQuittance;
	/**
	 * montantRenteTrimestrielle Montant Rente Trimestrielle
	 */
	@Indexation(label = "montantRenteTrimestrielle", analyzed = false)
	private Double montantRenteTrimestrielle;
	/**
	 * periodicite Periodécité Rente
	 */
	@Indexation(label = "periodicite", analyzed = false)
	private String periodicite;
	/**
	 * Tuteur Tuteur Rentier
	 */
	@Indexation(label = "Tuteur", analyzed = false)
	private Boolean tuteur;

	/**
	 * civilite Civilité
	 */
	@Indexation(label = "civilite", analyzed = false)
	private String civilite;
	/**
	 * nom Nom Rentier
	 */
	@Indexation(label = "nom", analyzed = false)
	private String nom;
	/**
	 * prenom Prénom Rentier
	 */
	@Indexation(label = "prenom", analyzed = false)
	private String prenom;
	/**
	 * sexe Sexe Rentier
	 */
	@Indexation(label = "sexe", analyzed = false)
	private String sexe;

	/**
	 * sexe observationConforme Rentier
	 */
	@Indexation(label = "observationConforme", analyzed = false)
	private String observationConforme;
	/**
	 * dateCreation
	 */
	@Indexation(label = "dateCreation", analyzed = false)
	private Calendar dateCreation;
	/**
	 * Modtif etat
	 */
	@Indexation(label = "motifEtat", analyzed = false)
	private String motifEtat;
	/**
	 * orphelinPur
	 */
	@Indexation(label = "orphelinPur", analyzed = false)
	private Boolean orphelinPur;
	/**
	 * dateModification
	 */
	@Indexation(label = "dateModification", analyzed = false)
	private Calendar dateModification;
	/**
	 * userSas
	 */
	@Indexation(label = "userSas", analyzed = false)
	private String userSas;

	/**
	 * Evo Lot 1
	 */
	private Double arrerageAvantConstitution;
	private Double arrerages;
	private String userSasModificateur;
	
	private Boolean donneeConforme;
	private String observationDonneeConforme;
	/**
	 * Fin Evo 
	 */
	
	private long idRentier;
	private long refAyantDroit;
	private long refVictime;
	private long refTuteur;
	private long refEtatRentier;
	private long refModePayement;
	private long refDossierRente;

	public String toString() {
		return String.valueOf(prenom + " " + nom);
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entité
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new RentierHistoFactory();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getLienParente() {
		return this.lienParente;
	}

	public void setLienParente(Long lienParente) {
		this.lienParente = lienParente;
	}

	public String getNumeroCIN() {
		return this.numeroCIN;
	}

	public void setNumeroCIN(String numeroCIN) {
		this.numeroCIN = numeroCIN;
	}

	public Calendar getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Calendar dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public long getNationalite() {
		return this.nationalite;
	}

	public void setNationalite(long nationalite) {
		this.nationalite = nationalite;
	}

	public String getNumeroTelephone() {
		return this.numeroTelephone;
	}

	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}

	public String getNumeroGSM() {
		return this.numeroGSM;
	}

	public void setNumeroGSM(String numeroGSM) {
		this.numeroGSM = numeroGSM;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Boolean getProthese() {
		return this.prothese;
	}

	public void setProthese(Boolean prothese) {
		this.prothese = prothese;
	}

	public Boolean getRentierARisque() {
		return this.rentierARisque;
	}

	public void setRentierARisque(Boolean rentierARisque) {
		this.rentierARisque = rentierARisque;
	}

	public Boolean getRenteConforme() {
		return this.renteConforme;
	}

	public void setRenteConforme(Boolean renteConforme) {
		this.renteConforme = renteConforme;
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

	public Double getCapitalConstitutif() {
		return this.capitalConstitutif;
	}

	public void setCapitalConstitutif(Double capitalConstitutif) {
		this.capitalConstitutif = capitalConstitutif;
	}

	public Calendar getDateConstitution() {
		return this.dateConstitution;
	}

	public void setDateConstitution(Calendar dateConstitution) {
		this.dateConstitution = dateConstitution;
	}

	public Calendar getDateDepartRente() {
		return this.dateDepartRente;
	}

	public void setDateDepartRente(Calendar dateDepartRente) {
		this.dateDepartRente = dateDepartRente;
	}

	public Double getIppTauxRente() {
		return this.ippTauxRente;
	}

	public void setIppTauxRente(Double ippTauxRente) {
		this.ippTauxRente = ippTauxRente;
	}

	public Double getReserveMathematique() {
		return this.reserveMathematique;
	}

	public void setReserveMathematique(Double reserveMathematique) {
		this.reserveMathematique = reserveMathematique;
	}

	public Calendar getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(Calendar dateValidation) {
		this.dateValidation = dateValidation;
	}

	public Double getSalaireUtile() {
		return this.salaireUtile;
	}

	public void setSalaireUtile(Double salaireUtile) {
		this.salaireUtile = salaireUtile;
	}

	public Boolean getApprobationQuittance() {
		return this.approbationQuittance;
	}

	public void setApprobationQuittance(Boolean approbationQuittance) {
		this.approbationQuittance = approbationQuittance;
	}

	public Double getMontantRenteTrimestrielle() {
		return this.montantRenteTrimestrielle;
	}

	public void setMontantRenteTrimestrielle(Double montantRenteTrimestrielle) {
		this.montantRenteTrimestrielle = montantRenteTrimestrielle;
	}

	public String getPeriodicite() {
		return this.periodicite;
	}

	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}

	public Boolean getTuteur() {
		return this.tuteur;
	}

	public void setTuteur(Boolean tuteur) {
		this.tuteur = tuteur;
	}

	public String getCivilite() {
		return this.civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public String getMotifEtat() {
		return motifEtat;
	}

	public void setMotifEtat(String motifEtat) {
		this.motifEtat = motifEtat;
	}

	/**
	 * Methode qui retourne l' Id du lockable
	 * 
	 * @returns id du locakble
	 */
	public String getIdLockable() {
		return Long.toString(getId());
	}

	/**
	 * Methode qui retourne le type du lockable
	 * 
	 * @returns type du locakble
	 */
	public String getLockableType() {
		return this.getClass().getName();
	}

	/**
	 * Methode qui retourne la liste des entités fils d' un historisable
	 * 
	 * @returns La liste des entités fils
	 */
	public Map recupererChildren() {
		return null;
	}

	/**
	 * Methode qui ajoute les differentes versions des fils relatives à un une
	 * version d' un historisable
	 * 
	 * @param children
	 *            versions des fils d' une version l' historisable parent
	 */

	public void addChildren(List children) {
		this.versionsChildren = children;
	}

	/**
	 * Methode qui recupere les differentes versions des fils relatives à un une
	 * version d' un historisable
	 * 
	 * @returns children versions des fils d' une version
	 */
	public List recupererVersionChildren() {
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

	public void setObservationConforme(String observationConforme) {
		this.observationConforme = observationConforme;
	}

	public String getObservationConforme() {
		return observationConforme;
	}

	public Long getSituationRentier() {
		return situationRentier;
	}

	public void setSituationRentier(Long situationRentier) {
		this.situationRentier = situationRentier;
	}

	public void setOrphelinPur(Boolean orphelinPur) {
		this.orphelinPur = orphelinPur;
	}

	public Boolean getOrphelinPur() {
		return orphelinPur;
	}
	public Calendar getDateModification() {
		return dateModification;
	}

	public String getUserSas() {
		return userSas;
	}

	public long getIdRentier() {
		return idRentier;
	}

	public long getRefAyantDroit() {
		return refAyantDroit;
	}

	public long getRefVictime() {
		return refVictime;
	}

	public long getRefTuteur() {
		return refTuteur;
	}

	public long getRefEtatRentier() {
		return refEtatRentier;
	}

	public long getRefModePayement() {
		return refModePayement;
	}

	public long getRefDossierRente() {
		return refDossierRente;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public void setUserSas(String userSas) {
		this.userSas = userSas;
	}

	public void setIdRentier(long idRentier) {
		this.idRentier = idRentier;
	}

	public void setRefAyantDroit(long refAyantDroit) {
		this.refAyantDroit = refAyantDroit;
	}

	public void setRefVictime(long refVictime) {
		this.refVictime = refVictime;
	}

	public void setRefTuteur(long refTuteur) {
		this.refTuteur = refTuteur;
	}

	public void setRefEtatRentier(long refEtatRentier) {
		this.refEtatRentier = refEtatRentier;
	}

	public void setRefModePayement(long refModePayement) {
		this.refModePayement = refModePayement;
	}

	public void setRefDossierRente(long refDossierRente) {
		this.refDossierRente = refDossierRente;
	}
	public List getParents() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setArrerageAvantConstitution(Double arrerageAvantConstitution) {
		this.arrerageAvantConstitution = arrerageAvantConstitution;
	}

	public Double getArrerageAvantConstitution() {
		return arrerageAvantConstitution;
	}

	public void setArrerages(Double arrerages) {
		this.arrerages = arrerages;
	}

	public Double getArrerages() {
		return arrerages;
	}

	public void setUserSasModificateur(String userSasModificateur) {
		this.userSasModificateur = userSasModificateur;
	}

	public String getUserSasModificateur() {
		return userSasModificateur;
	}

	public void setDonneeConforme(Boolean donneeConforme) {
		this.donneeConforme = donneeConforme;
	}

	public Boolean getDonneeConforme() {
		return donneeConforme;
	}

	public void setObservationDonneeConforme(String observationDonneeConforme) {
		this.observationDonneeConforme = observationDonneeConforme;
	}

	public String getObservationDonneeConforme() {
		return observationDonneeConforme;
	}

}