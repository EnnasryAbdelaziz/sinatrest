package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.validation.IValidator;

/**
 * Value Object Converter de Heritier
 * 
 * @author Nom Prenom (email)
 */
public class HeritierVOConverter extends CommunMouvementVOConverter {

	public IValidator getValidator() {
		return null;
	}

//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		HeritierVO vo = (HeritierVO) ovo;
//		this.doValider(vo);
//		Heritier item = new Heritier();
//		voToItem(vo, item);
//		List<Heritier> itemList = new ArrayList<Heritier>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		HeritierVO vo = new HeritierVO();
//		if (itemList.get(0) instanceof Heritier) {
//			itemToVo(vo, (Heritier) itemList.get(0), itemList);
//		}
//
//		return vo;
//	}
//
//	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
//		for (int i = 0; i < itemList.size(); i++) {
//			if (itemList.get(i) instanceof Heritier) {
//				HeritierVO vo = new HeritierVO();
//				itemToVo(vo, (Heritier) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(HeritierVO vo, Heritier item, List itemList) {
//		vo.setId(item.getId());
//
//		vo.setNomBeneficiaire(item.getNomBeneficiaire());
//		vo.setPrenomBeneficiaire(item.getPrenomBeneficiaire());
//		vo.setNumCIN(item.getNumCIN());
//		vo.setQuotePart(TypeConverter.getInstance().doubleToString(
//				item.getQuotePart()));
//		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
//				item.getDateCreation()));
//
//		// if (item.getRefMvtDecesRentier() instanceof MvtDecesRentier)
//		vo.setTypeParentB("MvtDecesRentier");
//
//		if (item.getRefMvtDecesRentier() != null) {
//			vo.setRefMvtDecesRentier(TypeConverter.getInstance().longToString(
//					item.getRefMvtDecesRentier().getId()));
//			vo.setRefMvtDecesRentierLabel(item.getRefMvtDecesRentier()
//					.toString());
//		}
//
//		/*
//		 * if (itemList == null) return;
//		 * 
//		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
//		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
//		 */
//	}
//
//	public void voToItem(HeritierVO vo, Heritier item) {
//		item.setId(vo.getId());
//		item.setNomBeneficiaire(vo.getNomBeneficiaire());
//		item.setPrenomBeneficiaire(vo.getPrenomBeneficiaire());
//		item.setNumCIN(vo.getNumCIN());
//		if (vo.getQuotePart() != null) {
//
//			item.setQuotePart(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getQuotePart(), " ", ""),
//							",", ".")));
//		}
//		if (vo.getDateCreation() != null) {
//			item.setDateCreation(TypeConverter.getInstance().stringToCalendar(
//					vo.getDateCreation()));
//		}
//
//		if ((vo.getRefMvtDecesRentierObj() != null)
//				&& ("MvtDecesRentier".equals(vo.getTypeParentB()))) {
//			item.setRefMvtDecesRentier((MvtDecesRentier) vo
//					.getRefMvtDecesRentierObj());
//		} else if (StringUtils.isNotEmpty(vo.getRefMvtDecesRentier())
//				&& "MvtDecesRentier".equals(vo.getTypeParentB())) {
//			MvtDecesRentier refMvtDecesRentier = new MvtDecesRentier();
//			((IEntite) refMvtDecesRentier).setId(TypeConverter.getInstance()
//					.stringToLong(vo.getRefMvtDecesRentier()));
//			item.setRefMvtDecesRentier(refMvtDecesRentier);
//		}
//
//	}
//
//	protected void doValider(HeritierVO vo) throws ValidationException {
//
//	}
	protected void doValider(IValueObject vo) throws ValidationException {

	}
}
