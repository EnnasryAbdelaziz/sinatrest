package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.TypeAction;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeActionVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeActionVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class TypeActionConsulterUCConverter extends TypeActionVOConverter {

	public void voToItem(TypeActionVO vo, TypeAction item) {
		item.setId(vo.getId());
	}

}