package eai.devass.gsr.appli.modele.metier.dossierRente;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.impl.DeepDefaultConditionBuilder;
import ma.co.omnidata.framework.services.historique.HistoryItem;
import ma.co.omnidata.framework.services.historique.IHistorisable;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.historisation.ALazy;
import eai.devass.gsr.appli.manager.metier.dossierRente.DossierRenteFactory;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.MotifSituationEtat;
import eai.devass.gsr.appli.modele.parametrage.SituationDossierRentier;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.MotifEtat;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;

/**
 * Dossier Rente: DossierRente;DossierRente
 * 
 * @author Nom Prenom (email)
 */
public class DossierRente implements IEntite, ILockable, IHistorisable {

	private long id;
	private static final long serialVersionUID = 1L;
	private List versionsChildren;

	/**
	 * Compagnie rente compagnieRente;compagnierente
	 */
	@Indexation(label = "Compagnie rente", analyzed = false)
	private String compagnieRente;
	/**
	 * N° Rente numeroRente;numerorente
	 */
	@Indexation(label = "N° Rente", analyzed = false)
	private Long numeroRente;
	/**
	 * idDossierRente Identification Dossier Rente
	 */
	@Indexation(label = "idDossierRente", analyzed = false)
	private Double idDossierRente;
	
	/**
	 * etat Etat Dossier Rente
	 */
	private EtatRentier refEtatRentier;
	
	/**
	 * dateEtat
	 */
	@Indexation(label = "dateEtat", analyzed = false)
	private Calendar dateEtat;
	/**
	 * dateValidation date validation
	 */
	@Indexation(label = "dateValidation", analyzed = false)
	private Calendar dateValidation;
	/**
	 * validation Validation
	 */
	@Indexation(label = "validation", analyzed = false)
	private Boolean validation;
	/**
	 * dateCreation
	 */
	@Indexation(label = "dateCreation", analyzed = false)
	private Calendar dateCreation;

	/**
	 * EVO Lot1
	 */
	private String userSasCreateur;
	private String userSasModificateur;
	private Calendar dateModification;
	/**
	 * Fin EVO
	 */
	
	private Sinistre refSinistre;
	@ALazy(lazy=false)
	private List<Rentier> refsRentier;

	private transient String dateCreationDebut;

	private transient String dateCreationFin;
	private List<SituationDossierRentier> refSituationDossierRentier;

	//Evo lot2
	private Integer numeroOrdre;
	
	
	
	public DossierRente() {
		
	}
	

	public DossierRente(long id) {
		this.id = id;
	}


	public String toString() {
		return String.valueOf(getId());
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entité
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new DossierRenteFactory();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompagnieRente() {
		return this.compagnieRente;
	}

	public void setCompagnieRente(String compagnieRente) {
		this.compagnieRente = compagnieRente;
	}

	public Long getNumeroRente() {
		return this.numeroRente;
	}

	public void setNumeroRente(Long numeroRente) {
		this.numeroRente = numeroRente;
	}

	public Double getIdDossierRente() {
		return this.idDossierRente;
	}

	public void setIdDossierRente(Double idDossierRente) {
		this.idDossierRente = idDossierRente;
	}

//	public Long getEtat() {
//		return this.etat;
//	}
//
//	public void setEtat(long etat) {
//		this.etat = etat;
//	}
	
	

	public Calendar getDateEtat() {
		return this.dateEtat;
	}

	public EtatRentier getRefEtatRentier() {
		return refEtatRentier;
	}


	public void setRefEtatRentier(EtatRentier refEtatRentier) {
		this.refEtatRentier = refEtatRentier;
	}


	public void setDateEtat(Calendar dateEtat) {
		this.dateEtat = dateEtat;
	}

	public Calendar getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(Calendar dateValidation) {
		this.dateValidation = dateValidation;
	}

	public Boolean getValidation() {
		return this.validation;
	}

	public void setValidation(Boolean validation) {
		this.validation = validation;
	}

	public Calendar getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public List<Rentier> getRefsRentier() {
		return this.refsRentier;
	}

	public void setRefsRentier(List<Rentier> refsRentier) {
		this.refsRentier = refsRentier;
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
		IHistorisable c;
		Map histos = null;
		Iterator it2 = null;
		if (this.getId() != 0) {
			DossierRente dossierRente = new DossierRente();
			dossierRente.setId(this.getId());
			List<IHistorisable> rentierEditedList = null;
			Rentier rentierToEdit = new Rentier();
			rentierToEdit.setRefDossierRente(dossierRente);
			RentierManager rentierManager = new RentierManager();
			try {
				DeepDefaultConditionBuilder builderDef = new DeepDefaultConditionBuilder();
				rentierManager.setConditionBuilder(builderDef);
				rentierEditedList = (List) rentierManager
						.lookupEntite(rentierToEdit);
			} catch (Exception e) {
//				logger.error("probl�me technique",e);
			}

			if (rentierEditedList != null && !rentierEditedList.isEmpty()) {
				histos = new HashMap();
					it2 = rentierEditedList.iterator();
					if (it2 != null) {						
						while (it2.hasNext()) {
							c = (IHistorisable) it2.next();
							histos.put("refRentier" + c.getId(), c);
						}
					}				
			}
			
		}
			return histos;

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

	public void setRefSinistre(Sinistre refSinistre) {
		this.refSinistre = refSinistre;
	}

	public Sinistre getRefSinistre() {
		return refSinistre;
	}

	public String getDateCreationDebut() {
		return dateCreationDebut;
	}

	public void setDateCreationDebut(String dateCreationDebut) {
		this.dateCreationDebut = dateCreationDebut;
	}

	public String getDateCreationFin() {
		return dateCreationFin;
	}

	public void setDateCreationFin(String dateCreationFin) {
		this.dateCreationFin = dateCreationFin;
	}

	public Integer getNumeroOrdre() {
		return numeroOrdre;
	}

	public void setNumeroOrdre(Integer numeroOrdre) {
		this.numeroOrdre = numeroOrdre;
	}

	public void setUserSasCreateur(String userSasCreateur) {
		this.userSasCreateur = userSasCreateur;
	}

	public String getUserSasCreateur() {
		return userSasCreateur;
	}

	public void setUserSasModificateur(String userSasModificateur) {
		this.userSasModificateur = userSasModificateur;
	}

	public String getUserSasModificateur() {
		return userSasModificateur;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public List<SituationDossierRentier> getRefSituationDossierRentier() {
		return refSituationDossierRentier;
	}

	public void setRefSituationDossierRentier(
			List<SituationDossierRentier> refSituationDossierRentier) {
		this.refSituationDossierRentier = refSituationDossierRentier;
	}
	
	public SituationDossierRentier getCurSituationDossierRentier(EtatRente etatRente, 
			MotifEtat motifSituation) {
		
		this.setRefEtatRentier(new EtatRentier(etatRente.getCode()));
		this.setDateEtat(new GregorianCalendar());
		SituationDossierRentier situationDossierRentier = new SituationDossierRentier();
		situationDossierRentier.setDateEtat(new Date());
		situationDossierRentier.setRefEtatRentier(new EtatRentier(etatRente.getCode()));
		situationDossierRentier.setRefDossierRente(this);
		
		// Motif situatio etat
		if(motifSituation != null) {
			MotifSituationEtat motifSituationEtat = new MotifSituationEtat();
			motifSituationEtat.setCode(motifSituation.getCode());
			situationDossierRentier.setRefMotifSituationEtat(motifSituationEtat);
		}
		return situationDossierRentier;
	}
	
}
