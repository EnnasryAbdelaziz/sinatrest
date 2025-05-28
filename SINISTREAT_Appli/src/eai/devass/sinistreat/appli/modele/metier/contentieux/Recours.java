package eai.devass.sinistreat.appli.modele.metier.contentieux;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import eai.devass.sinistreat.appli.modele.metier.recours.RecoursAmiable;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.Assurance;
import eai.devass.sinistreat.appli.modele.parametrage.Banque;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRecours;
import eai.devass.sinistreat.appli.modele.parametrage.PronosticRC;

public class Recours implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String idRecours;
	private String typeRecours;
	private Sinistre refSinistre;
	private Date dateDeclenchement;
	private Assurance refCompagnie;
	private String userCreateur;
	private String codeTypeAccident;
	private String immatriculation;
	private String nomConducteur;
	private String typeProcedure;
	private String isCivilResponsable;
	private String nomResponsableCivile;
	private String adresseResponsabiliteCivile;
	private String numeroPV;
	private String autoriteVerbalisatrice;
	private String codeAvocatConseil;
	private String nomAvocatConseil;
	private String codeAvocatAdverse;
	private String nomAvocatAdverse;
	private String partage;
	private String numPoliceCompagnieAdverse;
	private String marque;
	private String responsableRC;
	private Date dateEtablissement;
	private Date dateTransmission;
	private Date dateDemande;
	private Date dateReception;
	private PronosticRC refPronosticRC;
	private String causeEtCircanstances;
	private String commentairePronostic;
	private Date dateModification;
	private Double montantProvision;
	/* MFBO@205 ajout numéro de recours */
	private String numeroRecours;
	/* MFBO@492 ajout de la référence compagnie adverse */
	private String refCompagnieAdverse;
	/* MFBO@443 ajout référence compagnie */
	private String referenceCompagnie;
	/* MFBO@443 ajout référence compagnie */
	// Recuperation
	/* MFBO@444 ajout Montant débours loi réclamé */
	private Double deboursLoiReclame;
	private String isRecuperationTotale;
	private Date dateRecuperation;
	private Date dateExecution;
	private String numRemise;
	private Banque refBanque;
	private float mntDeBoursLoi;
	private float mntDeBoursLoiRestant;
	private String numeroCheque;
	private List<RecoursAmiable> listRecoursAmiable;
	// Champs non mappés
	private EtatRecours refEtatRecours;
	private Date dateEtatRecours;
	private Date dateCreation;

	private String numeroSinistre;
	private String numeroDossierInstruction;
	private String codeBranche;
	private String etatRecoursLast;
	private Double montantFinal;
	private String typeProcedureInitial;
	private String codeCoassurance;
	private Date datePrescription;

	private Date dateSinistre;
	private String numeroPolice;
	private String assure;
	private String codeIntermediaire;

	private List listeProcedureJudiciaire;

	// POur la convertion (VO BO)
	private transient String[] propertiesToConvert;

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

	public Recours() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeRecours() {
		return typeRecours;
	}

	public void setTypeRecours(String typeRecours) {
		this.typeRecours = typeRecours;
	}

	public Date getDateDeclenchement() {
		return dateDeclenchement;
	}

	public void setDateDeclenchement(Date dateDeclenchement) {
		this.dateDeclenchement = dateDeclenchement;
	}

	public String getUserCreateur() {
		return userCreateur;
	}

	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getNomConducteur() {
		return nomConducteur;
	}

	public void setNomConducteur(String nomConducteur) {
		this.nomConducteur = nomConducteur;
	}

	public String getIsCivilResponsable() {
		return isCivilResponsable;
	}

	public void setIsCivilResponsable(String isCivilResponsable) {
		this.isCivilResponsable = isCivilResponsable;
	}

	public String getNomResponsableCivile() {
		return nomResponsableCivile;
	}

	public void setNomResponsableCivile(String nomResponsableCivile) {
		this.nomResponsableCivile = nomResponsableCivile;
	}

	public String getNumeroPV() {
		return numeroPV;
	}

	public void setNumeroPV(String numeroPV) {
		this.numeroPV = numeroPV;
	}

	public String getAutoriteVerbalisatrice() {
		return autoriteVerbalisatrice;
	}

	public void setAutoriteVerbalisatrice(String autoriteVerbalisatrice) {
		this.autoriteVerbalisatrice = autoriteVerbalisatrice;
	}

	public String getCodeAvocatConseil() {
		return codeAvocatConseil;
	}

	public void setCodeAvocatConseil(String codeAvocatConseil) {
		this.codeAvocatConseil = codeAvocatConseil;
	}

	public String getNomAvocatConseil() {
		return nomAvocatConseil;
	}

	public void setNomAvocatConseil(String nomAvocatConseil) {
		this.nomAvocatConseil = nomAvocatConseil;
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

	public String getPartage() {
		return partage;
	}

	public void setPartage(String partage) {
		this.partage = partage;
	}

	public float getMntDeBoursLoi() {
		return mntDeBoursLoi;
	}

	public void setMntDeBoursLoi(float mntDeBoursLoi) {
		this.mntDeBoursLoi = mntDeBoursLoi;
	}

	public float getMntDeBoursLoiRestant() {
		return mntDeBoursLoiRestant;
	}

	public void setMntDeBoursLoiRestant(float mntDeBoursLoiRestant) {
		this.mntDeBoursLoiRestant = mntDeBoursLoiRestant;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public EtatRecours getRefEtatRecours() {
		return refEtatRecours;
	}

	public void setRefEtatRecours(EtatRecours refEtatRecours) {
		this.refEtatRecours = refEtatRecours;
	}

	public void setListRecoursAmiable(List<RecoursAmiable> listRecoursAmiable) {
		this.listRecoursAmiable = listRecoursAmiable;
	}

	public List<RecoursAmiable> getListRecoursAmiable() {
		return listRecoursAmiable;
	}

	public String getAdresseResponsabiliteCivile() {
		return adresseResponsabiliteCivile;
	}

	public void setAdresseResponsabiliteCivile(
			String adresseResponsabiliteCivile) {
		this.adresseResponsabiliteCivile = adresseResponsabiliteCivile;
	}

	public Date getDateEtatRecours() {
		return dateEtatRecours;
	}

	public void setDateEtatRecours(Date dateEtatRecours) {
		this.dateEtatRecours = dateEtatRecours;
	}

	public String getNumPoliceCompagnieAdverse() {
		return numPoliceCompagnieAdverse;
	}

	public void setNumPoliceCompagnieAdverse(String numPoliceCompagnieAdverse) {
		this.numPoliceCompagnieAdverse = numPoliceCompagnieAdverse;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getResponsableRC() {
		return responsableRC;
	}

	public void setResponsableRC(String responsableRC) {
		this.responsableRC = responsableRC;
	}

	public Date getDateEtablissement() {
		return dateEtablissement;
	}

	public void setDateEtablissement(Date dateEtablissement) {
		this.dateEtablissement = dateEtablissement;
	}

	public Date getDateTransmission() {
		return dateTransmission;
	}

	public void setDateTransmission(Date dateTransmission) {
		this.dateTransmission = dateTransmission;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Date getDateReception() {
		return dateReception;
	}

	public void setDateReception(Date dateReception) {
		this.dateReception = dateReception;
	}

	public Assurance getRefCompagnie() {
		return refCompagnie;
	}

	public void setRefCompagnie(Assurance refCompagnie) {
		this.refCompagnie = refCompagnie;
	}

	public String getCodeTypeAccident() {
		if (StringUtils.isEmpty(this.codeTypeAccident)) {
			return "";
		}
		return codeTypeAccident;
	}

	public void setCodeTypeAccident(String codeTypeAccident) {
		if (StringUtils.isEmpty(codeTypeAccident)) {
			codeTypeAccident = "";
		}
		this.codeTypeAccident = codeTypeAccident;
	}

	public PronosticRC getRefPronosticRC() {
		return refPronosticRC;
	}

	public void setRefPronosticRC(PronosticRC refPronosticRC) {
		this.refPronosticRC = refPronosticRC;
	}

	public String getCauseEtCircanstances() {
		return causeEtCircanstances;
	}

	public void setCauseEtCircanstances(String causeEtCircanstances) {
		this.causeEtCircanstances = causeEtCircanstances;
	}

	public String getCommentairePronostic() {
		return commentairePronostic;
	}

	public void setCommentairePronostic(String commentairePronostic) {
		this.commentairePronostic = commentairePronostic;
	}

	public void setRefSinistre(Sinistre refSinistre) {
		this.refSinistre = refSinistre;
	}

	public Sinistre getRefSinistre() {
		return refSinistre;
	}

	public Date getDateRecuperation() {
		return dateRecuperation;
	}

	public void setDateRecuperation(Date dateRecuperation) {
		this.dateRecuperation = dateRecuperation;
	}

	public Date getDateExecution() {
		return dateExecution;
	}

	public void setDateExecution(Date dateExecution) {
		this.dateExecution = dateExecution;
	}

	public String getNumRemise() {
		return numRemise;
	}

	public void setNumRemise(String numRemise) {
		this.numRemise = numRemise;
	}

	public Banque getRefBanque() {
		return refBanque;
	}

	public void setRefBanque(Banque refBanque) {
		this.refBanque = refBanque;
	}

	public void setIsRecuperationTotale(String isRecuperationTotale) {
		this.isRecuperationTotale = isRecuperationTotale;
	}

	public String getIsRecuperationTotale() {
		return isRecuperationTotale;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getTypeProcedure() {
		return typeProcedure;
	}

	public void setTypeProcedure(String typeProcedure) {
		this.typeProcedure = typeProcedure;
	}

	public String getNumeroSinistre() {
		return numeroSinistre;
	}

	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}

	public String getNumeroDossierInstruction() {
		return numeroDossierInstruction;
	}

	public void setNumeroDossierInstruction(String numeroDossierInstruction) {
		this.numeroDossierInstruction = numeroDossierInstruction;
	}

	public String getCodeBranche() {
		return codeBranche;
	}

	public void setCodeBranche(String codeBranche) {
		this.codeBranche = codeBranche;
	}

	public String getEtatRecoursLast() {
		return etatRecoursLast;
	}

	public void setEtatRecoursLast(String etatRecoursLast) {
		this.etatRecoursLast = etatRecoursLast;
	}

	public Double getMontantFinal() {
		return montantFinal;
	}

	public void setMontantFinal(Double montantFinal) {
		this.montantFinal = montantFinal;
	}

	public String getTypeProcedureInitial() {
		return typeProcedureInitial;
	}

	public void setTypeProcedureInitial(String typeProcedureInitial) {
		this.typeProcedureInitial = typeProcedureInitial;
	}

	public String getCodeCoassurance() {
		return codeCoassurance;
	}

	public void setCodeCoassurance(String codeCoassurance) {
		this.codeCoassurance = codeCoassurance;
	}

	public Date getDatePrescription() {
		return datePrescription;
	}

	public void setDatePrescription(Date datePrescription) {
		this.datePrescription = datePrescription;
	}

	public Date getDateSinistre() {
		return dateSinistre;
	}

	public void setDateSinistre(Date dateSinistre) {
		this.dateSinistre = dateSinistre;
	}

	public String getNumeroPolice() {
		return numeroPolice;
	}

	public void setNumeroPolice(String numeroPolice) {
		this.numeroPolice = numeroPolice;
	}

	public String getAssure() {
		return assure;
	}

	public void setAssure(String assure) {
		this.assure = assure;
	}

	public String getCodeIntermediaire() {
		return codeIntermediaire;
	}

	public void setCodeIntermediaire(String codeIntermediaire) {
		this.codeIntermediaire = codeIntermediaire;
	}

	public List getListeProcedureJudiciaire() {
		return listeProcedureJudiciaire;
	}

	public void setListeProcedureJudiciaire(List listeProcedureJudiciaire) {
		this.listeProcedureJudiciaire = listeProcedureJudiciaire;

	}

	public String getIdRecours() {
		return idRecours;
	}

	public void setIdRecours(String idRecours) {
		this.idRecours = idRecours;
	}

	/**
	 * get montantProvision
	 * 
	 * @return
	 */
	public Double getMontantProvision() {
		return montantProvision;
	}

	/**
	 * set montantProvision
	 * 
	 * @param montantProvision
	 */
	public void setMontantProvision(Double montantProvision) {
		this.montantProvision = montantProvision;
	}

	/**
	 * @return the numeroRecours
	 */
	public String getNumeroRecours() {
		return numeroRecours;
	}

	/**
	 * @param numeroRecours
	 *            the numeroRecours to set
	 */
	public void setNumeroRecours(String numeroRecours) {
		this.numeroRecours = numeroRecours;
	}

	/**
	 * @return the refCompagnieAdverse
	 */
	public String getRefCompagnieAdverse() {
		return refCompagnieAdverse;
	}

	/**
	 * @param refCompagnieAdverse
	 *            the refCompagnieAdverse to set
	 */
	public void setRefCompagnieAdverse(String refCompagnieAdverse) {
		this.refCompagnieAdverse = refCompagnieAdverse;
	}

	/**
	 * @return the referenceCompagnie
	 */
	public String getReferenceCompagnie() {
		return referenceCompagnie;
	}

	/**
	 * @param referenceCompagnie
	 *            the referenceCompagnie to set
	 */
	public void setReferenceCompagnie(String referenceCompagnie) {
		this.referenceCompagnie = referenceCompagnie;
	}

	/**
	 * @return the deboursLoiReclame
	 */
	public Double getDeboursLoiReclame() {
		return deboursLoiReclame;
	}

	/**
	 * @param deboursLoiReclame
	 *            the deboursLoiReclame to set
	 */
	public void setDeboursLoiReclame(Double deboursLoiReclame) {
		this.deboursLoiReclame = deboursLoiReclame;
	}

}
