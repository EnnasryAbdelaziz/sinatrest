package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.TypeMouvement;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMouvementVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMouvementVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class TypeMouvementRechercherUCConverter extends RechListeVOConverter {


	TypeMouvementVOConverter typeMouvementVOConverter = new TypeMouvementVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		TypeMouvementVO vo = new TypeMouvementVO();
		typeMouvementVOConverter.itemToVo(vo, (TypeMouvement)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<TypeMouvement> convertValueObjectToItems(Object ovo) throws ValidationException {
		TypeMouvementVO vo = (TypeMouvementVO) ovo;
		TypeMouvement item = new TypeMouvement();
		typeMouvementVOConverter.voToItem(vo,item);
		List<TypeMouvement> res = new ArrayList<TypeMouvement>();
		res.add(item);
		return res;
	}

}
