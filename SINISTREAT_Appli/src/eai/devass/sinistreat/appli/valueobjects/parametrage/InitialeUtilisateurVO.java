package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class InitialeUtilisateurVO implements IValueObject  {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String initiales;
	private String email;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getInitiales() {
		return initiales;
	}
	public void setInitiales(String initiales) {
		this.initiales = initiales;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
