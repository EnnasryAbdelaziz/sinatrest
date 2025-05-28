/**
 * 
 */
package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.OrigineRachat;

/**
 * @author elfaismo
 *
 */
public class OrigineRachatVOConverter implements IValueObjectConverter{
	   
	   public IValidator getValidator() {
			return null;
		}

		public List convertValueObjectToItems(Object ovo) throws ValidationException {
			OrigineRachatVO vo = (OrigineRachatVO) ovo;	
			this.doValider(vo);		
			OrigineRachat item = new OrigineRachat();	
			voToItem(vo,item);
			List<OrigineRachat> itemList = new ArrayList<OrigineRachat>();
			itemList.add(item);
			return itemList;
		}


		public Object convertItemsToValueObject(List itemList) {
			if ( (itemList == null) || (itemList.size()==0) ){
				return null;
			}
			OrigineRachatVO vo = new OrigineRachatVO();
			if (itemList.get(0) instanceof OrigineRachat){
				itemToVo(vo, (OrigineRachat)itemList.get(0), itemList);
			}
			
			return vo;
		}
		


		
		public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
			if ((itemList == null) || (itemList.size() == 0)) {
				return null;
			}
			ArrayList<IValueObject> list = new ArrayList<IValueObject>();
			for(int i=0;i<itemList.size();i++){
				if (itemList.get(i) instanceof OrigineRachat) {
					OrigineRachatVO vo = new OrigineRachatVO();
					itemToVo(vo, (OrigineRachat) itemList.get(i), null);
					list.add(vo);
				}
			}
			return list;
		}
		
		public void itemToVo(OrigineRachatVO vo, OrigineRachat item, List itemList) {
				vo.setId(item.getId() );
			    vo.setLibelle(item.getLibelle() );		    
//	   		    vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));	

	}

		public void voToItem(OrigineRachatVO vo, OrigineRachat item) {
			item.setId(vo.getId());
	     if(vo.getId()!= 0) {
			item.setLibelle(vo.getLibelle() );
		}

		}
		protected void doValider(OrigineRachatVO vo) throws ValidationException{
			
		}
	}
