package eai.devass.sinistreat.appli.usecase.ihm;

/* @author joundi : 1 Nov. 10 */
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class InitSinistreUCConverter extends ValueObjectConverterAbst {
	private Logger logger = Logger.getLogger("loggerSINAT");
	
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		SinistreVO sinvo = new SinistreVO();
		try {
			Sinistre sin = (Sinistre)listeItems.get(0);
			sinvo = (SinistreVO)ConverterTools.getInstance().convertToObjectVOWithoutList(sin);		
			if (sin.getRefEvenement()!=null && sin.getRefEvenement().getHeureAccident()!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				sinvo.getRefEvenement().setHeureAccident(sdf.format(sin.getRefEvenement().getHeureAccident()));
			}				
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return sinvo;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
