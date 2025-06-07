package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.TypeMouvement;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de TypeMouvement
 * 
 * @author Nom Prenom (email)
 */
public class TypeMouvementVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		TypeMouvementVO vo = (TypeMouvementVO) ovo;
		this.doValider(vo);
		TypeMouvement item = new TypeMouvement();
		voToItem(vo, item);
		List<TypeMouvement> itemList = new ArrayList<TypeMouvement>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		TypeMouvementVO vo = new TypeMouvementVO();
		if (itemList.get(0) instanceof TypeMouvement) {
			itemToVo(vo, (TypeMouvement) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof TypeMouvement) {
				TypeMouvementVO vo = new TypeMouvementVO();
				itemToVo(vo, (TypeMouvement) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(TypeMouvementVO vo, TypeMouvement item, List itemList) {
		vo.setId(item.getId());

		vo.setCode(item.getCode());
		vo.setLibelle(item.getLibelle());
		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
				item.getDateCreation()));
	}

	public void voToItem(TypeMouvementVO vo, TypeMouvement item) {
		if (vo != null) {
			item.setId(vo.getId());
			if (vo.getCode() != null) {
				item.setCode(vo.getCode());
			}
			item.setLibelle(vo.getLibelle());
			if (vo.getDateCreation() != null) {
				item.setDateCreation(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateCreation()));
			}
		}
	}

	protected void doValider(TypeMouvementVO vo) throws ValidationException {

	}
}
