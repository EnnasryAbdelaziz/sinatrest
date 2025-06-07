package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.OffreVO;


public class ValiderOffreAvantCreationUCConverter  extends ValueObjectConverterAbst
implements IMessageException{
	
	protected Fonctions functions = new Fonctions();
	private Logger logger = Logger.getLogger("loggerSINAT");
	
	@Override
	public Object doConvertItemsToValueObject(List listeItems) 
	{
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();
		try {
			map.put("listMessage", listeItems.get(0));
			map.put("Bloquer", listeItems.get(1));
		} catch (Exception e) {
			logger.fatal("Erreur lors de la conversion !", e);
		}
		return map;

	}
	
	@Override
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		OffreVO offreVO = (OffreVO) vo;
        listOut.add(offreVO);
		return listOut;

	}


}
