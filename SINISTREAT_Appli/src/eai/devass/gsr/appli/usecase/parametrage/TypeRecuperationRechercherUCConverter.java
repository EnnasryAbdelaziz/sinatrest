package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.TypeRecuperation;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeRecuperationVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeRecuperationVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class TypeRecuperationRechercherUCConverter extends RechListeVOConverter {


	TypeRecuperationVOConverter typeRecuperationVOConverter = new TypeRecuperationVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		TypeRecuperationVO vo = new TypeRecuperationVO();
		typeRecuperationVOConverter.itemToVo(vo, (TypeRecuperation)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<TypeRecuperation> convertValueObjectToItems(Object ovo) throws ValidationException {
		TypeRecuperationVO vo = (TypeRecuperationVO) ovo;
		TypeRecuperation item = new TypeRecuperation();
		typeRecuperationVOConverter.voToItem(vo,item);
		List<TypeRecuperation> res = new ArrayList<TypeRecuperation>();
		res.add(item);
		return res;
	}

}
