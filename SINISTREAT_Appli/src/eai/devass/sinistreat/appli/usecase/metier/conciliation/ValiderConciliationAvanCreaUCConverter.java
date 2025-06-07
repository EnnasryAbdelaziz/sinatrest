package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;


@SuppressWarnings("all")
public class ValiderConciliationAvanCreaUCConverter extends ValueObjectConverterAbst
implements IMessageException{
	
	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();

	
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();
		try {
			map.put("listMessage", listeItems.get(0));
			map.put("Bloquer", listeItems.get(1));
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal("Erreur lors de la conversion !", e);
		}
		return map;
	}


	@Override
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		ConciliationVO conVO = (ConciliationVO) vo;
		//SinistreVO sin = conVO.getRefSinistre();

		try {
			Conciliation conciliation = (Conciliation) converterTools.convertToObjectBO(conVO);
			listOut.add(conciliation);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
