package eai.devass.gsr.appli.modele.metier.mouvements;
 
import java.util.Date;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;





/**
 MvtDefautRemise:  
@author Nom Prenom (email)
*/

@AConverter(entiteMapping="eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtDefautRemiseVO")
public class MvtDefautRemise extends  Mouvement implements IEntite, ILockable          {

private static final long serialVersionUID = 1L;

	private String action;
	private Double totalMontantQuittance;
	private Date dateDebutEcheance;
	private Date dateFinEcheance;
	private Date dateDerniereEcheance;
	private Integer nbrQtcAnnule;
		
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Double getTotalMontantQuittance() {
		return totalMontantQuittance;
	}
	public void setTotalMontantQuittance(Double totalMontantQuittance) {
		this.totalMontantQuittance = totalMontantQuittance;
	}
	public Date getDateDebutEcheance() {
		return dateDebutEcheance;
	}
	public void setDateDebutEcheance(Date dateDebutEcheance) {
		this.dateDebutEcheance = dateDebutEcheance;
	}
	public Date getDateFinEcheance() {
		return dateFinEcheance;
	}
	public void setDateFinEcheance(Date dateFinEcheance) {
		this.dateFinEcheance = dateFinEcheance;
	}
	public Date getDateDerniereEcheance() {
		return dateDerniereEcheance;
	}
	public void setDateDerniereEcheance(Date dateDerniereEcheance) {
		this.dateDerniereEcheance = dateDerniereEcheance;
	}
	public Integer getNbrQtcAnnule() {
		return nbrQtcAnnule;
	}
	public void setNbrQtcAnnule(Integer nbrQtcAnnule) {
		this.nbrQtcAnnule = nbrQtcAnnule;
	}
	
	
	

}

