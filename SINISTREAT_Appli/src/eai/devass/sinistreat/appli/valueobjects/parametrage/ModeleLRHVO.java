package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;


public class ModeleLRHVO extends ParamATVO implements IValueObject {
	
	@Validate(obligatoire=true)
	private String idModele;
	private String libelle;
	
	public ModeleLRHVO() {
		
	}
	public ModeleLRHVO(String idModele) {
		this.idModele = idModele;
	}
	public ModeleLRHVO(String idModele, String libelle) {
		this.idModele = idModele;
		this.libelle=libelle;
	}	
	public String getIdModele() {
		return idModele;
	}
	public void setIdModele(String idModele) {
		this.idModele = idModele;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}