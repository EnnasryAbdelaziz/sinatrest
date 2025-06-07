/***********************************************************************
 * Module:  ReferenceMission.java
 * Author:  Administrateur
 * Purpose: Defines the Class ReferenceMission
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.metier.mission;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

/**
 * Entité fonctionnelle « Références d’une mission »
 * 
 * @pdOid 4a5d45cc-8c2a-42e9-9a54-71eec72772bd
 */
public class ReferenceMissionATVO implements IValueObject {
	
	private String id;
	private String valeur;
	private MissionATVO refMission;
	private String codeReference;
	private String libelleReference;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	public MissionATVO getRefMission() {
		return refMission;
	}
	public void setRefMission(MissionATVO refMission) {
		this.refMission = refMission;
	}
	public String getCodeReference() {
		return codeReference;
	}
	public void setCodeReference(String codeReference) {
		this.codeReference = codeReference;
	}
	public String getLibelleReference() {
		return libelleReference;
	}
	public void setLibelleReference(String libelleReference) {
		this.libelleReference = libelleReference;
	}

	
}