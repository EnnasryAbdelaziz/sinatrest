package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;

import org.apache.log4j.Logger;

/**
 * Value Object de Societe
 * 
 * @author Nom Prenom (email)
 */
public class SocieteVO extends RechListeVO implements ITracable {

	private String id;
	private static final long serialVersionUID = 1L;
	private Logger logger= Logger.getLogger("loggerSINAT");
	@Indexation(label = "code", analyzed = false)
	private String code;
	@Indexation(label = "libelle", analyzed = false)
	private String libelle;
	// @Indexation(label = "dateCreation", analyzed = false)
	// private String dateCreation;
	private String refSociete;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void setRefSociete(String refSociete) {
		this.refSociete = refSociete;
	}

	public String getRefSociete() {
		return refSociete;
	}

	// public String getDateCreation() {
	// return this.dateCreation;
	// }
	//
	// public void setDateCreation(String dateCreation) {
	// this.dateCreation = dateCreation;
	// }
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("code");
		listAttributes.add("libelle");
		// listAttributes.add("dateCreation");
		listAttributes.add("refSociete");

		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this, listAttributes);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return rslt;
	}

}
