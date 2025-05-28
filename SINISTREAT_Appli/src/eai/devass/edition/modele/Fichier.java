package eai.devass.edition.modele;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;

public class Fichier implements IEntite   {
	private static final long serialVersionUID = 1L;
	private String cheminFichier;

	public String getCheminFichier() {
		return cheminFichier;
	}

	public void setCheminFichier(String cheminFichier) {
		this.cheminFichier = cheminFichier;
	}

	public IEntiteFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setId(long arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
