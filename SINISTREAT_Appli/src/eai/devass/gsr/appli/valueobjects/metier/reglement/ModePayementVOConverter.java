
package eai.devass.gsr.appli.valueobjects.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.metier.reglement.ModePayement;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
Value Object Converter de ModePayement
@author Nom Prenom (email)
*/
public class ModePayementVOConverter implements IValueObjectConverter{
   
   public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo) throws ValidationException {
		ModePayementVO vo = (ModePayementVO) ovo;	
		this.doValider(vo);		
		ModePayement item = new ModePayement();	
		voToItem(vo,item);
		//Infos version if set
		if ( vo.getIdHistorisable() != 0 ){
			item.setId(vo.getIdHistorisable());
		}
		List<ModePayement> itemList = new ArrayList<ModePayement>();
		itemList.add(item);
		return itemList;
	}


	public Object convertItemsToValueObject(List itemList) {
		if ( (itemList == null) || (itemList.size()==0) ){
			return null;
		}
		ModePayementVO vo = new ModePayementVO();
		if (itemList.get(0) instanceof ModePayement){
			itemToVo(vo, (ModePayement)itemList.get(0), itemList);
		}
		
		return vo;
	}
	


	
	public ArrayList<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for(int i=0;i<itemList.size();i++){
			if (itemList.get(i) instanceof ModePayement) {
				ModePayementVO vo = new ModePayementVO();
				itemToVo(vo, (ModePayement) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}
	
	public void itemToVo(ModePayementVO vo, ModePayement item, List itemList) {
			vo.setId(TypeConverter.getInstance().longToString(item.getId() ));


		    vo.setIdModePayement(TypeConverter.getInstance().longToString(item.getIdModePayement()));	
		    vo.setDescription(item.getDescription() );
		    vo.setBeneficiaire(item.getBeneficiaire() );
		    vo.setNumeroCIN(item.getNumeroCIN() );
		    vo.setAdresse(item.getAdresse() );
		    vo.setCodePostale(TypeConverter.getInstance().doubleToString(item.getCodePostale()));	
		    vo.setVille(item.getVille());	
		    vo.setPays(item.getPays());	
		    vo.setNumeroRIB(item.getNumeroRIB() );
		    vo.setBanque(item.getBanque());	
		    vo.setAgenceBancaire(item.getAgenceBancaire());	
		    vo.setEtat(TypeConverter.getInstance().doubleToString(item.getEtat()));	
		    vo.setDateEtat(TypeConverter.getInstance().calendarToString(item.getDateEtat()));	
		    vo.setValidation(TypeConverter.getInstance().booleanToString(item.getValidation()));	
		    vo.setDateValidation(TypeConverter.getInstance().calendarToString(item.getDateValidation()));	
		    vo.setDateCreation(TypeConverter.getInstance().calendarToString(item.getDateCreation()));
		    vo.setVirmentOMC(item.getVirmentOMC());

}

	public void voToItem(ModePayementVO vo, ModePayement item) {
		item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
     if(vo.getIdModePayement()!=null) {
		item.setIdModePayement(TypeConverter.getInstance().stringToLong(vo.getIdModePayement()));
	}	
		item.setDescription(vo.getDescription() );
		item.setBeneficiaire(vo.getBeneficiaire() );
		item.setNumeroCIN(vo.getNumeroCIN() );
		item.setAdresse(vo.getAdresse() );
     if(vo.getCodePostale()!=null && !"".equals(vo.getCodePostale())) {
		item.setCodePostale(TypeConverter.getInstance().stringToDouble(vo.getCodePostale()));
	}	
     if(vo.getVille()!=null) {
		item.setVille(vo.getVille());
	}	
     if(vo.getPays()!=null) {
		item.setPays(vo.getPays());
	}	
		item.setNumeroRIB(vo.getNumeroRIB() );
     if(vo.getBanque()!=null && !"".equals(vo.getBanque())) {
		item.setBanque(vo.getBanque());
	}	
     if(vo.getAgenceBancaire()!=null) {
		item.setAgenceBancaire(vo.getAgenceBancaire());
	}	
     if(vo.getEtat()!=null && !vo.getEtat().trim().equals("")) {
		item.setEtat(TypeConverter.getInstance().stringToDouble(vo.getEtat()));
	}	
     if(vo.getDateEtat()!=null && !"".equals(vo.getDateEtat())) {
		item.setDateEtat(TypeConverter.getInstance().stringToCalendar(vo.getDateEtat()));
	}	
     if(vo.getValidation()!=null && !"".equals(vo.getValidation())) {
		item.setValidation(TypeConverter.getInstance().stringToBoolean(vo.getValidation()));
	}	
     if(vo.getDateValidation()!=null && !"".equals(vo.getDateValidation())) {
		item.setDateValidation(TypeConverter.getInstance().stringToCalendar(vo.getDateValidation()));
	}	
     if(vo.getDateCreation()!=null && !"".equals(vo.getDateCreation())) {
		item.setDateCreation(TypeConverter.getInstance().stringToCalendar(vo.getDateCreation()));
	}	
     
     if(vo.getVirmentOMC()!=null && !"".equals(vo.getVirmentOMC())) {
		item.setVirmentOMC(vo.getVirmentOMC());
	}



	}
	protected void doValider(ModePayementVO vo) throws ValidationException{
		
	}
}

