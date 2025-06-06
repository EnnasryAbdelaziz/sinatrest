package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;

public class RouvrirRecoursRsUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		RecoursVO recoursSortantVO= new RecoursVO();
		try {
			Recours recoursSortant = (Recours)listeItems.get(0);
			recoursSortantVO=(RecoursVO)converterTools.convertToObjectVO(recoursSortant);

			
		} catch (Exception e) {
			logger.error("probl�me technique",e);
		}
		
		
		return recoursSortantVO;
	}

	public List doConvertValueObjectToItems(Object vo) {
		List listOut = new ArrayList();
		RecoursVO recoursVO = (RecoursVO) vo;
		try {
			if ( StringUtils.isEmpty(recoursVO.getId())) {
				throw new FonctionnelleException("EXP_ID_REQUIRED");
			}
			
			Recours recoursSortant=(Recours)converterTools.convertToObjectBO(recoursVO);
			listOut.add(recoursSortant);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}

		return listOut;
	}
}
