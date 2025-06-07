package eai.devass.gsr.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.CommunMouvementVOConverter;






/**
Value Object Converter de MotifRenteEchue
@author Nom Prenom (email)
*/
public class MotifRenteEchueVOConverter extends CommunMouvementVOConverter{
   
//   public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo) throws ValidationException {
//		MotifRenteEchueVO vo = (MotifRenteEchueVO) ovo;	
//		this.doValider(vo);		
//		MotifRenteEchue item = new MotifRenteEchue();	
//		voToItem(vo,item);
//		List<MotifRenteEchue> itemList = new ArrayList<MotifRenteEchue>();
//		itemList.add(item);
//		return itemList;
//	}
//
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ( (itemList == null) || (itemList.size()==0) ){
//			return null;
//		}
//		MotifRenteEchueVO vo = new MotifRenteEchueVO();
//		if (itemList.get(0) instanceof MotifRenteEchue){
//			itemToVo(vo, (MotifRenteEchue)itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MotifRenteEchue) {
//				MotifRenteEchueVO vo = new MotifRenteEchueVO();
//				itemToVo(vo, (MotifRenteEchue) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//	
//	public void itemToVo(MotifRenteEchueVO vo, MotifRenteEchue item, List itemList) {
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
//	public void voToItem(MotifRenteEchueVO vo, MotifRenteEchue item) {
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

