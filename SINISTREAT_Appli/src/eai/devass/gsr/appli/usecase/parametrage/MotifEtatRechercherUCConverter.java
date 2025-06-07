package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.MotifEtat;
import eai.devass.gsr.appli.valueobjects.parametrage.MotifEtatVO;
import eai.devass.gsr.appli.valueobjects.parametrage.MotifEtatVOConverter;


/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class MotifEtatRechercherUCConverter extends RechListeVOConverter {


	MotifEtatVOConverter motifEtatVOConverter = new MotifEtatVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		MotifEtatVO vo = new MotifEtatVO();
		motifEtatVOConverter.itemToVo(vo, (MotifEtat)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<MotifEtat> convertValueObjectToItems(Object ovo) throws ValidationException {
		MotifEtatVO vo = (MotifEtatVO) ovo;
		MotifEtat item = new MotifEtat();
		motifEtatVOConverter.voToItem(vo,item);
		List<MotifEtat> res = new ArrayList<MotifEtat>();
		res.add(item);
		return res;
	}

}
