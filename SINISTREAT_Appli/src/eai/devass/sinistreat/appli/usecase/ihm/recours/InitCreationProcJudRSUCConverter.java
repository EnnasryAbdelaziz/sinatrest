package eai.devass.sinistreat.appli.usecase.ihm.recours;

/* @author joundi : 1 Nov. 10 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.utils.ConverterTools;


@SuppressWarnings("unchecked")
public class InitCreationProcJudRSUCConverter extends ValueObjectConverterAbst {

	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		
		try {
			List listJuridiction = (List)listeItems.get(0);
			List listJuridictionVO = converterTools.convertToListObjectVO(listJuridiction);			
			List listTypeJuridiction = (List)listeItems.get(1);
			List listTypeJuridictionVO = converterTools.convertToListObjectVO(listTypeJuridiction);			
			List listNatureProcedure =(List)listeItems.get(2);
			List listNatureProcedureVO = converterTools.convertToListObjectVO(listNatureProcedure);
			List listNatureDossier =(List)listeItems.get(3);
			List listNatureDossierVO = converterTools.convertToListObjectVO(listNatureDossier);						
			List listPronosticRC =(List)listeItems.get(4);
			List listPronosticRCVO = converterTools.convertToListObjectVO(listPronosticRC);
			
			map.put("listJuridictionVO", listJuridictionVO);
			map.put("listTypeJuridictionVO", listTypeJuridictionVO);	
			map.put("listNatureProcedureVO", listNatureProcedureVO);		
			map.put("listNatureDossierVO", listNatureDossierVO);
			map.put("listPronosticRC", listPronosticRCVO);
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
