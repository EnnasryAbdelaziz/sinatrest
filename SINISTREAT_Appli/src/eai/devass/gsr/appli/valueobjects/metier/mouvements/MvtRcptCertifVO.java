
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;


/**
Value Object de MvtRcptCertif
@author Nom Prenom (email)
*/
@AConverter(entiteDist="eai.devass.gsr.appli.modele.metier.mouvements.MvtRcptCertif")
public class MvtRcptCertifVO extends  MouvementVO  implements ITracable{





	@Indexation(label="datAu",analyzed=false) 
	@AConverter(propertyDist="datAu", pattern = "yyyyMMdd")
	private String datAu;
	
	@Indexation(label="datDu",analyzed=false) 
	@AConverter(propertyDist="datDu", pattern = "yyyyMMdd")
	private String datDu;
	
	@Indexation(label="datRcpt",analyzed=false) 
	@AConverter(propertyDist="datRcpt", pattern = "yyyyMMdd")
	private String datRcpt;
	
//	@Indexation(label="numCertificat",analyzed=false) 
//	@AConverter(propertyDist="numCertificat")
//	private String numCertificat;
	
//	@Indexation(label="observation",analyzed=false) 
//	@AConverter(propertyDist="observation")
//	private String observation;
//	@Indexation(label="dateCreation",analyzed=false) 
//	private String dateCreation;
	
	@Indexation(label="TypeCertificat",analyzed=false) 
	@AConverter(propertyDist="refTypeCertificat.id")
	private String refTypeCertificat ;
	
	@AConverter(propertyDist="refTypeCertificat.libelle")
	private String refTypeCertificatLabel ;
	
	private String typeParentA;
	private String typeParentB;
//	@AConverter(propertyDist="numOrderCertif")
//	private String numOrderCertif;
	
	



//	public String getNumOrderCertif() {
//		return numOrderCertif;
//	}
//
//	public void setNumOrderCertif(String numOrderCertif) {
//		this.numOrderCertif = numOrderCertif;
//	}

	public String getDatAu() {
		return this.datAu;
	}

	public void setDatAu(String datAu) {
		this.datAu = datAu;
	}
	public String getDatDu() {
		return this.datDu;
	}

	public void setDatDu(String datDu) {
		this.datDu = datDu;
	}
	public String getDatRcpt() {
		return this.datRcpt;
	}

	public void setDatRcpt(String datRcpt) {
		this.datRcpt = datRcpt;
	}
//	public String getNumCertificat() {
//		return this.numCertificat;
//	}
//
//	public void setNumCertificat(String numCertificat) {
//		this.numCertificat = numCertificat;
//	}
//	public String getObservation() {
//		return this.observation;
//	}
//
//	public void setObservation(String observation) {
//		this.observation = observation;
//	}
//	public String getDateCreation() {
//		return this.dateCreation;
//	}
//
//	public void setDateCreation(String dateCreation) {
//		this.dateCreation = dateCreation;
//	}
	public String getRefTypeCertificat() {
		return this.refTypeCertificat;
	}

	public void setRefTypeCertificat(String refTypeCertificat) {
		this.refTypeCertificat = refTypeCertificat;
	}
	public String getRefTypeCertificatLabel() {
		return this.refTypeCertificatLabel;
	}

	public void setRefTypeCertificatLabel(String refTypeCertificatLabel) {
		this.refTypeCertificatLabel = refTypeCertificatLabel;
	}	
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
			listAttributes.add("datAu");
			listAttributes.add("datDu");
			listAttributes.add("datRcpt");
//			listAttributes.add("numCertificat");
			listAttributes.add("observation");
			listAttributes.add("dateCreation");
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this,listAttributes);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}	
		return rslt;
	}

	public String getTypeParentA(){
		return typeParentA; 
	}
	
	public void setTypeParentA (String typeParentA){
		this.typeParentA = typeParentA;
		this.typeParentB = typeParentA;
	}

	public String getTypeParentB(){
		return typeParentB; 
	}
	
	public void setTypeParentB (String typeParentB){
		this.typeParentB = typeParentB;
	}


	
	


}

