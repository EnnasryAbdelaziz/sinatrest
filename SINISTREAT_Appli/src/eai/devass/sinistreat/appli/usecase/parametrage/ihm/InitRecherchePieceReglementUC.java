package eai.devass.sinistreat.appli.usecase.parametrage.ihm;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class InitRecherchePieceReglementUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		// inisialisation de la page de creation des instructions support
		try {
			List listPieceReglement = (List) parametrageManager.getAllPieceReglement();

			this.addResultItem(listPieceReglement);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
	}

}