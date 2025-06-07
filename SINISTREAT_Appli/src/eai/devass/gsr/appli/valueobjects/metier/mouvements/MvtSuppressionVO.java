package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;

/**
 * Value Object de MvtSuppression
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.mouvements.MvtSuppression")
public class MvtSuppressionVO extends MouvementVO implements ITracable {

	@AConverter(propertyDist = "mntIndu")
	private String mntIndu;
	@AConverter(propertyDist = "datSuppression", pattern = "yyyyMMdd")
	private String datSuppression;
	@AConverter(propertyDist = "motif")
	private String motif;

	// @Indexation(label = "refMotifSuppression", analyzed = false)
	// @AConverter(propertyDist = "motifSituationEtat.code")
	// private String refMotifSuppression;
	// @Indexation(label = "dateCreation", analyzed = false)
	// private String dateCreation;

	// @Indexation(label = "TypeAction", analyzed = false)
	// private String refTypeAction;
	private String refTypeActionLabel;
	private String typeParentA;
	private String typeParentB;

	public String getMntIndu() {
		return this.mntIndu;
	}

	public void setMntIndu(String mntIndu) {
		this.mntIndu = mntIndu;
	}

	public String getDatSuppression() {
		return this.datSuppression;
	}

	public void setDatSuppression(String datSuppression) {
		this.datSuppression = datSuppression;
	}

	public String getMotif() {
		return this.motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	// public String getDateCreation() {
	// return this.dateCreation;
	// }
	//
	// public void setDateCreation(String dateCreation) {
	// this.dateCreation = dateCreation;
	// }

	// public String getRefTypeAction() {
	// return this.refTypeAction;
	// }
	//
	// public void setRefTypeAction(String refTypeAction) {
	// this.refTypeAction = refTypeAction;
	// }

	public String getRefTypeActionLabel() {
		return this.refTypeActionLabel;
	}

	public void setRefTypeActionLabel(String refTypeActionLabel) {
		this.refTypeActionLabel = refTypeActionLabel;
	}

	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		//List listAttributes = new Vector();
		List listAttributes = new ArrayList(); 
		listAttributes.add("mntIndu");
		listAttributes.add("datSuppression");
		listAttributes.add("motif");
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

//	public void setRefMotifSuppression(String refMotifSuppression) {
//		this.refMotifSuppression = refMotifSuppression;
//	}
//
//	public String getRefMotifSuppression() {
//		return refMotifSuppression;
//	}

}
