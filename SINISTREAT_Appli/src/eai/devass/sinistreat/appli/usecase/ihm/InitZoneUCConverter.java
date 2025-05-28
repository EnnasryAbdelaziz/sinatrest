package eai.devass.sinistreat.appli.usecase.ihm;

/* @author joundi : 1 Nov. 10 */
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.valueobjects.parametrage.ZoneVO;

public class InitZoneUCConverter extends ValueObjectConverterAbst {

	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		String zone=null;
		ZoneVO zonvo=null;
		try {
			zone = (String)listeItems.get(0);
			if(!StringUtils.isEmpty(zone)){
				zonvo= new ZoneVO(zone);
			}
				
				
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return zonvo;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
