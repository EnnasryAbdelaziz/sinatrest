package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class InitCreateRecuperationUCConverter extends ValueObjectConverterAbst {
	private Logger logger = Logger.getLogger("loggerSINAT");
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		// TODO Auto-generated method stub
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();
		try {
			List listBanqueVO = ConverterTools.getInstance()
					.convertToListObjectVO((List) listeItems.get(0));
			List listeChoixRubrique = ConverterTools.getInstance()
					.convertToListObjectVO((List) listeItems.get(1));
			SinistreVO sinvo = (SinistreVO) ConverterTools.getInstance()
					.convertToObjectVOWithoutList((Sinistre) listeItems.get(2));
			List listNatureRecuperation = ConverterTools.getInstance()
					.convertToListObjectVO((List) listeItems.get(3));
			List listCompagnies = ConverterTools.getInstance()
					.convertToListObjectVO((List) listeItems.get(4));
			map.put("listBanque", listBanqueVO);
			map.put("listeChoixRubrique", listeChoixRubrique);
			map.put("SinistreVO", sinvo);
			map.put("listNatureRecuperation", listNatureRecuperation);
			map.put("listeCompagnies", listCompagnies);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return map;
	}

	@Override
	public List doConvertValueObjectToItems(Object arg0)
			throws ValidationUnitaireException, ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
