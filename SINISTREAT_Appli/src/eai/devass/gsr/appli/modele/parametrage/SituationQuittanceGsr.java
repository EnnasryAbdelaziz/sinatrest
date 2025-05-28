/***********************************************************************
 * Module:  SituationRentier.java
 * Author:  WMOS
 * Purpose: Defines the Class SituationRentier
 ***********************************************************************/

package eai.devass.gsr.appli.modele.parametrage;

import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;



public class SituationQuittanceGsr extends SituationEtat      {

	private static final long serialVersionUID = 1L;
	private EtatQtc refEtatQtc;
	private QuittanceGsr refQuittanceGsr;
	
	
	public EtatQtc getRefEtatQtc() {
		return refEtatQtc;
	}
	public void setRefEtatQtc(EtatQtc refEtatQtc) {
		this.refEtatQtc = refEtatQtc;
	}
	public QuittanceGsr getRefQuittanceGsr() {
		return refQuittanceGsr;
	}
	public void setRefQuittanceGsr(QuittanceGsr refQuittanceGsr) {
		this.refQuittanceGsr = refQuittanceGsr;
	}
	
	
	
	
	

}

