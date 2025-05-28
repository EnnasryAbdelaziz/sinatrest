package eai.devass.commun.appli.modele;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import eai.devass.commun.appli.converter.AConverter;

public class EntiteVO extends RechListeVO implements IValueObject {
		
	private static final long serialVersionUID = 1L;
	private String runRegleGestion;
	
	@AConverter(propertyDist = "operateur")
	private String operateur;
	
	
	
	public String getRunRegleGestion() {
		return runRegleGestion;
	}
	public void setRunRegleGestion(String runRegleGestion) {
		this.runRegleGestion = runRegleGestion;
	}
	public String getOperateur() {
		return operateur;
	}
	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}
	
	
	
}
