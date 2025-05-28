package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.recours.RecoursAmiable;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.recours.RecoursAmiableVO;

public class RechercherRecoursAmiableUCConverter extends
		ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();
	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();

	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		RecoursAmiableVO resultVO = new RecoursAmiableVO();
		RecoursAmiable recoursAmiable = (RecoursAmiable) listeItems.get(0);

		try {
			if (recoursAmiable != null) {
				resultVO = (RecoursAmiableVO) converterTools
						.convertToObjectVO(recoursAmiable);
			}

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return resultVO;
	}

	@SuppressWarnings("rawtypes")
	public List doConvertValueObjectToItems(Object vo) {

		List listOut = new ArrayList();
		RecoursAmiableVO recoursVO = (RecoursAmiableVO) vo;

		try {
			RecoursAmiable recoursAmiable = (RecoursAmiable) converterTools
					.convertToObjectBO(recoursVO);

			listOut.add(recoursAmiable);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}

		return listOut;
	}
}
