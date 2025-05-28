package eai.devass.gsr.appli.valueobjects.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.metier.reglement.OrderesVirements;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de OrderesVirements
 * 
 * @author Nom Prenom (email)
 */
public class OrderesVirementsVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		OrderesVirementsVO vo = (OrderesVirementsVO) ovo;
		this.doValider(vo);
		OrderesVirements item = new OrderesVirements();
		voToItem(vo, item);
		List<OrderesVirements> itemList = new ArrayList<OrderesVirements>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		OrderesVirementsVO vo = new OrderesVirementsVO();
		if (itemList.get(0) instanceof OrderesVirements) {
			itemToVo(vo, (OrderesVirements) itemList.get(0), itemList);
		}

		return vo;
	}

	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof OrderesVirements) {
				OrderesVirementsVO vo = new OrderesVirementsVO();
				itemToVo(vo, (OrderesVirements) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(OrderesVirementsVO vo, OrderesVirements item,
			List itemList) {
		vo.setId(item.getId());

		vo.setAnnee(TypeConverter.getInstance().doubleToString(item.getAnnee()));
		vo.setCommission(TypeConverter.getInstance().doubleToString(
				item.getCommission()));
		vo.setCommissionH(TypeConverter.getInstance().doubleToString(
				item.getCommissionH()));
		vo.setDateEdition(TypeConverter.getInstance().calendarToString(
				item.getDateEdition()));
		vo.setEdit(TypeConverter.getInstance().booleanToString(item.getEdit()));
		vo.setMontant(TypeConverter.getInstance().doubleToString(
				item.getMontant()));
		vo.setNombre(TypeConverter.getInstance().doubleToString(
				item.getNombre()));
		vo.setNomFichier(item.getNomFichier());
		vo.setNumeroVirement(item.getNumeroVirement());
		vo.setTrimestre(item.getTrimestre());
		vo.setTypeRente(item.getTypeRente());
		vo.setTypeVirement(item.getTypeVirement());
		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
				item.getDateCreation()));

		/*
		 * if (itemList == null) return;
		 * 
		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
		 */
	}

	public void voToItem(OrderesVirementsVO vo, OrderesVirements item) {
		item.setId(vo.getId());
		if (vo.getAnnee() != null) {
			item.setAnnee(TypeConverter.getInstance().stringToDouble(
					vo.getAnnee()));
		}
		if (vo.getCommission() != null) {
			item.setCommission(TypeConverter.getInstance().stringToDouble(
					vo.getCommission()));
		}
		if (vo.getCommissionH() != null) {
			item.setCommissionH(TypeConverter.getInstance().stringToDouble(
					vo.getCommissionH()));
		}
		if (vo.getDateEdition() != null) {
			item.setDateEdition(TypeConverter.getInstance().stringToCalendar(
					vo.getDateEdition()));
		}
		if (vo.getEdit() != null) {
			item.setEdit(TypeConverter.getInstance().stringToBoolean(
					vo.getEdit()));
		}
		if (vo.getMontant() != null) {
			item.setMontant(TypeConverter.getInstance().stringToDouble(
					vo.getMontant()));
		}
		if (vo.getNombre() != null) {
			item.setNombre(TypeConverter.getInstance().stringToDouble(
					vo.getNombre()));
		}
		item.setNomFichier(vo.getNomFichier());
		item.setNumeroVirement(vo.getNumeroVirement());
		item.setTrimestre(vo.getTrimestre());
		item.setTypeRente(vo.getTypeRente());
		item.setTypeVirement(vo.getTypeVirement());
		if (vo.getDateCreation() != null) {
			item.setDateCreation(TypeConverter.getInstance().stringToCalendar(
					vo.getDateCreation()));
		}

	}

	protected void doValider(OrderesVirementsVO vo) throws ValidationException {

	}
}
