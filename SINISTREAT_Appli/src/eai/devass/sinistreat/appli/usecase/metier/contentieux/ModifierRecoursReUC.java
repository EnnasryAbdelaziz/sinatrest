
package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.contentieux.converters.RecoursVOConverter;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;

public class ModifierRecoursReUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		Recours recoursSortant=(Recours)this.getItems(Recours.class);
		RecoursVOConverter recoursConverter = new RecoursVOConverter();
		RecoursVO recoursVO = (RecoursVO) recoursConverter.sinistreToRecoursEntrant(entite);
		Recours recoursResult = null;
		try {
			recoursResult = (Recours) contentieuxManager
					.modifierRecours(recoursVO,recoursSortant);

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
