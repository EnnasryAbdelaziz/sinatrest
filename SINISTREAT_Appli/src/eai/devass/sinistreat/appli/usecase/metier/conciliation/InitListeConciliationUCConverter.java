package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Mouvement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.MouvementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

@SuppressWarnings("all")
public class InitListeConciliationUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	
	public Object doConvertItemsToValueObject(List listeItems) {
		Map map = new HashMap();
		//List<ConciliationVO> listConciliationVO = new ArrayList<ConciliationVO>();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		try {
			List<Conciliation> listConciliation = (List<Conciliation> ) listeItems.get(0);
			// Liste entite reglement
			if (listConciliation != null && !listConciliation.isEmpty()) {
				Conciliation conci = listConciliation.get(0);
				if (conci!= null) {
					((Conciliation) conci).setPropertiesToConvert(new String[] {
							"id", "dateEtat","refOrigineConciliation.code", "etat", "offre.id", "nomAvocat", "adresseAvocat", "refVilleAvocat.code","refVilleAvocat.libelle"});		
					List<ConciliationVO>  listConciliationVO = (List) ConverterTools.getInstance().convertToListObjectVO(listConciliation);
					if (listConciliationVO != null
							&& !listConciliationVO.isEmpty()) {
						map.put("list"
								+ listConciliationVO.get(0).getClass()
										.getSimpleName(), listConciliationVO);
					}
					return map;
				} 
				
			}
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return null;
	}


	
	
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		SinistreVO sinVO = (SinistreVO) vo;

		try {

			Sinistre sinistre = (Sinistre) converterTools
					.convertToObjectBO(sinVO);
			listOut.add(sinistre);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
