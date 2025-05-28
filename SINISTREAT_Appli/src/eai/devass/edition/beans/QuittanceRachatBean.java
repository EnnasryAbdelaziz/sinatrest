package eai.devass.edition.beans;

public class QuittanceRachatBean {
	
	private String numeroRente="";
	private String dateQuittance="";
	private String nomPrenomVictime="";
	private String montant="";
	private String montantEnLettre="";
	private String dateDecision="";
	private String villeTribunal="";
	private String referenceDossierTribunal="";
	private String dateAccident="";
	private String nomAssure="";
	
	
	public QuittanceRachatBean(String numeroRente, String dateQuittance,
			String nomPrenomVictime, String montant, String montantEnLettre,
			String dateDecision, String villeTribunal,
			String referenceDossierTribunal, String dateAccident,
			String nomAssure) {
		super();
		this.numeroRente = numeroRente;
		this.dateQuittance = dateQuittance;
		this.nomPrenomVictime = nomPrenomVictime;
		this.montant = montant;
		this.montantEnLettre = montantEnLettre;
		this.dateDecision = dateDecision;
		this.villeTribunal = villeTribunal;
		this.referenceDossierTribunal = referenceDossierTribunal;
		this.dateAccident = dateAccident;
		this.nomAssure = nomAssure;
	}

	

	public QuittanceRachatBean() {
		super();
	}



	public String getNumeroRente() {
		return numeroRente;
	}

	public void setNumeroRente(String numeroRente) {
		this.numeroRente = numeroRente;
	}

	public String getDateQuittance() {
		return dateQuittance;
	}


	public void setDateQuittance(String dateQuittance) {
		this.dateQuittance = dateQuittance;
	}

	public String getNomPrenomVictime() {
		return nomPrenomVictime;
	}

	public void setNomPrenomVictime(String nomPrenomVictime) {
		this.nomPrenomVictime = nomPrenomVictime;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

	public String getMontantEnLettre() {
		return montantEnLettre;
	}

	public void setMontantEnLettre(String montantEnLettre) {
		this.montantEnLettre = montantEnLettre;
	}

	public String getDateDecision() {
		return dateDecision;
	}

	public void setDateDecision(String dateDecision) {
		this.dateDecision = dateDecision;
	}

	public String getVilleTribunal() {
		return villeTribunal;
	}

	public void setVilleTribunal(String villeTribunal) {
		this.villeTribunal = villeTribunal;
	}

	public String getReferenceDossierTribunal() {
		return referenceDossierTribunal;
	}

	public void setReferenceDossierTribunal(String referenceDossierTribunal) {
		this.referenceDossierTribunal = referenceDossierTribunal;
	}

	public String getDateAccident() {
		return dateAccident;
	}

	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}

	public String getNomAssure() {
		return nomAssure;
	}

	public void setNomAssure(String nomAssure) {
		this.nomAssure = nomAssure;
	}
}
