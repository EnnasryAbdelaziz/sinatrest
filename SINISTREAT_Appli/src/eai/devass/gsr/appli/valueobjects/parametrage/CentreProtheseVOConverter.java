package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.CentreProthese;
import eai.devass.gsr.appli.utile.TypeConverter;






/**
Value Object Converter de CentreProthese
@author Nom Prenom (email)
*/
public class CentreProtheseVOConverter implements IValueObjectConverter{
   
   public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo) throws ValidationException {
		CentreProtheseVO vo = (CentreProtheseVO) ovo;	
		this.doValider(vo);		
		CentreProthese item = new CentreProthese();	
		voToItem(vo,item);
		List<CentreProthese> itemList = new ArrayList<CentreProthese>();
		itemList.add(item);
		return itemList;
	}


	public Object convertItemsToValueObject(List itemList) {
		if ( (itemList == null) || (itemList.size()==0) ){
			return null;
		}
		CentreProtheseVO vo = new CentreProtheseVO();
		if (itemList.get(0) instanceof CentreProthese){
			itemToVo(vo, (CentreProthese)itemList.get(0), itemList);
		}
		
		return vo;
	}
	


	
	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for(int i=0;i<itemList.size();i++){
			if (itemList.get(i) instanceof CentreProthese) {
				CentreProtheseVO vo = new CentreProtheseVO();
				itemToVo(vo, (CentreProthese) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}
	
	public void itemToVo(CentreProtheseVO vo, CentreProthese item, List itemList) {
			vo.setId(item.getId() );
		    vo.setCode(TypeConverter.getInstance().doubleToString(item.getCode()));	
		    vo.setLibelle(item.getLibelle() );
		    vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));	
}

	public void voToItem(CentreProtheseVO vo, CentreProthese item) {
		item.setId(vo.getId());
     if(vo.getCode()!=null) {
		item.setCode(TypeConverter.getInstance().stringToDouble(vo.getCode()));
	}	
		item.setLibelle(vo.getLibelle() );
     if(vo.getDateCreation()!=null) {
		item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));
	}	




	}
	protected void doValider(CentreProtheseVO vo) throws ValidationException{
		
	}
}

