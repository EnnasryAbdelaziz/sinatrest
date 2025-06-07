/***********************************************************************
 * Module:  TypeEchange.java
 * Author:  Administrateur
 * Purpose: Defines the Class TypeEchange
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;


public class EtatCourrierVO implements IValueObject {
	
	@Validate(obligatoire=true)
	private String idEtat;
	private String libelle;
	
	public EtatCourrierVO() {
		
	}
	public EtatCourrierVO(String idEtat) {
		this.idEtat = idEtat;
	}
	public EtatCourrierVO(String idEtat, String libelle) {
		this.idEtat = idEtat;
		this.libelle=libelle;
	}
	public String getIdEtat() {
		return idEtat;
	}
	public void setIdEtat(String idEtat) {
		this.idEtat = idEtat;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}