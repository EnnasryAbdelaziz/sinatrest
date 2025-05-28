package eai.devass.sinistreat.appli.valueobjects.parametrage;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ZoneVO extends ParamATVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Validate(obligatoire=true)
	private String code;
	private String libelle;
	private String ippMin;
	public ZoneVO() {
		
	}
	public ZoneVO(String code) {
		this.code = code;
	}
	public ZoneVO(String code, String libelle) {
		this.code = code;
		this.libelle=libelle;
	}	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		if(StringUtils.isEmpty(code)) {
			code="99";
		}		
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public void setIppMin(String ippMin) {
		this.ippMin = ippMin;
	}
	public String getIppMin() {
		return ippMin;
	}
}