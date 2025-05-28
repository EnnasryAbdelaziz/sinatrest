package eai.devass.sinistreat.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RechercheParamUCConverter extends  ValueObjectConverterAbst implements IMessageException {

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
			listSinvo=(List)converterTools.convertToListObjectVO(listSin);
			
			if (listeItems.size() > 1) {
				PagerVO pagerVO = (PagerVO) listeItems.get(1);
				Map map = new HashMap();
				if (listSinvo != null && !listSinvo.isEmpty()) {
					map.put("list" + listSinvo.get(0).getClass().getSimpleName(), listSinvo);
				}
					map.put(IParam.PAGER, pagerVO);
				return map;
			}
			
			

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return listSinvo;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		
		List listOut = new ArrayList();
//		SinistreVO sinvo = (SinistreVO) vo;
		
		//wmos: correction sonar 29/09/2014
//		try{
//			
//
////			Sinistre sin=(Sinistre)converterTools.convertToObjectBO(sinvo);
//			
////			listOut.add(sin);		
//		} catch(Exception e) {
////			if(Fonctions.sendMail){functions.sendMail(e, sinvo);logger.error("problème technique",e);}
//			throw new ValidationUnitaireException(e.getMessage());
//		}
						
		return listOut;
	}
	

}
