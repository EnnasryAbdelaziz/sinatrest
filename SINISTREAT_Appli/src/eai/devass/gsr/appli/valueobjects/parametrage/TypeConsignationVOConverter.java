package eai.devass.gsr.appli.valueobjects.parametrage;
 
import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.TypeConsignation;
/**
@author aelhacham
*/
public class TypeConsignationVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)throws ValidationException{
		TypeConsignationVO vo = (TypeConsignationVO) ovo;
		this.doValider(vo);
		TypeConsignation item = new TypeConsignation();
		voToItem(vo, item);
		List<TypeConsignation> itemList = new ArrayList<TypeConsignation>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		TypeConsignationVO vo = new TypeConsignationVO();
		if (itemList.get(0) instanceof TypeConsignation) {
			itemToVo(vo, (TypeConsignation) itemList.get(0), itemList);
		}
		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof TypeConsignation) {
				TypeConsignationVO vo = new TypeConsignationVO();
				itemToVo(vo, (TypeConsignation) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(TypeConsignationVO vo, TypeConsignation item, List itemList) {
		vo.setId(item.getId());
		vo.setId(item.getId());
		vo.setLibelle(item.getLibelle());
	}

	//
	public void voToItem(TypeConsignationVO vo, TypeConsignation item) {
		if (vo != null) {
			if (vo.getId() != 0) {
				item.setId(vo.getId());
			}
			if (vo.getCode() != null) {
				item.setId(vo.getId());
			}
			item.setLibelle(vo.getLibelle());
			
		}

	}
	protected void doValider(TypeConsignationVO vo) throws ValidationException {
	}
}
