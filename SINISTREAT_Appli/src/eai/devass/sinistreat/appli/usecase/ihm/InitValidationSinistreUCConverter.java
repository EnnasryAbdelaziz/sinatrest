package eai.devass.sinistreat.appli.usecase.ihm;

/* @author joundi : 1 Nov. 10 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;


@SuppressWarnings("unchecked")
public class InitValidationSinistreUCConverter extends ValueObjectConverterAbst {
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List<SinistreVO> listSinvo = new ArrayList<SinistreVO>();
		
		try {
			List<Sinistre> listSin = (List<Sinistre>)listeItems.get(0);
			if(CommonUtils.getInstance().isEmpty(listSin)) {
				return listSinvo;
			}
			
			Sinistre sinistre = listSin.get(0);
			sinistre.setPropertiesToConvert(new String[] {"numeroSinistre",
					"refVictime.nomprenom","refEvenement.dateAccident","numeroPolice","nomClient","numeroGrave",
					"dateCreation","coutSinistre"});
			
			
			listSinvo = (List<SinistreVO>) ConverterTools.getInstance()
					.convertToListObjectVOWithoutList(listSin);		
			
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return listSinvo;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
