package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.MotifEtat;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de MotifEtat
 * 
 * @author Nom Prenom (email)
 */
public class MotifEtatVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		MotifEtatVO vo = (MotifEtatVO) ovo;
		this.doValider(vo);
		MotifEtat item = new MotifEtat();
		voToItem(vo, item);
		List<MotifEtat> itemList = new ArrayList<MotifEtat>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		MotifEtatVO vo = new MotifEtatVO();
		if (itemList.get(0) instanceof MotifEtat) {
			itemToVo(vo, (MotifEtat) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof MotifEtat) {
				MotifEtatVO vo = new MotifEtatVO();
				itemToVo(vo, (MotifEtat) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(MotifEtatVO vo, MotifEtat item, List itemList) {
		vo.setId(item.getId());

		vo.setCode(TypeConverter.getInstance().doubleToString(item.getCode()));
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

	public void voToItem(MotifEtatVO vo, MotifEtat item) {
		if (vo != null) {
			item.setId(vo.getId());
			if (vo.getCode() != null) {
				item.setCode(TypeConverter.getInstance().stringToDouble(
						vo.getCode()));
			}
			item.setLibelle(vo.getLibelle());
			if (vo.getDateCreation() != null) {
				item.setDateCreation(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateCreation()));
			}
		}
	}

	protected void doValider(MotifEtatVO vo) throws ValidationException {

	}
}
