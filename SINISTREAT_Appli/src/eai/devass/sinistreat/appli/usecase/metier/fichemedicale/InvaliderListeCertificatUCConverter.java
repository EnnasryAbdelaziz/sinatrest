package eai.devass.sinistreat.appli.usecase.metier.fichemedicale;

import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.ListVO;
import eai.devass.sinistreat.appli.valueobjects.metier.fichemedicale.CertificatMedicalVO;

public class InvaliderListeCertificatUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
		try {
			
			
			List<CertificatMedical> listCertificatMedical = (List<CertificatMedical>) listeItems
					.get(0);
			List<CertificatMedicalVO> listCertificats;
			listCertificats = (List<CertificatMedicalVO>) converterTools
					.convertToListObjectVO(listCertificatMedical);
			return listCertificats;
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return null;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException {
		ListVO list = (ListVO) vo;
		try {
			List listCertificatMedicalVO = list.getListCertificatMedical();
			List<CertificatMedical> listCertificats = (List<CertificatMedical>) converterTools
					.convertToListObjectBO(listCertificatMedicalVO);
			return listCertificats;
		} catch (Exception e) {
			throw new ValidationUnitaireException(e.getMessage());
		}
		
	}

}
