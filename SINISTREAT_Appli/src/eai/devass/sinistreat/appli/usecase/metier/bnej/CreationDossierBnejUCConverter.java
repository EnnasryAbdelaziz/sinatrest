package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.bnej.DossierBnej;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.ListVO;
import eai.devass.sinistreat.appli.valueobjects.metier.bnej.DossierBnejVO;

public class CreationDossierBnejUCConverter extends ValueObjectConverterAbst {

	private Logger logger = Logger.getLogger("loggerSINAT");
	private ConverterTools converterTools = ConverterTools.getInstance();
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		} else {

			DossierBnej dossierBnejItem = (DossierBnej) listeItems.get(0);
			DossierBnejVO dossierBnejVO = new DossierBnejVO();

			try {
				dossierBnejVO = (DossierBnejVO) converterTools.convertToObjectVO(dossierBnejItem);
			} catch (Exception e) {
				logger.error("problème technique", e);
			}

			return dossierBnejVO;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List doConvertValueObjectToItems(Object arg0)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		ListVO listDossierBnejVO = (ListVO) arg0;

		try {
			for (DossierBnejVO dossierBnejVO2 : listDossierBnejVO.getListDossierBnej()) {
				DossierBnej dossierBnejItem = (DossierBnej) converterTools.convertToObjectBO(dossierBnejVO2);
				listOut.add(dossierBnejItem);
			}
			
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}
}
