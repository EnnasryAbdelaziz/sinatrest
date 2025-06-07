package eai.devass.gsr.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.trace.ITracable;


/**
 * Value Object de SortGsr
 * 
 * @author Mossab wassim
 */

public class SortGsrVO extends RechListeVO implements ITracable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String libelle;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getTraceInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
