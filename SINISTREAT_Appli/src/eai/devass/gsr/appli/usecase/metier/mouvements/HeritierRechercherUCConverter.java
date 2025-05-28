package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.metier.mouvements.Heritier;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.HeritierVO;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class HeritierRechercherUCConverter extends RechListeVOConverter {


	//HeritierVOConverter heritierVOConverter = new HeritierVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		HeritierVO vo = new HeritierVO();
	//	heritierVOConverter.itemToVo(vo, (Heritier)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<Heritier> convertValueObjectToItems(Object ovo) throws ValidationException {
		Heritier item = new Heritier();
		List<Heritier> res = new ArrayList<Heritier>();
		res.add(item);
		return res;
	}
}
