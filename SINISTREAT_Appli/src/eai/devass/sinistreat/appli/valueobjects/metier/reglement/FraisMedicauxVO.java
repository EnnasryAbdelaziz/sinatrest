package eai.devass.sinistreat.appli.valueobjects.metier.reglement;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeFraisVO;

public class FraisMedicauxVO implements IValueObject {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private ReglementVO refReglement;
	private TypeFraisVO refTypeFrais;
	private String montantFacture;
	private String montantAregler;

	public FraisMedicauxVO() {
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

	public TypeFraisVO getRefTypeFrais() {
		return refTypeFrais;
	}

	public void setRefTypeFrais(TypeFraisVO refTypeFrais) {
		this.refTypeFrais = refTypeFrais;
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
