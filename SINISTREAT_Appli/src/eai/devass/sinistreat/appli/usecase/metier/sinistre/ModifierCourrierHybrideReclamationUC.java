package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideReclamation;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideReclamationVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class ModifierCourrierHybrideReclamationUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		CourrierHybrideReclamation courrierReclamationHybride = (CourrierHybrideReclamation) this
				.getItem(CourrierHybrideReclamation.class);
		CourrierHybrideReclamation ChQResult = null;
		CourrierHybrideReclamationVO ChQResultVO = null;
		logger.info("Debut UC ModifierCourrierHybrideReclamationUC");
		try {
			logger.info("Debut Methode majCourrierHybridesReclamation");
			ChQResult = (CourrierHybrideReclamation) parametrageManager.majCourrierHybridesReclamation(courrierReclamationHybride);
			logger.info("Fin Methode majCourrierHybridesReclamation");

			//ChQResult = castFrom(ChQResult);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(ChQResult);
		logger.info("Fin UC ModifierCourrierHybrideReclamationUC");
	}

	public boolean isTransactionnal() {
		return true;
	}

	public CourrierHybrideReclamation castFrom(CourrierHybrideReclamation ChQResult){
		
		CourrierHybrideReclamation courrierHybrideReclamation= new CourrierHybrideReclamation();
			if(ChQResult.getIdLRH() != null) 
			{
				courrierHybrideReclamation.setIdLRH(ChQResult.getIdLRH());
			}
			else
			{
				courrierHybrideReclamation.setIdLRH(" ");
			}
			if(ChQResult.getNumeroSinistre() != null) 
			{
				courrierHybrideReclamation.setNumeroSinistre(ChQResult.getNumeroSinistre());
			}
			else
			{
				courrierHybrideReclamation.setNumeroSinistre(" ");
			}
			if(ChQResult.getNumeroGrave() != null) 
			{
				courrierHybrideReclamation.setNumeroGrave(ChQResult.getNumeroGrave());
			}
			else
			{
				courrierHybrideReclamation.setNumeroGrave(" ");
			}
			if(ChQResult.getNomClient() != null) 
			{
				courrierHybrideReclamation.setNomClient(ChQResult.getNomClient());
			}
			else
			{
				courrierHybrideReclamation.setNomClient(" ");
			}
			if(ChQResult.getDateAccident() != null) 
			{
				courrierHybrideReclamation.setDateAccident(ChQResult.getDateAccident());
			}
			else
			{
				courrierHybrideReclamation.setDateAccident(null);
			}
			
			if(ChQResult.getDateCreation() != null) 
			{
				courrierHybrideReclamation.setDateCreation(ChQResult.getDateCreation());
			}
			else
			{
				courrierHybrideReclamation.setDateCreation(null);
			}
			
			if(ChQResult.getNomBeneficiaireLettre() != null) 
			{
				courrierHybrideReclamation.setNomBeneficiaireLettre(ChQResult.getNomBeneficiaireLettre());
			}
			else
			{
				courrierHybrideReclamation.setNomBeneficiaireLettre(" ");
			}
			if(ChQResult.getAdressBeneficiaireLettre() != null) 
			{
				courrierHybrideReclamation.setAdressBeneficiaireLettre(ChQResult.getAdressBeneficiaireLettre());
			}
			else
			{
				courrierHybrideReclamation.setAdressBeneficiaireLettre(" ");
			}
			
			if(ChQResult.getVilleBeneficiaireLettre() != null) 
			{
				courrierHybrideReclamation.setVilleBeneficiaireLettre(ChQResult.getVilleBeneficiaireLettre());
			}
			else
			{
				courrierHybrideReclamation.setVilleBeneficiaireLettre(" ");
			}
			if(ChQResult.getPiece() != null) 
			{
				courrierHybrideReclamation.setPiece(ChQResult.getPiece());
			}
			else
			{
				courrierHybrideReclamation.setPiece(" ");
			}
			
			if(ChQResult.getvRef() != null) 
			{
				courrierHybrideReclamation.setvRef(ChQResult.getvRef());
			}
			else
			{
				courrierHybrideReclamation.setvRef(" ");
			}
			if(ChQResult.getNomUserCreateur() != null) 
			{
				courrierHybrideReclamation.setNomUserCreateur(ChQResult.getNomUserCreateur());
			}
			else
			{
				courrierHybrideReclamation.setNomUserCreateur(" ");
			}
			if(ChQResult.getNomVictime() != null) 
			{
				courrierHybrideReclamation.setNomVictime(ChQResult.getNomVictime());
			}
			else
			{
				courrierHybrideReclamation.setNomVictime(" ");
			}
			if(ChQResult.getGsm() != null) 
			{
				courrierHybrideReclamation.setGsm(ChQResult.getGsm());
			}
			else
			{
				courrierHybrideReclamation.setGsm(" ");
			}
			if(ChQResult.getDateGeneration() != null) 
			{
				courrierHybrideReclamation.setDateGeneration(ChQResult.getDateGeneration());
			}
			else
			{
				courrierHybrideReclamation.setDateGeneration(null);
			}
			
			
        
        return courrierHybrideReclamation;
    }

}
