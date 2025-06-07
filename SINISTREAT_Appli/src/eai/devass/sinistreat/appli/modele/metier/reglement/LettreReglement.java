package eai.devass.sinistreat.appli.modele.metier.reglement;

import java.util.Date;

public class LettreReglement {

	private Long id;
	private Date dateEdition;
	private Date dateReedition;
	private String typeLettre;
	private int nbrReedition;
	private Reglement refReglement;
	private Boolean editer = Boolean.FALSE;
	private String rubrique;
	private String typeBeneficiaire;
	private String nomBeneficiaire;
	private String userCreation;
	private Date dateCreation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateEdition() {
		return dateEdition;
	}

	public void setDateEdition(Date dateEdition) {
		this.dateEdition = dateEdition;
	}

	public Date getDateReedition() {
		return dateReedition;
	}

	public void setDateReedition(Date dateReedition) {
		this.dateReedition = dateReedition;
	}

	public String getTypeLettre() {
		return typeLettre;
	}

	public void setTypeLettre(String typeLettre) {
		this.typeLettre = typeLettre;
	}

	public int getNbrReedition() {
		return nbrReedition;
	}

	public void setNbrReedition(int nbrReedition) {
		this.nbrReedition = nbrReedition;
	}

	public Reglement getRefReglement() {
		return refReglement;
	}

	public void setRefReglement(Reglement refReglement) {
		this.refReglement = refReglement;
	}

	public Boolean getEditer() {
		return editer;
	}

	public void setEditer(Boolean editer) {
		this.editer = editer;
	}

	public String getRubrique() {
		return rubrique;
	}

	public void setRubrique(String rubrique) {
		this.rubrique = rubrique;
	}

	public String getTypeBeneficiaire() {
		return typeBeneficiaire;
	}

	public void setTypeBeneficiaire(String typeBeneficiaire) {
		this.typeBeneficiaire = typeBeneficiaire;
	}

	public String getNomBeneficiaire() {
		return nomBeneficiaire;
	}

	public void setNomBeneficiaire(String nomBeneficiaire) {
		this.nomBeneficiaire = nomBeneficiaire;
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

}
