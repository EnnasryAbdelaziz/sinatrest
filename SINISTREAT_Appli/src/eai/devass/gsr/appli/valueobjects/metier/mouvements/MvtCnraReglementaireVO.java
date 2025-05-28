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
@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.mouvements.MvtCnraReglementaire")
public class MvtCnraReglementaireVO extends MouvementVO implements ITracable {
	
	@AConverter(propertyDist = "refTypeConsignation")
	private String refTypeConsignation;
	@AConverter(propertyDist = "refTypeRevision")
	private String refTypeRevision;
	@AConverter(propertyDist = "refNatureDecision")
	private String refNatureDecision;
	@AConverter(propertyDist = "numDossierTribunal")
	private String numDossierTribunal;
	@AConverter(propertyDist = "numJugementPv")
	private String numJugementPv;
	@AConverter(propertyDist = "datJugementPv", pattern = "yyyyMMdd")
	private String datJugementPv;
	@AConverter(propertyDist = "datNotification", pattern = "yyyyMMdd")
	private String datNotification;
	@AConverter(propertyDist = "juridiction")
	private String juridiction;
	@AConverter(propertyDist = "libelleJuridiction")
	private String libelleJuridiction;
	@AConverter(propertyDist = "datLimtePaiement", pattern = "yyyyMMdd")
	private String datLimtePaiement;
	@AConverter(propertyDist = "datServiceCNRA")
	private String datServiceCNRA;
	@AConverter(propertyDist = "listRentier")
	private List<RentierVO> listRentierVO;
	@AConverter(propertyDist = "quotePart")
	private String quotePart;
	//////////////////////////
	

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
	@AConverter(propertyDist = "procConsignRegl")
	private String procConsignRegl;
	

	
	private String totalCapitalNet;
	private String totalReliquat;
	
	/* Evolution Mouvement CNRA Reglementaire */

	@AConverter(propertyDist = "mntCapitalDu")
	private String mntCapitalDu;
	@AConverter(propertyDist = "nbrTrimestreAregler")
	private String nbrTrimestreAregler; 
	@AConverter(propertyDist = "mntArrerage")
	private String mntArrerage ; 
	@AConverter(propertyDist = "mntReliquat")
	private String mntReliquat ; 
	@AConverter(propertyDist = "prorataCNRA")
	private String prorataCNRA;

	
	public String getRefTypeConsignation() {
		return refTypeConsignation;
	}

	public void setRefTypeConsignation(String refTypeConsignation) {
		this.refTypeConsignation = refTypeConsignation;
	}

	public String getRefTypeRevision() {
		return refTypeRevision;
	}

	public void setRefTypeRevision(String refTypeRevision) {
		this.refTypeRevision = refTypeRevision;
	}

	public String getRefNatureDecision() {
		return refNatureDecision;
	}

	public void setRefNatureDecision(String refNatureDecision) {
		this.refNatureDecision = refNatureDecision;
	}
	
	public String getDatServiceCNRA() {
		return datServiceCNRA;
	}

	public void setDatServiceCNRA(String datServiceCNRA) {
		this.datServiceCNRA = datServiceCNRA;
	}
	
	
	public String getProrataCNRA() {
		return prorataCNRA;
	}

	public void setProrataCNRA(String prorataCNRA) {
		this.prorataCNRA = prorataCNRA;
	}

	public String getQuotePart() {
		return quotePart;
	}

	public void setQuotePart(String quotePart) {
		this.quotePart = quotePart;
	}

	public String getMntCapitalDu() {
		return mntCapitalDu;
	}

	public void setMntCapitalDu(String mntCapitalDu) {
		this.mntCapitalDu = mntCapitalDu;
	}

	public String getNbrTrimestreAregler() {
		return nbrTrimestreAregler;
	}

	public void setNbrTrimestreAregler(String nbrTrimestreAregler) {
		this.nbrTrimestreAregler = nbrTrimestreAregler;
	}

	public String getMntArrerage() {
		return mntArrerage;
	}

	public void setMntArrerage(String mntArrerage) {
		this.mntArrerage = mntArrerage;
	}
	public String getMntReliquat() {
		return mntReliquat;
	}

	public void setMntReliquat(String mntReliquat) {
		this.mntReliquat = mntReliquat;
	}

	/* Fin */
	
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

	public String getTotalCapitalNet() {
		return totalCapitalNet;
	}

	public void setTotalCapitalNet(String totalCapitalNet) {
		this.totalCapitalNet = totalCapitalNet;
	}

	public String getTotalReliquat() {
		return totalReliquat;
	}

	public void setTotalReliquat(String totalReliquat) {
		this.totalReliquat = totalReliquat;
	}
	
	
}
