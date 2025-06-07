package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.io.Serializable;
import java.util.Date;

import eai.devass.sinistreat.appli.modele.parametrage.ResultatOffre;

public class SinResultatOffre implements Serializable {

	private String id;
	private Date dateRefus;
	private Date dateResultat;
	private String motifRefus;
	private ResultatOffre refEtatResultat;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateRefus() {
		return dateRefus;
	}

	public void setDateRefus(Date dateRefus) {
		this.dateRefus = dateRefus;
	}

	public Date getDateResultat() {
		return dateResultat;
	}

	public void setDateResultat(Date dateResultat) {
		this.dateResultat = dateResultat;
	}

	public ResultatOffre getRefEtatResultat() {
		return refEtatResultat;
	}

	public void setRefEtatResultat(ResultatOffre refEtatResultat) {
		this.refEtatResultat = refEtatResultat;
	}

	public String getMotifRefus() {
		return motifRefus;
	}

	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}

}
