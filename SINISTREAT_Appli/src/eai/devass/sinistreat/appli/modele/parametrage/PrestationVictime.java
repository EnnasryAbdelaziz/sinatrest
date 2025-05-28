package eai.devass.sinistreat.appli.modele.parametrage;

public class PrestationVictime implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String codeVictime;
	private String codeRubrique;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodeVictime() {
		return codeVictime;
	}

	public void setCodeVictime(String codeVictime) {
		this.codeVictime = codeVictime;
	}

	public String getCodeRubrique() {
		return codeRubrique;
	}

	public void setCodeRubrique(String codeRubrique) {
		this.codeRubrique = codeRubrique;
	}

	

}
