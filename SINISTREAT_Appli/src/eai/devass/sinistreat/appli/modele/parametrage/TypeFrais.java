package eai.devass.sinistreat.appli.modele.parametrage;

import java.io.Serializable;

public class TypeFrais implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;

	private String libelle;

	public TypeFrais() {

	}

	public TypeFrais(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String newCode) {
		code = newCode;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String newLibelle) {
		libelle = newLibelle;
	}
}
