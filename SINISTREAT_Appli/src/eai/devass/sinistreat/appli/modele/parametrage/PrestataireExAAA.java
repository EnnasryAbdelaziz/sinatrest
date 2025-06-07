package eai.devass.sinistreat.appli.modele.parametrage;

public class PrestataireExAAA implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String typeCode;
	private String libelle;
	private String adresse;
	private String ville;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}
