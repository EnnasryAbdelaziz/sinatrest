package eai.devass.sinistreat.appli.valueobjects.metier.reglement;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.BanqueVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatRglVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.NatureRecuperationVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeQuittanceVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeReglementVO;

public class ReglementVO implements IValueObject {
	private String id;
	private String idReglement;
	private String sort;
	private String adresseBenef;
	private String villeBenef;
	private String dateSort;
	private String numeroQuittance;
	private String numeroQuittanceRemplacement;
	private String typeBeneficiaire;
	private String nomBeneficiaire;
	private String codeIntermediaire;
	private String nomIntermediaire;
	private String dateEmission;
	private String nomUserCreateur;
	private String codeUserCreateur;
	private String codeUserModificateur;
	private String nomUserModificateur;
	private String motifRejet;
	private String dateReglement;
	private String dateEtablissement;
	private String modeReglement;
	private String montant;
	private String dateModification;
	private String dateCreation;
	private String service;
	private String choixTypeCheque;
	private String vosReference;
	private String avocatTiers;
	private String subordonnees;
	// Direct
	private String codeChefGreffier;
	private String nomChefGreffier;
	private String nomBarreau;
	private String codeBarreau;
	private String nomIntermediaireRgl;
	private String codeIntermediaireRgl;
	// Auxilaire
	private String referenceAuxiliaire;
	private String nomMandataire;
	private String codeMandataire;
	private String nomAuxiliaire;
	private String codeAuxiliaire;
	private String numeroMission;
	private String dateNote;
	private String reglement;
	// Intermedaire
	private String idOrdonnancement;
	private String numeroBordereau;
	private String dateBordereau;
	private String numCheque;
	// Recuperation
	private NatureRecuperationVO refNatureRecuperation;
	private String numeroRemise;
	private String dateRemise;
	private String emetteur;
	private String codeAvocatAdverse;
	private String nomAvocatAdverse;
	private String nomPartieAdverse;
	private String typePartieAdverse;
	private String codePartieAdverse;
	private String motifTropPercu;
	private String motifAnnulation;
	private String dateEtat;
	private String numeroRecours;
	// Recherche
	private String dateEtablissementFin;
	private String dateEtablissementDebut;
	private String sousTypeQuittance;
	// A confirmer
	private String typeIntermediaireRgl;
	private String typeIntermediaire;
	private String codeBeneficiaire;
	private String reference;
	private TypeReglementVO refTypeReglement;
	private TypeQuittanceVO refTypeQuittance;
	private OrdonnancementVO refOrdonnancement;
	private SinistreVO refSinistre;
	private BanqueVO refBanque;
	private EtatRglVO refEtatReglement;
	private List listDetailReglement;

	private String codeSasSup;
	private String isSup;
	private String typeUtilisateurConnecte;
	/**
	 * WMOS:15/09/2015 -Début 'Evol Réglementaire l'ajout d'un champ date
	 * signature quittance (champ modifiable quelque soit l'état de la
	 * quittance)
	 */
	private String dateSignature;
	/**
	 * WMOS:15/09/2015 -Fin 'Evol Réglementaire
	 */

	private String dateReception;
	private String typeBeneficiaireLettre;
	private String nomBeneficiaireLettre;

	private String typeLettre;
	private String nbrEdition;
	private String refProcedure;
	private String salaireMensuel;
	private String renteAnnuelle;
	private String ancienEtat;

	private String dateProchaineEcheance;
	private String[] codeEtatRgl;
	private String emissionITT;
	private String creatorFirstQtc;
	private String smig;
	private String salaireSmig;
	private String rappel;
	
	private String adresseBeneficiaireLettre;
	private String villeBeneficiaireLettre;
	private String codeIntermediaireBL;
	private String codePrestataireBL;
	private List listFraisMedicaux;
	private List listFraisFuneraire;
	
	// RBAD evol l'ajout du bloc destinataire chèque
	private String typeDestinataireCheque;
	private String nomDestinataireCheque;
	private String adresseDestinataireCheque;
	private String villeDestinataireCheque;
	private String codeIntermediaireDC;
	private String codePrestataireDC;
	// Evol lot 5
	private  String dateEditionIntermediaire;
	// Evol MAD
	private String nbRelance;
	private String montantRAS;
	private String zoneARisque;
	private String actionPreQuitt;
	private String isComplement;
	private String regler;
    private List reglementPieceAt;
    private String ras;
    
    
	public String getRas() {
		return ras;
	}

	public void setRas(String ras) {
		this.ras = ras;
	}

	
	public List getReglementPieceAt() {
		return reglementPieceAt;
	}

	public void setReglementPieceAt(List reglementPieceAt) {
		this.reglementPieceAt = reglementPieceAt;
	}
	
	public String getMontantRAS() {
		return montantRAS;
	}

	public void setMontantRAS(String montantRAS) {
		this.montantRAS = montantRAS;
	}
	
	public String getVilleBenef() {
		return villeBenef;
	}

	public void setVilleBenef(String villeBenef) {
		this.villeBenef = villeBenef;
	}

	public ReglementVO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public void setNumeroQuittanceRemplacement(
			String numeroQuittanceRemplacement) {
		this.numeroQuittanceRemplacement = numeroQuittanceRemplacement;
	}

	public String getTypeBeneficiaire() {
		return typeBeneficiaire;
	}

	public void setTypeBeneficiaire(String typeBeneficiaire) {
		this.typeBeneficiaire = typeBeneficiaire;
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

	public String getDateEmission() {
		return dateEmission;
	}

	public void setDateEmission(String dateEmission) {
		this.dateEmission = dateEmission;
	}

	public String getMotifRejet() {
		return motifRejet;
	}

	public void setMotifRejet(String motifRejet) {
		this.motifRejet = motifRejet;
	}

	public String getSubordonnees() {
		return subordonnees;
	}

	public void setSubordonnees(String subordonnees) {
		this.subordonnees = subordonnees;
	}

	public BanqueVO getRefBanque() {
		return refBanque;
	}

	public void setRefBanque(BanqueVO refBanque) {
		this.refBanque = refBanque;
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

	public String getIdOrdonnancement() {
		return idOrdonnancement;
	}

	public void setIdOrdonnancement(String idOrdonnancement) {
		this.idOrdonnancement = idOrdonnancement;
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

	public String getDateNote() {
		return dateNote;
	}

	public void setDateNote(String dateNote) {
		this.dateNote = dateNote;
	}

	public String getReglement() {
		return reglement;
	}

	public void setReglement(String reglement) {
		this.reglement = reglement;
	}

	public String getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(String dateEtat) {
		this.dateEtat = dateEtat;
	}

	public String getNumeroRecours() {
		return numeroRecours;
	}

	public void setNumeroRecours(String numeroRecours) {
		this.numeroRecours = numeroRecours;
	}

	public String getDateEtablissement() {
		return dateEtablissement;
	}

	public void setDateEtablissement(String dateEtablissement) {
		this.dateEtablissement = dateEtablissement;
	}

	public String getNumeroBordereau() {
		return numeroBordereau;
	}

	public void setNumeroBordereau(String numeroBordereau) {
		this.numeroBordereau = numeroBordereau;
	}

	public String getDateBordereau() {
		return dateBordereau;
	}

	public void setDateBordereau(String dateBordereau) {
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

	public EtatRglVO getRefEtatReglement() {
		return refEtatReglement;
	}

	public void setRefEtatReglement(EtatRglVO refEtatReglement) {
		this.refEtatReglement = refEtatReglement;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

	public String getDateModification() {
		return dateModification;
	}

	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getDateReglement() {
		return dateReglement;
	}

	public void setDateReglement(String dateReglement) {
		this.dateReglement = dateReglement;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public NatureRecuperationVO getRefNatureRecuperation() {
		return refNatureRecuperation;
	}

	public void setRefNatureRecuperation(
			NatureRecuperationVO refNatureRecuperation) {
		this.refNatureRecuperation = refNatureRecuperation;
	}

	public String getNumeroRemise() {
		return numeroRemise;
	}

	public void setNumeroRemise(String numeroRemise) {
		this.numeroRemise = numeroRemise;
	}

	public String getDateRemise() {
		return dateRemise;
	}

	public void setDateRemise(String dateRemise) {
		this.dateRemise = dateRemise;
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

	public String getDateEtablissementFin() {
		return dateEtablissementFin;
	}

	public void setDateEtablissementFin(String dateEtablissementFin) {
		this.dateEtablissementFin = dateEtablissementFin;
	}

	public String getDateEtablissementDebut() {
		return dateEtablissementDebut;
	}

	public void setDateEtablissementDebut(String dateEtablissementDebut) {
		this.dateEtablissementDebut = dateEtablissementDebut;
	}

	public String getSousTypeQuittance() {
		return sousTypeQuittance;
	}

	public void setSousTypeQuittance(String sousTypeQuittance) {
		this.sousTypeQuittance = sousTypeQuittance;
	}

	public String getCodeBeneficiaire() {
		return codeBeneficiaire;
	}

	public void setCodeBeneficiaire(String codeBeneficiaire) {
		this.codeBeneficiaire = codeBeneficiaire;
	}

	public TypeReglementVO getRefTypeReglement() {
		return refTypeReglement;
	}

	public void setRefTypeReglement(TypeReglementVO refTypeReglement) {
		this.refTypeReglement = refTypeReglement;
	}

	public TypeQuittanceVO getRefTypeQuittance() {
		return refTypeQuittance;
	}

	public void setRefTypeQuittance(TypeQuittanceVO refTypeQuittance) {
		this.refTypeQuittance = refTypeQuittance;
	}

	public OrdonnancementVO getRefOrdonnancement() {
		return refOrdonnancement;
	}

	public void setRefOrdonnancement(OrdonnancementVO refOrdonnancement) {
		this.refOrdonnancement = refOrdonnancement;
	}

	public SinistreVO getRefSinistre() {
		return refSinistre;
	}

	public void setRefSinistre(SinistreVO refSinistre) {
		this.refSinistre = refSinistre;
	}

	public List getListDetailReglement() {
		return listDetailReglement;
	}

	public void setListDetailReglement(List listDetailReglement) {
		this.listDetailReglement = listDetailReglement;
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

	public void setVosReference(String vosReference) {
		this.vosReference = vosReference;
	}

	public String getVosReference() {
		return vosReference;
	}

	public String getAvocatTiers() {
		return avocatTiers;
	}

	public void setAvocatTiers(String avocatTiers) {
		this.avocatTiers = avocatTiers;
	}

	public void setChoixTypeCheque(String choixTypeCheque) {
		this.choixTypeCheque = choixTypeCheque;
	}

	public String getChoixTypeCheque() {
		return choixTypeCheque;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getTypeIntermediaireRgl() {
		return typeIntermediaireRgl;
	}

	public void setTypeIntermediaireRgl(String typeIntermediaireRgl) {
		this.typeIntermediaireRgl = typeIntermediaireRgl;
	}

	public String getTypeIntermediaire() {
		return typeIntermediaire;
	}

	public void setTypeIntermediaire(String typeIntermediaire) {
		this.typeIntermediaire = typeIntermediaire;
	}

	public String getCodeSasSup() {
		return codeSasSup;
	}

	public void setCodeSasSup(String codeSasSup) {
		this.codeSasSup = codeSasSup;
	}

	public String getIsSup() {
		return isSup;
	}

	public void setIsSup(String isSup) {
		this.isSup = isSup;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDateSort() {
		return dateSort;
	}

	public void setDateSort(String dateSort) {
		this.dateSort = dateSort;
	}

	public String getAdresseBenef() {
		return adresseBenef;
	}

	public void setAdresseBenef(String adresseBenef) {
		this.adresseBenef = adresseBenef;
	}

	public void setDateSignature(String dateSignature) {
		this.dateSignature = dateSignature;
	}

	public String getDateSignature() {
		return dateSignature;
	}

	public String getDateReception() {
		return dateReception;
	}

	public void setDateReception(String dateReception) {
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

	public String getIdReglement() {
		return idReglement;
	}

	public void setIdReglement(String idReglement) {
		this.idReglement = idReglement;
	}

	public String getTypeLettre() {
		return typeLettre;
	}

	public void setTypeLettre(String typeLettre) {
		this.typeLettre = typeLettre;
	}

	public void setNbrEdition(String nbrEdition) {
		this.nbrEdition = nbrEdition;
	}

	public String getNbrEdition() {
		return nbrEdition;
	}

	public String getRefProcedure() {
		return refProcedure;
	}

	public void setRefProcedure(String refProcedure) {
		this.refProcedure = refProcedure;
	}

	public String getSalaireMensuel() {
		return salaireMensuel;
	}

	public void setSalaireMensuel(String salaireMensuel) {
		this.salaireMensuel = salaireMensuel;
	}

	public String getRenteAnnuelle() {
		return renteAnnuelle;
	}

	public void setRenteAnnuelle(String renteAnnuelle) {
		this.renteAnnuelle = renteAnnuelle;
	}

	public String getAncienEtat() {
		return ancienEtat;
	}

	public void setAncienEtat(String ancienEtat) {
		this.ancienEtat = ancienEtat;
	}

	public String getDateProchaineEcheance() {
		return dateProchaineEcheance;
	}

	public void setDateProchaineEcheance(String dateProchaineEcheance) {
		this.dateProchaineEcheance = dateProchaineEcheance;
	}

	public String getEmissionITT() {
		return emissionITT;
	}

	public void setEmissionITT(String emissionITT) {
		this.emissionITT = emissionITT;
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

	public String getCreatorFirstQtc() {
		return creatorFirstQtc;
	}

	public void setCreatorFirstQtc(String creatorFirstQtc) {
		this.creatorFirstQtc = creatorFirstQtc;
	}

	public String getSmig() {
		return smig;
	}

	public void setSmig(String smig) {
		this.smig = smig;
	}

	public String getSalaireSmig() {
		return salaireSmig;
	}

	public void setSalaireSmig(String salaireSmig) {
		this.salaireSmig = salaireSmig;
	}

	public String getRappel() {
		return rappel;
	}

	public void setRappel(String rappel) {
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

	public List getListFraisMedicaux() {
		return listFraisMedicaux;
	}

	public void setListFraisMedicaux(List listFraisMedicaux) {
		this.listFraisMedicaux = listFraisMedicaux;
	}
	
	public List getListFraisFuneraire() {
		return listFraisFuneraire;
	}

	public void setListFraisFuneraire(List listFraisFuneraire) {
		this.listFraisFuneraire = listFraisFuneraire;
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

	public String getDateEditionIntermediaire() {
		return dateEditionIntermediaire;
	}

	public void setDateEditionIntermediaire(String dateEditionIntermediaire) {
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
	public String getActionPreQuitt() {
		return actionPreQuitt;
	}

	public void setActionPreQuitt(String actionPreQuitt) {
		this.actionPreQuitt = actionPreQuitt;
	}

	public String getIsComplement() {
		return isComplement;
	}

	public String getZoneARisque() {
		return zoneARisque;
	}

	public void setZoneARisque(String zoneARisque) {
		this.zoneARisque = zoneARisque;
	}

	public void setIsComplement(String isComplement) {
		this.isComplement = isComplement;
	}

	public String getRegler() {
		return regler;
	}

	public void setRegler(String regler) {
		this.regler = regler;
	}
	

}