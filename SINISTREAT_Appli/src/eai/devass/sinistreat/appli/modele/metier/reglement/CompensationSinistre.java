package eai.devass.sinistreat.appli.modele.metier.reglement;

import eai.devass.commun.appli.converter.AConverter;

@AConverter(entiteDist = "com.rmawatanya.moyenpaiement.application.metier.valueobjects.CompensationSinistreVO")
public class CompensationSinistre {
	
	@AConverter(propertyDist = "numeroRemise")
	private String numeroRemise;
  
	@AConverter(propertyDist = "mntRegle")
    private double mntRegle;


	public String getNumeroRemise() {
		return numeroRemise;
	}

	public void setNumeroRemise(String numeroRemise) {
		this.numeroRemise = numeroRemise;
	}
	
	public String toString() {
    	return "Compensation Intermediaire";
    }

	public double getMntRegle() {
		return mntRegle;
	}

	public void setMntRegle(double mntRegle) {
		this.mntRegle = mntRegle;
	}

	
   
	
    
}
