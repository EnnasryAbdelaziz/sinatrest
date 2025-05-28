package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.TypeApprobation;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeApprobationVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeApprobationVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class TypeApprobationConsulterUCConverter extends TypeApprobationVOConverter {

	public void voToItem(TypeApprobationVO vo, TypeApprobation item) {
		item.setId(vo.getId());
	}

}