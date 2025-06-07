package eai.devass.gsr.appli.valueobjects.parametrage;
 
import java.util.ArrayList;
import java.util.List;

import eai.devass.gsr.appli.modele.parametrage.TypeRevision;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
/**
@author aelhacham
*/
public class TypeRevisionVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		TypeRevisionVO vo = (TypeRevisionVO) ovo;
		this.doValider(vo);
		TypeRevision item = new TypeRevision();
		voToItem(vo, item);
		List<TypeRevision> itemList = new ArrayList<TypeRevision>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		TypeRevisionVO vo = new TypeRevisionVO();
		if (itemList.get(0) instanceof TypeRevision) {
			itemToVo(vo, (TypeRevision) itemList.get(0), itemList);
		}
		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof TypeRevision) {
				TypeRevisionVO vo = new TypeRevisionVO();
				itemToVo(vo, (TypeRevision) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(TypeRevisionVO vo, TypeRevision item, List itemList) {
		vo.setId(item.getId());
		vo.setId(item.getId());
		vo.setLibelle(item.getLibelle());
	}

	//
	public void voToItem(TypeRevisionVO vo, TypeRevision item) {
		if (vo != null) {
			if (vo.getId() != 0) {
				item.setId(vo.getId());
			}
			item.setLibelle(vo.getLibelle());
			
		}

	}
	protected void doValider(TypeRevisionVO vo) throws ValidationException {
	}
}
