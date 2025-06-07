package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.ModeReglement;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de ModeReglement
 * 
 * @author Nom Prenom (email)
 */
public class ModeReglementVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		ModeReglementVO vo = (ModeReglementVO) ovo;
		this.doValider(vo);
		ModeReglement item = new ModeReglement();
		voToItem(vo, item);
		List<ModeReglement> itemList = new ArrayList<ModeReglement>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ModeReglementVO vo = new ModeReglementVO();
		if (itemList.get(0) instanceof ModeReglement) {
			itemToVo(vo, (ModeReglement) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof ModeReglement) {
				ModeReglementVO vo = new ModeReglementVO();
				itemToVo(vo, (ModeReglement) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(ModeReglementVO vo, ModeReglement item, List itemList) {
		vo.setId(item.getId());

		vo.setCode(TypeConverter.getInstance().integerToString(item.getCode()));
		vo.setLibelle(item.getLibelle());
		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
				item.getDateCreation()));

		/*
		 * if (itemList == null) return;
		 * 
		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
		 */
	}

	//
	public void voToItem(ModeReglementVO vo, ModeReglement item) {
		if (vo != null) {
			item.setId(vo.getId());
			if (vo.getCode() != null) {
				item.setCode(TypeConverter.getInstance().stringToInteger(
						vo.getCode()));
			}
			item.setLibelle(vo.getLibelle());
			if (vo.getDateCreation() != null) {
				item.setDateCreation(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateCreation()));
			}
		}

	}

	// public IValueObject itemToVo(ModeReglementVO vo, ModeReglement item, List
	// itemList) {
	// ConverterTools converterTools = ConverterTools.getInstance();
	// try {
	// vo= (ModeReglementVO)
	// converterTools.getInstance().convertToObjectVO(item);
	// return vo;
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// logger.error("problème technique",e);
	// }
	// return null;
	// }
	// public IEntite voToItem(ModeReglementVO vo, ModeReglement item){
	// ConverterTools converterTools = ConverterTools.getInstance();
	// try {
	// item= (ModeReglement) converterTools.getInstance().convertToObjectBO(vo);
	// return (IEntite) item;
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// logger.error("problème technique",e);
	// }
	// return null;
	// }

	protected void doValider(ModeReglementVO vo) throws ValidationException {

	}
}
