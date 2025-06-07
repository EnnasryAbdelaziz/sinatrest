package eai.devass.sinistreat.appli.modele.metier.reglement;

import java.io.Serializable;
import java.util.Date;

public class HistorisationDateRappel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Reglement reglement;
	private Date dateRappel;
	private Date dateCreation;
	private String userCreation;
	
	

	public HistorisationDateRappel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reglement getReglement() {
		return reglement;
	}

	public void setReglement(Reglement reglement) {
		this.reglement = reglement;
	}

	public Date getDateRappel() {
		return dateRappel;
	}

	public void setDateRappel(Date dateRappel) {
		this.dateRappel = dateRappel;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(String userCreation) {
		this.userCreation = userCreation;
	}
}
