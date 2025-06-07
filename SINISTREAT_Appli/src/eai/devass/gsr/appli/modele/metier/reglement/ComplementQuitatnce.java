package eai.devass.gsr.appli.modele.metier.reglement;
 
import java.util.Date;

import eai.devass.commun.appli.modele.EntiteBO;





public class ComplementQuitatnce extends EntiteBO       {

	private static final long serialVersionUID = 1L;
	private Date dateDebut;
	private Date dateFin;
	private String detailMotif;
	private Double montantCommission;
	
	
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public String getDetailMotif() {
		return detailMotif;
	}
	public void setDetailMotif(String detailMotif) {
		this.detailMotif = detailMotif;
	}
	public Double getMontantCommission() {
		return montantCommission;
	}
	public void setMontantCommission(Double montantCommission) {
		this.montantCommission = montantCommission;
	}
	
	
	

}

