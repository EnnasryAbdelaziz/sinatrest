package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.modele.metier.sinistre.EtatSinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Mouvement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.EtatSinistreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.MouvementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

@SuppressWarnings("all")
public class ListMouvementSinistreUCConverter extends ValueObjectConverterAbst {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
		List<MouvementVO> listMouvementSinistreVO = new ArrayList<MouvementVO>();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		try {

			List<Mouvement> listMouvementSinistre = (List<Mouvement>) listeItems
					.get(0);
			if (CommonUtils.getInstance().isEmpty(listMouvementSinistre)) {
				return listMouvementSinistreVO;
			}
			Mouvement mouvement = listMouvementSinistre.get(0);
			mouvement.setPropertiesToConvert(new String[] { "id", "reserveOrd",
					"reserveGrave", "reserveProthese", "dateCreation",
					"userCreateur", "refEtatSinistre.refEtat.libelle",
					"refMotif.id", "refMotif.libelle" , "refEtatSinistre.refEtat.libelle"});
			listMouvementSinistreVO = (List<MouvementVO>) ConverterTools
					.getInstance().convertToListObjectVOWithoutList(
							listMouvementSinistre);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return listMouvementSinistreVO;

	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		SinistreVO sinistreVO = (SinistreVO) vo;
		try {
			Sinistre sinistre = (Sinistre) converterTools
					.convertToObjectBO(sinistreVO);
			listOut.add(sinistre);
		} catch (Exception e) {

			logger.error("problème technique",e);

		}
		return listOut;
	}
}
