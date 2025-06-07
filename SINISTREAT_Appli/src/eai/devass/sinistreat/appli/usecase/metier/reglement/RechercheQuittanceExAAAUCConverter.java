package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.reglement.QuittanceExAAA;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.QuittanceExAAAVO;

public class RechercheQuittanceExAAAUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings({ "unchecked","rawtypes"})
	public Object doConvertItemsToValueObject(List listeItems) {
		Map map = new HashMap();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		try {
			List listQuittance = (List) listeItems.get(0);
			// Liste entite reglement
			if (listQuittance != null && !listQuittance.isEmpty()) {
				Object quittanceExAAA = listQuittance.get(0);
				if (quittanceExAAA instanceof QuittanceExAAA) {
					List listQuittancevo = (List) converterTools
							.convertToListObjectVO(listQuittance);
					if (listQuittancevo != null
							&& !listQuittancevo.isEmpty()) {
						map.put("list"
								+ listQuittancevo.get(0).getClass()
										.getSimpleName(), listQuittancevo);
					}
					if (listeItems.size() > 1 && listeItems.get(1) instanceof QuittanceExAAA) {
						QuittanceExAAA quit = (QuittanceExAAA)listeItems.get(1);
						QuittanceExAAAVO quitVO = (QuittanceExAAAVO) ConverterTools
						.getInstance().convertToObjectVOWithoutList(quit);
						map.put("QuittanceExAAAVO", quitVO);
					}
					return map;
				} else {
					// Liste reglement VO
					if (listeItems.size() > 1) {
							map.put("list"
									+ listQuittance.get(0).getClass()
											.getSimpleName(), listQuittance);

						return map;
					}
					return listQuittance;
				}
			}
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return null;

	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

		List listOut = new ArrayList();
		QuittanceExAAAVO quittancevo = (QuittanceExAAAVO) vo;
		try {
			QuittanceExAAA quittance = (QuittanceExAAA) converterTools.convertToObjectBO(quittancevo);
			listOut.add(quittance);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
