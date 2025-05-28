package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.ListVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;

public class ValidateConciliationUCConverter extends
		ValueObjectConverterAbst implements IMessageException {
	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");

	/**
	 * Methode qui convertit une entité à un Value Object
	 * 
	 * @param item
	 *            entité à convertir
	 * @returns Value Object resultat converti
	 */

	public Object doConvertItemsToValueObject(List item) {
		return null;
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
	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		ListVO list = (ListVO) vo;

		List<ConciliationVO> listConciliationVO = list.getListConciliation();
		List<Conciliation> listCon = null;
		try {
			if (listConciliationVO == null) {
				throw new FonctionnelleException("EXP_CHAMPS_SERVICE_NULL");
			}
			listCon = (List) converterTools.convertToListObjectBOBis(listConciliationVO);
			
		} catch (Exception e) {
			Logger.getLogger("loggerSINAT").fatal("Erreur lors de la conversion !", e);
		}
		return listCon;
	}

}
