package eai.devass.gsr.appli.modele.metier.dossierRente;

import java.util.Date;

@SuppressWarnings("all")
public class HistReserveMath implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long idRente;
	private Date dateActualisation;
	private Double age;
	private Double coeffage;
	private Double mntRenteAnnuel;
	private Double reserveprothese;
	private Double reserveMath;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateActualisation() {
		return dateActualisation;
	}
	public void setDateActualisation(Date dateActualisation) {
		this.dateActualisation = dateActualisation;
	}
	public Long getIdRente() {
		return idRente;
	}
	public void setIdRente(Long idRente) {
		this.idRente = idRente;
	}
	public Double getAge() {
		return age;
	}
	public void setAge(Double age) {
		this.age = age;
	}
	public Double getCoeffage() {
		return coeffage;
	}
	public void setCoeffage(Double coeffage) {
		this.coeffage = coeffage;
	}
	public Double getMntRenteAnnuel() {
		return mntRenteAnnuel;
	}
	public void setMntRenteAnnuel(Double mntRenteAnnuel) {
		this.mntRenteAnnuel = mntRenteAnnuel;
	}
	public Double getReserveprothese() {
		return reserveprothese;
	}
	public void setReserveprothese(Double reserveprothese) {
		this.reserveprothese = reserveprothese;
	}
	public Double getReserveMath() {
		return reserveMath;
	}
	public void setReserveMath(Double reserveMath) {
		this.reserveMath = reserveMath;
	}	
	
}
