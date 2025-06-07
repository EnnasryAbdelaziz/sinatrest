package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import eai.devass.gsr.appli.modele.metier.dossierRente.RentierHisto;

/**
 * Service d' ajout d' une entité
 * 
 * @author Nom Prenom (email)
 */
public class RentierHistoAjouterUC extends FacadeServiceUseCase {

	/**
	 * Methode qui execute le Use case d' ajout d' une entite
	 * 
	 * @param entite
	 *            L' objet à ajouter
	 * @param params
	 *            paramatere additionel qu 'on peut passer au Use Case
	 * @throws Exception
	 *             Probleme lors de l' execution du Use case
	 */

	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		// Ici je recupere l' Objet provenant de l' IHM
		RentierHisto lToCreate = (RentierHisto) this
				.getItem(RentierHisto.class);
		// Ici Je persiste l' entite Rentier au niveau de la base de donnees
		RentierHisto lCreated = (RentierHisto) lToCreate.getFactory()
				.newEntiteManager().createEntite(lToCreate);
		this.addResultItem(lCreated);
	}

	/**
	 * Methode pour activer le service de transaction
	 * 
	 * @returns soit true pour activer le service de transaction ou false pour
	 *          le desactiver
	 */
	public boolean isTransactionnal() {
		return true;
	}

	/**
	 * Methode pour activer le service de trace
	 * 
	 * @returns soit true pour activer le service de trace ou false pour le
	 *          desactiver
	 */
	public boolean isTracable() {
		return false;
	}

}