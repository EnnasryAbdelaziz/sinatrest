package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class TypeProcedureVO  implements IValueObject{
	
	private Long id;
	private String libelle;
	public Long getId() {
		return id;
	}
	public TypeProcedureVO() {
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
