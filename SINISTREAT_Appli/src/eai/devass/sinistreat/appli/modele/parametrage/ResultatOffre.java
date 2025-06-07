/***********************************************************************
 * Module:  Etat.java
 * Author:  Administrateur
 * Purpose: Defines the Class Etat
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.parametrage;

/** @pdOid 1f8e6c5e-49a3-49b4-8e34-d868502fc3db */
public class ResultatOffre implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** @pdOid b39bf1f3-fef4-409f-b168-56ed37970d8a */
	private String id;
	/** @pdOid 1de0474c-1b2b-4dd2-ac43-03723ca0a40e */
	private String libelle;
	
	public ResultatOffre() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

}