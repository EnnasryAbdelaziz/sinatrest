package eai.devass.sinistreat.appli.valueobjects.metier.reglement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

/** kchakib : 21/05/2009 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class DetailReglementVO implements IValueObject {
	private static final long serialVersionUID = 0xac68a65771271ac0L;
	private String id;
	private String codePrestation;
	private String libellePrestation;
	private String codeGarantie;
	private String montant;
	private String montantRejete;
	private String dateDebutPrestation;
	private String dateFinPrestation;
	private String contentieux;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodePrestation() {
		return codePrestation;
	}

	public void setCodePrestation(String codePrestation) {
		this.codePrestation = codePrestation;
	}

	public String getLibellePrestation() {
		return libellePrestation;
	}

	public void setLibellePrestation(String libellePrestation) {
		this.libellePrestation = libellePrestation;
	}

	public String getCodeGarantie() {
		return codeGarantie;
	}

	public void setCodeGarantie(String codeGarantie) {
		this.codeGarantie = codeGarantie;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

	public String getMontantRejete() {
		return montantRejete;
	}

	public void setMontantRejete(String montantRejete) {
		this.montantRejete = montantRejete;
	}

	public String getDateDebutPrestation() {
		return dateDebutPrestation;
	}

	public void setDateDebutPrestation(String dateDebutPrestation) {
		this.dateDebutPrestation = dateDebutPrestation;
	}

	public String getDateFinPrestation() {
		return dateFinPrestation;
	}

	public void setDateFinPrestation(String dateFinPrestation) {
		this.dateFinPrestation = dateFinPrestation;
	}

	public String getContentieux() {
		return contentieux;
	}

	public void setContentieux(String contentieux) {
		this.contentieux = contentieux;
	}

}
