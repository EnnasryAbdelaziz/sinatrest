package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.Calendar;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.bnej.LotBnej;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitCreationLotBnejUC extends BaseUC {

	@SuppressWarnings("rawtypes")
	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		
		Integer year=Calendar.getInstance().get(Calendar.YEAR);
		
		Integer numero=0;
		
		try{
			numero=bnejManager.getCompteurLot(Calendar.getInstance());
		}catch (Exception e) {
			
				throw new FonctionnelleException(e.getMessage());
	
		}
	 
		
		LotBnej lotBnej=new LotBnej();
		if(numero!=null && numero!=0){
			
			lotBnej.setNumero(bnejManager.buildNumeroLot(numero,year));
		}else{
			lotBnej.setNumero(bnejManager.buildNumeroLot(1, year));
		}
		
		
		
		this.addResultItem(lotBnej);
		
		
		

	}

}


