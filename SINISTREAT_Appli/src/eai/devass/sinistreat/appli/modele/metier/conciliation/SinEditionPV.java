package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.io.Serializable;
import java.util.Date;

import eai.devass.commun.appli.converter.AConverter;

@AConverter(entiteDist = "eai.devass.sinistreat.appli.valueobjects.metier.conciliation.SinEditionPVVO")
public class SinEditionPV implements Serializable {

	private long id;
	private String userEditeur;
	private Date dateEdition;
	private Boolean editionSiege;
	private Boolean editionInermediaire;
	private Offre refOffre;
	private Double nbrEdition;

	private transient Boolean isEdit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserEditeur() {
		return userEditeur;
	}

	public void setUserEditeur(String userEditeur) {
		this.userEditeur = userEditeur;
	}

	public Date getDateEdition() {
		return dateEdition;
	}

	public void setDateEdition(Date dateEdition) {
		this.dateEdition = dateEdition;
	}

	public Boolean getEditionSiege() {
		return editionSiege;
	}

	public void setEditionSiege(Boolean editionSiege) {
		this.editionSiege = editionSiege;
	}

	public Boolean getEditionInermediaire() {
		return editionInermediaire;
	}

	public void setEditionInermediaire(Boolean editionInermediaire) {
		this.editionInermediaire = editionInermediaire;
	}

	public Offre getRefOffre() {
		return refOffre;
	}

	public void setRefOffre(Offre refOffre) {
		this.refOffre = refOffre;
	}

	public Double getNbrEdition() {
		return nbrEdition;
	}

	public void setNbrEdition(Double nbrEdition) {
		this.nbrEdition = nbrEdition;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

}
