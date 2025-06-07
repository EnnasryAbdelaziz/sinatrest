/***********************************************************************
 * Module:  TypeEchange.java
 * Author:  Administrateur
 * Purpose: Defines the Class TypeEchange
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;


public class PieceVO implements IValueObject {
	private String id;
	@Validate(obligatoire=true)
	private String code;
	private String libelle;
	
	public PieceVO() 
	{
		
	}
	public PieceVO(String code) 
	{
		this.code = code;
	}
	public PieceVO(String code, String libelle) 
	{
		this.code = code;
		this.libelle=libelle;
	}
	public String getCode() 
	{
		return code;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}
	public String getLibelle() 
	{
		return libelle;
	}
	public void setLibelle(String libelle) 
	{
		this.libelle = libelle;
	}
	
	public void setId(String id) 
		{
			this.id = id;
		}
		public String getId() 
		{
			return id;
		}
	
}