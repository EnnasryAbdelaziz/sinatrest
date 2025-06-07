package eai.devass.gsr.appli.valueobjects.parametrage;

import eai.devass.commun.appli.converter.AConverter;

/**
 * Value Object de TypeQuittance
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteDist="eai.devass.gsr.appli.modele.parametrage.TypeQuittance")
public class TypeQuittanceVO extends ParamVO {

	private static final long serialVersionUID = 1L;

	public TypeQuittanceVO() {
		super();
	
	}

	public TypeQuittanceVO(long id) {
		super(id);
	
	}
	
	

}
