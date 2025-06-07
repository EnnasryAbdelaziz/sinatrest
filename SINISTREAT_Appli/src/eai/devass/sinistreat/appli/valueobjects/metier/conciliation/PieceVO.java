package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;



/** @pdOid 1f8e6c5e-49a3-49b4-8e34-d868502fc3db */
public class PieceVO implements IValueObject {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String code;

	private String libelle;
	public String getCode() {
		return code;
	}
	public void setCode(String newCode) {
		code = newCode;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String newLibelle) {
		libelle = newLibelle;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	public Long getId() 
	{
		return id;
	}
    
}