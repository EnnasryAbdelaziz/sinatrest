package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.commun.appli.converter.ConvertorTools;
import eai.devass.commun.appli.modele.EntiteBO;



/**
 * Value Object Converter de CommunMouvementVOConverter
 * 
 * @author Nom Prenom (email)
 */
public abstract class CommunMouvementVOConverter implements IValueObjectConverter {
	
	private ConvertorTools convertorTools = ConvertorTools.getInstance();
	
	
	protected abstract void doValider(IValueObject vo) throws ValidationException;
	
	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo) throws ValidationException {
		
		IValueObject vo = (IValueObject) ovo;
		if(ovo instanceof MouvementVO) {
			if(((MouvementVO) ovo).getContextRegleGestion() != null) {
				return null;
			}
		}
			
		doValider(vo);
		EntiteBO item = voToItem(vo);								
		List<EntiteBO> itemList = new ArrayList<EntiteBO>();
		itemList.add(item);
		return itemList;
	}
	
	public EntiteBO voToItem(IValueObject vo) throws ValidationException {
		try {
			return (EntiteBO) convertorTools.convertToObject(vo);
			
		} catch(Exception e) {
			throw new ValidationException("Impossible de convertir l'entité : "
					+ vo.getClass().getSimpleName());
		}
	}
	
	

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		
		
		Object vo = itemToVo(itemList.get(0));

		return vo;
	}
	
	public Object itemToVo(Object item) {
		
		try {
			
			if (item.getClass().equals(String.class)
					|| convertorTools.isPrimitive(item)) {
				return item;
			}
			return (IValueObject) convertorTools
					.inverseConvertToObject(item);
			
		} catch(Exception e) {
			throw new RuntimeException("Impossible de convertir l'entité : "
					+ item.getClass().getSimpleName());
		}
		
	}

	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		IValueObject vo = null;
		for (IEntite curEntite : itemList) {
			
			try {
				vo = (IValueObject) convertorTools
						.inverseConvertToObject(curEntite);			
				
			} catch(Exception e) {
				throw new RuntimeException("Impossible de convertir l'entité : "
						+ curEntite.getClass().getSimpleName());
			}
				list.add(vo);
			
		}
		return list;
	}


}
