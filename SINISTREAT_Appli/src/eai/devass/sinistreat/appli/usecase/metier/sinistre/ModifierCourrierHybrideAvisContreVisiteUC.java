package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideAvisContreVisite;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideAvisContreVisiteVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class ModifierCourrierHybrideAvisContreVisiteUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		CourrierHybrideAvisContreVisite courrierAvisContreVisiteHybride = (CourrierHybrideAvisContreVisite) this
				.getItem(CourrierHybrideAvisContreVisite.class);
		CourrierHybrideAvisContreVisite ChQResult = null;
		CourrierHybrideAvisContreVisiteVO ChQResultVO = null;
		logger.info("Debut UC ModifierCourrierHybrideReclamationUC");
		try {
			ChQResult = (CourrierHybrideAvisContreVisite) parametrageManager.majCourrierHybridesAvisContreVisite(courrierAvisContreVisiteHybride);
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

	public CourrierHybrideAvisContreVisite castFrom(CourrierHybrideAvisContreVisite ChQResult){
		
		CourrierHybrideAvisContreVisite courrierHybrideAvisContreVisite= new CourrierHybrideAvisContreVisite();
			if(ChQResult.getIdLRH() != null) 
			{
				courrierHybrideAvisContreVisite.setIdLRH(ChQResult.getIdLRH());
			}
			else
			{
				courrierHybrideAvisContreVisite.setIdLRH(" ");
			}
			if(ChQResult.getNomPrestataire() != null) 
			{
				courrierHybrideAvisContreVisite.setNomPrestataire(ChQResult.getNomPrestataire());
			}
			else
			{
				courrierHybrideAvisContreVisite.setNomPrestataire(" ");
			}
			if(ChQResult.getNomClient() != null) 
			{
				courrierHybrideAvisContreVisite.setNomClient(ChQResult.getNomClient());
			}
			else
			{
				courrierHybrideAvisContreVisite.setNomClient(" ");
			}
			if(ChQResult.getAdressePrestataire() != null) 
			{
				courrierHybrideAvisContreVisite.setAdressePrestataire(ChQResult.getAdressePrestataire());
			}
			else
			{
				courrierHybrideAvisContreVisite.setAdressePrestataire(" ");
			}
			if(ChQResult.getDateAccident() != null) 
			{
				courrierHybrideAvisContreVisite.setDateAccident(ChQResult.getDateAccident());
			}
			else
			{
				courrierHybrideAvisContreVisite.setDateAccident(null);
			}
			
			if(ChQResult.getDateCreation() != null) 
			{
				courrierHybrideAvisContreVisite.setDateCreation(ChQResult.getDateCreation());
			}
			else
			{
				courrierHybrideAvisContreVisite.setDateCreation(null);
			}
			
			if(ChQResult.getNumeroSinistre() != null) 
			{
				courrierHybrideAvisContreVisite.setNumeroSinistre(ChQResult.getNumeroSinistre());
			}
			else
			{
				courrierHybrideAvisContreVisite.setNumeroSinistre(" ");
			}
			if(ChQResult.getTypeLettre() != null) 
			{
				courrierHybrideAvisContreVisite.setTypeLettre(ChQResult.getTypeLettre());
			}
			else
			{
				courrierHybrideAvisContreVisite.setTypeLettre(" ");
			}
			if(ChQResult.getHeureContreVisite() != null) 
			{
				courrierHybrideAvisContreVisite.setHeureContreVisite(ChQResult.getHeureContreVisite());
			}
			else
			{
				courrierHybrideAvisContreVisite.setHeureContreVisite(" ");
			}
			if(ChQResult.getNomVictime() != null) 
			{
				courrierHybrideAvisContreVisite.setNomVictime(ChQResult.getNomVictime());
			}
			else
			{
				courrierHybrideAvisContreVisite.setNomVictime(" ");
			}
			if(ChQResult.getAdresseVictime() != null) 
			{
				courrierHybrideAvisContreVisite.setAdresseVictime(ChQResult.getAdresseVictime());
			}
			else
			{
				courrierHybrideAvisContreVisite.setAdresseVictime(null);
			}
		
		

        
        return courrierHybrideAvisContreVisite;
    }

}
