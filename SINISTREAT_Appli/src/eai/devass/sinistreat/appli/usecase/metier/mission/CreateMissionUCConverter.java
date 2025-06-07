package eai.devass.sinistreat.appli.usecase.metier.mission;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import eai.devass.recoursentrant.application.utils.entites.IConstantes;
import eai.devass.sinistreat.appli.modele.metier.mission.MissionAT;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.mission.MissionATVO;

public class CreateMissionUCConverter extends ValueObjectConverterAbst
		implements IMessageException, IConstantes {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");

	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		MissionAT mission = (MissionAT) listeItems.get(0);
		MissionATVO missionVO = null;
		try {
			missionVO = (MissionATVO) converterTools.convertToObjectVO(mission);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return missionVO;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

		List listOut = new ArrayList();
		MissionATVO missvo = (MissionATVO) vo;
		try {
			if (missvo.getRefEtatMission() == null
					|| StringUtils.isEmpty(missvo.getRefEtatMission()
							.getCode())
					|| missvo.getRefEtatMission().getCode().equals("99")) {
				missvo.setRefEtatMission(null);
			}
			listOut.add(converterTools.convertToObjectBO(missvo));
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
