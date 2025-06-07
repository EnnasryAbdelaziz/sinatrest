package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.SituationRentier;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de SituationRentier
 * 
 * @author Nom Prenom (email)
 */
public class SituationRentierVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		SituationRentierVO vo = (SituationRentierVO) ovo;
		this.doValider(vo);
		SituationRentier item = new SituationRentier();
		voToItem(vo, item);
		List<SituationRentier> itemList = new ArrayList<SituationRentier>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		SituationRentierVO vo = new SituationRentierVO();
		if (itemList.get(0) instanceof SituationRentier) {
			itemToVo(vo, (SituationRentier) itemList.get(0), itemList);
		}

		return vo;
	}

	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof SituationRentier) {
				SituationRentierVO vo = new SituationRentierVO();
				itemToVo(vo, (SituationRentier) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(SituationRentierVO vo, SituationRentier item, List itemList) {
		
		vo.setId(item.getId());
		vo.setCode(TypeConverter.getInstance().longToString(item.getCode()));
		vo.setLibelle(item.getLibelle());
		
	}

	public void voToItem(SituationRentierVO vo, SituationRentier item) {
		item.setId(vo.getId());
		if (vo.getCode() != null) {
			item.setCode(TypeConverter.getInstance().stringToLong(vo.getCode()));
		}
		item.setLibelle(vo.getLibelle());
		// if(vo.getDateCreation()!=null)
		// item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));

	}

	protected void doValider(SituationRentierVO vo) throws ValidationException {

	}
}
