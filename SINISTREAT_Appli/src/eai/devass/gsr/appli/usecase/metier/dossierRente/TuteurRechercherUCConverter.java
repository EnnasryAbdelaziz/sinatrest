package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVOConverter;
import eai.devass.gsr.appli.modele.metier.dossierRente.Tuteur;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.TuteurVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.TuteurVOConverter;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class TuteurRechercherUCConverter extends RechListeVOConverter {


	TuteurVOConverter tuteurVOConverter = new TuteurVOConverter();
/**
Methode qui convertit une entité à un Value Object
@param item entité à convertir
@returns Value Object resultat  converti
*/	
	protected Object convertItemtoVOItem(Object item) {
		TuteurVO vo = new TuteurVO();
		tuteurVOConverter.itemToVo(vo, (Tuteur)item, null);
		return vo;
	}
/**
Methode qui convertit un Value Object à une liste d' entités
@param ovo value object à convertir
@returns Listes des entités resultats convertis
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/			
	public List<Tuteur> convertValueObjectToItems(Object ovo) throws ValidationException {
		TuteurVO vo = (TuteurVO) ovo;
		Tuteur item = new Tuteur();
		tuteurVOConverter.voToItem(vo,item);
		List<Tuteur> res = new ArrayList<Tuteur>();
		res.add(item);
		return res;
	}

}
