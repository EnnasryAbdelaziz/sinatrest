package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.NatureProthese;
import eai.devass.gsr.appli.utile.TypeConverter;






/**
Value Object Converter de NatureProthese
@author Nom Prenom (email)
*/
public class NatureProtheseVOConverter implements IValueObjectConverter{
   
   public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo) throws ValidationException {
		NatureProtheseVO vo = (NatureProtheseVO) ovo;	
		this.doValider(vo);		
		NatureProthese item = new NatureProthese();	
		voToItem(vo,item);
		List<NatureProthese> itemList = new ArrayList<NatureProthese>();
		itemList.add(item);
		return itemList;
	}


	public Object convertItemsToValueObject(List itemList) {
		if ( (itemList == null) || (itemList.size()==0) ){
			return null;
		}
		NatureProtheseVO vo = new NatureProtheseVO();
		if (itemList.get(0) instanceof NatureProthese){
			itemToVo(vo, (NatureProthese)itemList.get(0), itemList);
		}
		
		return vo;
	}
	


	
	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for(int i=0;i<itemList.size();i++){
			if (itemList.get(i) instanceof NatureProthese) {
				NatureProtheseVO vo = new NatureProtheseVO();
				itemToVo(vo, (NatureProthese) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}
	
	public void itemToVo(NatureProtheseVO vo, NatureProthese item, List itemList) {
			vo.setId(item.getId());


		    vo.setCode(TypeConverter.getInstance().longToString(item.getCode()));	
		    vo.setLibelle(item.getLibelle() );
//		    vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));	




/*
	    if (itemList == null) return;

		for (int i=1; i<itemList.size(); i++) {
			if (itemList.get(i) instanceof List)
			   if (((List)itemList.get(i)).size() != 0) {
			   }
		}
*/
}

	public void voToItem(NatureProtheseVO vo, NatureProthese item) {
		item.setId(vo.getId());
     if(vo.getCode()!=null) {
		item.setCode(TypeConverter.getInstance().stringToLong(vo.getCode()));
	}	
		item.setLibelle(vo.getLibelle() );
//     if(vo.getDateCreation()!=null)
//		item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));	




	}
	protected void doValider(NatureProtheseVO vo) throws ValidationException{
		
	}
}

