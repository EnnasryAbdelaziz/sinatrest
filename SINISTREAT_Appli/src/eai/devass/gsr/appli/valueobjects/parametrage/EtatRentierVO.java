package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;

/**
 * Value Object de EtatRentier
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteDist="eai.devass.gsr.appli.modele.parametrage.EtatRentier")
public class EtatRentierVO extends RechListeVO implements ITracable {
	
	@AConverter(propertyDist = "id")
	private long id;
	private static final long serialVersionUID = 1L;

	@Indexation(label = "code", analyzed = false)
	@AConverter(propertyDist = "code")
	private String code;
	@Indexation(label = "libelle", analyzed = false)
	@AConverter(propertyDist = "libelle")
	private String libelle;

	// @Indexation(label="dateCreation",analyzed=false)
	// private String dateCreation;
	private String typeEtat;
	private String typeParentA;
	private String typeParentB;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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
		listAttributes.add("dateCreation");
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this, listAttributes);
		} catch (Exception e) {
			// logger.error("problème technique",e);
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

	public String getTypeEtat() {
		return typeEtat;
	}

	public void setTypeEtat(String typeEtat) {
		this.typeEtat = typeEtat;
	}

}
