package eai.devass.sinistreat.appli.usecase.metier.fichemedicale;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.fichemedicale.CertificatMedicalVO;

public class CreateCertificatMedicalUCConverter extends  ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();	
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
						
	
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		CertificatMedicalVO certificatMedicalVO= null;
		CertificatMedical certificatMedical = (CertificatMedical)listeItems.get(0);
		try {
			if(certificatMedical==null) {
				return null;
			}
			
			certificatMedicalVO = (CertificatMedicalVO)converterTools.convertToObjectVOWithoutList(certificatMedical);
			
			

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return certificatMedicalVO;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		
		List listOut = new ArrayList();
		CertificatMedicalVO certificatMedicalVO = (CertificatMedicalVO) vo;
		try{
			
			// CertificatMedical
			// certificatMedical=(CertificatMedical)this.getItems(CertificatMedical.class);
			CertificatMedical certificatMedical = (CertificatMedical) converterTools.convertToObjectBO(certificatMedicalVO);
			
//			if( StringUtils.isEmpty(certificatMedical.getNomPrestataire())) {
//				throw new FonctionnelleException("EXP_NOM_AUXILIAIRE_OBLIGATOIRE");
//			}				
//			if( StringUtils.isEmpty(certificatMedical.getCodePrestataire())) {
//				throw new FonctionnelleException("EXP_CODE_AUXILIAIRE_OBLIGATOIRE");
//			}	
			
			listOut.add(certificatMedical);		
		} catch(Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
						
		return listOut;
	}
	

}
