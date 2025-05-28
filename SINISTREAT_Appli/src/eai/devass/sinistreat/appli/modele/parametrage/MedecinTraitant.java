package eai.devass.sinistreat.appli.modele.parametrage;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;

public class MedecinTraitant implements IEntite{
	
	private static final long serialVersionUID = 1L;
	
	private String code;//c
	
	private String type;//c
	
	private String nomRaisonSocial; //c
	
	private String adresse; //c
	
	private String telephone;//c
	
	private String modeReglement ;//c
	
	private DomainePrest refDomaineActivite;
	
	private ActivitePrest refActivite;

	private Ville refVille;
	//private String refVille;
	
	private String libelleModeReglement ;//c
	
	private String mobile;
	
	private String numeroRib;
	
	private String montantForfait;
		
	private Boolean etatConnexion;
	
	private String mail;
	
	private String codeMandataire;
	
	private String nomMandataire;

	public MedecinTraitant() {
		
	}
	
	public MedecinTraitant(String code) {
		this.code = code;
	}


	/** @pdOid 8f750112-d133-4a6a-b04e-95b2834e9380 */
	public String getCode() {
		return code;
	}

	/**
	 * @param newCode
	 * @pdOid 8a1520bd-64f2-487e-a13f-d90bfc16f74d
	 */
	public void setCode(String newCode) {
		code = newCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNomRaisonSocial() {
		return nomRaisonSocial;
	}

	public void setNomRaisonSocial(String nomRaisonSocial) {
		this.nomRaisonSocial = nomRaisonSocial;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

//	public String getFax() {
//		return fax;
//	}
//
//	public void setFax(String fax) {
//		this.fax = fax;
//	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public String getLibelleModeReglement() {
		return libelleModeReglement;
	}

	public void setLibelleModeReglement(String libelleModeReglement) {
		this.libelleModeReglement = libelleModeReglement;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNumeroRib() {
		return numeroRib;
	}

	public void setNumeroRib(String numeroRib) {
		this.numeroRib = numeroRib;
	}

	public String getMontantForfait() {
		return montantForfait;
	}

	public void setMontantForfait(String montantForfait) {
		this.montantForfait = montantForfait;
	}

	public Boolean getEtatConnexion() {
		return etatConnexion;
	}

	public void setEtatConnexion(Boolean etatConnexion) {
		this.etatConnexion = etatConnexion;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public EntiteFactory getFactory() {
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

	public void setRefDomaineActivite(DomainePrest refDomaineActivite) {
		this.refDomaineActivite = refDomaineActivite;
	}

	public DomainePrest getRefDomaineActivite() {
		return refDomaineActivite;
	}

	public void setRefActivite(ActivitePrest refActivite) {
		this.refActivite = refActivite;
	}

	public ActivitePrest getRefActivite() {
		return refActivite;
	}

	
//	public String getRefVille() {
//		return refVille;
//	}
//
//	public void setRefVille(String refVille) {
//		this.refVille = refVille;
//	}

	public Ville getRefVille() {
		return refVille;
	}

	public void setRefVille(Ville refVille) {
		this.refVille = refVille;
	}

	public String getCodeMandataire() {
		return codeMandataire;
	}

	public void setCodeMandataire(String codeMandataire) {
		this.codeMandataire = codeMandataire;
	}

	public String getNomMandataire() {
		return nomMandataire;
	}

	public void setNomMandataire(String nomMandataire) {
		this.nomMandataire = nomMandataire;
	}


}