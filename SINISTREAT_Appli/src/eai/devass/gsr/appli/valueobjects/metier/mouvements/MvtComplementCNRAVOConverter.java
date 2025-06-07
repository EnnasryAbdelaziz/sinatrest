package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;

/**
 * Value Object Converter de MvtComplementCNRA
 * 
 * @author Nom Prenom (email)
 */
public class MvtComplementCNRAVOConverter extends CommunMouvementVOConverter {

//	public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MvtComplementCNRAVO vo = (MvtComplementCNRAVO) ovo;
//		this.doValider(vo);
//		MvtComplementCNRA item = new MvtComplementCNRA();
//		voToItem(vo, item);
//		List<MvtComplementCNRA> itemList = new ArrayList<MvtComplementCNRA>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MvtComplementCNRAVO vo = new MvtComplementCNRAVO();
//		if (itemList.get(0) instanceof MvtComplementCNRA) {
//			itemToVo(vo, (MvtComplementCNRA) itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MvtComplementCNRA) {
//				MvtComplementCNRAVO vo = new MvtComplementCNRAVO();
//				itemToVo(vo, (MvtComplementCNRA) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(MvtComplementCNRAVO vo, MvtComplementCNRA item,
//			List itemList) {
//
//		vo.setMntComplementCalcule(TypeConverter.getInstance().doubleToString(
//				item.getMntComplementCalcule()));
//		vo.setDatDecesParent(TypeConverter.getInstance().calendarToString(
//				item.getDatDecesParent()));
//		vo.setDatDepartAugmentation(TypeConverter.getInstance()
//				.calendarToString(item.getDatDepartAugmentation()));
//		vo.setMntComplementCNRA(TypeConverter.getInstance().doubleToString(
//				item.getMntComplementCNRA()));
//		vo.setNouvMntRente(TypeConverter.getInstance().doubleToString(
//				item.getNouvMntRente()));
//		vo.setNouvTaux(TypeConverter.getInstance().doubleToString(
//				item.getNouvTaux()));
//		vo.setParentDecede(item.getParentDecede());
//		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
//				item.getDateCreation()));
//
//		if (item.getRefMotifComplementCNRA() != null) {
//			vo.setRefMotifComplementCNRA(TypeConverter.getInstance()
//					.longToString(
//							((IEntite) item.getRefMotifComplementCNRA())
//									.getId()));
//			vo.setRefMotifComplementCNRALabel(item.getRefMotifComplementCNRA()
//					.toString());
//		}
//		if (item.getRefSituationRentier() != null) {
//			vo.setRefSituationRentier(TypeConverter.getInstance().longToString(
//					((IEntite) item.getRefSituationRentier()).getId()));
//			vo.setRefSituationRentierLabel(item.getRefSituationRentier()
//					.toString());
//		}
//
//		(new MvtConsignCNRAVOConverter()).itemToVo(vo, item, itemList);
//		/*
//		 * if (itemList == null) return;
//		 * 
//		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
//		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
//		 */
//	}
//
//	public void voToItem(MvtComplementCNRAVO vo, MvtComplementCNRA item) throws ValidationException {
//		if (StringUtils.isNotEmpty(vo.getMntComplementCalcule()))
//			// item.setMntComplementCalcule(TypeConverter.getInstance().stringToDouble(vo.getMntComplementCalcule()));
//			item.setMntComplementCalcule(TypeConverter.getInstance()
//					.stringToDouble(
//							StringUtils.replace(StringUtils.replace(
//									vo.getMntComplementCalcule(), " ", ""),
//									",", ".")));
//		if (StringUtils.isNotEmpty(vo.getDatDecesParent()))
//			item.setDatDecesParent(TypeConverter.getInstance()
//					.stringToCalendar("yyyyMMdd", vo.getDatDecesParent()));
//		if (StringUtils.isNotEmpty(vo.getDatDepartAugmentation()))
//			item.setDatDepartAugmentation(TypeConverter
//					.getInstance()
//					.stringToCalendar("yyyyMMdd", vo.getDatDepartAugmentation()));
//		if (StringUtils.isNotEmpty(vo.getMntComplementCNRA()))
//			// item.setMntComplementCNRA(TypeConverter.getInstance().stringToDouble(vo.getMntComplementCNRA()));
//			item.setMntComplementCNRA(TypeConverter
//					.getInstance()
//					.stringToDouble(
//							StringUtils.replace(
//									StringUtils.replace(
//											vo.getMntComplementCNRA(), " ", ""),
//									",", ".")));
//		if (StringUtils.isNotEmpty(vo.getNouvMntRente()))
//			// item.setNouvMntRente(TypeConverter.getInstance().stringToDouble(vo.getNouvMntRente()));
//			item.setNouvMntRente(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getNouvMntRente(), " ", ""),
//							",", ".")));
//		if (StringUtils.isNotEmpty(vo.getNouvTaux()))
//			// item.setNouvTaux(TypeConverter.getInstance().stringToDouble(vo.getNouvTaux()));
//			item.setNouvTaux(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getNouvTaux(), " ", ""),
//							",", ".")));
//		item.setParentDecede(vo.getParentDecede());
//		// if(vo.getDateCreation()!=null)
//		// item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));
//
//		if (StringUtils.isNotEmpty(vo.getRefMotifComplementCNRA())) {
//			MotifComplementCNRA refMotifComplementCNRA = new MotifComplementCNRA();
//
//			((IEntite) refMotifComplementCNRA)
//					.setId(TypeConverter.getInstance().stringToLong(
//							vo.getRefMotifComplementCNRA()));
//			item.setRefMotifComplementCNRA(refMotifComplementCNRA);
//		}
//		if (StringUtils.isNotEmpty(vo.getRefSituationRentier())) {
//			SituationRentier refSituationRentier = new SituationRentier();
//
//			((IEntite) refSituationRentier).setId(TypeConverter.getInstance()
//					.stringToLong(vo.getRefSituationRentier()));
//			item.setRefSituationRentier(refSituationRentier);
//		}
//
//		(new MvtConsignCNRAVOConverter()).voToItem(vo, item);
//	}

	protected void doValider(IValueObject vo) throws ValidationException {

	}
}
