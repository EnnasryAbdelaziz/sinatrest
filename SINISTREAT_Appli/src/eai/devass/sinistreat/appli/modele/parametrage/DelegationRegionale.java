package eai.devass.sinistreat.appli.modele.parametrage;


public class DelegationRegionale implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String libelle;
	private String ville; 
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	private String adresse;
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
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
