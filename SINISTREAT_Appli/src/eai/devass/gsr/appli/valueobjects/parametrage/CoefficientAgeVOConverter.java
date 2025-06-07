package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.CoefficientAge;
import eai.devass.gsr.appli.utile.TypeConverter;






/**
Value Object Converter de CoefficientAge
@author Nom Prenom (email)
*/
public class CoefficientAgeVOConverter implements IValueObjectConverter{
   
   public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo) throws ValidationException {
		CoefficientAgeVO vo = (CoefficientAgeVO) ovo;	
		this.doValider(vo);		
		CoefficientAge item = new CoefficientAge();	
		voToItem(vo,item);
		List<CoefficientAge> itemList = new ArrayList<CoefficientAge>();
		itemList.add(item);
		return itemList;
	}


	public Object convertItemsToValueObject(List itemList) {
		if ( (itemList == null) || (itemList.size()==0) ){
			return null;
		}
		CoefficientAgeVO vo = new CoefficientAgeVO();
		if (itemList.get(0) instanceof CoefficientAge){
			itemToVo(vo, (CoefficientAge)itemList.get(0), itemList);
		}
		
		return vo;
	}
	


	
	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for(int i=0;i<itemList.size();i++){
			if (itemList.get(i) instanceof CoefficientAge) {
				CoefficientAgeVO vo = new CoefficientAgeVO();
				itemToVo(vo, (CoefficientAge) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}
	
	public void itemToVo(CoefficientAgeVO vo, CoefficientAge item, List itemList) {
			vo.setId(String.valueOf(item.getId()));
			vo.setAge(item.getAge().toString());
		    vo.setCoefficient(item.getCoefficient().toString());
		    vo.setType(item.getType());
/*
	    if (itemList == null) return;

		for (int i=1; i<itemList.size(); i++) {
			if (itemList.get(i) instanceof List)
			   if (((List)itemList.get(i)).size() != 0) {
			   }
		}
*/
}

	public void voToItem(CoefficientAgeVO vo, CoefficientAge item) {
	if(vo.getId()!=null) {
		item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
	}
     if(vo.getCoefficient()!=null) {
		item.setCoefficient(TypeConverter.getInstance().stringToDouble(vo.getCoefficient()));
	}	
     if(vo.getAge()!=null) {
		item.setAge(TypeConverter.getInstance().stringToLongObject(vo.getAge()));
	}	
		item.setType(vo.getType() );
  




	}
	protected void doValider(CoefficientAgeVO vo) throws ValidationException{
		
	}
}

