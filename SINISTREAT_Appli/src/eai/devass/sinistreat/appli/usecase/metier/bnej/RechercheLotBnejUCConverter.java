package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.bnej.LotBnej;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.bnej.LotBnejVO;

public class RechercheLotBnejUCConverter extends ValueObjectConverterAbst {

	private Logger logger = Logger.getLogger("loggerSINAT");
	private ConverterTools converterTools = ConverterTools.getInstance();

	@SuppressWarnings("rawtypes")
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {

		
		Map map = new HashMap();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		} else {

			List<LotBnejVO> lstVo=new ArrayList<LotBnejVO>();
			
			
			List<LotBnej> listItem = (List<LotBnej>) listeItems.get(0);
			
			
			
			try{
				for (LotBnej lotBnej : listItem) {
					if (lotBnej != null && lotBnej instanceof LotBnej) {

						
						 LotBnejVO	lotBnejVO = (LotBnejVO) converterTools
							.convertToObjectVO(lotBnej);
						
						
						lstVo.add(lotBnejVO);
						
						
					}
					
				}
				map.put("listLotBnejVO", lstVo);
				return map;
			}catch (Exception e) {
				logger.error("problème technique", e);
			}
			
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List<LotBnej> listOut = new ArrayList<LotBnej>();
		LotBnejVO lotBnejVO = (LotBnejVO) vo;
		try {

			LotBnej lotBnej = (LotBnej) converterTools.convertToObjectBO(lotBnejVO);

			listOut.add(lotBnej);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}
	
}


