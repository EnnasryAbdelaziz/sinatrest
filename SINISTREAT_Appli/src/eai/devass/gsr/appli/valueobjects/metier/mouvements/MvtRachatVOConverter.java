package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.validation.IValidator;

/**
 * Value Object Converter de MvtRachat
 * 
 * @author Nom Prenom (email)
 */
public class MvtRachatVOConverter extends CommunMouvementVOConverter {

	public IValidator getValidator() {
		return null;
	}

//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MvtRachatVO vo = (MvtRachatVO) ovo;
//		this.doValider(vo);
//		MvtRachat item = new MvtRachat();
//		voToItem(vo, item);
//		List<MvtRachat> itemList = new ArrayList<MvtRachat>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MvtRachatVO vo = new MvtRachatVO();
//		if (itemList.get(0) instanceof MvtRachat) {
//			itemToVo(vo, (MvtRachat) itemList.get(0), itemList);
//		}
//
//		return vo;
//	}

//	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
//		for (int i = 0; i < itemList.size(); i++) {
//			if (itemList.get(i) instanceof MvtRachat) {
//				MvtRachatVO vo = new MvtRachatVO();
//				itemToVo(vo, (MvtRachat) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}

//	public void itemToVo(IValueObject valueObject, EntiteBO entiteBO, List itemList) {
//
//		MvtRachatVO vo = (MvtRachatVO) valueObject;
//		MvtRachat item = (MvtRachat) entiteBO;
//		
//		vo.setCapitalJuge(TypeConverter.getInstance().doubleToString(
//				item.getCapitalJuge()));
//		vo.setMntPercu(TypeConverter.getInstance().doubleToString(
//				item.getMntPercu()));
//		vo.setMntDiff(TypeConverter.getInstance().doubleToString(
//				item.getMntDiff()));
//		// vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));
//		vo.setDateCalcul(TypeConverter.getInstance().calendarToString(item.getDateCalcul()));
//        vo.setOrigineRachat(item.getOrigineRachat());
//		(new MouvementVOConverter()).itemToVo(vo, item, itemList);
//		/*
//		 * if (itemList == null) return;
//		 * 
//		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
//		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
//		 */
//	}
//
//	public void voToItem(IValueObject valueObject, EntiteBO entiteBO) {
//		
//		MvtRachatVO vo = (MvtRachatVO) valueObject;
//		MvtRachat item = (MvtRachat) entiteBO;
//		
//		if (vo.getCapitalJuge() != null)
//			// item.setCapitalJuge(TypeConverter.getInstance().stringToDouble(vo.getCapitalJuge()));
//			item.setCapitalJuge(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getCapitalJuge(), " ", ""),
//							",", ".")));
//		if (vo.getMntPercu() != null)
//			// item.setMntPercu(TypeConverter.getInstance().stringToDouble(vo.getMntPercu()));
//			item.setMntPercu(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getMntPercu(), " ", ""),
//							",", ".")));
//		if (vo.getMntDiff() != null)
//			// item.setMntDiff(TypeConverter.getInstance().stringToDouble(vo.getMntDiff()));
//			item.setMntDiff(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getMntDiff(), " ", ""), ",",
//							".")));
//		// if(vo.getDateCreation()!=null)
//		// item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));
//
//		 Calendar calCalcul = Calendar.getInstance();
//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		 if(vo.getDateCalcul()!=null){
//			 try {
//				calCalcul.setTime(sdf.parse(vo.getDateCalcul()));
//				 item.setDateCalcul(calCalcul);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				logger.error("problème technique",e);
//			}
//
//
//		 }
//		 item.setOrigineRachat(vo.getOrigineRachat());
//
//		(new MouvementVOConverter()).voToItem(vo, item);
//	}

	@Override
	protected void doValider(IValueObject vo) throws ValidationException {

	}

	
	

	
}
