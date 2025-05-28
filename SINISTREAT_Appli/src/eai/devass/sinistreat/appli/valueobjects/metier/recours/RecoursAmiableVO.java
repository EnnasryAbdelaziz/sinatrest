package eai.devass.sinistreat.appli.valueobjects.metier.recours;



import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.BanqueVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.DecisionRecoursAmiableVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatPropositionVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatReponseVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PronosticRCVO;


public class RecoursAmiableVO implements IValueObject {
	private String id;
	private String dateProposition;
	private String deboursLoiReclame;
	private String dateReponse;
	private String commentaire;
	private String montantQuitance;
	private String numeroQuittance;
	private String dateQuittance;
	private String numeroRemise;
	private String montantRemise;
	private String dateRecuperation;
	private String modeRecuperation;
	private String numeroCheque;
	private String decisionSurRejet;
	private String motifAnnulation;
	private RecoursVO refRecours;
	private BanqueVO refBanque;
	private EtatPropositionVO refEtatProposition;
	private PronosticRCVO refPartageResponsablite;
	private EtatReponseVO refEtatReponse;
	private DecisionRecoursAmiableVO refDecision;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDateProposition() {
		return dateProposition;
	}
	public void setDateProposition(String dateProposition) {
		this.dateProposition = dateProposition;
	}
	public String getDeboursLoiReclame() {
		return deboursLoiReclame;
	}
	public void setDeboursLoiReclame(String deboursLoiReclame) {
		this.deboursLoiReclame = deboursLoiReclame;
	}
	public String getDateReponse() {
		return dateReponse;
	}
	public void setDateReponse(String dateReponse) {
		this.dateReponse = dateReponse;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public String getMontantQuitance() {
		return montantQuitance;
	}
	public void setMontantQuitance(String montantQuitance) {
		this.montantQuitance = montantQuitance;
	}

	public String getNumeroQuittance() {
		return numeroQuittance;
	}
	public void setNumeroQuittance(String numeroQuittance) {
		this.numeroQuittance = numeroQuittance;
	}

	public String getDateQuittance() {
		return dateQuittance;
	}
	public void setDateQuittance(String dateQuittance) {
		this.dateQuittance = dateQuittance;
	}
	public String getNumeroRemise() {
		return numeroRemise;
	}
	public void setNumeroRemise(String numeroRemise) {
		this.numeroRemise = numeroRemise;
	}
	public String getMontantRemise() {
		return montantRemise;
	}
	public void setMontantRemise(String montantRemise) {
		this.montantRemise = montantRemise;
	}
	public String getDateRecuperation() {
		return dateRecuperation;
	}
	public void setDateRecuperation(String dateRecuperation) {
		this.dateRecuperation = dateRecuperation;
	}
	public String getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
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
	public void setRefRecours(RecoursVO refRecours) {
		this.refRecours = refRecours;
	}
	public RecoursVO getRefRecours() {
		return refRecours;
	}
	public String getModeRecuperation() {
		return modeRecuperation;
	}
	public void setModeRecuperation(String modeRecuperation) {
		this.modeRecuperation = modeRecuperation;
	}
	public BanqueVO getRefBanque() {
		return refBanque;
	}
	public void setRefBanque(BanqueVO refBanque) {
		this.refBanque = refBanque;
	}
	public EtatPropositionVO getRefEtatProposition() {
		return refEtatProposition;
	}
	public void setRefEtatProposition(EtatPropositionVO refEtatProposition) {
		this.refEtatProposition = refEtatProposition;
	}
	public PronosticRCVO getRefPartageResponsablite() {
		return refPartageResponsablite;
	}
	public void setRefPartageResponsablite(PronosticRCVO refPartageResponsablite) {
		this.refPartageResponsablite = refPartageResponsablite;
	}
	public EtatReponseVO getRefEtatReponse() {
		return refEtatReponse;
	}
	public void setRefEtatReponse(EtatReponseVO refEtatReponse) {
		this.refEtatReponse = refEtatReponse;
	}
	public DecisionRecoursAmiableVO getRefDecision() {
		return refDecision;
	}
	public void setRefDecision(DecisionRecoursAmiableVO refDecision) {
		this.refDecision = refDecision;
	}
}