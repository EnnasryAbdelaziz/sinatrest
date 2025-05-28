package eai.devass.edition.Utils.beans;

public class LigneTitreBean {
	
	private String libelle;
	private Boolean isDynamique;
	
	public LigneTitreBean(String libelle, Boolean isDynamique) {
		this.libelle = libelle;
		this.isDynamique = isDynamique;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Boolean getIsDynamique() {
		return isDynamique;
	}
	public void setIsDynamique(Boolean isDynamique) {
		this.isDynamique = isDynamique;
	}
}
