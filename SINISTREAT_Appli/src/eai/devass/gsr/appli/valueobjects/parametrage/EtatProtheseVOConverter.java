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
import eai.devass.gsr.appli.modele.parametrage.EtatProthese;

/**
 * @author elfaismo
 *
 */
public class EtatProtheseVOConverter implements IValueObjectConverter{
	   
	   public IValidator getValidator() {
			return null;
		}

		public List convertValueObjectToItems(Object ovo) throws ValidationException {
			EtatProtheseVO vo = (EtatProtheseVO) ovo;	
			this.doValider(vo);		
			EtatProthese item = new EtatProthese();	
			voToItem(vo,item);
			List<EtatProthese> itemList = new ArrayList<EtatProthese>();
			itemList.add(item);
			return itemList;
		}


		public Object convertItemsToValueObject(List itemList) {
			if ( (itemList == null) || (itemList.size()==0) ){
				return null;
			}
			EtatProtheseVO vo = new EtatProtheseVO();
			if (itemList.get(0) instanceof EtatProthese){
				itemToVo(vo, (EtatProthese)itemList.get(0), itemList);
			}
			
			return vo;
		}
		


		
		public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
			if ((itemList == null) || (itemList.size() == 0)) {
				return null;
			}
			ArrayList<IValueObject> list = new ArrayList<IValueObject>();
			for(int i=0;i<itemList.size();i++){
				if (itemList.get(i) instanceof EtatProthese) {
					EtatProtheseVO vo = new EtatProtheseVO();
					itemToVo(vo, (EtatProthese) itemList.get(i), null);
					list.add(vo);
				}
			}
			return list;
		}
		
		public void itemToVo(EtatProtheseVO vo, EtatProthese item, List itemList) {
				vo.setId(item.getId() );
			    vo.setLibelle(item.getLibelle() );		    
//	   		    vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));	

	}

		public void voToItem(EtatProtheseVO vo, EtatProthese item) {
			item.setId(vo.getId());
	     if(vo.getId()!= 0) {
			item.setLibelle(vo.getLibelle() );
		}

		}
		protected void doValider(EtatProtheseVO vo) throws ValidationException{
			
		}
	}
