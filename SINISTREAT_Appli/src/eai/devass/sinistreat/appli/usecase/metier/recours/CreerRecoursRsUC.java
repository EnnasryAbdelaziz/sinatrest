package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;

public class CreerRecoursRsUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		Recours recours = (Recours) this.getItem(Recours.class);
		RecoursVO recoursVO = (RecoursVO) entite;
		Recours recoursResult = null;
		try {

			/**
			 * wmos 26/11/2014: correction AnomaliDefect ID:50 "le SI doit
			 * permettre la création d'un recours sur un dossier desposant déjà
			 * d'un recours en cours"
			 */
			// Début correction
			
			// Boolean isRecoursEncours=
			// recoursManager.IsRecoursEncours(recours);
			// if(isRecoursEncours){
			// throw new FonctionnelleException(MSG_CREATION_RECOURS_ENCOURS);
			//
			// }else{
			// recoursResult = (Recours) recoursManager
			// .creerRecours(recours,recoursVO);
			//
			// }

			recoursResult = (Recours) recoursManager.creerRecours(recours,
					recoursVO);
			// Fin correction

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(recoursResult);

	}

	public boolean isTransactionnal() {
		return true;
	}

}
