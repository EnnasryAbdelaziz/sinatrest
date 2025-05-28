
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import eai.devass.commun.appli.converter.AConverter;


/**
Value Object de MvtDefautRemise
@author Nom Prenom (email)
*/

@AConverter(entiteDist="eai.devass.gsr.appli.modele.metier.mouvements.MvtDefautRemise")
public class MvtDefautRemiseVO extends  MouvementVO {

	private static final long serialVersionUID = 1L;

	@AConverter(propertyDist="action")
	private String actionDR;
	
	@AConverter(propertyDist="totalMontantQuittance")
	private String totalMntQtc;
	
	@AConverter(propertyDist="dateDebutEcheance")
	private String dateDebutEcheanceDR;
	
	@AConverter(propertyDist="dateFinEcheance")
	private String dateFinEcheanceDR;
	
	@AConverter(propertyDist="dateDerniereEcheance")
	private String echeance;
	
	@AConverter(propertyDist="nbrQtcAnnule")
	private String nbrQtcAnnule;
	
	
	public String getActionDR() {
		return actionDR;
	}
	public void setActionDR(String actionDR) {
		this.actionDR = actionDR;
	}
	
	public String getTotalMntQtc() {
		return totalMntQtc;
	}
	public void setTotalMntQtc(String totalMntQtc) {
		this.totalMntQtc = totalMntQtc;
	}
	public String getDateDebutEcheanceDR() {
		return dateDebutEcheanceDR;
	}
	public void setDateDebutEcheanceDR(String dateDebutEcheanceDR) {
		this.dateDebutEcheanceDR = dateDebutEcheanceDR;
	}
	public String getDateFinEcheanceDR() {
		return dateFinEcheanceDR;
	}
	public void setDateFinEcheanceDR(String dateFinEcheanceDR) {
		this.dateFinEcheanceDR = dateFinEcheanceDR;
	}
	public String getEcheance() {
		return echeance;
	}
	public void setEcheance(String echeance) {
		this.echeance = echeance;
	}
	public String getNbrQtcAnnule() {
		return nbrQtcAnnule;
	}
	public void setNbrQtcAnnule(String nbrQtcAnnule) {
		this.nbrQtcAnnule = nbrQtcAnnule;
	}
	
	
	
	
	


}

