package eai.devass.gsr.appli.modele.metier.mouvements;
 
import java.util.Date;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;





/**
 MvtTropPercu:  
@author chak's
*/

@AConverter(entiteMapping="eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtTropPercuVO")
public class MvtTropPercu extends  Mouvement implements IEntite, ILockable          {

private static final long serialVersionUID = 1L;

	private Date dateDernierEcheanceRegle;
	private Date dateDebutConstatation;
	private Date dateFinConstatation;
	private Double totalTropPercu;
	private Double totalTropPercuRecupere;
	private Double totalTropPercuARecupere;
	private String modeRemboursement;	
	private Double mntReliquat;
	private Double mntRemiseBancaire;
	private Double mntAccord;
	private Double mntAbondonner;
	private Double pourcentPrelevement;
	private String numRemiseBancaire;
	private String action;
	private String typeRemboursement;
	private Date dateRemiseBancaire;
	private Date dateDebutPrelevement;
	private Date dateFinPrelevement;
	private String dernierTrimestrePrelevement;
	private Double mntPeriodiqueRecuperation;
	private Double mntRenteAVerser;
	private Double mntProrata;
	private Double nbrTrimestre;
	
	
	
	public Date getDateDernierEcheanceRegle() {
		return dateDernierEcheanceRegle;
	}
	public void setDateDernierEcheanceRegle(Date dateDernierEcheanceRegle) {
		this.dateDernierEcheanceRegle = dateDernierEcheanceRegle;
	}
	public Date getDateDebutConstatation() {
		return dateDebutConstatation;
	}
	public void setDateDebutConstatation(Date dateDebutConstatation) {
		this.dateDebutConstatation = dateDebutConstatation;
	}
	public Date getDateFinConstatation() {
		return dateFinConstatation;
	}
	public void setDateFinConstatation(Date dateFinConstatation) {
		this.dateFinConstatation = dateFinConstatation;
	}
	public Double getTotalTropPercu() {
		return totalTropPercu;
	}
	public void setTotalTropPercu(Double totalTropPercu) {
		this.totalTropPercu = totalTropPercu;
	}
	public Double getTotalTropPercuRecupere() {
		return totalTropPercuRecupere;
	}
	public void setTotalTropPercuRecupere(Double totalTropPercuRecupere) {
		this.totalTropPercuRecupere = totalTropPercuRecupere;
	}
	public String getModeRemboursement() {
		return modeRemboursement;
	}
	public void setModeRemboursement(String modeRemboursement) {
		this.modeRemboursement = modeRemboursement;
	}
	public Double getTotalTropPercuARecupere() {
		return totalTropPercuARecupere;
	}
	public void setTotalTropPercuARecupere(Double totalTropPercuARecupere) {
		this.totalTropPercuARecupere = totalTropPercuARecupere;
	}
	public Double getMntReliquat() {
		return mntReliquat;
	}
	public void setMntReliquat(Double mntReliquat) {
		this.mntReliquat = mntReliquat;
	}
	public Double getMntRemiseBancaire() {
		return mntRemiseBancaire;
	}
	public void setMntRemiseBancaire(Double mntRemiseBancaire) {
		this.mntRemiseBancaire = mntRemiseBancaire;
	}
	public Double getMntAccord() {
		return mntAccord;
	}
	public void setMntAccord(Double mntAccord) {
		this.mntAccord = mntAccord;
	}
	public Double getMntAbondonner() {
		return mntAbondonner;
	}
	public void setMntAbondonner(Double mntAbondonner) {
		this.mntAbondonner = mntAbondonner;
	}
	public Double getPourcentPrelevement() {
		return pourcentPrelevement;
	}
	public void setPourcentPrelevement(Double pourcentPrelevement) {
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
	public Date getDateRemiseBancaire() {
		return dateRemiseBancaire;
	}
	public void setDateRemiseBancaire(Date dateRemiseBancaire) {
		this.dateRemiseBancaire = dateRemiseBancaire;
	}
	public Date getDateDebutPrelevement() {
		return dateDebutPrelevement;
	}
	public void setDateDebutPrelevement(Date dateDebutPrelevement) {
		this.dateDebutPrelevement = dateDebutPrelevement;
	}
	public Date getDateFinPrelevement() {
		return dateFinPrelevement;
	}
	public void setDateFinPrelevement(Date dateFinPrelevement) {
		this.dateFinPrelevement = dateFinPrelevement;
	}
	public String getDernierTrimestrePrelevement() {
		return dernierTrimestrePrelevement;
	}
	public void setDernierTrimestrePrelevement(String dernierTrimestrePrelevement) {
		this.dernierTrimestrePrelevement = dernierTrimestrePrelevement;
	}
	public Double getMntPeriodiqueRecuperation() {
		return mntPeriodiqueRecuperation;
	}
	public void setMntPeriodiqueRecuperation(Double mntPeriodiqueRecuperation) {
		this.mntPeriodiqueRecuperation = mntPeriodiqueRecuperation;
	}
	public Double getMntRenteAVerser() {
		return mntRenteAVerser;
	}
	public void setMntRenteAVerser(Double mntRenteAVerser) {
		this.mntRenteAVerser = mntRenteAVerser;
	}
	public Double getMntProrata() {
		return mntProrata;
	}
	public void setMntProrata(Double mntProrata) {
		this.mntProrata = mntProrata;
	}
	public Double getNbrTrimestre() {
		return nbrTrimestre;
	}
	public void setNbrTrimestre(Double nbrTrimestre) {
		this.nbrTrimestre = nbrTrimestre;
	}
	
	
	
	
		
	
	
	

}

