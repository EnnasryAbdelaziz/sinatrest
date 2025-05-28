package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.RelanceConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.GestionnaireRelance;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.RelanceConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.GestionnaireRelanceVO;

public class InitRelanceConciliationUCConverter extends ValueObjectConverterAbst {
	
	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();
	
	@SuppressWarnings("unchecked")
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();
		List<ConciliationVO> listconciliationvo = new ArrayList<ConciliationVO>();
		List<GestionnaireRelanceVO> listGestionnaireRelanceVO = new ArrayList<GestionnaireRelanceVO>();
		try {
			List<Conciliation> listconciliation = (List<Conciliation>)listeItems.get(0);
			listconciliationvo = (List<ConciliationVO>)ConverterTools.getInstance().convertToListObjectVO(listconciliation);
			
			List<GestionnaireRelance> listGestionnaireRelance = (List<GestionnaireRelance>)listeItems.get(1);
			listGestionnaireRelanceVO = (List<GestionnaireRelanceVO>)ConverterTools.getInstance().convertToListObjectVO(listGestionnaireRelance);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		map.put("listconciliationvo", listconciliationvo);
		map.put("listGestionnaireRelanceVO", listGestionnaireRelanceVO);
		return map;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		 
		List listOut = new ArrayList();
		ConciliationVO conciliationVO = (ConciliationVO) vo;
		
		
		try {
			Conciliation conciliation = (Conciliation) converterTools.convertToObjectBO(conciliationVO);
			listOut.add(conciliation);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
		
		return listOut;
		}

   
}
