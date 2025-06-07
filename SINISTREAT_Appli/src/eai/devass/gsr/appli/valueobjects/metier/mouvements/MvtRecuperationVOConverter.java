package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;

/**
 * Value Object Converter de MvtRecupeartion
 * 
 * @author Nom Prenom (email)
 */
public class MvtRecuperationVOConverter extends CommunMouvementVOConverter {

//	public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MvtRecuperationVO vo = (MvtRecuperationVO) ovo;
//		this.doValider(vo);
//		MvtRecuperation item = new MvtRecuperation();
//		voToItem(vo, item);
//		List<MvtRecuperation> itemList = new ArrayList<MvtRecuperation>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MvtRecuperationVO vo = new MvtRecuperationVO();
//		if (itemList.get(0) instanceof MvtRecuperation) {
//			itemToVo(vo, (MvtRecuperation) itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof MvtRecuperation) {
//				MvtRecuperationVO vo = new MvtRecuperationVO();
//				itemToVo(vo, (MvtRecuperation) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(MvtRecuperationVO vo, MvtRecuperation item,
//			List itemList) {
//
//		vo.setPourcentage(TypeConverter.getInstance().doubleToString(
//				item.getPourcentage()));
//		vo.setDatDebut(TypeConverter.getInstance().calendarToString(
//				item.getDatDebut()));
//		vo.setDatFin(TypeConverter.getInstance().calendarToString(
//				item.getDatFin()));
//		vo.setDatRemiseBancaire(TypeConverter.getInstance().calendarToString(
//				item.getDatRemiseBancaire()));
//		vo.setMntPreleveRente(TypeConverter.getInstance().doubleToString(
//				item.getMntPreleveRente()));
//		vo.setMntProrataRecuperation(TypeConverter.getInstance()
//				.doubleToString(item.getMntProrataRecuperation()));
//		vo.setMntRecupere(TypeConverter.getInstance().doubleToString(
//				item.getMntRecupere()));
//		vo.setMntReliquat(TypeConverter.getInstance().doubleToString(
//				item.getMntReliquat()));
//		vo.setMntTropPercu(TypeConverter.getInstance().doubleToString(
//				item.getMntTropPercu()));
//		vo.setNouvMntRente(TypeConverter.getInstance().doubleToString(
//				item.getNouvMntRente()));
//		vo.setNumRemiseBancaire(TypeConverter.getInstance().integerToString(
//				item.getNumRemiseBancaire()));
//		// vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));
//
//		if (item.getRefTypeAction() != null) {
//			vo.setRefTypeAction(TypeConverter.getInstance().longToString(
//					((IEntite) item.getRefTypeAction()).getId()));
//			vo.setRefTypeActionLabel(item.getRefTypeAction().toString());
//		}
//		if (item.getRefTypeRecuperation() != null) {
//			vo.setRefTypeRecuperation(TypeConverter.getInstance().longToString(
//					((IEntite) item.getRefTypeRecuperation()).getId()));
//			vo.setRefTypeRecuperationLabel(item.getRefTypeRecuperation()
//					.toString());
//		}
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
//	public void voToItem(MvtRecuperationVO vo, MvtRecuperation item) {
//		if (StringUtils.isNotEmpty(vo.getPourcentage()))
//			// item.setPourcentage(TypeConverter.getInstance().stringToDouble(vo.getPourcentage()));
//			item.setPourcentage(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getPourcentage(), " ", ""),
//							",", ".")));
//		if (StringUtils.isNotEmpty(vo.getDatDebut()))
//			item.setDatDebut(TypeConverter.getInstance().stringToCalendar(
//					"yyyyMMdd", vo.getDatDebut()));
//		if (StringUtils.isNotEmpty(vo.getDatFin()))
//			item.setDatFin(TypeConverter.getInstance().stringToCalendar(
//					"yyyyMMdd", vo.getDatFin()));
//		if (StringUtils.isNotEmpty(vo.getDatRemiseBancaire()))
//			item.setDatRemiseBancaire(TypeConverter.getInstance()
//					.stringToCalendar("yyyyMMdd", vo.getDatRemiseBancaire()));
//		if (StringUtils.isNotEmpty(vo.getMntPreleveRente()))
//			// item.setMntPreleveRente(TypeConverter.getInstance().stringToDouble(vo.getMntPreleveRente()));
//			item.setMntPreleveRente(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(StringUtils.replace(
//							vo.getMntPreleveRente(), " ", ""), ",", ".")));
//		if (StringUtils.isNotEmpty(vo.getMntProrataRecuperation()))
//			// item.setMntProrataRecuperation(TypeConverter.getInstance().stringToDouble(vo.getMntProrataRecuperation()));
//			item.setMntProrataRecuperation(TypeConverter.getInstance()
//					.stringToDouble(
//							StringUtils.replace(StringUtils.replace(
//									vo.getMntProrataRecuperation(), " ", ""),
//									",", ".")));
//		if (StringUtils.isNotEmpty(vo.getMntRecupere()))
//			// item.setMntRecupere(TypeConverter.getInstance().stringToDouble(vo.getMntRecupere()));
//			item.setMntRecupere(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getMntRecupere(), " ", ""),
//							",", ".")));
//		if (StringUtils.isNotEmpty(vo.getMntReliquat()))
//			// item.setMntReliquat(TypeConverter.getInstance().stringToDouble(vo.getMntReliquat()));
//			item.setMntReliquat(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getMntReliquat(), " ", ""),
//							",", ".")));
//		if (StringUtils.isNotEmpty(vo.getMntTropPercu()))
//			// item.setMntTropPercu(TypeConverter.getInstance().stringToDouble(vo.getMntTropPercu()));
//			item.setMntTropPercu(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getMntTropPercu(), " ", ""),
//							",", ".")));
//		if (StringUtils.isNotEmpty(vo.getNouvMntRente()))
//			// item.setNouvMntRente(TypeConverter.getInstance().stringToDouble(vo.getNouvMntRente()));
//			item.setNouvMntRente(TypeConverter.getInstance().stringToDouble(
//					StringUtils.replace(
//							StringUtils.replace(vo.getNouvMntRente(), " ", ""),
//							",", ".")));
//		if (StringUtils.isNotEmpty(vo.getNumRemiseBancaire()))
//			item.setNumRemiseBancaire(TypeConverter.getInstance()
//					.stringToInteger(vo.getNumRemiseBancaire()));
//		// if(vo.getDateCreation()!=null)
//		// item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));
//
//		if (StringUtils.isNotEmpty(vo.getRefTypeAction())) {
//			TypeAction refTypeAction = new TypeAction();
//
//			((IEntite) refTypeAction).setId(TypeConverter.getInstance()
//					.stringToLong(vo.getRefTypeAction()));
//			item.setRefTypeAction(refTypeAction);
//		}
//		if (StringUtils.isNotEmpty(vo.getRefTypeRecuperation())) {
//			TypeRecuperation refTypeRecuperation = new TypeRecuperation();
//
//			((IEntite) refTypeRecuperation).setId(TypeConverter.getInstance()
//					.stringToLong(vo.getRefTypeRecuperation()));
//			item.setRefTypeRecuperation(refTypeRecuperation);
//		}
//
//		(new MouvementVOConverter()).voToItem(vo, item);
//	}

	protected void doValider(IValueObject vo) throws ValidationException {

	}
}
