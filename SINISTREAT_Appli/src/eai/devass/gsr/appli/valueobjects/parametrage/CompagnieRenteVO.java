package eai.devass.gsr.appli.valueobjects.parametrage;


/**
 * Value Object de CentreProthese
 * 
 * @author Nom Prenom (email)
 */
public class CompagnieRenteVO extends ParamVO {
	private long id;
	private String code;
	private static final long serialVersionUID = 1L;
	private String libelle;


	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
