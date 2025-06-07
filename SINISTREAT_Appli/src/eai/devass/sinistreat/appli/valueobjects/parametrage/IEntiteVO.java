package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;
import eai.devass.sinistreat.appli.utils.validation.Validate.Context;
import eai.devass.sinistreat.appli.utils.validation.Validate.Type;

/* @author kchakib : 6 oct. 10 */
public class IEntiteVO implements IValueObject {
	
	@Validate(autoGenerate=true, type=Type.LONG)
	private String id;
	
	@Validate(obligatoire=true, type=Type.DATE, context={Context.CREATE, Context.UPDATE})
	private String dateDebutValidite;

	@Validate(obligatoire=true, type=Type.DATE, context={Context.CREATE, Context.UPDATE})
	private String dateFinValidite;

	public boolean canConvertVOtoBO(){
		return true;
	}
	
	
	public IEntiteVO() {
		
	}
	
	public IEntiteVO(String id) {
		this.id = id;
	}

	public String getDateDebutValidite() {
		return dateDebutValidite;
	}

	public void setDateDebutValidite(String dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}

	public String getDateFinValidite() {
		return dateFinValidite;
	}

	public void setDateFinValidite(String dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	

}


