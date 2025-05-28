package eai.devass.sinistreat.appli.modele.metier.instruction;

public class DestinataireCheque implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String code;
	private String libelle;

	public DestinataireCheque() {
	}

	public DestinataireCheque(String code) {
		this.code = code;
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
