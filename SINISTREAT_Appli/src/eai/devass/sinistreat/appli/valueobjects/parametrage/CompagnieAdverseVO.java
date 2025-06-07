package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class CompagnieAdverseVO implements IValueObject{
	/** @pdOid b39bf1f3-fef4-409f-b168-56ed37970d8a */
	private String code;

	/** @pdOid 1de0474c-1b2b-4dd2-ac43-03723ca0a40e */
	private String libelle;
	
	/** @pdOid 1de0474c-1b2b-4dd2-ac43-03723ca0a40e */
	private String adresse;

	private String ville;

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
}
