package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.RelanceConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.DestinataireRelance;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.RelanceConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.DestinataireRelanceVO;

@SuppressWarnings("all")
public class ValiderRelanceConciliationUCConverter  extends ValueObjectConverterAbst
implements IMessageException{
	
	ConverterTools converterTools = ConverterTools.getInstance();
	protected Fonctions functions = new Fonctions();
	private Logger logger = Logger.getLogger("loggerSINAT");
	
	public Object doConvertItemsToValueObject(List listeItems) {
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		RelanceConciliationVO relanceConciliationVO = new RelanceConciliationVO();
		try {
			RelanceConciliation relance = (RelanceConciliation) listeItems.get(0);
			relanceConciliationVO = (RelanceConciliationVO) ConverterTools.getInstance().convertToObjectVO(relance);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return relanceConciliationVO;
	}

	
		public List doConvertValueObjectToItems(Object vo)
		throws ValidationUnitaireException, ValidationException {
				
				List listOut = new ArrayList();
				RelanceConciliationVO relanceConVO = (RelanceConciliationVO) vo;
				
				
				try {
					RelanceConciliation relanceConciliation = (RelanceConciliation) converterTools.convertToObjectBO(relanceConVO);
					listOut.add(relanceConciliation);
				} catch (Exception e) {
					Logger.getLogger("loggerSinat").fatal("Erreur lors de la conversion !", e);
					throw new ValidationUnitaireException(e.getMessage());
				}
				
				return listOut;
				}

}
