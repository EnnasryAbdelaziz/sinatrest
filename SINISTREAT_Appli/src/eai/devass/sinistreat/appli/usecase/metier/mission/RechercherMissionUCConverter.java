package eai.devass.sinistreat.appli.usecase.metier.mission;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.recoursentrant.application.utils.entites.IConstantes;
import eai.devass.sinistreat.appli.modele.metier.mission.MissionAT;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.mission.MissionATVO;

public class RechercherMissionUCConverter extends ValueObjectConverterAbst
		implements IMessageException, IConstantes {

	protected Fonctions functions = new Fonctions();
	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List<MissionAT> missionList = (List<MissionAT>) listeItems.get(0);
		List<MissionATVO> missionVOList = null;
		try {
			missionVOList = (List<MissionATVO>) converterTools.convertToListObjectVO(missionList);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return missionVOList;
	}

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
