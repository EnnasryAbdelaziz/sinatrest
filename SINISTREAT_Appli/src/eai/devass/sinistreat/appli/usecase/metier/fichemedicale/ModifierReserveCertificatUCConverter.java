package eai.devass.sinistreat.appli.usecase.metier.fichemedicale;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.fichemedicale.CertificatMedicalVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class ModifierReserveCertificatUCConverter extends
		ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();
	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		SinistreVO sinvo = null;
		Sinistre sin = (Sinistre) listeItems.get(0);
		try {
			if (sin == null) {
				return null;
			}
			sinvo = (SinistreVO) ConverterTools.getInstance()
					.convertToObjectVOWithoutList(sin);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return sinvo;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

		List listOut = new ArrayList();
		CertificatMedicalVO certificatMedicalVO = (CertificatMedicalVO) vo;
		try {
			CertificatMedical certificatMedical = (CertificatMedical) converterTools
					.convertToObjectBO(certificatMedicalVO);
			listOut.add(certificatMedical);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
