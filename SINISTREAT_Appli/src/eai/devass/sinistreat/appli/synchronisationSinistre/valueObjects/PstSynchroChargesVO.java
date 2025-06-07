package eai.devass.sinistreat.appli.synchronisationSinistre.valueObjects;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.sqlquery.SqlProperty;
import eai.devass.sinistreat.appli.utils.sqlquery.TableName;

@TableName(tableName="charges")
public class PstSynchroChargesVO implements IValueObject {
		
	private static final long serialVersionUID = 1L;

	@SqlProperty(defaultValue="2")
	private Integer natcout;
	
	@SqlProperty(defaultValue="4")
	private Integer iddevise;
	
	@SqlProperty(defaultValue="001")
	private String codgar;
	
	private Integer catres;
	private Integer ctrres;
	private Double resouv;
	private Double cumpai;
	private Double cumrec;
	private Double fraisd;
	private Double rcours;
	private String datmaj;
	private String datpec;
	private Integer codsoc;
	private Integer typsin;
	private Integer cd_bgd;
	private Integer catsin;
	private Integer annsin;
	private Integer ordsin;
	
	@SqlProperty(defaultValue="1")
	private Integer branche;
	
	
	private String date_branche;
	
	
	public Integer getNatcout() {
		return natcout;
	}
	public void setNatcout(Integer natcout) {
		this.natcout = natcout;
	}
	public Integer getIddevise() {
		return iddevise;
	}
	public void setIddevise(Integer iddevise) {
		this.iddevise = iddevise;
	}
	public String getCodgar() {
		return codgar;
	}
	public void setCodgar(String codgar) {
		this.codgar = codgar;
	}
	public Integer getCatres() {
		return catres;
	}
	public void setCatres(Integer catres) {
		this.catres = catres;
	}
	public Integer getCtrres() {
		return ctrres;
	}
	public void setCtrres(Integer ctrres) {
		this.ctrres = ctrres;
	}
	public Double getResouv() {
		return resouv;
	}
	public void setResouv(Double resouv) {
		this.resouv = resouv;
	}
	public Double getCumpai() {
		return cumpai;
	}
	public void setCumpai(Double cumpai) {
		this.cumpai = cumpai;
	}
	public Double getCumrec() {
		return cumrec;
	}
	public void setCumrec(Double cumrec) {
		this.cumrec = cumrec;
	}
	public Double getFraisd() {
		return fraisd;
	}
	public void setFraisd(Double fraisd) {
		this.fraisd = fraisd;
	}
	public Double getRcours() {
		return rcours;
	}
	public void setRcours(Double rcours) {
		this.rcours = rcours;
	}
	public String getDatmaj() {
		return datmaj;
	}
	public void setDatmaj(String date) {
		this.datmaj = date;
	}
	public String getDatpec() {
		return datpec;
	}
	public void setDatpec(String date) {
		this.datpec = date;
	}
	public Integer getCodsoc() {
		return codsoc;
	}
	public void setCodsoc(Integer codsoc) {
		this.codsoc = codsoc;
	}
	public Integer getTypsin() {
		return typsin;
	}
	public void setTypsin(Integer typsin) {
		this.typsin = typsin;
	}
	public Integer getCd_bgd() {
		return cd_bgd;
	}
	public void setCd_bgd(Integer cd_bgd) {
		this.cd_bgd = cd_bgd;
	}
	public Integer getCatsin() {
		return catsin;
	}
	public void setCatsin(Integer catsin) {
		this.catsin = catsin;
	}
	public Integer getAnnsin() {
		return annsin;
	}
	public void setAnnsin(Integer annsin) {
		this.annsin = annsin;
	}
	public Integer getOrdsin() {
		return ordsin;
	}
	public void setOrdsin(Integer ordsin) {
		this.ordsin = ordsin;
	}
	public Integer getBranche() {
		return branche;
	}
	public void setBranche(Integer branche) {
		this.branche = branche;
	}
	public String getDate_branche() {
		return date_branche;
	}
	public void setDate_branche(String date) {
		this.date_branche = date;
	}
	
	
	
	
	

	
}
