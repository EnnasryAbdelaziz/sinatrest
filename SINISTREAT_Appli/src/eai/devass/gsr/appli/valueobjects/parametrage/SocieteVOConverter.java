package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.Societe;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de NatureProthese
 * 
 * @author Nom Prenom (email)
 */
public class SocieteVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		SocieteVO vo = (SocieteVO) ovo;
		this.doValider(vo);
		Societe item = new Societe();
		voToItem(vo, item);
		List<Societe> itemList = new ArrayList<Societe>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		SocieteVO vo = new SocieteVO();
		if (itemList.get(0) instanceof Societe) {
			itemToVo(vo, (Societe) itemList.get(0), itemList);
		}

		return vo;
	}

	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof Societe) {
				SocieteVO vo = new SocieteVO();
				itemToVo(vo, (Societe) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(SocieteVO vo, Societe item, List itemList) {
		vo.setId(TypeConverter.getInstance().longToString(item.getId()));
		vo.setCode(TypeConverter.getInstance().longToString(item.getCode()));
		vo.setLibelle(item.getLibelle());
		vo.setRefSociete(item.getRefSociete());
		// vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));

		/*
		 * if (itemList == null) return;
		 * 
		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
		 */
	}

	public void voToItem(SocieteVO vo, Societe item) {
		item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
		if (vo.getCode() != null) {
			item.setCode(TypeConverter.getInstance().stringToLong(vo.getCode()));
		}
		item.setLibelle(vo.getLibelle());
		item.setRefSociete(vo.getRefSociete());
		// if(vo.getDateCreation()!=null)
		// item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));

	}

	protected void doValider(SocieteVO vo) throws ValidationException {

	}
}
