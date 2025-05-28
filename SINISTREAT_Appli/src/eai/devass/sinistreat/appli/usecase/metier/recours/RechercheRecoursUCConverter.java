package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;

public class RechercheRecoursUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();

	@SuppressWarnings("unchecked")
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		List<RecoursVO> recoursSortantVOList = null;
		try {
			List<Recours> resultList = (List<Recours>) listeItems.get(0);
			if(CommonUtils.getInstance().isEmpty(resultList)) {
				return resultList;
			}
				resultList.get(0).setPropertiesToConvert(new String[] {"idRecours","refSinistre.numeroSinistre",
						"refSinistre.numeroGrave","refSinistre.refEvenement.dateAccident","numeroRecours","refCompagnie.libelle",
						"typeRecours","refEtatRecours.libelle"});
			
			recoursSortantVOList = (List) converterTools
					.convertToListObjectVOWithoutList(resultList);
		} catch (Exception e) {
		}

		return recoursSortantVOList;
	}

	public List doConvertValueObjectToItems(Object vo) {
		List listOut = new ArrayList();
		RecoursVO recoursVO = (RecoursVO) vo;
		try {
			Recours recoursSortant = (Recours) converterTools.convertToObjectBO(recoursVO);
			listOut.add(recoursSortant);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}

		return listOut;
	}

}
