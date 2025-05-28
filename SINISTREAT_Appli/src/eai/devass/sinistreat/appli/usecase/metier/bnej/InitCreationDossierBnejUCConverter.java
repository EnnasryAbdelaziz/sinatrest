package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.bnej.LotBnej;
import eai.devass.sinistreat.appli.modele.metier.conciliation.User;
import eai.devass.sinistreat.appli.modele.parametrage.TypeDossierBnej;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.bnej.LotBnejVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.UserVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeDossierBnejVO;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

public class InitCreationDossierBnejUCConverter extends
		ValueObjectConverterAbst {
	private Logger logger = Logger.getLogger("loggerSINAT");
	private ConverterTools converterTools = ConverterTools.getInstance();

	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		Map map = new HashMap();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List<TypeDossierBnejVO> listVO = new ArrayList<TypeDossierBnejVO>();
		try {
			List<TypeDossierBnej> list = (List<TypeDossierBnej>) listeItems
					.get(0);
			for (TypeDossierBnej dossierBnej : list) {
				if (dossierBnej != null
						&& dossierBnej instanceof TypeDossierBnej) {

					TypeDossierBnejVO dossierBnejVO = (TypeDossierBnejVO) converterTools
							.convertToObjectVO(dossierBnej);
					listVO.add(dossierBnejVO);

				}

			}

			map.put("listTypeDossierBnejVO", listVO);
			return map;

		} catch (Exception e) {
			logger.error("problème technique", e);
		}
		return null;
	}

	@Override
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
//		List<TypeDossierBnej> listOut = new ArrayList<TypeDossierBnej>();
//		TypeDossierBnejVO typeDossierBnejVO = (TypeDossierBnejVO) vo;
//		try {
//
//			TypeDossierBnej typeDossierBnej = (TypeDossierBnej) converterTools
//					.convertToObjectBO(typeDossierBnejVO);
//
//			listOut.add(typeDossierBnej);
//		} catch (Exception e) {
//			Logger.getLogger("loggerGSR").fatal(
//					"Erreur lors de la conversion !", e);
//			throw new ValidationUnitaireException(e.getMessage());
//		}
//
//		return listOut;
		return null;
	}
}
