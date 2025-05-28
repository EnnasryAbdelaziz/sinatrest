package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class SinAnterieurVictimeVO implements IValueObject{

	private String id;
	private String numeroSinistre;
	private String dateAccident;
	private String tauxIPP;
	private VictimeVO refVictime;
	private SinistreVO sinistre;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumeroSinistre() {
		return numeroSinistre;
	}
	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}
	public String getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}
	public String getTauxIPP() {
		return tauxIPP;
	}
	public void setTauxIPP(String tauxIPP) {
		this.tauxIPP = tauxIPP;
	}
	public VictimeVO getRefVictime() {
		return refVictime;
	}
	public void setRefVictime(VictimeVO refVictime) {
		this.refVictime = refVictime;
	}
	public SinistreVO getSinistre() {
		return sinistre;
	}
	public void setSinistre(SinistreVO sinistre) {
		this.sinistre = sinistre;
	} 
}
