package eai.devass.sinistreat.appli.modele.metier.reglement;

import java.util.Date;

import eai.devass.commun.appli.converter.AConverter;

@AConverter(entiteDist = "com.rmawatanya.moyenpaiement.application.metier.valueobjects.ChequeSinistreBGDVO")

public class ChequeSinistreBGD {

	/** vos references */
	@AConverter(propertyDist = "numCheque")
	private String numCheque;
	
	/**pour le compte de  */
	@AConverter(propertyDist = "codeBanque")
	private String codeBanque;
	
	/** a envoyer A1,A2,A3 et A4 ???  */
	@AConverter(propertyDist = "dateReglement")
	private Date dateReglement;

	public String getNumCheque() {
		return numCheque;
	}

	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}

	public String getCodeBanque() {
		return codeBanque;
	}

	public void setCodeBanque(String codeBanque) {
		this.codeBanque = codeBanque;
	}

	public Date getDateReglement() {
		return dateReglement;
	}

	public void setDateReglement(Date date) {
		this.dateReglement = date;
	}


}
