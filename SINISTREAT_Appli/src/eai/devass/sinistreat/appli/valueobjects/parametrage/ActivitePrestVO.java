package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.validation.Validate;


public class ActivitePrestVO implements IValueObject {
	
	@Validate(obligatoire=true)
	private String code;
	private String libelle;
	private DomainePrestVO refDomaineActivite = new DomainePrestVO();
	public ActivitePrestVO() {
		
	}
	public ActivitePrestVO(String code) {
		this.code = code;
	}
	public ActivitePrestVO(String code, String libelle) {
		this.code = code;
		this.libelle=libelle;
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
	public DomainePrestVO getRefDomaineActivite() {
		return refDomaineActivite;
	}
	public void setRefDomaineActivite(DomainePrestVO refDomaineActivite) {
		this.refDomaineActivite = refDomaineActivite;
	}
	
	
	
}