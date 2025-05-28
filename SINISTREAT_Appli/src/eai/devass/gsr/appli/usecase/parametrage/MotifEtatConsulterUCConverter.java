package eai.devass.gsr.appli.usecase.parametrage;

import eai.devass.gsr.appli.modele.parametrage.MotifEtat;
import eai.devass.gsr.appli.valueobjects.parametrage.MotifEtatVO;
import eai.devass.gsr.appli.valueobjects.parametrage.MotifEtatVOConverter;



/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class MotifEtatConsulterUCConverter extends MotifEtatVOConverter {

	public void voToItem(MotifEtatVO vo, MotifEtat item) {
		item.setId(vo.getId());
	}

}