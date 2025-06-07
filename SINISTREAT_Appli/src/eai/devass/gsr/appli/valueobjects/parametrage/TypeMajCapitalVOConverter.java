package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.TypeMajCapital;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de TypeMajCapital
 * 
 * @author Nom Prenom (email)
*/
public class TypeMajCapitalVOConverter implements IValueObjectConverter{
   
   public IValidator getValidator() {
		return null;
	}

    public List convertValueObjectToItems(Object ovo)
            throws ValidationException {
		TypeMajCapitalVO vo = (TypeMajCapitalVO) ovo;	
		this.doValider(vo);		
		TypeMajCapital item = new TypeMajCapital();	
		voToItem(vo,item);
		List<TypeMajCapital> itemList = new ArrayList<TypeMajCapital>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ( (itemList == null) || (itemList.size()==0) ){
			return null;
		}
		TypeMajCapitalVO vo = new TypeMajCapitalVO();
		if (itemList.get(0) instanceof TypeMajCapital){
			itemToVo(vo, (TypeMajCapital)itemList.get(0), itemList);
		}
		
		return vo;
	}
	
	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for(int i=0;i<itemList.size();i++){
			if (itemList.get(i) instanceof TypeMajCapital) {
				TypeMajCapitalVO vo = new TypeMajCapitalVO();
				itemToVo(vo, (TypeMajCapital) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}
	
	public void itemToVo(TypeMajCapitalVO vo, TypeMajCapital item, List itemList) {
			vo.setId(item.getId() );

		    vo.setCode(TypeConverter.getInstance().integerToString(item.getCode()));	
		    vo.setLibelle(item.getLibelle() );
        vo.setDateCreation(TypeConverter.getInstance().calendarToString(
                item.getDateCreation()));

}

	public void voToItem(TypeMajCapitalVO vo, TypeMajCapital item) {
        if (vo != null) {
			item.setId(vo.getId());
			if (vo.getCode() != null) {
				item.setCode(TypeConverter.getInstance().stringToInteger(
						vo.getCode()));
			}
			item.setLibelle(vo.getLibelle());
			if (vo.getDateCreation() != null) {
				item.setDateCreation(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateCreation()));
			}

		}

    }

	protected void doValider(TypeMajCapitalVO vo) throws ValidationException{
		
	}
}
