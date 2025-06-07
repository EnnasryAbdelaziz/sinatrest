package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.TypeRgltGsr;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeRgltGsrVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeRgltGsrVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class TypeRgltGsrRechercherUCConverter extends RechListeVOConverter {


	TypeRgltGsrVOConverter typeReglementVOConverter = new TypeRgltGsrVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		TypeRgltGsrVO vo = new TypeRgltGsrVO();
		typeReglementVOConverter.itemToVo(vo, (TypeRgltGsr)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<TypeRgltGsr> convertValueObjectToItems(Object ovo) throws ValidationException {
		TypeRgltGsrVO vo = (TypeRgltGsrVO) ovo;
		TypeRgltGsr item = new TypeRgltGsr();
		typeReglementVOConverter.voToItem(vo,item);
		List<TypeRgltGsr> res = new ArrayList<TypeRgltGsr>();
		res.add(item);
		return res;
	}

}
