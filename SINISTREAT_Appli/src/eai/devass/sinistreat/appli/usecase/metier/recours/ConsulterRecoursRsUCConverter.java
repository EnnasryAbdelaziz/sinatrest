package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;

public class ConsulterRecoursRsUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		RecoursVO recoursVO = null;
		try {
			Recours result = (Recours) listeItems.get(0);
			recoursVO = (RecoursVO) converterTools
					.convertToObjectVO(result);
//			if (recoursSortantVO.getEtatRecours().equals(IConstantes.ETAT_EN_COURS)){
//				recoursSortantVO.setLibelleEtatRecours("En cours");
//			}else if (recoursSortantVO.getEtatRecours().equals(IConstantes.ETAT_CLOTURE)){
//				recoursSortantVO.setLibelleEtatRecours("Clôturé");
//			}else if (recoursSortantVO.getEtatRecours().equals(IConstantes.ETAT_CLASSE_SANS_SUITE)){
//				recoursSortantVO.setLibelleEtatRecours("Classer sans suite");
//			}else if (recoursSortantVO.getEtatRecours().equals(IConstantes.ETAT_REOUVERT)){
//				recoursSortantVO.setLibelleEtatRecours("Réouvert");
//			}
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return recoursVO;
	}

	public List doConvertValueObjectToItems(Object vo) {
		List listOut = new ArrayList();
		RecoursVO recoursVO = (RecoursVO) vo;
		try {
			if (recoursVO.getId() == null) {
				throw new FonctionnelleException("EXP_ID_REQUIRED");
			}

			Recours recours = (Recours) converterTools
					.convertToObjectBO(recoursVO);
			listOut.add(recours);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}

		return listOut;
	}

}
