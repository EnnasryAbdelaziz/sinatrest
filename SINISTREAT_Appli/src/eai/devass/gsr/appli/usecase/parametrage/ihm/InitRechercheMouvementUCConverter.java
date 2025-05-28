
package eai.devass.gsr.appli.usecase.parametrage.ihm;

/* @author joundi : 1 Nov. 10 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.valueobjects.parametrage.CentreProtheseVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMouvementVO;
import eai.devass.sinistreat.appli.utils.ConverterTools;


@SuppressWarnings("unchecked")
public class InitRechercheMouvementUCConverter extends ValueObjectConverterAbst {

	//protected Fonctions functions = new Fonctions();	
	//DateFormat dateFormat = new SimpleDateFormat(IDate.FORMAT_SIMPLE);
	//ConverterMetier converterMetier = ConverterMetier.getInstance();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		
		try {
			
		   	List listEtatRecoursVO = converterTools.convertToListObjectVO((List)listeItems.get(0));			
			List listEtatRentierVO = converterTools.convertToListObjectVO((List)listeItems.get(1));			
			List<TypeMouvementVO> listTypeMouvementVO = converterTools.convertToListObjectVO((List)listeItems.get(2));	
			List listTypeMvtProtheseVO = converterTools.convertToListObjectVO((List)listeItems.get(3));	
			List listModeReglementVO = converterTools.convertToListObjectVO((List)listeItems.get(4));	
			List listTypeReglementVO = converterTools.convertToListObjectVO((List)listeItems.get(5));	
			List typeActionVO = converterTools.convertToListObjectVO((List)listeItems.get(6));	
			List<CentreProtheseVO> listCentreProtheseVO = converterTools.convertToListObjectVO((List)listeItems.get(7));	
			List listEtatRenteVO = converterTools.convertToListObjectVO((List) listeItems.get(8));			
			List listTypeCertificatVO =  converterTools.convertToListObjectVO((List)listeItems.get(9)); 

			map.put("listEtatRecoursVO", listEtatRecoursVO);			
			map.put("listEtatRentierVO", listEtatRentierVO);
			map.put("listTypeMouvementVO", listTypeMouvementVO);
			map.put("listTypeMvtProtheseVO", listTypeMvtProtheseVO);
			map.put("listModeReglementVO", listModeReglementVO);
			map.put("listTypeReglementVO", listTypeReglementVO);
			map.put("listtypeActionVO", typeActionVO);
			map.put("listEtatRenteVO", listEtatRenteVO);
			map.put("listCentreProtheseVO", listCentreProtheseVO);
			map.put("listTypeCertificatVO", listTypeCertificatVO);
			
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}
}
