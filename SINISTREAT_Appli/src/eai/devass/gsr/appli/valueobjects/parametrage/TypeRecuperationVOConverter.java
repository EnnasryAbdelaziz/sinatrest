package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.TypeRecuperation;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de TypeRecuperation
 * 
 * @author Nom Prenom (email)
*/
public class TypeRecuperationVOConverter implements IValueObjectConverter{
   
   public IValidator getValidator() {
		return null;
	}

    public List convertValueObjectToItems(Object ovo)
            throws ValidationException {
		TypeRecuperationVO vo = (TypeRecuperationVO) ovo;	
		this.doValider(vo);		
		TypeRecuperation item = new TypeRecuperation();	
		voToItem(vo,item);
		List<TypeRecuperation> itemList = new ArrayList<TypeRecuperation>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ( (itemList == null) || (itemList.size()==0) ){
			return null;
		}
		TypeRecuperationVO vo = new TypeRecuperationVO();
		if (itemList.get(0) instanceof TypeRecuperation){
			itemToVo(vo, (TypeRecuperation)itemList.get(0), itemList);
		}
		
		return vo;
	}
	
	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for(int i=0;i<itemList.size();i++){
			if (itemList.get(i) instanceof TypeRecuperation) {
				TypeRecuperationVO vo = new TypeRecuperationVO();
				itemToVo(vo, (TypeRecuperation) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}
	
    public void itemToVo(TypeRecuperationVO vo, TypeRecuperation item,
            List itemList) {
			vo.setId(item.getId() );

		    vo.setCode(TypeConverter.getInstance().doubleToString(item.getCode()));	
		    vo.setLibelle(item.getLibelle() );
        vo.setDateCreation(TypeConverter.getInstance().calendarToString(
                item.getDateCreation()));

}

	public void voToItem(TypeRecuperationVO vo, TypeRecuperation item) {
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

	protected void doValider(TypeRecuperationVO vo) throws ValidationException{
		
	}
}
