package eai.devass.sinistreat.appli.usecase.metier.fichemedicale;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.fichemedicale.CertificatMedicalVO;

public class InvaliderListeCertifConstatationUCConverter extends
		ValueObjectConverterAbst implements IMessageException{

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {

		return listeItems.get(0);
	}

	public List doConvertValueObjectToItems(Object vo) {
		CertificatMedicalVO certificatVO = (CertificatMedicalVO) vo;
		List listOut = new ArrayList();
		try {
			if (certificatVO.getId() == null) {
				throw new FonctionnelleException("EXP_ID_REQUIRED");
			}
			CertificatMedical certificat = (CertificatMedical) converterTools
					.convertToObjectBO(certificatVO);
			listOut.add(certificat);

		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}

		return listOut;
	}
}
