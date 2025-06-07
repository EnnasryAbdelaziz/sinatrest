package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.bnej.LotBnej;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.bnej.LotBnejVO;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

public class CreationLotBnejUCConverter extends ValueObjectConverterAbst {

	
	private Logger logger = Logger.getLogger("loggerSINAT");
	private ConverterTools converterTools = ConverterTools.getInstance();
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		} else {

			LotBnej bnejItem = (LotBnej) listeItems.get(0);
			LotBnejVO bnejVO = new LotBnejVO();

			try {
				bnejVO = (LotBnejVO) converterTools.convertToObjectVO(bnejItem);
			} catch (Exception e) {
				logger.error("problème technique", e);
			}

			return bnejVO;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List doConvertValueObjectToItems(Object arg0)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		LotBnejVO bnejVO = (LotBnejVO) arg0;

		try {

			LotBnej bnejItem = (LotBnej) converterTools
					.convertToObjectBO(bnejVO);
			listOut.add(bnejItem);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
