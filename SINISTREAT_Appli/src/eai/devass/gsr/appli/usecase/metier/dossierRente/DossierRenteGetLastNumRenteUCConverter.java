package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

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

public class DossierRenteGetLastNumRenteUCConverter extends
		ValueObjectConverterAbst {

	DossierRenteVOConverter dossierRenteVOConverter = new DossierRenteVOConverter();
	private Logger logger = Logger.getLogger("loggerSINAT");
	/**
	 * Methode qui convertit une entité à un Value Object
	 * 
	 * @param item
	 *            entité à convertir
	 * @returns Value Object resultat converti
	 */

	public Object doConvertItemsToValueObject(List item) {
		DossierRenteVO lastNumRent = new DossierRenteVO();
		try {
			// lastNumRent.setNumeroRente((String)item.get(0));
			// Evo Lot1 28/06/2013
			lastNumRent.setNumeroRente(TypeConverter.getInstance()
					.longToString((Long) item.get(0)));
			// Fin Evo Lot1
		} catch (Exception e) {
			logger.error("probl�me technique",e);
		}
		return lastNumRent;
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
