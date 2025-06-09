package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;


public class NotificationSmsVO implements IValueObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String mouvement;
	private String datEnvoi;
	private SinistreVO refSinistre;	
	private String userCreateur;
	
	public NotificationSmsVO() {
		
	}
	
	public String getUserCreateur() {
		return userCreateur;
	}
	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}
	public String getMouvement() {
		return mouvement;
	}
	public void setMouvement(String mouvement) {
		this.mouvement = mouvement;
	}

	public SinistreVO getRefSinistre() {
		return refSinistre;
	}
	public void setRefSinistre(SinistreVO refSinistre) {
		this.refSinistre = refSinistre;
	}
	public String getDatEnvoi() {
		return datEnvoi;
	}
	public void setDatEnvoi(String datEnvoi) {
		this.datEnvoi = datEnvoi;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
}