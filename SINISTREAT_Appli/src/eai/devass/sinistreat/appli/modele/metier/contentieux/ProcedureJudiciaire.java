package eai.devass.sinistreat.appli.modele.metier.contentieux;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eai.devass.sinistreat.appli.modele.parametrage.GestionnaireRelance;
import eai.devass.sinistreat.appli.modele.parametrage.NatureDossier;
import eai.devass.sinistreat.appli.modele.parametrage.NatureProcedure;
import eai.devass.sinistreat.appli.modele.parametrage.Tribunal;
import eai.devass.sinistreat.appli.modele.parametrage.TypeJuridiction;

/**
 * @author Mouhane
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class ProcedureJudiciaire implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private Long id ;
	private Recours refRecours ;
	private String numeroDossierTribunal;
	private Tribunal refJuridiction;
	private TypeJuridiction refTypeJuridiction;
	private NatureDossier refNatureDossier;
	private String lettreInstruction;
	private String resultatJugement;
	private String requeteAdversaire;
	private String requeteAvocat;
	private String compteRenduAvocat;
	private String motifDecisionCie;
	private String decisionAdverse;
	private Date dateReceptionCopie;
	private String reglementAmiable;
	private Date dateAcceptRglAmiable;
	private String statutRglAmiable;
	private String decisionCompagnie;
	private String actionCivile;
	private String actionPublique;
	private Double montantActionPublique;
	private NatureProcedure refNatureProcedure;
	private String etatProcJud ;
	private Date dateAssignation ;
	private Date dateCreation ;
	private Date dateCreationDebut ;
	private Date dateCreationFin ;
	private String lettreAgent ;
	private String codeAvocatConseil ;
	private String nomAvocatConseil ;
	private String codeAvocatAdverse ;
	private String nomAvocatAdverse ;
	private Date dateDepot ;
	private Date dateReception ;
    private String competenceTerritorial;
    private String lienCausalite;
    private String prescription;
    private AudienceJugement derniereAudience;
	private List listePartieAdverses = new ArrayList<PartieAdverseJud>();
	private List listeAudiences ;
	private String motifModification;
	private String userCreateur;
	private String userModificateur;
	private Date dateModification;
	private Long idProcedure;
	private GestionnaireRelance refGestionnaire;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Recours getRefRecours() {
		return refRecours;
	}

	public void setRefRecours(Recours refRecours) {
		this.refRecours = refRecours;
	}

	public String getNumeroDossierTribunal() {
		return numeroDossierTribunal;
	}

	public void setNumeroDossierTribunal(String numeroDossierTribunal) {
		this.numeroDossierTribunal = numeroDossierTribunal;
	}

	public Tribunal getRefJuridiction() {
		return refJuridiction;
	}

	public void setRefJuridiction(Tribunal refJuridiction) {
		this.refJuridiction = refJuridiction;
	}



	public TypeJuridiction getRefTypeJuridiction() {
		return refTypeJuridiction;
	}

	public void setRefTypeJuridiction(TypeJuridiction refTypeJuridiction) {
		this.refTypeJuridiction = refTypeJuridiction;
	}



	public NatureDossier getRefNatureDossier() {
		return refNatureDossier;
	}

	public void setRefNatureDossier(NatureDossier refNatureDossier) {
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

	public String getDecisionAdverse() {
		return decisionAdverse;
	}

	public void setDecisionAdverse(String decisionAdverse) {
		this.decisionAdverse = decisionAdverse;
	}

	public Date getDateReceptionCopie() {
		return dateReceptionCopie;
	}

	public void setDateReceptionCopie(Date dateReceptionCopie) {
		this.dateReceptionCopie = dateReceptionCopie;
	}

	public String getReglementAmiable() {
		return reglementAmiable;
	}

	public void setReglementAmiable(String reglementAmiable) {
		this.reglementAmiable = reglementAmiable;
	}

	public Date getDateAcceptRglAmiable() {
		return dateAcceptRglAmiable;
	}

	public void setDateAcceptRglAmiable(Date dateAcceptRglAmiable) {
		this.dateAcceptRglAmiable = dateAcceptRglAmiable;
	}

	public String getStatutRglAmiable() {
		return statutRglAmiable;
	}

	public void setStatutRglAmiable(String statutRglAmiable) {
		this.statutRglAmiable = statutRglAmiable;
	}

	public String getDecisionCompagnie() {
		return decisionCompagnie;
	}

	public void setDecisionCompagnie(String decisionCompagnie) {
		this.decisionCompagnie = decisionCompagnie;
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

	public Double getMontantActionPublique() {
		return montantActionPublique;
	}

	public void setMontantActionPublique(Double montantActionPublique) {
		this.montantActionPublique = montantActionPublique;
	}


	public NatureProcedure getRefNatureProcedure() {
		return refNatureProcedure;
	}

	public void setRefNatureProcedure(NatureProcedure refNatureProcedure) {
		this.refNatureProcedure = refNatureProcedure;
	}

	public String getEtatProcJud() {
		return etatProcJud;
	}

	public void setEtatProcJud(String etatProcJud) {
		this.etatProcJud = etatProcJud;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
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

	public Date getDateDepot() {
		return dateDepot;
	}

	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}

	public Date getDateReception() {
		return dateReception;
	}

	public void setDateReception(Date dateReception) {
		this.dateReception = dateReception;
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

	public AudienceJugement getDerniereAudience() {
		return derniereAudience;
	}

	public void setDerniereAudience(AudienceJugement derniereAudience) {
		this.derniereAudience = derniereAudience;
	}

	public List getListePartieAdverses() {
		return listePartieAdverses;
	}

	public void setListePartieAdverses(List listePartieAdverses) {
		this.listePartieAdverses = listePartieAdverses;
	}

	public List getListeAudiences() {
		return listeAudiences;
	}

	public void setListeAudiences(List listeAudiences) {
		this.listeAudiences = listeAudiences;
	}

	public Date getDateAssignation() {
		return dateAssignation;
	}

	public void setDateAssignation(Date dateAssignation) {
		this.dateAssignation = dateAssignation;
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

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public void setIdProcedure(Long idProcedure) {
		this.idProcedure = idProcedure;
	}

	public Long getIdProcedure() {
		return idProcedure;
	}
	public GestionnaireRelance getRefGestionnaire() {
		return refGestionnaire;
	}


	public void setRefGestionnaire(GestionnaireRelance refGestionnaire) {
		this.refGestionnaire = refGestionnaire;
	}
	
	
	
}