package eai.devass.sinistreat.appli.usecase.metier.contentieux;

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

public class ModifierRecoursReUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		RecoursVO recoursSortantVO= new RecoursVO();
		try {
			Recours recoursSortant = (Recours)listeItems.get(0);
			recoursSortantVO=(RecoursVO)converterTools.convertToObjectVO(recoursSortant);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return recoursSortantVO;
	}

	public List doConvertValueObjectToItems(Object vo) {
		List listOut = new ArrayList();
		RecoursVO recoursVO = (RecoursVO) vo;
		try {
			if (recoursVO.getId() == null
					|| recoursVO.getId().equals("")) {
				throw new FonctionnelleException("EXP_ID_REQUIRED");
			}
			if (recoursVO.getRefSinistre() == null
					|| recoursVO.getRefSinistre().getNumeroSinistre().equals("")) {
				throw new FonctionnelleException("EXP_NUMERO_SINISTRE_REQUIRED");
			}
			
			Recours recoursSortant=(Recours)converterTools.convertToObjectBO(recoursVO);
			listOut.add(recoursSortant);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}

		return listOut;
	}
}
