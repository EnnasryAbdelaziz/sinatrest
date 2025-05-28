/***********************************************************************
 * Module:  Mission.java
 * Author:  Administrateur
 * Purpose: Defines the Class Mission
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.metier.mission;

import java.util.Date;
import java.util.List;

import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.EtatMission;


public class MissionAT   {
	private long id;
	private String idMission;
	private String codePrestataire;
	private String codeNatureMission;
	private String libelleNatureMission;
    private String instructions;
    private Date dateMission;
    private EtatMission refEtatMission;
    private Date dateEtatMission;
    private String commentaire;
    private boolean reponseRecu;
    private String reponse;
    private Date dateReponse;
    private double montantFacture;
    private double montantAPaye;
	private List listPrestation;
	private List listReference;
	private Sinistre refSinistre;
	private String codeUser;
	private String idReponse;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCodePrestataire() {
		return codePrestataire;
	}
	public void setCodePrestataire(String codePrestataire) {
		this.codePrestataire = codePrestataire;
	}
	public String getCodeNatureMission() {
		return codeNatureMission;
	}
	public void setCodeNatureMission(String codeNatureMission) {
		this.codeNatureMission = codeNatureMission;
	}
	public String getLibelleNatureMission() {
		return libelleNatureMission;
	}
	public void setLibelleNatureMission(String libelleNatureMission) {
		this.libelleNatureMission = libelleNatureMission;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public Date getDateMission() {
		return dateMission;
	}
	public void setDateMission(Date dateMission) {
		this.dateMission = dateMission;
	}
	public EtatMission getRefEtatMission() {
		return refEtatMission;
	}
	public void setRefEtatMission(EtatMission refEtatMission) {
		this.refEtatMission = refEtatMission;
	}
	public Date getDateEtatMission() {
		return dateEtatMission;
	}
	public void setDateEtatMission(Date dateEtatMission) {
		this.dateEtatMission = dateEtatMission;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public boolean isReponseRecu() {
		return reponseRecu;
	}
	public void setReponseRecu(boolean reponseRecu) {
		this.reponseRecu = reponseRecu;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public Date getDateReponse() {
		return dateReponse;
	}
	public void setDateReponse(Date dateReponse) {
		this.dateReponse = dateReponse;
	}
	public double getMontantFacture() {
		return montantFacture;
	}
	public void setMontantFacture(double montantFacture) {
		this.montantFacture = montantFacture;
	}
	public double getMontantAPaye() {
		return montantAPaye;
	}
	public void setMontantAPaye(double montantAPaye) {
		this.montantAPaye = montantAPaye;
	}
	public List getListPrestation() {
		return listPrestation;
	}
	public void setListPrestation(List listPrestation) {
		this.listPrestation = listPrestation;
	}
	public List getListReference() {
		return listReference;
	}
	public void setListReference(List listReference) {
		this.listReference = listReference;
	}
	public void setIdMission(String idMission) {
		this.idMission = idMission;
	}
	public String getIdMission() {
		return idMission;
	}
	public void setRefSinistre(Sinistre refSinistre) {
		this.refSinistre = refSinistre;
	}
	public Sinistre getRefSinistre() {
		return refSinistre;
	}
	public void setCodeUser(String codeUser) {
		this.codeUser = codeUser;
	}
	public String getCodeUser() {
		return codeUser;
	}
	public void setIdReponse(String idReponse) {
		this.idReponse = idReponse;
	}
	public String getIdReponse() {
		return idReponse;
	}

}