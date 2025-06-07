package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.metier.mouvements.ConsomReserveRente;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.ConsomReserveRenteVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.ConsomReserveRenteVOConverter;


/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class ConsomReserveRenteRechercherUCConverter extends RechListeVOConverter {


	ConsomReserveRenteVOConverter consomReserveRenteVOConverter = new ConsomReserveRenteVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		ConsomReserveRenteVO vo = new ConsomReserveRenteVO();
		consomReserveRenteVOConverter.itemToVo(vo, (ConsomReserveRente)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<ConsomReserveRente> convertValueObjectToItems(Object ovo) throws ValidationException {
		ConsomReserveRenteVO vo = (ConsomReserveRenteVO) ovo;
		ConsomReserveRente item = new ConsomReserveRente();
		consomReserveRenteVOConverter.voToItem(vo,item);
		List<ConsomReserveRente> res = new ArrayList<ConsomReserveRente>();
		res.add(item);
		return res;
	}

}
