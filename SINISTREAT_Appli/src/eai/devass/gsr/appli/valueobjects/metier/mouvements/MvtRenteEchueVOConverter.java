package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;

/**
 * Value Object Converter de MvtRenteEchue
 * 
 * @author Nom Prenom (email)
 */
public class MvtRenteEchueVOConverter extends CommunMouvementVOConverter {

//	public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MvtRenteEchueVO vo = (MvtRenteEchueVO) ovo;
//		this.doValider(vo);
//		MvtRenteEchue item = new MvtRenteEchue();
//		voToItem(vo, item);
//		List<MvtRenteEchue> itemList = new ArrayList<MvtRenteEchue>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MvtRenteEchueVO vo = new MvtRenteEchueVO();
//		if (itemList.get(0) instanceof MvtRenteEchue) {
//			itemToVo(vo, (MvtRenteEchue) itemList.get(0), itemList);
//		}
//
//		return vo;
//	}
//
//	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
//		for (int i = 0; i < itemList.size(); i++) {
//			if (itemList.get(i) instanceof MvtRenteEchue) {
//				MvtRenteEchueVO vo = new MvtRenteEchueVO();
//				itemToVo(vo, (MvtRenteEchue) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(MvtRenteEchueVO vo, MvtRenteEchue item, List itemList) {
//
//		vo.setDatFinRente(TypeConverter.getInstance().calendarToString(
//				item.getDatFinRente()));
//		vo.setMntProrata(TypeConverter.getInstance().doubleToString(
//				item.getMntProrata()));
//		// vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));
//
//		if (item.getRefMotifRenteEchue() != null) {
//			vo.setRefMotifRenteEchue(TypeConverter.getInstance().longToString(
//					((IEntite) item.getRefMotifRenteEchue()).getId()));
//			vo.setRefMotifRenteEchueLabel(item.getRefMotifRenteEchue()
//					.toString());
//		}
//
//		(new MouvementVOConverter()).itemToVo(vo, item, itemList);
//		/*
//		 * if (itemList == null) return;
//		 * 
//		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
//		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
//		 */
//	}
//
//	public void voToItem(MvtRenteEchueVO vo, MvtRenteEchue item) {
//		if (StringUtils.isNotEmpty(vo.getDatFinRente()))
//			item.setDatFinRente(TypeConverter.getInstance().stringToCalendar(
//					"yyyyMMdd", vo.getDatFinRente()));
//		if (StringUtils.isNotEmpty(vo.getMntProrata()))
//			// item.setMntProrata(TypeConverter.getInstance().stringToDouble(vo.getMntProrata()));
//			item.setMntProrata(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getMntProrata(), " ", ""),
//							",", ".")));
//		// if(StringUtils.isNotEmpty(vo.getDateCreation()))
//		// item.setDateCreation(TypeConverter.getInstance().stringToCalendar("yyyyMMdd",vo.getDateCreation()));
//
//		if (StringUtils.isNotEmpty(vo.getRefMotifRenteEchue())) {
//			MotifRenteEchue refMotifRenteEchue = new MotifRenteEchue();
//
//			((IEntite) refMotifRenteEchue).setId(TypeConverter.getInstance()
//					.stringToLong(vo.getRefMotifRenteEchue()));
//			item.setRefMotifRenteEchue(refMotifRenteEchue);
//		}
//
//		(new MouvementVOConverter()).voToItem(vo, item);
//	}

	protected void doValider(IValueObject vo) throws ValidationException {

	}
}
