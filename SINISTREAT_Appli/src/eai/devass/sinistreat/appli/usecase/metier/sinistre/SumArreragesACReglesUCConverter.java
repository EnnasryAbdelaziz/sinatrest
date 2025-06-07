package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class SumArreragesACReglesUCConverter extends ValueObjectConverterAbst
		implements IMessageException {
	private Logger logger= Logger.getLogger("loggerSINAT");
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
		SinistreVO sinistreVO = new SinistreVO();

		Double sumArrerageACR = (Double) item.get(0);
		sinistreVO.setCapitalAvantConstitution(String.valueOf(sumArrerageACR));
		return sinistreVO;
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
	public List<Sinistre> doConvertValueObjectToItems(Object ovo)
			throws ValidationException {
		SinistreVO vo = (SinistreVO) ovo;
		Sinistre item = new Sinistre();
		try {
			converterTools.convertToObjectBO(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("probl�me technique",e);
		}
		List<Sinistre> res = new ArrayList<Sinistre>();
		res.add(item);
		return res;
	}
	// //

}
