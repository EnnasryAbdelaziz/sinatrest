package eai.devass.commun.appli.modele;

import java.io.Serializable;
import java.util.Date;

import eai.devass.commun.appli.rg.ContextRegleGestion;



public class EntiteBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long identifiant;
	private Date dateSuppression;
	
	private transient String operateur;
	private transient String contextRegleGestion;
	private transient Boolean runRegleGestion;
	private transient Boolean lightConvertion;
	private transient Boolean persistObject;
		
	public EntiteBO() {
		
	}
	
	public EntiteBO(Long identifiant) {
		this.identifiant = identifiant;
	}


	public Long getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(Long identifiant) {
		this.identifiant = identifiant;
	}

	public Boolean getRunRegleGestion() {
		return runRegleGestion;
	}

	public void setRunRegleGestion(Boolean runRegleGestion) {
		this.runRegleGestion = runRegleGestion;
	}

	public void setContextRegleGestion(String contextRegleGestion) {
		this.contextRegleGestion = contextRegleGestion;
	}
	

	public Date getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Date dateSuppression) {
		this.dateSuppression = dateSuppression;
	}
	
	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}
	
	public Boolean getLightConvertion() {
		return lightConvertion;
	}

	public void setLightConvertion(Boolean lightConvertion) {
		this.lightConvertion = lightConvertion;
	}
	
	public Boolean getPersistObject() {
		return persistObject;
	}

	public void setPersistObject(Boolean persistObject) {
		this.persistObject = persistObject;
	}

	public ContextRegleGestion getContextRegleGestionEnum() {
		if (ContextRegleGestion.CREATION.toString().equalsIgnoreCase(
				contextRegleGestion)) {
			return ContextRegleGestion.CREATION;
			
		} else if (ContextRegleGestion.MODIFICATION.toString().equalsIgnoreCase(
				contextRegleGestion)) {
			return ContextRegleGestion.MODIFICATION;
			
		} else if (ContextRegleGestion.SUPPRESSION.toString().equalsIgnoreCase(
				contextRegleGestion)) {
			return ContextRegleGestion.SUPPRESSION;
			
		} else if (ContextRegleGestion.VALIDATION.toString().equalsIgnoreCase(
				contextRegleGestion)) {
			return ContextRegleGestion.VALIDATION;
			
		} else if (ContextRegleGestion.ANNULATION.toString().equalsIgnoreCase(
				contextRegleGestion)) {
			return ContextRegleGestion.ANNULATION;
			
		} else if (ContextRegleGestion.BATCH.toString().equalsIgnoreCase(
				contextRegleGestion)) {
			return ContextRegleGestion.BATCH;
			
			
		} else {
			return ContextRegleGestion.DEFAULT;
		}
			
	}

	public String getDescription() {
		return null;
	}
	
	
	public boolean addEntiteToListItem() {
		return false;
	}
	
	public boolean hasDateSuppression() {
		return true;
	}
	

}
