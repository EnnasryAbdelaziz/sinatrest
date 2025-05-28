package eai.devass.sinistreat.appli.modele.parametrage;

public class CanalConciliation implements java.io.Serializable {
	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
}
