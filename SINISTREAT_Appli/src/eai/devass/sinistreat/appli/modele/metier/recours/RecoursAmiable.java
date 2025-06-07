package eai.devass.sinistreat.appli.modele.metier.recours;

import java.util.Date;

import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.modele.parametrage.Banque;
import eai.devass.sinistreat.appli.modele.parametrage.DecisionRecoursAmiable;
import eai.devass.sinistreat.appli.modele.parametrage.EtatProposition;
import eai.devass.sinistreat.appli.modele.parametrage.EtatReponse;
import eai.devass.sinistreat.appli.modele.parametrage.PronosticRC;



public class RecoursAmiable{
	
	private Long id;

	private Date dateProposition;
	private Double deboursLoiReclame;
	private Date dateReponse;
	private String commentaire;
	private Double montantQuitance;
	private String numeroQuittance;
	private Date dateQuittance;
	private String numeroRemise;
	private Double montantRemise;
	private Date dateRecuperation;
	private String modeRecuperation;
	private String numeroCheque;
	private String decisionSurRejet;
	private String motifAnnulation;
	private Banque refBanque;
	private Recours refRecours;
	private EtatProposition refEtatProposition;
	private PronosticRC refPartageResponsablite;
	private EtatReponse refEtatReponse;
	private DecisionRecoursAmiable refDecision;
	
	public RecoursAmiable() {
		super();
	}
	public RecoursAmiable(Long id) {
		super();
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateProposition() {
		return dateProposition;
	}
	public void setDateProposition(Date dateProposition) {
		this.dateProposition = dateProposition;
	}

	public Double getDeboursLoiReclame() {
		return deboursLoiReclame;
	}
	public void setDeboursLoiReclame(Double deboursLoiReclame) {
		this.deboursLoiReclame = deboursLoiReclame;
	}
	public Date getDateReponse() {
		return dateReponse;
	}
	public void setDateReponse(Date dateReponse) {
		this.dateReponse = dateReponse;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Date getDateQuittance() {
		return dateQuittance;
	}
	public void setDateQuittance(Date dateQuittance) {
		this.dateQuittance = dateQuittance;
	}
	public Date getDateRecuperation() {
		return dateRecuperation;
	}
	public void setDateRecuperation(Date dateRecuperation) {
		this.dateRecuperation = dateRecuperation;
	}


	public String getModeRecuperation() {
		return modeRecuperation;
	}
	public void setModeRecuperation(String modeRecuperation) {
		this.modeRecuperation = modeRecuperation;
	}
	public String getDecisionSurRejet() {
		return decisionSurRejet;
	}
	public void setDecisionSurRejet(String decisionSurRejet) {
		this.decisionSurRejet = decisionSurRejet;
	}
	public String getMotifAnnulation() {
		return motifAnnulation;
	}
	public void setMotifAnnulation(String motifAnnulation) {
		this.motifAnnulation = motifAnnulation;
	}
	public void setRefRecours(Recours refRecours) {
		this.refRecours = refRecours;
	}
	public Recours getRefRecours() {
		return refRecours;
	}
	public Double getMontantQuitance() {
		return montantQuitance;
	}
	public void setMontantQuitance(Double montantQuitance) {
		this.montantQuitance = montantQuitance;
	}
	public String getNumeroRemise() {
		return numeroRemise;
	}
	public void setNumeroRemise(String numeroRemise) {
		this.numeroRemise = numeroRemise;
	}
	public Double getMontantRemise() {
		return montantRemise;
	}
	public void setMontantRemise(Double montantRemise) {
		this.montantRemise = montantRemise;
	}
	public String getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	public String getNumeroQuittance() {
		return numeroQuittance;
	}
	public void setNumeroQuittance(String numeroQuittance) {
		this.numeroQuittance = numeroQuittance;
	}
	public Banque getRefBanque() {
		return refBanque;
	}
	public void setRefBanque(Banque refBanque) {
		this.refBanque = refBanque;
	}
	public EtatProposition getRefEtatProposition() {
		return refEtatProposition;
	}
	public void setRefEtatProposition(EtatProposition refEtatProposition) {
		this.refEtatProposition = refEtatProposition;
	}
	public PronosticRC getRefPartageResponsablite() {
		return refPartageResponsablite;
	}
	public void setRefPartageResponsablite(PronosticRC refPartageResponsablite) {
		this.refPartageResponsablite = refPartageResponsablite;
	}
	public EtatReponse getRefEtatReponse() {
		return refEtatReponse;
	}
	public void setRefEtatReponse(EtatReponse refEtatReponse) {
		this.refEtatReponse = refEtatReponse;
	}
	public DecisionRecoursAmiable getRefDecision() {
		return refDecision;
	}
	public void setRefDecision(DecisionRecoursAmiable refDecision) {
		this.refDecision = refDecision;
	}	
}
