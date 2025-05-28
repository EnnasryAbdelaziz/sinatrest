package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.validation.IValidator;

/**
 * Value Object Converter de MvtSuspension
 * 
 * @author Nom Prenom (email)
 */
public class MvtSuspensionVOConverter extends CommunMouvementVOConverter {

	public IValidator getValidator() {
		return null;
	}

//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MvtSuspensionVO vo = (MvtSuspensionVO) ovo;
//		this.doValider(vo);
//		MvtSuspension item = new MvtSuspension();
//		voToItem(vo, item);
//		List<MvtSuspension> itemList = new ArrayList<MvtSuspension>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MvtSuspensionVO vo = new MvtSuspensionVO();
//		if (itemList.get(0) instanceof MvtSuspension) {
//			itemToVo(vo, (MvtSuspension) itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MvtSuspension) {
//				MvtSuspensionVO vo = new MvtSuspensionVO();
//				itemToVo(vo, (MvtSuspension) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(MvtSuspensionVO vo, MvtSuspension item, List itemList) {
//
//		vo.setDatSuspension(TypeConverter.getInstance().calendarToString(
//				item.getDatSuspension()));
//		vo.setMntRegle(TypeConverter.getInstance().doubleToString(
//				item.getMntRegle()));
//		vo.setMotif(item.getMotif());
//		vo.setNouvMntRente(TypeConverter.getInstance().doubleToString(
//				item.getNouvMntRente()));
//		if(item.getRefTypeCertificat() != null){
//			vo.setRefTypeCertificat(TypeConverter.getInstance().longToString(item.getRefTypeCertificat().getId()));
//			vo.setLibelleTypeCertificat(item.getRefTypeCertificat().getLibelle());
//		}
//		// vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));
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
//	public void voToItem(MvtSuspensionVO vo, MvtSuspension item) {
//		if (StringUtils.isNotEmpty(vo.getDatSuspension()))
//			item.setDatSuspension(TypeConverter.getInstance().stringToCalendar(
//					"yyyyMMdd", vo.getDatSuspension()));
//		if (StringUtils.isNotEmpty(vo.getMntRegle()))
//			// item.setMntRegle(TypeConverter.getInstance().stringToDouble(vo.getMntRegle()));
//
//			item.setMntRegle(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getMntRegle(), " ", ""),
//							",", ".")));
//		item.setMotif(vo.getMotif());
//		if (StringUtils.isNotEmpty(vo.getNouvMntRente()))
//			// item.setNouvMntRente(TypeConverter.getInstance().stringToDouble(vo.getNouvMntRente()));
//			item.setNouvMntRente(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getNouvMntRente(), " ", ""),
//							",", ".")));
//		// if(vo.getDateCreation()!=null)
//		// item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));
//
//		(new MouvementVOConverter()).voToItem(vo, item);
//	}
//
//	protected void doValider(MvtSuspensionVO vo) throws ValidationException {
//
//	}

	@Override
	protected void doValider(IValueObject vo) throws ValidationException {
		// TODO Auto-generated method stub
		
	}
}
