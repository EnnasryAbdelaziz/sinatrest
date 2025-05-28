package eai.devass.sinistreat.appli.valueobjects.metier.fichemedicale;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;


public class CertificatMedicalHistoriqueVO implements IValueObject  {

	private String id;
	private String motif;
	private String dateEvenement;
	private CertificatMedicalVO refCertificat;
	//Debut evo 969 /WMOS 15/09/2014
	private String userCreateur;
    //Fin evo 969 /WMOS 15/09/2014
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	public String getDateEvenement() {
		return dateEvenement;
	}
	public void setDateEvenement(String dateEvenement) {
		this.dateEvenement = dateEvenement;
	}
	public CertificatMedicalVO getRefCertificat() {
		return refCertificat;
	}
	public void setRefCertificat(CertificatMedicalVO refCertificat) {
		this.refCertificat = refCertificat;
	}
	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}
	public String getUserCreateur() {
		return userCreateur;
	}
	
}
