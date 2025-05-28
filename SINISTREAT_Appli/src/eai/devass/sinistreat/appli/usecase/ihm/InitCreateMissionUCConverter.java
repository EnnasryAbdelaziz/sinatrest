package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.utils.ConverterTools;

public class InitCreateMissionUCConverter extends ValueObjectConverterAbst {

	private Logger logger = Logger.getLogger("loggerSINAT");
	
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		// TODO Auto-generated method stub
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();
		try {
			List listEtatMissionBO = (List) listeItems.get(0);
			List listDomainePrestBO = (List) listeItems.get(1);
			List listPresationBO = (List) listeItems.get(2);
			List listEtatMissionVO = ConverterTools.getInstance()
					.convertToListObjectVO(listEtatMissionBO);
			List listDomainePrestVO = ConverterTools.getInstance()
					.convertToListObjectVO(listDomainePrestBO);
			List listPresationVO = ConverterTools.getInstance()
					.convertToListObjectVO(listPresationBO);
			map.put("listEtatMissionVO", listEtatMissionVO);
			map.put("listDomainePrestVO", listDomainePrestVO);
			map.put("listPresationVO", listPresationVO);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return map;
	}

	@Override
	public List doConvertValueObjectToItems(Object arg0)
			throws ValidationUnitaireException, ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
