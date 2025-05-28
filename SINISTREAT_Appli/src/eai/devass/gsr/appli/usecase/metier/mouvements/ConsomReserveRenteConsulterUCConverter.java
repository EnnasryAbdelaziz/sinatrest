package eai.devass.gsr.appli.usecase.metier.mouvements;

import eai.devass.gsr.appli.modele.metier.mouvements.ConsomReserveRente;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.ConsomReserveRenteVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.ConsomReserveRenteVOConverter;


/**
Converter du Use case de consultation
@author Nom Prenom (email)
*/
public class ConsomReserveRenteConsulterUCConverter extends ConsomReserveRenteVOConverter {

	public void voToItem(ConsomReserveRenteVO vo, ConsomReserveRente item) {
		item.setId(vo.getId());
	}

}