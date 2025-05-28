package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;

public class SinistreVOConverter implements IValueObjectConverter  {

	private Logger logger = Logger.getLogger("loggerSINAT");
	
	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		SinistreVO vo = new SinistreVO();
		if (itemList.get(0) instanceof Sinistre) {
			itemToVo(vo, (Sinistre) itemList.get(0), itemList);
		}

		return vo;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException, ValidationUnitaireException {
		SinistreVO vo = (SinistreVO) ovo;
	
		Sinistre item = new Sinistre();
		voToItem(vo, item);
		List<Sinistre> itemList = new ArrayList<Sinistre>();
		itemList.add(item);
		return itemList;
	}

	public IValidator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public IValueObject itemToVo(SinistreVO vo, Sinistre item, List itemList) {
		ConverterTools converterTools = ConverterTools.getInstance();
		try {
			vo= (SinistreVO) converterTools.getInstance().convertToObjectVO(item);
			return vo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("itemToVo", e);
		}
		return null;
	}
	public IValueObject itemToVoWithoutList(SinistreVO vo, Sinistre item, List itemList) {
		ConverterTools converterTools = ConverterTools.getInstance();
		try {
			vo= (SinistreVO) converterTools.getInstance().convertToObjectVOWithoutList(item);
			return vo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("itemToVoWithoutList", e);
		}
		return null;
	}
	public IEntite voToItem(SinistreVO vo, Sinistre item){
		ConverterTools converterTools = ConverterTools.getInstance();
		try {
			item= (Sinistre) converterTools.getInstance().convertToObjectBO(vo);
			return item;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("voToItem", e);
		}
		return null;
	}


}