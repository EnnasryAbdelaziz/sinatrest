package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.modele.metier.conciliation.RelanceConciliation;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.RelanceConciliationVO;

@SuppressWarnings("all")
public class ListeEditionRelanceConciliationUCConverter extends ValueObjectConverterAbst {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List<RelanceConciliationVO> listRelanceConciliationVO = new ArrayList<RelanceConciliationVO>();
		try {
			List<RelanceConciliation> listRelanceConciliation = (List<RelanceConciliation>)listeItems.get(0);
			if(CommonUtils.getInstance().isEmpty(listRelanceConciliation)) {
				return listRelanceConciliationVO;
			}

		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return listRelanceConciliationVO;
		
	}

    public List doConvertValueObjectToItems(Object vo)
            throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		RelanceConciliationVO relanceConciliationVO = (RelanceConciliationVO) vo;
		try {
			RelanceConciliation relanceConciliation = (RelanceConciliation) converterTools
                    .convertToObjectBO(relanceConciliationVO);
			listOut.add(relanceConciliation);
		} catch (Exception e) {
            Logger.getLogger("loggerGSR").fatal(
                    "Erreur lors de la conversion !", e);
		}
		return listOut;
	}
}

