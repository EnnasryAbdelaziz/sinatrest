package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.PoliceVO;

public class RecherchePoliceUCConverter extends  ValueObjectConverterAbst implements IMessageException {
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
				
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List<PoliceVO> listpolvo= null;
		try {
			listpolvo = (List<PoliceVO>)listeItems.get(0);
		} catch (Exception e) {
			logger.error("probl�me technique",e);
		}

		return listpolvo;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		
			return null;
	}
	

}
