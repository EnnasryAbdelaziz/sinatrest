package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.metier.mouvements.ConsomReserveRente;
import eai.devass.gsr.appli.utile.TypeConverter;






/**
Value Object Converter de ConsomReserveRente
@author Nom Prenom (email)
*/
public class ConsomReserveRenteVOConverter implements IValueObjectConverter{
   
   public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo) throws ValidationException {
		ConsomReserveRenteVO vo = (ConsomReserveRenteVO) ovo;	
		this.doValider(vo);		
		ConsomReserveRente item = new ConsomReserveRente();	
		voToItem(vo,item);
		List<ConsomReserveRente> itemList = new ArrayList<ConsomReserveRente>();
		itemList.add(item);
		return itemList;
	}


	public Object convertItemsToValueObject(List itemList) {
		if ( (itemList == null) || (itemList.size()==0) ){
			return null;
		}
		ConsomReserveRenteVO vo = new ConsomReserveRenteVO();
		if (itemList.get(0) instanceof ConsomReserveRente){
			itemToVo(vo, (ConsomReserveRente)itemList.get(0), itemList);
		}
		
		return vo;
	}
	


	
	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for(int i=0;i<itemList.size();i++){
			if (itemList.get(i) instanceof ConsomReserveRente) {
				ConsomReserveRenteVO vo = new ConsomReserveRenteVO();
				itemToVo(vo, (ConsomReserveRente) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}
	
	public void itemToVo(ConsomReserveRenteVO vo, ConsomReserveRente item, List itemList) {
			vo.setId(item.getId() );


		    vo.setNumeroQuittance(item.getNumeroQuittance() );
		    vo.setReserveApres(TypeConverter.getInstance().doubleToString(item.getReserveApres()));	
		    vo.setReserveAvant(TypeConverter.getInstance().doubleToString(item.getReserveAvant()));	
		    vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));	




/*
	    if (itemList == null) return;

		for (int i=1; i<itemList.size(); i++) {
			if (itemList.get(i) instanceof List)
			   if (((List)itemList.get(i)).size() != 0) {
			   }
		}
*/
}

	public void voToItem(ConsomReserveRenteVO vo, ConsomReserveRente item) {
		item.setId(vo.getId());
		item.setNumeroQuittance(vo.getNumeroQuittance() );
     if(vo.getReserveApres()!=null) {
		item.setReserveApres(TypeConverter.getInstance().stringToDouble(vo.getReserveApres()));
	}	
     if(vo.getReserveAvant()!=null) {
		item.setReserveAvant(TypeConverter.getInstance().stringToDouble(vo.getReserveAvant()));
	}	
     if(vo.getDateCreation()!=null) {
		item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));
	}	




	}
	protected void doValider(ConsomReserveRenteVO vo) throws ValidationException{
		
	}
}

