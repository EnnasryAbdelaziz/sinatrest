package eai.devass.gsr.appli.valueobjects.metier.dossierRente;

import java.util.Date;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

@SuppressWarnings("all")
public class HistReserveMathVO implements IValueObject {
	
	private String id;
	private String idRente;
	private String dateActualisation;
	private String age;
	private String coeffage;
	private String mntRenteAnnuel;
	private String reserveprothese;
	private String reserveMath;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDateActualisation() {
		return dateActualisation;
	}
	public void setDateActualisation(String dateActualisation) {
		this.dateActualisation = dateActualisation;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCoeffage() {
		return coeffage;
	}
	public void setCoeffage(String coeffage) {
		this.coeffage = coeffage;
	}
	public String getMntRenteAnnuel() {
		return mntRenteAnnuel;
	}
	public void setMntRenteAnnuel(String mntRenteAnnuel) {
		this.mntRenteAnnuel = mntRenteAnnuel;
	}
	public String getReserveprothese() {
		return reserveprothese;
	}
	public void setReserveprothese(String reserveprothese) {
		this.reserveprothese = reserveprothese;
	}
	public String getReserveMath() {
		return reserveMath;
	}
	public void setReserveMath(String reserveMath) {
		this.reserveMath = reserveMath;
	}
	public String getIdRente() {
		return idRente;
	}
	public void setIdRente(String idRente) {
		this.idRente = idRente;
	}
	
	
	

}
