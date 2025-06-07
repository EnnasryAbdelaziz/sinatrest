/***********************************************************************
 * Module:  Prestataire.java
 * Author:  Administrateur
 * Purpose: Defines the Class Prestataire
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;

public class PrestataireVO implements IValueObject {

	/** Type Prestataire M:Moral et P:Physique */
	public enum TypePrestataire {
		M, P
	};

	@Validate(autoGenerate = true)
	private String code;// c

	private String type;// c

	private String nomRaisonSocial; // c

	private String adresse; // c

	private String telephone;// c

	private String fax; // c

	private String modeReglement;// c

	private ActivitePrestVO refActivite;
	private DomainePrestVO refDomaineActivite;
	private VilleVO refVille;
	private String mobile;

	private String libelleModeReglement;// c

	private String numeroRib;

	private String montantForfait;

	private Boolean etatConnexion;

	private String mail;

	// @Validate(obligatoire=true,type=Type.REF,context={CREATE,UPDATE})
	// private ActivitePrestataireVO refActivite = new ActivitePrestataireVO();
	//
	// @Validate(obligatoire=true,type=Type.LIST,context={CREATE})
	// private List<MoyenPaiementVO> refMoyenPaiements = new
	// ArrayList<MoyenPaiementVO>();
	//
	// @Validate(obligatoire=true,type=Type.REF,context={CREATE,UPDATE})
	// private PeriodiciteReglementVO refPeriodiciteReglement = new
	// PeriodiciteReglementVO();
	//
	// @Validate(type=Type.REF)
	// private BanqueVO refBanque = new BanqueVO();
	//
	// @Validate(obligatoire=true,type=Type.REF,context={CREATE,UPDATE})
	// private TypeRelationCompagnieVO refTypeRelationCompagnie = new
	// TypeRelationCompagnieVO();
	//
	// @Validate(obligatoire=true,type=Type.REF,context={CREATE,UPDATE})
	// private EtatCollaborationVO refEtatCollaboration = new
	// EtatCollaborationVO();
	//
	// @Validate(obligatoire=true,type=Type.REF,context={CREATE,UPDATE})
	// private PaysVO refPays = new PaysVO();
	//
	// @Validate(type=Type.REF,context={CREATE,UPDATE})
	// private RegionVO refRegion= new RegionVO();
	//
	// @Validate(obligatoire=true,type=Type.REF,context={CREATE,UPDATE})
	// private VilleVO refVille= new VilleVO();
	//
	// @Validate(type=Type.REF)
	// private PrestataireVO refMandataire;
	//
	// @Validate(type=Type.LIST,context={CREATE,UPDATE})
	// private List<DocumentIdentitePrestataireVO> refDocumentIdentite = new
	// ArrayList<DocumentIdentitePrestataireVO>();
	//
	// @Validate(type=Type.REF)
	// private QuartierVO refQuartier = new QuartierVO();

	private String codeMandataire;

	private String nomMandataire;

	private String nom;
	private String prenom;
	
    private String ras;
	
	public String getRas() {
		return ras;
	}

	public void setRas(String ras) {
		this.ras = ras;
	}

	public PrestataireVO() {

	}

	public PrestataireVO(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public void setRefVille(VilleVO refVille) {
		this.refVille = refVille;
	}

	public VilleVO getRefVille() {
		return refVille;
	}

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

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setRefDomaineActivite(DomainePrestVO refDomaineActivite) {
		this.refDomaineActivite = refDomaineActivite;
	}

	public DomainePrestVO getRefDomaineActivite() {
		return refDomaineActivite;
	}

	public void setRefActivite(ActivitePrestVO refActivite) {
		this.refActivite = refActivite;
	}

	public ActivitePrestVO getRefActivite() {
		return refActivite;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}