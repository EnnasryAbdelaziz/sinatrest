package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.VictimeVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RecherchePartieAdverseUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		try {
			// Sinistre sin=(Sinistre)((List<Ay>)listeItems.get(0)).get(0);
			Object object = listeItems.get(0);
			if (object instanceof Victime) {
				VictimeVO victimeVo = (VictimeVO) converterTools
						.convertToObjectVO(object);
				return victimeVo;
			} else {
				List<AyantDroit> listAY = (List<AyantDroit>) listeItems.get(0);
				List<AyantDroitVO> listAYvo = (List) converterTools
						.convertToListObjectVOWithoutList(listAY);
				if (listeItems.size() > 1) {
					PagerVO pagerVO = (PagerVO) listeItems.get(1);
					Map map = new HashMap();
					if (listAYvo != null && !listAYvo.isEmpty()) {
						map.put("list"
								+ listAYvo.get(0).getClass().getSimpleName(),
								listAYvo);
					}
					map.put(IParam.PAGER, pagerVO);
					return map;
				}
				return listAYvo;
			}

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return null;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

		return null;

	}

}
