package eai.devass.gsr.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.CommunMouvementVOConverter;

/**
 * Value Object Converter de MotifDefautRemise
 * 
 * @author Nom Prenom (email)
 */
public class MotifDefautRemiseVOConverter extends CommunMouvementVOConverter {

//	public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MotifDefautRemiseVO vo = (MotifDefautRemiseVO) ovo;
//		this.doValider(vo);
//		MotifDefautRemise item = new MotifDefautRemise();
//		voToItem(vo, item);
//		List<MotifDefautRemise> itemList = new ArrayList<MotifDefautRemise>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MotifDefautRemiseVO vo = new MotifDefautRemiseVO();
//		if (itemList.get(0) instanceof MotifDefautRemise) {
//			itemToVo(vo, (MotifDefautRemise) itemList.get(0), itemList);
//		}
//
//		return vo;
//	}
//
//	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
//		for (int i = 0; i < itemList.size(); i++) {
//			if (itemList.get(i) instanceof MotifDefautRemise) {
//				MotifDefautRemiseVO vo = new MotifDefautRemiseVO();
//				itemToVo(vo, (MotifDefautRemise) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(MotifDefautRemiseVO vo, MotifDefautRemise item,
//			List itemList) {
//		vo.setId(item.getId());
//
//		vo.setLibelle(item.getLibelle());
//		vo.setCode(TypeConverter.getInstance().doubleToString(item.getCode()));
//		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
//				item.getDateCreation()));
//
//		/*
//		 * if (itemList == null) return;
//		 * 
//		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
//		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
//		 */
//	}
//
//	public void voToItem(MotifDefautRemiseVO vo, MotifDefautRemise item) {
//		if (vo != null) {
//			item.setId(vo.getId());
//			item.setLibelle(vo.getLibelle());
//			if (vo.getCode() != null)
//				item.setCode(TypeConverter.getInstance().stringToDouble(
//						vo.getCode()));
//			if (vo.getDateCreation() != null)
//				item.setDateCreation(TypeConverter.getInstance()
//						.stringToCalendar(vo.getDateCreation()));
//
//		}
//	}

	protected void doValider(IValueObject vo) throws ValidationException {

	}
}
