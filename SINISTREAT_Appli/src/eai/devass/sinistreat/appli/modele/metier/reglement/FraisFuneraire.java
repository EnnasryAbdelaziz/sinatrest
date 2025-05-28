package eai.devass.sinistreat.appli.modele.metier.reglement;

import java.io.Serializable;

import eai.devass.sinistreat.appli.modele.parametrage.TypeFuneraire;

public class FraisFuneraire implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Reglement refReglement;
	private TypeFuneraire refTypeFuneraire;
	private Double montantFacture;
	private Double montantAregler;

	public FraisFuneraire() {
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

	

	public TypeFuneraire getRefTypeFuneraire() {
		return refTypeFuneraire;
	}

	public void setRefTypeFuneraire(TypeFuneraire refTypeFuneraire) {
		this.refTypeFuneraire = refTypeFuneraire;
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
