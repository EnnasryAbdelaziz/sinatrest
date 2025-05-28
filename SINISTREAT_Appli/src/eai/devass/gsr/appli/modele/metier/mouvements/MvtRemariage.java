package eai.devass.gsr.appli.modele.metier.mouvements;
 
import java.util.Date;

import eai.devass.commun.appli.converter.AConverter;



@AConverter(entiteMapping="eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtRemariageVO")
public class MvtRemariage extends  Mouvement implements IMvtSortant {

private static final long serialVersionUID = 1L;


	private Date datRemariage;	
	private Boolean vofEnfants = false;	
	private Double mntRachatAnnuitee;
	private Date datCertificatRemariage;
	private Double mntIndu;
	private Double mntARegler;
	
	
	
	public Date getDatRemariage() {
		return datRemariage;
	}
	public void setDatRemariage(Date datRemariage) {
		this.datRemariage = datRemariage;
	}
	public Boolean getVofEnfants() {
		return vofEnfants;
	}
	public void setVofEnfants(Boolean vofEnfants) {
		this.vofEnfants = vofEnfants;
	}
	public Double getMntRachatAnnuitee() {
		return mntRachatAnnuitee;
	}
	public void setMntRachatAnnuitee(Double mntRachatAnnuitee) {
		this.mntRachatAnnuitee = mntRachatAnnuitee;
	}
	public Date getDatCertificatRemariage() {
		return datCertificatRemariage;
	}
	public void setDatCertificatRemariage(Date datCertificatRemariage) {
		this.datCertificatRemariage = datCertificatRemariage;
	}
	public Double getMntIndu() {
		return mntIndu;
	}
	public void setMntIndu(Double mntIndu) {
		this.mntIndu = mntIndu;
	}
	public Double getMntARegler() {
		return mntARegler;
	}
	public void setMntARegler(Double mntARegler) {
		this.mntARegler = mntARegler;
	}
	
	
	
	
	
	




}

