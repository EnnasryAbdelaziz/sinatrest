package eai.devass.sinistreat.appli.usecase.parametrage.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.parametrage.ActivitePrest;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.parametrage.ActivitePrestVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.VilleVO;


@SuppressWarnings("unchecked")
public class InitRecherchePrestataireUCConverter extends ValueObjectConverterAbst {

	protected Fonctions functions = new Fonctions();	
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		
		try {
			List listeDomaines =(List)listeItems.get(0);
			List listDomainesVO = converterTools.convertToListObjectVO(listeDomaines);
			List listeActivitesVO = converterTools.convertToListObjectVO((List)listeItems.get(1));
			List listeVillesVO = getLightListVille((List)listeItems.get(2));
			map.put("listDomainesVO", listDomainesVO);
			map.put("listeActivitesVO", listeActivitesVO);
			map.put("listeVillesVO", listeVillesVO);	
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

	
	private List<VilleVO> getLightListVille(List<Ville> listVille) {
		
		List<VilleVO> listOut = new ArrayList<VilleVO>();
		VilleVO villeVO = null;
		for(Ville curvVille : listVille) {
			villeVO = new VilleVO();
			villeVO.setCode(curvVille.getCode() + "|" + curvVille.getCodeVille());
			villeVO.setCodeVille(curvVille.getCodeVille());
			villeVO.setLibelle(curvVille.getLibelle());
			listOut.add(villeVO);
		}
		
		return listOut;
	}
	
	private List<ActivitePrestVO> getLightListActivitePrest(List<ActivitePrest> listActivitePrest) {
		
		List<ActivitePrestVO> listOut = new ArrayList<ActivitePrestVO>();
		ActivitePrestVO activitePrestVO = new ActivitePrestVO();
		for(ActivitePrest curActivitePrest : listActivitePrest) {
			activitePrestVO.setCode(curActivitePrest.getCode());
			activitePrestVO.setLibelle(curActivitePrest.getLibelle());
			listOut.add(activitePrestVO);
		}
		
		return listOut;
	}
    
    

   
}
