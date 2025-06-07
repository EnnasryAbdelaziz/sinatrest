package eai.devass.gsr.appli.modele.metier.mouvements;

import java.util.Calendar;
import java.util.List;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;

/**
 * MvtConsignCNRA:
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtConsignCNRAVO")
public class MvtConsignCNRA extends Mouvement 
			implements IEntite, IMvtSortant {

	private static final long serialVersionUID = 1L;
	
	//Champs disponible sur IHM
	/**
	 * datLimtePaiement
	 */
	
	private Calendar datLimtePaiement;
	/**
	 * datRcptCNRA
	 */

	private Calendar datRcptCNRA;
	/**
	 * dateVersementCNRA
	 */

	private Calendar dateVersementCNRA;
	/**
	 * mntCNRA
	 */

	private Double mntCNRA;
	/**
	 * refDossierCNRA
	 */

	private String refDossierCNRA;
	/**
	 * avp
	 */
	private Boolean avp;
	
	
	
	private transient List<Rentier> listRentier;

	//WMOS 03/11/2015: début Evo [ajout des nouveaux champs besoin batch CNRA (XML)]
	/**
	 * Numéro jugement pv
	 */
	private String numJugementPv;
	/**
	 * Numéro dossier Tribunal
	 */
	private String numDossierTribunal;
	/**
	 * Date jugement pv
	 */
	private Calendar datJugementPv;
	/**
	 * Date notification
	 */
	private Calendar datNotification;
	/**
	 * Juridication
	 */
	private String juridiction;
	/**
	 * Procédure de consignation réglementaire 
	 */
	private Boolean procConsignRegl;
	
	/**
	 * Libelle juridication
	 */
	private String libelleJuridiction;
	//WMOS 03/11/2015: Fin Evo

	public String toString() {
		return super.toString();
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entitÃ©
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return null;
	}

	public Calendar getDatLimtePaiement() {
		return this.datLimtePaiement;
	}

	public void setDatLimtePaiement(Calendar datLimtePaiement) {
		this.datLimtePaiement = datLimtePaiement;
	}

	public Calendar getDatRcptCNRA() {
		return this.datRcptCNRA;
	}

	public void setDatRcptCNRA(Calendar datRcptCNRA) {
		this.datRcptCNRA = datRcptCNRA;
	}

	
	public Calendar getDateVersementCNRA() {
		return dateVersementCNRA;
	}

	public void setDateVersementCNRA(Calendar dateVersementCNRA) {
		this.dateVersementCNRA = dateVersementCNRA;
	}

	public Double getMntCNRA() {
		return this.mntCNRA;
	}

	public void setMntCNRA(Double mntCNRA) {
		this.mntCNRA = mntCNRA;
	}

	public String getRefDossierCNRA() {
		return this.refDossierCNRA;
	}

	public void setRefDossierCNRA(String refDossierCNRA) {
		this.refDossierCNRA = refDossierCNRA;
	}
	public Boolean getAvp() {
		return avp;
	}

	public void setAvp(Boolean avp) {
		this.avp = avp;
	}
	
	

	public String getNumJugementPv() {
		return numJugementPv;
	}

	public void setNumJugementPv(String numJugementPv) {
		this.numJugementPv = numJugementPv;
	}

	public String getNumDossierTribunal() {
		return numDossierTribunal;
	}

	public void setNumDossierTribunal(String numDossierTribunal) {
		this.numDossierTribunal = numDossierTribunal;
	}

	public Calendar getDatJugementPv() {
		return datJugementPv;
	}

	public void setDatJugementPv(Calendar datJugementPv) {
		this.datJugementPv = datJugementPv;
	}

	public Calendar getDatNotification() {
		return datNotification;
	}

	public void setDatNotification(Calendar datNotification) {
		this.datNotification = datNotification;
	}

	public String getJuridiction() {
		return juridiction;
	}

	public void setJuridiction(String juridiction) {
		this.juridiction = juridiction;
	}

	public List<Rentier> getListRentier() {
		return listRentier;
	}

	public void setListRentier(List<Rentier> listRentier) {
		this.listRentier = listRentier;
	}

	/**
	 * Methode qui retourne l' Id du lockable
	 * 
	 * @returns id du locakble
	 */
	public String getIdLockable() {
		return Long.toString(getId());
	}

	/**
	 * Methode qui retourne le type du lockable
	 * 
	 * @returns type du locakble
	 */
	public String getLockableType() {
		return this.getClass().getName();
	}

	public void setProcConsignRegl(Boolean procConsignRegl) {
		this.procConsignRegl = procConsignRegl;
	}

	public Boolean getProcConsignRegl() {
		return procConsignRegl;
	}

	public void setLibelleJuridiction(String libelleJuridiction) {
		this.libelleJuridiction = libelleJuridiction;
	}

	public String getLibelleJuridiction() {
		return libelleJuridiction;
	}

}
