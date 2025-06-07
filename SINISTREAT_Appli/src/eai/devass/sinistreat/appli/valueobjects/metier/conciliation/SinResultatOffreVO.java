package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.ResultatOffreVO;

public class SinResultatOffreVO implements IValueObject {

	private long id ;
	private String dateRefus;
	private String dateResultat ;
	private String  motifRefus ;
	private ResultatOffreVO  refEtatResultat ;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDateRefus() {
		return dateRefus;
	}
	public void setDateRefus(String dateRefus) {
		this.dateRefus = dateRefus;
	}

	public String getDateResultat() {
		return dateResultat;
	}
	public void setDateResultat(String dateResultat) {
		this.dateResultat = dateResultat;
	}
	public ResultatOffreVO getRefEtatResultat() {
		return refEtatResultat;
	}
	public void setRefEtatResultat(ResultatOffreVO refEtatResultat) {
		this.refEtatResultat = refEtatResultat;
	}
	public String getMotifRefus() {
		return motifRefus;
	}
	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}

}
