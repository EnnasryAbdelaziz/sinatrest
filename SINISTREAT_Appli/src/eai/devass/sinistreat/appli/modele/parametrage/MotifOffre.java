package eai.devass.sinistreat.appli.modele.parametrage;

import java.io.Serializable;

public class MotifOffre implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String libelle;
	private transient String[] propertiesToConvert;
	public Long getId() {
		return id;
	}
	public MotifOffre() {
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}
}
