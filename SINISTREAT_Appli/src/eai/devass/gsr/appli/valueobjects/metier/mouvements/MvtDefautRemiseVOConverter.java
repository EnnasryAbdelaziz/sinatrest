package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;






/**
Value Object Converter de MvtDefautRemise
@author Nom Prenom (email)
*/
public class MvtDefautRemiseVOConverter extends CommunMouvementVOConverter {
   
//   public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo) throws ValidationException {
//		MvtDefautRemiseVO vo = (MvtDefautRemiseVO) ovo;	
//		this.doValider(vo);		
//		MvtDefautRemise item = new MvtDefautRemise();	
//		voToItem(vo,item);
//		List<MvtDefautRemise> itemList = new ArrayList<MvtDefautRemise>();
//		itemList.add(item);
//		return itemList;
//	}
//
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ( (itemList == null) || (itemList.size()==0) ){
//			return null;
//		}
//		MvtDefautRemiseVO vo = new MvtDefautRemiseVO();
//		if (itemList.get(0) instanceof MvtDefautRemise){
//			itemToVo(vo, (MvtDefautRemise)itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MvtDefautRemise) {
//				MvtDefautRemiseVO vo = new MvtDefautRemiseVO();
//				itemToVo(vo, (MvtDefautRemise) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//	
//	public void itemToVo(MvtDefautRemiseVO vo, MvtDefautRemise item, List itemList) {
//
//
//		    vo.setObservation(item.getObservation() );
//		    vo.setDatEffet(TypeConverter.getInstance().calendarToString("yyyyMMdd",item.getDatEffet()));	
//		    vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));	
//
//
//			if (item.getRefMotifDefautRemise() != null ) {
//				vo.setRefMotifDefautRemise(TypeConverter.getInstance().longToString(((IEntite)item.getRefMotifDefautRemise()).getId()));
//				vo.setRefMotifDefautRemiseLabel(item.getRefMotifDefautRemise().toString());
//			}
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
//	public void voToItem(MvtDefautRemiseVO vo, MvtDefautRemise item) {
//		item.setObservation(vo.getObservation() );
//     if(vo.getDatEffet()!=null)
//		item.setDatEffet(TypeConverter.getInstance().stringToCalendar("yyyyMMdd",vo.getDatEffet()));	
//     if(vo.getDateCreation()!=null)
//		item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));	
//
//
//		if (StringUtils.isNotEmpty(vo.getRefMotifDefautRemise())) {
//			MotifDefautRemise refMotifDefautRemise = new MotifDefautRemise();
//
//			((IEntite)refMotifDefautRemise).setId(TypeConverter.getInstance().stringToLong(vo.getRefMotifDefautRemise()));
//			item.setRefMotifDefautRemise(refMotifDefautRemise);
//		}
//
//
//		(new MouvementVOConverter()).voToItem(vo,item);
//	}
	protected void doValider(IValueObject vo) throws ValidationException{
		
	}
}

