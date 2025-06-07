package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatMvtVO;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatMvtVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class EtatMvtConsulterUCConverter extends EtatMvtVOConverter {

	public void voToItem(EtatMvtVO vo, EtatMvt item) {
		item.setId(vo.getId());
	}

}