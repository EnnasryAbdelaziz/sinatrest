package eai.devass.sinistreat.appli.modele.parametrage;

public class GestionnaireRelance implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String nomComplet;
	private String mail;
	
	
	public GestionnaireRelance(String code, String nomComplet, String mail) {
		super();
		this.code = code;
		this.nomComplet = nomComplet;
		this.mail = mail;
	}
	public GestionnaireRelance() {
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getNomComplet() {
		return nomComplet;
	}
	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
