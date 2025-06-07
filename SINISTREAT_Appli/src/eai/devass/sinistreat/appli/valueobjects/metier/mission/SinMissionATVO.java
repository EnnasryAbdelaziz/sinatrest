/***********************************************************************
 * Module:  Mission.java
 * Author:  Administrateur
 * Purpose: Defines the Class Mission
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.metier.mission;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class SinMissionATVO implements IValueObject {

	private String id;
    private long idmission;
	private String numerosinistre;
	private String codprestataire;
	

	
	
	public SinMissionATVO() {
		
	}
	
	public SinMissionATVO(String id) {
		this.setId(id);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public long getidmission() {
		return idmission;
	}
	public void setidmission(long idmission) {
		this.idmission = idmission;
	}
	public String getnumerosinistre() {
		return numerosinistre;
	}
	public void setnumerosinistre(String numerosinistre) {
		this.numerosinistre = numerosinistre;
	}
	
	public String getcodprestataire() {
		return codprestataire;
	}

	public void setcodprestataire(String codprestataire) {
		this.codprestataire = codprestataire;
	}



}