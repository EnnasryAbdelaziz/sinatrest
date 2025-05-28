package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;






/**
Value Object Converter de MvtRemariage
@author Nom Prenom (email)
*/
public class MvtRemariageVOConverter extends CommunMouvementVOConverter {
   
//   public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo) throws ValidationException {
//		MvtRemariageVO vo = (MvtRemariageVO) ovo;	
//		this.doValider(vo);		
//		MvtRemariage item = new MvtRemariage();	
//		voToItem(vo,item);
//		List<MvtRemariage> itemList = new ArrayList<MvtRemariage>();
//		itemList.add(item);
//		return itemList;
//	}
//
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ( (itemList == null) || (itemList.size()==0) ){
//			return null;
//		}
//		MvtRemariageVO vo = new MvtRemariageVO();
//		if (itemList.get(0) instanceof MvtRemariage){
//			itemToVo(vo, (MvtRemariage)itemList.get(0), itemList);
//		}
//		
//		return vo;
//	}
//	
//
//
//	
//	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
//		for(int i=0;i<itemList.size();i++){
//			if (itemList.get(i) instanceof MvtRemariage) {
//				MvtRemariageVO vo = new MvtRemariageVO();
//				itemToVo(vo, (MvtRemariage) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//	
//	public void itemToVo(MvtRemariageVO vo, MvtRemariage item, List itemList) {
//
//
//		    vo.setDatRemariage(TypeConverter.getInstance().calendarToString(item.getDatRemariage()));	
//		    vo.setVofEnfants(TypeConverter.getInstance().booleanToString(item.getVofEnfants()));	
//		    vo.setMntRachatAnnuitee(TypeConverter.getInstance().doubleToString(item.getMntRachatAnnuitee()));			  
//
//		(new MouvementVOConverter()).itemToVo(vo,item, itemList);
///*
//	    if (itemList == null) return;
//
//		for (int i=1; i<itemList.size(); i++) {
//			if (itemList.get(i) instanceof List)
//			   if (((List)itemList.get(i)).size() != 0) {
//			   }
//		}
//*/
//}
//
//	public void voToItem(MvtRemariageVO vo, MvtRemariage item) {
//     if(vo.getDatRemariage()!=null)
//		item.setDatRemariage(TypeConverter.getInstance().stringToCalendar("yyyyMMdd",vo.getDatRemariage()));	
//     if(vo.getVofEnfants()!=null)
//		item.setVofEnfants(TypeConverter.getInstance().stringToBoolean(vo.getVofEnfants()));	
//     if(vo.getMntRachatAnnuitee()!=null)
////		item.setMntRachatAnnuitee(TypeConverter.getInstance().stringToDouble(vo.getMntRachatAnnuitee()));	
//     item.setMntRachatAnnuitee(TypeConverter.getInstance().stringToDouble(
//				StringUtils.replace(
//						StringUtils.replace(vo.getMntRachatAnnuitee(), " ", ""),
//						",", ".")));
//
//
//		(new MouvementVOConverter()).voToItem(vo,item);
//	}
	protected void doValider(IValueObject vo) throws ValidationException{
		
	}
}

