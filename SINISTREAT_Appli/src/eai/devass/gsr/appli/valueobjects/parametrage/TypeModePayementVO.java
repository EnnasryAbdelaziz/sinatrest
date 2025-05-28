
package eai.devass.gsr.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;


/**
Value Object de EtatRentier
@author AZAS (email)
*/
public class TypeModePayementVO implements IValueObject{

	private long code;
	private static final long serialVersionUID = 1L;

	private String libelle;
	
	private String type;

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

