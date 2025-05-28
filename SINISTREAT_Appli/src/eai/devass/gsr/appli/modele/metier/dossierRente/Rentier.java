package eai.devass.gsr.appli.modele.metier.dossierRente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.commun.appli.historisation.ALazy;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.modele.IHistorisable;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierFactory;
import eai.devass.gsr.appli.modele.metier.reglement.ModePayement;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.Nationalite;
import eai.devass.gsr.appli.modele.parametrage.SituationEtatRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationRentier;
import eai.devass.gsr.appli.modele.parametrage.SortGsr;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;

/**
 * Rentier: Rentier;Rentier
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO")
public class Rentier extends EntiteBO implements IEntite, IHistorisable {

	private long id;
	private static final long serialVersionUID = 1L;
	private transient List versionsChildren;

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
	private Nationalite refNationalite;

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
	private SituationRentier refSituationRentier;
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
	 * Evo Lot 1
	 */
	private Double arrerageAvantConstitution;
	private Double arrerages;
	private String userSasModificateur;
	private Calendar dateModification;

	private Boolean donneeConforme;
	private String observationDonneeConforme;
	/**
	 * Fin Evo
	 */

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
	 * userSas
	 */
	@Indexation(label = "userSas", analyzed = false)
	private String userSas;

	/**
	 * Motif etat
	 */
	@Indexation(label = "motifEtat", analyzed = false)
	private String motifEtat;
	private Boolean orphelinPur;
	private AyantDroit refAyantDroit;

	private Victime refVictime;
	private Tuteur refTuteur;
	private EtatRentier refEtatRentier;
	private List<SituationEtatRentier> refSituationEtatRentier;
	private ModePayement refModePayement;
	private List<Prothese> refsProtheses = new ArrayList<Prothese>();

	@ALazy(lazy = false)
	private DossierRente refDossierRente;

	private transient double mntAncienneRente = 0;
	private transient double tauxAncienneIpp = 0;
	private transient Double montantPercu;
	private transient Double mntQuittancesRegles;

	// CNRA
	private transient Double mntCapitalCnra;
	private transient Double mntCapitalCnraCalcule;
	private transient Double mntCapitalCnraDiff;
	private transient Double mntCapitalDu;
	private transient long quotePart;
	private transient long nbrTrimestreAregler;
	private transient Double mntArrerage;
	private transient Double mntReliquat;
	// POur la convertion (VO BO)
	private transient String[] propertiesToConvert;
	private transient int ageRentier;

	//EVO SORT RENTIER (CONSIGNATION)
	private SortGsr refSortGsr;
	private transient Double prorataCNRA;
	@Indexation(label = "dateLimiteConsignation", analyzed = false)
	private Calendar dateLimiteConsignation;
	
	
	public Rentier() {

	}

	public Rentier(long id) {
		this.id = id;
	}

	public String toString() {
		return "[ID  :" + id + ", " + prenom + " " + nom + "]";

	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entité
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new RentierFactory();
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

	public Nationalite getRefNationalite() {
		return refNationalite;
	}

	public void setRefNationalite(Nationalite refNationalite) {
		this.refNationalite = refNationalite;
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

	public Victime getRefVictime() {
		return this.refVictime;
	}

	public void setRefVictime(Victime refVictime) {
		this.refVictime = refVictime;
	}

	public Tuteur getRefTuteur() {
		return this.refTuteur;
	}

	public void setRefTuteur(Tuteur refTuteur) {
		this.refTuteur = refTuteur;
	}

	public String getMotifEtat() {
		return motifEtat;
	}

	public void setMotifEtat(String motifEtat) {
		this.motifEtat = motifEtat;
	}

	public List getRefsProtheses() {
		return this.refsProtheses;
	}

	public void setRefsProtheses(List refsProtheses) {
		this.refsProtheses = refsProtheses;
	}

	public DossierRente getRefDossierRente() {
		return this.refDossierRente;
	}

	public void setRefDossierRente(DossierRente refDossierRente) {
		this.refDossierRente = refDossierRente;
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
	 * Methode qui retourne la liste des entités parentes
	 * 
	 * @returns La liste des entités parentes
	 */
	public List getParents() {
		List<IEntite> Parents = new ArrayList<IEntite>();
		Parents.add(refDossierRente);
		return Parents;
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

	public void historiser() {

	}

	public void historiser(Object arg0) {

	}

	public void setRefModePayement(ModePayement refModePayement) {
		this.refModePayement = refModePayement;
	}

	public ModePayement getRefModePayement() {
		return refModePayement;
	}

	public void setRefEtatRentier(EtatRentier refEtatRentier) {
		this.refEtatRentier = refEtatRentier;
	}

	public EtatRentier getRefEtatRentier() {
		return refEtatRentier;
	}

	public void setObservationConforme(String observationConforme) {
		this.observationConforme = observationConforme;
	}

	public String getObservationConforme() {
		return observationConforme;
	}

	public void setRefAyantDroit(AyantDroit refAyantDroit) {
		this.refAyantDroit = refAyantDroit;
	}

	public AyantDroit getRefAyantDroit() {
		return refAyantDroit;
	}

	public SituationRentier getRefSituationRentier() {
		return refSituationRentier;
	}

	public void setRefSituationRentier(SituationRentier refSituationRentier) {
		this.refSituationRentier = refSituationRentier;
	}

	public void setOrphelinPur(Boolean orphelinPur) {
		this.orphelinPur = orphelinPur;
	}

	public Boolean getOrphelinPur() {
		return orphelinPur;
	}

	public void setUserSas(String userSas) {
		this.userSas = userSas;
	}

	public String getUserSas() {
		return userSas;
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

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Calendar getDateModification() {
		return dateModification;
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

	public List<SituationEtatRentier> getRefSituationEtatRentier() {
		return refSituationEtatRentier;
	}

	public void setRefSituationEtatRentier(
			List<SituationEtatRentier> refSituationEtatRentier) {
		this.refSituationEtatRentier = refSituationEtatRentier;
	}

	public double getMntAncienneRente() {
		return mntAncienneRente;
	}

	public void setMntAncienneRente(double mntAncienneRente) {
		this.mntAncienneRente = mntAncienneRente;
	}

	public SituationEtatRentier getCurSituationEtatRentier(EtatRente etatRente) {
		this.setRefEtatRentier(new EtatRentier(etatRente.getCode()));
		this.setDateEtat(new GregorianCalendar());
		SituationEtatRentier situationEtatRentier = new SituationEtatRentier();
		situationEtatRentier.setDateEtat(new Date());
		situationEtatRentier.setRefEtatRentier(new EtatRentier(etatRente
				.getCode()));
		situationEtatRentier.setRefRentier(this);
		return situationEtatRentier;
	}

	public String getNomComplet() {
		return nom + " " + prenom;
	}

	/**
	 * 
	 * @return
	 */
	public Double getMontantPercu() {
		return montantPercu;
	}

	/**
	 * 
	 * @param montantPercu
	 */
	public void setMontantPercu(Double montantPercu) {
		this.montantPercu = montantPercu;
	}

	/**
	 * 
	 * @return
	 */
	public Double getMntQuittancesRegles() {
		return mntQuittancesRegles;
	}

	/**
	 * 
	 * @param mntQuittancesRegles
	 */
	public void setMntQuittancesRegles(Double mntQuittancesRegles) {
		this.mntQuittancesRegles = mntQuittancesRegles;
	}

	public double getTauxAncienneIpp() {
		return tauxAncienneIpp;
	}

	public void setTauxAncienneIpp(double tauxAncienneIpp) {
		this.tauxAncienneIpp = tauxAncienneIpp;
	}

	public Double getMntCapitalCnra() {
		return mntCapitalCnra;
	}

	public void setMntCapitalCnra(Double mntCapitalCnra) {
		this.mntCapitalCnra = mntCapitalCnra;
	}

	public Double getMntCapitalCnraCalcule() {
		return mntCapitalCnraCalcule;
	}

	public void setMntCapitalCnraCalcule(Double mntCapitalCnraCalcule) {
		this.mntCapitalCnraCalcule = mntCapitalCnraCalcule;
	}

	public Double getMntCapitalDu() {
		return mntCapitalDu;
	}

	public void setMntCapitalDu(Double mntCapitalDu) {
		this.mntCapitalDu = mntCapitalDu;
	}

	public Double getMntCapitalCnraDiff() {
		return mntCapitalCnraDiff;
	}

	public void setMntCapitalCnraDiff(Double mntCapitalCnraDiff) {
		this.mntCapitalCnraDiff = mntCapitalCnraDiff;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}

	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;

	}

	public int getAgeRentier() {
		if (dateNaissance == null) {
			return 0;
		}

		Calendar today = Calendar.getInstance();
//		ageRentier = today.get(Calendar.YEAR)
//				- dateNaissance.get(Calendar.YEAR);
//		if (today.get(Calendar.MONTH) < dateNaissance.get(Calendar.MONTH)) {
//			ageRentier--;
//		} else if (today.get(Calendar.MONTH) == dateNaissance
//				.get(Calendar.MONTH)
//				&& today.get(Calendar.DAY_OF_MONTH) < dateNaissance
//						.get(Calendar.DAY_OF_MONTH)) {
//			ageRentier--;
//		}
		//Evol 01/10/2020: Arrondir l'age
		int timeDebut = (int) ((dateNaissance.getTime()).getTime()/ (24 * 60 * 60 * 1000));
		int timeFin = (int) ((new Date().getTime())/ (24 * 60 * 60 * 1000));
		ageRentier = (int) Math.round((timeFin - timeDebut)/365d);
		return ageRentier;
	}

	public void setAgeRentier(int ageRentier) {
		this.ageRentier = ageRentier;
	}

	public void setRefSortGsr(SortGsr refSortGsr) {
		this.refSortGsr = refSortGsr;
	}

	public SortGsr getRefSortGsr() {
		return refSortGsr;
	}

	public void setDateLimiteConsignation(Calendar dateLimiteConsignation) {
		this.dateLimiteConsignation = dateLimiteConsignation;
	}

	public Calendar getDateLimiteConsignation() {
		return dateLimiteConsignation;
	}

	public long getQuotePart() {
		return quotePart;
	}

	public void setQuotePart(long quotePart) {
		this.quotePart = quotePart;
	}

	public long getNbrTrimestreAregler() {
		return nbrTrimestreAregler;
	}

	public void setNbrTrimestreAregler(long nbrTrimestreAregler) {
		this.nbrTrimestreAregler = nbrTrimestreAregler;
	}

	public Double getMntArrerage() {
		return mntArrerage;
	}

	public void setMntArrerage(Double mntArrerage) {
		this.mntArrerage = mntArrerage;
	}

	public Double getMntReliquat() {
		return mntReliquat;
	}

	public void setMntReliquat(Double mntReliquat) {
		this.mntReliquat = mntReliquat;
	}

	public Double getProrataCNRA() {
		return prorataCNRA;
	}

	public void setProrataCNRA(Double prorataCNRA) {
		this.prorataCNRA = prorataCNRA;
	}
	
}