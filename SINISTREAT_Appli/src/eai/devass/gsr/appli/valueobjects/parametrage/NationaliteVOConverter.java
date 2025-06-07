package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.Nationalite;

/**
 * Value Object Converter de EtatRentier
 * 
 * @author AZAS (email)
 */
public class NationaliteVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		NationaliteVO vo = (NationaliteVO) ovo;
		this.doValider(vo);
		Nationalite item = new Nationalite();
		voToItem(vo, item);
		List<Nationalite> itemList = new ArrayList<Nationalite>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		NationaliteVO vo = new NationaliteVO();
		if (itemList.get(0) instanceof Nationalite) {
			itemToVo(vo, (Nationalite) itemList.get(0), itemList);
		}

		return vo;
	}

	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof Nationalite) {
				NationaliteVO vo = new NationaliteVO();
				itemToVo(vo, (Nationalite) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(NationaliteVO vo, Nationalite item, List itemList) {
		vo.setCode(item.getCode());
		vo.setLibelle(item.getLibelle());
	}

	public void voToItem(NationaliteVO vo, Nationalite item) {
		item.setCode(vo.getCode());
		item.setCode(vo.getCode());
		item.setLibelle(vo.getLibelle());

	}

	protected void doValider(NationaliteVO vo) throws ValidationException {

	}
}
