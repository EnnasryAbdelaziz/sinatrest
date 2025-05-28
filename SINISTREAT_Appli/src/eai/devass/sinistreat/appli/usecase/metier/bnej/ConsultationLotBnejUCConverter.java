package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.bnej.DossierBnej;
import eai.devass.sinistreat.appli.modele.metier.bnej.LotBnej;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.bnej.DossierBnejVO;
import eai.devass.sinistreat.appli.valueobjects.metier.bnej.LotBnejVO;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

public class ConsultationLotBnejUCConverter extends ValueObjectConverterAbst {

	private Logger logger = Logger.getLogger("loggerSINAT");
	private ConverterTools converterTools = ConverterTools.getInstance();
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		Map map = new HashMap();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		} else {

			List<DossierBnejVO> lstVo=new ArrayList<DossierBnejVO>();
			
			
			List<DossierBnej> listItem = (List<DossierBnej>) listeItems.get(0);
			
			
			
			try{
				for (DossierBnej dossierBnej : listItem) {
					if (dossierBnej != null && dossierBnej instanceof DossierBnej) {
						
						DossierBnejVO	dossierBnejVO = (DossierBnejVO) converterTools
							.convertToObjectVO(dossierBnej);
						
						
						lstVo.add(dossierBnejVO);
						
						
					}
					
				}
				map.put("listDossierBnejVO", lstVo);
				return map;
			}catch (Exception e) {
				logger.error("problème technique", e);
			}
			
			return null;
		}
	}

	@Override
	public List doConvertValueObjectToItems(Object arg0)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		LotBnejVO bnejVO = (LotBnejVO) arg0;

		try {

			LotBnej bnejItem = (LotBnej) converterTools
					.convertToObjectBO(bnejVO);
			listOut.add(bnejItem);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
