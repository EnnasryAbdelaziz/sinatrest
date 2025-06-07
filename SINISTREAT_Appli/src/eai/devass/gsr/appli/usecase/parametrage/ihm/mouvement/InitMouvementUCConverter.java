
package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.utils.ConverterTools;


@SuppressWarnings("unchecked")
public class InitMouvementUCConverter extends ValueObjectConverterAbst {

	private CommonUtils commonUtils = CommonUtils.getInstance();	
	private ConverterTools convertorTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (commonUtils.isEmpty(listeItems)) {
			return null;
		}
		
		Map map = (Map) listeItems.get(0);
		Map mapOut = new HashMap();				
		try {
			Set<Entry<String, Object>> entryList = map.entrySet();
			List listObjetc = null;
			for (Entry<String, Object> curEntry : entryList) {
				if(curEntry.getValue() == null) {
					continue;
				}
				
				if(commonUtils.isCollection(curEntry.getValue().getClass())) {
					listObjetc = (List) curEntry.getValue();
					if(commonUtils.isEmpty(listObjetc)) {
						mapOut.put(curEntry.getKey(), new ArrayList());
						continue;
					}
					
					mapOut.put(curEntry.getKey(), convertorTools
							.convertToListObjectVO((List) curEntry.getValue()));
					
				} else if(commonUtils.isReference(curEntry.getValue().getClass())) {
					mapOut.put(curEntry.getKey(), convertorTools
							.convertToObjectVO(curEntry.getValue()));
				}
				else {
					mapOut.put(curEntry.getKey(), curEntry.getValue());
				}
				
			}
		   
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return mapOut;
		
		
	}
	
	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}
}
