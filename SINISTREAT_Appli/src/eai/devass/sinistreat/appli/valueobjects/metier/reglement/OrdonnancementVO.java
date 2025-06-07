package eai.devass.sinistreat.appli.valueobjects.metier.reglement;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatOrdVO;

public class OrdonnancementVO implements IValueObject  {

	private static final long serialVersionUID = 7374996884690559809L;
	
	private String id;
	private String numeroOrdonnacement;
	private String modeReglement;
    private String codeUser;
    private String codeSasUser;
	/** vos references */
	private String vosReference;
	/**pour le compte de  */
	private String compteDe;

	private String envoyerA;
	private String nomBeneficiaire;
	private String barre;
	private String detailReglement;
	private String adresse;
	private String ville;
	private String libelleNature;
	/**Montant cheque  */
	private String montant;
	/** service */
	private String codeService;
	private String numCheque;
    
	//compensation
	private String numeroRemise;
    private String mntRegle;
    
    
    //virement
	private String periodicite;
	private String nomClient;
	private String libVirement;
	private String codEtabliClient;
	private String codLocaliteClient;
	private String numCompteClient;
	private String cleRibClient;
	private String codEtabliCompagnie;
	private String codLocaliteCompagnie;
	private String numCompteCompagnie;
	private String cleRibCompagnie;
	
	private List listeReglement;
	private String service;
	
	private EtatOrdVO refEtat;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumeroOrdonnacement() {
		return numeroOrdonnacement;
	}
	public void setNumeroOrdonnacement(String numeroOrdonnacement) {
		this.numeroOrdonnacement = numeroOrdonnacement;
	}
	
	public String getVosReference() {
		return vosReference;
	}
	public void setVosReference(String vosReference) {
		this.vosReference = vosReference;
	}
	public String getCompteDe() {
		return compteDe;
	}
	public void setCompteDe(String compteDe) {
		this.compteDe = compteDe;
	}
	public String getEnvoyerA() {
		return envoyerA;
	}
	public void setEnvoyerA(String envoyerA) {
		this.envoyerA = envoyerA;
	}
	public String getNomBeneficiaire() {
		return nomBeneficiaire;
	}
	public void setNomBeneficiaire(String nomBeneficiaire) {
		this.nomBeneficiaire = nomBeneficiaire;
	}
	public String getBarre() {
		return barre;
	}
	public void setBarre(String barre) {
		this.barre = barre;
	}
	public String getDetailReglement() {
		return detailReglement;
	}
	public void setDetailReglement(String detailReglement) {
		this.detailReglement = detailReglement;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMontant() {
		return montant;
	}
	public void setMontant(String montant) {
		this.montant = montant;
	}
	public String getCodeService() {
		return codeService;
	}
	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}
	public String getNumCheque() {
		return numCheque;
	}
	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}
	public String getNumeroRemise() {
		return numeroRemise;
	}
	public void setNumeroRemise(String numeroRemise) {
		this.numeroRemise = numeroRemise;
	}
	public String getMntRegle() {
		return mntRegle;
	}
	public void setMntRegle(String mntRegle) {
		this.mntRegle = mntRegle;
	}
	public String getPeriodicite() {
		return periodicite;
	}
	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getLibVirement() {
		return libVirement;
	}
	public void setLibVirement(String libVirement) {
		this.libVirement = libVirement;
	}
	public String getCodEtabliClient() {
		return codEtabliClient;
	}
	public void setCodEtabliClient(String codEtabliClient) {
		this.codEtabliClient = codEtabliClient;
	}
	public String getCodLocaliteClient() {
		return codLocaliteClient;
	}
	public void setCodLocaliteClient(String codLocaliteClient) {
		this.codLocaliteClient = codLocaliteClient;
	}
	public String getNumCompteClient() {
		return numCompteClient;
	}
	public void setNumCompteClient(String numCompteClient) {
		this.numCompteClient = numCompteClient;
	}
	public String getCleRibClient() {
		return cleRibClient;
	}
	public void setCleRibClient(String cleRibClient) {
		this.cleRibClient = cleRibClient;
	}
	public String getCodEtabliCompagnie() {
		return codEtabliCompagnie;
	}
	public void setCodEtabliCompagnie(String codEtabliCompagnie) {
		this.codEtabliCompagnie = codEtabliCompagnie;
	}
	public String getCodLocaliteCompagnie() {
		return codLocaliteCompagnie;
	}
	public void setCodLocaliteCompagnie(String codLocaliteCompagnie) {
		this.codLocaliteCompagnie = codLocaliteCompagnie;
	}
	public String getNumCompteCompagnie() {
		return numCompteCompagnie;
	}
	public void setNumCompteCompagnie(String numCompteCompagnie) {
		this.numCompteCompagnie = numCompteCompagnie;
	}
	public String getCleRibCompagnie() {
		return cleRibCompagnie;
	}
	public void setCleRibCompagnie(String cleRibCompagnie) {
		this.cleRibCompagnie = cleRibCompagnie;
	}
	public String getCodeUser() {
		return codeUser;
	}
	public void setCodeUser(String codeUser) {
		this.codeUser = codeUser;
	}
	public String getCodeSasUser() {
		return codeSasUser;
	}
	public void setCodeSasUser(String codeSasUser) {
		this.codeSasUser = codeSasUser;
	}
	public String getLibelleNature() {
		return libelleNature;
	}
	public void setLibelleNature(String libelleNature) {
		this.libelleNature = libelleNature;
	}
	public String getModeReglement() {
		return modeReglement;
	}
	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}
	public List getListeReglement() {
		return listeReglement;
	}
	public void setListeReglement(List listeReglement) {
		this.listeReglement = listeReglement;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	
	public void setRefEtat(EtatOrdVO refEtat) {
		this.refEtat = refEtat;
	}
	public EtatOrdVO getRefEtat() {
		return refEtat;
	}
	
	
}