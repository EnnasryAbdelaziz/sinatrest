package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.MvtEtatRentier;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de MvtEtatRentier
 * 
 * @author Nom Prenom (email)
 */
public class MvtEtatRentierVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		MvtEtatRentierVO vo = (MvtEtatRentierVO) ovo;
		this.doValider(vo);
		MvtEtatRentier item = new MvtEtatRentier();
		voToItem(vo, item);
		List<MvtEtatRentier> itemList = new ArrayList<MvtEtatRentier>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		MvtEtatRentierVO vo = new MvtEtatRentierVO();
		if (itemList.get(0) instanceof MvtEtatRentier) {
			itemToVo(vo, (MvtEtatRentier) itemList.get(0), itemList);
		}

		return vo;
	}

	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof MvtEtatRentier) {
				MvtEtatRentierVO vo = new MvtEtatRentierVO();
				itemToVo(vo, (MvtEtatRentier) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(MvtEtatRentierVO vo, MvtEtatRentier item, List itemList) {
		vo.setId(item.getId());

		vo.setCode(TypeConverter.getInstance().doubleToString(item.getCode()));
		vo.setLibelle(item.getLibelle());
		vo.setTypeEtat(item.getTypeEtat());
	}

	public void voToItem(MvtEtatRentierVO vo, MvtEtatRentier item) {
		item.setId(vo.getId());
		if (vo.getCode() != null) {
			item.setCode(TypeConverter.getInstance().stringToDouble(
					vo.getCode()));
		}
		item.setLibelle(vo.getLibelle());
		item.setTypeEtat(vo.getTypeEtat());
	}

	protected void doValider(MvtEtatRentierVO vo) throws ValidationException {

	}
}
