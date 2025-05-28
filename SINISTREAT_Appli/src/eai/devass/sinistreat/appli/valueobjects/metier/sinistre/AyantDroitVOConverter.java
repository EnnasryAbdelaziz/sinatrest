package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.utils.ConverterTools;

public class AyantDroitVOConverter implements IValueObjectConverter  {
    private static final Logger LOGGER = Logger.getLogger("loggerSINAT");

	public Object convertItemsToValueObject(List itemList) {
        if ((itemList == null) || itemList.isEmpty()) {
			return null;
		}
		AyantDroitVO vo = new AyantDroitVO();
		if (itemList.get(0) instanceof AyantDroit) {
			itemToVo(vo, (AyantDroit) itemList.get(0), itemList);
		}

		return vo;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException, ValidationUnitaireException {
		AyantDroitVO vo = (AyantDroitVO) ovo;
	
		AyantDroit item = new AyantDroit();
		voToItem(vo, item);
		List<AyantDroit> itemList = new ArrayList<AyantDroit>();
		itemList.add(item);
		return itemList;
	}

	public IValidator getValidator() {
		return null;
	}

	public IValueObject itemToVo(AyantDroitVO vo, AyantDroit item, List itemList) {
		ConverterTools converterTools = ConverterTools.getInstance();
		try {
            vo = (AyantDroitVO) converterTools.getInstance().convertToObjectVO(
                    item);
			return vo;
		} catch (Exception e) {
            LOGGER.error("problème technique", e);
		}
		return null;
	}

	public IEntite voToItem(AyantDroitVO vo, AyantDroit item){
		ConverterTools converterTools = ConverterTools.getInstance();
		try {
            item = (AyantDroit) converterTools.getInstance().convertToObjectBO(
                    vo);
			return (IEntite) item;
		} catch (Exception e) {
            LOGGER.error("problème technique", e);
		}
		return null;
	}

}