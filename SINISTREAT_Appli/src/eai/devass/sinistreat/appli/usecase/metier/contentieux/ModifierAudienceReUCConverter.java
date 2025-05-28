//package eai.devass.sinistreat.appli.usecase.metier.contentieux;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import ma.co.omnidata.framework.services.businessInterface.IValueObject;
//import ma.co.omnidata.framework.services.businessInterface.ValidationException;
//import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
//import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
//import eai.devass.recoursentrant.application.utils.entites.IConstantes;
//import eai.devass.sinistreat.appli.exception.FonctionnelleException;
//import eai.devass.sinistreat.appli.utils.Fonctions;
//import eai.devass.sinistreat.appli.utils.exception.IMessageException;
//import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;
//
//public class ModifierAudienceReUCConverter extends ValueObjectConverterAbst
//		implements IMessageException, IConstantes {
//	protected Fonctions functions = new Fonctions();
//
//	public Object doConvertItemsToValueObject(List listeItems) {
//
//		if (listeItems == null || listeItems.isEmpty()) {
//			return null;
//		}
//		AudienceJugementVO AudienceJugementVO = null;
//		try {
//			AudienceJugementVO = (AudienceJugementVO) listeItems.get(0);
//
//		} catch (Exception e) {
//			logger.error("problème technique",e);
//		}
//
//		return AudienceJugementVO;
//	}
//
//	public List doConvertValueObjectToItems(Object vo)
//			throws ValidationUnitaireException, ValidationException {
//
//		List listOut = new ArrayList();
//		AudienceJugementVO audienceVO = (AudienceJugementVO)vo;
//		try {
//			if (audienceVO.getDateConvocation() == null
//					|| audienceVO.getDateConvocation().isEmpty())
//				throw new FonctionnelleException(
//						"EXP_DATE_CONVOCATION_REQUIRED");
//			if (audienceVO.getNumeroSalleAudience() == null
//					|| audienceVO.getNumeroSalleAudience().isEmpty())
//				throw new FonctionnelleException(
//						"EXP_NUM_SALLE_AUDIENCE_REQUIRED");
//			if (audienceVO.getTypeConvocation() == null
//					|| audienceVO.getTypeConvocation().isEmpty())
//				throw new FonctionnelleException(
//						"EXP_TYPE_CONVOCATION_REQUIRED");
//			if (audienceVO.getTypeJugement() == null || audienceVO.getTypeJugement().isEmpty())
//				throw new FonctionnelleException("EXP_TYPE_JUGEMENT_REQUIRED");
//
//		} catch (Exception e) {
//			if (Fonctions.sendMail) {
//				functions.sendMail(e, audienceVO);
//				logger.error("problème technique",e);
//			}
//			throw new ValidationUnitaireException(e.getMessage());
//		}
//
//		return listOut;
//	}
//}

package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;

public class ModifierAudienceReUCConverter extends ValueObjectConverterAbst
		implements IMessageException {
	protected Fonctions functions = new Fonctions();
	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		AudienceJugementVO audienceVO = null;
		AudienceJugement audience = null;
		try {
			audience = (AudienceJugement) listeItems.get(0);
			audienceVO = (AudienceJugementVO) converterTools.convertToObjectVO(audience);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return audienceVO;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

		List listOut = new ArrayList();

		AudienceJugementVO audienceVO = (AudienceJugementVO) vo;
		AudienceJugement audience=null;
		try {
			if (StringUtils.isEmpty(audienceVO.getId())) {
				throw new FonctionnelleException(
						"EXP_ID_AUDIENCE_REQUIRED");
			}

			audience = (AudienceJugement) converterTools.convertToObjectBO(audienceVO);
			listOut.add(audience);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}
}

