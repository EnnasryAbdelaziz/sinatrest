package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;

/**
 * Value Object Converter de MvtProthese
 * 
 * @author Nom Prenom (email)
 */
public class MvtProtheseVOConverter extends CommunMouvementVOConverter {

//	public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MvtProtheseVO vo = (MvtProtheseVO) ovo;
//		this.doValider(vo);
//		MvtProthese item = new MvtProthese();
//		voToItem(vo, item);
//		List<MvtProthese> itemList = new ArrayList<MvtProthese>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MvtProtheseVO vo = new MvtProtheseVO();
//		if (itemList.get(0) instanceof MvtProthese) {
//			itemToVo(vo, (MvtProthese) itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MvtProthese) {
//				MvtProtheseVO vo = new MvtProtheseVO();
//				itemToVo(vo, (MvtProthese) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(MvtProtheseVO vo, MvtProthese item, List itemList) {
//
//		vo.setDatMvtProthese(TypeConverter.getInstance().calendarToString(
//				"yyyyMMdd", item.getDatMvtProthese()));
//		vo.setDetailsMvt(item.getDetailsMvt());
//		vo.setMntFraisAppareil(TypeConverter.getInstance().doubleToString(
//				item.getMntFraisAppareil()));
//		vo.setMntMvtProthese(TypeConverter.getInstance().doubleToString(
//				item.getMntMvtProthese()));
//		vo.setNumProthese(TypeConverter.getInstance().integerToString(
//				item.getNumProthese()));
//		vo.setVofEstimatif(TypeConverter.getInstance().booleanToString(
//				item.getVofEstimatif()));
//		// vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));
//
//		if (item.getRefCentreProthese() != null) {
//			vo.setRefCentreProthese(TypeConverter.getInstance().longToString(
//					((IEntite) item.getRefCentreProthese()).getId()));
//			vo.setRefCentreProtheseLabel(item.getRefCentreProthese().toString());
//		}
//		if (item.getRefTypeMvtProthese() != null) {
//			vo.setRefTypeMvtProthese(TypeConverter.getInstance().longToString(
//					((IEntite) item.getRefTypeMvtProthese()).getId()));
//			vo.setRefTypeMvtProtheseLabel(item.getRefTypeMvtProthese()
//					.toString());
//		}
//		if (item.getRefNatureProthese() != null) {
//			vo.setRefNatureProthese(TypeConverter.getInstance().longToString(
//					((IEntite) item.getRefNatureProthese()).getId()));
//			vo.setRefNatureProtheseLabel(item.getRefNatureProthese().toString());
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
//	public void voToItem(MvtProtheseVO vo, MvtProthese item) throws ValidationException {
//		if (StringUtils.isNotEmpty(vo.getDatMvtProthese()))
//			item.setDatMvtProthese(TypeConverter.getInstance()
//					.stringToCalendar("yyyyMMddd", vo.getDatMvtProthese()));
//		item.setDetailsMvt(vo.getDetailsMvt());
//		if (StringUtils.isNotEmpty(vo.getMntFraisAppareil()))
//			// item.setMntFraisAppareil(TypeConverter.getInstance().stringToDouble(vo.getMntFraisAppareil()));
//			item.setMntFraisAppareil(TypeConverter.getInstance()
//					.stringToDouble(
//							StringUtils.replace(
//									StringUtils.replace(
//											vo.getMntFraisAppareil(), " ", ""),
//									",", ".")));
//		if (StringUtils.isNotEmpty(vo.getMntMvtProthese()))
//			// item.setMntMvtProthese(TypeConverter.getInstance().stringToDouble(vo.getMntMvtProthese()));
//			item.setMntMvtProthese(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(StringUtils.replace(
//							vo.getMntMvtProthese(), " ", ""), ",", ".")));
//		if (StringUtils.isNotEmpty(vo.getNumProthese()))
//			item.setNumProthese(TypeConverter.getInstance().stringToInteger(
//					vo.getNumProthese()));
//		if (StringUtils.isNotEmpty(vo.getVofEstimatif()))
//			item.setVofEstimatif(TypeConverter.getInstance().stringToBoolean(
//					vo.getVofEstimatif()));
//		// if(vo.getDateCreation()!=null)
//		// item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));
//
//		if (StringUtils.isNotEmpty(vo.getRefCentreProthese())) {
//			CentreProthese refCentreProthese = new CentreProthese();
//
//			((IEntite) refCentreProthese).setId(TypeConverter.getInstance()
//					.stringToLong(vo.getRefCentreProthese()));
//			item.setRefCentreProthese(refCentreProthese);
//		}
//		if (StringUtils.isNotEmpty(vo.getRefTypeMvtProthese())) {
//			TypeMvtProthese refTypeMvtProthese = new TypeMvtProthese();
//
//			((IEntite) refTypeMvtProthese).setId(TypeConverter.getInstance()
//					.stringToLong(vo.getRefTypeMvtProthese()));
//			item.setRefTypeMvtProthese(refTypeMvtProthese);
//		}
//		if (StringUtils.isNotEmpty(vo.getRefNatureProthese())) {
//			NatureProthese refNatureProthese = new NatureProthese();
//
//			((IEntite) refNatureProthese).setId(TypeConverter.getInstance()
//					.stringToLong(vo.getRefNatureProthese()));
//			item.setRefNatureProthese(refNatureProthese);
//		}
//
//		(new MouvementVOConverter()).voToItem(vo, item);
//	}

	protected void doValider(IValueObject vo) throws ValidationException {

	}
}
