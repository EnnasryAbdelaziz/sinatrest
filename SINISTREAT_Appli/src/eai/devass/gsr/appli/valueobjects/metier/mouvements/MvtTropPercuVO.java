
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import eai.devass.commun.appli.converter.AConverter;


/**
Value Object de MvtTropPercu
@author chak's
*/

@AConverter(entiteDist="eai.devass.gsr.appli.modele.metier.mouvements.MvtTropPercu")
public class MvtTropPercuVO extends  MouvementVO {

	private static final long serialVersionUID = 1L;

	@AConverter(propertyDist="dateDernierEcheanceRegle")
	private String dateDernierEcheanceRegle;
	
	@AConverter(propertyDist="dateDebutConstatation")
	private String dateDebutConstatation;
	
	@AConverter(propertyDist="dateFinConstatation")
	private String dateFinConstatation;
	
	@AConverter(propertyDist="totalTropPercu")
	private String totalTropPercu;
	
	@AConverter(propertyDist="totalTropPercuRecupere")
	private String totalTropPercuRecupere;
	
	@AConverter(propertyDist="modeRemboursement")
	private String modeRemboursement;
	
	@AConverter(propertyDist="totalTropPercuARecupere")
	private String totalTropPercuARecupere;
	
	@AConverter(propertyDist="mntReliquat")
	private String mntReliquat;
	
	@AConverter(propertyDist="mntRemiseBancaire")
	private String mntRemiseBancaire;
	
	@AConverter(propertyDist="mntAccord")
	private String mntAccord;
	
	@AConverter(propertyDist="mntAbondonner")
	private String mntAbondonner;
	
	@AConverter(propertyDist="pourcentPrelevement")
	private String pourcentPrelevement;
	
	@AConverter(propertyDist="numRemiseBancaire")
	private String numRemiseBancaire;
	
	@AConverter(propertyDist="action")
	private String action;
	
	@AConverter(propertyDist="typeRemboursement")
	private String typeRemboursement;
	
	@AConverter(propertyDist="dateRemiseBancaire")
	private String dateRemiseBancaire;
	
	@AConverter(propertyDist="dateDebutPrelevement")
	private String dateDebutPrelevement;
	
	@AConverter(propertyDist="dateFinPrelevement")
	private String dateFinPrelevement;
	
	@AConverter(propertyDist="dernierTrimestrePrelevement")
	private String dernierTrimestrePrelevement;
	
	@AConverter(propertyDist="mntPeriodiqueRecuperation")
	private String mntPeriodiqueRecuperation;
	
	@AConverter(propertyDist="mntRenteAVerser")
	private String mntRenteAVerser;
	
	@AConverter(propertyDist="mntProrata")
	private String mntProrata;
	
	@AConverter(propertyDist="nbrTrimestre")
	private String nbrTrimestre;
	
	public String getDateDernierEcheanceRegle() {
		return dateDernierEcheanceRegle;
	}
	public void setDateDernierEcheanceRegle(String dateDernierEcheanceRegle) {
		this.dateDernierEcheanceRegle = dateDernierEcheanceRegle;
	}
	public String getDateDebutConstatation() {
		return dateDebutConstatation;
	}
	public void setDateDebutConstatation(String dateDebutConstatation) {
		this.dateDebutConstatation = dateDebutConstatation;
	}
	public String getDateFinConstatation() {
		return dateFinConstatation;
	}
	public void setDateFinConstatation(String dateFinConstatation) {
		this.dateFinConstatation = dateFinConstatation;
	}
	public String getTotalTropPercu() {
		return totalTropPercu;
	}
	public void setTotalTropPercu(String totalTropPercu) {
		this.totalTropPercu = totalTropPercu;
	}
	public String getTotalTropPercuRecupere() {
		return totalTropPercuRecupere;
	}
	public void setTotalTropPercuRecupere(String totalTropPercuRecupere) {
		this.totalTropPercuRecupere = totalTropPercuRecupere;
	}
	public String getModeRemboursement() {
		return modeRemboursement;
	}
	public void setModeRemboursement(String modeRemboursement) {
		this.modeRemboursement = modeRemboursement;
	}
	
	public String getTotalTropPercuARecupere() {
		return totalTropPercuARecupere;
	}
	public void setTotalTropPercuARecupere(String totalTropPercuARecupere) {
		this.totalTropPercuARecupere = totalTropPercuARecupere;
	}
	public String getMntReliquat() {
		return mntReliquat;
	}
	public void setMntReliquat(String mntReliquat) {
		this.mntReliquat = mntReliquat;
	}
	public String getMntRemiseBancaire() {
		return mntRemiseBancaire;
	}
	public void setMntRemiseBancaire(String mntRemiseBancaire) {
		this.mntRemiseBancaire = mntRemiseBancaire;
	}
	public String getMntAccord() {
		return mntAccord;
	}
	public void setMntAccord(String mntAccord) {
		this.mntAccord = mntAccord;
	}
	public String getMntAbondonner() {
		return mntAbondonner;
	}
	public void setMntAbondonner(String mntAbondonner) {
		this.mntAbondonner = mntAbondonner;
	}
	public String getPourcentPrelevement() {
		return pourcentPrelevement;
	}
	public void setPourcentPrelevement(String pourcentPrelevement) {
		this.pourcentPrelevement = pourcentPrelevement;
	}
	public String getNumRemiseBancaire() {
		return numRemiseBancaire;
	}
	public void setNumRemiseBancaire(String numRemiseBancaire) {
		this.numRemiseBancaire = numRemiseBancaire;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTypeRemboursement() {
		return typeRemboursement;
	}
	public void setTypeRemboursement(String typeRemboursement) {
		this.typeRemboursement = typeRemboursement;
	}
	public String getDateRemiseBancaire() {
		return dateRemiseBancaire;
	}
	public void setDateRemiseBancaire(String dateRemiseBancaire) {
		this.dateRemiseBancaire = dateRemiseBancaire;
	}
	public String getDateDebutPrelevement() {
		return dateDebutPrelevement;
	}
	public void setDateDebutPrelevement(String dateDebutPrelevement) {
		this.dateDebutPrelevement = dateDebutPrelevement;
	}
	public String getDateFinPrelevement() {
		return dateFinPrelevement;
	}
	public void setDateFinPrelevement(String dateFinPrelevement) {
		this.dateFinPrelevement = dateFinPrelevement;
	}
	public String getDernierTrimestrePrelevement() {
		return dernierTrimestrePrelevement;
	}
	public void setDernierTrimestrePrelevement(String dernierTrimestrePrelevement) {
		this.dernierTrimestrePrelevement = dernierTrimestrePrelevement;
	}
	public String getMntPeriodiqueRecuperation() {
		return mntPeriodiqueRecuperation;
	}
	public void setMntPeriodiqueRecuperation(String mntPeriodiqueRecuperation) {
		this.mntPeriodiqueRecuperation = mntPeriodiqueRecuperation;
	}
	public String getMntRenteAVerser() {
		return mntRenteAVerser;
	}
	public void setMntRenteAVerser(String mntRenteAVerser) {
		this.mntRenteAVerser = mntRenteAVerser;
	}
	public String getMntProrata() {
		return mntProrata;
	}
	public void setMntProrata(String mntProrata) {
		this.mntProrata = mntProrata;
	}
	public String getNbrTrimestre() {
		return nbrTrimestre;
	}
	public void setNbrTrimestre(String nbrTrimestre) {
		this.nbrTrimestre = nbrTrimestre;
	}
	
	
	
	
	


}

