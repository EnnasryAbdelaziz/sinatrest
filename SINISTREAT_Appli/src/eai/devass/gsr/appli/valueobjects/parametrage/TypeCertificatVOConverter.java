package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.TypeCertificat;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de TypeCertificat
 * 
 * @author Nom Prenom (email)
 */
public class TypeCertificatVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		TypeCertificatVO vo = (TypeCertificatVO) ovo;
		this.doValider(vo);
		TypeCertificat item = new TypeCertificat();
		voToItem(vo, item);
		List<TypeCertificat> itemList = new ArrayList<TypeCertificat>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		TypeCertificatVO vo = new TypeCertificatVO();
		if (itemList.get(0) instanceof TypeCertificat) {
			itemToVo(vo, (TypeCertificat) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof TypeCertificat) {
				TypeCertificatVO vo = new TypeCertificatVO();
				itemToVo(vo, (TypeCertificat) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(TypeCertificatVO vo, TypeCertificat item, List itemList) {
		vo.setId(item.getId());

		vo.setCode(TypeConverter.getInstance().doubleToString(item.getCode()));
		vo.setLibelle(item.getLibelle());
		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
				item.getDateCreation()));
	}

	public void voToItem(TypeCertificatVO vo, TypeCertificat item) {
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

	protected void doValider(TypeCertificatVO vo) throws ValidationException {

	}
}
