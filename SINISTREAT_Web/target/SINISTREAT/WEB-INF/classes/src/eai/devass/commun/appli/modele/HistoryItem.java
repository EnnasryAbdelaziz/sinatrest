package eai.devass.commun.appli.modele;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Calendar;

import org.hibernate.Hibernate;

import eai.devass.commun.appli.util.CommonUtils;

public class HistoryItem implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private long id;
	private long idHistorisable;
	private long numeroVersion;
	private byte[] serialisation;
	private String nomClasse;
	private String utilisateur;
	private Calendar dateVersion;
	private String action;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdHistorisable() {
		return idHistorisable;
	}

	public void setIdHistorisable(long idHistorisable) {
		this.idHistorisable = idHistorisable;
	}

	public long getNumeroVersion() {
		return numeroVersion;
	}

	public void setNumeroVersion(long numeroVersion) {
		this.numeroVersion = numeroVersion;
	}

	public String getNomClasse() {
		return nomClasse;
	}

	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Calendar getDateVersion() {
		return dateVersion;
	}

	public void setDateVersion(Calendar dateVersion) {
		this.dateVersion = dateVersion;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public byte[] getSerialisation() {
		return serialisation;
	}

	public void setSerialisation(byte[] serialisation) {

		byte[] copySerialisation = null;
		if (serialisation != null) {
			copySerialisation = serialisation.clone();
		}

		this.serialisation = copySerialisation;
	}

	// Don't invoke this. Used by Hibernate only.
	public Blob getSerialisationBlob() {
		return Hibernate.createBlob(this.getSerialisation());
	}

	public void setSerialisationBlob(Blob serialisationBlob) {
		this.setSerialisation(CommonUtils.getInstance().toByteArray(
				serialisationBlob));
	}
}
