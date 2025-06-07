/***********************************************************************
 * Module:  SituationRentier.java
 * Author:  WMOS
 * Purpose: Defines the Class SituationRentier
 ***********************************************************************/

package eai.devass.gsr.appli.modele.parametrage;

import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;



public class SituationEtatRentier extends SituationEtat      {

	private static final long serialVersionUID = 1L;
	private EtatRentier refEtatRentier;
	private Rentier refRentier;
	
	public EtatRentier getRefEtatRentier() {
		return refEtatRentier;
	}
	public void setRefEtatRentier(EtatRentier refEtatRentier) {
		this.refEtatRentier = refEtatRentier;
	}
	public Rentier getRefRentier() {
		return refRentier;
	}
	public void setRefRentier(Rentier refRentier) {
		this.refRentier = refRentier;
	}
	
	
	
	
	
	
	

}

