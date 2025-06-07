package eai.devass.gsr.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.CommunMouvementVOConverter;

/**
 * Value Object Converter de MotifManuelSuspensionRente
 * 
 * @author JEFFAR Hicham (email)
 */
public class MotifManuelSuspensionRenteVOConverter extends CommunMouvementVOConverter {

//	public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MotifManuelSuspensionRenteVO vo = (MotifManuelSuspensionRenteVO) ovo;
//		this.doValider(vo);
//		MotifManuelSuspensionRente item = new MotifManuelSuspensionRente();
//		voToItem(vo, item);
//		List<MotifManuelSuspensionRente> itemList = new ArrayList<MotifManuelSuspensionRente>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MotifManuelSuspensionRenteVO vo = new MotifManuelSuspensionRenteVO();
//		if (itemList.get(0) instanceof MotifManuelSuspensionRente) {
//			itemToVo(vo, (MotifManuelSuspensionRente) itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MotifManuelSuspensionRente) {
//				MotifManuelSuspensionRenteVO vo = new MotifManuelSuspensionRenteVO();
//				itemToVo(vo, (MotifManuelSuspensionRente) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(MotifManuelSuspensionRenteVO vo,
//			MotifManuelSuspensionRente item, List itemList) {
//		vo.setId(item.getId());
//
//		vo.setLibelle(item.getLibelle());
//		vo.setCode(item.getCode());
//		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
//				item.getDateCreation()));
//
//	}
//
//	public void voToItem(MotifManuelSuspensionRenteVO vo,
//			MotifManuelSuspensionRente item) {
//		if (vo != null) {
//			item.setId(vo.getId());
//			item.setLibelle(vo.getLibelle());
//			if (vo.getCode() != null)
//				item.setCode(vo.getCode());
//			if (vo.getDateCreation() != null)
//				item.setDateCreation(TypeConverter.getInstance()
//						.stringToCalendar(vo.getDateCreation()));
//		}
//	}

	protected void doValider(IValueObject vo)
			throws ValidationException {

	}
}
