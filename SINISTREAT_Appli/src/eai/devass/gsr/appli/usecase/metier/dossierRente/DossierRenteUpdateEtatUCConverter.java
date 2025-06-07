package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVOConverter;

/**
 * Converter du use case de recherche
 * 
 * @author Nom Prenom (email)
 */

public class DossierRenteUpdateEtatUCConverter extends ValueObjectConverterAbst {

	DossierRenteVOConverter dossierRenteVOConverter = new DossierRenteVOConverter();

	/**
	 * Methode qui convertit une entité à un Value Object
	 * 
	 * @param item
	 *            entité à convertir
	 * @returns Value Object resultat converti
	 */

	public Object doConvertItemsToValueObject(List item) {
		DossierRenteVO dossierRenteVO = new DossierRenteVO();
		Long etat = (Long)item.get(0);
		Boolean validation = (Boolean)item.get(1);
		Calendar dateEtat = (Calendar)item.get(2);
		dossierRenteVO.setEtat(TypeConverter.getInstance().longToString(etat));
		dossierRenteVO.setValidation(TypeConverter.getInstance().booleanToString(validation));
		dossierRenteVO.setDateEtat(TypeConverter.getInstance().calendarToString(dateEtat));
		
	return dossierRenteVO;
	}

	/**
	 * Methode qui convertit un Value Object à une liste d' entités
	 * 
	 * @param ovo
	 *            value object à convertir
	 * @returns Listes des entités resultats convertis
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	public List<DossierRente> doConvertValueObjectToItems(Object ovo)
			throws ValidationException {
		DossierRenteVO vo = (DossierRenteVO) ovo;
		DossierRente item = new DossierRente();
		dossierRenteVOConverter.voToItem(vo, item);
		List<DossierRente> res = new ArrayList<DossierRente>();
		res.add(item);
		return res;
	}

}
