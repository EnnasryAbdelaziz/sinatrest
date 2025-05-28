package eai.devass.gsr.appli.modele.metier.mouvements;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;

/**
 * MvtCnraReglementaire:
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtCnraReglementaireVO")
public class MvtCnraReglementaire extends Mouvement 
			implements IEntite, IMvtSortant {

	private static final long serialVersionUID = 1L;
	/**
	 * Type de Consignation
	 */
	private String refTypeConsignation;
	/**
	 * Type de Revision
	 */
	private String refTypeRevision;
	/**
	 * Nature Decision 
	 */
	private String refNatureDecision;
	/**
	 * Numéro dossier Tribunal
	 */
	private String numDossierTribunal;
	/**
	 * Numéro Jugement 
	 */
	private String numJugementPv;
	/**
	 * Date jugement/Conciliation
	 */
	private Calendar datJugementPv;

	/**
	 * Date notification
	 */
	private Calendar datNotification;
	/**
	 * Code Juridication
	 */
	private String juridiction;
	/**
	 * Libelle juridication
	 */
	private String libelleJuridiction;
	/**
	 * Date Prise en charge
	 */	
	private Calendar datLimtePaiement;
	/**
	 * Date service à la CNRA
	 */	
	private Date datServiceCNRA;
	/**
	 * Prorata CNRA
	 */	
	private Double prorataCNRA;
	/**
	 * Liste Rentier
	 */	
	private transient List<Rentier> listRentier;
	/**
	 * Quote Part
	 */	
	private long quotePart;
	/**
	 * Montant Capital Du 
	 */	
	private Double mntCapitalDu;
	/**
	 * Nombre Trimestre a regler
	 */	
	private long nbrTrimestreAregler; 
	/**
	 * Montant Arrerege
	 */	
	private Double mntArrerage ; 
	/**
	 * Montant reliquat/Complement
	 */	
	private Double mntReliquat ; 
///////////////////////////////////////////////////////
	
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
	
	
	



	/**
	 * Procédure de consignation réglementaire 
	 */
	private Boolean procConsignRegl;
	

	//WMOS 03/11/2015: Fin Evo
	
	/* Evolution Mouvement CNRA Reglementaire */

	

	public Date getDatServiceCNRA() {
		return datServiceCNRA;
	}

	public void setDatServiceCNRA(Date datServiceCNRA) {
		this.datServiceCNRA = datServiceCNRA;
	}
	
	


	public Double getProrataCNRA() {
		return prorataCNRA;
	}

	public void setProrataCNRA(Double prorataCNRA) {
		this.prorataCNRA = prorataCNRA;
	}

	public long getQuotePart() {
		return quotePart;
	}

	public void setQuotePart(long quotePart) {
		this.quotePart = quotePart;
	}

	public Double getMntCapitalDu() {
		return mntCapitalDu;
	}

	public void setMntCapitalDu(Double mntCapitalDu) {
		this.mntCapitalDu = mntCapitalDu;
	}

	public long getNbrTrimestreAregler() {
		return nbrTrimestreAregler;
	}

	public void setNbrTrimestreAregler(long nbrTrimestreAregler) {
		this.nbrTrimestreAregler = nbrTrimestreAregler;
	}
	
	public Double getMntArrerage() {
		return mntArrerage;
	}

	public void setMntArrerage(Double mntArrerage) {
		this.mntArrerage = mntArrerage;
	}
	public Double getMntReliquat() {
		return mntReliquat;
	}

	public void setMntReliquat(Double mntReliquat) {
		this.mntReliquat = mntReliquat;
	}

	public String getRefTypeConsignation() {
		return refTypeConsignation;
	}

	public void setRefTypeConsignation(String refTypeConsignation) {
		this.refTypeConsignation = refTypeConsignation;
	}

	public String getRefTypeRevision() {
		return refTypeRevision;
	}

	public void setRefTypeRevision(String refTypeRevision) {
		this.refTypeRevision = refTypeRevision;
	}

	public String getRefNatureDecision() {
		return refNatureDecision;
	}

	public void setRefNatureDecision(String refNatureDecision) {
		this.refNatureDecision = refNatureDecision;
	}
	/*Fin*/
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
