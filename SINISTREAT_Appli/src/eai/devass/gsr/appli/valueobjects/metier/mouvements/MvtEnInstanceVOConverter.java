package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;






/**
Value Object Converter de MvtEnInstance
@author Nom Prenom (email)
*/
public class MvtEnInstanceVOConverter extends CommunMouvementVOConverter {
   
//   public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo) throws ValidationException {
//		MvtEnInstanceVO vo = (MvtEnInstanceVO) ovo;	
//		this.doValider(vo);		
//		MvtEnInstance item = new MvtEnInstance();	
//		voToItem(vo,item);
//		List<MvtEnInstance> itemList = new ArrayList<MvtEnInstance>();
//		itemList.add(item);
//		return itemList;
//	}
//
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ( (itemList == null) || (itemList.size()==0) ){
//			return null;
//		}
//		MvtEnInstanceVO vo = new MvtEnInstanceVO();
//		if (itemList.get(0) instanceof MvtEnInstance){
//			itemToVo(vo, (MvtEnInstance)itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MvtEnInstance) {
//				MvtEnInstanceVO vo = new MvtEnInstanceVO();
//				itemToVo(vo, (MvtEnInstance) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//	
//	public void itemToVo(MvtEnInstanceVO vo, MvtEnInstance item, List itemList) {
//
//
//		    vo.setVofEnInstance(TypeConverter.getInstance().booleanToString(item.getVofEnInstance()));	
//		    vo.setDatVofEnInstance(TypeConverter.getInstance().calendarToString(item.getDatVofEnInstance()));	
//		    vo.setMotif(item.getMotif() );
//		    vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));	
//
//
//
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
//	public void voToItem(MvtEnInstanceVO vo, MvtEnInstance item) {
//     if(vo.getVofEnInstance()!=null)
//		item.setVofEnInstance(TypeConverter.getInstance().stringToBoolean(vo.getVofEnInstance()));	
//     if(vo.getDatVofEnInstance()!=null)
//		item.setDatVofEnInstance(TypeConverter.getInstance().stringToCalendar("yyyyMMdd", vo.getDatVofEnInstance()));	
//		item.setMotif(vo.getMotif() );
//		(new MouvementVOConverter()).voToItem(vo,item);
//	}
	protected void doValider(IValueObject vo) throws ValidationException{
		
	}
}

