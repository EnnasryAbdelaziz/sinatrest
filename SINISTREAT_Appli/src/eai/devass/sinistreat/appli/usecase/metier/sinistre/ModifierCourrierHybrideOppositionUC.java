package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideOpposition;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideOppositionVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class ModifierCourrierHybrideOppositionUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		CourrierHybrideOpposition courrierOppositionHybride = (CourrierHybrideOpposition) this
				.getItem(CourrierHybrideOpposition.class);
		CourrierHybrideOpposition ChQResult = null;
		CourrierHybrideOppositionVO ChQResultVO = null;
		logger.info("Debut UC ModifierCourrierHybrideOppositionUC");
		try {
			ChQResult = (CourrierHybrideOpposition) parametrageManager.majCourrierHybridesOpposition(courrierOppositionHybride);
			//ChQResult = castFrom(ChQResult);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(ChQResult);
		logger.info("Fin UC ModifierCourrierHybrideOppositionUC");
	}

	public boolean isTransactionnal() {
		return true;
	}

	public CourrierHybrideOpposition castFrom(CourrierHybrideOpposition ChQResult){
		
		CourrierHybrideOpposition courrierHybrideOpposition= new CourrierHybrideOpposition();
			if(ChQResult.getIdLRH() != null) 
			{
				courrierHybrideOpposition.setIdLRH(ChQResult.getIdLRH());
			}
			else
			{
				courrierHybrideOpposition.setIdLRH(" ");
			}
			if(ChQResult.getNumeroSinistre() != null) 
			{
				courrierHybrideOpposition.setNumeroSinistre(ChQResult.getNumeroSinistre());
			}
			else
			{
				courrierHybrideOpposition.setNumeroSinistre(" ");
			}
			if(ChQResult.getNumeroGrave() != null) 
			{
				courrierHybrideOpposition.setNumeroGrave(ChQResult.getNumeroGrave());
			}
			else
			{
				courrierHybrideOpposition.setNumeroGrave(" ");
			}
			if(ChQResult.getNomClient() != null) 
			{
				courrierHybrideOpposition.setNomClient(ChQResult.getNomClient());
			}
			else
			{
				courrierHybrideOpposition.setNomClient(" ");
			}
			if(ChQResult.getDateAccident() != null) 
			{
				courrierHybrideOpposition.setDateAccident(ChQResult.getDateAccident());
			}
			else
			{
				courrierHybrideOpposition.setDateAccident(null);
			}
			
			if(ChQResult.getDateCreation() != null) 
			{
				courrierHybrideOpposition.setDateCreation(ChQResult.getDateCreation());
			}
			else
			{
				courrierHybrideOpposition.setDateCreation(null);
			}
			
			if(ChQResult.getNomCompagnieAdverse() != null) 
			{
				courrierHybrideOpposition.setNomCompagnieAdverse(ChQResult.getNomCompagnieAdverse());
			}
			else
			{
				courrierHybrideOpposition.setNomCompagnieAdverse(" ");
			}
			if(ChQResult.getAdresseCompagnieAdverse() != null) 
			{
				courrierHybrideOpposition.setAdresseCompagnieAdverse(ChQResult.getAdresseCompagnieAdverse());
			}
			else
			{
				courrierHybrideOpposition.setAdresseCompagnieAdverse(" ");
			}
			if(ChQResult.getImmatriculation() != null) 
			{
				courrierHybrideOpposition.setImmatriculation(ChQResult.getImmatriculation());
			}
			else
			{
				courrierHybrideOpposition.setImmatriculation(" ");
			}
			if(ChQResult.getMarque() != null) 
			{
				courrierHybrideOpposition.setMarque(ChQResult.getMarque());
			}
			else
			{
				courrierHybrideOpposition.setMarque(" ");
			}
			if(ChQResult.getNomConducteur() != null) 
			{
				courrierHybrideOpposition.setNomConducteur(ChQResult.getNomConducteur());
			}
			else
			{
				courrierHybrideOpposition.setNomConducteur(" ");
			}
			if(ChQResult.getGsm() != null) 
			{
				courrierHybrideOpposition.setGsm(ChQResult.getGsm());
			}
			else
			{
				courrierHybrideOpposition.setGsm(" ");
			}
			if(ChQResult.getVilleCompagnieAdverse() != null) 
			{
				courrierHybrideOpposition.setVilleCompagnieAdverse(ChQResult.getVilleCompagnieAdverse());
			}
			else
			{
				courrierHybrideOpposition.setVilleCompagnieAdverse(" ");
			}
			if(ChQResult.getPoliceCompagnieAdverse() != null) 
			{
				courrierHybrideOpposition.setPoliceCompagnieAdverse(ChQResult.getPoliceCompagnieAdverse());
			}
			else
			{
				courrierHybrideOpposition.setPoliceCompagnieAdverse(" ");
			}
			
			if(ChQResult.getDateGeneration() != null) 
			{
				courrierHybrideOpposition.setDateGeneration(ChQResult.getDateGeneration());
			}
			else
			{
				courrierHybrideOpposition.setDateGeneration(null);
			}
			if(ChQResult.getNomCompletVictime() != null) 
			{
				courrierHybrideOpposition.setNomCompletVictime(ChQResult.getNomCompletVictime());
			}
			else
			{
				courrierHybrideOpposition.setNomCompletVictime(null);
			}
        
        return courrierHybrideOpposition;
    }

}
