/***********************************************************************
 * Module:  Etat.java
 * Author:  Administrateur
 * Purpose: Defines the Class Etat
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.parametrage;

/** @pdOid 1f8e6c5e-49a3-49b4-8e34-d868502fc3db */
public class ImpactRubriqueReserve implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */

	private Long id;
	private Prestation refPrestation;
	private String typeReserve;
	private TypeReglement refTypeReglement;
	private String modeReglement;
	private String etatSinistre;
	
	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Prestation getRefPrestation() {
		return refPrestation;
	}

	public void setRefPrestation(Prestation refPrestation) {
		this.refPrestation = refPrestation;
	}

	public String getTypeReserve() {
		return typeReserve;
	}

	public void setTypeReserve(String typeReserve) {
		this.typeReserve = typeReserve;
	}

	public TypeReglement getRefTypeReglement() {
		return refTypeReglement;
	}

	public void setRefTypeReglement(TypeReglement refTypeReglement) {
		this.refTypeReglement = refTypeReglement;
	}

	public String getEtatSinistre() {
		return etatSinistre;
	}

	public void setEtatSinistre(String etatSinistre) {
		this.etatSinistre = etatSinistre;
	}
	
	}