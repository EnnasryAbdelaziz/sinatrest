package eai.devass.sinistreat.appli.valueobjects.metier.reglement;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeFuneraireVO;

public class FraisFuneraireVO implements IValueObject {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private ReglementVO refReglement;
	private TypeFuneraireVO refTypeFuneraire;
	private String montantFacture;
	private String montantAregler;

	public FraisFuneraireVO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ReglementVO getRefReglement() {
		return refReglement;
	}

	public void setRefReglement(ReglementVO refReglement) {
		this.refReglement = refReglement;
	}

	public TypeFuneraireVO getRefTypeFuneraire() {
		return refTypeFuneraire;
	}

	public void setRefTypeFuneraire(TypeFuneraireVO refTypeFuneraire) {
		this.refTypeFuneraire = refTypeFuneraire;
	}

	public String getMontantFacture() {
		return montantFacture;
	}

	public void setMontantFacture(String montantFacture) {
		this.montantFacture = montantFacture;
	}

	public String getMontantAregler() {
		return montantAregler;
	}

	public void setMontantAregler(String montantAregler) {
		this.montantAregler = montantAregler;
	}
}
