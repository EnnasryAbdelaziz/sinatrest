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
 * Value Object de Certificats
 * 
 * @author Nom Prenom (email)
 */
public class CertificatsVO extends RechListeVO implements ITracable {

	private long id;
	private static final long serialVersionUID = 1L;

	@Indexation(label = "Date certificat", analyzed = false)
	private String dateReception;
	@Indexation(label = "Nom médecin", analyzed = false)
	private String observation;
	@Indexation(label = "Type certificat", analyzed = false)
	private String typeCertificat;
	@Indexation(label = "idCertificat", analyzed = false)
	private String idCertificat;
	@Indexation(label = "numeroCertificat", analyzed = false)
	private String numeroCertificat;
	@Indexation(label = "periodeDU", analyzed = false)
	private String periodeDU;
	@Indexation(label = "periodeAU", analyzed = false)
	private String periodeAU;
	@Indexation(label = "etat", analyzed = false)
	private String etat;
	@Indexation(label = "dateEtat", analyzed = false)
	private String dateEtat;
	@Indexation(label = "validation", analyzed = false)
	private String validation;
	@Indexation(label = "dateValidation", analyzed = false)
	private String dateValidation;
	@Indexation(label = "dateCreation", analyzed = false)
	private String dateCreation;
	private String typeParentA;
	private String typeParentB;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDateReception() {
		return this.dateReception;
	}

	public void setDateReception(String dateReception) {
		this.dateReception = dateReception;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getTypeCertificat() {
		return this.typeCertificat;
	}

	public void setTypeCertificat(String typeCertificat) {
		this.typeCertificat = typeCertificat;
	}

	public String getIdCertificat() {
		return this.idCertificat;
	}

	public void setIdCertificat(String idCertificat) {
		this.idCertificat = idCertificat;
	}

	public String getNumeroCertificat() {
		return this.numeroCertificat;
	}

	public void setNumeroCertificat(String numeroCertificat) {
		this.numeroCertificat = numeroCertificat;
	}

	public String getPeriodeDU() {
		return this.periodeDU;
	}

	public void setPeriodeDU(String periodeDU) {
		this.periodeDU = periodeDU;
	}

	public String getPeriodeAU() {
		return this.periodeAU;
	}

	public void setPeriodeAU(String periodeAU) {
		this.periodeAU = periodeAU;
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

	public String getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("dateReception");
		listAttributes.add("observation");
		listAttributes.add("typeCertificat");
		listAttributes.add("idCertificat");
		listAttributes.add("numeroCertificat");
		listAttributes.add("periodeDU");
		listAttributes.add("periodeAU");
		listAttributes.add("etat");
		listAttributes.add("dateEtat");
		listAttributes.add("validation");
		listAttributes.add("dateValidation");
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

}
