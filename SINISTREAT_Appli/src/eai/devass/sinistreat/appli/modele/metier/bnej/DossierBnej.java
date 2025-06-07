package eai.devass.sinistreat.appli.modele.metier.bnej;

import java.util.Date;
import java.util.List;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.parametrage.TypeDecisionBnej;
import eai.devass.sinistreat.appli.modele.parametrage.TypeDossierBnej;

public class DossierBnej implements IEntite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String numero;
	private String operateur;
	private Date dateCreation;
	private LotBnej refLotBnej;

	private String numeroSinistre;
	private String nomVictime;
	private String dateAccident;
	private String numeroPolice;
	private String nomClient;
	private String nomIntermediaire;
	private TypeDecisionBnej decision;
	private TypeDossierBnej typeDossier;

	private String operateurTraitement;
	private String dateTraitement;

	private String numeroGrave;
	private String refReglement;
	private Date dateReglement;
	private Double montant;
	private String commentaire;
	private ProcedureJudiciaire refProcedure;
	private List<DossierBnejHisto> listDossierBnejHisto;

	public IEntiteFactory getFactory() {
		return null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setRefLotBnej(LotBnej refLotBnej) {
		this.refLotBnej = refLotBnej;
	}

	public LotBnej getRefLotBnej() {
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

	public TypeDecisionBnej getDecision() {
		return decision;
	}

	public void setDecision(TypeDecisionBnej decision) {
		this.decision = decision;
	}

	public TypeDossierBnej getTypeDossier() {
		return typeDossier;
	}

	public void setTypeDossier(TypeDossierBnej typeDossier) {
		this.typeDossier = typeDossier;
	}

	public void increment() {
		this.getRefLotBnej().increment();

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

	public Date getDateReglement() {
		return dateReglement;
	}

	public void setDateReglement(Date dateReglement) {
		this.dateReglement = dateReglement;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public ProcedureJudiciaire getRefProcedure() {
		return refProcedure;
	}

	public void setRefProcedure(ProcedureJudiciaire refProcedure) {
		this.refProcedure = refProcedure;
	}

	public List<DossierBnejHisto> getListDossierBnejHisto() {
		return listDossierBnejHisto;
	}

	public void setListDossierBnejHisto(
			List<DossierBnejHisto> listDossierBnejHisto) {
		this.listDossierBnejHisto = listDossierBnejHisto;
	}

	@Override
	public String toString() {
		return "DossierBnej [id=" + id + ", numero=" + numero + ", operateur="
				+ operateur + ", dateCreation=" + dateCreation
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
