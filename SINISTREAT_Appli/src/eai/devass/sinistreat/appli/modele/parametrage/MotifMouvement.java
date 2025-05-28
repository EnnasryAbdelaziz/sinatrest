package eai.devass.sinistreat.appli.modele.parametrage;

public class MotifMouvement implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MotifMouvement() {
	}

	private long id;

	private String libelle;

	public long getId() {
		return id;
	}

	public void setId(long newid) {
		this.id = newid;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String newlibelle) {
		this.libelle = newlibelle;
	}

}
