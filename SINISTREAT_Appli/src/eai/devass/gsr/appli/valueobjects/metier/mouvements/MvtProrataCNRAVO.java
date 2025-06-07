package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;

/**
 * Value Object de MvtProrataCNRA
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.mouvements.MvtProrataCNRA")
public class MvtProrataCNRAVO extends MvtConsignCNRAVO implements ITracable {

	@Indexation(label = "mntProrataCNRA", analyzed = false)
	@AConverter(propertyDist = "mntProrataCNRA")
	private String mntProrataCNRA;
	/**
	 * datePriseEnCharge
	 */
	@AConverter(propertyDist = "datePriseEnCharge", pattern = "yyyyMMdd")
	private String datePriseEnCharge;
	// @Indexation(label = "dateCreation", analyzed = false)
	// private String dateCreation;
	private String typeParentA;
	private String typeParentB;

	public String getMntProrataCNRA() {
		return this.mntProrataCNRA;
	}

	public void setMntProrataCNRA(String mntProrataCNRA) {
		this.mntProrataCNRA = mntProrataCNRA;
	}

	// public String getDateCreation() {
	// return this.dateCreation;
	// }
	//
	// public void setDateCreation(String dateCreation) {
	// this.dateCreation = dateCreation;
	// }

	public void setDatePriseEnCharge(String datePriseEnCharge) {
		this.datePriseEnCharge = datePriseEnCharge;
	}

	public String getDatePriseEnCharge() {
		return datePriseEnCharge;
	}

	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("mntProrataCNRA");
		listAttributes.add("dateCreation");
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this, listAttributes);
		} catch (Exception e) {
			logger.error("problème technique",e);
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
