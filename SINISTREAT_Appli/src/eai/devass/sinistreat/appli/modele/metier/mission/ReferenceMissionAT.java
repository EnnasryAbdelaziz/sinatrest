/***********************************************************************
 * Module:  ReferenceMission.java
 * Author:  Administrateur
 * Purpose: Defines the Class ReferenceMission
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.metier.mission;

/**
 * Entité fonctionnelle « Références d’une mission »
 * 
 * @pdOid 4a5d45cc-8c2a-42e9-9a54-71eec72772bd
 */
public class ReferenceMissionAT {

	private long id;
	private String valeur;
	private MissionAT refMission;
	private String codeReference;
	private String libelleReference;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public ReferenceMissionAT() {

	}

	public MissionAT getRefMission() {
		return refMission;
	}

	public void setRefMission(MissionAT refMission) {
		this.refMission = refMission;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

}