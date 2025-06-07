package eai.devass.sinistreat.appli.modele.metier.fichemedicale;

import java.util.Date;

import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.TypeCertificat;

public class CertificatMedical {

	private Long id;
	private Date dateConsultation;
	private Date dateDepartITT;
	private Date dateReprise;
	private String diagnostique;
	private Double IPP;
	private Double ITT;
	private String codePrestataire;
	private String nomPrestataire;
	private Date dateCreation;
	private TypeCertificat refType;
	private String numeroMission;
	private Sinistre refSinistre;
	private Boolean provisoireCodePrestataire;
	private Date dateCertificat;
	private String observation;
	private Double salaireResultat;
	private Double reserveOrdinaireResultat;
	private Double reserveGraveResultat;
	private Boolean valide;
	//AZAS
	private Date dateGuerison;
	//WMOS 28/09/2015 add date récéption
	private Date dateReception;
	
	public Date getDateGuerison() {
		return dateGuerison;
	}

	public void setDateGuerison(Date dateGuerison) {
		this.dateGuerison = dateGuerison;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateConsultation() {
		return dateConsultation;
	}

	public void setDateConsultation(Date dateConsultation) {
		this.dateConsultation = dateConsultation;
	}

	public Date getDateDepartITT() {
		return dateDepartITT;
	}

	public void setDateDepartITT(Date dateDepartITT) {
		this.dateDepartITT = dateDepartITT;
	}

	public Date getDateReprise() {
		return dateReprise;
	}

	public void setDateReprise(Date dateReprise) {
		this.dateReprise = dateReprise;
	}

	public String getDiagnostique() {
		return diagnostique;
	}

	public void setDiagnostique(String diagnostique) {
		this.diagnostique = diagnostique;
	}

	public Double getIPP() {
		return IPP;
	}

	public void setIPP(Double iPP) {
		IPP = iPP;
	}

	public Double getITT() {
		return ITT;
	}

	public void setITT(Double iTT) {
		ITT = iTT;
	}

	public String getCodePrestataire() {
		return codePrestataire;
	}

	public void setCodePrestataire(String codePrestataire) {
		this.codePrestataire = codePrestataire;
	}

	public String getNomPrestataire() {
		return nomPrestataire;
	}

	public void setNomPrestataire(String nomPrestataire) {
		this.nomPrestataire = nomPrestataire;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getNumeroMission() {
		return numeroMission;
	}

	public void setNumeroMission(String numeroMission) {
		this.numeroMission = numeroMission;
	}

	public Sinistre getRefSinistre() {
		return refSinistre;
	}

	public void setRefSinistre(Sinistre refSinistre) {
		this.refSinistre = refSinistre;
	}

	public Boolean getProvisoireCodePrestataire() {
		return provisoireCodePrestataire;
	}

	public void setProvisoireCodePrestataire(Boolean provisoireCodePrestataire) {
		this.provisoireCodePrestataire = provisoireCodePrestataire;
	}

	public Date getDateCertificat() {
		return dateCertificat;
	}

	public void setDateCertificat(Date dateCertificat) {
		this.dateCertificat = dateCertificat;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Double getSalaireResultat() {
		return salaireResultat;
	}

	public void setSalaireResultat(Double salaireResultat) {
		this.salaireResultat = salaireResultat;
	}

	public Double getReserveGraveResultat() {
		return reserveGraveResultat;
	}

	public void setReserveGraveResultat(Double reserveGraveResultat) {
		this.reserveGraveResultat = reserveGraveResultat;
	}

	public Double getReserveOrdinaireResultat() {
		return reserveOrdinaireResultat;
	}

	public void setReserveOrdinaireResultat(Double reserveOrdinaireResultat) {
		this.reserveOrdinaireResultat = reserveOrdinaireResultat;
	}

	public void setRefType(TypeCertificat refType) {
		this.refType = refType;
	}

	public TypeCertificat getRefType() {
		return refType;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	public Boolean getValide() {
		return valide;
	}

	public void setDateReception(Date dateReception) {
		this.dateReception = dateReception;
	}

	public Date getDateReception() {
		return dateReception;
	}

}
