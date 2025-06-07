package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.TypeRecuperation;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeRecuperationVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeRecuperationVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class TypeRecuperationConsulterUCConverter extends TypeRecuperationVOConverter {

	public void voToItem(TypeRecuperationVO vo, TypeRecuperation item) {
		item.setId(vo.getId());
	}

}