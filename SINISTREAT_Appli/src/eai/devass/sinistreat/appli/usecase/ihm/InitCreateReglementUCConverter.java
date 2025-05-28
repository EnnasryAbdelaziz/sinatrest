package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;

public class InitCreateReglementUCConverter extends ValueObjectConverterAbst {
	private ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		// TODO Auto-generated method stub
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();
		try {
			for (int i= 0; i<listeItems.size();i++){
				Object item = listeItems.get(i);
				if(item instanceof List){
					List itemList= (List)item;
					if(!itemList.isEmpty()){
						String nomClasse = itemList.get(0).getClass().getSimpleName();
						map.put("list"+nomClasse, converterTools.convertToListObjectVO(itemList));
					}
					
				}else{
					String nomClasse = item.getClass().getSimpleName();
					map.put(nomClasse, converterTools.convertToObjectVOWithoutList(item));
				}
			}
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return map;
	}

	@Override
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		ReglementVO rglvo = (ReglementVO) vo;
		try {
			Reglement rgl = (Reglement) converterTools.convertToObjectBO(rglvo);
			listOut.add(rgl);
		} catch (Exception e) {
			throw new ValidationUnitaireException(e.getMessage());
		}
		return listOut;
	}

}
