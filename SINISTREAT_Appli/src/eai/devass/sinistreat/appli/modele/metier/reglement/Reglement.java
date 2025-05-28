package eai.devass.sinistreat.appli.modele.metier.reglement;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.rmawatanya.reglement.application.metier.usecases.services.quittance.IEmissionQuittance;
import com.rmawatanya.reglement.application.metier.usecases.services.quittance.IVariableQuittance;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.Banque;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRgl;
import eai.devass.sinistreat.appli.modele.parametrage.NatureRecuperation;
import eai.devass.sinistreat.appli.modele.parametrage.PieceReglement;
import eai.devass.sinistreat.appli.modele.parametrage.TypeQuittance;
import eai.devass.sinistreat.appli.modele.parametrage.TypeReglement;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;

/**
 * SuppressWarnings("unused"): champs avec valeurs fixes necessaires pour la
 * conversion au type quittance du service de quittancement
 */
@SuppressWarnings("unused")
@AConverter(entiteDist = "com.rmawatanya.reglement.application.metier.valueobjects.QuittanceRRVO")
public class Reglement {
	private Long id;
	private String adresseBenef;
	private String villeBenef;

	@AConverter(propertyDist = "numeroQuittance")
	private String numeroQuittance;
	private String numeroQuittanceRemplacement;
	@AConverter(propertyDist = "typeBeneficiare")
	private String typeBeneficiaire;
	@AConverter(propertyDist = "nomBeneficiaire")
	private String nomBeneficiaire;
	@AConverter(propertyDist = "codeIntermediaire")
	private String codeIntermediaire;
	private String nomIntermediaire;
	@AConverter(propertyDist = "dateEmission", pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dateEmission;
	private String nomUserCreateur;
	@AConverter(propertyDist = "codeUtilisateur")
	private String codeUserCreateur;
	private String motifRejet;
	private String codeUserModificateur;
	private String nomUserModificateur;
	@AConverter(propertyDist = "valeurTresorerie")
	private Double montant;
	private Date dateCreation;
	private Date dateModification;
	private String service;
	private Date dateReglement;
	private String modeReglement;
	private Date dateEtablissement;
	private String choixTypeCheque;
	private String vosReference;
	@AConverter(propertyDist = "refReglement")
	private String reference;
	// Direct
	private String codeChefGreffier;
	private String typeIntermediaireRgl;
	private String nomChefGreffier;
	private String nomBarreau;
	private String codeBarreau;
	private String nomIntermediaireRgl;
	private String codeIntermediaireRgl;
	private String avocatTiers;
	private Boolean subordonnees;
	private transient boolean reglement;
	// Auxilaire
	@AConverter(propertyDist = "referencefacture")
	private String referenceAuxiliaire;
	private String nomMandataire;
	@AConverter(propertyDist = "codeMondataire")
	private String codeMandataire;
	private String nomAuxiliaire;
	private String codeAuxiliaire;
	private String numeroMission;
	@AConverter(propertyDist = "datefacture", pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dateNote;
	// Intermedaire
	private String numeroBordereau;
	private Date dateBordereau;
	private String numCheque;
	// Recuperation
	private NatureRecuperation refNatureRecuperation;
	private String numeroRemise;
	private Date dateRemise;
	private Date dateEtat;
	private String emetteur;
	private String codeAvocatAdverse;
	private String nomAvocatAdverse;
	@AConverter(propertyDist = "nomPartieAdverse")
	private String nomPartieAdverse;
	private String typePartieAdverse;
	private String codePartieAdverse;
	private String motifTropPercu;
	private String motifAnnulation;
	private String numeroRecours;
	@AConverter(propertyDist = "codeBeneficiaire")
	private String codeBeneficiaire;
	private Banque refBanque;
	private TypeReglement refTypeReglement;
	@AConverter(propertyDist = "typeQuittance", propertyOrig = "refTypeQuittance.code")
	private TypeQuittance refTypeQuittance;
	@AConverter(propertyDist = "codeSousTypeQuittance")
	private String sousTypeQuittance;
	private EtatRgl refEtatReglement;
	private Sinistre refSinistre;
	@AConverter(propertyDist = "listDetailPrestationVO")
	private List<DetailReglement> listDetailReglement;
	private Long idOrdonnancement;
	@AConverter(propertyDist = "numeroSinistre")
	private transient String numeroSinistre;
	@AConverter(propertyDist = "numeroContrat")
	private transient String numeroContrat;
	@AConverter(propertyDist = "numeroClient")
	private transient String numeroClient;
	@AConverter(propertyDist = "raisonSociale")
	private transient String raisonSociale;
	@AConverter(propertyDist = "typeIntermediaire")
	private transient String typeIntermediaire;
	@AConverter(propertyDist = "codeSousTypeQuittance")
	private transient String codeSousTypeQuittance;
	@AConverter(propertyDist = "codeBranche")
	private transient String codeBranche;
	@AConverter(propertyDist = "codeCategorie")
	private transient String codeCategorie;
	@AConverter(propertyDist = "codeServiceOrdonnateur")
	private transient String codeServiceOrdonnateur;
	@AConverter(propertyDist = "codeCoassurance")
	private transient String codeCoassurance;
	@AConverter(propertyDist = "codeDevisePenalite")
	private transient String codeDevisePenalite;
	@AConverter(propertyDist = "codeDevisePrestation")
	private transient String codeDevisePrestation;
	@AConverter(propertyDist = "codeDeviseTaxe")
	private transient String codeDeviseTaxe;
	@AConverter(propertyDist = "codeSociete")
	private transient String codeSociete;
	@AConverter(propertyDist = "codeSocieteGestion")
	private transient String codeSocieteGestion;
	@AConverter(propertyDist = "typeBeneficiare")
	private transient String typeBeneficiare;
	@AConverter(propertyDist = "codePays")
	private transient String codePays;
	@AConverter(propertyDist = "codeProduit")
	private transient String codeProduit;

	private String sort;
	private Date dateSort;
	@AConverter(propertyDist = "dateReceptionfacture" , pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dateReception;
	private String typeBeneficiaireLettre;
	private String nomBeneficiaireLettre;
	@AConverter(propertyDist = "typeLettre")
	private transient String typeLettre;
	@AConverter(propertyDist = "refProcedure")
	private Long refProcedure;
	@AConverter(propertyDist = "salaireMensuel")
	private Double salaireMensuel;
	@AConverter(propertyDist = "renteAnnuelle")
	private Double renteAnnuelle;
	@AConverter(propertyDist = "ancienEtat")
	private transient String ancienEtat;

	/**
	 * WMOS:15/09/2015 -Début 'Evol Réglementaire l'ajout d'un champ date signature
	 * quittance (champ modifiable quelque soit l'état de la quittance)
	 */
	private Date dateSignature;
	private Date dateProchaineEcheance;
	private Boolean emissionITT = Boolean.FALSE;
	private String creatorFirstQtc;
	private Boolean smig = Boolean.FALSE;
	private transient Double salaireSmig;
	private Boolean rappel = Boolean.FALSE;

	/**
	 * WMOS:15/09/2015 -Fin 'Evol Réglementaire
	 */

	private String villeBeneficiaireLettre;
	private String adresseBeneficiaireLettre;
	private String codeIntermediaireBL;
	private String codePrestataireBL;
	private List<FraisMedicaux> listFraisMedicaux;
	private List<FraisFuneraire> listFraisFuneraire;

	// RBAD: Début Evol : l'ajout du bloc destinataire chèque
	private String typeDestinataireCheque;
	private String nomDestinataireCheque;
	private String adresseDestinataireCheque;
	private String villeDestinataireCheque;
	private String codeIntermediaireDC;
	private String codePrestataireDC;
	private String typeUtilisateurConnecte;
	private String nbRelance;
	private Double montantRAS;
	private String ras;
	private Boolean isComplement = Boolean.FALSE;
	private String actionPreQuitt;
	private String zoneARisque;
	private Boolean regler ;
	private List<ReglementPieceAt> reglementPieceAt;

	public String getRas() {
		return ras;
	}

	public void setRas(String ras) {
		this.ras = ras;
	}
	public List<ReglementPieceAt> getReglementPieceAt() {
		return reglementPieceAt;
	}

	public void setReglementPieceAt(List<ReglementPieceAt> reglementPieceAt) {
		this.reglementPieceAt = reglementPieceAt;
	}
	
	public Double getMontantRAS() {
		return montantRAS;
	}

	public void setMontantRAS(Double montantRAS) {
		this.montantRAS = montantRAS;
	}
	// Evol lot 5
	private Date dateEditionIntermediaire;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Date getDateSort() {
		return dateSort;
	}

	public void setDateSort(Date dateSort) {
		this.dateSort = dateSort;
	}

	// Pour la convertion (VO BO)
	private transient String[] propertiesToConvert;

	private transient String[] codeEtatRgl;

	public Reglement() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroQuittance() {
		return numeroQuittance;
	}

	public void setNumeroQuittance(String numeroQuittance) {
		this.numeroQuittance = numeroQuittance;
	}

	public String getNumeroQuittanceRemplacement() {
		return numeroQuittanceRemplacement;
	}

	public void setNumeroQuittanceRemplacement(String numeroQuittanceRemplacement) {
		this.numeroQuittanceRemplacement = numeroQuittanceRemplacement;
	}

	public String getTypeBeneficiaire() {
		return typeBeneficiaire;
	}

	public void setTypeBeneficiaire(String typeBeneficiaire) {
		this.typeBeneficiaire = typeBeneficiaire;
	}

	public Boolean getSubordonnees() {
		return subordonnees;
	}

	public void setSubordonnees(Boolean subordonnees) {
		this.subordonnees = subordonnees;
	}

	public boolean isReglement() {
		return reglement;
	}

	public void setReglement(boolean reglement) {
		this.reglement = reglement;
	}

	public Long getIdOrdonnancement() {
		return idOrdonnancement;
	}

	public void setIdOrdonnancement(Long idOrdonnancement) {
		this.idOrdonnancement = idOrdonnancement;
	}

	public String getNomBeneficiaire() {
		return nomBeneficiaire;
	}

	public void setNomBeneficiaire(String nomBeneficiaire) {
		this.nomBeneficiaire = nomBeneficiaire;
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

	public Date getDateEmission() {
		return dateEmission;
	}

	public void setDateEmission(Date dateEmission) {
		this.dateEmission = dateEmission;
	}

	public String getMotifRejet() {
		return motifRejet;
	}

	public void setMotifRejet(String motifRejet) {
		this.motifRejet = motifRejet;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Date getDateReglement() {
		return dateReglement;
	}

	public void setDateReglement(Date dateReglement) {
		this.dateReglement = dateReglement;
	}

	public String getCodeChefGreffier() {
		return codeChefGreffier;
	}

	public void setCodeChefGreffier(String codeChefGreffier) {
		this.codeChefGreffier = codeChefGreffier;
	}

	public String getNomChefGreffier() {
		return nomChefGreffier;
	}

	public void setNomChefGreffier(String nomChefGreffier) {
		this.nomChefGreffier = nomChefGreffier;
	}

	public String getNomBarreau() {
		return nomBarreau;
	}

	public void setNomBarreau(String nomBarreau) {
		this.nomBarreau = nomBarreau;
	}

	public String getCodeBarreau() {
		return codeBarreau;
	}

	public void setCodeBarreau(String codeBarreau) {
		this.codeBarreau = codeBarreau;
	}

	public String getNomIntermediaireRgl() {
		return nomIntermediaireRgl;
	}

	public void setNomIntermediaireRgl(String nomIntermediaireRgl) {
		this.nomIntermediaireRgl = nomIntermediaireRgl;
	}

	public String getCodeIntermediaireRgl() {
		return codeIntermediaireRgl;
	}

	public void setCodeIntermediaireRgl(String codeIntermediaireRgl) {
		this.codeIntermediaireRgl = codeIntermediaireRgl;
	}

	public String getTypeIntermediaireRgl() {
		return typeIntermediaireRgl;
	}

	public void setTypeIntermediaireRgl(String typeIntermediaireRgl) {
		this.typeIntermediaireRgl = typeIntermediaireRgl;
	}

	public String getAvocatTiers() {
		return avocatTiers;
	}

	public void setAvocatTiers(String avocatTiers) {
		this.avocatTiers = avocatTiers;
	}

	public String getReferenceAuxiliaire() {
		return referenceAuxiliaire;
	}

	public void setReferenceAuxiliaire(String referenceAuxiliaire) {
		this.referenceAuxiliaire = referenceAuxiliaire;
	}

	public String getNomMandataire() {
		return nomMandataire;
	}

	public void setNomMandataire(String nomMandataire) {
		this.nomMandataire = nomMandataire;
	}

	public String getCodeMandataire() {
		return codeMandataire;
	}

	public void setCodeMandataire(String codeMandataire) {
		this.codeMandataire = codeMandataire;
	}

	public String getNomAuxiliaire() {
		return nomAuxiliaire;
	}

	public void setNomAuxiliaire(String nomAuxiliaire) {
		this.nomAuxiliaire = nomAuxiliaire;
	}

	public String getCodeAuxiliaire() {
		return codeAuxiliaire;
	}

	public void setCodeAuxiliaire(String codeAuxiliaire) {
		this.codeAuxiliaire = codeAuxiliaire;
	}

	public String getNumeroMission() {
		return numeroMission;
	}

	public void setNumeroMission(String numeroMission) {
		this.numeroMission = numeroMission;
	}

	public Date getDateNote() {
		return dateNote;
	}

	public void setDateNote(Date dateNote) {
		this.dateNote = dateNote;
	}

	public String getNumeroBordereau() {
		return numeroBordereau;
	}

	public void setNumeroBordereau(String numeroBordereau) {
		this.numeroBordereau = numeroBordereau;
	}

	public Date getDateBordereau() {
		return dateBordereau;
	}

	public void setDateBordereau(Date dateBordereau) {
		this.dateBordereau = dateBordereau;
	}

	public String getNumCheque() {
		return numCheque;
	}

	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public NatureRecuperation getRefNatureRecuperation() {
		return refNatureRecuperation;
	}

	public void setRefNatureRecuperation(NatureRecuperation refNatureRecuperation) {
		this.refNatureRecuperation = refNatureRecuperation;
	}

	public String getNumeroRemise() {
		return numeroRemise;
	}

	public void setNumeroRemise(String numeroRemise) {
		this.numeroRemise = numeroRemise;
	}

	public Date getDateRemise() {
		return dateRemise;
	}

	public void setDateRemise(Date dateRemise) {
		this.dateRemise = dateRemise;
	}

	public Date getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(Date dateEtat) {
		this.dateEtat = dateEtat;
	}

	public String getEmetteur() {
		return emetteur;
	}

	public void setEmetteur(String emetteur) {
		this.emetteur = emetteur;
	}

	public String getCodeAvocatAdverse() {
		return codeAvocatAdverse;
	}

	public void setCodeAvocatAdverse(String codeAvocatAdverse) {
		this.codeAvocatAdverse = codeAvocatAdverse;
	}

	public String getNomAvocatAdverse() {
		return nomAvocatAdverse;
	}

	public void setNomAvocatAdverse(String nomAvocatAdverse) {
		this.nomAvocatAdverse = nomAvocatAdverse;
	}

	public String getNomPartieAdverse() {
		return nomPartieAdverse;
	}

	public void setNomPartieAdverse(String nomPartieAdverse) {
		this.nomPartieAdverse = nomPartieAdverse;
	}

	public String getTypePartieAdverse() {
		return typePartieAdverse;
	}

	public void setTypePartieAdverse(String typePartieAdverse) {
		this.typePartieAdverse = typePartieAdverse;
	}

	public String getCodePartieAdverse() {
		return codePartieAdverse;
	}

	public void setCodePartieAdverse(String codePartieAdverse) {
		this.codePartieAdverse = codePartieAdverse;
	}

	public String getMotifTropPercu() {
		return motifTropPercu;
	}

	public void setMotifTropPercu(String motifTropPercu) {
		this.motifTropPercu = motifTropPercu;
	}

	public String getMotifAnnulation() {
		return motifAnnulation;
	}

	public void setMotifAnnulation(String motifAnnulation) {
		this.motifAnnulation = motifAnnulation;
	}

	public String getNumeroRecours() {
		return numeroRecours;
	}

	public void setNumeroRecours(String numeroRecours) {
		this.numeroRecours = numeroRecours;
	}

	public String getCodeBeneficiaire() {
		if (typeBeneficiaire != null) {
			if (typeBeneficiaire.equals(IConstantes.BENEFICIAIRE_BARREAU)) {
				codeBeneficiaire = this.codeBarreau;
			} else if (typeBeneficiaire.equals(IConstantes.BENEFICIAIRE_CHEF_GREFFIER)) {
				codeBeneficiaire = this.codeChefGreffier;
			} else if (typeBeneficiaire.equals(IConstantes.BENEFICIAIRE_INTERMEDIAIRE)) {
				codeBeneficiaire = this.codeIntermediaireRgl;
			} else if (typeBeneficiaire.equals(IConstantes.BENEFICIAIRE_ASSURE)) {
				codeBeneficiaire = this.refSinistre.getCodeClient();
			}
		} else {
			codeBeneficiaire = "0";
		}

		return codeBeneficiaire;
	}

	public void setCodeBeneficiaire(String codeBeneficiaire) {
		this.codeBeneficiaire = codeBeneficiaire;
	}

	public String getTypeDestinataireCheque() {
		return typeDestinataireCheque;
	}

	public void setTypeDestinataireCheque(String typeDestinataireCheque) {
		this.typeDestinataireCheque = typeDestinataireCheque;
	}

	public String getNomDestinataireCheque() {
		return nomDestinataireCheque;
	}

	public void setNomDestinataireCheque(String nomDestinataireCheque) {
		this.nomDestinataireCheque = nomDestinataireCheque;
	}

	public String getAdresseDestinataireCheque() {
		return adresseDestinataireCheque;
	}

	public void setAdresseDestinataireCheque(String adresseDestinataireCheque) {
		this.adresseDestinataireCheque = adresseDestinataireCheque;
	}

	public String getVilleDestinataireCheque() {
		return villeDestinataireCheque;
	}

	public void setVilleDestinataireCheque(String villeDestinataireCheque) {
		this.villeDestinataireCheque = villeDestinataireCheque;
	}

	public Banque getRefBanque() {
		return refBanque;
	}

	public void setRefBanque(Banque refBanque) {
		this.refBanque = refBanque;
	}

	public TypeReglement getRefTypeReglement() {
		return refTypeReglement;
	}

	public void setRefTypeReglement(TypeReglement refTypeReglement) {
		this.refTypeReglement = refTypeReglement;
	}

	public TypeQuittance getRefTypeQuittance() {
		return refTypeQuittance;
	}

	public void setRefTypeQuittance(TypeQuittance refTypeQuittance) {
		this.refTypeQuittance = refTypeQuittance;
	}

	public String getSousTypeQuittance() {
		return sousTypeQuittance;
	}

	public void setSousTypeQuittance(String sousTypeQuittance) {
		this.sousTypeQuittance = sousTypeQuittance;
	}

	public EtatRgl getRefEtatReglement() {
		return refEtatReglement;
	}

	public void setRefEtatReglement(EtatRgl refEtatReglement) {
		this.refEtatReglement = refEtatReglement;
	}

	public Sinistre getRefSinistre() {
		return refSinistre;
	}

	public void setRefSinistre(Sinistre refSinistre) {
		this.refSinistre = refSinistre;
	}

	public List<DetailReglement> getListDetailReglement() {
		return listDetailReglement;
	}

	public void setListDetailReglement(List<DetailReglement> listDetailReglement) {
		this.listDetailReglement = listDetailReglement;
	}

	public void setDateEtablissement(Date dateEtablissement) {
		this.dateEtablissement = dateEtablissement;
	}

	public Date getDateEtablissement() {
		return dateEtablissement;
	}

	public String getNomUserCreateur() {
		return nomUserCreateur;
	}

	public void setNomUserCreateur(String nomUserCreateur) {
		this.nomUserCreateur = nomUserCreateur;
	}

	public String getCodeUserCreateur() {
		return codeUserCreateur;
	}

	public void setCodeUserCreateur(String codeUserCreateur) {
		this.codeUserCreateur = codeUserCreateur;
	}

	public String getCodeUserModificateur() {
		return codeUserModificateur;
	}

	public void setCodeUserModificateur(String codeUserModificateur) {
		this.codeUserModificateur = codeUserModificateur;
	}

	public String getNomUserModificateur() {
		return nomUserModificateur;
	}

	public void setNomUserModificateur(String nomUserModificateur) {
		this.nomUserModificateur = nomUserModificateur;
	}

	public void setChoixTypeCheque(String choixTypeCheque) {
		this.choixTypeCheque = choixTypeCheque;
	}

	public String getChoixTypeCheque() {
		return choixTypeCheque;
	}

	public void setVosReference(String vosReference) {
		this.vosReference = vosReference;
	}

	public String getVosReference() {
		return vosReference;
	}

	public String getNumeroSinistre() {
		if (refSinistre != null && !StringUtils.isEmpty(refSinistre.getNumeroSinistre())) {
			numeroSinistre = refSinistre.getNumeroSinistre();
		}
		return numeroSinistre;
	}

	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}

	public String getNumeroContrat() {
		if (refSinistre != null && !StringUtils.isEmpty(refSinistre.getNumeroPolice())) {
			numeroContrat = refSinistre.getNumeroPolice();
		}
		return numeroContrat;
	}

	public void setNumeroContrat(String numeroContrat) {
		this.numeroContrat = numeroContrat;
	}

	public String getNumeroClient() {
		if (refSinistre != null && !StringUtils.isEmpty(refSinistre.getCodeClient())) {
			numeroClient = refSinistre.getCodeClient();
		}
		return numeroClient;
	}

	public void setNumeroClient(String numeroClient) {
		this.numeroClient = numeroClient;
	}

	public String getRaisonSociale() {
		if (refSinistre != null && !StringUtils.isEmpty(refSinistre.getNomClient())) {
			raisonSociale = refSinistre.getNomClient();
		}
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public String getTypeIntermediaire() {
		return typeIntermediaire;
	}

	public void setTypeIntermediaire(String typeIntermediaire) {
		this.typeIntermediaire = typeIntermediaire;
	}

	public String getCodeSousTypeQuittance() {
		return codeSousTypeQuittance;
	}

	public void setCodeSousTypeQuittance(String codeSousTypeQuittance) {
		this.codeSousTypeQuittance = codeSousTypeQuittance;
	}

	public String getCodeBranche() {
		return codeBranche;
	}

	public void setCodeBranche(String codeBranche) {
		this.codeBranche = codeBranche;
	}

	public String getCodeCategorie() {
		return codeCategorie;
	}

	public void setCodeCategorie(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public String getCodeServiceOrdonnateur() {
		return codeServiceOrdonnateur;
	}

	public void setCodeServiceOrdonnateur(String codeServiceOrdonnateur) {
		this.codeServiceOrdonnateur = codeServiceOrdonnateur;
	}

	public String getCodeCoassurance() {
		return String.valueOf(IVariableQuittance.CONTRAT_AFFAIRE_100);
	}

	public void setCodeCoassurance(String codeCoassurance) {
		this.codeCoassurance = codeCoassurance;
	}

	public String getCodeDevisePenalite() {
		return IEmissionQuittance.CODE_DEVISE_MAD;
	}

	public void setCodeDevisePenalite(String codeDevisePenalite) {
		this.codeDevisePenalite = codeDevisePenalite;
	}

	public String getCodeDevisePrestation() {
		return IEmissionQuittance.CODE_DEVISE_MAD;
	}

	public void setCodeDevisePrestation(String codeDevisePrestation) {
		this.codeDevisePrestation = codeDevisePrestation;
	}

	public String getCodeDeviseTaxe() {
		return IEmissionQuittance.CODE_DEVISE_MAD;
	}

	public void setCodeDeviseTaxe(String codeDeviseTaxe) {
		this.codeDeviseTaxe = codeDeviseTaxe;
	}

	public String getCodeSociete() {
		return IParam.CODE_SOCIETE_AT;
	}

	public void setCodeSociete(String codeSociete) {
		this.codeSociete = codeSociete;
	}

	public String getCodeSocieteGestion() {
		return IParam.CODE_SOCIETE_GESTION_AT;
	}

	public void setCodeSocieteGestion(String codeSocieteGestion) {
		this.codeSocieteGestion = codeSocieteGestion;
	}

	public String getTypeBeneficiare() {
		return "0";
	}

	public void setTypeBeneficiare(String typeBeneficiare) {
		this.typeBeneficiare = typeBeneficiare;
	}

	public String getCodePays() {
		return IEmissionQuittance.CODE_PAYS_MA;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public String getCodeProduit() {
		return IParam.CODE_BRANCHE_AT;
	}

	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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

	public String[] getCodeEtatRgl() {
		return codeEtatRgl;
	}

	public void setCodeEtatRgl(String[] codeEtatRgl) {

		String[] copyCodeEtatRgl = null;
		if (codeEtatRgl != null) {
			copyCodeEtatRgl = codeEtatRgl.clone();
		}

		this.codeEtatRgl = copyCodeEtatRgl;

	}

	public String getAdresseBenef() {
		return adresseBenef;
	}

	public void setAdresseBenef(String adresseBenef) {
		this.adresseBenef = adresseBenef;
	}

	public String getVilleBenef() {
		return villeBenef;
	}

	public void setVilleBenef(String villeBenef) {
		this.villeBenef = villeBenef;
	}

	// Ck
	public boolean isInstanceValidationSup() {
		return (IConstantes.ETAT_REGLEMENT_EN_INSTANCE_VALIDATION_SUPERIEURE.equals(getRefEtatReglement().getCode()));
	}

	public boolean isInstancePositionnement() {
		return (IConstantes.ETAT_REGLEMENT_EN_INSTANCE_POSITIONNEMENT.equals(getRefEtatReglement().getCode()));
	}

	public Date getDateSignature() {
		return dateSignature;
	}

	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	public Date getDateReception() {
		return dateReception;
	}

	public void setDateReception(Date dateReception) {
		this.dateReception = dateReception;
	}

	public String getTypeBeneficiaireLettre() {
		return typeBeneficiaireLettre;
	}

	public void setTypeBeneficiaireLettre(String typeBeneficiaireLettre) {
		this.typeBeneficiaireLettre = typeBeneficiaireLettre;
	}

	public String getNomBeneficiaireLettre() {
		return nomBeneficiaireLettre;
	}

	public void setNomBeneficiaireLettre(String nomBeneficiaireLettre) {
		this.nomBeneficiaireLettre = nomBeneficiaireLettre;
	}

	public String getTypeLettre() {
		return typeLettre;
	}

	public void setTypeLettre(String typeLettre) {
		this.typeLettre = typeLettre;
	}

	public Long getRefProcedure() {
		return refProcedure;
	}

	public void setRefProcedure(Long refProcedure) {
		this.refProcedure = refProcedure;
	}

	public Double getSalaireMensuel() {
		return salaireMensuel;
	}

	public void setSalaireMensuel(Double salaireMensuel) {
		this.salaireMensuel = salaireMensuel;
	}

	public Double getRenteAnnuelle() {
		return renteAnnuelle;
	}

	public void setRenteAnnuelle(Double renteAnnuelle) {
		this.renteAnnuelle = renteAnnuelle;
	}

	public String getAncienEtat() {
		return ancienEtat;
	}

	public void setAncienEtat(String ancienEtat) {
		this.ancienEtat = ancienEtat;
	}

	public Date getDateProchaineEcheance() {
		return dateProchaineEcheance;
	}

	public void setDateProchaineEcheance(Date dateProchaineEcheance) {
		this.dateProchaineEcheance = dateProchaineEcheance;
	}

	public Boolean getEmissionITT() {
		return emissionITT;
	}

	public void setEmissionITT(Boolean emissionITT) {
		this.emissionITT = emissionITT;
	}

	public String getCreatorFirstQtc() {
		return creatorFirstQtc;
	}

	public void setCreatorFirstQtc(String creatorFirstQtc) {
		this.creatorFirstQtc = creatorFirstQtc;
	}

	public Boolean getSmig() {
		return smig;
	}

	public void setSmig(Boolean smig) {
		this.smig = smig;
	}

	public Double getSalaireSmig() {
		return salaireSmig;
	}

	public void setSalaireSmig(Double salaireSmig) {
		this.salaireSmig = salaireSmig;
	}

	public Boolean getRappel() {
		return rappel;
	}

	public void setRappel(Boolean rappel) {
		this.rappel = rappel;
	}

	public String getVilleBeneficiaireLettre() {
		return villeBeneficiaireLettre;
	}

	public void setVilleBeneficiaireLettre(String villeBeneficiaireLettre) {
		this.villeBeneficiaireLettre = villeBeneficiaireLettre;
	}

	public String getAdresseBeneficiaireLettre() {
		return adresseBeneficiaireLettre;
	}

	public void setAdresseBeneficiaireLettre(String adresseBeneficiaireLettre) {
		this.adresseBeneficiaireLettre = adresseBeneficiaireLettre;
	}

	public String getCodeIntermediaireBL() {
		return codeIntermediaireBL;
	}

	public void setCodeIntermediaireBL(String codeIntermediaireBL) {
		this.codeIntermediaireBL = codeIntermediaireBL;
	}

	public String getCodePrestataireBL() {
		return codePrestataireBL;
	}

	public void setCodePrestataireBL(String codePrestataireBL) {
		this.codePrestataireBL = codePrestataireBL;
	}

	public List<FraisMedicaux> getListFraisMedicaux() {
		return listFraisMedicaux;
	}

	public void setListFraisMedicaux(List<FraisMedicaux> listFraisMedicaux) {
		this.listFraisMedicaux = listFraisMedicaux;
	}
	
	public List<FraisFuneraire> getListFraisFuneraire() {
		return listFraisFuneraire;
	}

	public void setListFraisFuneraire(List<FraisFuneraire> listFraisFuneraire) {
		this.listFraisFuneraire = listFraisFuneraire;
	}

	public String getCodeIntermediaireDC() {
		return codeIntermediaireDC;
	}

	public void setCodeIntermediaireDC(String codeIntermediaireDC) {
		this.codeIntermediaireDC = codeIntermediaireDC;
	}

	public String getCodePrestataireDC() {
		return codePrestataireDC;
	}

	public void setCodePrestataireDC(String codePrestataireDC) {
		this.codePrestataireDC = codePrestataireDC;
	}

	public Date getDateEditionIntermediaire() {
		return dateEditionIntermediaire;
	}

	public void setDateEditionIntermediaire(Date dateEditionIntermediaire) {
		this.dateEditionIntermediaire = dateEditionIntermediaire;
	}

	public String getTypeUtilisateurConnecte() {
		return typeUtilisateurConnecte;
	}

	public void setTypeUtilisateurConnecte(String typeUtilisateurConnecte) {
		this.typeUtilisateurConnecte = typeUtilisateurConnecte;
	}

	public String getNbRelance() {
		return nbRelance;
	}

	public void setNbRelance(String nbRelance) {
		this.nbRelance = nbRelance;
	}
	public Boolean getIsComplement() {
		return isComplement;
	}

	public void setIsComplement(Boolean isComplement) {
		this.isComplement = isComplement;
	}
	public String getZoneARisque() {
		return zoneARisque;
	}

	public void setZoneARisque(String zoneARisque) {
		this.zoneARisque = zoneARisque;
	}

	public String getActionPreQuitt() {
		return actionPreQuitt;
	}

	public void setActionPreQuitt(String actionPreQuitt) {
		this.actionPreQuitt = actionPreQuitt;
	}
	public Boolean getRegler() {
		return regler;
	}

	public void setRegler(Boolean regler) {
		this.regler = regler;
	}
	

}