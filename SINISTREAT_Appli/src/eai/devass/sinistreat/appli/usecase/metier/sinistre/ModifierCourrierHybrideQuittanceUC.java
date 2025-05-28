package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideQuittance;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideQuittanceVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class ModifierCourrierHybrideQuittanceUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		CourrierHybrideQuittance courrierQuittanceHybride = (CourrierHybrideQuittance) this
				.getItem(CourrierHybrideQuittance.class);
		CourrierHybrideQuittance ChQResult = null;
		CourrierHybrideQuittanceVO ChQResultVO = null;
		logger.info("Debut UC ModifierCourrierHybrideQuittanceUC");
		try {
			ChQResult = (CourrierHybrideQuittance) parametrageManager.majCourrierHybridesQuittance(courrierQuittanceHybride);
			//ChQResult = castFrom(ChQResult);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(ChQResult);
		logger.info("Fin UC ModifierCourrierHybrideQuittanceUC");
	}

	public boolean isTransactionnal() {
		return true;
	}

	public CourrierHybrideQuittance castFrom(CourrierHybrideQuittance ChQResult){
		
		CourrierHybrideQuittance courrierHybrideQuittance= new CourrierHybrideQuittance();
			if(ChQResult.getIdLRH() != null) 
			{
				courrierHybrideQuittance.setIdLRH(ChQResult.getIdLRH());
			}
			else
			{
				courrierHybrideQuittance.setIdLRH(" ");
			}
			if(ChQResult.getNumeroSinistre() != null) 
			{
				courrierHybrideQuittance.setNumeroSinistre(ChQResult.getNumeroSinistre());
			}
			else
			{
				courrierHybrideQuittance.setNumeroSinistre(" ");
			}
			if(ChQResult.getNumeroGrave() != null) 
			{
				courrierHybrideQuittance.setNumeroGrave(ChQResult.getNumeroGrave());
			}
			else
			{
				courrierHybrideQuittance.setNumeroGrave(" ");
			}
			if(ChQResult.getNomClient() != null) 
			{
				courrierHybrideQuittance.setNomClient(ChQResult.getNomClient());
			}
			else
			{
				courrierHybrideQuittance.setNomClient(" ");
			}
			if(ChQResult.getDateAccident() != null) 
			{
				courrierHybrideQuittance.setDateAccident(ChQResult.getDateAccident());
			}
			else
			{
				courrierHybrideQuittance.setDateAccident(" ");
			}
			if(ChQResult.getVictime() != null) 
			{
				courrierHybrideQuittance.setVictime(ChQResult.getVictime());
			}
			else
			{
				courrierHybrideQuittance.setVictime(" ");
			}
			if(ChQResult.getNumeroQuittance() != null) 
			{
				courrierHybrideQuittance.setNumeroQuittance(ChQResult.getNumeroQuittance());
			}
			else
			{
				courrierHybrideQuittance.setNumeroQuittance(" ");
			}
			if(ChQResult.getInitiales() != null) 
			{
				courrierHybrideQuittance.setInitiales(ChQResult.getInitiales());
			}
			else
			{
				courrierHybrideQuittance.setInitiales(" ");
			}
			if(ChQResult.getNomUserCreateur() != null) 
			{
				courrierHybrideQuittance.setNomUserCreateur(ChQResult.getNomUserCreateur());
			}
			else
			{
				courrierHybrideQuittance.setNomUserCreateur(" ");
			}
			if(ChQResult.getNomBeneficiaireLettre() != null) 
			{
				courrierHybrideQuittance.setNomBeneficiaireLettre(ChQResult.getNomBeneficiaireLettre());
			}
			else
			{
				courrierHybrideQuittance.setNomBeneficiaireLettre(" ");
			}
			if(ChQResult.getAdresseBeneficiaireLettre() != null) 
			{
				courrierHybrideQuittance.setAdresseBeneficiaireLettre(ChQResult.getAdresseBeneficiaireLettre());
			}
			else
			{
				courrierHybrideQuittance.setAdresseBeneficiaireLettre(" ");
			}
			if(ChQResult.getVilleBeneficiaireLettre() != null) 
			{
				courrierHybrideQuittance.setVilleBeneficiaireLettre(ChQResult.getVilleBeneficiaireLettre());
			}
			else
			{
				courrierHybrideQuittance.setVilleBeneficiaireLettre(" ");
			}
			if(ChQResult.getNumeroDossierTribunal() != null) 
			{
				courrierHybrideQuittance.setNumeroDossierTribunal(ChQResult.getNumeroDossierTribunal());
			}
			else
			{
				courrierHybrideQuittance.setNumeroDossierTribunal(" ");
			}
			if(ChQResult.getLblJuridiction() != null) 
			{
				courrierHybrideQuittance.setLblJuridiction(ChQResult.getLblJuridiction());
			}
			else
			{
				courrierHybrideQuittance.setLblJuridiction(" ");
			}
			if(ChQResult.getDateDecision() != null) 
			{
				courrierHybrideQuittance.setDateDecision(ChQResult.getDateDecision());
			}
			else
			{
				courrierHybrideQuittance.setDateDecision(" ");
			}
			if(ChQResult.getLibellePrestation() != null) 
			{
				courrierHybrideQuittance.setLibellePrestation(ChQResult.getLibellePrestation());
			}
			else
			{
				courrierHybrideQuittance.setLibellePrestation(" ");
			}
			if(ChQResult.getMontant() != null) 
			{
				courrierHybrideQuittance.setMontant(ChQResult.getMontant());
			}
			else
			{
				courrierHybrideQuittance.setMontant(" ");
			}
			if(ChQResult.getIpp() != null) 
			{
				courrierHybrideQuittance.setIpp(ChQResult.getIpp());
			}
			else
			{
				courrierHybrideQuittance.setIpp(" ");
			}
        
        return courrierHybrideQuittance;
    }

}
