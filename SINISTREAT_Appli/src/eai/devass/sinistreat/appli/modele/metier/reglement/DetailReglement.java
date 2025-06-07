package eai.devass.sinistreat.appli.modele.metier.reglement;

import java.io.Serializable;
import java.util.Date;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.sinistreat.appli.modele.parametrage.Prestation;

/**
 * SuppressWarnings("unused"): champs avec valeurs fixes necessaires pour la
 * conversion au type quittance du service de quittancement
 */
@SuppressWarnings("unused")
@AConverter(entiteDist = "com.rmawatanya.reglement.application.metier.valueobjects.DetailQuittanceVO")
public class DetailReglement implements Serializable {
	public static final String PRESTATION = "PRESTATION";
	public static final String DEDUCTION = "DEDUCTION";

	/** serialVersionUID */
	private static final long serialVersionUID = -6023381607117284672L;
	private Long id;
	private Reglement refReglement;
	@AConverter(propertyDist = "codePrestation", propertyOrig = "refPrestation.codePrestataionQtc")
	private Prestation refPrestation;

	@AConverter(propertyDist = "prestation")
	private String libellePrestation;
	private Double montant;
	private Double montantRejete;
	// Champs necessaire pour le mapping avec la quittance du service
	@AConverter(propertyDist = "numQuittance")
	private transient String numQuittance;
	@AConverter(propertyDist = "mntPrestation")
	private transient Double mntPrestation;
	@AConverter(propertyDist = "type")
	private transient String type;
	@AConverter(propertyDist = "codeGarantie")
	private String codeGarantie;

	@AConverter(propertyDist = "dateDebutPrestation")
	private Date dateDebutPrestation;
	@AConverter(propertyDist = "dateFinPrestation")
	private Date dateFinPrestation;

	private transient String codePrestation;
	@AConverter(propertyDist = "contentieux")
	private Boolean contentieux;

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getCodeGarantie() {
		if (this.refReglement != null
				&& this.refReglement.getRefSinistre() != null
				&& this.refReglement.getRefSinistre().getRefTypeGarantie() != null) {
			return this.refReglement.getRefSinistre().getRefTypeGarantie()
					.getCode();
		}
		return codeGarantie;
	}

	public void setCodeGarantie(String codeGarantie) {
		this.codeGarantie = codeGarantie;
	}

	public Prestation getRefPrestation() {
		return refPrestation;
	}

	public void setRefPrestation(Prestation refPrestation) {
		this.refPrestation = refPrestation;
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

	public void setLibellePrestation(String libellePrestation) {
		this.libellePrestation = libellePrestation;
	}

	public String getLibellePrestation() {
		return libellePrestation;
	}

	public void setMontantRejete(Double montantRejete) {
		this.montantRejete = montantRejete;
	}

	public Double getMontantRejete() {
		return montantRejete;
	}

	public String getNumQuittance() {
		return this.refReglement.getNumeroQuittance();
	}

	public void setNumQuittance(String numQuittance) {
		this.numQuittance = numQuittance;
	}

	public Double getMntPrestation() {
		if (this.montantRejete != null) {
			this.mntPrestation = this.montant - this.montantRejete;
		} else {
			this.mntPrestation = this.montant;
		}
		return mntPrestation;
	}

	public void setMntPrestation(Double mntPrestation) {
		this.mntPrestation = mntPrestation;
	}

	public Date getDateDebutPrestation() {
		return dateDebutPrestation;
	}

	public void setDateDebutPrestation(Date dateDebutPrestation) {
		this.dateDebutPrestation = dateDebutPrestation;
	}

	public Date getDateFinPrestation() {
		return dateFinPrestation;
	}

	public void setDateFinPrestation(Date dateFinPrestation) {
		this.dateFinPrestation = dateFinPrestation;
	}

    public String getType() {
		
		if(getCodePrestation().equals("83")) {
			return DEDUCTION;
		}
		return PRESTATION;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCodePrestation() {
		if (refPrestation != null) {
			return refPrestation.getCode();
		}

		return codePrestation;
	}

	public void setCodePrestation(String codePrestation) {
		this.codePrestation = codePrestation;

		if (codePrestation != null) {
			this.refPrestation = new Prestation(codePrestation);
		}

	}

	public Boolean getContentieux() {
		return contentieux;
	}

	public void setContentieux(Boolean contentieux) {
		this.contentieux = contentieux;
	}

}
