package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.util.Date;
import java.util.List;

import eai.devass.sinistreat.appli.modele.parametrage.CanalConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.DestinataireRelance;
import eai.devass.sinistreat.appli.modele.parametrage.GestionnaireRelance;
import eai.devass.sinistreat.appli.modele.parametrage.RelanceConciliationPiece;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;



@SuppressWarnings("all")
public class RelanceConciliation implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private GestionnaireRelance refgestionnaire;
	private DestinataireRelance refDestinataireRelance;
	private String mailDestinataire;
	private CanalConciliation refCanalConciliation;
	private String telephone;
	private String adresse;
	private String typeRelance;
	private Date dateRelance;
	private Date dateAppel; 
	private int heureAppel;
	private int minAppel; 
	private int numeroRelance;
	private Date dateDerniereRelance;
	private Conciliation refConciliation;
	private List<RelanceConciliationPiece> relanceConciliationPiece;
	private String villeDestinataire;
	private String codeIntermediaire;
	private String nomDestinataire;
	private String dateRealisation;
	private String userCreateur;
	private Date dateCreation;
	
	public String getUserCreateur() {
		return userCreateur;
	}
	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	private transient String[] propertiesToConvert;
	
	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}
	public RelanceConciliation() {

	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public GestionnaireRelance getRefgestionnaire() {
		return refgestionnaire;
	}


	public void setRefgestionnaire(GestionnaireRelance refgestionnaire) {
		this.refgestionnaire = refgestionnaire;
	}


	public DestinataireRelance getRefDestinataireRelance() {
		return refDestinataireRelance;
	}


	public void setRefDestinataireRelance(DestinataireRelance refDestinataireRelance) {
		this.refDestinataireRelance = refDestinataireRelance;
	}


	public CanalConciliation getRefCanalConciliation() {
		return refCanalConciliation;
	}


	public void setRefCanalConciliation(CanalConciliation refCanalConciliation) {
		this.refCanalConciliation = refCanalConciliation;
	}


	public String getMailDestinataire() {
		return mailDestinataire;
	}
	public void setMailDestinataire(String mailDestinataire) {
		this.mailDestinataire = mailDestinataire;
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
	public Date getDateRelance() {
		return dateRelance;
	}
	public void setDateRelance(Date dateRelance) {
		this.dateRelance = dateRelance;
	}
	public Date getDateAppel() {
		return dateAppel;
	}
	public void setDateAppel(Date dateAppel) {
		this.dateAppel = dateAppel;
	}
	public int getHeureAppel() {
		return heureAppel;
	}
	public void setHeureAppel(int heureAppel) {
		this.heureAppel = heureAppel;
	}
	public int getMinAppel() {
		return minAppel;
	}
	public void setMinAppel(int minAppel) {
		this.minAppel = minAppel;
	}
	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}

	public int getNumeroRelance() {
		return numeroRelance;
	}
	public void setNumeroRelance(int numeroRelance) {
		this.numeroRelance = numeroRelance;
	}
	public Date getDateDerniereRelance() {
		return dateDerniereRelance;
	}
	public void setDateDerniereRelance(Date dateDerniereRelance) {
		this.dateDerniereRelance = dateDerniereRelance;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Conciliation getRefConciliation() {
		return refConciliation;
	}


	public void setRefConciliation(Conciliation refConciliation) {
		this.refConciliation = refConciliation;
	}
	public List<RelanceConciliationPiece> getRelanceConciliationPiece() {
		return relanceConciliationPiece;
	}
	
	public void setRelanceConciliationPiece(
			List<RelanceConciliationPiece> relanceConciliationPiece) {
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

}

