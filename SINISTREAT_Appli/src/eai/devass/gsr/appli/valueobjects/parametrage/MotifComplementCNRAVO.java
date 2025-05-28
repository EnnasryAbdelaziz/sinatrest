package eai.devass.gsr.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;

/**
 * Value Object de MotifComplementCNRA
 * 
 * @author Nom Prenom (email)
 */

public class MotifComplementCNRAVO extends RechListeVO {

	private static final long serialVersionUID = 1L;

	private String libelle;

	private String code;

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
