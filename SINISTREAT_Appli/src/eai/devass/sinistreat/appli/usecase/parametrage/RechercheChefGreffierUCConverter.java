package eai.devass.sinistreat.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.parametrage.ChefGreffier;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.parametrage.ChefGreffierVO;

public class RechercheChefGreffierUCConverter extends  ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();	
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
						
	
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		List<ChefGreffierVO> listChefGreffierVO= new ArrayList<ChefGreffierVO>();
		try {
			List<ChefGreffier> listChefGreffier = (List<ChefGreffier>)listeItems.get(0);
			listChefGreffierVO=(List)converterTools.convertToListObjectVO(listChefGreffier);
		
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return listChefGreffierVO;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		
		List listOut = new ArrayList();
		ChefGreffierVO chefGreffierVO = (ChefGreffierVO) vo;
		try{
			

			ChefGreffier chefGreffier=(ChefGreffier)converterTools.convertToObjectBO(chefGreffierVO);
			
			listOut.add(chefGreffier);		
		} catch(Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
						
		return listOut;
	}
	

}
