/***********************************************************************
 * Module:  EntiteOrg.java
 * Author:  Administrateur
 * Purpose: Defines the Class EntiteOrg
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.parametrage;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

/**
 * Entité Organisationnelle, récupérée via le SAS
 * 
 * @pdOid 3b63dcd3-59c8-449d-b9ab-dd7c22386bd3
 */
public class EntiteOrgVO implements IValueObject {
	
	private String code;
	private String libelle;
	private String activite;
	private List<EntiteOrgVO> refEntiteOrgVOs;
	
	
	public EntiteOrgVO() {
		super();
	}
	

	public EntiteOrgVO(String code, String libelle) {
		this.code = code;
		this.libelle = libelle;
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


	public List<EntiteOrgVO> getRefEntiteOrgVOs() {
		return refEntiteOrgVOs;
	}


	public void setRefEntiteOrgVOs(List<EntiteOrgVO> refEntiteOrgVOs) {
		this.refEntiteOrgVOs = refEntiteOrgVOs;
	}


	public String getActivite() {
		return activite;
	}


	public void setActivite(String activite) {
		this.activite = activite;
	}
	
	
	

}