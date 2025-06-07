/***********************************************************************
 * Module:  SituationRentier.java
 * Author:  WMOS
 * Purpose: Defines the Class SituationRentier
 ***********************************************************************/

package eai.devass.gsr.appli.modele.parametrage;

import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;



public class SituationDossierRentier extends SituationEtat      {

	private static final long serialVersionUID = 1L;
	private EtatRentier refEtatRentier;
	private DossierRente refDossierRente;
	
	public EtatRentier getRefEtatRentier() {
		return refEtatRentier;
	}
	public void setRefEtatRentier(EtatRentier refEtatRentier) {
		this.refEtatRentier = refEtatRentier;
	}
	public DossierRente getRefDossierRente() {
		return refDossierRente;
	}
	public void setRefDossierRente(DossierRente refDossierRente) {
		this.refDossierRente = refDossierRente;
	}
	
	
	
	
	
	
	

}

