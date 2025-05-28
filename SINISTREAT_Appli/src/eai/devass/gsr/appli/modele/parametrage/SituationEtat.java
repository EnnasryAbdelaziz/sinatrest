/***********************************************************************
 * Module:  SituationRentier.java
 * Author:  WMOS
 * Purpose: Defines the Class SituationRentier
 ***********************************************************************/

package eai.devass.gsr.appli.modele.parametrage;

import java.util.Date;

import eai.devass.commun.appli.modele.EntiteBO;


public class SituationEtat extends EntiteBO      {

	private static final long serialVersionUID = 1L;
	private String operateur;
	private MotifSituationEtat refMotifSituationEtat;
	private Date dateEtat;

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public MotifSituationEtat getRefMotifSituationEtat() {
		return refMotifSituationEtat;
	}

	public void setRefMotifSituationEtat(MotifSituationEtat refMotifSituationEtat) {
		this.refMotifSituationEtat = refMotifSituationEtat;
	}

	public Date getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(Date dateEtat) {
		this.dateEtat = dateEtat;
	}
}

