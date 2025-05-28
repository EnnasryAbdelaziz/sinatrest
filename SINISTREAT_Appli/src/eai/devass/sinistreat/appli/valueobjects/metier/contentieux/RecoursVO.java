package eai.devass.sinistreat.appli.valueobjects.metier.contentieux;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;

import eai.devass.sinistreat.appli.valueobjects.metier.recours.RecoursAmiableVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.AssuranceVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.BanqueVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatRecoursVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PronosticRCVO;

/**
 * @author Mouhane
 * 
 */

public class RecoursVO implements IValueObject {
	
	private String id;
	private String idRecours;
	private String typeRecours;
	private SinistreVO refSinistre;
	private String dateDeclenchement;
	private AssuranceVO refCompagnie;
	private String userCreateur;
	private String codeTypeAccident;
	private String immatriculation;
	private String nomConducteur;
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
	private String dateEtablissement;
	private String dateTransmission;
	private String dateDemande;
	private String dateReception;
	private PronosticRCVO refPronosticRC;
	private String causeEtCircanstances;
	private String commentairePronostic;
	private String dateModification;
	private String montantProvision;
	/* MFBO@205 ajout numéro de recours */
	private String numeroRecours;
	private String typeProcedure;
	// Recuperation
	private String deboursLoiReclame;
	private String isRecuperationTotale;
	private String dateRecuperation;
	private String dateExecution;
	private String numRemise;
	private BanqueVO refBanque;
	private String mntDeBoursLoi;
	private String mntDeBoursLoiRestant;
	private String numeroCheque;
	private List<RecoursAmiableVO> listRecoursAmiable;
	/*MFBO@492 ajout de la référence compagnie adverse*/
	private String refCompagnieAdverse;
	/*MFBO@443 ajout référence compagnie*/
	private String referenceCompagnie;
	
	// Champs non mappés
	private EtatRecoursVO refEtatRecours;
	private String dateEtatRecours;
	private String dateCreation;

	private String numeroDossierInstruction;
	private String codeBranche;
	private String etatRecoursLast;
	private String montantFinal;
	private String typeProcedureInitial;
	private String codeCoassurance;
	private String datePrescription;

	private String dateSinistre;
	private String numeroPolice;
	private String assure;
	private String codeIntermediaire;
	private List listeProcedureJudiciaire;
	private String dateDebut;
	private String dateFin;

	public RecoursVO() {
		super();

	}

	public RecoursVO(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdRecours() {
		return idRecours;
	}

	public void setIdRecours(String idRecours) {
		this.idRecours = idRecours;
	}

	public String getTypeRecours() {
		return typeRecours;
	}

	public void setTypeRecours(String typeRecours) {
		this.typeRecours = typeRecours;
	}

	public SinistreVO getRefSinistre() {
		return refSinistre;
	}

	public void setRefSinistre(SinistreVO refSinistre) {
		this.refSinistre = refSinistre;
	}

	public String getDateDeclenchement() {
		return dateDeclenchement;
	}

	public void setDateDeclenchement(String dateDeclenchement) {
		this.dateDeclenchement = dateDeclenchement;
	}

	public AssuranceVO getRefCompagnie() {
		return refCompagnie;
	}

	public void setRefCompagnie(AssuranceVO refCompagnie) {
		this.refCompagnie = refCompagnie;
	}

	public String getUserCreateur() {
		return userCreateur;
	}

	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
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

	public String getAdresseResponsabiliteCivile() {
		return adresseResponsabiliteCivile;
	}

	public void setAdresseResponsabiliteCivile(
			String adresseResponsabiliteCivile) {
		this.adresseResponsabiliteCivile = adresseResponsabiliteCivile;
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

	public String getDateEtablissement() {
		return dateEtablissement;
	}

	public void setDateEtablissement(String dateEtablissement) {
		this.dateEtablissement = dateEtablissement;
	}

	public String getDateTransmission() {
		return dateTransmission;
	}

	public void setDateTransmission(String dateTransmission) {
		this.dateTransmission = dateTransmission;
	}

	public String getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getDateReception() {
		return dateReception;
	}

	public void setDateReception(String dateReception) {
		this.dateReception = dateReception;
	}

	public PronosticRCVO getRefPronosticRC() {
		return refPronosticRC;
	}

	public void setRefPronosticRC(PronosticRCVO refPronosticRC) {
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

	public String getDateModification() {
		return dateModification;
	}

	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}

	public String getDeboursLoiReclame() {
		return deboursLoiReclame;
	}

	public void setDeboursLoiReclame(String deboursLoiReclame) {
		this.deboursLoiReclame = deboursLoiReclame;
	}

	public String getIsRecuperationTotale() {
		return isRecuperationTotale;
	}

	public void setIsRecuperationTotale(String isRecuperationTotale) {
		this.isRecuperationTotale = isRecuperationTotale;
	}

	public String getDateRecuperation() {
		return dateRecuperation;
	}

	public void setDateRecuperation(String dateRecuperation) {
		this.dateRecuperation = dateRecuperation;
	}

	public String getDateExecution() {
		return dateExecution;
	}

	public void setDateExecution(String dateExecution) {
		this.dateExecution = dateExecution;
	}

	public String getNumRemise() {
		return numRemise;
	}

	public void setNumRemise(String numRemise) {
		this.numRemise = numRemise;
	}

	public BanqueVO getRefBanque() {
		return refBanque;
	}

	public void setRefBanque(BanqueVO refBanque) {
		this.refBanque = refBanque;
	}

	public String getMntDeBoursLoi() {
		return mntDeBoursLoi;
	}

	public void setMntDeBoursLoi(String mntDeBoursLoi) {
		this.mntDeBoursLoi = mntDeBoursLoi;
	}

	public String getMntDeBoursLoiRestant() {
		return mntDeBoursLoiRestant;
	}

	public void setMntDeBoursLoiRestant(String mntDeBoursLoiRestant) {
		this.mntDeBoursLoiRestant = mntDeBoursLoiRestant;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public List<RecoursAmiableVO> getListRecoursAmiable() {
		return listRecoursAmiable;
	}

	public void setListRecoursAmiable(List<RecoursAmiableVO> listRecoursAmiable) {
		this.listRecoursAmiable = listRecoursAmiable;
	}

	public EtatRecoursVO getRefEtatRecours() {
		return refEtatRecours;
	}

	public void setRefEtatRecours(EtatRecoursVO refEtatRecours) {
		this.refEtatRecours = refEtatRecours;
	}

	public String getDateEtatRecours() {
		return dateEtatRecours;
	}

	public void setDateEtatRecours(String dateEtatRecours) {
		this.dateEtatRecours = dateEtatRecours;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
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

	public String getMontantFinal() {
		return montantFinal;
	}

	public void setMontantFinal(String montantFinal) {
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

	public String getDatePrescription() {
		return datePrescription;
	}

	public void setDatePrescription(String datePrescription) {
		this.datePrescription = datePrescription;
	}

	public String getDateSinistre() {
		return dateSinistre;
	}

	public void setDateSinistre(String dateSinistre) {
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

	// Recherche recous
	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * 
	 * @return
	 */
	public String getDateFin() {
		return dateFin;
	}

	/**
	 * 
	 * @param dateFin
	 */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * 
	 * @return
	 */
	public String getMontantProvision() {
		return montantProvision;
	}

	/**
	 * 
	 * @param montantProvision
	 */
	public void setMontantProvision(String montantProvision) {
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

	public String getTypeProcedure() {
		return typeProcedure;
	}

	public void setTypeProcedure(String typeProcedure) {
	this.typeProcedure = typeProcedure;
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

}