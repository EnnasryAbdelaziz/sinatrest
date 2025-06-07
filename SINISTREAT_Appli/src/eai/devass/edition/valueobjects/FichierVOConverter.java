package eai.devass.edition.valueobjects;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.edition.modele.Fichier;
import eai.devass.sinistreat.appli.utils.ConverterTools;

public class FichierVOConverter implements IValueObjectConverter  {

    private static final Logger LOGGER = Logger.getLogger("loggerSINAT");
	
	public Object convertItemsToValueObject(List itemList) {
        if ((itemList == null) || (itemList.size() == 0) || itemList.isEmpty()) {
			return null;
		}
		FichierVO vo = new FichierVO();
		if (itemList.get(0) instanceof Fichier) {
			itemToVo(vo, (Fichier) itemList.get(0), itemList);
		}

		return vo;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException, ValidationUnitaireException {
		FichierVO vo = (FichierVO) ovo;
	
		Fichier item = new Fichier();
		voToItem(vo, item);
		List<Fichier> itemList = new ArrayList<Fichier>();
		itemList.add(item);
		return itemList;
	}

	public IValidator getValidator() {
		return null;
	}

	public IValueObject itemToVo(FichierVO vo, Fichier item, List itemList) {
		ConverterTools converterTools = ConverterTools.getInstance();
		try {
            vo = (FichierVO) converterTools.getInstance().convertToObjectVO(
                    item);
			return vo;
		} catch (Exception e) {
            LOGGER.error("itemToVo", e);
		}
		return null;
	}

    public IValueObject itemToVoWithoutList(FichierVO vo, Fichier item,
            List itemList) {
		ConverterTools converterTools = ConverterTools.getInstance();
		try {
            vo = (FichierVO) converterTools.getInstance()
                    .convertToObjectVOWithoutList(item);
			return vo;
		} catch (Exception e) {
            LOGGER.error("itemToVoWithoutList", e);
		}
		return null;
	}

	public IEntite voToItem(FichierVO vo, Fichier item){
		ConverterTools converterTools = ConverterTools.getInstance();
		try {
			item= (Fichier) converterTools.getInstance().convertToObjectBO(vo);
			return item;
		} catch (Exception e) {
            LOGGER.error("itemToVo", e);
		}
		return null;
	}

}