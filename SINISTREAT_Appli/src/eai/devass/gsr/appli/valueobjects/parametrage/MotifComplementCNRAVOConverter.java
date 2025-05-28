package eai.devass.gsr.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.CommunMouvementVOConverter;






/**
Value Object Converter de MotifComplementCNRA
@author Nom Prenom (email)
*/
public class MotifComplementCNRAVOConverter extends CommunMouvementVOConverter {
   
//   public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo) throws ValidationException {
//		MotifComplementCNRAVO vo = (MotifComplementCNRAVO) ovo;	
//		this.doValider(vo);		
//		MotifComplementCNRA item = new MotifComplementCNRA();	
//		voToItem(vo,item);
//		List<MotifComplementCNRA> itemList = new ArrayList<MotifComplementCNRA>();
//		itemList.add(item);
//		return itemList;
//	}
//
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ( (itemList == null) || (itemList.size()==0) ){
//			return null;
//		}
//		MotifComplementCNRAVO vo = new MotifComplementCNRAVO();
//		if (itemList.get(0) instanceof MotifComplementCNRA){
//			itemToVo(vo, (MotifComplementCNRA)itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MotifComplementCNRA) {
//				MotifComplementCNRAVO vo = new MotifComplementCNRAVO();
//				itemToVo(vo, (MotifComplementCNRA) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//	
//	public void itemToVo(MotifComplementCNRAVO vo, MotifComplementCNRA item, List itemList) {
//			vo.setId(item.getId() );
//
//
//		    vo.setCode(TypeConverter.getInstance().doubleToString(item.getCode()));	
//		    vo.setLibelle(item.getLibelle() );
//		    vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));	
//
//
//
//
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
//	public void voToItem(MotifComplementCNRAVO vo, MotifComplementCNRA item) {
//		item.setId(vo.getId());
//     if(vo.getCode()!=null)
//		item.setCode(TypeConverter.getInstance().stringToDouble(vo.getCode()));	
//		item.setLibelle(vo.getLibelle() );
//     if(vo.getDateCreation()!=null)
//		item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));	
//
//
//
//
//	}
	protected void doValider(IValueObject vo) throws ValidationException{
		
	}
}

