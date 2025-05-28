/***********************************************************************
 * Module:  EtatMissionVO.java
 * Author:  Administrateur
 * Purpose: Defines the Class EtatMissionVO
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.metier.reglement;

import java.io.Serializable;
import java.util.Date;

import eai.devass.sinistreat.appli.modele.parametrage.EtatRgl;


/**
 * Entité fonctionnelle « Etat d’une mission »
 * 
 * @pdOid 204668b2-76b9-48a3-86cf-2e675c00d751
 */
public class EtatReglement implements Serializable {
	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String motifEtat;
	private Date dateEtat;
	private String utilisateurCreateur;
	private EtatRgl refEtat;
	private Reglement refReglement;
	private String reference;
	private String modeReglement;

	public EtatReglement() {
		
	}
	
	public EtatReglement(EtatRgl refEtat) {
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

	public Date getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(java.util.Date dateEtat) {
		this.dateEtat = dateEtat;
	}

	public String getUtilisateurCreateur() {
		return utilisateurCreateur;
	}

	public void setUtilisateurCreateur(String utilisateurCreateur) {
		this.utilisateurCreateur = utilisateurCreateur;
	}

	public EtatRgl getRefEtat() {
		return refEtat;
	}

	public void setRefEtat(EtatRgl refEtat) {
		this.refEtat = refEtat;
	}

	public Reglement getRefReglement() {
		return refReglement;
	}

	public void setRefReglement(Reglement refReglement) {
		this.refReglement = refReglement;
	}








	

}