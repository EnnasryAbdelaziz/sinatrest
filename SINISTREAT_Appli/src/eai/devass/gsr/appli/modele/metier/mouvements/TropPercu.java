package eai.devass.gsr.appli.modele.metier.mouvements;

import java.util.Date;

import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
 


public class TropPercu extends  EntiteBO {

	private static final long serialVersionUID = 1L;
	private Double mntTropPercu;
	private Double mntRecuperer;
	private Date dateOperation;
	private Mouvement refMouvement;
	private Rentier refRentier;
	
	
	
	public Double getMntTropPercu() {
		return mntTropPercu;
	}
	public void setMntTropPercu(Double mntTropPercu) {
		this.mntTropPercu = mntTropPercu;
	}
	public Double getMntRecuperer() {
		return mntRecuperer;
	}
	public void setMntRecuperer(Double mntRecuperer) {
		this.mntRecuperer = mntRecuperer;
	}
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}
	public Mouvement getRefMouvement() {
		return refMouvement;
	}
	public void setRefMouvement(Mouvement refMouvement) {
		this.refMouvement = refMouvement;
	}
	public Rentier getRefRentier() {
		return refRentier;
	}
	public void setRefRentier(Rentier refRentier) {
		this.refRentier = refRentier;
	}
	
	
	
	

}

