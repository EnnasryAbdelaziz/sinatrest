package eai.devass.sinistreat.appli.modele.metier.sinistre;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;

public class ComplementQuittanceView implements IEntite{
	
	private static final long serialVersionUID = 1L;
	
	private String numeroQuittance;
    private String typeSinistre;
    private String codeValidite;
    private String numeroGrave;
    private String natureContrat;
    private String codeClassificationPolice;
    private String codeSousActivite;
    private String codeClient;
    private String typeBeneficiaire;
    private String assure;
    private String codeOperateurRedacteur;
    private String codeDecentralisation;
    private Calendar dateAccident;
    private Calendar dateEffetPolice;
    private String codeEmetteurQtc;
    private String codeBeneficiaire;
    private String refSinistre;
    private String nomBeneficiaire;
    private String nomVictime;
    private Calendar dateSinistre;
    private String anneeEffetPolice;
    private String codeAuxiliaire;
    private String adresseBeneficiaire;
    private String villeBeneficiaire;
    private String idReglement;
    private String source;
    
	public String getNumeroQuittance() {
		return numeroQuittance;
	}
	public void setNumeroQuittance(String numeroQuittance) {
		this.numeroQuittance = numeroQuittance;
	}
	public String getTypeSinistre() {
		return typeSinistre;
	}
	public void setTypeSinistre(String typeSinistre) {
		this.typeSinistre = typeSinistre;
	}
	public String getCodeValidite() {
		return codeValidite;
	}
	public void setCodeValidite(String codeValidite) {
		this.codeValidite = codeValidite;
	}
	public String getNumeroGrave() {
		return numeroGrave;
	}
	public void setNumeroGrave(String numeroGrave) {
		this.numeroGrave = numeroGrave;
	}
	public String getNatureContrat() {
		return natureContrat;
	}
	public void setNatureContrat(String natureContrat) {
		this.natureContrat = natureContrat;
	}
	public String getCodeClassificationPolice() {
		return codeClassificationPolice;
	}
	public void setCodeClassificationPolice(String codeClassificationPolice) {
		this.codeClassificationPolice = codeClassificationPolice;
	}
	public String getCodeSousActivite() {
		return codeSousActivite;
	}
	public void setCodeSousActivite(String codeSousActivite) {
		this.codeSousActivite = codeSousActivite;
	}
	public String getCodeClient() {
		return codeClient;
	}
	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}
	public String getTypeBeneficiaire() {
		return typeBeneficiaire;
	}
	public void setTypeBeneficiaire(String typeBeneficiaire) {
		this.typeBeneficiaire = typeBeneficiaire;
	}
	public String getAssure() {
		return assure;
	}
	public void setAssure(String assure) {
		this.assure = assure;
	}
	public String getCodeOperateurRedacteur() {
		return codeOperateurRedacteur;
	}
	public void setCodeOperateurRedacteur(String codeOperateurRedacteur) {
		this.codeOperateurRedacteur = codeOperateurRedacteur;
	}
	public String getCodeDecentralisation() {
		return codeDecentralisation;
	}
	public void setCodeDecentralisation(String codeDecentralisation) {
		this.codeDecentralisation = codeDecentralisation;
	}
	public Calendar getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(Calendar dateAccident) {
		this.dateAccident = dateAccident;
	}
	public Calendar getDateEffetPolice() {
		return dateEffetPolice;
	}
	public void setDateEffetPolice(Calendar dateEffetPolice) {
		this.dateEffetPolice = dateEffetPolice;
	}
	public String getCodeEmetteurQtc() {
		return codeEmetteurQtc;
	}
	public void setCodeEmetteurQtc(String codeEmetteurQtc) {
		this.codeEmetteurQtc = codeEmetteurQtc;
	}
	public String getCodeBeneficiaire() {
		return codeBeneficiaire;
	}
	public void setCodeBeneficiaire(String codeBeneficiaire) {
		this.codeBeneficiaire = codeBeneficiaire;
	}
	public String getRefSinistre() {
		return refSinistre;
	}
	public void setRefSinistre(String refSinistre) {
		this.refSinistre = refSinistre;
	}
	public String getNomBeneficiaire() {
		return nomBeneficiaire;
	}
	public void setNomBeneficiaire(String nomBeneficiaire) {
		this.nomBeneficiaire = nomBeneficiaire;
	}
	public String getNomVictime() {
		return nomVictime;
	}
	public void setNomVictime(String nomVictime) {
		this.nomVictime = nomVictime;
	}
	public Calendar getDateSinistre() {
		return dateSinistre;
	}
	public void setDateSinistre(Calendar dateSinistre) {
		this.dateSinistre = dateSinistre;
	}
	public String getAnneeEffetPolice() {
		return anneeEffetPolice;
	}
	public void setAnneeEffetPolice(String anneeEffetPolice) {
		this.anneeEffetPolice = anneeEffetPolice;
	}
	public String getCodeAuxiliaire() {
		return codeAuxiliaire;
	}
	public void setCodeAuxiliaire(String codeAuxiliaire) {
		this.codeAuxiliaire = codeAuxiliaire;
	}
	public String getAdresseBeneficiaire() {
		return adresseBeneficiaire;
	}
	public void setAdresseBeneficiaire(String adresseBeneficiaire) {
		this.adresseBeneficiaire = adresseBeneficiaire;
	}
	public String getVilleBeneficiaire() {
		return villeBeneficiaire;
	}
	public void setVilleBeneficiaire(String villeBeneficiaire) {
		this.villeBeneficiaire = villeBeneficiaire;
	}
	
	public String getIdReglement() {
		return idReglement;
	}
	public void setIdReglement(String idReglement) {
		this.idReglement = idReglement;
	}
	
	
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public IEntiteFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setId(long arg0) {
		// TODO Auto-generated method stub
		
	}

}
