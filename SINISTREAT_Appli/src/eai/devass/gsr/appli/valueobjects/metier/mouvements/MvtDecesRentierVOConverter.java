package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;

/**
 * Value Object Converter de MvtDecesRentier
 * 
 * @author Nom Prenom (email)
 */
public class MvtDecesRentierVOConverter extends CommunMouvementVOConverter {

//	public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MvtDecesRentierVO vo = (MvtDecesRentierVO) ovo;
//		this.doValider(vo);
//		MvtDecesRentier item = new MvtDecesRentier();
//		voToItem(vo, item);
//		List<MvtDecesRentier> itemList = new ArrayList<MvtDecesRentier>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MvtDecesRentierVO vo = new MvtDecesRentierVO();
//		if (itemList.get(0) instanceof MvtDecesRentier) {
//			itemToVo(vo, (MvtDecesRentier) itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MvtDecesRentier) {
//				MvtDecesRentierVO vo = new MvtDecesRentierVO();
//				itemToVo(vo, (MvtDecesRentier) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(MvtDecesRentierVO vo, MvtDecesRentier item,
//			List itemList) {
//
//		vo.setDateDeces(TypeConverter.getInstance().calendarToString(
//				item.getDateDeces()));
//		vo.setMntProrata(TypeConverter.getInstance().doubleToString(
//				item.getMntProrata()));
//		
//		vo.setArreragesRente(TypeConverter.getInstance().doubleToString(
//				item.getArreragesRente()));
//		
//		vo.setMntGlobalVersee(TypeConverter.getInstance().doubleToString(
//				item.getMntGlobalVersee()));
//		
//		vo.setDateReceptionCertifDeces(TypeConverter.getInstance().calendarToString(
//				item.getDateReceptionCertifDeces()));
//
//		if ((item.getRefsHeritier() != null)
//				&& (item.getRefsHeritier().size() > 0)) {
//			List refsHeritier = new ArrayList();
//			Iterator iter = item.getRefsHeritier().iterator();
//			while (iter.hasNext()) {
//				Heritier heritier = (Heritier) iter.next();
//				HeritierVOConverter heritierVOConverter = new HeritierVOConverter();
//				HeritierVO heritierVO = new HeritierVO();
//				heritierVOConverter.itemToVo(heritierVO, heritier, null);
//				refsHeritier.add(heritierVO);
//			}
//			vo.setRefsHeritier(refsHeritier);
//		}
//
//		(new MouvementVOConverter()).itemToVo(vo, item, itemList);
//		/*
//		 * if (itemList == null) return;
//		 * 
//		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
//		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { if
//		 * (((List)itemList.get(i)).get(0) instanceof Heritier) {
//		 * HeritierVOConverter heritierVOConverter = new HeritierVOConverter ();
//		 * vo
//		 * .setRefsHeritier(heritierVOConverter.convertItemsToVos((List)itemList
//		 * .get(i))); } } }
//		 */
//	}
//
//	public void voToItem(MvtDecesRentierVO vo, MvtDecesRentier item) throws ValidationException {
//		if (vo.getDateDeces() != null)
//			item.setDateDeces(TypeConverter.getInstance().stringToCalendar(
//					"yyyyMMdd", vo.getDateDeces()));
//		if (vo.getDateReceptionCertifDeces() != null)
//			item.setDateReceptionCertifDeces(TypeConverter.getInstance().stringToCalendar(
//					"yyyyMMdd", vo.getDateReceptionCertifDeces()));
//		if (vo.getMntProrata() != null)
//			// item.setMntProrata(TypeConverter.getInstance().stringToDouble(vo.getMntProrata()));
//			item.setMntProrata(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getMntProrata(), " ", ""),
//							",", ".")));		
//
//		
//		
//		item.setMntGlobalVersee(TypeConverter.getInstance().stringToDouble(
//				StringUtils.replace(
//						StringUtils.replace(vo.getMntGlobalVersee(), " ", ""),
//						",", ".")));		
//		
//		item.setArreragesRente(TypeConverter.getInstance().stringToDouble(
//				StringUtils.replace(
//						StringUtils.replace(vo.getArreragesRente(), " ", ""),
//						",", ".")));		
//		
//		item.setTropPercu(TypeConverter.getInstance().stringToDouble(
//				StringUtils.replace(
//						StringUtils.replace(vo.getTropPercu(), " ", ""),
//						",", ".")));		
//		
//		
//		(new MouvementVOConverter()).voToItem(vo, item);
//
//		// ============ Ajout pere et fils ====================
//		if (((vo.getRefsHeritier() != null))
//				&& (vo.getRefsHeritier().size() > 0)) {
//			List refsHeritierItems = new ArrayList();
//			HeritierVOConverter heritierVOConverter = new HeritierVOConverter();
//			Iterator iter = vo.getRefsHeritier().iterator();
//			while (iter.hasNext()) {
//				HeritierVO heritierVO = (HeritierVO) iter.next();
//				Heritier heritier = new Heritier();
//				heritierVOConverter.voToItem(heritierVO, heritier);
//				heritier.setRefMvtDecesRentier(item);
//				refsHeritierItems.add(heritier);
//			}
//			item.setRefsHeritier(refsHeritierItems);
//		}
//		// ====================================================
//	}

	protected void doValider(IValueObject vo) throws ValidationException {

	}
}
