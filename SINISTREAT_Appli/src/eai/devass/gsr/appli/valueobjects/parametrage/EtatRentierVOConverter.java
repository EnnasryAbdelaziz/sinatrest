package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.utile.TypeConverter;






/**
Value Object Converter de EtatRentier
@author Nom Prenom (email)
*/
public class EtatRentierVOConverter implements IValueObjectConverter{
   
   public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo) throws ValidationException {
		EtatRentierVO vo = (EtatRentierVO) ovo;	
		this.doValider(vo);		
		EtatRentier item = new EtatRentier();	
		voToItem(vo,item);
		List<EtatRentier> itemList = new ArrayList<EtatRentier>();
		itemList.add(item);
		return itemList;
	}


	public Object convertItemsToValueObject(List itemList) {
		if ( (itemList == null) || (itemList.size()==0) ){
			return null;
		}
		EtatRentierVO vo = new EtatRentierVO();
		if (itemList.get(0) instanceof EtatRentier){
			itemToVo(vo, (EtatRentier)itemList.get(0), itemList);
		}
		
		return vo;
	}
	


	
	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for(int i=0;i<itemList.size();i++){
			if (itemList.get(i) instanceof EtatRentier) {
				EtatRentierVO vo = new EtatRentierVO();
				itemToVo(vo, (EtatRentier) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}
	
	public void itemToVo(EtatRentierVO vo, EtatRentier item, List itemList) {
			vo.setId(item.getId() );

			vo.setCode(TypeConverter.getInstance().doubleToString(item.getCode()));	
		    vo.setLibelle(item.getLibelle() );		    
   		    vo.setTypeEtat(item.getTypeEtat());		    

}

	public void voToItem(EtatRentierVO vo, EtatRentier item) {
		item.setId(vo.getId());
     if(vo.getCode()!=null) {
		item.setCode(TypeConverter.getInstance().stringToDouble(vo.getCode()));
	}	
		item.setLibelle(vo.getLibelle() );
		item.setTypeEtat(vo.getTypeEtat());

//     if(vo.getDateCreation()!=null)
//		item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));	

	}
	protected void doValider(EtatRentierVO vo) throws ValidationException{
		
	}
}

