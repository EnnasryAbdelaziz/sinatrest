package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.ProtheseVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.ProtheseVOConverter;


/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class ProthesesRechercherUCConverter extends RechListeVOConverter {


	ProtheseVOConverter prothesesVOConverter = new ProtheseVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		ProtheseVO vo = new ProtheseVO();
		prothesesVOConverter.itemToVo(vo, (Prothese)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<Prothese> convertValueObjectToItems(Object ovo) throws ValidationException {
		ProtheseVO vo = (ProtheseVO) ovo;
		Prothese item = new Prothese();
		prothesesVOConverter.voToItem(vo,item);
		List<Prothese> res = new ArrayList<Prothese>();
		res.add(item);
		return res;
	}

}
