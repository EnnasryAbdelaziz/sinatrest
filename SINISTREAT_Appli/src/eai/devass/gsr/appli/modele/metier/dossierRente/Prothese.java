package eai.devass.gsr.appli.modele.metier.dossierRente;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.historique.HistoryItem;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.modele.IHistorisable;
import eai.devass.gsr.appli.manager.metier.dossierRente.ProtheseFactory;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.CentreProthese;
import eai.devass.gsr.appli.modele.parametrage.EtatProthese;
import eai.devass.gsr.appli.modele.parametrage.NatureProthese;

/**
 * Prothese: Protheses;Protheses
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.dossierRente.ProtheseVO")
public class Prothese extends EntiteBO implements IEntite, Serializable,IHistorisable {

	private long id;
	private static final long serialVersionUID = 1L;
	private List versionsChildren;

	/**
	 * natureProthese Nature Prothèse
	 */
	@Indexation(label = "natureProthese", analyzed = false)
	private NatureProthese refNatureProthese;
	/**
	 * reserveProthese Réserve Prothèse
	 */
	@Indexation(label = "reserveProthese", analyzed = false)
	private Double reserveProthese;
	
	/**
	 * reserveProthese Réserve Prothèse
	 */
	@Indexation(label = "ancienneReserveProthese", analyzed = false)
	private transient Double ancienneReserveProthese;

//	/**
//	 * etatProthese Etat Prothèse
//	 */
//	@Indexation(label = "etatProthese", analyzed = false)
//	private Double etatProthese;
	/**
	 * dateEtat Date Etat Prothèse
	 */
	@Indexation(label = "dateEtat", analyzed = false)
	private Calendar dateEtat;
	/**
	 * validation Validation Prothèse
	 */
	@Indexation(label = "validation", analyzed = false)
	private Boolean validation;
	/**
	 * dateValidation Date Validation Prothèse
	 */
	@Indexation(label = "dateValidation", analyzed = false)
	private Calendar dateValidation;
//	/**
//	 * centreProthese Centre Prothese
//	 */
//	@Indexation(label = "centreProthese", analyzed = false)
//	private Double centreProthese;
	/**
	 * Date de prothèse Date Prothèse
	 */
	@Indexation(label = "Date de prothèse", analyzed = false)
	private Calendar dateProthese;
	/**
	 * Montant Montant Prothèse
	 */
	@Indexation(label = "Montant", analyzed = false)
	private Double montantProthese;
	/**
	 * idProthese Identification Prothèse
	 */
	@Indexation(label = "idProthese", analyzed = false)
	private Long idProthese;

	/**
	 * numeroProthese Numéro Prothèse
	 */
	@Indexation(label = "numeroProthese", analyzed = false)
	private Long numeroProthese;
	/**
	 * dateCreation
	 */
	@Indexation(label = "dateCreation", analyzed = false)
	private Calendar dateCreation;

	/**
	 * montantEstime Montant Estime Prothèse
	 */
	@Indexation(label = "montantEstime", analyzed = false)
	private Boolean montantEstime;
	/**
	 * montant de frais d'appareillage
	 */
	@Indexation(label = "mntFraisAppareillage", analyzed = false)
	private Double mntFraisAppareillage;

	/**
	 * @return the mntFraisAppareillage
	 */
	public Double getMntFraisAppareillage() {
		return mntFraisAppareillage;
	}

	/**
	 * @param mntFraisAppareillage the mntFraisAppareillage to set
	 */
	public void setMntFraisAppareillage(Double mntFraisAppareillage) {
		this.mntFraisAppareillage = mntFraisAppareillage;
	}

	private Rentier refRentier;
	
	private String statut;
	
	
	/**
	 *Id Mouvement Proth�se
	 */
	@Indexation(label = "MvtProthese", analyzed = false)
	private MvtProthese refMvtProthese;

	
	/**
	 *Id Quittance GSR
	 */
	@Indexation(label = "refQuittanceGSR", analyzed = false)
	private QuittanceGsr refQuittanceGSR;
	
	
	
	/**
	 * Id centre prothese
	 */
	@Indexation(label = "refCentreProthese", analyzed = false)
	private CentreProthese refCentreProthese;
	
	/**
	 * Id Etat prothese
	 */
	@Indexation(label = "refEtatProthese", analyzed = false)
	private EtatProthese refEtatProthese;

	
	/**
	 * @return the refCentreProthese
	 */
	public CentreProthese getRefCentreProthese() {
		return refCentreProthese;
	}

	/**
	 * @param refCentreProthese the refCentreProthese to set
	 */
	public void setRefCentreProthese(CentreProthese refCentreProthese) {
		this.refCentreProthese = refCentreProthese;
	}

	/**
	 * @return the refEtatProthese
	 */
	public EtatProthese getRefEtatProthese() {
		return refEtatProthese;
	}

	/**
	 * @param refEtatProthese the refEtatProthese to set
	 */
	public void setRefEtatProthese(EtatProthese refEtatProthese) {
		this.refEtatProthese = refEtatProthese;
	}

	/**
	 * @return the refMvtProthese
	 */
	public MvtProthese getRefMvtProthese() {
		return refMvtProthese;
	}

	/**
	 * @param refMvtProthese the refMvtProthese to set
	 */
	public void setRefMvtProthese(MvtProthese refMvtProthese) {
		this.refMvtProthese = refMvtProthese;
	}

	/**
	 * @return the refQuittanceGSR
	 */
	public QuittanceGsr getRefQuittanceGSR() {
		return refQuittanceGSR;
	}

	/**
	 * @param refQuittanceGSR the refQuittanceGSR to set
	 */
	public void setRefQuittanceGSR(QuittanceGsr refQuittanceGSR) {
		this.refQuittanceGSR = refQuittanceGSR;
	}

	public String toString() {
		return String.valueOf(getId());
	}

	/**
	 * @return the idProthese
	 */
	public Long getIdProthese() {
		return idProthese;
	}

	/**
	 * @param idProthese the idProthese to set
	 */
	public void setIdProthese(Long idProthese) {
		this.idProthese = idProthese;
	}
	
	/**
	 * Methode qui retourne l' instance de la factory d'une entité
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new ProtheseFactory();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getReserveProthese() {
		return this.reserveProthese;
	}

	public void setReserveProthese(Double reserveProthese) {
		this.reserveProthese = reserveProthese;
	}

	public Calendar getDateEtat() {
		return this.dateEtat;
	}

	public void setDateEtat(Calendar dateEtat) {
		this.dateEtat = dateEtat;
	}

	public Boolean getValidation() {
		return this.validation;
	}

	public void setValidation(Boolean validation) {
		this.validation = validation;
	}

	public Calendar getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(Calendar dateValidation) {
		this.dateValidation = dateValidation;
	}

	public Calendar getDateProthese() {
		return this.dateProthese;
	}

	public void setDateProthese(Calendar dateProthese) {
		this.dateProthese = dateProthese;
	}

	public Double getMontantProthese() {
		return this.montantProthese;
	}

	public void setMontantProthese(Double montantProthese) {
		this.montantProthese = montantProthese;
	}

	

	/**
	 * @return the numeroProthese
	 */
	public Long getNumeroProthese() {
		return numeroProthese;
	}

	/**
	 * @param numeroProthese the numeroProthese to set
	 */
	public void setNumeroProthese(Long numeroProthese) {
		this.numeroProthese = numeroProthese;
	}

	public Calendar getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
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

	/**
	 * Methode qui retourne la liste des entités parentes d' un historisable
	 * 
	 * @returns La liste des entités parentes
	 */
	public List getParents() {
		return null;
	}

	/**
	 * Methode qui retourne la liste des entités fils d' un historisable
	 * 
	 * @returns La liste des entités fils
	 */
	public Map recupererChildren() {
		return null;
	}

	/**
	 * Methode qui ajoute les differentes versions des fils relatives à un une
	 * version d' un historisable
	 * 
	 * @param children
	 *            versions des fils d' une version l' historisable parent
	 */

	public void addChildren(List children) {
		this.versionsChildren = children;
	}

	/**
	 * Methode qui recupere les differentes versions des fils relatives à un
	 * une version d' un historisable
	 * 
	 * @returns children versions des fils d' une version
	 */
	public List recupererVersionChildren() {
		return versionsChildren;

	}

	public List getHistory() {
		return null;
	}

	public HistoryItem getLastHistoryItem() {
		return null;
	}

	public void historiser() {

	}

	public void historiser(Object arg0) {

	}

	public void setRefRentier(Rentier refRentier) {
		this.refRentier = refRentier;
	}

	public Rentier getRefRentier() {
		return refRentier;
	}

	public void setRefNatureProthese(NatureProthese refNatureProthese) {
		this.refNatureProthese = refNatureProthese;
	}

	public NatureProthese getRefNatureProthese() {
		return refNatureProthese;
	}

	public void setMontantEstime(Boolean montantEstime) {
		this.montantEstime = montantEstime;
	}

	public Boolean getMontantEstime() {
		return montantEstime;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	/**
	 * @return the ancienneReserveProthese
	 */
	public Double getAncienneReserveProthese() {
		return ancienneReserveProthese;
	}

	/**
	 * @param ancienneReserveProthese the ancienneReserveProthese to set
	 */
	public void setAncienneReserveProthese(Double ancienneReserveProthese) {
		this.ancienneReserveProthese = ancienneReserveProthese;
	}

}
