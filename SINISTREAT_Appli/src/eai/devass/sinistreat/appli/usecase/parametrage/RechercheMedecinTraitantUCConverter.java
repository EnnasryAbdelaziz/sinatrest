package eai.devass.sinistreat.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.manager.CacheParametrageManager;
import eai.devass.sinistreat.appli.modele.parametrage.MedecinTraitant;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.parametrage.MedecinTraitantVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.VilleVO;

@SuppressWarnings("all")
public class RechercheMedecinTraitantUCConverter extends
		ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
		
	public Object doConvertItemsToValueObject(List listeItems) {
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		CacheParametrageManager cacheParametrageManager = new CacheParametrageManager();
		List<MedecinTraitantVO> listMedecinVO = new ArrayList<MedecinTraitantVO>();
		try {
			List<MedecinTraitant> listMedecin = (List<MedecinTraitant>) listeItems
					.get(0);
			
			int i = 0;
			Ville ville = null;
			MedecinTraitantVO medecinVO = null;
			VilleVO villeVO = null;
			for (MedecinTraitant curDetailVO : listMedecin) {
		
				medecinVO = (MedecinTraitantVO) converterTools.convertToObjectVO(curDetailVO);
				
				// REcuper le lib ville via le manager
				ville = cacheParametrageManager.getVilleByCode(curDetailVO.getRefVille().getCode());
				if(ville != null) {
					villeVO = new VilleVO();
					villeVO.setCodeVille(ville.getCode());
					villeVO.setCode(ville.getCode());
					villeVO.setLibelle(ville.getLibelle());
					medecinVO.setRefVille(villeVO);
				}
				listMedecinVO.add(medecinVO);
				
			}
			

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return listMedecinVO;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

		List listOut = new ArrayList();
		MedecinTraitantVO medecinVO = (MedecinTraitantVO) vo;
		try {
			MedecinTraitant med = (MedecinTraitant) converterTools.convertToObjectBO(medecinVO);
			
			// Ck : traitement special code ville, a ne pas supprimer !
			VilleVO ville = medecinVO.getRefVille();
			if(ville != null) {
				StringTokenizer stringTokenizer = new StringTokenizer(ville.getCode(), "|");
				stringTokenizer.nextToken();
				med.getRefVille().setCodeVille(stringTokenizer.nextToken());
			}
			
			
			listOut.add(med);
		} catch (Exception e) {
			throw new ValidationUnitaireException(e.getMessage());
		}
		return listOut;

	}
}
