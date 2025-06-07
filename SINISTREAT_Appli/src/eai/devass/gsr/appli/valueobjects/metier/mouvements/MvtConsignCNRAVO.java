package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;

/**
 * Value Object de MvtConsignCNRA
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.mouvements.MvtConsignCNRA")
public class MvtConsignCNRAVO extends MouvementVO implements ITracable {

	
	@AConverter(propertyDist = "datLimtePaiement", pattern = "yyyyMMdd")
	private String datLimtePaiement;
	@AConverter(propertyDist = "datRcptCNRA", pattern = "yyyyMMdd")
	private String datRcptCNRA;
	@AConverter(propertyDist = "dateVersementCNRA", pattern = "yyyyMMdd")
	private String dateVersementCNRA;
	@AConverter(propertyDist = "mntCNRA")
	private String mntCNRA;
	@AConverter(propertyDist = "refDossierCNRA")
	private String refDossierCNRA;
	@AConverter(propertyDist = "avp")
	private String avp;
	//WMOS 03/11/2015: début Evo [ajout des nouveaux champs besoin batch CNRA (XML)]
	@AConverter(propertyDist = "numJugementPv")
	private String numJugementPv;
	@AConverter(propertyDist = "numDossierTribunal")
	private String numDossierTribunal;
	@AConverter(propertyDist = "datJugementPv", pattern = "yyyyMMdd")
	private String datJugementPv;
	@AConverter(propertyDist = "datNotification", pattern = "yyyyMMdd")
	private String datNotification;
	@AConverter(propertyDist = "juridiction")
	private String juridiction;
	@AConverter(propertyDist = "procConsignRegl")
	private String procConsignRegl;
	@AConverter(propertyDist = "libelleJuridiction")
	private String libelleJuridiction;
	//WMOS 03/11/2015: Fin Evo
	
	@AConverter(propertyDist = "listRentier")
	private List<RentierVO> listRentierVO;
	private String diffMntCapitalCnra;

	public String getDatLimtePaiement() {
		return this.datLimtePaiement;
	}

	public void setDatLimtePaiement(String datLimtePaiement) {
		this.datLimtePaiement = datLimtePaiement;
	}

	public String getDatRcptCNRA() {
		return this.datRcptCNRA;
	}

	public void setDatRcptCNRA(String datRcptCNRA) {
		this.datRcptCNRA = datRcptCNRA;
	}

	public String getDateVersementCNRA() {
		return dateVersementCNRA;
	}

	public void setDateVersementCNRA(String dateVersementCNRA) {
		this.dateVersementCNRA = dateVersementCNRA;
	}

	public String getMntCNRA() {
		return this.mntCNRA;
	}

	public void setMntCNRA(String mntCNRA) {
		this.mntCNRA = mntCNRA;
	}

	public String getRefDossierCNRA() {
		return this.refDossierCNRA;
	}

	public void setRefDossierCNRA(String refDossierCNRA) {
		this.refDossierCNRA = refDossierCNRA;
	}

	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("datLimtePaiement");
		listAttributes.add("datRcptCNRA");
		listAttributes.add("datPriseEnCharge");
		listAttributes.add("mntCNRA");
		listAttributes.add("refDossierCNRA");
		listAttributes.add("numJugementPv");
		listAttributes.add("numDossierTribunal");
		listAttributes.add("datJugementPv");
		listAttributes.add("datNotification");
		listAttributes.add("juridiction ");
		listAttributes.add("procConsignRegl");
		
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this, listAttributes);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return rslt;
	}

	public void setAvp(String avp) {
		this.avp = avp;
	}

	public String getAvp() {
		return avp;
	}

	public String getNumJugementPv() {
		return numJugementPv;
	}

	public void setNumJugementPv(String numJugementPv) {
		this.numJugementPv = numJugementPv;
	}

	public String getNumDossierTribunal() {
		return numDossierTribunal;
	}

	public void setNumDossierTribunal(String numDossierTribunal) {
		this.numDossierTribunal = numDossierTribunal;
	}

	public String getDatJugementPv() {
		return datJugementPv;
	}

	public void setDatJugementPv(String datJugementPv) {
		this.datJugementPv = datJugementPv;
	}

	public String getDatNotification() {
		return datNotification;
	}

	public void setDatNotification(String datNotification) {
		this.datNotification = datNotification;
	}

	public String getJuridiction() {
		return juridiction;
	}

	public void setJuridiction(String juridiction) {
		this.juridiction = juridiction;
	}

	public void setProcConsignRegl(String procConsignRegl) {
		this.procConsignRegl = procConsignRegl;
	}

	public String getProcConsignRegl() {
		return procConsignRegl;
	}

	public void setLibelleJuridiction(String libelleJuridiction) {
		this.libelleJuridiction = libelleJuridiction;
	}

	public String getLibelleJuridiction() {
		return libelleJuridiction;
	}

	public List<RentierVO> getListRentierVO() {
		return listRentierVO;
	}

	public void setListRentierVO(List<RentierVO> listRentierVO) {
		this.listRentierVO = listRentierVO;
	}

	public String getDiffMntCapitalCnra() {
		return diffMntCapitalCnra;
	}

	public void setDiffMntCapitalCnra(String diffMntCapitalCnra) {
		this.diffMntCapitalCnra = diffMntCapitalCnra;
	}
	
	
}
