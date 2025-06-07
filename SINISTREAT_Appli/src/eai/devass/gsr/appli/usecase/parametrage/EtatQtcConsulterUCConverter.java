package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatQtcVO;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatQtcVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class EtatQtcConsulterUCConverter extends EtatQtcVOConverter {

	public void voToItem(EtatQtcVO vo, EtatQtc item) {
		item.setId(vo.getId());
	}

}