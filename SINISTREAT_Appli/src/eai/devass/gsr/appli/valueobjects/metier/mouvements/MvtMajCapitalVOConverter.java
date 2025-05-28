package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.validation.IValidator;

/**
 * Value Object Converter de MvtMajCapital
 * 
 * @author Nom Prenom (email)
 */
public class MvtMajCapitalVOConverter extends CommunMouvementVOConverter {

	public IValidator getValidator() {
		return null;
	}

	// public List convertValueObjectToItems(Object ovo)
	// throws ValidationException {
	// MvtMajCapitalVO vo = (MvtMajCapitalVO) ovo;
	// this.doValider(vo);
	// MvtMajCapital item = new MvtMajCapital();
	// voToItem(vo, item);
	// List<MvtMajCapital> itemList = new ArrayList<MvtMajCapital>();
	// itemList.add(item);
	// return itemList;
	// }
	//
	// public Object convertItemsToValueObject(List itemList) {
	// if ((itemList == null) || (itemList.size() == 0)) {
	// return null;
	// }
	// MvtMajCapitalVO vo = new MvtMajCapitalVO();
	// if (itemList.get(0) instanceof MvtMajCapital) {
	// itemToVo(vo, (MvtMajCapital) itemList.get(0), itemList);
	// }
	//
	// return vo;
	// }
	//
	// public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList)
	// {
	// if ((itemList == null) || (itemList.size() == 0)) {
	// return null;
	// }
	// ArrayList<IValueObject> list = new ArrayList<IValueObject>();
	// for (int i = 0; i < itemList.size(); i++) {
	// if (itemList.get(i) instanceof MvtMajCapital) {
	// MvtMajCapitalVO vo = new MvtMajCapitalVO();
	// itemToVo(vo, (MvtMajCapital) itemList.get(i), null);
	// list.add(vo);
	// }
	// }
	// return list;
	// }
	//
	// public void itemToVo(MvtMajCapitalVO vo, MvtMajCapital item, List
	// itemList) {
	//
	// vo.setNouvTaux(TypeConverter.getInstance().doubleToString(
	// item.getNouvTaux()));
	// vo.setDatSuspension(TypeConverter.getInstance().calendarToString(
	// item.getDatSuspension()));
	// vo.setDatRemise(TypeConverter.getInstance().calendarToString(
	// item.getDatRemise()));
	// vo.setArreragesDus(TypeConverter.getInstance().doubleToString(
	// item.getArreragesDus()));
	// vo.setArreragesPercus(TypeConverter.getInstance().doubleToString(
	// item.getArreragesPercus()));
	// vo.setNouvMntRente(TypeConverter.getInstance().doubleToString(
	// item.getNouvMntRente()));
	// vo.setNouvSalaire(TypeConverter.getInstance().doubleToString(
	// item.getNouvSalaire()));
	// vo.setNouvIPP(TypeConverter.getInstance().doubleToString(
	// item.getNouvIPP()));
	// vo.setDatDebutArrerage(TypeConverter.getInstance().calendarToString(
	// item.getDatDebutArrerage()));
	// vo.setDatFinArrerage(TypeConverter.getInstance().calendarToString(
	// item.getDatFinArrerage()));
	// vo.setMntProrata(TypeConverter.getInstance().doubleToString(
	// item.getMntProrata()));
	// vo.setMntDiff(TypeConverter.getInstance().doubleToString(
	// item.getMntDiff()));
	// vo.setNouvCapitalConstitutif(TypeConverter.getInstance()
	// .doubleToString(item.getNouvCapitalConstitutif()));
	// vo.setNouvDatNaissance(TypeConverter.getInstance().calendarToString(
	// item.getNouvDatNaissance()));
	// //
	// vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));
	//
	// if (item.getRefTypeMajCapital() != null) {
	// vo.setRefTypeMajCapital(TypeConverter.getInstance().longToString(
	// ((IEntite) item.getRefTypeMajCapital()).getId()));
	// vo.setRefTypeMajCapitalLabel(item.getRefTypeMajCapital().toString());
	// }
	//
	// (new MouvementVOConverter()).itemToVo(vo, item, itemList);
	// /*
	// * if (itemList == null) return;
	// *
	// * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
	// * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
	// */
	// }
	//
	// public void voToItem(MvtMajCapitalVO vo, MvtMajCapital item) {
	// if (StringUtils.isNotEmpty(vo.getNouvTaux()))
	// //
	// item.setNouvTaux(TypeConverter.getInstance().stringToDouble(vo.getNouvTaux()));
	// item.setNouvTaux(TypeConverter.getInstance().stringToDouble(
	// StringUtils.replace(
	// StringUtils.replace(vo.getNouvTaux(), " ", ""),
	// ",", ".")));
	// if (StringUtils.isNotEmpty(vo.getDatSuspension()))
	// item.setDatSuspension(TypeConverter.getInstance().stringToCalendar(
	// "yyyyMMdd", vo.getDatSuspension()));
	// if (StringUtils.isNotEmpty(vo.getDatRemise()))
	// item.setDatRemise(TypeConverter.getInstance().stringToCalendar(
	// "yyyyMMdd", vo.getDatRemise()));
	// if (StringUtils.isNotEmpty(vo.getArreragesDus()))
	// //
	// item.setArreragesDus(TypeConverter.getInstance().stringToDouble(vo.getArreragesDus()));
	// item.setArreragesDus(TypeConverter.getInstance().stringToDouble(
	// StringUtils.replace(
	// StringUtils.replace(vo.getArreragesDus(), " ", ""),
	// ",", ".")));
	// if (StringUtils.isNotEmpty(vo.getArreragesPercus()))
	// //
	// item.setArreragesPercus(TypeConverter.getInstance().stringToDouble(vo.getArreragesPercus()));
	// item.setArreragesPercus(TypeConverter.getInstance().stringToDouble(
	// StringUtils.replace(StringUtils.replace(
	// vo.getArreragesPercus(), " ", ""), ",", ".")));
	// if (StringUtils.isNotEmpty(vo.getNouvMntRente()))
	// //
	// item.setNouvMntRente(TypeConverter.getInstance().stringToDouble(vo.getNouvMntRente()));
	// item.setNouvMntRente(TypeConverter.getInstance().stringToDouble(
	// StringUtils.replace(
	// StringUtils.replace(vo.getNouvMntRente(), " ", ""),
	// ",", ".")));
	// if (StringUtils.isNotEmpty(vo.getNouvSalaire()))
	// //
	// item.setNouvSalaire(TypeConverter.getInstance().stringToDouble(vo.getNouvSalaire()));
	// item.setNouvSalaire(TypeConverter.getInstance().stringToDouble(
	// StringUtils.replace(
	// StringUtils.replace(vo.getNouvSalaire(), " ", ""),
	// ",", ".")));
	// if (StringUtils.isNotEmpty(vo.getNouvIPP()))
	// //
	// item.setNouvIPP(TypeConverter.getInstance().stringToDouble(vo.getNouvIPP()));
	// item.setNouvIPP(TypeConverter.getInstance().stringToDouble(
	// StringUtils.replace(
	// StringUtils.replace(vo.getNouvIPP(), " ", ""), ",",
	// ".")));
	// if (StringUtils.isNotEmpty(vo.getDatDebutArrerage()))
	// item.setDatDebutArrerage(TypeConverter.getInstance()
	// .stringToCalendar("yyyyMMdd", vo.getDatDebutArrerage()));
	// if (StringUtils.isNotEmpty(vo.getDatFinArrerage()))
	// item.setDatFinArrerage(TypeConverter.getInstance()
	// .stringToCalendar("yyyyMMdd", vo.getDatFinArrerage()));
	// if (StringUtils.isNotEmpty(vo.getMntProrata()))
	// //
	// item.setMntProrata(TypeConverter.getInstance().stringToDouble(vo.getMntProrata()));
	// item.setMntProrata(TypeConverter.getInstance().stringToDouble(
	// StringUtils.replace(
	// StringUtils.replace(vo.getMntProrata(), " ", ""),
	// ",", ".")));
	// if (StringUtils.isNotEmpty(vo.getMntDiff()))
	// //
	// item.setMntDiff(TypeConverter.getInstance().stringToDouble(vo.getMntDiff()));
	// item.setMntDiff(TypeConverter.getInstance().stringToDouble(
	// StringUtils.replace(
	// StringUtils.replace(vo.getMntDiff(), " ", ""), ",",
	// ".")));
	// if (StringUtils.isNotEmpty(vo.getNouvCapitalConstitutif()))
	// //
	// item.setNouvCapitalConstitutif(TypeConverter.getInstance().stringToDouble(vo.getNouvCapitalConstitutif()));
	// item.setNouvCapitalConstitutif(TypeConverter.getInstance()
	// .stringToDouble(
	// StringUtils.replace(StringUtils.replace(
	// vo.getNouvCapitalConstitutif(), " ", ""),
	// ",", ".")));
	// if (StringUtils.isNotEmpty(vo.getNouvDatNaissance()))
	// item.setNouvDatNaissance(TypeConverter.getInstance()
	// .stringToCalendar("yyyyMMdd", vo.getNouvDatNaissance()));
	// // if(StringUtils.isNotEmpty(vo.getDateCreation()))
	// //
	// item.setDateCreation(TypeConverter.getInstance().stringToCalendar("yyyyMMdd",vo.getDateCreation()));
	//
	// if (StringUtils.isNotEmpty(vo.getRefTypeMajCapital())) {
	// TypeMajCapital refTypeMajCapital = new TypeMajCapital();
	//
	// ((IEntite) refTypeMajCapital).setId(TypeConverter.getInstance()
	// .stringToLong(vo.getRefTypeMajCapital()));
	// item.setRefTypeMajCapital(refTypeMajCapital);
	// }
	//
	// (new MouvementVOConverter()).voToItem(vo, item);
	// }
	//
	// protected void doValider(MvtMajCapitalVO vo) throws ValidationException {
	//
	// }

	@Override
	protected void doValider(IValueObject vo) throws ValidationException {
		// TODO Auto-generated method stub
		
	}
}
