package eai.devass.gsr.appli.valueobjects.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.valueobjects.parametrage.CentreProtheseVO;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatProtheseVO;
import eai.devass.gsr.appli.valueobjects.parametrage.NatureProtheseVO;

/**
 * Value Object de Protheses
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteDist="eai.devass.gsr.appli.modele.metier.dossierRente.Prothese")
public class ProtheseVO extends RechListeVO implements IValueObject,ITracable {

	@AConverter(propertyDist="id")
	private String id;
	private static final long serialVersionUID = 1L;

	// Historique
	@Indexation(label = "idHistorisable", analyzed = false)
	@AConverter(propertyDist="idHistorisable")
	private long idHistorisable;
	@Indexation(label = "numVersion", analyzed = false)
	@AConverter(propertyDist="numVersion")
	private long numVersion;
	@Indexation(label = "utilisateurVersion", analyzed = false)
	@AConverter(propertyDist="utilisateurVersion")
	private String utilisateurVersion;
	@Indexation(label = "dateVersion", analyzed = false)
	@AConverter(propertyDist="dateVersion")
	private String dateVersion;
	@Indexation(label = "reserveProthese", analyzed = false)
	@AConverter(propertyDist="reserveProthese")
	private String reserveProthese;
		
	/**
	 * Id Etat prothese
	 */
	@Indexation(label = "refEtatProthese", analyzed = false)
	@AConverter(propertyDist="refEtatProthese")
	private EtatProtheseVO refEtatProthese;
	@Indexation(label = "dateEtat", analyzed = false)
	@AConverter(propertyDist="dateEtat")
	private String dateEtat;
	@Indexation(label = "validation", analyzed = false)
	@AConverter(propertyDist="validation")
	private String validation;
	@Indexation(label = "dateValidation", analyzed = false)
	@AConverter(propertyDist="dateValidation")
	private String dateValidation;
//	@Indexation(label = "centreProthese", analyzed = false)
//	@AConverter(propertyDist = "centreProthese")
//	private String centreProthese;
	@Indexation(label = "Date de prothèse", analyzed = false)
	@AConverter(propertyDist="dateProthese")
	private String dateProthese;
	@Indexation(label = "Montant", analyzed = false)
	@AConverter(propertyDist="montantProthese")
	private String montantProthese;
	@Indexation(label = "idProthese", analyzed = false)
	@AConverter(propertyDist="idProthese")
	private String idProthese;
	@Indexation(label = "numeroProthese", analyzed = false)
	@AConverter(propertyDist="numeroProthese")
	private String numeroProthese;
	@Indexation(label = "montantEstime", analyzed = false)
	@AConverter(propertyDist="montantEstime")
	private String montantEstime;
	@Indexation(label = "dateCreation", analyzed = false)
	@AConverter(propertyDist="dateCreation")
	private String dateCreation;
	@Indexation(label = "mntFraisAppareillage", analyzed = false)
	@AConverter(propertyDist="mntFraisAppareillage")
	private String mntFraisAppareillage;
	@AConverter(propertyDist="ancienneReserveProthese")
	private String ancienneReserveProthese;
	
	private String typeParentA;
	private String typeParentB;
	@AConverter(propertyDist="statut")
	private String statut;
	
	@AConverter(propertyDist="refMvtProthese.id")
	private String refMvtProthese;
	
	@Indexation(label = "natureProthese", analyzed = false)
	@AConverter(propertyDist="refNatureProthese")
	private NatureProtheseVO refNatureProthese;
	
	/**
	 *Id Quittance GSR
	 */
	@AConverter(propertyDist="refQuittanceGSR.id")
	private String refQuittanceGSR;
	
	
	
	
	/**
	 * Id centre prothese
	 */
	@Indexation(label = "CentreProthese", analyzed = false)
	@AConverter(propertyDist="refCentreProthese")
	private CentreProtheseVO refCentreProthese;
	
	/**
	 * @return the refNatureProthese
	 */
	public NatureProtheseVO getRefNatureProthese() {
		return refNatureProthese;
	}

	/**
	 * @param refNatureProthese the refNatureProthese to set
	 */
	public void setRefNatureProthese(NatureProtheseVO refNatureProthese) {
		this.refNatureProthese = refNatureProthese;
	}

	/**
	 * @return the refCentreProthese
	 */
	public CentreProtheseVO getRefCentreProthese() {
		return refCentreProthese;
	}

	/**
	 * @param refCentreProthese the refCentreProthese to set
	 */
	public void setRefCentreProthese(CentreProtheseVO refCentreProthese) {
		this.refCentreProthese = refCentreProthese;
	}

	
	/**
	 * @return the refEtatProthese
	 */
	public EtatProtheseVO getRefEtatProthese() {
		return refEtatProthese;
	}

	/**
	 * @param refEtatProthese the refEtatProthese to set
	 */
	public void setRefEtatProthese(EtatProtheseVO refEtatProthese) {
		this.refEtatProthese = refEtatProthese;
	}

	/**
	 * @return the refMvtProthese
	 */
	public String getRefMvtProthese() {
		return refMvtProthese;
	}

	/**
	 * @param refMvtProthese the refMvtProthese to set
	 */
	public void setRefMvtProthese(String refMvtProthese) {
		this.refMvtProthese = refMvtProthese;
	}

	/**
	 * @return the refQuittanceGSR
	 */
	public String getRefQuittanceGSR() {
		return refQuittanceGSR;
	}

	/**
	 * @param refQuittanceGSR the refQuittanceGSR to set
	 */
	public void setRefQuittanceGSR(String refQuittanceGSR) {
		this.refQuittanceGSR = refQuittanceGSR;
	}



	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReserveProthese() {
		return this.reserveProthese;
	}

	public void setReserveProthese(String reserveProthese) {
		this.reserveProthese = reserveProthese;
	}

	public String getDateEtat() {
		return this.dateEtat;
	}

	public void setDateEtat(String dateEtat) {
		this.dateEtat = dateEtat;
	}

	public String getValidation() {
		return this.validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(String dateValidation) {
		this.dateValidation = dateValidation;
	}

//	public String getCentreProthese() {
//		return this.centreProthese;
//	}
//
//	public void setCentreProthese(String centreProthese) {
//		this.centreProthese = centreProthese;
//	}

	public String getDateProthese() {
		return this.dateProthese;
	}

	public void setDateProthese(String dateProthese) {
		this.dateProthese = dateProthese;
	}

	public String getMontantProthese() {
		return this.montantProthese;
	}

	public void setMontantProthese(String montantProthese) {
		this.montantProthese = montantProthese;
	}

	public String getIdProthese() {
		return this.idProthese;
	}

	public void setIdProthese(String idProthese) {
		this.idProthese = idProthese;
	}

	public String getNumeroProthese() {
		return this.numeroProthese;
	}

	public void setNumeroProthese(String numeroProthese) {
		this.numeroProthese = numeroProthese;
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
		listAttributes.add("natureProthese");
		listAttributes.add("reserveProthese");
		listAttributes.add("etatProthese");
		listAttributes.add("dateEtat");
		listAttributes.add("validation");
		listAttributes.add("dateValidation");
		listAttributes.add("centreProthese");
		listAttributes.add("dateProthese");
		listAttributes.add("montantProthese");
		listAttributes.add("idProthese");
		listAttributes.add("numeroProthese");
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

	public void setMontantEstime(String montantEstime) {
		this.montantEstime = montantEstime;
	}

	public String getMontantEstime() {
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
	public String getAncienneReserveProthese() {
		return ancienneReserveProthese;
	}

	/**
	 * @param ancienneReserveProthese the ancienneReserveProthese to set
	 */
	public void setAncienneReserveProthese(String ancienneReserveProthese) {
		this.ancienneReserveProthese = ancienneReserveProthese;
	}

	/**
	 * @return the mntFraisAppareillage
	 */
	public String getMntFraisAppareillage() {
		return mntFraisAppareillage;
	}

	/**
	 * @param mntFraisAppareillage the mntFraisAppareillage to set
	 */
	public void setMntFraisAppareillage(String mntFraisAppareillage) {
		this.mntFraisAppareillage = mntFraisAppareillage;
	}

}
