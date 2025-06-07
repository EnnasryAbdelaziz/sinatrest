package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.EtatSinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.EtatSinistreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

@SuppressWarnings("all")
public class ListEtatSinistreUCConverter extends ValueObjectConverterAbst {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
		List<EtatSinistreVO> listEtatSinistreVO = new ArrayList<EtatSinistreVO>();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		try {
			
			List<EtatSinistre> listEtatSinistre = (List<EtatSinistre>)listeItems.get(0);
			if(CommonUtils.getInstance().isEmpty(listEtatSinistre)) {
				return listEtatSinistreVO;
			}
			EtatSinistre etatSinistre = listEtatSinistre.get(0);
			etatSinistre.setPropertiesToConvert(new String[] {"id",
					"motifEtat","dateEtat","refEtat.code","refEtat.libelle","userCreateur"});
			listEtatSinistreVO = (List<EtatSinistreVO>) ConverterTools.getInstance()
					.convertToListObjectVOWithoutList(listEtatSinistre);

			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		return listEtatSinistreVO;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		SinistreVO sinistreVO = (SinistreVO) vo;
		try {
			Sinistre sinistre = (Sinistre) converterTools.convertToObjectBO(sinistreVO);
			listOut.add(sinistre);
		} catch (Exception e) {
//			if (Fonctions.sendMail){
//				functions.sendMail(e,sinistreVO);
				logger.error("problème technique",e);
	//		}
		}
		return listOut;
	}
}