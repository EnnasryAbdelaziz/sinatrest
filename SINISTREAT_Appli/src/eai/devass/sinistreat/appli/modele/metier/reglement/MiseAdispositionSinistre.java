package eai.devass.sinistreat.appli.modele.metier.reglement;

import eai.devass.commun.appli.converter.AConverter;


@AConverter(entiteDist = "com.rmawatanya.moyenpaiement.application.metier.valueobjects.MiseAdispositionSinistreVO")
public class MiseAdispositionSinistre {
	
	@AConverter(propertyDist = "mntRegle")
    private double mntRegle;
	@AConverter(propertyDist = "cinBeneficiaire")
    private String cinBeneficiaire;
	@AConverter(propertyDist = "nomBeneficiaireMAD")
    private String nomBeneficiaireMAD;
	
	public String getCinBeneficiaire() {
		return cinBeneficiaire;
	}

	public void setCinBeneficiaire(String cinBeneficiaire) {
		this.cinBeneficiaire = cinBeneficiaire;
	}

	public String getNomBeneficiaireMAD() {
		return nomBeneficiaireMAD;
	}

	public void setNomBeneficiaireMAD(String nomBeneficiaireMAD) {
		this.nomBeneficiaireMAD = nomBeneficiaireMAD;
	}

	public double getMntRegle() {
		return mntRegle;
	}

	public void setMntRegle(double mntRegle) {
		this.mntRegle = mntRegle;
	}
	
	
}
