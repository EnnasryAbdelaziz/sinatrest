/***********************************************************************
 * Module:  EtatMission.java
 * Author:  Administrateur
 * Purpose: Defines the Class EtatMission
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.metier.sinistre;

import java.io.Serializable;
import java.util.Date;

import eai.devass.edition.Utils.beans.VariableBean;
import eai.devass.sinistreat.appli.modele.parametrage.EtatSin;

/**
 * Entité fonctionnelle « Etat d’une mission »
 * 
 * @pdOid 204668b2-76b9-48a3-86cf-2e675c00d751
 */
public class EtatSinistre implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String motifEtat;
	private Date dateEtat;
	private EtatSin refEtat;
	private Sinistre refSinistre;
	private String userCreateur;
	// POur la convertion (VO BO)
	private transient String[] propertiesToConvert;

	public EtatSinistre() {

	}

	public EtatSinistre(EtatSin refEtat) {
		this.refEtat = refEtat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotifEtat() {
		return motifEtat;
	}

	public void setMotifEtat(String motifEtat) {
		this.motifEtat = motifEtat;
	}

	public java.util.Date getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(java.util.Date dateEtat) {
		this.dateEtat = dateEtat;
	}

	public EtatSin getRefEtat() {
		return refEtat;
	}

	public void setRefEtat(EtatSin refEtat) {
		this.refEtat = refEtat;
	}

	public Sinistre getRefSinistre() {
		return refSinistre;
	}

	public void setRefSinistre(Sinistre refSinistre) {
		this.refSinistre = refSinistre;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}

	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	public String getUserCreateur() {
		return userCreateur;
	}

	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}

}