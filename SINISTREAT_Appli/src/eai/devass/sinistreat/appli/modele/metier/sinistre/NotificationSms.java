/***********************************************************************
 * Module:  EtatMission.java
 * Author:  Administrateur
 * Purpose: Defines the Class EtatMission
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.metier.sinistre;

import java.io.Serializable;
import java.util.Date;

/**
 * Entité fonctionnelle « Etat d’une mission »
 * 
 * @pdOid 204668b2-76b9-48a3-86cf-2e675c00d751
 */
public class NotificationSms implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String mouvement;
	private Date datEnvoi;
	private Sinistre refSinistre;
	private String userCreateur;
	// POur la convertion (VO BO)
	private transient String[] propertiesToConvert;

	public NotificationSms() {

	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMouvement() {
		return mouvement;
	}

	public void setMouvement(String mouvement) {
		this.mouvement = mouvement;
	}

	public java.util.Date getDatEnvoi() {
		return datEnvoi;
	}

	public void setDatEnvoi(java.util.Date datEnvoi) {
		this.datEnvoi = datEnvoi;
	}

	

	public Sinistre getRefSinistre() {
		return refSinistre;
	}

	public void setRefSinistre(Sinistre refSinistre) {
		this.refSinistre = refSinistre;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}

	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	public String getUserCreateur() {
		return userCreateur;
	}

	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}

}
