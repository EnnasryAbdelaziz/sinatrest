package eai.devass.sinistreat.appli.synchronisationSinistre.valueObjects;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.sqlquery.SqlProperty;
import eai.devass.sinistreat.appli.utils.sqlquery.TableName;


@TableName(tableName="sapr.sinistre")
public class PstSynchroSinistreVO implements IValueObject{

	private static final long serialVersionUID = 1L;
	
	@SqlProperty(defaultValue="0")
	private Integer numrel;
	private Integer codMvt;
	private String datMvt;
	@SqlProperty(defaultValue="0")
	private Integer servic;
	private String datpec;
	private String datpec1;
	@SqlProperty(defaultValue="0")
	private String hmnpec;
	private String redact;
	@SqlProperty(defaultValue="2")
	private Integer codsoc;
	private Integer typsin;
	private Integer catsin;       
	private Integer annsin;
	private Integer ordsin;
	private String refext;
	
//	@SqlProperty(champName="NUSIN1")
	private String nusin1;
	private Integer typdoc;
	private String numdoc;
	
//	@SqlProperty(champName="NUSIN2")
	private String nusin2;
	private String clibgd;
	
//	@SqlProperty(champName="NUSIN3")
	private String nusin3;
	
	@SqlProperty(length=15)
	private String police;
	
//	@SqlProperty(champName="NUSIN4")
	private String nusin4;
	private String datsur;
	private Integer catsin1;
	private String codapp;
	
//	@SqlProperty(champName="NUSIN5")
	private String nusin5;	
	
	@SqlProperty(defaultValue="9")
	private Integer typevt;	
	@SqlProperty(defaultValue="999999999999999999999")
	private String numevt;
	
	@SqlProperty(champName="DATDEC")
	private String datedec;
	
	@SqlProperty(defaultValue="0")
	private Integer moddec;
	@SqlProperty(defaultValue="0")
	private Integer codpay;

	
	@SqlProperty(defaultValue="0",ignore=true)
	private String bgdsin;
	
	@SqlProperty(defaultValue="0")
	private String codagl;
	@SqlProperty(defaultValue="0")
	private Integer clocal;
	@SqlProperty(defaultValue="0")
	private Integer codvil;
	private String adres1;
	private String adres2;
	@SqlProperty(defaultValue="0")
	private Integer codcir1;
	private String libcir1;
	@SqlProperty(defaultValue="0")
	private Integer codcir2;
	private String libcir2;
	@SqlProperty(defaultValue="0")
	private Integer codcir3;
	@SqlProperty(ignore=true)
	private String libcir3;
	private Integer codbgd;
	@SqlProperty(defaultValue="2000")
	private Integer catmin;
	private Integer annsin1;
	private Integer ordsin1;
	private Integer coddom;
	private Integer numclc;
	@SqlProperty(defaultValue="N")
	private String codcid;
	@SqlProperty(defaultValue="N")
	private String codetr;
	private Integer codeta;
	@SqlProperty(defaultValue="1")
	private String ctrsin;
	@SqlProperty(defaultValue="1")
	private String ctrres;
	@SqlProperty(defaultValue="1001")
	private Integer catres;
	@SqlProperty(defaultValue=" ")
	private String docomm1;
	@SqlProperty(defaultValue=" ")
	private String docomm2;
	@SqlProperty(defaultValue=" ")
	private String docomm3;
	@SqlProperty(defaultValue=" ")
	private String docomm4;
	@SqlProperty(defaultValue=" ")
	private String docomm5;
	@SqlProperty(defaultValue=" ")
	private String docomm6;
	private Integer ccateg;
	@SqlProperty(defaultValue="0")
	private Integer codprd;
	@SqlProperty(defaultValue="0")
	private Integer codfrm;
	private Integer typctr;
	private Integer codact;
	private Integer souact;
	@SqlProperty(defaultValue="22")
	private Integer socges;
	private String etapol;
	private String detpol;
	private String defpol; 
	private String datdbg;
	private String datfng;
	@SqlProperty(defaultValue="001")
	private String codgar1;
	@SqlProperty(defaultValue="002")
	private String codgar2;
	@SqlProperty(defaultValue="000")
	private String codgar3;
	@SqlProperty(defaultValue="000")
	private String codgar4;
	@SqlProperty(defaultValue="000")
	private String codgar5;
	@SqlProperty(defaultValue="000")
	private String codgar6;
	@SqlProperty(defaultValue="000")
	private String codgar7;
	@SqlProperty(defaultValue="000")
	private String codgar8;
	@SqlProperty(defaultValue="000")
	private String codgar9;
	@SqlProperty(defaultValue="000")
	private String codgar10;
	@SqlProperty(defaultValue="1")
	private String ctlgar;
	@SqlProperty(defaultValue="N")
	private String coddel;
	@SqlProperty(defaultValue="0")
	private Double  mntpre;
	@SqlProperty(defaultValue="0")
	private String codepb;
	@SqlProperty(defaultValue="0")
	private Double perdeb;
	@SqlProperty(defaultValue="0")
	private Double perfin;
	@SqlProperty(defaultValue="0")
	private Double txparb;
	@SqlProperty(defaultValue="0")
	private Double txfgen;
	@SqlProperty(defaultValue="1")
	private Integer ccoasp;
	@SqlProperty(defaultValue="W4007")
	private String codcmp;
	
	@SqlProperty(length=20)
	private String refpol;
	private String refsin;
	@SqlProperty(defaultValue="1")
	private Integer codpai;
	@SqlProperty(defaultValue="100")
	private Double parwat;
	@SqlProperty(defaultValue="0")
	private String cfrdis;
	@SqlProperty(defaultValue="0")
	private Double mtxdis;
	@SqlProperty(defaultValue="N")
	private String cdrepr;
	@SqlProperty(defaultValue="0")
	private String creasp;
	@SqlProperty(defaultValue="0")
	private Double numces;
    
	@SqlProperty(defaultValue="10")
	private String tauxcl;
	
	@SqlProperty(defaultValue="0")
    private Double hmnpec1;
	
	@SqlProperty(defaultValue="000")
	private String codred;
	@SqlProperty(defaultValue="000")
	private String cstat1;
	@SqlProperty(defaultValue="000")
	private String cstat2;
	@SqlProperty(defaultValue="0")
	private String cstat3;
	private Double affilie;
	@SqlProperty(defaultValue="0")
	private String codpres;
	@SqlProperty(defaultValue="0")
	private String dateadh;
	@SqlProperty(defaultValue="0")
	private Integer avcrac;
	@SqlProperty(defaultValue="0")
	private Integer finrac;
	@SqlProperty(defaultValue="0")
	private Integer avccap;
	@SqlProperty(defaultValue="0")
	private Integer fincap;
	@SqlProperty(defaultValue="0")
	private Double capuni;
	@SqlProperty(defaultValue="0")
	private String datref;
	@SqlProperty(defaultValue="0")
	private String nfmsar;
	private Integer semaph;
	private Integer cd_bgd;
	@SqlProperty(defaultValue="1")
	private Integer branche;
	private String date_branche;
	private String dateta;
	public Integer getNumrel() {
		return numrel;
	}
	public void setNumrel(Integer numrel) {
		this.numrel = numrel;
	}
	public Integer getCodMvt() {
		return codMvt;
	}
	public void setCodMvt(Integer codMvt) {
		this.codMvt = codMvt;
	}
	public String getDatMvt() {
		return datMvt;
	}
	public void setDatMvt(String datMvt) {
		this.datMvt = datMvt;
	}
	public Integer getServic() {
		return servic;
	}
	public void setServic(Integer servic) {
		this.servic = servic;
	}
	public String getDatpec() {
		return datpec;
	}
	public void setDatpec(String datpec) {
		this.datpec = datpec;
	}
	public String getDatpec1() {
		return datpec1;
	}
	public void setDatpec1(String datpec1) {
		this.datpec1 = datpec1;
	}
	public String getHmnpec() {
		return hmnpec;
	}
	public void setHmnpec(String hmnpec) {
		this.hmnpec = hmnpec;
	}
	public String getRedact() {
		return redact;
	}
	public void setRedact(String redact) {
		this.redact = redact;
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
	public String getRefext() {
		return refext;
	}
	public void setRefext(String refext) {
		this.refext = refext;
	}
	public String getNusin1() {
		return nusin1;
	}
	public void setNusin1(String nusin1) {
		this.nusin1 = nusin1;
	}
	public Integer getTypdoc() {
		return typdoc;
	}
	public void setTypdoc(Integer typdoc) {
		this.typdoc = typdoc;
	}
	public String getNumdoc() {
		return numdoc;
	}
	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}
	public String getNusin2() {
		return nusin2;
	}
	public void setNusin2(String nusin2) {
		this.nusin2 = nusin2;
	}
	public String getClibgd() {
		return clibgd;
	}
	public void setClibgd(String string) {
		this.clibgd = string;
	}
	public String getNusin3() {
		return nusin3;
	}
	public void setNusin3(String nusin3) {
		this.nusin3 = nusin3;
	}
	public String getPolice() {
		return police;
	}
	public void setPolice(String police) {
		this.police = police;
	}
	public String getNusin4() {
		return nusin4;
	}
	public void setNusin4(String nusin4) {
		this.nusin4 = nusin4;
	}
	public String getDatsur() {
		return datsur;
	}
	public void setDatsur(String datsur) {
		this.datsur = datsur;
	}
	public Integer getCatsin1() {
		return catsin1;
	}
	public void setCatsin1(Integer catsin1) {
		this.catsin1 = catsin1;
	}
	public String getCodapp() {
		return codapp;
	}
	public void setCodapp(String codapp) {
		this.codapp = codapp;
	}
	public String getNusin5() {
		return nusin5;
	}
	public void setNusin5(String nusin5) {
		this.nusin5 = nusin5;
	}
	public Integer getTypevt() {
		return typevt;
	}
	public void setTypevt(Integer typevt) {
		this.typevt = typevt;
	}
	public String getNumevt() {
		return numevt;
	}
	public void setNumevt(String numevt) {
		this.numevt = numevt;
	}
	public String getDatedec() {
		return datedec;
	}
	public void setDatedec(String datedec) {
		this.datedec = datedec;
	}
	public Integer getModdec() {
		return moddec;
	}
	public void setModdec(Integer moddec) {
		this.moddec = moddec;
	}
	public Integer getCodpay() {
		return codpay;
	}
	public void setCodpay(Integer codpay) {
		this.codpay = codpay;
	}
	public String getBgdsin() {
		return bgdsin;
	}
	public void setBgdsin(String bgdsin) {
		this.bgdsin = bgdsin;
	}
	public String getCodagl() {
		return codagl;
	}
	public void setCodagl(String codagl) {
		this.codagl = codagl;
	}
	public Integer getClocal() {
		return clocal;
	}
	public void setClocal(Integer clocal) {
		this.clocal = clocal;
	}
	public Integer getCodvil() {
		return codvil;
	}
	public void setCodvil(Integer codvil) {
		this.codvil = codvil;
	}
	public String getAdres1() {
		return adres1;
	}
	public void setAdres1(String adres1) {
		this.adres1 = adres1;
	}
	public String getAdres2() {
		return adres2;
	}
	public void setAdres2(String adres2) {
		this.adres2 = adres2;
	}
	public Integer getCodcir1() {
		return codcir1;
	}
	public void setCodcir1(Integer codcir1) {
		this.codcir1 = codcir1;
	}
	public String getLibcir1() {
		return libcir1;
	}
	public void setLibcir1(String libcir1) {
		this.libcir1 = libcir1;
	}
	public Integer getCodcir2() {
		return codcir2;
	}
	public void setCodcir2(Integer codcir2) {
		this.codcir2 = codcir2;
	}
	public String getLibcir2() {
		return libcir2;
	}
	public void setLibcir2(String libcir2) {
		this.libcir2 = libcir2;
	}
	public Integer getCodcir3() {
		return codcir3;
	}
	public void setCodcir3(Integer codcir3) {
		this.codcir3 = codcir3;
	}
	public String getLibcir3() {
		return libcir3;
	}
	public void setLibcir3(String libcir3) {
		this.libcir3 = libcir3;
	}
	public Integer getCodbgd() {
		return codbgd;
	}
	public void setCodbgd(Integer codbgd) {
		this.codbgd = codbgd;
	}
	public Integer getCatmin() {
		return catmin;
	}
	public void setCatmin(Integer catmin) {
		this.catmin = catmin;
	}
	public Integer getAnnsin1() {
		return annsin1;
	}
	public void setAnnsin1(Integer annsin1) {
		this.annsin1 = annsin1;
	}
	public Integer getOrdsin1() {
		return ordsin1;
	}
	public void setOrdsin1(Integer ordsin1) {
		this.ordsin1 = ordsin1;
	}
	public Integer getCoddom() {
		return coddom;
	}
	public void setCoddom(Integer coddom) {
		this.coddom = coddom;
	}
	public Integer getNumclc() {
		return numclc;
	}
	public void setNumclc(Integer numclc) {
		this.numclc = numclc;
	}
	public String getCodcid() {
		return codcid;
	}
	public void setCodcid(String codcid) {
		this.codcid = codcid;
	}
	public String getCodetr() {
		return codetr;
	}
	public void setCodetr(String codetr) {
		this.codetr = codetr;
	}
	public Integer getCodeta() {
		return codeta;
	}
	public void setCodeta(Integer codeta) {
		this.codeta = codeta;
	}
	public String getCtrsin() {
		return ctrsin;
	}
	public void setCtrsin(String ctrsin) {
		this.ctrsin = ctrsin;
	}
	public String getCtrres() {
		return ctrres;
	}
	public void setCtrres(String ctrres) {
		this.ctrres = ctrres;
	}
	public Integer getCatres() {
		return catres;
	}
	public void setCatres(Integer catres) {
		this.catres = catres;
	}
	public String getDocomm1() {
		return docomm1;
	}
	public void setDocomm1(String docomm1) {
		this.docomm1 = docomm1;
	}
	public String getDocomm2() {
		return docomm2;
	}
	public void setDocomm2(String docomm2) {
		this.docomm2 = docomm2;
	}
	public String getDocomm3() {
		return docomm3;
	}
	public void setDocomm3(String docomm3) {
		this.docomm3 = docomm3;
	}
	public String getDocomm4() {
		return docomm4;
	}
	public void setDocomm4(String docomm4) {
		this.docomm4 = docomm4;
	}
	public String getDocomm5() {
		return docomm5;
	}
	public void setDocomm5(String docomm5) {
		this.docomm5 = docomm5;
	}
	public String getDocomm6() {
		return docomm6;
	}
	public void setDocomm6(String docomm6) {
		this.docomm6 = docomm6;
	}
	public Integer getCcateg() {
		return ccateg;
	}
	public void setCcateg(Integer ccateg) {
		this.ccateg = ccateg;
	}
	public Integer getCodprd() {
		return codprd;
	}
	public void setCodprd(Integer codprd) {
		this.codprd = codprd;
	}
	public Integer getCodfrm() {
		return codfrm;
	}
	public void setCodfrm(Integer codfrm) {
		this.codfrm = codfrm;
	}
	public Integer getTypctr() {
		return typctr;
	}
	public void setTypctr(Integer typctr) {
		this.typctr = typctr;
	}
	public Integer getCodact() {
		return codact;
	}
	public void setCodact(Integer codact) {
		this.codact = codact;
	}
	public Integer getSouact() {
		return souact;
	}
	public void setSouact(Integer souact) {
		this.souact = souact;
	}
	public Integer getSocges() {
		return socges;
	}
	public void setSocges(Integer socges) {
		this.socges = socges;
	}
	public String getEtapol() {
		return etapol;
	}
	public void setEtapol(String etapol) {
		this.etapol = etapol;
	}
	
	public String getDetpol() {
		return detpol;
	}
	public void setDetpol(String detpol) {
		this.detpol = detpol;
	}
	public String getDefpol() {
		return defpol;
	}
	public void setDefpol(String defpol) {
		this.defpol = defpol;
	}
	public String getDatdbg() {
		return datdbg;
	}
	public void setDatdbg(String datdbg) {
		this.datdbg = datdbg;
	}
	public String getDatfng() {
		return datfng;
	}
	public void setDatfng(String datfng) {
		this.datfng = datfng;
	}
	public String getCodgar1() {
		return codgar1;
	}
	public void setCodgar1(String codgar1) {
		this.codgar1 = codgar1;
	}
	public String getCodgar2() {
		return codgar2;
	}
	public void setCodgar2(String codgar2) {
		this.codgar2 = codgar2;
	}
	public String getCodgar3() {
		return codgar3;
	}
	public void setCodgar3(String codgar3) {
		this.codgar3 = codgar3;
	}
	public String getCodgar4() {
		return codgar4;
	}
	public void setCodgar4(String codgar4) {
		this.codgar4 = codgar4;
	}
	public String getCodgar5() {
		return codgar5;
	}
	public void setCodgar5(String codgar5) {
		this.codgar5 = codgar5;
	}
	public String getCodgar6() {
		return codgar6;
	}
	public void setCodgar6(String codgar6) {
		this.codgar6 = codgar6;
	}
	public String getCodgar7() {
		return codgar7;
	}
	public void setCodgar7(String codgar7) {
		this.codgar7 = codgar7;
	}
	public String getCodgar8() {
		return codgar8;
	}
	public void setCodgar8(String codgar8) {
		this.codgar8 = codgar8;
	}
	public String getCodgar9() {
		return codgar9;
	}
	public void setCodgar9(String codgar9) {
		this.codgar9 = codgar9;
	}
	public String getCodgar10() {
		return codgar10;
	}
	public void setCodgar10(String codgar10) {
		this.codgar10 = codgar10;
	}
	public String getCtlgar() {
		return ctlgar;
	}
	public void setCtlgar(String ctlgar) {
		this.ctlgar = ctlgar;
	}
	public String getCoddel() {
		return coddel;
	}
	public void setCoddel(String coddel) {
		this.coddel = coddel;
	}
	public Double getMntpre() {
		return mntpre;
	}
	public void setMntpre(Double mntpre) {
		this.mntpre = mntpre;
	}
	public String getCodepb() {
		return codepb;
	}
	public void setCodepb(String codepb) {
		this.codepb = codepb;
	}
	public Double getPerdeb() {
		return perdeb;
	}
	public void setPerdeb(Double perdeb) {
		this.perdeb = perdeb;
	}
	public Double getPerfin() {
		return perfin;
	}
	public void setPerfin(Double perfin) {
		this.perfin = perfin;
	}
	public Double getTxparb() {
		return txparb;
	}
	public void setTxparb(Double txparb) {
		this.txparb = txparb;
	}
	public Double getTxfgen() {
		return txfgen;
	}
	public void setTxfgen(Double txfgen) {
		this.txfgen = txfgen;
	}
	public Integer getCcoasp() {
		return ccoasp;
	}
	public void setCcoasp(Integer ccoasp) {
		this.ccoasp = ccoasp;
	}
	public String getCodcmp() {
		return codcmp;
	}
	public void setCodcmp(String codcmp) {
		this.codcmp = codcmp;
	}
	public String getRefpol() {
		return refpol;
	}
	public void setRefpol(String refpol) {
		this.refpol = refpol;
	}
	public String getRefsin() {
		return refsin;
	}
	public void setRefsin(String refsin) {
		this.refsin = refsin;
	}
	public Integer getCodpai() {
		return codpai;
	}
	public void setCodpai(Integer codpai) {
		this.codpai = codpai;
	}
	public Double getParwat() {
		return parwat;
	}
	public void setParwat(Double parwat) {
		this.parwat = parwat;
	}
	public String getCfrdis() {
		return cfrdis;
	}
	public void setCfrdis(String cfrdis) {
		this.cfrdis = cfrdis;
	}
	public Double getMtxdis() {
		return mtxdis;
	}
	public void setMtxdis(Double mtxdis) {
		this.mtxdis = mtxdis;
	}
	public String getCdrepr() {
		return cdrepr;
	}
	public void setCdrepr(String cdrepr) {
		this.cdrepr = cdrepr;
	}
	public String getCreasp() {
		return creasp;
	}
	public void setCreasp(String creasp) {
		this.creasp = creasp;
	}
	public Double getNumces() {
		return numces;
	}
	public void setNumces(Double numces) {
		this.numces = numces;
	}
	public String getTauxcl() {
		return tauxcl;
	}
	public void setTauxcl(String tauxcl) {
		this.tauxcl = tauxcl;
	}
	public Double getHmnpec1() {
		return hmnpec1;
	}
	public void setHmnpec1(Double hmnpec1) {
		this.hmnpec1 = hmnpec1;
	}
	public String getCodred() {
		return codred;
	}
	public void setCodred(String codred) {
		this.codred = codred;
	}
	public String getCstat1() {
		return cstat1;
	}
	public void setCstat1(String cstat1) {
		this.cstat1 = cstat1;
	}
	public String getCstat2() {
		return cstat2;
	}
	public void setCstat2(String cstat2) {
		this.cstat2 = cstat2;
	}
	public String getCstat3() {
		return cstat3;
	}
	public void setCstat3(String cstat3) {
		this.cstat3 = cstat3;
	}
	public Double getAffilie() {
		return affilie;
	}
	public void setAffilie(Double affilie) {
		this.affilie = affilie;
	}
	public String getCodpres() {
		return codpres;
	}
	public void setCodpres(String codpres) {
		this.codpres = codpres;
	}
	public String getDateadh() {
		return dateadh;
	}
	public void setDateadh(String dateadh) {
		this.dateadh = dateadh;
	}
	public Integer getAvcrac() {
		return avcrac;
	}
	public void setAvcrac(Integer avcrac) {
		this.avcrac = avcrac;
	}
	public Integer getFinrac() {
		return finrac;
	}
	public void setFinrac(Integer finrac) {
		this.finrac = finrac;
	}
	public Integer getAvccap() {
		return avccap;
	}
	public void setAvccap(Integer avccap) {
		this.avccap = avccap;
	}
	public Integer getFincap() {
		return fincap;
	}
	public void setFincap(Integer fincap) {
		this.fincap = fincap;
	}
	public Double getCapuni() {
		return capuni;
	}
	public void setCapuni(Double capuni) {
		this.capuni = capuni;
	}
	public String getDatref() {
		return datref;
	}
	public void setDatref(String datref) {
		this.datref = datref;
	}
	public String getNfmsar() {
		return nfmsar;
	}
	public void setNfmsar(String nfmsar) {
		this.nfmsar = nfmsar;
	}
	public Integer getSemaph() {
		return semaph;
	}
	public void setSemaph(Integer semaph) {
		this.semaph = semaph;
	}
	public Integer getCd_bgd() {
		return cd_bgd;
	}
	public void setCd_bgd(Integer cd_bgd) {
		this.cd_bgd = cd_bgd;
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
	public void setDate_branche(String date_branche) {
		this.date_branche = date_branche;
	}
	public String getDateta() {
		return dateta;
	}
	public void setDateta(String dateta) {
		this.dateta = dateta;
	}

}
