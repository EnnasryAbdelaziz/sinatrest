package eai.devass.gsr.appli.usecase.metier.dossierRente;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVOConverter;

/**
 * Converter de modification
 * 
 * @author Nom Prenom (email)
 */
public class RentierModifierUCConverter extends RentierVOConverter {
	/**
	 * Methode de validation de l 'object à modifier
	 * 
	 * @param vo
	 *            L 'objet à valider
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	protected void doValider(RentierVO vo) throws ValidationException {
	}

}
