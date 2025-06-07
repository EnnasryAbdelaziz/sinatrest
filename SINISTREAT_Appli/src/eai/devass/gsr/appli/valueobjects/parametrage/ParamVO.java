package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;

public class ParamVO extends RechListeVO  {
	
	
private static final long serialVersionUID = 1L;
	
	@AConverter(propertyDist="id")
	private long id;
	

	@Indexation(label = "code", analyzed = false)
	@AConverter(propertyDist="code")
	private String code;
	
	@Indexation(label = "libelle", analyzed = false)
	@AConverter(propertyDist="libelle")
	private String libelle;
	
	@Indexation(label = "dateCreation", analyzed = false)
	@AConverter(propertyDist="dateCreation", pattern="yyyyMMdd")
	private String dateCreation;
	
	private String typeParentA;
	private String typeParentB;
	
	
	
	public ParamVO() {
		
	}
	public ParamVO(long id) {
		this.id = id;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getTypeParentA() {
		return typeParentA;
	}
	public void setTypeParentA(String typeParentA) {
		this.typeParentA = typeParentA;
	}
	public String getTypeParentB() {
		return typeParentB;
	}
	public void setTypeParentB(String typeParentB) {
		this.typeParentB = typeParentB;
	}
	
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

	
	
}
