package eai.devass.sinistreat.appli.modele.parametrage;

public class CourrierHybrideContreVisite implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String idModele;
	private String numeroSinistre;
	private String idReglement;
	private String codeFraisMedicaux;
	private String montantARegler;
	private String datEnvoi;
	private String etat;
	private String motifRejet;
	private String renvoie;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdModele() {
		return idModele;
	}
	public void setIdModele(String idModele) {
		this.idModele = idModele;
	}
	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}
	public String getIdReglement() {
		return idReglement;
	}
	public void setIdReglement(String idReglement) {
		this.idReglement = idReglement;
	}
	public String getCodeFraisMedicaux() {
		return codeFraisMedicaux;
	}
	public void setCodeFraisMedicaux(String codeFraisMedicaux) {
		this.codeFraisMedicaux = codeFraisMedicaux;
	}
	public String getMontantARegler() {
		return montantARegler;
	}
	public void setMontantARegler(String montantARegler) {
		this.montantARegler = montantARegler;
	}
	public String getDatEnvoi() {
		return datEnvoi;
	}
	public void setDatEnvoi(String datEnvoi) {
		this.datEnvoi = datEnvoi;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}