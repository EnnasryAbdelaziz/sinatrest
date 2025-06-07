package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class SinEditionPVVO implements IValueObject {

	private String id;
	private String userEditeur;
	private String dateEdition;
	private String editionSiege;
	private String editionInermediaire;
	private OffreVO refOffre;
	private String nbrEdition;

	private String isEdit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserEditeur() {
		return userEditeur;
	}

	public void setUserEditeur(String userEditeur) {
		this.userEditeur = userEditeur;
	}

	public String getDateEdition() {
		return dateEdition;
	}

	public void setDateEdition(String dateEdition) {
		this.dateEdition = dateEdition;
	}

	public String getEditionSiege() {
		return editionSiege;
	}

	public void setEditionSiege(String editionSiege) {
		this.editionSiege = editionSiege;
	}

	public String getEditionInermediaire() {
		return editionInermediaire;
	}

	public void setEditionInermediaire(String editionInermediaire) {
		this.editionInermediaire = editionInermediaire;
	}

	public OffreVO getRefOffre() {
		return refOffre;
	}

	public void setRefOffre(OffreVO refOffre) {
		this.refOffre = refOffre;
	}

	public String getNbrEdition() {
		return nbrEdition;
	}

	public void setNbrEdition(String nbrEdition) {
		this.nbrEdition = nbrEdition;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

}
