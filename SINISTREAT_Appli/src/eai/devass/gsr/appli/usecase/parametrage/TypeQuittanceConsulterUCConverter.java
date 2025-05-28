package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.TypeQuittance;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeQuittanceVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeQuittanceVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class TypeQuittanceConsulterUCConverter extends TypeQuittanceVOConverter {

	public void voToItem(TypeQuittanceVO vo, TypeQuittance item) {
		item.setId(vo.getId());
	}

}