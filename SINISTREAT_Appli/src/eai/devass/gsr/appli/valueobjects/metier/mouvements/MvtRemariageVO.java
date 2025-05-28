
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import eai.devass.commun.appli.converter.AConverter;


/**
Value Object de MvtRemariage
@author Nom Prenom (email)
*/

@AConverter(entiteDist="eai.devass.gsr.appli.modele.metier.mouvements.MvtRemariage")
public class MvtRemariageVO extends  MouvementVO {


private static final long serialVersionUID = 1L;

	@AConverter(propertyDist="datRemariage")
	private String datRemariage;	
	
	@AConverter(propertyDist="vofEnfants")
	private String vofEnfants;	
	
	@AConverter(propertyDist="mntRachatAnnuitee")
	private String mntRachatAnnuitee;
	
	@AConverter(propertyDist="datCertificatRemariage")
	private String datCertificatRemariage;
	
	
	@AConverter(propertyDist="mntIndu")
	private String mntIndu;
	
	@AConverter(propertyDist="mntARegler")
	private String mntARegler;
	
	
	public String getDatRemariage() {
		return datRemariage;
	}
	public void setDatRemariage(String datRemariage) {
		this.datRemariage = datRemariage;
	}
	public String getVofEnfants() {
		return vofEnfants;
	}
	public void setVofEnfants(String vofEnfants) {
		this.vofEnfants = vofEnfants;
	}
	public String getMntRachatAnnuitee() {
		return mntRachatAnnuitee;
	}
	public void setMntRachatAnnuitee(String mntRachatAnnuitee) {
		this.mntRachatAnnuitee = mntRachatAnnuitee;
	}
	public String getDatCertificatRemariage() {
		return datCertificatRemariage;
	}
	public void setDatCertificatRemariage(String datCertificatRemariage) {
		this.datCertificatRemariage = datCertificatRemariage;
	}
	public String getMntIndu() {
		return mntIndu;
	}
	public void setMntIndu(String mntIndu) {
		this.mntIndu = mntIndu;
	}
	public String getMntARegler() {
		return mntARegler;
	}
	public void setMntARegler(String mntARegler) {
		this.mntARegler = mntARegler;
	}
	
	
	
	
	


}

