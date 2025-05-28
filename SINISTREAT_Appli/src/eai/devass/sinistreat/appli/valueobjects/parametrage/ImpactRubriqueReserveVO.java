/***********************************************************************
 * Module:  Etat.java
 * Author:  Administrateur
 * Purpose: Defines the Class Etat
 ***********************************************************************/

package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

/** @pdOid 1f8e6c5e-49a3-49b4-8e34-d868502fc3db */
public class ImpactRubriqueReserveVO implements IValueObject {

	private String id;
	private PrestationVO refPrestation;
	private String typeReserve;
	private TypeReglementVO refTypeReglement;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PrestationVO getRefPrestation() {
		return refPrestation;
	}

	public void setRefPrestation(PrestationVO refPrestation) {
		this.refPrestation = refPrestation;
	}

	public String getTypeReserve() {
		return typeReserve;
	}

	public void setTypeReserve(String typeReserve) {
		this.typeReserve = typeReserve;
	}

	public TypeReglementVO getRefTypeReglement() {
		return refTypeReglement;
	}

	public void setRefTypeReglement(TypeReglementVO refTypeReglement) {
		this.refTypeReglement = refTypeReglement;
	}
	
}