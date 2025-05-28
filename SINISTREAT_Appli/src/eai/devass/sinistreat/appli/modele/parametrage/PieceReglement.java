/***********************************************************************
 * Module:  Etat.java
 * Author:  Administrateur
 * Purpose: Defines the Class Etat
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.parametrage;

/** @pdOid 1f8e6c5e-49a3-49b4-8e34-d868502fc3db */
public class PieceReglement implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String libelle;
	
	public PieceReglement() {
	}
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

    
}