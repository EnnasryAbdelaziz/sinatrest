package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.ListVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;

public class ValiderReglementAvtCreationUCConverter extends
		ValueObjectConverterAbst implements IMessageException {

	private ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();
		try {
			map.put("listMessage", listeItems.get(0));
			map.put("Bloquer", listeItems.get(1));
		} catch (Exception e) {
            Logger.getLogger("loggerGSR").fatal(
                    "Erreur lors de la conversion !", e);
		}
		return map;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		try {
			List<Reglement> lisrgl = new ArrayList<Reglement>();
			if (vo instanceof ListVO) {
				ListVO list = (ListVO) vo;
				List<ReglementVO> lisrglvo = list.getListReglement();

				lisrgl = (List) converterTools
						.convertToListObjectBOBis(lisrglvo);
			} else {
				Reglement rgl = (Reglement) converterTools
						.convertToObjectBO((ReglementVO) vo);
				
				lisrgl.add(rgl);
			}
			return lisrgl;
		} catch (Exception e) {
			throw new ValidationUnitaireException(e.getMessage());
		}
	}

}
