package eai.devass.sinistreat.appli.valueobjects.metier.contentieux;

import java.util.ArrayList;
import java.util.List;

import eai.devass.sinistreat.appli.valueobjects.parametrage.GestionnaireRelanceVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.JuridictionVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.NatureDossierVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.NatureProcedureVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TribunalVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeJuridictionVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

/**
 * @author Mouhane
 *
 */

public class ProcedureJudiciaireVO implements IValueObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id ;
	private RecoursVO refRecours ;
	private String numeroDossierTribunal;
	private TribunalVO refJuridiction;
	private TypeJuridictionVO refTypeJuridiction;
	private NatureDossierVO refNatureDossier;
	private String lettreInstruction;
	private String resultatJugement;
	private String requeteAdversaire;
	private String requeteAvocat;
	private String compteRenduAvocat;
	private String motifDecisionCie;
	private String decisionAdverse;
	private String dateReceptionCopie;
	private String reglementAmiable;
	private String dateAcceptRglAmiable;
	private String statutRglAmiable;
	private String decisionCompagnie;
	private String actionCivile;
	private String actionPublique;
	private String montantActionPublique;
	private NatureProcedureVO refNatureProcedure;
	private String etatProcJud ;
	private String dateCreation ;
	private String dateCreationDebut ;
	private String dateCreationFin ;
	private String lettreAgent ;
	private String codeAvocatConseil ;
	private String nomAvocatConseil ;
	private String codeAvocatAdverse ;
	private String nomAvocatAdverse ;
	private String dateDepot ;
	private String dateReception ;
    private String competenceTerritorial;
    private String lienCausalite;
    private String prescription;
    private String libellePrescription;
    private AudienceJugementVO derniereAudience;
	private List listTypeJuridiction = new ArrayList<TypeJuridictionVO>();
	private List listJuridiction = new ArrayList<JuridictionVO>();
	private List listNatureDossier = new ArrayList<NatureDossierVO>();
	private List listePartieAdverses = new ArrayList<PartieAdverseJudVO>();
	private List listeAudiences = new ArrayList<AudienceJugementVO>();
	private List listGestionnaire = new ArrayList<GestionnaireRelanceVO>();
	
	private String dateAssignation;
	private String motifModification;
	private String userCreateur;
	private String userModificateur;
	private String dateModification;
	private String idProcedure;
	private GestionnaireRelanceVO refGestionnaire;
	
	public ProcedureJudiciaireVO() {
		super();
	}

	public ProcedureJudiciaireVO(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumeroDossierTribunal() {
		return numeroDossierTribunal;
	}

	public void setNumeroDossierTribunal(String numeroDossierTribunal) {
		this.numeroDossierTribunal = numeroDossierTribunal;
	}

	public RecoursVO getRefRecours() {
		return refRecours;
	}

	public void setRefRecours(RecoursVO refRecours) {
		this.refRecours = refRecours;
	}

	public TribunalVO getRefJuridiction() {
		return refJuridiction;
	}

	public void setRefJuridiction(TribunalVO refJuridiction) {
		this.refJuridiction = refJuridiction;
	}

	public TypeJuridictionVO getRefTypeJuridiction() {
		return refTypeJuridiction;
	}

	public void setRefTypeJuridiction(TypeJuridictionVO refTypeJuridiction) {
		this.refTypeJuridiction = refTypeJuridiction;
	}

	public NatureDossierVO getRefNatureDossier() {
		return refNatureDossier;
	}

	public void setRefNatureDossier(NatureDossierVO refNatureDossier) {
		this.refNatureDossier = refNatureDossier;
	}

	public String getLettreInstruction() {
		return lettreInstruction;
	}

	public void setLettreInstruction(String lettreInstruction) {
		this.lettreInstruction = lettreInstruction;
	}

	public String getResultatJugement() {
		return resultatJugement;
	}

	public void setResultatJugement(String resultatJugement) {
		this.resultatJugement = resultatJugement;
	}

	public String getRequeteAdversaire() {
		return requeteAdversaire;
	}

	public void setRequeteAdversaire(String requeteAdversaire) {
		this.requeteAdversaire = requeteAdversaire;
	}

	public String getDecisionCompagnie() {
		return decisionCompagnie;
	}

	public void setDecisionCompagnie(String decisionCompagnie) {
		this.decisionCompagnie = decisionCompagnie;
	}

	public List getListTypeJuridiction() {
		return listTypeJuridiction;
	}

	public void setListTypeJuridiction(List listTypeJuridiction) {
		this.listTypeJuridiction = listTypeJuridiction;
	}

	public List getListJuridiction() {
		return listJuridiction;
	}

	public void setListJuridiction(List listJuridiction) {
		this.listJuridiction = listJuridiction;
	}

	public List getListNatureDossier() {
		return listNatureDossier;
	}

	public void setListNatureDossier(List listNatureDossier) {
		this.listNatureDossier = listNatureDossier;
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

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getEtatProcJud() {
		return etatProcJud;
	}

	public void setEtatProcJud(String etatProcJud) {
		this.etatProcJud = etatProcJud;
	}

	public List getListePartieAdverses() {
		return listePartieAdverses;
	}

	public void setListePartieAdverses(List listePartieAdverses) {
		this.listePartieAdverses = listePartieAdverses;
	}

	public String getLettreAgent() {
		return lettreAgent;
	}

	public void setLettreAgent(String lettreAgent) {
		this.lettreAgent = lettreAgent;
	}

	public String getNomAvocatAdverse() {
		return nomAvocatAdverse;
	}

	public void setNomAvocatAdverse(String nomAvocatAdverse) {
		this.nomAvocatAdverse = nomAvocatAdverse;
	}

	public String getCodeAvocatConseil() {
		return codeAvocatConseil;
	}

	public void setCodeAvocatConseil(String codeAvocatConseil) {
		this.codeAvocatConseil = codeAvocatConseil;
	}

	public String getDateDepot() {
		return dateDepot;
	}

	public void setDateDepot(String dateDepot) {
		this.dateDepot = dateDepot;
	}

	public String getDateReception() {
		return dateReception;
	}

	public void setDateReception(String dateReception) {
		this.dateReception = dateReception;
	}

	public String getRequeteAvocat() {
		return requeteAvocat;
	}

	public void setRequeteAvocat(String requeteAvocat) {
		this.requeteAvocat = requeteAvocat;
	}

	public String getCompteRenduAvocat() {
		return compteRenduAvocat;
	}

	public void setCompteRenduAvocat(String compteRenduAvocat) {
		this.compteRenduAvocat = compteRenduAvocat;
	}

	public String getMotifDecisionCie() {
		return motifDecisionCie;
	}

	public void setMotifDecisionCie(String motifDecisionCie) {
		this.motifDecisionCie = motifDecisionCie;
	}

	public String getDateReceptionCopie() {
		return dateReceptionCopie;
	}

	public void setDateReceptionCopie(String dateReceptionCopie) {
		this.dateReceptionCopie = dateReceptionCopie;
	}

	public String getReglementAmiable() {
		return reglementAmiable;
	}

	public void setReglementAmiable(String reglementAmiable) {
		this.reglementAmiable = reglementAmiable;
	}

	public String getDateAcceptRglAmiable() {
		return dateAcceptRglAmiable;
	}

	public void setDateAcceptRglAmiable(String dateAcceptRglAmiable) {
		this.dateAcceptRglAmiable = dateAcceptRglAmiable;
	}

	public String getStatutRglAmiable() {
		return statutRglAmiable;
	}

	public void setStatutRglAmiable(String statutRglAmiable) {
		this.statutRglAmiable = statutRglAmiable;
	}

	public String getDecisionAdverse() {
		return decisionAdverse;
	}

	public void setDecisionAdverse(String decisionAdverse) {
		this.decisionAdverse = decisionAdverse;
	}

	public String getActionCivile() {
		return actionCivile;
	}

	public void setActionCivile(String actionCivile) {
		this.actionCivile = actionCivile;
	}

	public String getActionPublique() {
		return actionPublique;
	}

	public void setActionPublique(String actionPublique) {
		this.actionPublique = actionPublique;
	}

	public String getCompetenceTerritorial() {
		return competenceTerritorial;
	}

	public void setCompetenceTerritorial(String competenceTerritorial) {
		this.competenceTerritorial = competenceTerritorial;
	}

	public String getLienCausalite() {
		return lienCausalite;
	}

	public void setLienCausalite(String lienCausalite) {
		this.lienCausalite = lienCausalite;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getMontantActionPublique() {
		return montantActionPublique;
	}

	public void setMontantActionPublique(String montantActionPublique) {
		this.montantActionPublique = montantActionPublique;
	}

	public String getLibellePrescription() {
		return libellePrescription;
	}

	public void setLibellePrescription(String libellePrescription) {
		this.libellePrescription = libellePrescription;
	}

	public NatureProcedureVO getRefNatureProcedure() {
		return refNatureProcedure;
	}

	public void setRefNatureProcedure(NatureProcedureVO refNatureProcedure) {
		this.refNatureProcedure = refNatureProcedure;
	}

	public void setDateAssignation(String dateAssignation) {
		this.dateAssignation = dateAssignation;
	}

	public String getDateAssignation() {
		return dateAssignation;
	}

	public void setListeAudiences(List listeAudiences) {
		this.listeAudiences = listeAudiences;
	}

	public List getListeAudiences() {
		return listeAudiences;
	}

	public void setDerniereAudience(AudienceJugementVO derniereAudience) {
		this.derniereAudience = derniereAudience;
	}

	public AudienceJugementVO getDerniereAudience() {
		return derniereAudience;
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

	public String getMotifModification() {
		return motifModification;
	}

	public void setMotifModification(String motifModification) {
		this.motifModification = motifModification;
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

	public String getDateModification() {
		return dateModification;
	}

	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}

	public void setIdProcedure(String idProcedure) {
		this.idProcedure = idProcedure;
	}

	public String getIdProcedure() {
		return idProcedure;
	}
	public GestionnaireRelanceVO getRefGestionnaire() {
		return refGestionnaire;
	}

	public void setRefGestionnaire(GestionnaireRelanceVO refGestionnaire) {
		this.refGestionnaire = refGestionnaire;
	}

	public List getListGestionnaire() {
		return listGestionnaire;
	}

	public void setListGestionnaire(List listGestionnaire) {
		this.listGestionnaire = listGestionnaire;
	}

}