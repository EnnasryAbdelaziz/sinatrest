package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class CourrierHybrideVO implements IValueObject{

	private String id;
	private String idLRH;
	
	/**
	 * 
	 */
	private ModeleLRHVO refModeleLRH;
	public ModeleLRHVO getRefModeleLRH() {
		return refModeleLRH;
	}
	public void setRefModeleLRH(ModeleLRHVO refModeleLRH) {
		this.refModeleLRH = refModeleLRH;
	}
	private String numeroSinistre;
	private String codeAbarre;
	private String datEnvoi;
	private EtatCourrierVO refEtatCourrier;
	private String methodeDistribution;
	private String notificationReception;
	private String attestationDistribution;
	private String motifRejet;
	private String renvoie;
	private String dateRenvoie;
	private String dateRetour;
    private String dateAnnulation;
	
	
	public String getDateAnnulation() {
		return dateAnnulation;
	}
	public void setDateAnnulation(String dateAnnulation) {
		this.dateAnnulation = dateAnnulation;
	}
	
	public String getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(String dateRetour) {
		this.dateRetour = dateRetour;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdLRH() {
		return idLRH;
	}
	public void setIdLRH(String idLRH) {
		this.idLRH = idLRH;
	}
	
	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}
	public String getCodeAbarre() {
		return codeAbarre;
	}
	public void setCodeAbarre(String codeAbarre) {
		this.codeAbarre = codeAbarre;
	}
	public String getDatEnvoi() {
		return datEnvoi;
	}
	public void setDatEnvoi(String datEnvoi) {
		this.datEnvoi = datEnvoi;
	}
	
	public EtatCourrierVO getRefEtatCourrier() {
		return refEtatCourrier;
	}
	public void setRefEtatCourrier(EtatCourrierVO refEtatCourrier) {
		this.refEtatCourrier = refEtatCourrier;
	}
	public String getMethodeDistribution() {
		return methodeDistribution;
	}
	public void setMethodeDistribution(String methodeDistribution) {
		this.methodeDistribution = methodeDistribution;
	}
	public String getNotificationReception() {
		return notificationReception;
	}
	public void setNotificationReception(String notificationReception) {
		this.notificationReception = notificationReception;
	}
	public String getAttestationDistribution() {
		return attestationDistribution;
	}
	public void setAttestationDistribution(String attestationDistribution) {
		this.attestationDistribution = attestationDistribution;
	}
	public String getMotifRejet() {
		return motifRejet;
	}
	public void setMotifRejet(String motifRejet) {
		this.motifRejet = motifRejet;
	}
	public String getRenvoie() {
		return renvoie;
	}
	public void setRenvoie(String renvoie) {
		this.renvoie = renvoie;
	}
	public String getDateRenvoie() {
		return dateRenvoie;
	}
	public void setDateRenvoie(String dateRenvoie) {
		this.dateRenvoie = dateRenvoie;
	}
}