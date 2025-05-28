/***********************************************************************
 * Module:  Mission.java
 * Author:  Administrateur
 * Purpose: Defines the Class Mission
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.metier.mission;

import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.historique.HistoryItem;


public class SinMissionAT   {
	private Long id;
	private long idmission;
	private String numerosinistre;
	private String codprestataire;
	private Logger logger = Logger.getLogger("loggerGSR");
	/** Attributes non mappée */
	
	public SinMissionAT() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdmission() {
		return idmission;
	}

	public void setIdmission(Long idmission) {
		this.idmission = idmission;
	}

	public String getNumeroSinistre() {
		return numerosinistre;
	}

	public void setNumeroSinistre(String numerosinistre) {
		this.numerosinistre = numerosinistre;
	}
	
	public String getCodPrestataire() {
		return codprestataire;
	}

	public void setCodPrestataire(String codprestataire) {
		this.codprestataire = codprestataire;
	}

	/** Ajouter une 'EtatMissionVO' à la liste getRefEtatMissions
	 * @param etatMission
	 */
	
	
	public HistoryItem getLastHistoryItem() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getParents() {
		// TODO Auto-generated method stub
		return null;
	}

	public void historiser() {
		// TODO Auto-generated method stub
		//System.err.println("okokok");
		logger.error("okokok");
	}


	

}