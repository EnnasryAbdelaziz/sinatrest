package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatRentierVO;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatRentierVOConverter;



/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class EtatRentierConsulterUCConverter extends EtatRentierVOConverter {

	public void voToItem(EtatRentierVO vo, EtatRentier item) {
		item.setId(vo.getId());
	}

}