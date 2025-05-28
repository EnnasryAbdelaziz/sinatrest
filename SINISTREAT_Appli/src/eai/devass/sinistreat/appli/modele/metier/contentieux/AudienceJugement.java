package eai.devass.sinistreat.appli.modele.metier.contentieux;

import java.io.Serializable;
import java.util.Date;

import eai.devass.sinistreat.appli.modele.parametrage.Banque;
import eai.devass.sinistreat.appli.modele.parametrage.CieCondamnee;
import eai.devass.sinistreat.appli.modele.parametrage.Decision;
import eai.devass.sinistreat.appli.modele.parametrage.GestionnaireTraitement;
import eai.devass.sinistreat.appli.modele.parametrage.NatureAudience;
import eai.devass.sinistreat.appli.modele.parametrage.PronosticRC;
import eai.devass.sinistreat.appli.modele.parametrage.SortJugement;
import eai.devass.sinistreat.appli.modele.parametrage.SuiteJugement;
import eai.devass.sinistreat.appli.modele.parametrage.TiersSaisi;
import eai.devass.sinistreat.appli.modele.parametrage.TypeRente;

/** @pdOid 6fd3d2a1-3df5-43d9-841a-2d6f774d30e5 */
public class AudienceJugement implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;

	private transient String suiteMode;

	public String getSuiteMode() {
		return suiteMode;
	}

	public void setSuiteMode(String suiteMode) {
		this.suiteMode = suiteMode;
	}

	public String getEtatEnCoursAUdience() {
		return etatEnCoursAUdience;
	}

	public void setEtatEnCoursAUdience(String etatEnCoursAUdience) {
		this.etatEnCoursAUdience = etatEnCoursAUdience;
	}

	private ProcedureJudiciaire refProcedureJudiciaire;
	private String etatEnCoursAUdience;
	private String typeConvocation;
	private Date dateNotification;
	private Date dateConvocation;
	private Date heureConvocation;
	private String numeroSalleAudience;
	private String adresse;
	private String ville;
	private Date dateCreation;
	private Date dateModification;
	private String userCreateur;
	private String userModificateur;
	private Date dateCreationDebut;
	private Date dateCreationFin;
	private String etat;
	private String motifModification;
	private String nomJuge;
	private String motifConvocation;
	private NatureAudience refNatureAudience;
	private String refTribunal;
	private Long idAudience;
	// Jugement
	private Long idJugement;
	private String typeJugement;
	private Date dateReceptionNotification;
	private String resultatEtudeJugement;
	private String codePartieAdverse;
	private String codeExpertJudiciaire;
	private String resumeCR;
	private String evaluationCR;
	private String receptionCR;
	private String typeCompteRendu;
	private Date dateReceptionCR;
	private String motifRejetCR;
	private String executionProvisoire;
	private String pourcentageExecution;
	private Date dateJugement;
	private String arretConfirmatif;
	private Double montantIndemnisation;
	private String decisionCompagnie;
	private Double motifDecisionCompagnie;
	private String resumeJugementFond;
	private Double tauxResponsabiliteAssureCie;
	private String typeJugementADD;
	private String typeJugementCS;
	private Date dateCreationJugement;
	private Date dateCreationDebutJugement;
	private Date dateCreationFinJugement;
	private String etatJugement;
	private String motifAnnulationJugement;
	private String nomJugeJugement;

	private Date dateNotificationJugement;
	private String numeroJugement;
	private Date dateDecision;
	private String resultatJugement;
	private Double montantJuge;
	private PronosticRC partResponsabilite;
	private SuiteJugement refSuiteJugement;
	private Decision refDecision;
	private Double salaireJugement;
	private Double ippJugement;
	private Date dateDepartRente;
	// /
	private Double reserveGrave;
	private Double reserveOrdinaire;
	private Double reserveProthese;
	private Long idSinistre;
	private SortJugement refSortJugement;
	private TiersSaisi refTiersSaisi;
	private Double montant;
	private Banque refBanque;
	private String codeBancaire;
	private String codeIntermediaireTS;
	private String nomIntermediaireTS;
	private TypeRente refTypeRente;
	private CieCondamnee refCieCondamnee;
	private GestionnaireTraitement refGestionnaireTraitement;
	private String montantRente;
	private String quotePart;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProcedureJudiciaire getRefProcedureJudiciaire() {
		return refProcedureJudiciaire;
	}

	public void setRefProcedureJudiciaire(
			ProcedureJudiciaire refProcedureJudiciaire) {
		this.refProcedureJudiciaire = refProcedureJudiciaire;
	}

	public String getTypeConvocation() {
		return typeConvocation;
	}

	public void setTypeConvocation(String typeConvocation) {
		this.typeConvocation = typeConvocation;
	}

	public Date getDateNotification() {
		return dateNotification;
	}

	public void setDateNotification(Date dateNotification) {
		this.dateNotification = dateNotification;
	}

	public Date getDateConvocation() {
		return dateConvocation;
	}

	public void setDateConvocation(Date dateConvocation) {
		this.dateConvocation = dateConvocation;
	}

	public Date getHeureConvocation() {
		return heureConvocation;
	}

	public void setHeureConvocation(Date heureConvocation) {
		this.heureConvocation = heureConvocation;
	}

	public String getNumeroSalleAudience() {
		return numeroSalleAudience;
	}

	public void setNumeroSalleAudience(String numeroSalleAudience) {
		this.numeroSalleAudience = numeroSalleAudience;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
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

	public Date getDateCreationDebut() {
		return dateCreationDebut;
	}

	public void setDateCreationDebut(Date dateCreationDebut) {
		this.dateCreationDebut = dateCreationDebut;
	}

	public Date getDateCreationFin() {
		return dateCreationFin;
	}

	public void setDateCreationFin(Date dateCreationFin) {
		this.dateCreationFin = dateCreationFin;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getNomJuge() {
		return nomJuge;
	}

	public void setNomJuge(String nomJuge) {
		this.nomJuge = nomJuge;
	}

	public String getMotifConvocation() {
		return motifConvocation;
	}

	public void setMotifConvocation(String motifConvocation) {
		this.motifConvocation = motifConvocation;
	}

	public String getRefTribunal() {
		return refTribunal;
	}

	public void setRefTribunal(String refTribunal) {
		this.refTribunal = refTribunal;
	}

	public String getTypeJugement() {
		return typeJugement;
	}

	public void setTypeJugement(String typeJugement) {
		this.typeJugement = typeJugement;
	}

	public Date getDateReceptionNotification() {
		return dateReceptionNotification;
	}

	public void setDateReceptionNotification(Date dateReceptionNotification) {
		this.dateReceptionNotification = dateReceptionNotification;
	}

	public String getResultatEtudeJugement() {
		return resultatEtudeJugement;
	}

	public void setResultatEtudeJugement(String resultatEtudeJugement) {
		this.resultatEtudeJugement = resultatEtudeJugement;
	}

	public String getCodePartieAdverse() {
		return codePartieAdverse;
	}

	public void setCodePartieAdverse(String codePartieAdverse) {
		this.codePartieAdverse = codePartieAdverse;
	}

	public String getCodeExpertJudiciaire() {
		return codeExpertJudiciaire;
	}

	public void setCodeExpertJudiciaire(String codeExpertJudiciaire) {
		this.codeExpertJudiciaire = codeExpertJudiciaire;
	}

	public String getResumeCR() {
		return resumeCR;
	}

	public void setResumeCR(String resumeCR) {
		this.resumeCR = resumeCR;
	}

	public String getEvaluationCR() {
		return evaluationCR;
	}

	public void setEvaluationCR(String evaluationCR) {
		this.evaluationCR = evaluationCR;
	}

	public String getReceptionCR() {
		return receptionCR;
	}

	public void setReceptionCR(String receptionCR) {
		this.receptionCR = receptionCR;
	}

	public String getTypeCompteRendu() {
		return typeCompteRendu;
	}

	public void setTypeCompteRendu(String typeCompteRendu) {
		this.typeCompteRendu = typeCompteRendu;
	}

	public Date getDateReceptionCR() {
		return dateReceptionCR;
	}

	public void setDateReceptionCR(Date dateReceptionCR) {
		this.dateReceptionCR = dateReceptionCR;
	}

	public String getMotifRejetCR() {
		return motifRejetCR;
	}

	public void setMotifRejetCR(String motifRejetCR) {
		this.motifRejetCR = motifRejetCR;
	}

	public String getExecutionProvisoire() {
		return executionProvisoire;
	}

	public void setExecutionProvisoire(String executionProvisoire) {
		this.executionProvisoire = executionProvisoire;
	}

	public String getPourcentageExecution() {
		return pourcentageExecution;
	}

	public void setPourcentageExecution(String pourcentageExecution) {
		this.pourcentageExecution = pourcentageExecution;
	}

	public Date getDateJugement() {
		return dateJugement;
	}

	public void setDateJugement(Date dateJugement) {
		this.dateJugement = dateJugement;
	}

	public String getArretConfirmatif() {
		return arretConfirmatif;
	}

	public void setArretConfirmatif(String arretConfirmatif) {
		this.arretConfirmatif = arretConfirmatif;
	}

	public Double getMontantIndemnisation() {
		return montantIndemnisation;
	}

	public void setMontantIndemnisation(Double montantIndemnisation) {
		this.montantIndemnisation = montantIndemnisation;
	}

	public String getDecisionCompagnie() {
		return decisionCompagnie;
	}

	public void setDecisionCompagnie(String decisionCompagnie) {
		this.decisionCompagnie = decisionCompagnie;
	}

	public Double getMotifDecisionCompagnie() {
		return motifDecisionCompagnie;
	}

	public void setMotifDecisionCompagnie(Double motifDecisionCompagnie) {
		this.motifDecisionCompagnie = motifDecisionCompagnie;
	}

	public String getResumeJugementFond() {
		return resumeJugementFond;
	}

	public void setResumeJugementFond(String resumeJugementFond) {
		this.resumeJugementFond = resumeJugementFond;
	}

	public Double getTauxResponsabiliteAssureCie() {
		return tauxResponsabiliteAssureCie;
	}

	public void setTauxResponsabiliteAssureCie(
			Double tauxResponsabiliteAssureCie) {
		this.tauxResponsabiliteAssureCie = tauxResponsabiliteAssureCie;
	}

	public String getTypeJugementADD() {
		return typeJugementADD;
	}

	public void setTypeJugementADD(String typeJugementADD) {
		this.typeJugementADD = typeJugementADD;
	}

	public String getTypeJugementCS() {
		return typeJugementCS;
	}

	public void setTypeJugementCS(String typeJugementCS) {
		this.typeJugementCS = typeJugementCS;
	}

	public Date getDateCreationJugement() {
		return dateCreationJugement;
	}

	public void setDateCreationJugement(Date dateCreationJugement) {
		this.dateCreationJugement = dateCreationJugement;
	}

	public Date getDateCreationDebutJugement() {
		return dateCreationDebutJugement;
	}

	public void setDateCreationDebutJugement(Date dateCreationDebutJugement) {
		this.dateCreationDebutJugement = dateCreationDebutJugement;
	}

	public Date getDateCreationFinJugement() {
		return dateCreationFinJugement;
	}

	public void setDateCreationFinJugement(Date dateCreationFinJugement) {
		this.dateCreationFinJugement = dateCreationFinJugement;
	}

	public String getEtatJugement() {
		return etatJugement;
	}

	public void setEtatJugement(String etatJugement) {
		this.etatJugement = etatJugement;
	}

	public String getMotifAnnulationJugement() {
		return motifAnnulationJugement;
	}

	public void setMotifAnnulationJugement(String motifAnnulationJugement) {
		this.motifAnnulationJugement = motifAnnulationJugement;
	}

	public String getNomJugeJugement() {
		return nomJugeJugement;
	}

	public void setNomJugeJugement(String nomJugeJugement) {
		this.nomJugeJugement = nomJugeJugement;
	}

	public Date getDateNotificationJugement() {
		return dateNotificationJugement;
	}

	public void setDateNotificationJugement(Date dateNotificationJugement) {
		this.dateNotificationJugement = dateNotificationJugement;
	}

	public String getNumeroJugement() {
		return numeroJugement;
	}

	public void setNumeroJugement(String numeroJugement) {
		this.numeroJugement = numeroJugement;
	}

	public Date getDateDecision() {
		return dateDecision;
	}

	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	public String getResultatJugement() {
		return resultatJugement;
	}

	public void setResultatJugement(String resultatJugement) {
		this.resultatJugement = resultatJugement;
	}

	public Double getMontantJuge() {
		return montantJuge;
	}

	public void setMontantJuge(Double montantJuge) {
		this.montantJuge = montantJuge;
	}

	public PronosticRC getPartResponsabilite() {
		return partResponsabilite;
	}

	public void setPartResponsabilite(PronosticRC partResponsabilite) {
		this.partResponsabilite = partResponsabilite;
	}

	public Double getSalaireJugement() {
		return salaireJugement;
	}

	public void setSalaireJugement(Double salaireJugement) {
		this.salaireJugement = salaireJugement;
	}

	public Double getIppJugement() {
		return ippJugement;
	}

	public void setIppJugement(Double ippJugement) {
		this.ippJugement = ippJugement;
	}

	public Date getDateDepartRente() {
		return dateDepartRente;
	}

	public void setDateDepartRente(Date dateDepartRente) {
		this.dateDepartRente = dateDepartRente;
	}

	public Double getReserveGrave() {
		return reserveGrave;
	}

	public void setReserveGrave(Double reserveGrave) {
		this.reserveGrave = reserveGrave;
	}

	public Double getReserveOrdinaire() {
		return reserveOrdinaire;
	}

	public void setReserveOrdinaire(Double reserveOrdinaire) {
		this.reserveOrdinaire = reserveOrdinaire;
	}

	public Double getReserveProthese() {
		return reserveProthese;
	}

	public void setReserveProthese(Double reserveProthese) {
		this.reserveProthese = reserveProthese;
	}

	public Long getIdSinistre() {
		return idSinistre;
	}

	public void setIdSinistre(Long idSinistre) {
		this.idSinistre = idSinistre;
	}

	public NatureAudience getRefNatureAudience() {
		return refNatureAudience;
	}

	public void setRefNatureAudience(NatureAudience refNatureAudience) {
		this.refNatureAudience = refNatureAudience;
	}

	public SuiteJugement getRefSuiteJugement() {
		return refSuiteJugement;
	}

	public void setRefSuiteJugement(SuiteJugement refSuiteJugement) {
		this.refSuiteJugement = refSuiteJugement;
	}

	public String getUserCreateur() {
		return userCreateur;
	}

	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}

	public String getUserModificateur() {
		return userModificateur;
	}

	public void setUserModificateur(String userModificateur) {
		this.userModificateur = userModificateur;
	}

	public String getMotifModification() {
		return motifModification;
	}

	public void setMotifModification(String motifModification) {
		this.motifModification = motifModification;
	}

	public Decision getRefDecision() {
		return refDecision;
	}

	public void setRefDecision(Decision refDecision) {
		this.refDecision = refDecision;
	}

	public void setIdAudience(Long idAudience) {
		this.idAudience = idAudience;
	}

	public Long getIdAudience() {
		return idAudience;
	}

	public void setIdJugement(Long idJugement) {
		this.idJugement = idJugement;
	}

	public Long getIdJugement() {
		return idJugement;
	}

	public SortJugement getRefSortJugement() {
		return refSortJugement;
	}

	public void setRefSortJugement(SortJugement refSortJugement) {
		this.refSortJugement = refSortJugement;
	}

	public TiersSaisi getRefTiersSaisi() {
		return refTiersSaisi;
	}

	public void setRefTiersSaisi(TiersSaisi refTiersSaisi) {
		this.refTiersSaisi = refTiersSaisi;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Banque getRefBanque() {
		return refBanque;
	}

	public void setRefBanque(Banque refBanque) {
		this.refBanque = refBanque;
	}

	public String getCodeBancaire() {
		return codeBancaire;
	}

	public void setCodeBancaire(String codeBancaire) {
		this.codeBancaire = codeBancaire;
	}

	public String getCodeIntermediaireTS() {
		return codeIntermediaireTS;
	}

	public void setCodeIntermediaireTS(String codeIntermediaireTS) {
		this.codeIntermediaireTS = codeIntermediaireTS;
	}

	public String getNomIntermediaireTS() {
		return nomIntermediaireTS;
	}

	public void setNomIntermediaireTS(String nomIntermediaireTS) {
		this.nomIntermediaireTS = nomIntermediaireTS;
	}

	public TypeRente getRefTypeRente() {
		return refTypeRente;
	}

	public void setRefTypeRente(TypeRente refTypeRente) {
		this.refTypeRente = refTypeRente;
	}

	public CieCondamnee getRefCieCondamnee() {
		return refCieCondamnee;
	}

	public void setRefCieCondamnee(CieCondamnee refCieCondamnee) {
		this.refCieCondamnee = refCieCondamnee;
	}

	public GestionnaireTraitement getRefGestionnaireTraitement() {
		return refGestionnaireTraitement;
	}

	public void setRefGestionnaireTraitement(
			GestionnaireTraitement refGestionnaireTraitement) {
		this.refGestionnaireTraitement = refGestionnaireTraitement;
	}

	public String getMontantRente() {
		return montantRente;
	}

	public void setMontantRente(String montantRente) {
		this.montantRente = montantRente;
	}

	public String getQuotePart() {
		return quotePart;
	}

	public void setQuotePart(String quotePart) {
		this.quotePart = quotePart;
	}

}