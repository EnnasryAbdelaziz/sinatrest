package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.TypeVirement;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de TypeVirement
 * 
 * @author Nom Prenom (email)
 */
public class TypeVirementVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		TypeVirementVO vo = (TypeVirementVO) ovo;
		this.doValider(vo);
		TypeVirement item = new TypeVirement();
		voToItem(vo, item);
		List<TypeVirement> itemList = new ArrayList<TypeVirement>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		TypeVirementVO vo = new TypeVirementVO();
		if (itemList.get(0) instanceof TypeVirement) {
			itemToVo(vo, (TypeVirement) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof TypeVirement) {
				TypeVirementVO vo = new TypeVirementVO();
				itemToVo(vo, (TypeVirement) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(TypeVirementVO vo, TypeVirement item, List itemList) {
		vo.setId(item.getId());

		vo.setCode(TypeConverter.getInstance().integerToString(item.getCode()));
		vo.setLibelle(item.getLibelle());
		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
				item.getDateCreation()));

	}

	//
	public void voToItem(TypeVirementVO vo, TypeVirement item) {
		if (vo != null) {
			if (vo.getId() != 0) {
				item.setId(vo.getId());
			}
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

	protected void doValider(TypeVirementVO vo) throws ValidationException {

	}
}
