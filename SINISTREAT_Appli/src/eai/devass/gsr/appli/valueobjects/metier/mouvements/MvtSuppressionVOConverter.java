package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;

/**
 * Value Object Converter de MvtSuppression
 * 
 * @author Nom Prenom (email)
 */
public class MvtSuppressionVOConverter extends CommunMouvementVOConverter {

//	public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MvtSuppressionVO vo = (MvtSuppressionVO) ovo;
//		this.doValider(vo);
//		MvtSuppression item = new MvtSuppression();
//		voToItem(vo, item);
//		List<MvtSuppression> itemList = new ArrayList<MvtSuppression>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MvtSuppressionVO vo = new MvtSuppressionVO();
//		if (itemList.get(0) instanceof MvtSuppression) {
//			itemToVo(vo, (MvtSuppression) itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MvtSuppression) {
//				MvtSuppressionVO vo = new MvtSuppressionVO();
//				itemToVo(vo, (MvtSuppression) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(MvtSuppressionVO vo, MvtSuppression item, List itemList) {
//
//		vo.setMntIndu(TypeConverter.getInstance().doubleToString(
//				item.getMntIndu()));
//		vo.setDatSuppression(TypeConverter.getInstance().calendarToString(
//				item.getDatSuppression()));
//		vo.setMotif(item.getMotif());
//
//		if (item.getRefTypeAction() != null) {
//			vo.setRefTypeAction(TypeConverter.getInstance().longToString(
//					((IEntite) item.getRefTypeAction()).getId()));
//			vo.setRefTypeActionLabel(item.getRefTypeAction().toString());
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
//	public void voToItem(MvtSuppressionVO vo, MvtSuppression item) {
//		if (vo.getMntIndu() != null)
//			// item.setMntIndu(TypeConverter.getInstance().stringToDouble(vo.getMntIndu()));
//			item.setMntIndu(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getMntIndu(), " ", ""), ",",
//							".")));
//		if (vo.getDatSuppression() != null)
//			item.setDatSuppression(TypeConverter.getInstance()
//					.stringToCalendar("yyyyMMdd", vo.getDatSuppression()));
//		item.setMotif(vo.getMotif());
//
//		if (StringUtils.isNotEmpty(vo.getRefTypeAction())) {
//			TypeAction refTypeAction = new TypeAction();
//
//			((IEntite) refTypeAction).setId(TypeConverter.getInstance()
//					.stringToLong(vo.getRefTypeAction()));
//			item.setRefTypeAction(refTypeAction);
//		}
//
//		(new MouvementVOConverter()).voToItem(vo, item);
//	}

	protected void doValider(IValueObject vo) throws ValidationException {

	}
}
