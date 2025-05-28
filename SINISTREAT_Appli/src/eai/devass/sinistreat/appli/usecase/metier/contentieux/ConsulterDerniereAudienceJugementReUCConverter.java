//package eai.devass.sinistreat.appli.usecase.metier.contentieux;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import ma.co.omnidata.framework.services.businessInterface.ValidationException;
//import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
//import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
//import eai.devass.recoursentrant.application.utils.entites.IConstantes;
//import eai.devass.sinistreat.appli.utils.Fonctions;
//import eai.devass.sinistreat.appli.utils.exception.IMessageException;
//import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;
//
//public class ConsulterAudienceJugementReUCConverter extends
//ValueObjectConverterAbst implements IMessageException, IConstantes {
//	protected Fonctions functions = new Fonctions();
//	@SuppressWarnings({"rawtypes"})
//	public Object doConvertItemsToValueObject(List listeItems) {
//
//		if (listeItems == null || listeItems.isEmpty()) {
//			return null;
//		}
//		
//		AudienceJugementVO audience = null;
//		try {
//			List<AudienceJugementVO> list = (List<AudienceJugementVO>) listeItems.get(0);
//			audience =list.get(0);
//			
//
//		} catch (Exception e) {
//			logger.error("problème technique",e);
//		}
//
//		return audience;
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public List doConvertValueObjectToItems(Object vo)
//			throws ValidationUnitaireException, ValidationException {
//	
//		List listOut = new ArrayList();
//		AudienceJugementVO audience = (AudienceJugementVO)vo;
//		try {
//		
//			listOut.add(audience);
//		} catch (Exception e) {
//			if (Fonctions.sendMail) {
//				functions.sendMail(e, audience);
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

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class ConsulterDerniereAudienceJugementReUCConverter extends
ValueObjectConverterAbst implements IMessageException {
	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings({"rawtypes"})
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		ProcedureJudiciaireVO procVO = null;
		ProcedureJudiciaire proc = null;
		try {
			proc = (ProcedureJudiciaire) listeItems.get(0);
			if(proc!=null){
				procVO = (ProcedureJudiciaireVO) converterTools.convertToObjectVO(proc);
			}
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return procVO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
	
		List listOut = new ArrayList();
		SinistreVO sinistreVO = (SinistreVO)vo;
		Sinistre sinistre=null;
		try {
			sinistre = (Sinistre) converterTools.convertToObjectBO(sinistreVO);
			listOut.add(sinistre);		
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}
}

