package eai.devass.gsr.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.parametrage.ModeReglement;
import eai.devass.gsr.appli.valueobjects.parametrage.ModeReglementVO;
import eai.devass.gsr.appli.valueobjects.parametrage.ModeReglementVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class ModeReglementRechercherUCConverter extends RechListeVOConverter {


	ModeReglementVOConverter modeReglementVOConverter = new ModeReglementVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		ModeReglementVO vo = new ModeReglementVO();
		modeReglementVOConverter.itemToVo(vo, (ModeReglement)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<ModeReglement> convertValueObjectToItems(Object ovo) throws ValidationException {
		ModeReglementVO vo = (ModeReglementVO) ovo;
		ModeReglement item = new ModeReglement();
		modeReglementVOConverter.voToItem(vo,item);
		List<ModeReglement> res = new ArrayList<ModeReglement>();
		res.add(item);
		return res;
	}

}
