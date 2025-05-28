package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.TypeAction;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de TypeAction
 * 
 * @author Nom Prenom (email)
 */
public class TypeActionVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		TypeActionVO vo = (TypeActionVO) ovo;
		this.doValider(vo);
		TypeAction item = new TypeAction();
		voToItem(vo, item);
		List<TypeAction> itemList = new ArrayList<TypeAction>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		TypeActionVO vo = new TypeActionVO();
		if (itemList.get(0) instanceof TypeAction) {
			itemToVo(vo, (TypeAction) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof TypeAction) {
				TypeActionVO vo = new TypeActionVO();
				itemToVo(vo, (TypeAction) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(TypeActionVO vo, TypeAction item, List itemList) {
		vo.setId(item.getId());

		vo.setLibelle(item.getLibelle());
		vo.setCode(TypeConverter.getInstance().doubleToString(item.getCode()));
		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
				item.getDateCreation()));

		/*
		 * if (itemList == null) return;
		 * 
		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
		 */
	}

	public void voToItem(TypeActionVO vo, TypeAction item) {
		if (vo != null) {
			item.setId(vo.getId());
			item.setLibelle(vo.getLibelle());
			if (vo.getCode() != null) {
				item.setCode(TypeConverter.getInstance().stringToDouble(
						vo.getCode()));
			}
			if (vo.getDateCreation() != null) {
				item.setDateCreation(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateCreation()));
			}
		}
	}

	protected void doValider(TypeActionVO vo) throws ValidationException {

	}
}
