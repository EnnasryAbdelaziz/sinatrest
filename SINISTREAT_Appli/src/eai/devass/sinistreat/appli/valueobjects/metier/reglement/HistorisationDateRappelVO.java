package eai.devass.sinistreat.appli.valueobjects.metier.reglement;

public class HistorisationDateRappelVO {

	private String id;
	private String reglement;
	private String dateRappel;
	private String dateCreation;
	private String userCreation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReglement() {
		return reglement;
	}

	public void setReglement(String reglement) {
		this.reglement = reglement;
	}

	public String getDateRappel() {
		return dateRappel;
	}

	public void setDateRappel(String dateRappel) {
		this.dateRappel = dateRappel;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(String userCreation) {
		this.userCreation = userCreation;
	}

}
