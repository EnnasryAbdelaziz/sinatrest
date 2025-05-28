package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideOuvertureRente;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideOuvertureRenteVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class ModifierCourrierHybrideOuvertureRenteUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		CourrierHybrideOuvertureRente courrierOuvertureRenteHybride = (CourrierHybrideOuvertureRente) this
				.getItem(CourrierHybrideOuvertureRente.class);
		CourrierHybrideOuvertureRente ChQResult = null;
		CourrierHybrideOuvertureRenteVO ChQResultVO = null;
		logger.info("Debut UC ModifierCourrierHybrideOuvertureRenteUC");
		try {
			ChQResult = (CourrierHybrideOuvertureRente) parametrageManager.majCourrierHybridesOuvertureRente(courrierOuvertureRenteHybride);
			//ChQResult = castFrom(ChQResult);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(ChQResult);
		logger.info("Fin UC ModifierCourrierHybrideOuvertureRenteUC");
	}

	public boolean isTransactionnal() {
		return true;
	}

	public CourrierHybrideOuvertureRente castFrom(CourrierHybrideOuvertureRente ChQResult){
		
		CourrierHybrideOuvertureRente courrierHybrideOuvertureRente= new CourrierHybrideOuvertureRente();
			if(ChQResult.getIdLRH() != null) 
			{
				courrierHybrideOuvertureRente.setIdLRH(ChQResult.getIdLRH());
			}
			else
			{
				courrierHybrideOuvertureRente.setIdLRH(" ");
			}
			if(ChQResult.getNumeroRente() != null) 
			{
				courrierHybrideOuvertureRente.setNumeroRente(ChQResult.getNumeroRente());
			}
			else
			{
				courrierHybrideOuvertureRente.setNumeroRente(" ");
			}
			if(ChQResult.getNomClient() != null) 
			{
				courrierHybrideOuvertureRente.setNomClient(ChQResult.getNomClient());
			}
			else
			{
				courrierHybrideOuvertureRente.setNomClient(" ");
			}
			if(ChQResult.getLienParente() != null) 
			{
				courrierHybrideOuvertureRente.setLienParente(ChQResult.getLienParente());
			}
			else
			{
				courrierHybrideOuvertureRente.setLienParente(" ");
			}
			if(ChQResult.getDateAccident() != null) 
			{
				courrierHybrideOuvertureRente.setDateAccident(ChQResult.getDateAccident());
			}
			else
			{
				courrierHybrideOuvertureRente.setDateAccident(null);
			}
			
			if(ChQResult.getDateCreation() != null) 
			{
				courrierHybrideOuvertureRente.setDateCreation(ChQResult.getDateCreation());
			}
			else
			{
				courrierHybrideOuvertureRente.setDateCreation(null);
			}
			
			if(ChQResult.getUserSasCreateur() != null) 
			{
				courrierHybrideOuvertureRente.setUserSasCreateur(ChQResult.getUserSasCreateur());
			}
			else
			{
				courrierHybrideOuvertureRente.setUserSasCreateur(" ");
			}
			if(ChQResult.getAdresse() != null) 
			{
				courrierHybrideOuvertureRente.setAdresse(ChQResult.getAdresse());
			}
			else
			{
				courrierHybrideOuvertureRente.setAdresse(" ");
			}
			if(ChQResult.getCompagnieRente() != null) 
			{
				courrierHybrideOuvertureRente.setCompagnieRente(ChQResult.getCompagnieRente());
			}
			else
			{
				courrierHybrideOuvertureRente.setCompagnieRente(" ");
			}
			if(ChQResult.getGsm() != null) 
			{
				courrierHybrideOuvertureRente.setGsm(ChQResult.getGsm());
			}
			else
			{
				courrierHybrideOuvertureRente.setGsm(" ");
			}
			if(ChQResult.getDateConstitution() != null) 
			{
				courrierHybrideOuvertureRente.setDateConstitution(ChQResult.getDateConstitution());
			}
			else
			{
				courrierHybrideOuvertureRente.setDateConstitution(null);
			}
			if(ChQResult.getDateLimiteConsignation() != null) 
			{
				courrierHybrideOuvertureRente.setDateLimiteConsignation(ChQResult.getDateLimiteConsignation());
			}
			else
			{
				courrierHybrideOuvertureRente.setDateLimiteConsignation(null);
			}
			if(ChQResult.getDateGeneration() != null) 
			{
				courrierHybrideOuvertureRente.setDateGeneration(ChQResult.getDateGeneration());
			}
			else
			{
				courrierHybrideOuvertureRente.setDateGeneration(null);
			}
			if(ChQResult.getNumeroSinistre() != null) 
			{
				courrierHybrideOuvertureRente.setNumeroSinistre(ChQResult.getNumeroSinistre());
			}
			else
			{
				courrierHybrideOuvertureRente.setNumeroSinistre(" ");
			}

        
        return courrierHybrideOuvertureRente;
    }

}
