package eai.devass.commun.appli.modele;

import eai.devass.commun.appli.rg.ContextRegleGestion;

public interface IHistorisable {
	public long getId();
	public String getOperateur();
	public ContextRegleGestion getContextRegleGestionEnum() ;
	
}
