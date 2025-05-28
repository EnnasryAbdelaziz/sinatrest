package eai.devass.sinistreat.appli.valueobjects.metier.contentieux;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.BanqueVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CieCondamneeVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.DecisionVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.GestionnaireTraitementVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.NatureAudienceVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PronosticRCVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.SortJugementVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.SuiteJugementVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TiersSaisiVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeRenteVO;


/** @pdOid 6fd3d2a1-3df5-43d9-841a-2d6f774d30e5 */
public class AudienceJugementVO implements IValueObject {

	private String id;
	private String etatEnCoursAUdience;
	private  String suiteMode;
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
	private ProcedureJudiciaireVO refProcedureJudiciaire;
	private String typeConvocation;
	private String dateNotification;
	private String dateConvocation;
	private String heureConvocation;
	private String numeroSalleAudience;
	private String adresse;
	private String ville;	
	private String dateCreation;
	private String dateModification;
	private String userCreateur;
	private String userModificateur;
	private String dateCreationDebut ;
	private String dateCreationFin ;
	private String etat;
	private String motifAnnulation;
	private String nomJuge;
	private String motifModification;
	private NatureAudienceVO refNatureAudience;
	private String tribunal;
	private String idAudience;
	private String motifConvocation;
	//Jugement
	private String idJugement;
	private String typeJugement;
	private String dateReceptionNotification;
	private String resultatEtudeJugement;
	private String codePartieAdverse;
	private String codeExpertJudiciaire;
	private String resumeCR;
	private String evaluationCR;
	private String receptionCR;
	private String typeCompteRendu;
	private String dateReceptionCR;
	private String motifRejetCR;
	private String executionProvisoire;
	private String pourcentageExecution;
	private String dateJugement;
	private String arretConfirmatif;
	private String montantIndemnisation;
	private String decisionCompagnie;
	private String motifDecisionCompagnie;
	private String resumeJugementFond;
	private String tauxResponsabiliteAssureCie;
	private String typeJugementADD;
	private String typeJugementCS;
	private String dateCreationJugement;
	private String dateCreationDebutJugement;
	private String dateCreationFinJugement;
	private String etatJugement;
	private String motifAnnulationJugement;
	private String nomJugeJugement;
	
	private String dateNotificationJugement;
	private String numeroJugement;
	private String dateDecision;
	private String resultatJugement;
	private String montantJuge;
	private PronosticRCVO partResponsabilite;
	private SuiteJugementVO refSuiteJugement;
	private DecisionVO refDecision;
	private String salaireJugement;
	private String ippJugement;
	private String dateDepartRente;
	///
	private String reserveGrave;
	private String reserveOrdinaire;
	private String reserveProthese;
	private String idSinistre;
	private SortJugementVO refSortJugement;
	private TiersSaisiVO refTiersSaisi;
	private String montant;
	private BanqueVO refBanque;
	private String codeBancaire;
	private String codeIntermediaireTS;
	private String nomIntermediaireTS;
	private TypeRenteVO refTypeRente;
	private CieCondamneeVO refCieCondamnee;
	private GestionnaireTraitementVO refGestionnaireTraitement;
	private String montantRente;
	private String quotePart;
	
	public String getIdSinistre() {
		return idSinistre;
	}
	public void setIdSinistre(String idSinistre) {
		this.idSinistre = idSinistre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	
	public String getTypeConvocation() {
		return typeConvocation;
	}
	public void setTypeConvocation(String typeConvocation) {
		this.typeConvocation = typeConvocation;
	}
	public String getDateConvocation() {

		return dateConvocation;
	}
	public void setDateConvocation(String dateConvocation) {
		this.dateConvocation = dateConvocation;
	}
	public String getHeureConvocation(){
		return heureConvocation;
	}
	public void setHeureConvocation(String heureConvocation) {
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
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getDateModification() {
		return dateModification;
	}
	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}
	public String getDateCreationDebut() {
		return dateCreationDebut;
	}
	public void setDateCreationDebut(String dateCreationDebut) {
		this.dateCreationDebut = dateCreationDebut;
	}
	public String getDateCreationFin() {
		return dateCreationFin;
	}
	public void setDateCreationFin(String dateCreationFin) {
		this.dateCreationFin = dateCreationFin;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getMotifAnnulation() {
		return motifAnnulation;
	}
	public void setMotifAnnulation(String motifAnnulation) {
		this.motifAnnulation = motifAnnulation;
	}
	//Jugement
	public String getIdJugement() {
		return idJugement;
	}
	public void setIdJugement(String idJugement) {
		this.idJugement = idJugement;
	}
	public String getTypeJugement() {
		return typeJugement;
	}
	public void setTypeJugement(String typeJugement) {
		this.typeJugement = typeJugement;
	}
	public String getDateReceptionNotification() {
		return dateReceptionNotification;
	}
	public void setDateReceptionNotification(String dateReceptionNotification) {
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
	public String getDateReceptionCR() {
		return dateReceptionCR;
	}
	public void setDateReceptionCR(String dateReceptionCR) {
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
	public String getDateJugement() {
		return dateJugement;
	}
	public void setDateJugement(String dateJugement) {
		this.dateJugement = dateJugement;
	}
	public String getArretConfirmatif() {
		return arretConfirmatif;
	}
	public void setArretConfirmatif(String arretConfirmatif) {
		this.arretConfirmatif = arretConfirmatif;
	}
	public String getMontantIndemnisation() {
		return montantIndemnisation;
	}
	public void setMontantIndemnisation(String montantIndemnisation) {
		this.montantIndemnisation = montantIndemnisation;
	}
	public String getDecisionCompagnie() {
		return decisionCompagnie;
	}
	public void setDecisionCompagnie(String decisionCompagnie) {
		this.decisionCompagnie = decisionCompagnie;
	}
	public String getMotifDecisionCompagnie() {
		return motifDecisionCompagnie;
	}
	public void setMotifDecisionCompagnie(String motifDecisionCompagnie) {
		this.motifDecisionCompagnie = motifDecisionCompagnie;
	}
	public String getResumeJugementFond() {
		return resumeJugementFond;
	}
	public void setResumeJugementFond(String resumeJugementFond) {
		this.resumeJugementFond = resumeJugementFond;
	}
	public String getTauxResponsabiliteAssureCie() {
		return tauxResponsabiliteAssureCie;
	}
	public void setTauxResponsabiliteAssureCie(String tauxResponsabiliteAssureCie) {
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
	public String getDateCreationJugement() {
		return dateCreationJugement;
	}
	public void setDateCreationJugement(String dateCreationJugement) {
		this.dateCreationJugement = dateCreationJugement;
	}
	public String getDateCreationDebutJugement() {
		return dateCreationDebutJugement;
	}
	public void setDateCreationDebutJugement(String dateCreationDebutJugement) {
		this.dateCreationDebutJugement = dateCreationDebutJugement;
	}
	public String getDateCreationFinJugement() {
		return dateCreationFinJugement;
	}
	public void setDateCreationFinJugement(String dateCreationFinJugement) {
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
	public String getNomJuge() {
		return nomJuge;
	}
	public void setNomJuge(String nomJuge) {
		this.nomJuge = nomJuge;
	}
	public String getNumeroJugement() {
		return numeroJugement;
	}
	public void setNumeroJugement(String numeroJugement) {
		this.numeroJugement = numeroJugement;
	}
	public String getDateNotification() {
		return dateNotification;
	}
	public void setDateNotification(String dateNotification) {
		this.dateNotification = dateNotification;
	}
	
	public String getTribunal() {
		return tribunal;
	}
	public void setTribunal(String tribunal) {
		this.tribunal = tribunal;
	}

	public String getNomJugeJugement() {
		return nomJugeJugement;
	}
	public void setNomJugeJugement(String nomJugeJugement) {
		this.nomJugeJugement = nomJugeJugement;
	}

	public SuiteJugementVO getRefSuiteJugement() {
		return refSuiteJugement;
	}
	public void setRefSuiteJugement(SuiteJugementVO refSuiteJugement) {
		this.refSuiteJugement = refSuiteJugement;
	}

	public DecisionVO getRefDecision() {
		return refDecision;
	}
	public void setRefDecision(DecisionVO refDecision) {
		this.refDecision = refDecision;
	}
	public void setPartResponsabilite(PronosticRCVO partResponsabilite) {
		this.partResponsabilite = partResponsabilite;
	}
	public PronosticRCVO getPartResponsabilite() {
		return partResponsabilite;
	}
	public String getDateDecision() {
		return dateDecision;
	}
	public void setDateDecision(String dateDecision) {
		this.dateDecision = dateDecision;
	}
	public String getResultatJugement() {
		return resultatJugement;
	}
	public void setResultatJugement(String resultatJugement) {
		this.resultatJugement = resultatJugement;
	}
	public String getMontantJuge() {
		return montantJuge;
	}
	public void setMontantJuge(String montantJuge) {
		this.montantJuge = montantJuge;
	}
	public String getDateNotificationJugement() {
		return dateNotificationJugement;
	}
	public void setDateNotificationJugement(String dateNotificationJugement) {
		this.dateNotificationJugement = dateNotificationJugement;
	}
	public void setReserveProthese(String reserveProthese) {
		this.reserveProthese = reserveProthese;
	}
	public String getReserveProthese() {
		return reserveProthese;
	}
	public void setReserveOrdinaire(String reserveOrdinaire) {
		this.reserveOrdinaire = reserveOrdinaire;
	}
	public String getReserveOrdinaire() {
		return reserveOrdinaire;
	}
	public void setReserveGrave(String reserveGrave) {
		this.reserveGrave = reserveGrave;
	}
	public String getReserveGrave() {
		return reserveGrave;
	}
	public void setSalaireJugement(String salaireJugement) {
		this.salaireJugement = salaireJugement;
	}
	public String getSalaireJugement() {
		return salaireJugement;
	}
	public void setIppJugement(String ippJugement) {
		this.ippJugement = ippJugement;
	}
	public String getIppJugement() {
		return ippJugement;
	}
	public void setDateDepartRente(String dateDepartRente) {
		this.dateDepartRente = dateDepartRente;
	}
	public String getDateDepartRente() {
		return dateDepartRente;
	}
	public ProcedureJudiciaireVO getRefProcedureJudiciaire() {
		return refProcedureJudiciaire;
	}
	public void setRefProcedureJudiciaire(
			ProcedureJudiciaireVO refProcedureJudiciaire) {
		this.refProcedureJudiciaire = refProcedureJudiciaire;
	}
	public NatureAudienceVO getRefNatureAudience() {
		return refNatureAudience;
	}
	public void setRefNatureAudience(NatureAudienceVO refNatureAudience) {
		this.refNatureAudience = refNatureAudience;
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
	public void setIdAudience(String idAudience) {
		this.idAudience = idAudience;
	}
	public String getIdAudience() {
		return idAudience;
	}
	public void setMotifConvocation(String motifConvocation) {
		this.motifConvocation = motifConvocation;
	}
	public String getMotifConvocation() {
		return motifConvocation;
	}
	public SortJugementVO getRefSortJugement() {
		return refSortJugement;
	}
	public void setRefSortJugement(SortJugementVO refSortJugement) {
		this.refSortJugement = refSortJugement;
	}
	public TiersSaisiVO getRefTiersSaisi() {
		return refTiersSaisi;
	}
	public void setRefTiersSaisi(TiersSaisiVO refTiersSaisi) {
		this.refTiersSaisi = refTiersSaisi;
	}
	public String getMontant() {
		return montant;
	}
	public void setMontant(String montant) {
		this.montant = montant;
	}
	public BanqueVO getRefBanque() {
		return refBanque;
	}
	public void setRefBanque(BanqueVO refBanque) {
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
	public TypeRenteVO getRefTypeRente() {
		return refTypeRente;
	}
	public void setRefTypeRente(TypeRenteVO refTypeRente) {
		this.refTypeRente = refTypeRente;
	}
	public CieCondamneeVO getRefCieCondamnee() {
		return refCieCondamnee;
	}
	public void setRefCieCondamnee(CieCondamneeVO refCieCondamnee) {
		this.refCieCondamnee = refCieCondamnee;
	}
	public GestionnaireTraitementVO getRefGestionnaireTraitement() {
		return refGestionnaireTraitement;
	}
	public void setRefGestionnaireTraitement(
			GestionnaireTraitementVO refGestionnaireTraitement) {
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