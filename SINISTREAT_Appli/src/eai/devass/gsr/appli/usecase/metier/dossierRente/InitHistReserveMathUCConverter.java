package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eai.devass.gsr.appli.modele.metier.dossierRente.HistReserveMath;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.HistReserveMathVO;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.parametrage.GestionnaireRelance;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.GestionnaireRelanceVO;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

public class InitHistReserveMathUCConverter extends ValueObjectConverterAbst {
	
	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();
	
	@SuppressWarnings("unchecked")
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();
		List<HistReserveMathVO> listhistreservemathhvo = new ArrayList<HistReserveMathVO>();
		try {
			List<HistReserveMath> listhistreservemath = (List<HistReserveMath>)listeItems.get(0);
			listhistreservemathhvo = (List<HistReserveMathVO>)ConverterTools.getInstance().convertToListObjectVO(listhistreservemath);
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		map.put("listhistreservemathhvo", listhistreservemathhvo);
		return map;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		 
		List listOut = new ArrayList();
		HistReserveMathVO histReserveMathVO = (HistReserveMathVO) vo;
		
		
		try {
			HistReserveMath histReserveMath = (HistReserveMath) converterTools.convertToObjectBO(histReserveMathVO);
			listOut.add(histReserveMath);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
		
		return listOut;
		}

   
}
