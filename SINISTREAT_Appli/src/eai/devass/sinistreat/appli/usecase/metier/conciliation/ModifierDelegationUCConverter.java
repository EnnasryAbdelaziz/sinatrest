package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.conciliation.Delegation;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.DelegationVO;

public class ModifierDelegationUCConverter extends
		ValueObjectConverterAbst implements IMessageException {
	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();


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
		List listOut = new ArrayList();
		DelegationVO delegVO = (DelegationVO) vo;

		try {
			Delegation delegation  = (Delegation) converterTools
					.convertToObjectBO(delegVO);
			listOut.add(delegation);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
