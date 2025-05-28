package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.TypeCheque;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeChequeVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeChequeVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class TypeChequeRechercherUCConverter extends RechListeVOConverter {


	TypeChequeVOConverter typeChequeVOConverter = new TypeChequeVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		TypeChequeVO vo = new TypeChequeVO();
		typeChequeVOConverter.itemToVo(vo, (TypeCheque)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<TypeCheque> convertValueObjectToItems(Object ovo) throws ValidationException {
		TypeChequeVO vo = (TypeChequeVO) ovo;
		TypeCheque item = new TypeCheque();
		typeChequeVOConverter.voToItem(vo,item);
		List<TypeCheque> res = new ArrayList<TypeCheque>();
		res.add(item);
		return res;
	}

}
