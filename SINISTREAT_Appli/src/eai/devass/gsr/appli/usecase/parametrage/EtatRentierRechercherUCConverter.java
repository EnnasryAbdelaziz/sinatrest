package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatRentierVO;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatRentierVOConverter;


/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class EtatRentierRechercherUCConverter extends RechListeVOConverter {


	EtatRentierVOConverter etatRentierVOConverter = new EtatRentierVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		EtatRentierVO vo = new EtatRentierVO();
		etatRentierVOConverter.itemToVo(vo, (EtatRentier)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<EtatRentier> convertValueObjectToItems(Object ovo) throws ValidationException {
		EtatRentierVO vo = (EtatRentierVO) ovo;
		EtatRentier item = new EtatRentier();
		etatRentierVOConverter.voToItem(vo,item);
		List<EtatRentier> res = new ArrayList<EtatRentier>();
		res.add(item);
		return res;
	}

}
