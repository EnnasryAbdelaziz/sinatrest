package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.CentreProthese;
import eai.devass.gsr.appli.valueobjects.parametrage.CentreProtheseVO;
import eai.devass.gsr.appli.valueobjects.parametrage.CentreProtheseVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class CentreProtheseConsulterUCConverter extends CentreProtheseVOConverter {

	public void voToItem(CentreProtheseVO vo, CentreProthese item) {
		item.setId(vo.getId());
	}

}