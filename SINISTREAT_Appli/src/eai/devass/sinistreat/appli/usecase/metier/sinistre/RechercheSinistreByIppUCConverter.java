package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class RechercheSinistreByIppUCConverter extends  ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();	
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
						
	
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		List<SinistreVO> listSinvo= new ArrayList<SinistreVO>();
		try {
			List<Sinistre> listSin = (List<Sinistre>)listeItems.get(0);
			listSinvo=(List)converterTools.convertToListObjectVOWithoutList(listSin);
			
			if (listeItems.size() > 1) {
				Map map = new HashMap();
				if (listSinvo != null && !listSinvo.isEmpty()) {
					map.put("list" + listSinvo.get(0).getClass().getSimpleName(), listSinvo);
				}
//					map.put(IParam.PAGER, pagerVO);
				return map;
			}
			
			

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return listSinvo;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		
		List listOut = new ArrayList();
		SinistreVO sinvo = (SinistreVO) vo;
		try{
			

			Sinistre sin=(Sinistre)converterTools.convertToObjectBO(sinvo);
			
			listOut.add(sin);		
		} catch(Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
						
		return listOut;
	}
	

}
