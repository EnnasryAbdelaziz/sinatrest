package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatMvtVO;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatMvtVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class EtatMvtRechercherUCConverter extends RechListeVOConverter {


	EtatMvtVOConverter etatMvtVOConverter = new EtatMvtVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		EtatMvtVO vo = new EtatMvtVO();
		etatMvtVOConverter.itemToVo(vo, (EtatMvt)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<EtatMvt> convertValueObjectToItems(Object ovo) throws ValidationException {
		EtatMvtVO vo = (EtatMvtVO) ovo;
		EtatMvt item = new EtatMvt();
		etatMvtVOConverter.voToItem(vo,item);
		List<EtatMvt> res = new ArrayList<EtatMvt>();
		res.add(item);
		return res;
	}

}
