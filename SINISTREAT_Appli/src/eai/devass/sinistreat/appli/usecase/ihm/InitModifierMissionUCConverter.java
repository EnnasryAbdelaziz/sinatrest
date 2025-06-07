package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.metier.mission.MissionATVO;

public class InitModifierMissionUCConverter extends ValueObjectConverterAbst {

	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	protected Fonctions functions = new Fonctions();
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		// TODO Auto-generated method stub
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();

		try {
			List listEtatMissionBO = (List) listeItems.get(0);
			List listDomainePrestBO = (List) listeItems.get(1);
			List listPresationBO = (List) listeItems.get(2);
			List listNatureMissionVO = (List) listeItems.get(3);

			List listEtatMissionVO = ConverterTools.getInstance()
					.convertToListObjectVO(listEtatMissionBO);
			List listDomainePrestVO = ConverterTools.getInstance()
					.convertToListObjectVO(listDomainePrestBO);

			List listPresationVO = ConverterTools.getInstance()
					.convertToListObjectVO(listPresationBO);
			map.put("listEtatMissionVO", listEtatMissionVO);
			map.put("listDomainePrestVO", listDomainePrestVO);
			map.put("listPresationVO", listPresationVO);
			map.put("listNatureMissionVO", listNatureMissionVO);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return map;
	}

	@Override
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		MissionATVO missvo = (MissionATVO) vo;
		try {
			listOut.add(converterTools.convertToObjectBO(missvo));
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
