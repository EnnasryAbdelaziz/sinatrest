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
import eai.devass.gsr.appli.modele.parametrage.StatutHeritier;

/**
 * @author elfaismo
 *
 */
public class StatutHeritierVOConverter implements IValueObjectConverter{
	   
	   public IValidator getValidator() {
			return null;
		}

		public List convertValueObjectToItems(Object ovo) throws ValidationException {
			StatutHeritierVO vo = (StatutHeritierVO) ovo;	
			this.doValider(vo);		
			StatutHeritier item = new StatutHeritier();	
			voToItem(vo,item);
			List<StatutHeritier> itemList = new ArrayList<StatutHeritier>();
			itemList.add(item);
			return itemList;
		}


		public Object convertItemsToValueObject(List itemList) {
			if ( (itemList == null) || (itemList.size()==0) ){
				return null;
			}
			StatutHeritierVO vo = new StatutHeritierVO();
			if (itemList.get(0) instanceof StatutHeritier){
				itemToVo(vo, (StatutHeritier)itemList.get(0), itemList);
			}
			
			return vo;
		}
		


		
		public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
			if ((itemList == null) || (itemList.size() == 0)) {
				return null;
			}
			ArrayList<IValueObject> list = new ArrayList<IValueObject>();
			for(int i=0;i<itemList.size();i++){
				if (itemList.get(i) instanceof StatutHeritier) {
					StatutHeritierVO vo = new StatutHeritierVO();
					itemToVo(vo, (StatutHeritier) itemList.get(i), null);
					list.add(vo);
				}
			}
			return list;
		}
		
		public void itemToVo(StatutHeritierVO vo, StatutHeritier item, List itemList) {
				vo.setId(item.getId() );
			    vo.setLibelle(item.getLibelle() );		    
//	   		    vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));	

	}

		public void voToItem(StatutHeritierVO vo, StatutHeritier item) {
			item.setId(vo.getId());
	     if(vo.getId()!= 0) {
			item.setLibelle(vo.getLibelle() );
		}

		}
		protected void doValider(StatutHeritierVO vo) throws ValidationException{
			
		}
	}
