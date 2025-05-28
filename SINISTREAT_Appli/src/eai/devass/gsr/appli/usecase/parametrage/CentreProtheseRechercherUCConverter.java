package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.CentreProthese;
import eai.devass.gsr.appli.valueobjects.parametrage.CentreProtheseVO;
import eai.devass.gsr.appli.valueobjects.parametrage.CentreProtheseVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class CentreProtheseRechercherUCConverter extends RechListeVOConverter {


	CentreProtheseVOConverter centreProtheseVOConverter = new CentreProtheseVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		CentreProtheseVO vo = new CentreProtheseVO();
		centreProtheseVOConverter.itemToVo(vo, (CentreProthese)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<CentreProthese> convertValueObjectToItems(Object ovo) throws ValidationException {
		CentreProtheseVO vo = (CentreProtheseVO) ovo;
		CentreProthese item = new CentreProthese();
		centreProtheseVOConverter.voToItem(vo,item);
		List<CentreProthese> res = new ArrayList<CentreProthese>();
		res.add(item);
		return res;
	}

}
