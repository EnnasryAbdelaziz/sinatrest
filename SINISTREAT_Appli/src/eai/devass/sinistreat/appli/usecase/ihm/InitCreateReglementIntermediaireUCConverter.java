package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class InitCreateReglementIntermediaireUCConverter extends
		ValueObjectConverterAbst {
	private Logger logger = Logger.getLogger("loggerSINAT");
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();
		try {
			List listBanque = (List) listeItems.get(0);
			List listResultEtatBO = (List) listeItems.get(1);
			List listeChoixRubriqueBO = (List) listeItems.get(2);
			Sinistre sin = (Sinistre) listeItems.get(3);
			Reglement reg = (Reglement) listeItems.get(4);
			List listBanqueVO = ConverterTools.getInstance()
					.convertToListObjectVO(listBanque);
			List listEtatSinistreVO = ConverterTools.getInstance()
					.convertToListObjectVO(listResultEtatBO);
			List listeChoixRubrique = ConverterTools.getInstance()
					.convertToListObjectVO(listeChoixRubriqueBO);
			SinistreVO sinvo = (SinistreVO) ConverterTools.getInstance()
					.convertToObjectVOWithoutList(sin);
			ReglementVO regVO = (ReglementVO) ConverterTools.getInstance()
					.convertToObjectVOWithoutList(reg);
			map.put("listBanque", listBanqueVO);
			map.put("listEtatSinistre", listEtatSinistreVO);
			map.put("listeChoixRubrique", listeChoixRubrique);
			map.put("SinistreVO", sinvo);
			map.put("ReglementVO", regVO);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return map;
	}

	@Override
	public List doConvertValueObjectToItems(Object arg0)
			throws ValidationUnitaireException, ValidationException {
		return null;
	}

}
