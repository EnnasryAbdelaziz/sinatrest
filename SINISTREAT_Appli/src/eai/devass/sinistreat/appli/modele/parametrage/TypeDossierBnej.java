package eai.devass.sinistreat.appli.modele.parametrage;

import java.io.Serializable;

public class TypeDossierBnej implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String libelle;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	@Override
	public String toString() {
		return "TypeDossierBnej [code=" + code + ", libelle=" + libelle + "]";
	}
	

}
