package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;

/**
 * Value Object Converter de MvtConsignCNRA
 * 
 * @author Nom Prenom (email)
 */
public class MvtConsignCNRAVOConverter extends CommunMouvementVOConverter {

//	public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MvtConsignCNRAVO vo = (MvtConsignCNRAVO) ovo;
//		this.doValider(vo);
//		MvtConsignCNRA item = new MvtConsignCNRA();
//		voToItem(vo, item);
//		List<MvtConsignCNRA> itemList = new ArrayList<MvtConsignCNRA>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MvtConsignCNRAVO vo = new MvtConsignCNRAVO();
//		if (itemList.get(0) instanceof MvtConsignCNRA) {
//			itemToVo(vo, (MvtConsignCNRA) itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MvtConsignCNRA) {
//				MvtConsignCNRAVO vo = new MvtConsignCNRAVO();
//				itemToVo(vo, (MvtConsignCNRA) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(MvtConsignCNRAVO vo, MvtConsignCNRA item, List itemList) {
//
//		if (item instanceof MvtComplementCNRA)
//			vo.setTypeFille("MvtComplementCNRA");
//		if (item instanceof MvtProrataCNRA)
//			vo.setTypeFille("MvtProrataCNRA");
//
//		vo.setDatLimtePaiement(TypeConverter.getInstance().calendarToString(
//				item.getDatLimtePaiement()));
//		vo.setDatRcptCNRA(TypeConverter.getInstance().calendarToString(
//				item.getDatRcptCNRA()));
//		vo.setDatPriseEnCharge(TypeConverter.getInstance().calendarToString(
//				item.getDatPriseEnCharge()));
//		vo.setMntCNRA(TypeConverter.getInstance().doubleToString(
//				item.getMntCNRA()));
//		vo.setRefDossierCNRA(TypeConverter.getInstance().doubleToString(
//				item.getRefDossierCNRA()));
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
//	public void voToItem(MvtConsignCNRAVO vo, MvtConsignCNRA item) throws ValidationException {
//		if (StringUtils.isNotEmpty(vo.getDatLimtePaiement()))
//			item.setDatLimtePaiement(TypeConverter.getInstance()
//					.stringToCalendar("yyyyMMdd", vo.getDatLimtePaiement()));
//		if (StringUtils.isNotEmpty(vo.getDatRcptCNRA()))
//			item.setDatRcptCNRA(TypeConverter.getInstance().stringToCalendar(
//					"yyyyMMdd", vo.getDatRcptCNRA()));
//		if (StringUtils.isNotEmpty(vo.getDatPriseEnCharge()))
//			item.setDatPriseEnCharge(TypeConverter.getInstance()
//					.stringToCalendar("yyyyMMdd", vo.getDatPriseEnCharge()));
//		if (StringUtils.isNotEmpty(vo.getMntCNRA()))
//			// item.setMntCNRA(TypeConverter.getInstance().stringToDouble(vo.getMntCNRA()));
//			item.setMntCNRA(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getMntCNRA(), " ", ""), ",",
//							".")));
//		if (StringUtils.isNotEmpty(vo.getRefDossierCNRA()))
//			// item.setRefDossierCNRA(TypeConverter.getInstance().stringToDouble(vo.getRefDossierCNRA()));
//			item.setRefDossierCNRA(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(StringUtils.replace(
//							vo.getRefDossierCNRA(), " ", ""), ",", ".")));
//
//		(new MouvementVOConverter()).voToItem(vo, item);
//	}

	protected void doValider(IValueObject vo) throws ValidationException {

	}
}
