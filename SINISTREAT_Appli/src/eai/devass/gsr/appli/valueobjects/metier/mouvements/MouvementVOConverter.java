package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;

/**
 * Value Object Converter de Mouvement
 * 
 * @author Nom Prenom (email)
 */
public class MouvementVOConverter extends CommunMouvementVOConverter {

//	public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo)
//			throws ValidationException {
//		MouvementVO vo = (MouvementVO) ovo;
//		this.doValider(vo);
//		Mouvement item = new Mouvement();
//		voToItem(vo, item);
//		List<Mouvement> itemList = new ArrayList<Mouvement>();
//		itemList.add(item);
//		return itemList;
//	}
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		MouvementVO vo = new MouvementVO();
//		if (itemList.get(0) instanceof Mouvement) {
//			itemToVo(vo, (Mouvement) itemList.get(0), itemList);
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
//			if (itemList.get(i) instanceof Mouvement) {
//				MouvementVO vo = new MouvementVO();
//				itemToVo(vo, (Mouvement) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//
//	public void itemToVo(MouvementVO vo, Mouvement item, List itemList) {
//
//		vo.setId(item.getId());
//		vo.setTypeFille(item.getClass().getSimpleName());
////		if (item instanceof MvtDefautRemise)
////			vo.setTypeFille("MvtDefautRemise");
////		if (item instanceof MvtRcptCertif)
////			vo.setTypeFille("MvtRcptCertif");
////		if (item instanceof MvtConsignCNRA)
////			vo.setTypeFille("MvtConsignCNRA");
////		if (item instanceof MvtRachat)
////			vo.setTypeFille("MvtRachat");
////		if (item instanceof MvtDecesRentier)
////			vo.setTypeFille("MvtDecesRentier");
////		if (item instanceof MvtRemariage)
////			vo.setTypeFille("MvtRemariage");
////		if (item instanceof MvtRenteEchue)
////			vo.setTypeFille("MvtRenteEchue");
////		if (item instanceof MvtRemiseEnCours)
////			vo.setTypeFille("MvtRemiseEnCours");
////		if (item instanceof MvtRecuperation)
////			vo.setTypeFille("MvtRecupeartion");
////		if (item instanceof MvtSuspension)
////			vo.setTypeFille("MvtSuspension");
////		if (item instanceof MvtSuppression)
////			vo.setTypeFille("MvtSuppression");
////		if (item instanceof MvtMajCapital)
////			vo.setTypeFille("MvtMajCapital");
////		if (item instanceof MvtProthese)
////			vo.setTypeFille("MvtProthese");
////		if (item instanceof MvtEnInstance)
////			vo.setTypeFille("MvtEnInstance");
//
//		vo.setCapitalCalcule(TypeConverter.getInstance().doubleToString(
//				item.getCapitalCalcule()));
//		vo.setRefRecours(item.getRefRecours());
//		vo.setDatEtat(TypeConverter.getInstance().calendarToString(
//				item.getDatEtat()));
//		vo.setMntRente(TypeConverter.getInstance().doubleToString(
//				item.getMntRente()));
//		vo.setNumOrder(TypeConverter.getInstance().integerToString(
//				item.getNumOrder()));
//		vo.setRefJudiciaire(item.getRefJudiciaire());
//		vo.setEcheanceEffective(TypeConverter.getInstance().calendarToString(
//				"yyyyMMdd", item.getEcheanceEffective()));
//		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
//				item.getDateCreation()));
//
//		if (item.getRefRentier() != null) {
//			vo.setRefRentier(TypeConverter.getInstance().longToString(
//					((IEntite) item.getRefRentier()).getId()));
//			vo.setRefRentierLabel(item.getRefRentier().getNom() + " "
//					+ item.getRefRentier().getPrenom());
//		}
//		if (item.getRefEtatMvt() != null) {
//			vo.setRefEtatMvt(TypeConverter.getInstance().longToString(
//					((IEntite) item.getRefEtatMvt()).getId()));
//			vo.setRefEtatMvtLabel(item.getRefEtatMvt().toString());
//		}
//		if (item.getRefTypeMouvement() != null) {
//			vo.setRefTypeMouvement(TypeConverter.getInstance().longToString(
//					((IEntite) item.getRefTypeMouvement()).getId()));
//			vo.setRefTypeMouvementLabel(item.getRefTypeMouvement().toString());
//		}
//
//		if ((item.getRefsQuittance() != null)
//				&& (item.getRefsQuittance().size() > 0)) {
//			List refsQuittance = new ArrayList();
//			Iterator iter = item.getRefsQuittance().iterator();
//			while (iter.hasNext()) {
//				QuittanceGsr quittance = (QuittanceGsr) iter.next();
//				QuittanceGsrVOConverter quittanceVOConverter = new QuittanceGsrVOConverter();
//				QuittanceGsrVO quittanceVO = (QuittanceGsrVO) quittanceVOConverter
//						.itemToVo(quittance);
//				refsQuittance.add(quittanceVO);
//			}
//			vo.setRefsQuittance(refsQuittance);
//		}
//
//		/*
//		 * if (itemList == null) return;
//		 * 
//		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
//		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { if
//		 * (((List)itemList.get(i)).get(0) instanceof Quittance) {
//		 * QuittanceVOConverter quittanceVOConverter = new QuittanceVOConverter
//		 * ();
//		 * vo.setRefsQuittance(quittanceVOConverter.convertItemsToVos((List)itemList
//		 * .get(i))); } } }
//		 */
//	}
//
//	public void voToItem(MouvementVO vo, Mouvement item) throws ValidationException {
//		item.setId(vo.getId());
//
//		if (StringUtils.isNotEmpty(vo.getCapitalCalcule()))
//			// item.setCapitalCalcule(TypeConverter.getInstance().stringToDouble(
//			// vo.getCapitalCalcule()));
//		item.setCapitalCalcule(TypeConverter.getInstance()
//				.stringToDouble(
//						StringUtils.replace(StringUtils.replace(
//								vo.getCapitalCalcule(), " ", ""), ",",
//								".")));
//		item.setRefRecours(vo.getRefRecours());
//		if (StringUtils.isNotEmpty(vo.getDatEtat()))
//			item.setDatEtat(TypeConverter.getInstance().stringToCalendar(
//					"yyyyMMdd", vo.getDatEtat()));
//		if (StringUtils.isNotEmpty(vo.getMntRente()))
//			// item.setMntRente(TypeConverter.getInstance().stringToDouble(
//			// vo.getMntRente()));
//		item.setMntRente(TypeConverter.getInstance()
//				.stringToDouble(
//						StringUtils.replace(StringUtils.replace(
//								vo.getMntRente(), " ", ""), ",",
//								".")));
//		if (StringUtils.isNotEmpty(vo.getNumOrder()))
//			item.setNumOrder(TypeConverter.getInstance().stringToInteger(
//					vo.getNumOrder()));
//		item.setRefJudiciaire(vo.getRefJudiciaire());
//		if (StringUtils.isNotEmpty(vo.getEcheanceEffective()))
//			item.setEcheanceEffective(TypeConverter.getInstance()
//					.stringToCalendar("yyyyMMdd", vo.getEcheanceEffective()));
//		if (StringUtils.isNotEmpty(vo.getDateCreation()))
//			item.setDateCreation(TypeConverter.getInstance().stringToCalendar(
//					"yyyyMMdd", vo.getDateCreation()));
//
//		if (StringUtils.isNotEmpty(vo.getRefRentier())) {
//			Rentier refRentier = new Rentier();
//
//			((IEntite) refRentier).setId(TypeConverter.getInstance()
//					.stringToLong(vo.getRefRentier()));
//			item.setRefRentier(refRentier);
//		}
//		if (StringUtils.isNotEmpty(vo.getRefEtatMvt())) {
//			EtatMvt refEtatMvt = new EtatMvt();
//
//			((IEntite) refEtatMvt).setId(TypeConverter.getInstance()
//					.stringToLong(vo.getRefEtatMvt()));
//			item.setRefEtatMvt(refEtatMvt);
//		}
//		if (StringUtils.isNotEmpty(vo.getRefTypeMouvement())) {
//			TypeMouvement refTypeMouvement = new TypeMouvement();
//
//			((IEntite) refTypeMouvement).setId(TypeConverter.getInstance()
//					.stringToLong(vo.getRefTypeMouvement()));
//			item.setRefTypeMouvement(refTypeMouvement);
//		}
//
//		// ============ Ajout pere et fils ====================
//
//		if ((vo.getRefsQuittance() != null)
//				&& (vo.getRefsQuittance().size() > 0)
//						&& !(vo instanceof MvtRcptCertifVO) && !(vo instanceof MvtEnInstanceVO)&& !(vo instanceof MvtDecesRentierVO)) {
//			// if (!vo.getRefTypeMouvement().equals("9") &&
//			// !vo.getRefTypeMouvement().equals("10") &&
//			// !vo.getRefTypeMouvement().equals("17")){
//			List<QuittanceGsr> refsQuittanceItems = new ArrayList<QuittanceGsr>();
//			QuittanceGsrVOConverter quittanceVOConverter = new QuittanceGsrVOConverter();
//			Iterator<QuittanceGsrVO> iter = vo.getRefsQuittance().iterator();
//			while (iter.hasNext()) {
//				QuittanceGsrVO quittanceVO = iter.next();
//				QuittanceGsr quittance = (QuittanceGsr) quittanceVOConverter.voToItem(quittanceVO);
//				refsQuittanceItems.add(quittance);
//			}
//			item.setRefsQuittance(refsQuittanceItems);
//		}
//		// ====================================================
//	}

	protected void doValider(IValueObject vo) throws ValidationException {

	}
}
