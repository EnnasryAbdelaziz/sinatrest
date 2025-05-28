package eai.devass.sinistreat.appli.valueobjects.metier.fichemedicale;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeCertificatVO;

public class CertificatMedicalVO implements IValueObject  {

	private String id;
	private String dateConsultation ;
	private String dateDepartITT ;
	private String dateReprise ;
	private String diagnostique;           
	private String IPP;             
	private String ITT      ;          
	private String codePrestataire ;           
	private String nomPrestataire ;               
	private String dateCreation  ;
	private TypeCertificatVO refType;
	private String numeroMission   ;
	private SinistreVO refSinistre;	 
	private String provisoireCodePrestataire;
	private String dateCertificat;
	private String observation;
	private String salaireResultat ;
	private String reserveOrdinaireResultat ;
	private String reserveGraveResultat ;
	private String valide;	
	//AZAS
	private String dateGuerison;
	//WMOS 28/09/2015 add date récéption
	private String dateReception;
	
	public String getDateGuerison() {
		return dateGuerison;
	}

	public void setDateGuerison(String dateGuerison) {
		this.dateGuerison = dateGuerison;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateConsultation() {
		return dateConsultation;
	}

	public void setDateConsultation(String dateConsultation) {
		this.dateConsultation = dateConsultation;
	}

	public String getDateDepartITT() {
		return dateDepartITT;
	}

	public void setDateDepartITT(String dateDepartITT) {
		this.dateDepartITT = dateDepartITT;
	}

	public String getDateReprise() {
		return dateReprise;
	}

	public void setDateReprise(String dateReprise) {
		this.dateReprise = dateReprise;
	}

	public String getDiagnostique() {
		return diagnostique;
	}

	public void setDiagnostique(String diagnostique) {
		this.diagnostique = diagnostique;
	}

	public String getIPP() {
		return IPP;
	}

	public void setIPP(String IPP) {
		this.IPP = IPP;
	}

	public String getITT() {
		return ITT;
	}

	public void setITT(String ITT) {
		this.ITT = ITT;
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

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getNumeroMission() {
		return numeroMission;
	}

	public void setNumeroMission(String numeroMission) {
		this.numeroMission = numeroMission;
	}

	public SinistreVO getRefSinistre() {
		return refSinistre;
	}

	public void setRefSinistre(SinistreVO refSinistre) {
		this.refSinistre = refSinistre;
	}
	
	public String getProvisoireCodePrestataire() {
		return provisoireCodePrestataire;
	}

	public void setProvisoireCodePrestataire(String provisoireCodePrestataire) {
		this.provisoireCodePrestataire = provisoireCodePrestataire;
	}

	public String getDateCertificat() {
		return dateCertificat;
	}

	public void setDateCertificat(String dateCertificat) {
		this.dateCertificat = dateCertificat;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getSalaireResultat() {
		return salaireResultat;
	}

	public void setSalaireResultat(String salaireResultat) {
		this.salaireResultat = salaireResultat;
	}

	public String getReserveGraveResultat() {
		return reserveGraveResultat;
	}

	public void setReserveGraveResultat(String reserveGraveResultat) {
		this.reserveGraveResultat = reserveGraveResultat;
	}

	public void setReserveOrdinaireResultat(String reserveOrdinaireResultat) {
		this.reserveOrdinaireResultat = reserveOrdinaireResultat;
	}

	public String getReserveOrdinaireResultat() {
		return reserveOrdinaireResultat;
	}

	public void setRefType(TypeCertificatVO refType) {
		this.refType = refType;
	}

	public TypeCertificatVO getRefType() {
		return refType;
	}

	public void setValide(String valide) {
		this.valide = valide;
	}

	public String getValide() {
		return valide;
	}

	public void setDateReception(String dateReception) {
		this.dateReception = dateReception;
	}

	public String getDateReception() {
		return dateReception;
	}
		
}
