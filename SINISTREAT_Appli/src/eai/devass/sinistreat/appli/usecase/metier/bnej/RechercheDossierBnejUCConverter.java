package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.recoursentrant.application.valueobjects.metier.DossierBNEJVO;
import eai.devass.sinistreat.appli.modele.metier.bnej.DossierBnej;
import eai.devass.sinistreat.appli.modele.metier.bnej.LotBnej;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.bnej.DossierBnejVO;
import eai.devass.sinistreat.appli.valueobjects.metier.bnej.LotBnejVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
@SuppressWarnings("all")
public class RechercheDossierBnejUCConverter extends ValueObjectConverterAbst {

	private Logger logger = Logger.getLogger("loggerSINAT");
	private ConverterTools converterTools = ConverterTools.getInstance();

	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		List<DossierBnejVO> listDossierBnejVO = new ArrayList<DossierBnejVO>();
		try {
			List<DossierBnej> listDossierBnej = (List<DossierBnej>) listeItems
					.get(0);
			if (CommonUtils.getInstance().isEmpty(listDossierBnej)) {
				return listDossierBnejVO;
			}

			listDossierBnejVO = (List) converterTools
					.convertToListObjectVO(listDossierBnej);

			if (listeItems.size() > 1) {
				Map map = new HashMap();
				if (listDossierBnejVO != null && !listDossierBnejVO.isEmpty()) {
					map.put("list"
							+ listDossierBnejVO.get(0).getClass()
									.getSimpleName(), listDossierBnejVO);
				}
				return map;
			}

		} catch (Exception e) {
			logger.error("problème technique", e);
		}
		return listDossierBnejVO;
	}

	@Override
	public List doConvertValueObjectToItems(Object arg0)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		DossierBnejVO dossierVo = (DossierBnejVO) arg0;
		try {
			DossierBnej dossier = (DossierBnej) converterTools
					.convertToObjectBO(dossierVo);
			listOut.add(dossier);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
		return listOut;
	}

}
