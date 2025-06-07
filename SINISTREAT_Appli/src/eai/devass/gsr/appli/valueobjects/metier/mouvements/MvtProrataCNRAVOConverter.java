package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;

/**
 * Value Object Converter de MvtProrataCNRA
 * 
 * @author Nom Prenom (email)
 */
public class MvtProrataCNRAVOConverter extends CommunMouvementVOConverter {

	// public IValidator getValidator() {
	// return null;
	// }
	//
	// public List convertValueObjectToItems(Object ovo)
	// throws ValidationException {
	// MvtProrataCNRAVO vo = (MvtProrataCNRAVO) ovo;
	// this.doValider(vo);
	// MvtProrataCNRA item = new MvtProrataCNRA();
	// voToItem(vo, item);
	// List<MvtProrataCNRA> itemList = new ArrayList<MvtProrataCNRA>();
	// itemList.add(item);
	// return itemList;
	// }
	//
	// public Object convertItemsToValueObject(List itemList) {
	// if ((itemList == null) || (itemList.size() == 0)) {
	// return null;
	// }
	// MvtProrataCNRAVO vo = new MvtProrataCNRAVO();
	// if (itemList.get(0) instanceof MvtProrataCNRA) {
	// itemToVo(vo, (MvtProrataCNRA) itemList.get(0), itemList);
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
	// if (itemList.get(i) instanceof MvtProrataCNRA) {
	// MvtProrataCNRAVO vo = new MvtProrataCNRAVO();
	// itemToVo(vo, (MvtProrataCNRA) itemList.get(i), null);
	// list.add(vo);
	// }
	// }
	// return list;
	// }
	//
	// public void itemToVo(MvtProrataCNRAVO vo, MvtProrataCNRA item, List
	// itemList) {
	//
	// vo.setMntProrataCNRA(TypeConverter.getInstance().doubleToString(
	// item.getMntProrataCNRA()));
	// //
	// vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));
	//
	// (new MvtConsignCNRAVOConverter()).itemToVo(vo, item, itemList);
	// /*
	// * if (itemList == null) return;
	// *
	// * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
	// * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
	// */
	// }
	//
	// public void voToItem(MvtProrataCNRAVO vo, MvtProrataCNRA item) {
	// if (vo.getMntProrataCNRA() != null)
	// //
	// item.setMntProrataCNRA(TypeConverter.getInstance().stringToDouble(vo.getMntProrataCNRA()));
	// item.setMntProrataCNRA(TypeConverter.getInstance().stringToDouble(
	// StringUtils.replace(StringUtils.replace(
	// vo.getMntProrataCNRA(), " ", ""), ",", ".")));
	// // if(vo.getDateCreation()!=null)
	// //
	// item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));
	//
	// (new MvtConsignCNRAVOConverter()).voToItem(vo, item);
	// }

	@Override
	protected void doValider(IValueObject vo) throws ValidationException {
		// TODO Auto-generated method stub
	}
}
