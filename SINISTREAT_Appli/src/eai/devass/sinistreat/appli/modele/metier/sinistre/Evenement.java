package eai.devass.sinistreat.appli.modele.metier.sinistre;

import java.io.Serializable;
import java.util.Date;

import eai.devass.sinistreat.appli.modele.parametrage.Cause;
import eai.devass.sinistreat.appli.modele.parametrage.TypeAccident;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;
import eai.devass.sinistreat.appli.modele.parametrage.Zone;

public class Evenement implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String numeroEvenement;
	private Boolean firstEvenement;
	private Date dateAccident;
	private TypeAccident refTypeAccident;
	private String lieuAccident;
	private Zone refZone;
	private Cause refCause;
	private Ville refVille;
	private Date dateCreation;
	private transient Date dateAccidentDebut;
	private transient Date dateAccidentFin;
	private Date heureAccident;
	/* MFBO@Evolution:456 */
	private String detailsCause;

	// POur la convertion (VO BO)
	private transient String[] propertiesToConvert;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroEvenement() {
		// if(StringUtils.isEmpty(this.numeroEvenement)) {
		// this.numeroEvenement="";
		// }
		return this.numeroEvenement;
	}

	public void setNumeroEvenement(String numeroEvenement) {
		// if(StringUtils.isEmpty(numeroEvenement)) {
		// numeroEvenement="";
		// }
		this.numeroEvenement = numeroEvenement;
	}

	public Boolean getFirstEvenement() {
		return firstEvenement;
	}

	public void setFirstEvenement(Boolean firstEvenement) {
		this.firstEvenement = firstEvenement;
	}

	public Date getDateAccident() {
		return dateAccident;
	}

	public void setDateAccident(Date dateAccident) {
		this.dateAccident = dateAccident;
	}

	public TypeAccident getRefTypeAccident() {
		return refTypeAccident;
	}

	public void setRefTypeAccident(TypeAccident refTypeAccident) {
		this.refTypeAccident = refTypeAccident;
	}

	public String getLieuAccident() {
		return lieuAccident;
	}

	public void setLieuAccident(String lieuAccident) {
		this.lieuAccident = lieuAccident;
	}

	public Zone getRefZone() {
		return refZone;
	}

	public void setRefZone(Zone refZone) {
		this.refZone = refZone;
	}

	public Cause getRefCause() {
		return refCause;
	}

	public void setRefCause(Cause refCause) {
		this.refCause = refCause;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Ville getRefVille() {
		return refVille;
	}

	public void setRefVille(Ville refVille) {
		this.refVille = refVille;
	}

	public Date getDateAccidentDebut() {
		return dateAccidentDebut;
	}

	public void setDateAccidentDebut(Date dateAccidentDebut) {
		this.dateAccidentDebut = dateAccidentDebut;
	}

	public Date getDateAccidentFin() {
		return dateAccidentFin;
	}

	public void setDateAccidentFin(Date dateAccidentFin) {
		this.dateAccidentFin = dateAccidentFin;
	}

	public Date getHeureAccident() {
		return heureAccident;
	}

	public void setHeureAccident(Date heureAccident) {
		this.heureAccident = heureAccident;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}

	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;

	}

	/**
	 * @return the detailsCause
	 */
	public String getDetailsCause() {
		return detailsCause;
	}

	/**
	 * @param detailsCause
	 *            the detailsCause to set
	 */
	public void setDetailsCause(String detailsCause) {
		this.detailsCause = detailsCause;
	}

}