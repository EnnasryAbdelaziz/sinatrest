/***********************************************************************
 * Module:  Etat.java
 * Author:  Administrateur
 * Purpose: Defines the Class Etat
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.parametrage;

import java.util.Date;

/** @pdOid 1f8e6c5e-49a3-49b4-8e34-d868502fc3db */
public class Palier implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/** @pdOid b39bf1f3-fef4-409f-b168-56ed37970d8a */
	private Long id;

	/** @pdOid 1de0474c-1b2b-4dd2-ac43-03723ca0a40e */
	private Date dateDebut;
	private Date dateFin;
	private Double salaireMinLeg;
	private Double palier1;
	private Double palier2;

	/**
	 * Empty constructor which is required by Hibernate
	 * 
	 */
	public Palier() {
		
	}
	
	public Palier(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Double getSalaireMinLeg() {
		return salaireMinLeg;
	}

	public void setSalaireMinLeg(Double salaireMinLeg) {
		this.salaireMinLeg = salaireMinLeg;
	}

	public Double getPalier1() {
		return palier1;
	}

	public void setPalier1(Double palier1) {
		this.palier1 = palier1;
	}

	public Double getPalier2() {
		return palier2;
	}

	public void setPalier2(Double palier2) {
		this.palier2 = palier2;
	}
	
	
	

}