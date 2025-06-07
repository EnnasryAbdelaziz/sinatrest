package eai.devass.edition.valueobjects;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class FichierVO implements IValueObject  {
	private static final long serialVersionUID = 1L;
	private List<String> cheminFichier;
	public List<String> getCheminFichier() {
		return cheminFichier;
	}
	public void setCheminFichier(List<String> cheminFichier) {
		this.cheminFichier = cheminFichier;
	}


	
	
	
}
