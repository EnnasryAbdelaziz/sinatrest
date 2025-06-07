package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.TypeApprobation;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de TypeApprobation
 * 
 * @author Nom Prenom (email)
*/
public class TypeApprobationVOConverter implements IValueObjectConverter{
   
   public IValidator getValidator() {
		return null;
	}

    public List convertValueObjectToItems(Object ovo)
            throws ValidationException {
		TypeApprobationVO vo = (TypeApprobationVO) ovo;	
		this.doValider(vo);		
		TypeApprobation item = new TypeApprobation();	
		voToItem(vo,item);
		List<TypeApprobation> itemList = new ArrayList<TypeApprobation>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ( (itemList == null) || (itemList.size()==0) ){
			return null;
		}
		TypeApprobationVO vo = new TypeApprobationVO();
		if (itemList.get(0) instanceof TypeApprobation){
			itemToVo(vo, (TypeApprobation)itemList.get(0), itemList);
		}
		
		return vo;
	}
	
	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for(int i=0;i<itemList.size();i++){
			if (itemList.get(i) instanceof TypeApprobation) {
				TypeApprobationVO vo = new TypeApprobationVO();
				itemToVo(vo, (TypeApprobation) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}
	
    public void itemToVo(TypeApprobationVO vo, TypeApprobation item,
            List itemList) {
			vo.setId(item.getId() );

		    vo.setCode(TypeConverter.getInstance().integerToString(item.getCode()));	
		    vo.setLibelle(item.getLibelle() );
        vo.setDateCreation(TypeConverter.getInstance().calendarToString(
                item.getDateCreation()));

}

	public void voToItem(TypeApprobationVO vo, TypeApprobation item) {
		item.setId(vo.getId());
     if(vo.getCode()!=null) {
            item.setCode(TypeConverter.getInstance().stringToInteger(
                    vo.getCode()));
	}	
		item.setLibelle(vo.getLibelle() );
     if(vo.getDateCreation()!=null) {
            item.setDateCreation(TypeConverter.getInstance().stringToCalendar(
                    vo.getDateCreation()));
	}	

    }

	protected void doValider(TypeApprobationVO vo) throws ValidationException{
		
	}
}
