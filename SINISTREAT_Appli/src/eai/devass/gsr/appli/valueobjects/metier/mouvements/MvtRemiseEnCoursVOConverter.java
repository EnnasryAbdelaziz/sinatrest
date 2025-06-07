package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;

/**
 * Value Object Converter de MvtRemiseEnCours
 * 
 * @author Nom Prenom (email)
 */
public class MvtRemiseEnCoursVOConverter extends CommunMouvementVOConverter {

//	public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MvtRemiseEnCoursVO vo = (MvtRemiseEnCoursVO) ovo;
//		this.doValider(vo);
//		MvtRemiseEnCours item = new MvtRemiseEnCours();
//		voToItem(vo, item);
//		List<MvtRemiseEnCours> itemList = new ArrayList<MvtRemiseEnCours>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MvtRemiseEnCoursVO vo = new MvtRemiseEnCoursVO();
//		if (itemList.get(0) instanceof MvtRemiseEnCours) {
//			itemToVo(vo, (MvtRemiseEnCours) itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MvtRemiseEnCours) {
//				MvtRemiseEnCoursVO vo = new MvtRemiseEnCoursVO();
//				itemToVo(vo, (MvtRemiseEnCours) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(MvtRemiseEnCoursVO vo, MvtRemiseEnCours item,
//			List itemList) {
//
//		vo.setMotif(item.getMotif());
//		vo.setDatMiseEnVigeur(TypeConverter.getInstance().calendarToString(
//				"yyyyMMdd", item.getDatMiseEnVigeur()));
//		vo.setNouvMntRente(TypeConverter.getInstance().doubleToString(
//				item.getNouvMntRente()));
//		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
//				"yyyyMMdd", item.getDateCreation()));
//
//		(new MouvementVOConverter()).itemToVo(vo, item, itemList);
//		/*
//		 * if (itemList == null) return;
//		 * 
//		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
//		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
//		 */
//	}
//
//	public void voToItem(MvtRemiseEnCoursVO vo, MvtRemiseEnCours item) {
//		item.setMotif(vo.getMotif());
//		if (vo.getDatMiseEnVigeur() != null)
//			item.setDatMiseEnVigeur(TypeConverter.getInstance()
//					.stringToCalendar("yyyyMMdd", vo.getDatMiseEnVigeur()));
//		if (vo.getNouvMntRente() != null)
//			// item.setNouvMntRente(TypeConverter.getInstance().stringToDouble(vo.getNouvMntRente()));
//			item.setNouvMntRente(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getNouvMntRente(), " ", ""),
//							",", ".")));
//		if (vo.getDateCreation() != null)
//			item.setDateCreation(TypeConverter.getInstance().stringToCalendar(
//					"yyyyMMdd", vo.getDateCreation()));
//
//		(new MouvementVOConverter()).voToItem(vo, item);
//	}

	protected void doValider(IValueObject vo) throws ValidationException {

	}
}
