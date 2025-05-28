package eai.devass.sinistreat.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import eai.devass.sinistreat.appli.modele.parametrage.Prestataire;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PrestataireVO;

public class RecherchePrestationUCConverter extends  ValueObjectConverterAbst implements IMessageException{
	
	protected Fonctions functions = new Fonctions();	
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List<PrestataireVO> listPrestaVO= new ArrayList<PrestataireVO>();
		try {
			List<Prestataire> listPresta = (List<Prestataire>)listeItems.get(0);
			listPrestaVO=(List)converterTools.convertToListObjectVO(listPresta);
		
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return listPrestaVO;
	}
	
	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		
		List listOut = new ArrayList();
//		PrestataireVO prestaVO = (PrestataireVO) vo;
//		try{
//			Prestataire presta=(Prestataire)converterTools.convertToObjectBO(prestaVO);
//			listOut.add(presta);		
//		} catch(Exception e) {
//			if(Fonctions.sendMail){functions.sendMail(e, prestaVO);
//			logger.error("problème technique",e);}
//			throw new ValidationUnitaireException(e.getMessage());
//		}
//						
		return listOut;
	}
}
