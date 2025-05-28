package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;

/**
 * Value Object de EtatRentier
 * 
 * @author AZAS (email)
 */
public class NationaliteVO extends RechListeVO implements ITracable {

	private long code;
	private static final long serialVersionUID = 1L;

	private String libelle;

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	// @Override
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("code");
		listAttributes.add("libelle");
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
