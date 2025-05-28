package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.TypeMvtProthese;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMvtProtheseVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMvtProtheseVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class TypeMvtProtheseConsulterUCConverter extends TypeMvtProtheseVOConverter {

	public void voToItem(TypeMvtProtheseVO vo, TypeMvtProthese item) {
		item.setId(vo.getId());
	}

}