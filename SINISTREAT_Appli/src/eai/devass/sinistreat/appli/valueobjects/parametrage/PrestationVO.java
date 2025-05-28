package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;


public class PrestationVO implements IValueObject {
	
	@Validate(obligatoire=true)
	private String code;
	private String libelle;
	private String maxReglement;
	private String datee;
	/**
	 * 
	 */
	public PrestationVO() {
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	/**
	 * 
	 * @param code
	 */
	public PrestationVO(String code) {
		this.code = code;
	}
	/**
	 * 
	 * @param code
	 * @param libelle
	 */
	public PrestationVO(String code, String libelle) {
		this.code = code;
		this.libelle=libelle;
	}	
	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 
	 * @return
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * 
	 * @param libelle
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * 
	 * @return
	 */
	public String getMaxReglement() {
		return maxReglement;
	}
	/**
	 * 
	 * @param maxReglement
	 */
	public void setMaxReglement(String maxReglement) {
		this.maxReglement = maxReglement;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDatee() {
		return datee;
	}
	
	/**
	 * 
	 * @param datee
	 */
	public void setDatee(String datee) {
		this.datee = datee;
	}
	
	
}