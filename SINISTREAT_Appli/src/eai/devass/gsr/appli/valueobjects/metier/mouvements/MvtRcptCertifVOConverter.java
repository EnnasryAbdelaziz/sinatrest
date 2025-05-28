package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;






/**
Value Object Converter de MvtRcptCertif
@author Nom Prenom (email)
*/
public class MvtRcptCertifVOConverter extends CommunMouvementVOConverter {
   
//   public IValidator getValidator() {
//		return null;
//	}
//
//	public List convertValueObjectToItems(Object ovo) throws ValidationException {
//		MvtRcptCertifVO vo = (MvtRcptCertifVO) ovo;	
//		this.doValider(vo);		
//		MvtRcptCertif item = new MvtRcptCertif();	
//		voToItem(vo,item);
//		List<MvtRcptCertif> itemList = new ArrayList<MvtRcptCertif>();
//		itemList.add(item);
//		return itemList;
//	}
//
//
//	public Object convertItemsToValueObject(List itemList) {
//		if ( (itemList == null) || (itemList.size()==0) ){
//			return null;
//		}
//		MvtRcptCertifVO vo = new MvtRcptCertifVO();
//		if (itemList.get(0) instanceof MvtRcptCertif){
//			itemToVo(vo, (MvtRcptCertif)itemList.get(0), itemList);
//		}
//		
//		return vo;
//	}
//	
//
//
//	
//	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
//		if ((itemList == null) || (itemList.size() == 0)) {
//			return null;
//		}
//		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
//		for(int i=0;i<itemList.size();i++){
//			if (itemList.get(i) instanceof MvtRcptCertif) {
//				MvtRcptCertifVO vo = new MvtRcptCertifVO();
//				itemToVo(vo, (MvtRcptCertif) itemList.get(i), null);
//				list.add(vo);
//			}
//		}
//		return list;
//	}
//	
//	public void itemToVo(MvtRcptCertifVO vo, MvtRcptCertif item, List itemList) {
//
//
//		    vo.setDatAu(TypeConverter.getInstance().calendarToString(item.getDatAu()));	
//		    vo.setDatDu(TypeConverter.getInstance().calendarToString(item.getDatDu()));	
//		    vo.setDatRcpt(TypeConverter.getInstance().calendarToString(item.getDatRcpt()));	
////		    vo.setNumCertificat(TypeConverter.getInstance().integerToString(item.getNumCertificat()));	
//		    vo.setObservation(item.getObservation() );		    
//
//			if (item.getRefTypeCertificat() != null ) {
//				vo.setRefTypeCertificat(TypeConverter.getInstance().longToString(((IEntite)item.getRefTypeCertificat()).getId()));
//				vo.setRefTypeCertificatLabel(item.getRefTypeCertificat().toString());
//			}
//
//
//		(new MouvementVOConverter()).itemToVo(vo,item, itemList);
///*
//	    if (itemList == null) return;
//
//		for (int i=1; i<itemList.size(); i++) {
//			if (itemList.get(i) instanceof List)
//			   if (((List)itemList.get(i)).size() != 0) {
//			   }
//		}
//*/
//}
//
//	public void voToItem(MvtRcptCertifVO vo, MvtRcptCertif item) {
//     if(vo.getDatAu()!=null)
//		item.setDatAu(TypeConverter.getInstance().stringToCalendar(vo.getDatAu()));	
//     if(vo.getDatDu()!=null)
//		item.setDatDu(TypeConverter.getInstance().stringToCalendar(vo.getDatDu()));	
//     if(vo.getDatRcpt()!=null && "".equals(vo.getDatRcpt()))
//		item.setDatRcpt(TypeConverter.getInstance().stringToCalendar(vo.getDatRcpt()));	
////     if(vo.getNumCertificat()!=null)
//////		item.setNumCertificat(TypeConverter.getInstance().stringToInteger(vo.getNumCertificat()));	
////		item.setObservation(vo.getObservation() );     
//
//		if (StringUtils.isNotEmpty(vo.getRefTypeCertificat())) {
//			TypeCertificat refTypeCertificat = new TypeCertificat();
//
//			((IEntite)refTypeCertificat).setId(TypeConverter.getInstance().stringToLong(vo.getRefTypeCertificat()));
//			item.setRefTypeCertificat(refTypeCertificat);
//		}
//
//
//		(new MouvementVOConverter()).voToItem(vo,item);
//	}
	protected void doValider(IValueObject vo) throws ValidationException{
		
	}
}

