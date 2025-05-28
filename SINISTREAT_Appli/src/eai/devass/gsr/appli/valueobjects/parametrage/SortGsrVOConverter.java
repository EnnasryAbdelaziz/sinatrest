package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.SortGsr;

/**
 * Value Object Converter de SortGsr
 * 
 * @author Nom Prenom (email)
 */
public class SortGsrVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		SortGsrVO vo = (SortGsrVO) ovo;
		this.doValider(vo);
		SortGsr item = new SortGsr();
		voToItem(vo, item);
		List<SortGsr> itemList = new ArrayList<SortGsr>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		SortGsrVO vo = new SortGsrVO();
		if (itemList.get(0) instanceof SortGsr) {
			itemToVo(vo, (SortGsr) itemList.get(0), itemList);
		}

		return vo;
	}

	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof SortGsr) {
				SortGsrVO vo = new SortGsrVO();
				itemToVo(vo, (SortGsr) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(SortGsrVO vo, SortGsr item, List itemList) {
		vo.setId(item.getId());
		vo.setLibelle(item.getLibelle());
	}

	public void voToItem(SortGsrVO vo, SortGsr item) {
		item.setId(vo.getId());
		item.setLibelle(vo.getLibelle());
	}

	protected void doValider(SortGsrVO vo) throws ValidationException {

	}
}
