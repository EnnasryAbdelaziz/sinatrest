
package eai.devass.gsr.appli.valueobjects.metier.reglement;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;


public class SituationEtatVO implements IValueObject {

	private static final long serialVersionUID = 1L;

	private String lblEtat;
	private String dateOperation;
	private String operateur;
	
	
	
	public String getLblEtat() {
		return lblEtat;
	}
	public void setLblEtat(String lblEtat) {
		this.lblEtat = lblEtat;
	}
	public String getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(String dateOperation) {
		this.dateOperation = dateOperation;
	}
	public String getOperateur() {
		return operateur;
	}
	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}
	
	
	



}

