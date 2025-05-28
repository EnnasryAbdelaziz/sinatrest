package eai.devass.gsr.appli.valueobjects.parametrage;

import eai.devass.commun.appli.converter.AConverter;

/**
 * Value Object de ModeReglement
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteDist="eai.devass.gsr.appli.modele.parametrage.ModeReglement")
public class ModeReglementVO extends ParamVO {

	private static final long serialVersionUID = 1L;

	public ModeReglementVO() {
		super();
		
	}

	public ModeReglementVO(long id) {
		super(id);
		
	}
	
	

}
