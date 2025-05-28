package eai.devass.gsr.appli.valueobjects.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.metier.reglement.Prerglt;
import eai.devass.gsr.appli.modele.parametrage.TypeCheque;
import eai.devass.gsr.appli.modele.parametrage.TypeVirement;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeChequeVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeChequeVOConverter;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeVirementVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeVirementVOConverter;







/**
Value Object Converter de Prerglt
@author Nom Prenom (email)
*/
public class PrergltVOConverter implements IValueObjectConverter{
   
   public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo) throws ValidationException {
		PrergltVO vo = (PrergltVO) ovo;	
		this.doValider(vo);		
		Prerglt item = new Prerglt();	
		voToItem(vo,item);
		List<Prerglt> itemList = new ArrayList<Prerglt>();
		itemList.add(item);
		return itemList;
	}


	public Object convertItemsToValueObject(List itemList) {
		if ( (itemList == null) || (itemList.size()==0) ){
			return null;
		}
		PrergltVO vo = new PrergltVO();
		if (itemList.get(0) instanceof Prerglt){
			itemToVo(vo, (Prerglt)itemList.get(0), itemList);
		}
		
		return vo;
	}
	


	
	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for(int i=0;i<itemList.size();i++){
			if (itemList.get(i) instanceof Prerglt) {
				PrergltVO vo = new PrergltVO();
				itemToVo(vo, (Prerglt) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}
	
	public void itemToVo(PrergltVO vo, Prerglt item, List itemList) {
			vo.setId(item.getId() );


		    vo.setPourLeCompte(item.getPourLeCompte() );
		    vo.setAdresse(item.getAdresse() );
		    vo.setDetails(item.getDetails() );
		    vo.setLblVirement(item.getLblVirement() );
		    vo.setNumCIN(item.getNumCIN() );
		    vo.setNumRIB(item.getNumRIB() );
		    vo.setRefBordereau(item.getRefBordereau() );
		    vo.setRefRglt(item.getRefRglt() );
		    vo.setIdsIntermediaire(item.getIdsIntermediaire());
		    vo.setCodeVille(item.getCodeVille());
		    vo.setCodePays(item.getCodePays());
		    vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));	


//		    if (item.getRefTypeReglement() != null) {
//				TypeReglementVO refVO = new TypeReglementVO();
//				TypeReglementVOConverter typeRgltVOConverter =new TypeReglementVOConverter();
//				
//				refVO = (TypeReglementVO) typeRgltVOConverter.itemToVo(refVO,item.getRefTypeReglement(), null);
//				vo.setRefTypeReglement(refVO);
//			}
//		    
//		    if (item.getRefModeReglement() != null) {
//				ModeReglementVO refVO = new ModeReglementVO();
//				ModeReglementVOConverter modeRgltVOConverter =new ModeReglementVOConverter();
//				
//				refVO = (ModeReglementVO) modeRgltVOConverter.itemToVo(refVO,item.getRefModeReglement(), null);
//				vo.setRefModeReglement(refVO);
//			}
		    if (item.getRefTypeCheque() != null) {
				TypeChequeVO refVO = new TypeChequeVO();
				TypeChequeVOConverter typeChqVOConverter =new TypeChequeVOConverter();
				
				typeChqVOConverter.itemToVo(refVO,item.getRefTypeCheque(), null);
				vo.setRefTypeCheque(refVO);
			}
		    
		    if (item.getRefTypeVirement() != null) {
				TypeVirementVO refVO = new TypeVirementVO();
				TypeVirementVOConverter typeVOConverter =new TypeVirementVOConverter();
				
				 typeVOConverter.itemToVo(refVO,item.getRefTypeVirement(), null);
				vo.setRefTypeVirement(refVO);
			}
		    
		    
//			if (item.getRefTypeCheque() != null ) {
//				vo.setRefTypeCheque(TypeConverter.getInstance().longToString(((IEntite)item.getRefTypeCheque()).getId()));
//				vo.setRefTypeChequeLabel(item.getRefTypeCheque().toString());
//			}
//			if (item.getRefTypeVirement() != null ) {
//				vo.setRefTypeVirement(TypeConverter.getInstance().longToString(((IEntite)item.getRefTypeVirement()).getId()));
//				vo.setRefTypeVirementLabel(item.getRefTypeVirement().toString());
//			}
//			if (item.getRefTypeReglement() != null ) {
//				vo.setRefTypeReglement(TypeConverter.getInstance().longToString(((IEntite)item.getRefTypeReglement()).getId()));
//				vo.setRefTypeReglementLabel(item.getRefTypeReglement().toString());
//			}
//			if (item.getRefModeReglement() != null ) {
//				vo.setRefModeReglement(TypeConverter.getInstance().longToString(((IEntite)item.getRefModeReglement()).getId()));
//				vo.setRefModeReglementLabel(item.getRefModeReglement().toString());
//			}
//			if (item.getRefPays() != null ) {
//				vo.setRefPays(TypeConverter.getInstance().longToString(((IEntite)item.getRefPays()).getId()));
//				vo.setRefPaysLabel(item.getRefPays().toString());
//			}
//			if (item.getRefVille() != null ) {
//				vo.setRefVille(TypeConverter.getInstance().longToString(((IEntite)item.getRefVille()).getId()));
//				vo.setRefVilleLabel(item.getRefVille().toString());
//			}
//		    if (item.getRefVille() != null ) {
//		    	VilleVO refVO = new VilleVO();
//		    	
//		    	refVO.setCode(item.getRefVille().getCode());
//		    	refVO.setLibelle(item.getRefVille().getLibelle());
//		    	
//		    	vo.setRefVille(refVO);
//				
//		    }
			
//			if (item.getRefIntermediaire() != null ) {
//				
//				IntermediaireVO o= new IntermediaireVO();
//				IntermediaireVOConverter oc=  new IntermediaireVOConverter();
//				oc.itemToVo(o,item.getRefIntermediaire(),null);
////				vo.setRefIntermediaire(TypeConverter.getInstance().longToString(((IEntite)item.getRefIntermediaire()).getId()));
////				vo.setRefIntermediaireLabel(item.getRefIntermediaire().toString());
//			}


/*
	    if (itemList == null) return;

		for (int i=1; i<itemList.size(); i++) {
			if (itemList.get(i) instanceof List)
			   if (((List)itemList.get(i)).size() != 0) {
			   }
		}
*/
}

	public void voToItem(PrergltVO vo, Prerglt item) {
		item.setId(vo.getId());
		item.setPourLeCompte(vo.getPourLeCompte() );
		item.setAdresse(vo.getAdresse() );
		item.setDetails(vo.getDetails() );
		item.setLblVirement(vo.getLblVirement() );
		item.setNumCIN(vo.getNumCIN() );
		item.setNumRIB(vo.getNumRIB() );
		item.setRefBordereau(vo.getRefBordereau() );
		item.setRefRglt(vo.getRefRglt() );
		item.setIdsIntermediaire(vo.getIdsIntermediaire());
		item.setCodeVille(vo.getCodeVille());
		item.setCodePays(vo.getCodePays());
     if(vo.getDateCreation()!=null) {
		item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));
	}	


//     if(vo.getRefTypeReglement()!=null){
//    	 TypeReglementVOConverter typeRgltVOConv = new TypeReglementVOConverter();
//    	 TypeReglement trglt=new TypeReglement();
//    	 typeRgltVOConv.voToItem(vo.getRefTypeReglement(), trglt);
//    	 item.setRefTypeReglement(trglt);
//    	 
//     }
//     
//if(vo.getRefModeReglement()!=null){
//    	 
//	ModeReglementVOConverter modeRgltVOConv = new ModeReglementVOConverter();
//	 ModeReglement mrglt=new ModeReglement();
//	 modeRgltVOConv.voToItem(vo.getRefModeReglement(), mrglt);
//	 item.setRefModeReglement(mrglt);
//    	 
//     }
//
//

     //CK --if(vo.getRefTypeCheque()!=null && StringUtils.isNotEmpty(vo.getRefTypeCheque().getId())){
		if (vo.getRefTypeCheque() != null && vo.getRefTypeCheque().getId() != 0) {
			TypeChequeVOConverter tchqVOConv = new TypeChequeVOConverter();
			TypeCheque tchq = new TypeCheque();
			tchqVOConv.voToItem(vo.getRefTypeCheque(), tchq);
			item.setRefTypeCheque(tchq);

		}
//     
//     
//CK -- if(vo.getRefTypeVirement() != null && StringUtils.isNotEmpty(vo.getRefTypeVirement().getId())){
		if (vo.getRefTypeVirement() != null
				&& vo.getRefTypeVirement().getId() != 0) {
			TypeVirementVOConverter typevrmtVOConv = new TypeVirementVOConverter();
			TypeVirement tvrmt = new TypeVirement();
			typevrmtVOConv.voToItem(vo.getRefTypeVirement(), tvrmt);
			item.setRefTypeVirement(tvrmt);

		}    
   


//if (vo.getRefVille() != null ) {
//	Ville refItem = new Ville();
//	
//	refItem.setCode(vo.getRefVille().getCode());
//	refItem.setLibelle(vo.getRefVille().getLibelle());
//	
//	item.setRefVille(refItem);
//	
//}
//if(vo.getRefIntermediaire()!=null){
//	 
//	TypeVirementVOConverter typevrmtVOConv = new TypeVirementVOConverter();
//	 TypeVirement tvrmt=new TypeVirement();
//	 typevrmtVOConv.voToItem(vo.getRefTypeVirement(), tvrmt);
//	 item.setRefTypeVirement(tvrmt);
//	 
//	 Intermediaire i= new Intermediaire();
//		IntermediaireVOConverter oc=  new IntermediaireVOConverter();
//		oc.voToItem(vo.getRefIntermediaire(),i);
//	 
//}    



//		if (StringUtils.isNotEmpty(vo.getRefTypeCheque())) {
//			TypeCheque refTypeCheque = new TypeCheque();
//
//			((IEntite)refTypeCheque).setId(TypeConverter.getInstance().stringToLong(vo.getRefTypeCheque()));
//			item.setRefTypeCheque(refTypeCheque);
//		}
//		if (StringUtils.isNotEmpty(vo.getRefTypeVirement())) {
//			TypeVirement refTypeVirement = new TypeVirement();
//
//			((IEntite)refTypeVirement).setId(TypeConverter.getInstance().stringToLong(vo.getRefTypeVirement()));
//			item.setRefTypeVirement(refTypeVirement);
//		}
//		if (StringUtils.isNotEmpty(vo.getRefTypeReglement())) {
//			TypeReglement refTypeReglement = new TypeReglement();
//
//			((IEntite)refTypeReglement).setId(TypeConverter.getInstance().stringToLong(vo.getRefTypeReglement()));
//			item.setRefTypeReglement(refTypeReglement);
//		}
//		if (StringUtils.isNotEmpty(vo.getRefModeReglement())) {
//			ModeReglement refModeReglement = new ModeReglement();
//
//			((IEntite)refModeReglement).setId(TypeConverter.getInstance().stringToLong(vo.getRefModeReglement()));
//			item.setRefModeReglement(refModeReglement);
//		}
//		if (StringUtils.isNotEmpty(vo.getRefPays())) {
//			Pays refPays = new Pays();
//
//			((IEntite)refPays).setId(TypeConverter.getInstance().stringToLong(vo.getRefPays()));
//			item.setRefPays(refPays);
//		}
//		if (StringUtils.isNotEmpty(vo.getRefVille())) {
//			Ville refVille = new Ville();
//
//			((IEntite)refVille).setId(TypeConverter.getInstance().stringToLong(vo.getRefVille()));
//			item.setRefVille(refVille);
//		}
//		if (StringUtils.isNotEmpty(vo.getRefIntermediaire())) {
//			Intermediaire refIntermediaire = new Intermediaire();
//
//			((IEntite)refIntermediaire).setId(TypeConverter.getInstance().stringToLong(vo.getRefIntermediaire()));
//			item.setRefIntermediaire(refIntermediaire);
//		}


	}
	protected void doValider(PrergltVO vo) throws ValidationException{
		
	}
}

