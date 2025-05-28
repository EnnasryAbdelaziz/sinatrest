/***********************************************************************
 * Module:  Etat.java
 * Author:  Administrateur
 * Purpose: Defines the Class Etat
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.parametrage;

public class PlafondMAD implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String code;
	private String plafond;
	private String sort;
	private String rejetMAD;

	public PlafondMAD() {
	}

	public PlafondMAD(String code) {
		this.code = code;
	}

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
