/***********************************************************************
 * Module:  MoyenPaiement.java
 * Author:  Administrateur
 * Purpose: Defines the Class MoyenPaiement
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.parametrage;

import static eai.devass.sinistreat.appli.utils.validation.Validate.Context.CREATE;
import static eai.devass.sinistreat.appli.utils.validation.Validate.Context.UPDATE;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;


public class MoyenPaiementVO implements IValueObject {
	
	@Validate(autoGenerate=true)
	private String id;
	
	@Validate(obligatoire=true,context={CREATE,UPDATE})
	private String code;
	
	@Validate(obligatoire=true,context={CREATE,UPDATE})
	private String libelle;
	
	private String rib;
	
		
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MoyenPaiementVO() {
		
	}
	
	public MoyenPaiementVO(String code, String libelle) {
		this.code = code;
		this.libelle = libelle;
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

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}
	
		

	
}