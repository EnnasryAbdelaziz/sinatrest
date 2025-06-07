package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.TypeQuittance;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeQuittanceVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeQuittanceVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class TypeQuittanceRechercherUCConverter extends RechListeVOConverter {


	TypeQuittanceVOConverter typeQuittanceVOConverter = new TypeQuittanceVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		TypeQuittanceVO vo = new TypeQuittanceVO();
		typeQuittanceVOConverter.itemToVo(vo, (TypeQuittance)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<TypeQuittance> convertValueObjectToItems(Object ovo) throws ValidationException {
		TypeQuittanceVO vo = (TypeQuittanceVO) ovo;
		TypeQuittance item = new TypeQuittance();
		typeQuittanceVOConverter.voToItem(vo,item);
		List<TypeQuittance> res = new ArrayList<TypeQuittance>();
		res.add(item);
		return res;
	}

}
