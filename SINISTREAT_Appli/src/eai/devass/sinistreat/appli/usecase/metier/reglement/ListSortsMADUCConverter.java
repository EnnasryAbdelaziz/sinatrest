package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.parametrage.PlafondMAD;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PlafondMADVO;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

@SuppressWarnings("all")
public class ListSortsMADUCConverter extends ValueObjectConverterAbst {
	
	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {
		
		Map map = new HashMap();
		List<ReglementVO> listReglementVO = new ArrayList<ReglementVO>();
		List<PlafondMADVO> listPlafondMADVO = new ArrayList<PlafondMADVO>();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		try {

			List<Reglement> listReglement = (List<Reglement>) listeItems
					.get(0);
			List<PlafondMAD> listPlafondMAD = (List<PlafondMAD>) listeItems
					.get(1);
			if (CommonUtils.getInstance().isEmpty(listReglement)) {
				return listReglementVO;
			}
			if (CommonUtils.getInstance().isEmpty(listPlafondMAD)) {
				return listPlafondMADVO;
			}
			listReglementVO = (List<ReglementVO>) ConverterTools
					.getInstance().convertToListObjectVOWithoutList(
							listReglement);
			listPlafondMADVO = (List<PlafondMADVO>) ConverterTools
					.getInstance().convertToListObjectVOWithoutList(
							listPlafondMAD);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		map.put("listReglementVO", listReglementVO);
		map.put("listPlafondMADVO", listPlafondMADVO);

		return map;

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
	
	