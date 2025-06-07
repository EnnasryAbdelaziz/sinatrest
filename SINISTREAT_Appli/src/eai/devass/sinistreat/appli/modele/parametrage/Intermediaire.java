/***********************************************************************
 * Module:  Etat.java
 * Author:  Administrateur
 * Purpose: Defines the Class Etat
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.parametrage;

/** @pdOid 1f8e6c5e-49a3-49b4-8e34-d868502fc3db */
public class Intermediaire implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** @pdOid b39bf1f3-fef4-409f-b168-56ed37970d8a */
	private String code;

	/** @pdOid 1de0474c-1b2b-4dd2-ac43-03723ca0a40e */
	private String libelle;
	private String ville;
	private String adresse;
	private String emailInterm;
	
	public String getEmailInterm() {
		return emailInterm;
	}
	public void setEmailInterm(String emailInterm) {
		this.emailInterm = emailInterm;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	private String codeGuichet;
	private String codeTypeIntermediaire;
	/**
	 * Empty constructor which is required by Hibernate
	 * 
	 */
	public Intermediaire() {
		
	}
	
	public Intermediaire(String code) {
		this.code = code;
	}


	/** @pdOid 8f750112-d133-4a6a-b04e-95b2834e9380 */
	public String getCode() {
		return code;
	}

	/**
	 * @param newCode
	 * @pdOid 8a1520bd-64f2-487e-a13f-d90bfc16f74d
	 */
	public void setCode(String newCode) {
		code = newCode;
	}

	/** @pdOid 0b533268-7c76-4497-af2a-08c6f1c7a58b */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param newLibelle
	 * @pdOid bef4ccfc-5fa8-4c83-a623-52c7a032595f
	 */
	public void setLibelle(String newLibelle) {
		libelle = newLibelle;
	}

	public void setCodeTypeIntermediaire(String codeTypeIntermediaire) {
		this.codeTypeIntermediaire = codeTypeIntermediaire;
	}

	public String getCodeTypeIntermediaire() {
		return codeTypeIntermediaire;
	}

	public String getCodeGuichet() {
		return codeGuichet;
	}

	public void setCodeGuichet(String codeGuichet) {
		this.codeGuichet = codeGuichet;
	}

}