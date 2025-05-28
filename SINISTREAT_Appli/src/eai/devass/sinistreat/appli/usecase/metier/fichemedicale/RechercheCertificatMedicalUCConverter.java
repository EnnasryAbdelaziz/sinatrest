package eai.devass.sinistreat.appli.usecase.metier.fichemedicale;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.fichemedicale.CertificatMedicalVO;

public class RechercheCertificatMedicalUCConverter extends  ValueObjectConverterAbst 
		implements IMessageException {

	protected Fonctions functions = new Fonctions();	
	//ConverterMetier converterMetier = ConverterMetier.getInstance();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
						
	
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		List<CertificatMedicalVO> listCertificatVO= new ArrayList<CertificatMedicalVO>();
		//CertificatMedicalVO certifvo= null;
		try {
			List<CertificatMedical> listCertificat = (List<CertificatMedical>)listeItems.get(0);
			listCertificatVO=(List)converterTools.convertToListObjectVOWithoutList(listCertificat);
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return listCertificatVO;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		
		List listOut = new ArrayList();
		CertificatMedicalVO certificatVO = (CertificatMedicalVO) vo;
		try{
			

			CertificatMedical certificat=(CertificatMedical)converterTools.convertToObjectBO(certificatVO);
			
			listOut.add(certificat);		
		} catch(Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
						
		return listOut;
	}
	

}
