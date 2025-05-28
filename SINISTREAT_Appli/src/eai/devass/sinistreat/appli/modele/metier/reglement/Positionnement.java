package eai.devass.sinistreat.appli.modele.metier.reglement;

import java.util.List;

import com.rmawatanya.reglement.application.metier.usecases.services.quittance.IEmissionQuittance;

import eai.devass.commun.appli.converter.AConverter;

/**
 * SuppressWarnings("unused"): champs avec valeurs fixes necessaires pour la
 * conversion au type quittance du service de quittancement
 */
@SuppressWarnings("unused")
@AConverter(entiteDist = "com.rmawatanya.moyenpaiement.application.metier.valueobjects.PositionnementVO")
public class Positionnement {

	private static final long serialVersionUID = 7374996884690559809L;

	public enum TypeDestinataire { ASSURE, AUXILLIAIRE, INTERMEDIAIRE, AUTRE};
	
	@AConverter(propertyDist = "codeDestinataire")
	private String codeDestinataire;
	@AConverter(propertyDist = "typeDestinataire")
	private TypeDestinataire typeDestinataire;
	@AConverter(propertyDist = "mntTotalQuittance")
	private String mntTotalQuittance;
	@AConverter(propertyDist = "codeService")
	private String codeService;
	@AConverter(propertyDist = "codDevise")
	private String codDevise;
	@AConverter(propertyDist = "utilisateur")
	private String utilisateur;
	@AConverter(propertyDist = "nomBeneficiaire")
	private String nomBeneficiaire;
	@AConverter(propertyDist = "refMoyenPaiement")
	private List<CompensationSinistre> refMoyenPaiement;
	@AConverter(propertyDist = "refQuittance")
	private List<QuittanceMoyenPaiement> refQuittance;
	public String getCodeDestinataire() {
		return codeDestinataire;
	}
		
	

	public void setCodeDestinataire(String codeDestinataire) {
		this.codeDestinataire = codeDestinataire;
	}

	public TypeDestinataire getTypeDestinataire() {
		return typeDestinataire;
	}

	public void setTypeDestinataire(TypeDestinataire typeDestinataire) {
		this.typeDestinataire = typeDestinataire;
	}

	public String getMntTotalQuittance() {
		return mntTotalQuittance;
	}

	public void setMntTotalQuittance(String mntTotalQuittance) {
		this.mntTotalQuittance = mntTotalQuittance;
	}

	public String getCodeService() {
		return codeService;
	}

	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}

	public String getCodDevise() {
		return IEmissionQuittance.CODE_DEVISE_MAD;
	}

	public void setCodDevise(String codDevise) {
		this.codDevise = codDevise;
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getNomBeneficiaire() {
		return nomBeneficiaire;
	}

	public void setNomBeneficiaire(String nomBeneficiaire) {
		this.nomBeneficiaire = nomBeneficiaire;
	}

	public List getRefMoyenPaiement() {
		return refMoyenPaiement;
	}

	public void setRefMoyenPaiement(List refMoyenPaiement) {
		this.refMoyenPaiement = refMoyenPaiement;
	}

	public List getRefQuittance() {
		return refQuittance;
	}

	public void setRefQuittance(List refQuittance) {
		this.refQuittance = refQuittance;
	}

}