/***********************************************************************
 * Module:  MoyenPaiement.java
 * Author:  Administrateur
 * Purpose: Defines the Class MoyenPaiement
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele;

import java.io.Serializable;
import java.util.Date;

import eai.devass.sinistreat.appli.modele.parametrage.Prestataire;

/** @pdOid 275aa7b5-2e9b-484f-a81c-6bc69b4ff668 */
public class MoyenPaiement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** @pdOid b6196c24-35be-48e4-bd70-8f370a224a28 */
	private Long id;
	
	/** Code moyen de paiement */
	private String code;
	
	/** Libelle moyen de paiement : Cheque, Virement ... */
	private String libelle;
	
	/** Dans le cas d'un virement */
	private String rib;
	
	private Date dateModification;
	private String codeUser;
	
	private Prestataire refPrestataire;
	

	public MoyenPaiement() {
		
	}
	public MoyenPaiement(Long id) {
		this.id = id;
	}


	/** @pdOid e581e17f-5fb4-4d83-a666-701ea198683a */
	public Long getId() {
		return id;
	}

	/**
	 * @param newId
	 * @pdOid 5e6fe962-8a74-4aec-82d1-4d241c744426
	 */
	public void setId(Long newId) {
		id = newId;
	}

	/** @pdOid a6b3b084-57bb-4ab9-bcf3-e0d997c46021 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param newLibelle
	 * @pdOid 1f0cf4ab-d40c-4531-9f11-26f0ae0929b4
	 */
	public void setLibelle(String newLibelle) {
		libelle = newLibelle;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeUser() {
		return codeUser;
	}
	public void setCodeUser(String codeUser) {
		this.codeUser = codeUser;
	}
	public Date getDateModification() {
		return dateModification;
	}
	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}
	public String getRib() {
		return rib;
	}
	public void setRib(String rib) {
		this.rib = rib;
	}
	public Prestataire getRefPrestataire() {
		return refPrestataire;
	}
	public void setRefPrestataire(Prestataire refPrestataire) {
		this.refPrestataire = refPrestataire;
	}
	
	
	
//	public String[] getUniqueProperty() {
//		return new String[]{"libelle"};
//	}

}