package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.io.Serializable;
import java.util.Date;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;

@AConverter(entiteDist = "eai.devass.sinistreat.appli.valueobjects.metier.conciliation.SinAnterieurOffreVO")
public class SinAnterieurOffre implements Serializable {

	private long id;
	private String numeroSinistre;
	private Date dateAccident;
	private Double tauxIPP;
	private Offre refOffre; 
	private transient String[] propertiesToConvert;
	private transient Sinistre sinistre;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getNumeroSinistre() {
		return numeroSinistre;
	}

	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}

	public Date getDateAccident() {
		return dateAccident;
	}

	public void setDateAccident(Date dateAccident) {
		this.dateAccident = dateAccident;
	}

	public Double getTauxIPP() {
		return tauxIPP;
	}

	public void setTauxIPP(Double tauxIPP) {
		this.tauxIPP = tauxIPP;
	}

	public Offre getRefOffre() {
		return refOffre;
	}

	public void setRefOffre(Offre refOffre) {
		this.refOffre = refOffre;
	}

	public Sinistre getSinistre() {
		return sinistre;
	}

	public void setSinistre(Sinistre sinistre) {
		this.sinistre = sinistre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateAccident == null) ? 0 : dateAccident.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((numeroSinistre == null) ? 0 : numeroSinistre.hashCode());
		result = prime * result
				+ ((refOffre == null) ? 0 : refOffre.hashCode());
		result = prime * result + ((tauxIPP == null) ? 0 : tauxIPP.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinAnterieurOffre other = (SinAnterieurOffre) obj;
		if (dateAccident == null) {
			if (other.dateAccident != null)
				return false;
		} else if (!dateAccident.equals(other.dateAccident))
			return false;
		if (id != other.id)
			return false;
		if (numeroSinistre == null) {
			if (other.numeroSinistre != null)
				return false;
		} else if (!numeroSinistre.equals(other.numeroSinistre))
			return false;
		if (refOffre == null) {
			if (other.refOffre != null)
				return false;
		} else if (!refOffre.equals(other.refOffre))
			return false;
		if (tauxIPP == null) {
			if (other.tauxIPP != null)
				return false;
		} else if (!tauxIPP.equals(other.tauxIPP))
			return false;
		return true;
	}

}
