package eai.devass.sinistreat.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.parametrage.Barreau;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.parametrage.BarreauVO;

public class RechercheBarreauUCConverter extends  ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();	
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
						
	
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		List<BarreauVO> listBarreauVO= new ArrayList<BarreauVO>();
		try {
			List<Barreau> listBarreau = (List<Barreau>)listeItems.get(0);
			listBarreauVO=(List)converterTools.convertToListObjectVO(listBarreau);
		
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return listBarreauVO;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		
		List listOut = new ArrayList();
		BarreauVO barreauVO = (BarreauVO) vo;
		try{
			

			Barreau barreau=(Barreau)converterTools.convertToObjectBO(barreauVO);
			
			listOut.add(barreau);		
		} catch(Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
						
		return listOut;
	}
	

}
