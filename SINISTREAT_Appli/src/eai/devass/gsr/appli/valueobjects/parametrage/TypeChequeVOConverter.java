package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.TypeCheque;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de TypeCheque
 * 
 * @author Nom Prenom (email)
 */
public class TypeChequeVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		TypeChequeVO vo = (TypeChequeVO) ovo;
		this.doValider(vo);
		TypeCheque item = new TypeCheque();
		voToItem(vo, item);
		List<TypeCheque> itemList = new ArrayList<TypeCheque>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		TypeChequeVO vo = new TypeChequeVO();
		if (itemList.get(0) instanceof TypeCheque) {
			itemToVo(vo, (TypeCheque) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof TypeCheque) {
				TypeChequeVO vo = new TypeChequeVO();
				itemToVo(vo, (TypeCheque) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(TypeChequeVO vo, TypeCheque item, List itemList) {
		if (item != null) {
			if (item.getId() != 0) {
				vo.setId(item.getId());
			}
			vo.setCode(TypeConverter.getInstance().doubleToString(
					item.getCode()));
			vo.setLibelle(item.getLibelle());
			vo.setDateCreation(TypeConverter.getInstance().calendarToString(
					item.getDateCreation()));
		}
	}

	public void voToItem(TypeChequeVO vo, TypeCheque item) {
		if (vo != null) {
			if (vo.getId() != 0) {
				item.setId(vo.getId());
			}
			if (vo.getCode() != null) {
				item.setCode(TypeConverter.getInstance().stringToDouble(
						vo.getCode()));
			}
			if (vo.getDateCreation() != null) {
				item.setDateCreation(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateCreation()));
			}
			item.setLibelle(vo.getLibelle());
		}

	}

	protected void doValider(TypeChequeVO vo) throws ValidationException {

	}
}
