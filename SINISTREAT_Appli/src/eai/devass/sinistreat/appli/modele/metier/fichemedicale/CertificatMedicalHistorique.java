package eai.devass.sinistreat.appli.modele.metier.fichemedicale;

import java.util.Date;

public class CertificatMedicalHistorique {

	private Long id;
	private String motif;
	private Date dateEvenement;
	private CertificatMedical refCertificat;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	public Date getDateEvenement() {
		return dateEvenement;
	}
	public void setDateEvenement(Date dateEvenement) {
		this.dateEvenement = dateEvenement;
	}
	public CertificatMedical getRefCertificat() {
		return refCertificat;
	}
	public void setRefCertificat(CertificatMedical refCertificat) {
		this.refCertificat = refCertificat;
	}

}
