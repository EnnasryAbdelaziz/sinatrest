package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.TypeMvtProthese;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de TypeMvtProthese
 * 
 * @author Nom Prenom (email)
*/
public class TypeMvtProtheseVOConverter implements IValueObjectConverter{
   
   public IValidator getValidator() {
		return null;
	}

    public List convertValueObjectToItems(Object ovo)
            throws ValidationException {
		TypeMvtProtheseVO vo = (TypeMvtProtheseVO) ovo;	
		this.doValider(vo);		
		TypeMvtProthese item = new TypeMvtProthese();	
		voToItem(vo,item);
		List<TypeMvtProthese> itemList = new ArrayList<TypeMvtProthese>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ( (itemList == null) || (itemList.size()==0) ){
			return null;
		}
		TypeMvtProtheseVO vo = new TypeMvtProtheseVO();
		if (itemList.get(0) instanceof TypeMvtProthese){
			itemToVo(vo, (TypeMvtProthese)itemList.get(0), itemList);
		}
		
		return vo;
	}
	
	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for(int i=0;i<itemList.size();i++){
			if (itemList.get(i) instanceof TypeMvtProthese) {
				TypeMvtProtheseVO vo = new TypeMvtProtheseVO();
				itemToVo(vo, (TypeMvtProthese) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}
	
    public void itemToVo(TypeMvtProtheseVO vo, TypeMvtProthese item,
            List itemList) {
			vo.setId(item.getId() );

		    vo.setCode(TypeConverter.getInstance().doubleToString(item.getCode()));	
		    vo.setLibelle(item.getLibelle() );
        vo.setDateCreation(TypeConverter.getInstance().calendarToString(
                item.getDateCreation()));

}

	public void voToItem(TypeMvtProtheseVO vo, TypeMvtProthese item) {
		item.setId(vo.getId());
     if(vo.getCode()!=null) {
            item.setCode(TypeConverter.getInstance().stringToDouble(
                    vo.getCode()));
	}	
		item.setLibelle(vo.getLibelle() );
     if(vo.getDateCreation()!=null) {
            item.setDateCreation(TypeConverter.getInstance().stringToCalendar(
                    vo.getDateCreation()));
	}	

    }

	protected void doValider(TypeMvtProtheseVO vo) throws ValidationException{
		
	}
}
