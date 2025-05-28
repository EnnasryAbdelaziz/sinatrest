package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitCreateReglementIntermediaireUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		// TODO Auto-generated method stub
		Reglement reglement= (Reglement) this.getItem(Reglement.class);
		try {			


		
			List listEtatSinistre = (List)parametrageManager.getListEtatSinistreSansInstance(null);
			this.addResultItem(listEtatSinistre);
			List listeChoixRubrique=(List)parametrageManager.getlistPrestation(null);
			this.addResultItem(listeChoixRubrique);
			Sinistre sin = (Sinistre)sinistreManager.getSinistreById(reglement.getRefSinistre().getId());
			this.addResultItem(sin);
			Reglement reg = new Reglement();
			reg.setRefSinistre(sin);
			double cumulQuittanceEmise = reglementManager.getCumulQuittanceEmise(reg);
			reg.setRefSinistre(null);
			reg.setMontant(cumulQuittanceEmise);
			this.addResultItem(reg);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
			
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		
	}

}
