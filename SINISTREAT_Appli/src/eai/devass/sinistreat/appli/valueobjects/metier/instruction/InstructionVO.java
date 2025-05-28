package eai.devass.sinistreat.appli.valueobjects.metier.instruction;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.modele.parametrage.DelegationRegionale;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.DelegationRegionaleVO;

public class InstructionVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private String id;
	private String vref;
	private String codeIntermediaire;
	private String codePrestataire;
	private String nom;
	private String prenom;
	private String ville;
	private String adresse;
	private String editer;
	private String userEditeur;
	private String userCreation;
	private String dateCreation;
	private String dateContreVisite;
	private DelegationRegionaleVO refDelegation;
	

	public DelegationRegionaleVO getRefDelegation() {
		return refDelegation;
	}

	public void setRefDelegation(DelegationRegionaleVO refDelegation) {
		this.refDelegation = refDelegation;
	}

	public String getDateContreVisite() {
		return dateContreVisite;
	}

	public void setDateContreVisite(String dateContreVisite) {
		this.dateContreVisite = dateContreVisite;
	}

	public String getHeureContreVisite() {
		return heureContreVisite;
	}

	public void setHeureContreVisite(String heureContreVisite) {
		this.heureContreVisite = heureContreVisite;
	}

	private String heureContreVisite;
	private String etatInstruction;
	private String dateModification;
	private String userModificateur;
	private List instructionPieceAt;
	private List instructionRejetAt;
	private DestinataireVO destinataire;
	private TypeLettreInstructionVO typeLettreInstruction;
	private TypeInstructionVO typeInstruction;
	private CategorieInstructionVO categorieInstruction;
	private ProcedureJudiciaireVO procedureJudiciaire;
	private SinistreVO sinistre;
	private String commentaire;
	private String emailIntermediaire;
	public String getCourrierRecommande() {
		return courrierRecommande;
	}

	public void setCourrierRecommande(String courrierRecommande) {
		this.courrierRecommande = courrierRecommande;
	}

	private String supplementPieces;
	private String userCodeSas;
	private String courrierRecommande;
	public InstructionVO() {
	}

	public InstructionVO(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getEditer() {
		return editer;
	}

	public void setEditer(String editer) {
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

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public List getInstructionPieceAt() {
		return instructionPieceAt;
	}

	public void setInstructionPieceAt(List instructionPieceAt) {
		this.instructionPieceAt = instructionPieceAt;
	}
	
	public List getInstructionRejetAt() {
		return instructionRejetAt;
	}

	public void setInstructionRejetAt(List instructionRejetAt) {
		this.instructionRejetAt = instructionRejetAt;
	}

	public DestinataireVO getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(DestinataireVO destinataire) {
		this.destinataire = destinataire;
	}

	public TypeLettreInstructionVO getTypeLettreInstruction() {
		return typeLettreInstruction;
	}

	public void setTypeLettreInstruction(
			TypeLettreInstructionVO typeLettreInstruction) {
		this.typeLettreInstruction = typeLettreInstruction;
	}

	public TypeInstructionVO getTypeInstruction() {
		return typeInstruction;
	}

	public void setTypeInstruction(TypeInstructionVO typeInstruction) {
		this.typeInstruction = typeInstruction;
	}

	public CategorieInstructionVO getCategorieInstruction() {
		return categorieInstruction;
	}

	public void setCategorieInstruction(
			CategorieInstructionVO categorieInstruction) {
		this.categorieInstruction = categorieInstruction;
	}

	public ProcedureJudiciaireVO getProcedureJudiciaire() {
		return procedureJudiciaire;
	}

	public void setProcedureJudiciaire(ProcedureJudiciaireVO procedureJudiciaire) {
		this.procedureJudiciaire = procedureJudiciaire;
	}

	public SinistreVO getSinistre() {
		return sinistre;
	}

	public void setSinistre(SinistreVO sinistre) {
		this.sinistre = sinistre;
	}

	public String getEtatInstruction() {
		return etatInstruction;
	}

	public void setEtatInstruction(String etatInstruction) {
		this.etatInstruction = etatInstruction;
	}

	public String getDateModification() {
		return dateModification;
	}

	public void setDateModification(String dateModification) {
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

	public String getUserCodeSas() {
		return userCodeSas;
	}

	public void setUserCodeSas(String userCodeSas) {
		this.userCodeSas = userCodeSas;
	}

	
}