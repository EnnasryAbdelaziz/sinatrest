
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.valueobjects.parametrage.OrigineRachatVO;


/**
Value Object de MvtRachat
@author Nom Prenom (email)
*/

@AConverter(entiteDist="eai.devass.gsr.appli.modele.metier.mouvements.MvtRachat")
public class MvtRachatVO extends  MouvementVO  implements ITracable{

	private static final long serialVersionUID = 1L;
	
	

	@Indexation(label="capitalCalcule",analyzed=false)
	@AConverter(propertyDist="capitalCalcule")
	private String capitalCalcule;

	@Indexation(label="capitalJuge",analyzed=false)
	@AConverter(propertyDist="capitalJuge")
	private String capitalJuge;
	
	@Indexation(label="mntPercu",analyzed=false) 
	@AConverter(propertyDist="mntPercu")
	private String mntPercu;
	@Indexation(label="mntDiff",analyzed=false) 
	@AConverter(propertyDist="mntDiff")
	private String mntDiff;
	@Indexation(label="dateCreation",analyzed=false) 
	//private String dateCreation;
	private String typeParentA;
	private String typeParentB;
	//MFBO@Evo mouvement Rachat Rente GSR
	@Indexation(label="dateCalcul",analyzed=false) 
	@AConverter(propertyDist="dateCalcul" ,pattern="yyyyMMdd")
	private String dateCalcul;
	@AConverter(propertyDist="nouveauIPP")
	private String nouveauIPP;
	@AConverter(propertyDist="salaireUtile")
	private String salaireUtile;
	@Indexation(label = "refOrigineRachat", analyzed = false)
	@AConverter(propertyDist="refOrigineRachat")
	private OrigineRachatVO refOrigineRachat;

	/**
	 * @return the refOrigineRachat
	 */
	public OrigineRachatVO getRefOrigineRachat() {
		return refOrigineRachat;
	}

	/**
	 * @param refOrigineRachat the refOrigineRachat to set
	 */
	public void setRefOrigineRachat(OrigineRachatVO refOrigineRachat) {
		this.refOrigineRachat = refOrigineRachat;
	}

	/**
	 * @return the capitalCalcule
	 */
	public String getCapitalCalcule() {
		return capitalCalcule;
	}

	/**
	 * @param capitalCalcule the capitalCalcule to set
	 */
	public void setCapitalCalcule(String capitalCalcule) {
		this.capitalCalcule = capitalCalcule;
	}
	
	/**
	 * @return the dateCalcul
	 */
	public String getDateCalcul() {
		return dateCalcul;
	}

	/**
	 * @param dateCalcul the dateCalcul to set
	 */
	public void setDateCalcul(String dateCalcul) {
		this.dateCalcul = dateCalcul;
	}

	public String getCapitalJuge() {
		return this.capitalJuge;
	}

	public void setCapitalJuge(String capitalJuge) {
		this.capitalJuge = capitalJuge;
	}
	public String getMntPercu() {
		return this.mntPercu;
	}

	public void setMntPercu(String mntPercu) {
		this.mntPercu = mntPercu;
	}
	public String getMntDiff() {
		return this.mntDiff;
	}

	public void setMntDiff(String mntDiff) {
		this.mntDiff = mntDiff;
	}
//	public String getDateCreation() {
//		return this.dateCreation;
//	}
//
//	public void setDateCreation(String dateCreation) {
//		this.dateCreation = dateCreation;
//	}
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
			listAttributes.add("capitalJuge");
			listAttributes.add("mntPercu");
			listAttributes.add("mntDiff");
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

	/**
	 * @return the nouveauIPP
	 */
	public String getNouveauIPP() {
		return nouveauIPP;
	}

	/**
	 * @param nouveauIPP the nouveauIPP to set
	 */
	public void setNouveauIPP(String nouveauIPP) {
		this.nouveauIPP = nouveauIPP;
	}

	/**
	 * @return the salaireUtile
	 */
	public String getSalaireUtile() {
		return salaireUtile;
	}

	/**
	 * @param salaireUtile the salaireUtile to set
	 */
	public void setSalaireUtile(String salaireUtile) {
		this.salaireUtile = salaireUtile;
	}
	


}

