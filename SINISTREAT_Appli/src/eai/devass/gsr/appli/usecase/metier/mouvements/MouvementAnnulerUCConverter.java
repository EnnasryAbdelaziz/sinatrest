package eai.devass.gsr.appli.usecase.metier.mouvements;

import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVOConverter;

/**
Converter du Use case de suppission
@author Nom Prenom (email)
*/
public class MouvementAnnulerUCConverter extends MouvementVOConverter {

	public void voToItem(MouvementVO vo, Mouvement item) {
		//nous n'avons besoin que de l'id de l'entité a annulée
		item.setId(vo.getId());
	}

}