package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.io.Serializable;
import java.util.Date;

import eai.devass.sinistreat.appli.modele.parametrage.MotifOffre;

@SuppressWarnings("all")
public class SinMotifOffre implements Serializable {

	private long id;
	private Date dateEtat;
	private String userCreateur;
	private Offre refOffre;
	private MotifOffre refMotifOffre;	
	private transient String[] propertiesToConvert;
	private String codeSas;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(Date dateEtat) {
		this.dateEtat = dateEtat;
	}

	public String getUserCreateur() {
		return userCreateur;
	}

	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}

	public Offre getRefOffre() {
		return refOffre;
	}

	public void setRefOffre(Offre refOffre) {
		this.refOffre = refOffre;
	}

	public MotifOffre getRefMotifOffre() {
		return refMotifOffre;
	}

	public void setRefMotifOffre(MotifOffre refMotifOffre) {
		this.refMotifOffre = refMotifOffre;
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

	public String getCodeSas() {
		return codeSas;
	}

	public void setCodeSas(String codeSas) {
		this.codeSas = codeSas;
	}

}
