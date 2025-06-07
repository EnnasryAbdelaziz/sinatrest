package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;


public class PalierVO implements IValueObject {
	
	private String id;

	/** @pdOid 1de0474c-1b2b-4dd2-ac43-03723ca0a40e */
	private String dateDebut;
	private String dateFin;
	private String salaireMinLeg;
	private String palier1;
	private String palier2;
	
	
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	public String getSalaireMinLeg() {
		return salaireMinLeg;
	}
	public void setSalaireMinLeg(String salaireMinLeg) {
		this.salaireMinLeg = salaireMinLeg;
	}
	public String getPalier1() {
		return palier1;
	}
	public void setPalier1(String palier1) {
		this.palier1 = palier1;
	}
	public String getPalier2() {
		return palier2;
	}
	public void setPalier2(String palier2) {
		this.palier2 = palier2;
	}
	

	
	
}