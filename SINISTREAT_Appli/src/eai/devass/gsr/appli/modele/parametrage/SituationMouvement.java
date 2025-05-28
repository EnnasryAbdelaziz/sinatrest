/***********************************************************************
 * Module:  SituationRentier.java
 * Author:  WMOS
 * Purpose: Defines the Class SituationRentier
 ***********************************************************************/

package eai.devass.gsr.appli.modele.parametrage;

import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;



public class SituationMouvement extends SituationEtat      {

	private static final long serialVersionUID = 1L;
	private EtatMvt refEtatMvt;
	private Mouvement refMouvement;
	
	
	
	public EtatMvt getRefEtatMvt() {
		return refEtatMvt;
	}
	public void setRefEtatMvt(EtatMvt refEtatMvt) {
		this.refEtatMvt = refEtatMvt;
	}
	public Mouvement getRefMouvement() {
		return refMouvement;
	}
	public void setRefMouvement(Mouvement refMouvement) {
		this.refMouvement = refMouvement;
	}
	
	
	
	
	
	
	

}

