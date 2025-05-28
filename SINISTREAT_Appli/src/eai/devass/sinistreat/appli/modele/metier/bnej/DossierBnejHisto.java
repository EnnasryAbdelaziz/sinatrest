package eai.devass.sinistreat.appli.modele.metier.bnej;

import java.io.Serializable;
import java.util.Date;

import eai.devass.sinistreat.appli.modele.parametrage.TypeDecisionBnej;

public class DossierBnejHisto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dateTraitement;
	private String commentaire;
	private DossierBnej refDossierBnej;
	private TypeDecisionBnej refDecisionBnej;
	private String operateur;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateTraitement() {
		return dateTraitement;
	}

	public void setDateTraitement(Date dateTraitement) {
		this.dateTraitement = dateTraitement;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public DossierBnej getRefDossierBnej() {
		return refDossierBnej;
	}

	public void setRefDossierBnej(DossierBnej refDossierBnej) {
		this.refDossierBnej = refDossierBnej;
	}

	public TypeDecisionBnej getRefDecisionBnej() {
		return refDecisionBnej;
	}

	public void setRefDecisionBnej(TypeDecisionBnej refDecisionBnej) {
		this.refDecisionBnej = refDecisionBnej;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

}
