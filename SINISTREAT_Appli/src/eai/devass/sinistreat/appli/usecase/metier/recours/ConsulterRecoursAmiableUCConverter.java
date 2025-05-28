package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.modele.metier.recours.RecoursAmiable;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.recours.RecoursAmiableVO;

public class ConsulterRecoursAmiableUCConverter extends
		ValueObjectConverterAbst implements IMessageException {
	private Logger logger = Logger.getLogger("loggerSINAT");
	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		RecoursAmiableVO recoursAmiable = null;
		try {

			RecoursAmiable recours = (RecoursAmiable) listeItems.get(0);
			if (recours != null) {
				recoursAmiable = (RecoursAmiableVO) converterTools
						.convertToObjectVO(recours);
//				if (recours.getRefRecours() != null
//						&& recours.getRefRecours().getId() != null) {
//					recoursAmiable.setRefRecours(new RecoursVO(recours.getRefRecours().getId().toString()));
//				}
			}
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return recoursAmiable;
	}

	public List doConvertValueObjectToItems(Object vo) {

		List listOut = new ArrayList();
		RecoursAmiableVO recoursVO = (RecoursAmiableVO) vo;

		try {
			RecoursAmiable recoursAmiable = (RecoursAmiable) converterTools
					.convertToObjectBO(recoursVO);
			if (recoursVO.getRefRecours().getId() != null) {
				Recours refRecours = new Recours();
				refRecours.setId(new Long(recoursVO.getRefRecours().getId()));
				recoursAmiable.setRefRecours(refRecours);
			}
			listOut.add(recoursAmiable);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}

		return listOut;
	}

}
