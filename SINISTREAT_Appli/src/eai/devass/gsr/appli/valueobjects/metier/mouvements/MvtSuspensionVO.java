package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;

/**
 * Value Object de MvtSuspension
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.mouvements.MvtSuspension")
public class MvtSuspensionVO extends MouvementVO implements ITracable {

	@Indexation(label = "datSuspension", analyzed = false)
	@AConverter(propertyDist = "datSuspension")
	private String datSuspension;
	@Indexation(label = "mntRegle", analyzed = false)
	@AConverter(propertyDist = "mntRegle")
	private String mntRegle;
	@Indexation(label = "motif", analyzed = false)
	@AConverter(propertyDist = "motif")
	private String motif;	
	@Indexation(label="TypeCertificat",analyzed=false)
	@AConverter(propertyDist="refTypeCertificat.id")
	private String refTypeCertificat;
	
	@Indexation(label="TypeCertificat",analyzed=false)
	@AConverter(propertyDist="refTypeCertificat.libelle")
	private String libelleTypeCertificat;
	
	@Indexation(label="mntTropPercu",analyzed=false)
	@AConverter(propertyDist = "mntTropPercu")
	private String mntTropPercu;
	
	private String typeParentA;
	private String typeParentB;

	public String getDatSuspension() {
		return this.datSuspension;
	}

	public void setDatSuspension(String datSuspension) {
		this.datSuspension = datSuspension;
	}

	public String getMntRegle() {
		return this.mntRegle;
	}

	public void setMntRegle(String mntRegle) {
		this.mntRegle = mntRegle;
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
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("datSuspension");
		listAttributes.add("mntRegle");
		listAttributes.add("motif");
		//listAttributes.add("nouvMntRente");
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

	public String getRefTypeCertificat() {
		return refTypeCertificat;
	}

	public String getLibelleTypeCertificat() {
		return libelleTypeCertificat;
	}

	public void setLibelleTypeCertificat(String libelleTypeCertificat) {
		this.libelleTypeCertificat = libelleTypeCertificat;
	}

	public void setRefTypeCertificat(String refTypeCertificat) {
		this.refTypeCertificat = refTypeCertificat;
	}

	public String getMntTropPercu() {
		return mntTropPercu;
	}

	public void setMntTropPercu(String mntTropPercu) {
		this.mntTropPercu = mntTropPercu;
	}
}
