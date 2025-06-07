package eai.devass.sinistreat.appli.valueobjects.metier.bnej;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeDecisionBnejVO;

public class DossierBnejHistoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private String id;
	private String dateTraitement;
	private String commentaire;
	private DossierBnejVO refDossierBnej;
	private TypeDecisionBnejVO refDecisionBnej;
	private String operateur;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateTraitement() {
		return dateTraitement;
	}

	public void setDateTraitement(String dateTraitement) {
		this.dateTraitement = dateTraitement;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public DossierBnejVO getRefDossierBnej() {
		return refDossierBnej;
	}

	public void setRefDossierBnej(DossierBnejVO refDossierBnej) {
		this.refDossierBnej = refDossierBnej;
	}

	public TypeDecisionBnejVO getRefDecisionBnej() {
		return refDecisionBnej;
	}

	public void setRefDecisionBnej(TypeDecisionBnejVO refDecisionBnej) {
		this.refDecisionBnej = refDecisionBnej;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

}
