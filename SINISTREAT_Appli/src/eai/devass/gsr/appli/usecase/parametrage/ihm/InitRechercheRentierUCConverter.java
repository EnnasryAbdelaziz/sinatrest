package eai.devass.gsr.appli.usecase.parametrage.ihm;

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
public class InitRechercheRentierUCConverter extends ValueObjectConverterAbst {

	//protected Fonctions functions = new Fonctions();	
	//DateFormat dateFormat = new SimpleDateFormat(IDate.FORMAT_SIMPLE);
	//ConverterMetier converterMetier = ConverterMetier.getInstance();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		
		try {
			
			List listePays =(List)listeItems.get(0);
			List listePaysVO = converterTools.convertToListObjectVO(listePays);
			List listeVilles =(List)listeItems.get(1);
			List listeVillesVO = converterTools.convertToListObjectVO(listeVilles);
			List listDegresParente =(List)listeItems.get(2);
			List listDegresParenteVO = converterTools.convertToListObjectVO(listDegresParente);			
			List listNationalite =(List)listeItems.get(3);
			List listNationaliteVO = converterTools.convertToListObjectVO(listNationalite);
			List listModePayement =(List)listeItems.get(4);
			List listModePayementVO = converterTools.convertToListObjectVO(listModePayement);
			List listNatureProthese =(List)listeItems.get(5);
			List listNatureProtheseVO = converterTools.convertToListObjectVO(listNatureProthese);
			List listBanque =(List)listeItems.get(6);
			List listBanqueVO = converterTools.convertToListObjectVO(listBanque);
			List listSituationRentier =(List)listeItems.get(7);
			List listSituationRentierVO = converterTools.convertToListObjectVO(listSituationRentier);
			List listCoefficientAge = (List)listeItems.get(8);
			List listCoefficientAgeVO = converterTools.convertToListObjectVO(listCoefficientAge);
			List listDecision = (List)listeItems.get(9);
			List listDecisionVO = converterTools.convertToListObjectVO(listDecision);
			List listSociete =(List)listeItems.get(10);
			List listSocieteVO = converterTools.convertToListObjectVO(listSociete);
			//Début Evo 24/12/2013
			List listEtatRentier =(List)listeItems.get(11);
			List listEtatRentierVO = converterTools.convertToListObjectVO(listEtatRentier);
			//Fin Evo 24/12/2013
			
			//Début Evo Consignation 25/05/2016
			List listSortRentier =(List)listeItems.get(12);
			List listSortRentierVO = converterTools.convertToListObjectVO(listSortRentier);
			//Fin Evo  25/05/2016
			
			
			map.put("listDegresParenteVO", listDegresParenteVO);		
			map.put("listPaysVO", listePaysVO);			
			map.put("listeVillesVO", listeVillesVO);
			map.put("listeNationaliteVO", listNationaliteVO);
			map.put("listModePayementVO", listModePayementVO);
			map.put("listNatureProtheseVO", listNatureProtheseVO);
			map.put("listBanqueVO", listBanqueVO);
			map.put("listSituationRentierVO", listSituationRentierVO);
			map.put("listCoefficientAgeVO", listCoefficientAgeVO);
			map.put("listDecisionVO", listDecisionVO);
			map.put("listSocieteVO", listSocieteVO);
			//Début Evo 24/12/2013
			map.put("listEtatRentierVO", listEtatRentierVO);
			//Fin Evo 24/12/2013
			//Début Evo Consignation 25/05/2016
			map.put("listSortRentierVO", listSortRentierVO);
			//Fin Evo 25/05/2016
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
