package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.TypeMajCapital;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMajCapitalVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMajCapitalVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class TypeMajCapitalConsulterUCConverter extends TypeMajCapitalVOConverter {

	public void voToItem(TypeMajCapitalVO vo, TypeMajCapital item) {
		item.setId(vo.getId());
	}

}