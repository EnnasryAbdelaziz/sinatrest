package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.parametrage.NatureDecision;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de NatureDecision
 * 
 * @author Nom Prenom (email)
 */
public class NatureDecisionVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		NatureDecisionVO vo = (NatureDecisionVO) ovo;
		this.doValider(vo);
		NatureDecision item = new NatureDecision();
		voToItem(vo, item);
		List<NatureDecision> itemList = new ArrayList<NatureDecision>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		NatureDecisionVO vo = new NatureDecisionVO();
		if (itemList.get(0) instanceof NatureDecision) {
			itemToVo(vo, (NatureDecision) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof NatureDecision) {
				NatureDecisionVO vo = new NatureDecisionVO();
				itemToVo(vo, (NatureDecision) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(NatureDecisionVO vo, NatureDecision item, List itemList) {
		vo.setId(item.getId());

//		vo.setCode(TypeConverter.getInstance().doubleToString(item.getCode()));
		vo.setLibelle(item.getLibelle());
		vo.setId(item.getId());
//		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
//				item.getDateCreation()));

		/*
		 * if (itemList == null) return;
		 * 
		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
		 */
	}

	public void voToItem(NatureDecisionVO vo, NatureDecision item) {
		if (vo != null) {
			item.setId(vo.getId());			
			item.setLibelle(vo.getLibelle());
			
		}
	}

	protected void doValider(NatureDecisionVO vo) throws ValidationException {

	}
}
