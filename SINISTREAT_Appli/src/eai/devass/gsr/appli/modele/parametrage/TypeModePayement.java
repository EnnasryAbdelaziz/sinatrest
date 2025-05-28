/***********************************************************************
 * Module:  Etat.java
 * Author:  Administrateur
 * Purpose: Defines the Class Etat
 ***********************************************************************/

package eai.devass.gsr.appli.modele.parametrage;

/** AZAS */
public class TypeModePayement implements java.io.Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	private String libelle;
	
	private String type;

	/**
	 * Empty constructor which is required by Hibernate
	 * 
	 */
	public TypeModePayement() {
		
	}
	
	public TypeModePayement(String code) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}