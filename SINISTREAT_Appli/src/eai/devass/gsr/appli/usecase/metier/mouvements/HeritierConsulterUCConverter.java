package eai.devass.gsr.appli.usecase.metier.mouvements;

import eai.devass.gsr.appli.modele.metier.mouvements.Heritier;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.HeritierVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.HeritierVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class HeritierConsulterUCConverter extends HeritierVOConverter {

	public void voToItem(HeritierVO vo, Heritier item) {
		item.setId(vo.getId());
	}

}