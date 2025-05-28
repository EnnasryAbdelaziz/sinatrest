package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideAvisSuspension;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideAvisSuspensionVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class ModifierCourrierHybrideAvisSuspensionUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		CourrierHybrideAvisSuspension courrierAvisSuspensionHybride = (CourrierHybrideAvisSuspension) this
				.getItem(CourrierHybrideAvisSuspension.class);
		CourrierHybrideAvisSuspension ChQResult = null;
		CourrierHybrideAvisSuspensionVO ChQResultVO = null;
		logger.info("Debut UC ModifierCourrierHybrideAvisSuspensionUC");
		try {
			ChQResult = (CourrierHybrideAvisSuspension) parametrageManager.majCourrierHybridesAvisSuspension(courrierAvisSuspensionHybride);
			//ChQResult = castFrom(ChQResult);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(ChQResult);
		logger.info("Fin UC ModifierCourrierHybrideAvisSuspensionUC");
	}

	public boolean isTransactionnal() {
		return true;
	}

	public CourrierHybrideAvisSuspension castFrom(CourrierHybrideAvisSuspension ChQResult){
		
		CourrierHybrideAvisSuspension courrierHybrideAvisSuspension= new CourrierHybrideAvisSuspension();
			if(ChQResult.getIdLRH() != null) 
			{
				courrierHybrideAvisSuspension.setIdLRH(ChQResult.getIdLRH());
			}
			else
			{
				courrierHybrideAvisSuspension.setIdLRH(" ");
			}
		
			if(ChQResult.getNomClient() != null) 
			{
				courrierHybrideAvisSuspension.setNomClient(ChQResult.getNomClient());
			}
			else
			{
				courrierHybrideAvisSuspension.setNomClient(" ");
			}
			
			if(ChQResult.getDateAccident() != null) 
			{
				courrierHybrideAvisSuspension.setDateAccident(ChQResult.getDateAccident());
			}
			else
			{
				courrierHybrideAvisSuspension.setDateAccident(null);
			}
			
			if(ChQResult.getDateCreation() != null) 
			{
				courrierHybrideAvisSuspension.setDateCreation(ChQResult.getDateCreation());
			}
			else
			{
				courrierHybrideAvisSuspension.setDateCreation(null);
			}
			
			if(ChQResult.getNumeroSinistre() != null) 
			{
				courrierHybrideAvisSuspension.setNumeroSinistre(ChQResult.getNumeroSinistre());
			}
			else
			{
				courrierHybrideAvisSuspension.setNumeroSinistre(" ");
			}
			if(ChQResult.getTypeLettre() != null) 
			{
				courrierHybrideAvisSuspension.setTypeLettre(ChQResult.getTypeLettre());
			}
			else
			{
				courrierHybrideAvisSuspension.setTypeLettre(" ");
			}
			
			if(ChQResult.getNomVictime() != null) 
			{
				courrierHybrideAvisSuspension.setNomVictime(ChQResult.getNomVictime());
			}
			else
			{ 
				courrierHybrideAvisSuspension.setNomVictime(" ");
			}
			if(ChQResult.getAdresseVictime() != null) 
			{
				courrierHybrideAvisSuspension.setAdresseVictime(ChQResult.getAdresseVictime());
			}
			else
			{
				courrierHybrideAvisSuspension.setAdresseVictime(" ");
			}
			if(ChQResult.getDateContreVisiteFirst() != null) 
			{
				courrierHybrideAvisSuspension.setDateContreVisiteFirst(ChQResult.getDateContreVisiteFirst());
			}
			else
			{
				courrierHybrideAvisSuspension.setDateContreVisiteFirst(null);
			}
			
			if(ChQResult.getDateContreVisiteSecond() != null) 
			{
				courrierHybrideAvisSuspension.setDateContreVisiteSecond(ChQResult.getDateContreVisiteSecond());
			}
			else
			{
				courrierHybrideAvisSuspension.setDateContreVisiteSecond(null);
			}
		

        
        return courrierHybrideAvisSuspension;
    }

}
