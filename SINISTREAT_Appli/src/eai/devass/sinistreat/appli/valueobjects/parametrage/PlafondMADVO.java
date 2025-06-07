package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;

import eai.devass.sinistreat.appli.utils.validation.Validate;


public class PlafondMADVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	private String code;
	private String plafond;
	private String sort;
	private String rejetMAD;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPlafond() {
		return plafond;
	}

	public void setPlafond(String plafond) {
		this.plafond = plafond;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getRejetMAD() {
		return rejetMAD;
	}

	public void setRejetMAD(String rejetMAD) {
		this.rejetMAD = rejetMAD;
	}



	

}
