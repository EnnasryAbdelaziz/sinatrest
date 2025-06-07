package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.TypeMajCapital;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMajCapitalVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMajCapitalVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class TypeMajCapitalRechercherUCConverter extends RechListeVOConverter {


	TypeMajCapitalVOConverter typeMajCapitalVOConverter = new TypeMajCapitalVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		TypeMajCapitalVO vo = new TypeMajCapitalVO();
		typeMajCapitalVOConverter.itemToVo(vo, (TypeMajCapital)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<TypeMajCapital> convertValueObjectToItems(Object ovo) throws ValidationException {
		TypeMajCapitalVO vo = (TypeMajCapitalVO) ovo;
		TypeMajCapital item = new TypeMajCapital();
		typeMajCapitalVOConverter.voToItem(vo,item);
		List<TypeMajCapital> res = new ArrayList<TypeMajCapital>();
		res.add(item);
		return res;
	}

}
