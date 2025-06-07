/***********************************************************************
 * Module:  Mission.java
 * Author:  Administrateur
 * Purpose: Defines the Class Mission
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.metier.mission;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatMissionVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PrestataireVO;

public class MissionATVO implements IValueObject {

	private String id;
	private String idMission;
	private PrestataireVO refPrestataire;
	private String codeNatureMission;
	private String libelleNatureMission;
    private String instructions;
    private String dateMission;
    private EtatMissionVO refEtatMission;
    private String dateEtatMission;
    private String commentaire;
    private String reponseRecu;
    private String reponse;
    private String dateReponse;
    private String montantFacture;
    private String montantAPaye;
	private List listPrestation;
	private List listReference;
	private SinistreVO refSinistre;
	private String codeUser;
	private String idReponse;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PrestataireVO getRefPrestataire() {
		return refPrestataire;
	}
	public void setRefPrestataire(PrestataireVO refPrestataire) {
		this.refPrestataire = refPrestataire;
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
	public String getDateMission() {
		return dateMission;
	}
	public void setDateMission(String dateMission) {
		this.dateMission = dateMission;
	}
	public EtatMissionVO getRefEtatMission() {
		return refEtatMission;
	}
	public void setRefEtatMission(EtatMissionVO refEtatMission) {
		this.refEtatMission = refEtatMission;
	}
	public String getDateEtatMission() {
		return dateEtatMission;
	}
	public void setDateEtatMission(String dateEtatMission) {
		this.dateEtatMission = dateEtatMission;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public String getReponseRecu() {
		return reponseRecu;
	}
	public void setReponseRecu(String reponseRecu) {
		this.reponseRecu = reponseRecu;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public String getDateReponse() {
		return dateReponse;
	}
	public void setDateReponse(String dateReponse) {
		this.dateReponse = dateReponse;
	}
	public String getMontantFacture() {
		return montantFacture;
	}
	public void setMontantFacture(String montantFacture) {
		this.montantFacture = montantFacture;
	}
	public String getMontantAPaye() {
		return montantAPaye;
	}
	public void setMontantAPaye(String montantAPaye) {
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
	public void setRefSinistre(SinistreVO refSinistre) {
		this.refSinistre = refSinistre;
	}
	public SinistreVO getRefSinistre() {
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