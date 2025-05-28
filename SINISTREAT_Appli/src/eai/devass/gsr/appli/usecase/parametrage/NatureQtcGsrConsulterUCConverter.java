package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.valueobjects.parametrage.NatureQtcGsrVO;
import eai.devass.gsr.appli.valueobjects.parametrage.NatureQtcGsrVOConverter;

/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class NatureQtcGsrConsulterUCConverter extends NatureQtcGsrVOConverter {

	public void voToItem(NatureQtcGsrVO vo, NatureQtcGsr item) {
		item.setId(vo.getId());
	}

}