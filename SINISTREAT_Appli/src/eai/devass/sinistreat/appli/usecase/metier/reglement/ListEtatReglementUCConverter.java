package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.modele.metier.reglement.EtatReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.EtatSinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;

import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.EtatReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.EtatSinistreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

@SuppressWarnings("all")
public class ListEtatReglementUCConverter extends ValueObjectConverterAbst {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
		List<EtatReglementVO> listEtatReglementVO = new ArrayList<EtatReglementVO>();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		try {

			List<EtatReglement> listEtatReglement = (List<EtatReglement>) listeItems
					.get(0);
			if (CommonUtils.getInstance().isEmpty(listEtatReglement)) {
				return listEtatReglementVO;
			}
			listEtatReglementVO = (List<EtatReglementVO>) ConverterTools
					.getInstance().convertToListObjectVOWithoutList(
							listEtatReglement);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return listEtatReglementVO;

	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		ReglementVO reglementVO = (ReglementVO) vo;
		try {
			Reglement reglement = (Reglement) converterTools
					.convertToObjectBO(reglementVO);
			listOut.add(reglement);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return listOut;
	}
}