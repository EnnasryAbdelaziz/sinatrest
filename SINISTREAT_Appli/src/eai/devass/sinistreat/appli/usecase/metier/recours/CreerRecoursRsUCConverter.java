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

public class CreerRecoursRsUCConverter extends ValueObjectConverterAbst implements
		IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		RecoursVO recoursVO= new RecoursVO();
		try {
			Recours recours = (Recours)listeItems.get(0);
			recoursVO=(RecoursVO)converterTools.convertToObjectVO(recours);

			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return recoursVO;
	}

	public List doConvertValueObjectToItems(Object vo) {
		List listOut = new ArrayList();
		RecoursVO recoursVO = (RecoursVO) vo;
		try {
			if (!StringUtils.isEmpty(recoursVO.getId())) {
				throw new FonctionnelleException("EXP_ID_NOT_NULL");
			}
			if (recoursVO.getRefSinistre() == null
					|| recoursVO.getRefSinistre().getNumeroSinistre().equals(""))
			 {
				throw new FonctionnelleException("EXP_NUMERO_SINISTRE_REQUIRED");
//			if (StringUtils.isEmpty(recoursVO.getCodeTypeAccident()))
//				throw new FonctionnelleException("EXP_TYPEACCIDENT_REQUIRED");
			}
			
			Recours recours=(Recours)converterTools.convertToObjectBO(recoursVO);
			listOut.add(recours);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}

		return listOut;
	}

}
