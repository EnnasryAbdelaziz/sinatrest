package eai.devass.sinistreat.appli.valueobjects.metier.bnej;

import java.util.List;

import eai.devass.sinistreat.appli.modele.metier.bnej.DossierBnejHisto;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeDecisionBnejVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeDossierBnejVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class DossierBnejVO implements IValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String numero;
	private String operateur;
	private String dateCreation;
	private LotBnejVO refLotBnej;

	private String numeroSinistre;
	private String nomVictime;
	private String dateAccident;
	private String numeroPolice;
	private String nomClient;
	private String nomIntermediaire;
	private TypeDecisionBnejVO decision;
	private TypeDossierBnejVO typeDossier;

	private String operateurTraitement;
	private String dateTraitement;

	private String numeroGrave;
	private String refReglement;
	private String dateReglement;
	private String montant;
	private String commentaire;
	private ProcedureJudiciaireVO refProcedure;
	private List<DossierBnejHistoVO> listDossierBnejHisto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setRefLotBnej(LotBnejVO refLotBnej) {
		this.refLotBnej = refLotBnej;
	}

	public LotBnejVO getRefLotBnej() {
		return refLotBnej;
	}

	public String getNumeroSinistre() {
		return numeroSinistre;
	}

	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}

	public String getNomVictime() {
		return nomVictime;
	}

	public void setNomVictime(String nomVictime) {
		this.nomVictime = nomVictime;
	}

	public String getDateAccident() {
		return dateAccident;
	}

	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}

	public String getNumeroPolice() {
		return numeroPolice;
	}

	public void setNumeroPolice(String numeroPolice) {
		this.numeroPolice = numeroPolice;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getNomIntermediaire() {
		return nomIntermediaire;
	}

	public void setNomIntermediaire(String nomIntermediaire) {
		this.nomIntermediaire = nomIntermediaire;
	}

	public TypeDecisionBnejVO getDecision() {
		return decision;
	}

	public void setDecision(TypeDecisionBnejVO decision) {
		this.decision = decision;
	}

	public TypeDossierBnejVO getTypeDossier() {
		return typeDossier;
	}

	public void setTypeDossier(TypeDossierBnejVO typeDossier) {
		this.typeDossier = typeDossier;
	}

	public String getOperateurTraitement() {
		return operateurTraitement;
	}

	public void setOperateurTraitement(String operateurTraitement) {
		this.operateurTraitement = operateurTraitement;
	}

	public String getDateTraitement() {
		return dateTraitement;
	}

	public void setDateTraitement(String dateTraitement) {
		this.dateTraitement = dateTraitement;
	}

	public String getNumeroGrave() {
		return numeroGrave;
	}

	public void setNumeroGrave(String numeroGrave) {
		this.numeroGrave = numeroGrave;
	}

	public String getRefReglement() {
		return refReglement;
	}

	public void setRefReglement(String refReglement) {
		this.refReglement = refReglement;
	}

	public String getDateReglement() {
		return dateReglement;
	}

	public void setDateReglement(String dateReglement) {
		this.dateReglement = dateReglement;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public ProcedureJudiciaireVO getRefProcedure() {
		return refProcedure;
	}

	public void setRefProcedure(ProcedureJudiciaireVO refProcedure) {
		this.refProcedure = refProcedure;
	}

	public List<DossierBnejHistoVO> getListDossierBnejHisto() {
		return listDossierBnejHisto;
	}

	public void setListDossierBnejHisto(
			List<DossierBnejHistoVO> listDossierBnejHisto) {
		this.listDossierBnejHisto = listDossierBnejHisto;
	}

	@Override
	public String toString() {
		return "DossierBnejVO [id=" + id + ", numero=" + numero
				+ ", operateur=" + operateur + ", dateCreation=" + dateCreation
				+ ", refLotBnej=" + refLotBnej + ", numeroSinistre="
				+ numeroSinistre + ", nomVictime=" + nomVictime
				+ ", dateAccident=" + dateAccident + ", numeroPolice="
				+ numeroPolice + ", nomClient=" + nomClient
				+ ", nomIntermediaire=" + nomIntermediaire + ", decision="
				+ decision + ", typeDossier=" + typeDossier
				+ ", operateurTraitement=" + operateurTraitement
				+ ", dateTraitement=" + dateTraitement + ", numeroGrave="
				+ numeroGrave + ", refReglement=" + refReglement
				+ ", dateReglement=" + dateReglement + ", montant=" + montant
				+ ", commentaire=" + commentaire + "]";
	}

}
