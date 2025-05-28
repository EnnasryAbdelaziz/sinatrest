package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.TypeMvtProthese;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMvtProtheseVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMvtProtheseVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class TypeMvtProtheseRechercherUCConverter extends RechListeVOConverter {


	TypeMvtProtheseVOConverter typeMvtProtheseVOConverter = new TypeMvtProtheseVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		TypeMvtProtheseVO vo = new TypeMvtProtheseVO();
		typeMvtProtheseVOConverter.itemToVo(vo, (TypeMvtProthese)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<TypeMvtProthese> convertValueObjectToItems(Object ovo) throws ValidationException {
		TypeMvtProtheseVO vo = (TypeMvtProtheseVO) ovo;
		TypeMvtProthese item = new TypeMvtProthese();
		typeMvtProtheseVOConverter.voToItem(vo,item);
		List<TypeMvtProthese> res = new ArrayList<TypeMvtProthese>();
		res.add(item);
		return res;
	}

}
