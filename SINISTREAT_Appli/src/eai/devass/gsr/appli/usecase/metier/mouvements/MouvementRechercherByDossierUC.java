package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.apache.commons.lang.StringUtils;

import eai.devass.gsr.appli.manager.metier.mouvements.MouvementManager;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.prm.EtatMouvement;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVO;

/**
 * Service de recherche d' une entité
 * 
 * @author Nom Prenom (email)
 */
public class MouvementRechercherByDossierUC extends FacadeServiceUseCase {

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

		MouvementManager mouvementManager = (MouvementManager) ServicesFactory
				.getService(MouvementManager.class);
		DossierRenteVO dossierRente = (DossierRenteVO) entite;
		if (dossierRente != null
				&& StringUtils.trimToNull(dossierRente.getId()) != null) {
			List<Mouvement> listeMvts = mouvementManager.getMouvementByDossier(
					dossierRente.getId(), EtatMouvement.Supprimer.getId(),dossierRente.getNumPage(),
					dossierRente.getPageSize());
			Long nbrResultat = mouvementManager
					.getNombreMouvementByDossier(dossierRente.getId(),EtatMouvement.Supprimer.getId());
			addResultItem(listeMvts);
			addResultItem(nbrResultat != null ? nbrResultat.intValue() : 0);
			addResultItem(dossierRente.getNumPage());
			addResultItem(dossierRente.getPageSize());
		}
	}

	/**
	 * Methode pour activer le service de transaction
	 * 
	 * @returns soit true pour activer le service de transaction ou false pour
	 *          le desactiver
	 */
	public boolean isTransactionnal() {
		return false;
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
