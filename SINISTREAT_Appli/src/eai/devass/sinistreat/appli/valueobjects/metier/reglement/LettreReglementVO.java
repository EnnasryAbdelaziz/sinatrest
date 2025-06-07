package eai.devass.sinistreat.appli.valueobjects.metier.reglement;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class LettreReglementVO implements IValueObject {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String dateEdition;
	private String dateReedition;
	private String typeLettre;
	private String nbrReedition;
	private ReglementVO refReglement;
	private String editer;
	private String rubrique;
	private String typeBeneficiaire;
	private String nomBeneficiaire;
	private String userCreation;
	private String dateCreation;

	public LettreReglementVO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateEdition() {
		return dateEdition;
	}

	public void setDateEdition(String dateEdition) {
		this.dateEdition = dateEdition;
	}

	public String getDateReedition() {
		return dateReedition;
	}

	public void setDateReedition(String dateReedition) {
		this.dateReedition = dateReedition;
	}

	public String getTypeLettre() {
		return typeLettre;
	}

	public void setTypeLettre(String typeLettre) {
		this.typeLettre = typeLettre;
	}

	public String getNbrReedition() {
		return nbrReedition;
	}

	public void setNbrReedition(String nbrReedition) {
		this.nbrReedition = nbrReedition;
	}

	public ReglementVO getRefReglement() {
		return refReglement;
	}

	public void setRefReglement(ReglementVO refReglement) {
		this.refReglement = refReglement;
	}

	public String getEditer() {
		return editer;
	}

	public void setEditer(String editer) {
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

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

}
