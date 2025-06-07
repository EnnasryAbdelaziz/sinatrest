package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeJuridictionVO;

public class ConsulterDerniereAudienceUCConverter extends
		ValueObjectConverterAbst implements IMessageException {
	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings({ "rawtypes" })
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		ProcedureJudiciaireVO procVO = null;

		try {
			String refJudiciaire = (String)listeItems.get(0);
			String organismeJudiciaire = (String)listeItems.get(1);
			Calendar dateDecision = (Calendar)listeItems.get(2);
			
			procVO = new ProcedureJudiciaireVO();
			
			TypeJuridictionVO typeJuridictionVO = new TypeJuridictionVO();
			
			typeJuridictionVO.setLibelle(organismeJudiciaire);
			
			AudienceJugementVO derniereAudienceVO = new AudienceJugementVO();
					
			derniereAudienceVO.setDateDecision(TypeConverter.getInstance().calendarToString(dateDecision));
			
			procVO.setNumeroDossierTribunal(refJudiciaire);
			
			procVO.setRefTypeJuridiction(typeJuridictionVO);
			
			procVO.setDerniereAudience(derniereAudienceVO);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return procVO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

		List listOut = new ArrayList();
		ProcedureJudiciaireVO procedureJudiciaireVO = (ProcedureJudiciaireVO) vo;

		ProcedureJudiciaire proJudiciaire = null;
		try {
			proJudiciaire = (ProcedureJudiciaire) converterTools
					.convertToObjectBO(procedureJudiciaireVO);
			listOut.add(proJudiciaire);
		} catch (Exception e) {
				logger.error("problème technique",e);
			    throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}
}
