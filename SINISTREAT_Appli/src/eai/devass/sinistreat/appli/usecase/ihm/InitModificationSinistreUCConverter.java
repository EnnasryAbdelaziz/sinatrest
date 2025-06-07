package eai.devass.sinistreat.appli.usecase.ihm;

/* @author joundi : 1 Nov. 10 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.SinAnterieurVictime;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PoliceUniversVO;

@SuppressWarnings("unchecked")
public class InitModificationSinistreUCConverter extends
        ValueObjectConverterAbst {
	private Logger logger = Logger.getLogger("loggerSINAT");
	
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		try {
			List listResultBO0 = (List)listeItems.get(0);
			List listResultBO1 = (List)listeItems.get(1);
			List listResultBO2 = (List)listeItems.get(2);
			List listResultBO3 = (List)listeItems.get(3);
			List listResultBO4 = (List)listeItems.get(4);
			List listResultBO5 = (List)listeItems.get(5);
			Sinistre sin = (Sinistre)listeItems.get(6);
			List listResultBO7 = (List)listeItems.get(7);
			List listResultBO8 = (List)listeItems.get(8);				
			List listResultBO9 = (List)listeItems.get(9);
			List listResultB10 = (List)listeItems.get(10);
			List listResultB11 = (List)listeItems.get(11);
			List listResultB12 = (List)listeItems.get(12);
			List listResultB13 = (List)listeItems.get(13);
			List listResultB14 = (List)listeItems.get(14);
			
            List listTypeAccidentVO = ConverterTools.getInstance()
                    .convertToListObjectVO(listResultBO0);
            List listGarantieVO = ConverterTools.getInstance()
                    .convertToListObjectVO(listResultBO1);
            List listZoneVO = ConverterTools.getInstance()
                    .convertToListObjectVO(listResultBO2);
            List listCauseVO = ConverterTools.getInstance()
                    .convertToListObjectVO(listResultBO3);
            List listVilleVO = ConverterTools.getInstance()
                    .convertToListObjectVO(listResultBO4);
            List listTypeMaladieVO = ConverterTools.getInstance()
                    .convertToListObjectVO(listResultBO5);
            SinistreVO sinvo = (SinistreVO) ConverterTools.getInstance()
                    .convertToObjectVOWithoutList(sin);
          //*sin anterieur 2022//
            Victime vicdb = sin.getRefVictime();
			List<SinAnterieurVictime> listSinAnt = vicdb.getListSinistreAnterieur();
			if (listSinAnt != null) {
				 List listsinAntVO = ConverterTools.getInstance()
		                    .convertToListObjectVO(listSinAnt);
				 sinvo.getRefVictime().setListSinistreAnterieur(listsinAntVO);
			}
			//*/
            
            if (sin.getRefEvenement() != null
                    && sin.getRefEvenement().getHeureAccident() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                sinvo.getRefEvenement().setHeureAccident(
                        sdf.format(sin.getRefEvenement().getHeureAccident()));
			}			
            List listPoliceUnivVO = ConverterTools.getInstance()
                    .convertToListObjectVO(listResultBO7);
            List listNationaliteVO = ConverterTools.getInstance()
                    .convertToListObjectVO(listResultBO9);
			
			for(Object polUniv : listPoliceUnivVO){
				String pol =((PoliceUniversVO) polUniv).getCode();
				if(sinvo.getNumeroPolice().equals(pol)){
					sinvo.setNumeroPoliceUniv(pol);
					sinvo.setNumeroPolice("");
					break;
				} 
			} 
            List listSituationVO = ConverterTools.getInstance()
                    .convertToListObjectVO(listResultBO8);
            List listPaysVO = ConverterTools.getInstance()
                    .convertToListObjectVO(listResultB10);
            List listTypeSuiviVO = ConverterTools.getInstance()
                    .convertToListObjectVO(listResultB11);
			List listTypeDeclarationVO = ConverterTools.getInstance().convertToListObjectVO(listResultB12);
			List listSourceDeclarationVO = ConverterTools.getInstance().convertToListObjectVO(listResultB13);
            List listConciliationVO = ConverterTools.getInstance().convertToListObjectVO(listResultB14);
			
			map.put("listTypeAccidentVO", listTypeAccidentVO);
			map.put("listGarantieVO", listGarantieVO);	
			map.put("listZoneVO", listZoneVO);	
			map.put("listCauseVO", listCauseVO);	
			map.put("listVilleVO", listVilleVO);
			map.put("listTypeMaladieVO", listTypeMaladieVO);
			map.put("SinistreVO", sinvo);				
			map.put("listPoliceUnivVO", listPoliceUnivVO);	
			map.put("listSituationVO", listSituationVO);				
			map.put("listNationaliteVO", listNationaliteVO);	
			map.put("listPaysVO", listPaysVO);
			map.put("listTypeSuiviVO", listTypeSuiviVO);
			map.put("listTypeDeclarationVO", listTypeDeclarationVO);
			map.put("listSourceDeclarationVO", listSourceDeclarationVO);
			map.put("listConciliationVO", listConciliationVO);
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		return map;
		
	}

    public List doConvertValueObjectToItems(Object vo)
            throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

}
