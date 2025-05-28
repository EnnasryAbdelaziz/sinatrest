package eai.devass.sinistreat.appli.modele.metier.reglement;

import eai.devass.commun.appli.converter.AConverter;
import java.util.Date;

@AConverter(entiteDist = "com.rmawatanya.moyenpaiement.application.metier.valueobjects.ChequePrimeVO")

public class ChequePrime {
	/** serialVersionUID */
	private static final long serialVersionUID = -396786987309910770L;
	
	@AConverter(propertyDist = "numCheque")
	private String numCheque;
	
	@AConverter(propertyDist = "ribClient")
	private String ribClient;
	
	@AConverter(propertyDist = "dateEmission")
	private String dateEmission;
	
	@AConverter(propertyDist = "numRemise")
    private String numRemise;
	     
    public String getNumRemise() {
		return numRemise;
	}

	public void setNumRemise(String numRemise) {
			this.numRemise = numRemise;
	}
	
	public String getDateEmission() {
		return dateEmission;
	}
	public void setDateEmission(String dateEmission) {
		this.dateEmission = dateEmission;
	}
	public String getNumCheque() {
		return numCheque;
	}
	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}
	public String getRibClient() {
		return ribClient;
	}
	public void setRibClient(String ribClient) {
		this.ribClient = ribClient;
	}
	
	
		
}
