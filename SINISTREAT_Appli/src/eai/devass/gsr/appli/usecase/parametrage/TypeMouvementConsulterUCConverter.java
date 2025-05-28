package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.TypeMouvement;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMouvementVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMouvementVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class TypeMouvementConsulterUCConverter extends TypeMouvementVOConverter {

	public void voToItem(TypeMouvementVO vo, TypeMouvement item) {
		item.setId(vo.getId());
	}

}