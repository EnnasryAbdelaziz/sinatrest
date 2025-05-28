package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.ListVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;

public class CreateReglementUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
		try {
			if (listeItems == null || listeItems.isEmpty()) {
				return null;
			}
			if (listeItems.size() == 1) {
				ReglementVO reglementVO = (ReglementVO) converterTools
						.convertToObjectVO((Reglement) listeItems.get(0));
				return reglementVO;
			} else {
				return converterTools.convertToListObjectVO(listeItems);
			}
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return null;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		try {
			List<Reglement> lisrgl = new ArrayList<Reglement>();
			if (vo instanceof ListVO) {
				ListVO list = (ListVO) vo;
				List<ReglementVO> lisrglvo = list.getListReglement();

				lisrgl = (List) converterTools
						.convertToListObjectBOBis(lisrglvo);
			} else {
				Reglement rgl = (Reglement) converterTools
						.convertToObjectBO((ReglementVO) vo);
				
				
				
				lisrgl.add(rgl);
			}

			return lisrgl;
		} catch (Exception e) {
			throw new ValidationUnitaireException(e.getMessage());
		}
	}

}
