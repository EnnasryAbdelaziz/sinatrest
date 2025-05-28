package eai.devass.gsr.appli.valueobjects.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;

/**
 * Value Object de DossierRenteHisto
 * 
 * @author Mossâb wassim
 */
public class DossierRenteHistoVO extends RechListeVO implements ITracable {

	private String id;
	private static final long serialVersionUID = 1L;

	// Historique
	@Indexation(label = "idHistorisable", analyzed = false)
	private long idHistorisable;
	@Indexation(label = "numVersion", analyzed = false)
	private long numVersion;
	@Indexation(label = "utilisateurVersion", analyzed = false)
	private String utilisateurVersion;
	@Indexation(label = "dateVersion", analyzed = false)
	private String dateVersion;

	@Indexation(label = "Compagnie rente", analyzed = false)
	private String compagnieRente;
	@Indexation(label = "NÂ° Rente", analyzed = false)
	private String numeroRente;
	@Indexation(label = "idDossierRente", analyzed = false)
	private String idDossierRente;
	@Indexation(label = "etat", analyzed = false)
	private String etat;
	@Indexation(label = "dateEtat", analyzed = false)
	private String dateEtat;
	@Indexation(label = "dateValidation", analyzed = false)
	private String dateValidation;
	@Indexation(label = "validation", analyzed = false)
	private String validation;
	@Indexation(label = "dateCreation", analyzed = false)
	private String dateCreation;

	/**
	 * EVO Lot1
	 */
	private String userSasCreateur;
	private String userSasModificateur;
	private String dateModification;
	/**
	 * Fin EVO
	 */

	@Indexation(label = "DossierSinistre", analyzed = false)
	private String typeParentA;
	private String typeParentB;
	private String refSinistre;
	private String idRente;
	private String dateCreationDebut;
	private String dateCreationFin;

	public void setRefSinistre(String refSinistre) {
		this.refSinistre = refSinistre;
	}

	public String getRefSinistre() {
		return refSinistre;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompagnieRente() {
		return this.compagnieRente;
	}

	public void setCompagnieRente(String compagnieRente) {
		this.compagnieRente = compagnieRente;
	}

	public String getNumeroRente() {
		return this.numeroRente;
	}

	public void setNumeroRente(String numeroRente) {
		this.numeroRente = numeroRente;
	}

	public String getIdDossierRente() {
		return this.idDossierRente;
	}

	public void setIdDossierRente(String idDossierRente) {
		this.idDossierRente = idDossierRente;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDateEtat() {
		return this.dateEtat;
	}

	public void setDateEtat(String dateEtat) {
		this.dateEtat = dateEtat;
	}

	public String getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(String dateValidation) {
		this.dateValidation = dateValidation;
	}

	public String getValidation() {
		return this.validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("compagnieRente");
		listAttributes.add("numeroRente");
		listAttributes.add("idDossierRente");
		listAttributes.add("etat");
		listAttributes.add("dateEtat");
		listAttributes.add("dateValidation");
		listAttributes.add("validation");
		listAttributes.add("dateCreation");
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this, listAttributes);
		} catch (Exception e) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e.getMessage());

		}
		return rslt;
	}

	public String getTypeParentA() {
		return typeParentA;
	}

	public void setTypeParentA(String typeParentA) {
		this.typeParentA = typeParentA;
		this.typeParentB = typeParentA;
	}

	public String getTypeParentB() {
		return typeParentB;
	}

	public void setTypeParentB(String typeParentB) {
		this.typeParentB = typeParentB;
	}

	// Historique
	public long getNumVersion() {
		return numVersion;
	}

	public void setNumVersion(long numVersion) {
		this.numVersion = numVersion;
	}

	public String getUtilisateurVersion() {
		return utilisateurVersion;
	}

	public void setUtilisateurVersion(String utilisateurVersion) {
		this.utilisateurVersion = utilisateurVersion;
	}

	public String getDateVersion() {
		return dateVersion;
	}

	public void setDateVersion(String dateVersion) {
		this.dateVersion = dateVersion;
	}

	public long getIdHistorisable() {
		return idHistorisable;
	}

	public void setIdHistorisable(long idHistorisable) {
		this.idHistorisable = idHistorisable;
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

	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}

	public String getDateModification() {
		return dateModification;
	}

	public void setIdRente(String idRente) {
		this.idRente = idRente;
	}

	public String getIdRente() {
		return idRente;
	}

}
