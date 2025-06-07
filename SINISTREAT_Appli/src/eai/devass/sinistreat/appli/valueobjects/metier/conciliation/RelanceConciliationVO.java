package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CanalConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.DestinataireRelanceVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.GestionnaireRelanceVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.RelanceConciliationPieceVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.VilleVO;

@SuppressWarnings("all")
public class RelanceConciliationVO implements IValueObject {
	
	private String id;
	private GestionnaireRelanceVO refgestionnaire;
	private DestinataireRelanceVO refDestinataireRelance;
	private String mailDestinataire;
	private CanalConciliationVO refCanalConciliation;
	private String telephone;
	private String adresse;
	private String typeRelance;
	private String dateRelance;
	private String dateAppel; 
	private String heureAppel;
	private String minAppel; 
	private String numeroRelance;
	private String dateDerniereRelance;
	private ConciliationVO refConciliation;
	private List<RelanceConciliationPieceVO> relanceConciliationPiece;
	private String villeDestinataire;
	private String codeIntermediaire;
	private String nomDestinataire;
	private String dateRealisation;
	private String dateCreation;
	private String userCreateur;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public GestionnaireRelanceVO getRefgestionnaire() {
		return refgestionnaire;
	}
	public void setRefgestionnaire(GestionnaireRelanceVO refgestionnaire) {
		this.refgestionnaire = refgestionnaire;
	}
	public DestinataireRelanceVO getRefDestinataireRelance() {
		return refDestinataireRelance;
	}
	public void setRefDestinataireRelance(
			DestinataireRelanceVO refDestinataireRelance) {
		this.refDestinataireRelance = refDestinataireRelance;
	}
	public String getMailDestinataire() {
		return mailDestinataire;
	}
	public void setMailDestinataire(String mailDestinataire) {
		this.mailDestinataire = mailDestinataire;
	}
	public CanalConciliationVO getRefCanalConciliation() {
		return refCanalConciliation;
	}
	public void setRefCanalConciliation(CanalConciliationVO refCanalConciliation) {
		this.refCanalConciliation = refCanalConciliation;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTypeRelance() {
		return typeRelance;
	}
	public void setTypeRelance(String typeRelance) {
		this.typeRelance = typeRelance;
	}
	public String getDateRelance() {
		return dateRelance;
	}
	public void setDateRelance(String dateRelance) {
		this.dateRelance = dateRelance;
	}
	public String getDateAppel() {
		return dateAppel;
	}
	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}
	public String getHeureAppel() {
		return heureAppel;
	}
	public void setHeureAppel(String heureAppel) {
		this.heureAppel = heureAppel;
	}
	public String getMinAppel() {
		return minAppel;
	}
	public void setMinAppel(String minAppel) {
		this.minAppel = minAppel;
	}
	public String getNumeroRelance() {
		return numeroRelance;
	}
	public void setNumeroRelance(String numeroRelance) {
		this.numeroRelance = numeroRelance;
	}
	public String getDateDerniereRelance() {
		return dateDerniereRelance;
	}
	public void setDateDerniereRelance(String dateDerniereRelance) {
		this.dateDerniereRelance = dateDerniereRelance;
	}
	public ConciliationVO getRefConciliation() {
		return refConciliation;
	}
	public void setRefConciliation(ConciliationVO refConciliation) {
		this.refConciliation = refConciliation;
	}
	public List<RelanceConciliationPieceVO> getRelanceConciliationPiece() {
		return relanceConciliationPiece;
	}
	public void setRelanceConciliationPiece(
			List<RelanceConciliationPieceVO> relanceConciliationPiece) {
		this.relanceConciliationPiece = relanceConciliationPiece;
	}
	public String getVilleDestinataire() {
		return villeDestinataire;
	}
	public void setVilleDestinataire(String villeDestinataire) {
		this.villeDestinataire = villeDestinataire;
	}
	public String getCodeIntermediaire() {
		return codeIntermediaire;
	}
	public void setCodeIntermediaire(String codeIntermediaire) {
		this.codeIntermediaire = codeIntermediaire;
	}
	public String getNomDestinataire() {
		return nomDestinataire;
	}
	public void setNomDestinataire(String nomDestinataire) {
		this.nomDestinataire = nomDestinataire;
	}
	public String getDateRealisation() {
		return dateRealisation;
	}
	public void setDateRealisation(String dateRealisation) {
		this.dateRealisation = dateRealisation;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getUserCreateur() {
		return userCreateur;
	}
	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}
	
}
