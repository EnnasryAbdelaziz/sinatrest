package eai.devass.sinistreat.appli.modele.metier.reglement;

import java.io.Serializable;

import eai.devass.sinistreat.appli.modele.parametrage.TypeFrais;

public class FraisMedicaux implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Reglement refReglement;
	private TypeFrais refTypeFrais;
	private Double montantFacture;
	private Double montantAregler;

	public FraisMedicaux() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reglement getRefReglement() {
		return refReglement;
	}

	public void setRefReglement(Reglement refReglement) {
		this.refReglement = refReglement;
	}

	public TypeFrais getRefTypeFrais() {
		return refTypeFrais;
	}

	public void setRefTypeFrais(TypeFrais refTypeFrais) {
		this.refTypeFrais = refTypeFrais;
	}

	public Double getMontantFacture() {
		return montantFacture;
	}

	public void setMontantFacture(Double montantFacture) {
		this.montantFacture = montantFacture;
	}

	public Double getMontantAregler() {
		return montantAregler;
	}

	public void setMontantAregler(Double montantAregler) {
		this.montantAregler = montantAregler;
	}

}
