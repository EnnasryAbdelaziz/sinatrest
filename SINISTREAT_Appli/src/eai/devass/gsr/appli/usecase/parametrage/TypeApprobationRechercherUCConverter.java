package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.TypeApprobation;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeApprobationVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeApprobationVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class TypeApprobationRechercherUCConverter extends RechListeVOConverter {


	TypeApprobationVOConverter typeApprobationVOConverter = new TypeApprobationVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		TypeApprobationVO vo = new TypeApprobationVO();
		typeApprobationVOConverter.itemToVo(vo, (TypeApprobation)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<TypeApprobation> convertValueObjectToItems(Object ovo) throws ValidationException {
		TypeApprobationVO vo = (TypeApprobationVO) ovo;
		TypeApprobation item = new TypeApprobation();
		typeApprobationVOConverter.voToItem(vo,item);
		List<TypeApprobation> res = new ArrayList<TypeApprobation>();
		res.add(item);
		return res;
	}

}
