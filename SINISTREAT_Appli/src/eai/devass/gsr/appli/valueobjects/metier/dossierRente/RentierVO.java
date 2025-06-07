package eai.devass.gsr.appli.valueobjects.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.commun.appli.modele.EntiteVO;
import eai.devass.gsr.appli.valueobjects.metier.reglement.ModePayementVO;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatRentierVO;
import eai.devass.gsr.appli.valueobjects.parametrage.SortGsrVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.VictimeVO;

/**
 * Value Object de Rentier
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteDist="eai.devass.gsr.appli.modele.metier.dossierRente.Rentier")
public class RentierVO extends EntiteVO implements ITracable {
	@AConverter(propertyDist = "id")
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

	@AConverter(propertyDist="lienParente")
	private String lienParente;
	
	@Indexation(label = "numeroCIN", analyzed = false)
	private String numeroCIN;
	
	@AConverter(propertyDist="dateNaissance")
	private String dateNaissance;
	
	private String nationalite;
	private String libelleNationalite;
	
	
	@Indexation(label = "numeroTelephone", analyzed = false)
	private String numeroTelephone;
	@Indexation(label = "numeroGSM", analyzed = false)
	private String numeroGSM;
	@Indexation(label = "email", analyzed = false)
	private String email;
	@Indexation(label = "adresse", analyzed = false)
	private String adresse;
	@Indexation(label = "codePostale", analyzed = false)
	private String codePostale;
	@Indexation(label = "ville", analyzed = false)
	private String ville;
	private String villeLibelle;
	
	@Indexation(label = "pays", analyzed = false)
	private String pays;
	private String libellePays;
	
	@Indexation(label = "prothese", analyzed = false)
	private String prothese;
	
	private String situationRentier;
	private String libelleSituationRentier;
	
	@Indexation(label = "rentierARisque", analyzed = false)
	private String rentierARisque;
	@Indexation(label = "renteConforme", analyzed = false)
	private String renteConforme;
	@Indexation(label = "etatRente", analyzed = false)
	private String etatRente;
	@Indexation(label = "dateEtat", analyzed = false)
	private String dateEtat;
	@Indexation(label = "validation", analyzed = false)
	private String validation;
	@Indexation(label = "Capital constitutif", analyzed = false)
	private String capitalConstitutif;
	@Indexation(label = "Date constitution", analyzed = false)
	private String dateConstitution;
	@Indexation(label = "Date départ", analyzed = false)
	private String dateDepartRente;
	@Indexation(label = "IPP", analyzed = false)
	private String ippTauxRente;

	@Indexation(label = "Reserve mathématique", analyzed = false)
	private String reserveMathematique;
	@Indexation(label = "dateValidation", analyzed = false)
	private String dateValidation;
	@Indexation(label = "Salaire utile", analyzed = false)
	private String salaireUtile;
	@Indexation(label = "Approbation quittance", analyzed = false)
	private String approbationQuittance;
	@Indexation(label = "montantRenteTrimestrielle", analyzed = false)
    @AConverter(propertyDist = "montantRenteTrimestrielle")
	private String montantRenteTrimestrielle;
	
	/**
	 * Evo Lot 1
	 */
	private String arrerageAvantConstitution;
	private String arrerages;
	private String userSasModificateur;
	private String dateModification;
	
	private String donneeConforme;
	private String observationDonneeConforme;
	
	/**
	 * Fin Evo 
	 */
	@Indexation(label = "periodicite", analyzed = false)
	private String periodicite;
	@Indexation(label = "Tuteur", analyzed = false)
	private String tuteur;

	@Indexation(label = "civilite", analyzed = false)
	private String civilite;
	
	@AConverter(propertyDist="nom")
	private String nom;
	
	@AConverter(propertyDist="prenom")
	private String prenom;
	
	@Indexation(label = "sexe", analyzed = false)
	private String sexe;
	@Indexation(label = "observationConforme", analyzed = false)
	private String observationConforme;
	@Indexation(label = "dateCreation", analyzed = false)
	private String dateCreation;
	private AyantDroitVO refAyantDroit;
	private VictimeVO refVictime;
	private TuteurVO refTuteur;
	
	@AConverter(propertyDist="refEtatRentier")
	private EtatRentierVO refEtatRentier;
	private String motifEtat;
	private String orphelinPur;
	private ModePayementVO refModePayement;
	private List<ProtheseVO> refsProtheses = new ArrayList<ProtheseVO>();

	@Indexation(label = "Dossier Rente", analyzed = false)
	private DossierRenteVO refDossierRente;

	/**
	 * userSas
	 */
	@Indexation(label = "userSas", analyzed = false)
	private String userSas;
	
	//kchakib : Besion BATCH
	private List<QuittanceGsrVO> listQuittanceVO;
	
	// REdistribution
	private String ancienMontantRenteTrimestrielle;
	private String ancienTauxIpp;
		
	//besoin Mouvement Annulation
	@AConverter(propertyDist = "montantPercu")
	private  String montantPercu ;

	//besoin Mouvement Annulation
	@AConverter(propertyDist = "mntQuittancesRegles")
	private  String mntQuittancesRegles ;
	
	// CNRA
	@AConverter(propertyDist = "mntCapitalCnra")
	private String mntCapitalCnra;
	
	@AConverter(propertyDist = "prorataCNRA")
	private String prorataCNRA;
	
	@AConverter(propertyDist = "mntCapitalCnraCalcule")
	private String mntCapitalCnraCalcule;
	
	@AConverter(propertyDist = "mntCapitalCnraDiff")
	private String mntCapitalCnraDiff;

	@AConverter(propertyDist = "mntCapitalDu")
	private String mntCapitalDu;
	
	@AConverter(propertyDist="quotePart")
	private String quotePart;
	
	@AConverter(propertyDist="nbrTrimestreAregler")
	private String nbrTrimestreAregler;
	
	@AConverter(propertyDist="mntArrerage")
	private String mntArrerage;
	
	@AConverter(propertyDist="mntReliquat")
	private String mntReliquat;
	
	//EVO SORT RENTIER (CONSIGNATION)
	private SortGsrVO refSortGsr;
	@AConverter(propertyDist = "dateLimiteConsignation")
	private String dateLimiteConsignation;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLienParente() {
		return this.lienParente;
	}

	public void setLienParente(String lienParente) {
		this.lienParente = lienParente;
	}

	public String getNumeroCIN() {
		return this.numeroCIN;
	}

	public void setNumeroCIN(String numeroCIN) {
		this.numeroCIN = numeroCIN;
	}

	public String getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNationalite() {
		return this.nationalite;
	}

	public void setNationalite(String nationalite) {
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

	public String getProthese() {
		return this.prothese;
	}

	public void setProthese(String prothese) {
		this.prothese = prothese;
	}

	public String getRentierARisque() {
		return this.rentierARisque;
	}

	public void setRentierARisque(String rentierARisque) {
		this.rentierARisque = rentierARisque;
	}

	public String getRenteConforme() {
		return this.renteConforme;
	}

	public void setRenteConforme(String renteConforme) {
		this.renteConforme = renteConforme;
	}

	public String getEtatRente() {
		return this.etatRente;
	}

	public void setEtatRente(String etatRente) {
		this.etatRente = etatRente;
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

	public String getCapitalConstitutif() {
		return this.capitalConstitutif;
	}

	public void setCapitalConstitutif(String capitalConstitutif) {
		this.capitalConstitutif = capitalConstitutif;
	}

	public String getDateConstitution() {
		return this.dateConstitution;
	}

	public void setDateConstitution(String dateConstitution) {
		this.dateConstitution = dateConstitution;
	}

	public String getDateDepartRente() {
		return this.dateDepartRente;
	}

	public void setDateDepartRente(String dateDepartRente) {
		this.dateDepartRente = dateDepartRente;
	}

	public String getIppTauxRente() {
		return this.ippTauxRente;
	}

	public void setIppTauxRente(String ippTauxRente) {
		this.ippTauxRente = ippTauxRente;
	}

	public String getReserveMathematique() {
		return this.reserveMathematique;
	}

	public void setReserveMathematique(String reserveMathematique) {
		this.reserveMathematique = reserveMathematique;
	}

	public String getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(String dateValidation) {
		this.dateValidation = dateValidation;
	}

	public String getSalaireUtile() {
		return this.salaireUtile;
	}

	public void setSalaireUtile(String salaireUtile) {
		this.salaireUtile = salaireUtile;
	}

	public String getApprobationQuittance() {
		return this.approbationQuittance;
	}

	public void setApprobationQuittance(String approbationQuittance) {
		this.approbationQuittance = approbationQuittance;
	}

	public String getMontantRenteTrimestrielle() {
		return this.montantRenteTrimestrielle;
	}

	public void setMontantRenteTrimestrielle(String montantRenteTrimestrielle) {
		this.montantRenteTrimestrielle = montantRenteTrimestrielle;
	}

	public String getProrataCNRA() {
		return prorataCNRA;
	}

	public void setProrataCNRA(String prorataCNRA) {
		this.prorataCNRA = prorataCNRA;
	}

	public String getPeriodicite() {
		return this.periodicite;
	}

	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}

	public String getTuteur() {
		return this.tuteur;
	}

	public void setTuteur(String tuteur) {
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

	public String getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("lienParente");
		listAttributes.add("numeroCIN");
		listAttributes.add("dateNaissance");
		listAttributes.add("nationalite");
		listAttributes.add("numeroTelephone");
		listAttributes.add("numeroGSM");
		listAttributes.add("email");
		listAttributes.add("adresse");
		listAttributes.add("codePostale");
		listAttributes.add("ville");
		listAttributes.add("pays");
		listAttributes.add("prothese");
		listAttributes.add("situationRentier");
		listAttributes.add("rentierARisque");
		listAttributes.add("renteConforme");
		listAttributes.add("etatRente");
		listAttributes.add("dateEtat");
		listAttributes.add("validation");
		listAttributes.add("capitalConstitutif");
		listAttributes.add("dateConstitution");
		listAttributes.add("dateDepartRente");
		listAttributes.add("ippTauxRente");
		listAttributes.add("modePayement");
		listAttributes.add("reserveMathematique");
		listAttributes.add("dateValidation");
		listAttributes.add("salaireUtile");
		listAttributes.add("approbationQuittance");
		listAttributes.add("montantRenteTrimestrielle");
		listAttributes.add("periodicite");
		listAttributes.add("tuteur");
		listAttributes.add("idRentier");
		listAttributes.add("civilite");
		listAttributes.add("nom");
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

	public void setRefDossierRente(DossierRenteVO refDossierRente) {
		this.refDossierRente = refDossierRente;
	}

	public DossierRenteVO getRefDossierRente() {
		return refDossierRente;
	}

	public VictimeVO getRefVictime() {
		return refVictime;
	}

	public void setRefVictime(VictimeVO refVictime) {
		this.refVictime = refVictime;
	}

	public TuteurVO getRefTuteur() {
		return refTuteur;
	}

	public void setRefTuteur(TuteurVO refTuteur) {
		this.refTuteur = refTuteur;
	}

	public String getMotifEtat() {
		return motifEtat;
	}

	public void setMotifEtat(String motifEtat) {
		this.motifEtat = motifEtat;
	}

	public List<ProtheseVO> getRefsProtheses() {
		return refsProtheses;
	}

	public void setRefsProtheses(List<ProtheseVO> refsProtheses) {
		this.refsProtheses = refsProtheses;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setRefModePayement(ModePayementVO refModePayement) {
		this.refModePayement = refModePayement;
	}

	public ModePayementVO getRefModePayement() {
		return refModePayement;
	}

	public void setRefEtatRentier(EtatRentierVO refEtatRentier) {
		this.refEtatRentier = refEtatRentier;
	}

	public EtatRentierVO getRefEtatRentier() {
		return refEtatRentier;
	}

	public void setObservationConforme(String observationConforme) {
		this.observationConforme = observationConforme;
	}

	public String getObservationConforme() {
		return observationConforme;
	}

	public void setRefAyantDroit(AyantDroitVO refAyantDroit) {
		this.refAyantDroit = refAyantDroit;
	}

	public AyantDroitVO getRefAyantDroit() {
		return refAyantDroit;
	}

	public String getSituationRentier() {
		return situationRentier;
	}

	public void setSituationRentier(String situationRentier) {
		this.situationRentier = situationRentier;
	}

	public void setOrphelinPur(String orphelinPur) {
		this.orphelinPur = orphelinPur;
	}

	public String getOrphelinPur() {
		return orphelinPur;
	}

	public void setUserSas(String userSas) {
		this.userSas = userSas;
	}

	public String getUserSas() {
		return userSas;
	}

	public void setArrerageAvantConstitution(String arrerageAvantConstitution) {
		this.arrerageAvantConstitution = arrerageAvantConstitution;
	}

	public String getArrerageAvantConstitution() {
		return arrerageAvantConstitution;
	}

	public void setArrerages(String arrerages) {
		this.arrerages = arrerages;
	}

	public String getArrerages() {
		return arrerages;
	}

	public void setUserSasModificateur(String userSasModificateur) {
		this.userSasModificateur = userSasModificateur;
	}

	public String getUserSasModificateur() {
		return userSasModificateur;
	}

	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}

	public String getDateModification() {
		return dateModification;
	}

	public void setDonneeConforme(String donneeConforme) {
		this.donneeConforme = donneeConforme;
	}

	public String getDonneeConforme() {
		return donneeConforme;
	}

	public void setObservationDonneeConforme(String observationDonneeConforme) {
		this.observationDonneeConforme = observationDonneeConforme;
	}

	public String getObservationDonneeConforme() {
		return observationDonneeConforme;
	}

	public List<QuittanceGsrVO> getListQuittanceVO() {
		return listQuittanceVO;
	}

	public void setListQuittanceVO(List<QuittanceGsrVO> listQuittanceVO) {
		this.listQuittanceVO = listQuittanceVO;
	}
	

	/**
	 * @return the montantPercu
	 */
	public String getMontantPercu() {
		return montantPercu;
	}

	/**
	 * @param montantPercu the montantPercu to set
	 */
	public void setMontantPercu(String montantPercu) {
		this.montantPercu = montantPercu;
	}

	/**
	 * @return the mntQuittancesRegles
	 */
	public String getMntQuittancesRegles() {
		return mntQuittancesRegles;
	}

	/**
	 * @param mntQuittancesRegles the mntQuittancesRegles to set
	 */
	public void setMntQuittancesRegles(String mntQuittancesRegles) {
		this.mntQuittancesRegles = mntQuittancesRegles;
	}

	public String getAncienMontantRenteTrimestrielle() {
		return ancienMontantRenteTrimestrielle;
	}

	public void setAncienMontantRenteTrimestrielle(
			String ancienMontantRenteTrimestrielle) {
		this.ancienMontantRenteTrimestrielle = ancienMontantRenteTrimestrielle;
	}

	public String getAncienTauxIpp() {
		return ancienTauxIpp;
	}

	public void setAncienTauxIpp(String ancienTauxIpp) {
		this.ancienTauxIpp = ancienTauxIpp;
	}

	public String getMntCapitalCnra() {
		return mntCapitalCnra;
	}

	public void setMntCapitalCnra(String mntCapitalCnra) {
		this.mntCapitalCnra = mntCapitalCnra;
	}

	public String getMntCapitalCnraCalcule() {
		return mntCapitalCnraCalcule;
	}

	public void setMntCapitalCnraCalcule(String mntCapitalCnraCalcule) {
		this.mntCapitalCnraCalcule = mntCapitalCnraCalcule;
	}

	public String getMntCapitalCnraDiff() {
		return mntCapitalCnraDiff;
	}

	public void setMntCapitalCnraDiff(String mntCapitalCnraDiff) {
		this.mntCapitalCnraDiff = mntCapitalCnraDiff;
	}

	public String getVilleLibelle() {
		return villeLibelle;
	}

	public void setVilleLibelle(String villeLibelle) {
		this.villeLibelle = villeLibelle;
	}

	public String getLibellePays() {
		return libellePays;
	}

	public void setLibellePays(String libellePays) {
		this.libellePays = libellePays;
	}

	public String getLibelleNationalite() {
		return libelleNationalite;
	}

	public void setLibelleNationalite(String libelleNationalite) {
		this.libelleNationalite = libelleNationalite;
	}

	public String getLibelleSituationRentier() {
		return libelleSituationRentier;
	}

	public void setLibelleSituationRentier(String libelleSituationRentier) {
		this.libelleSituationRentier = libelleSituationRentier;
	}

	public void setRefSortGsr(SortGsrVO refSortGsr) {
		this.refSortGsr = refSortGsr;
	}

	public SortGsrVO getRefSortGsr() {
		return refSortGsr;
	}

	public void setDateLimiteConsignation(String dateLimiteConsignation) {
		this.dateLimiteConsignation = dateLimiteConsignation;
	}

	public String getDateLimiteConsignation() {
		return dateLimiteConsignation;
	}

	public String getMntCapitalDu() {
		return mntCapitalDu;
	}

	public void setMntCapitalDu(String mntCapitalDu) {
		this.mntCapitalDu = mntCapitalDu;
	}

	public String getQuotePart() {
		return quotePart;
	}

	public void setQuotePart(String quotePart) {
		this.quotePart = quotePart;
	}

	public String getNbrTrimestreAregler() {
		return nbrTrimestreAregler;
	}

	public void setNbrTrimestreAregler(String nbrTrimestreAregler) {
		this.nbrTrimestreAregler = nbrTrimestreAregler;
	}

	public String getMntArrerage() {
		return mntArrerage;
	}

	public void setMntArrerage(String mntArrerage) {
		this.mntArrerage = mntArrerage;
	}

	public String getMntReliquat() {
		return mntReliquat;
	}

	public void setMntReliquat(String mntReliquat) {
		this.mntReliquat = mntReliquat;
	}
	
	

}
