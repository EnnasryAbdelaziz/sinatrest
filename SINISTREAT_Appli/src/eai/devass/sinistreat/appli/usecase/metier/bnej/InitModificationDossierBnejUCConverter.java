package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.modele.metier.bnej.DossierBnej;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.parametrage.TypeDecisionBnej;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.bnej.DossierBnejVO;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeDecisionBnejVO;

public class InitModificationDossierBnejUCConverter extends
		ValueObjectConverterAbst {

	private Logger logger = Logger.getLogger("loggerSINAT");
	private ConverterTools converterTools = ConverterTools.getInstance();

	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		Map map = new HashMap();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List<ProcedureJudiciaireVO> listProcedureJudiciaireVO = new ArrayList<ProcedureJudiciaireVO>();
		if (listeItems.size() > 1) {
			List<ProcedureJudiciaire> listProcedureJudiciaires = (List<ProcedureJudiciaire>) listeItems
					.get(1);
			try {
				listProcedureJudiciaireVO = (List) converterTools
						.convertToListObjectVOWithoutList(listProcedureJudiciaires);
			} catch (Exception e) {
				logger.error("problème technique", e);
			}
		}
		List<TypeDecisionBnejVO> listVO = new ArrayList<TypeDecisionBnejVO>();
		try {
			List<TypeDecisionBnej> list = (List<TypeDecisionBnej>) listeItems
					.get(0);
			for (TypeDecisionBnej decisionBnej : list) {
				if (decisionBnej != null
						&& decisionBnej instanceof TypeDecisionBnej) {

					TypeDecisionBnejVO decisionBnejVO = (TypeDecisionBnejVO) converterTools
							.convertToObjectVO(decisionBnej);
					listVO.add(decisionBnejVO);

				}

			}
			map.put("listProcedureJudiciaireVO", listProcedureJudiciaireVO);
			map.put("listTypeDecisionBnejVO", listVO);
			return map;

		} catch (Exception e) {
			logger.error("problème technique", e);
		}
		return null;
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
			Logger.getLogger("loggerAT").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
		return listOut;
	}

}
