
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.ProtheseVO;


/**
Value Object de MvtProthese
@author Nom Prenom (email)
*/
@AConverter(entiteDist="eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese")
public class MvtProtheseVO extends  MouvementVO  implements ITracable{



	private static final long serialVersionUID = 1L;

	@Indexation(label="datMvtProthese",analyzed=false) 
	@AConverter(propertyDist="datMvtProthese")
	private String datMvtProthese;
	@Indexation(label="detailsMvt",analyzed=false)
	@AConverter(propertyDist="detailsMvt")
	private String detailsMvt;
	@Indexation(label="mntFraisAppareil",analyzed=false) 
	@AConverter(propertyDist="mntFraisAppareil")
	private String mntFraisAppareil;
	@Indexation(label="mntMvtProthese",analyzed=false) 
	@AConverter(propertyDist="mntMvtProthese")
	private String mntMvtProthese;
	@AConverter(propertyDist="cumulReserveProthese")
	private String cumulReserveProthese;

	@Indexation(label="numProthese",analyzed=false) 
	@AConverter(propertyDist="numProthese")
	private String numProthese;
	@Indexation(label="vofEstimatif",analyzed=false) 
	@AConverter(propertyDist="vofEstimatif")
	private String vofEstimatif;
//	@Indexation(label="dateCreation",analyzed=false) 
//	private String dateCreation;
	
	@Indexation(label="CentreProthese",analyzed=false)  
	@AConverter(propertyDist="refCentreProthese.id")
	private String refCentreProthese ;
	@AConverter(propertyDist="refCentreProthese.libelle")
	private String refCentreProtheseLabel ;
	

	@Indexation(label="NatureProthese",analyzed=false)  
	@AConverter(propertyDist="refNatureProthese.id")
	private String refNatureProthese ;
	@AConverter(propertyDist="refNatureProthese.libelle")
	private String refNatureProtheseLabel ;
	private String typeParentA;
	private String typeParentB;
	@AConverter(propertyDist="refsProthese")
	private List<ProtheseVO> refsProthese;
	
	


	/**
	 * @return the refsProthese
	 */
	public List<ProtheseVO> getRefsProthese() {
		return refsProthese;
	}

	/**
	 * @param refsProthese the refsProthese to set
	 */
	public void setRefsProthese(List<ProtheseVO> refsProthese) {
		this.refsProthese = refsProthese;
	}

	public String getDatMvtProthese() {
		return this.datMvtProthese;
	}

	public void setDatMvtProthese(String datMvtProthese) {
		this.datMvtProthese = datMvtProthese;
	}
	public String getDetailsMvt() {
		return this.detailsMvt;
	}

	public void setDetailsMvt(String detailsMvt) {
		this.detailsMvt = detailsMvt;
	}
	public String getMntFraisAppareil() {
		return this.mntFraisAppareil;
	}

	public void setMntFraisAppareil(String mntFraisAppareil) {
		this.mntFraisAppareil = mntFraisAppareil;
	}
	public String getMntMvtProthese() {
		return this.mntMvtProthese;
	}

	public void setMntMvtProthese(String mntMvtProthese) {
		this.mntMvtProthese = mntMvtProthese;
	}
	public String getNumProthese() {
		return this.numProthese;
	}

	public void setNumProthese(String numProthese) {
		this.numProthese = numProthese;
	}
	public String getVofEstimatif() {
		return this.vofEstimatif;
	}

	public void setVofEstimatif(String vofEstimatif) {
		this.vofEstimatif = vofEstimatif;
	}
	
	/**
	 * @return the cumulReserveProthese
	 */
	public String getCumulReserveProthese() {
		return cumulReserveProthese;
	}

	/**
	 * @param cumulReserveProthese the cumulReserveProthese to set
	 */
	public void setCumulReserveProthese(String cumulReserveProthese) {
		this.cumulReserveProthese = cumulReserveProthese;
	}
//	public String getDateCreation() {
//		return this.dateCreation;
//	}
//
//	public void setDateCreation(String dateCreation) {
//		this.dateCreation = dateCreation;
//	}
	public String getRefCentreProthese() {
		return this.refCentreProthese;
	}

	public void setRefCentreProthese(String refCentreProthese) {
		this.refCentreProthese = refCentreProthese;
	}
	public String getRefCentreProtheseLabel() {
		return this.refCentreProtheseLabel;
	}

	public void setRefCentreProtheseLabel(String refCentreProtheseLabel) {
		this.refCentreProtheseLabel = refCentreProtheseLabel;
	}	

	public String getRefNatureProthese() {
		return this.refNatureProthese;
	}

	public void setRefNatureProthese(String refNatureProthese) {
		this.refNatureProthese = refNatureProthese;
	}
	public String getRefNatureProtheseLabel() {
		return this.refNatureProtheseLabel;
	}

	public void setRefNatureProtheseLabel(String refNatureProtheseLabel) {
		this.refNatureProtheseLabel = refNatureProtheseLabel;
	}	
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
			listAttributes.add("datMvtProthese");
			listAttributes.add("detailsMvt");
			listAttributes.add("mntFraisAppareil");
			listAttributes.add("mntMvtProthese");
			listAttributes.add("numProthese");
			listAttributes.add("vofEstimatif");
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

