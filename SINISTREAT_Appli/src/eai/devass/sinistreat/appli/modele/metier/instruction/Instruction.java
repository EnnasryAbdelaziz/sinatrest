package eai.devass.sinistreat.appli.modele.metier.instruction;

import java.util.Date;
import java.util.List;

import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.DelegationRegionale;

public class Instruction {

	private long id;
	private String vref;
	private String codeIntermediaire;
	private String codePrestataire;
	private String nom;
	private String prenom;
	private String ville;
	private String adresse;
	private Boolean editer = Boolean.FALSE;
	private String userEditeur;
	private String userCreation;
	private Date dateCreation;
	private Date dateModification;
	private Date dateContreVisite;
	private String heureContreVisite;
	private DelegationRegionale refDelegation;
	public DelegationRegionale getRefDelegation() {
		return refDelegation;
	}

	public void setRefDelegation(DelegationRegionale refDelegation) {
		this.refDelegation = refDelegation;
	}

	public Date getDateContreVisite() {
		return dateContreVisite;
	}

	public void setDateContreVisite(Date dateContreVisite) {
		this.dateContreVisite = dateContreVisite;
	}

	public String getHeureContreVisite() {
		return heureContreVisite;
	}

	public void setHeureContreVisite(String heureContreVisite) {
		this.heureContreVisite = heureContreVisite;
	}

	private String userModificateur;
	private String etatInstruction;
	private List<InstructionPieceAt> instructionPieceAt;
	private List<InstructionRejetAt> instructionRejetAt;
	private Destinataire destinataire;
	private TypeLettreInstruction typeLettreInstruction;
	private TypeInstruction typeInstruction;
	private CategorieInstruction categorieInstruction;
	private ProcedureJudiciaire procedureJudiciaire;
	private Sinistre sinistre;
	private String emailIntermediaire;
	private String supplementPieces;
	private Boolean courrierRecommande;
	public Boolean getCourrierRecommande() {
		return courrierRecommande;
	}

	public void setCourrierRecommande(Boolean courrierRecommande) {
		this.courrierRecommande = courrierRecommande;
	}

	// Pour la convertion (VO BO)
	private transient String[] propertiesToConvert;
	private String userCodeSas;

	public String getUserCodeSas() {
		return userCodeSas;
	}

	public void setUserCodeSas(String userCodeSas) {
		this.userCodeSas = userCodeSas;
	}

	private String commentaire;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVref() {
		return vref;
	}

	public void setVref(String vref) {
		this.vref = vref;
	}

	public String getCodeIntermediaire() {
		return codeIntermediaire;
	}

	public void setCodeIntermediaire(String codeIntermediaire) {
		this.codeIntermediaire = codeIntermediaire;
	}

	public String getCodePrestataire() {
		return codePrestataire;
	}

	public void setCodePrestataire(String codePrestataire) {
		this.codePrestataire = codePrestataire;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Boolean getEditer() {
		return editer;
	}

	public void setEditer(Boolean editer) {
		this.editer = editer;
	}

	public String getUserEditeur() {
		return userEditeur;
	}

	public void setUserEditeur(String userEditeur) {
		this.userEditeur = userEditeur;
	}

	public String getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(String userCreation) {
		this.userCreation = userCreation;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public List<InstructionPieceAt> getInstructionPieceAt() {
		return instructionPieceAt;
	}

	public void setInstructionPieceAt(
			List<InstructionPieceAt> instructionPieceAt) {
		this.instructionPieceAt = instructionPieceAt;
	}
	
	public List<InstructionRejetAt> getInstructionRejetAt() {
		return instructionRejetAt;
	}

	public void setInstructionRejetAt(List<InstructionRejetAt> instructionRejetAt) {
		this.instructionRejetAt = instructionRejetAt;
	}

	public Destinataire getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Destinataire destinataire) {
		this.destinataire = destinataire;
	}

	public TypeLettreInstruction getTypeLettreInstruction() {
		return typeLettreInstruction;
	}

	public void setTypeLettreInstruction(
			TypeLettreInstruction typeLettreInstruction) {
		this.typeLettreInstruction = typeLettreInstruction;
	}

	public TypeInstruction getTypeInstruction() {
		return typeInstruction;
	}

	public void setTypeInstruction(TypeInstruction typeInstruction) {
		this.typeInstruction = typeInstruction;
	}

	public CategorieInstruction getCategorieInstruction() {
		return categorieInstruction;
	}

	public void setCategorieInstruction(
			CategorieInstruction categorieInstruction) {
		this.categorieInstruction = categorieInstruction;
	}

	public ProcedureJudiciaire getProcedureJudiciaire() {
		return procedureJudiciaire;
	}

	public void setProcedureJudiciaire(ProcedureJudiciaire procedureJudiciaire) {
		this.procedureJudiciaire = procedureJudiciaire;
	}

	public Sinistre getSinistre() {
		return sinistre;
	}

	public void setSinistre(Sinistre sinistre) {
		this.sinistre = sinistre;
	}

	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}

	public String getEtatInstruction() {
		return etatInstruction;
	}

	public void setEtatInstruction(String etatInstruction) {
		this.etatInstruction = etatInstruction;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public String getUserModificateur() {
		return userModificateur;
	}

	public void setUserModificateur(String userModificateur) {
		this.userModificateur = userModificateur;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getEmailIntermediaire() {
		return emailIntermediaire;
	}

	public void setEmailIntermediaire(String emailIntermediaire) {
		this.emailIntermediaire = emailIntermediaire;
	}

	public String getSupplementPieces() {
		return supplementPieces;
	}

	public void setSupplementPieces(String supplementPieces) {
		this.supplementPieces = supplementPieces;
	}

	

}