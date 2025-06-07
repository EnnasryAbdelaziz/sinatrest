package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.manager.metier.dossierRente.ProthesesManager;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtProtheseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese;

/**
 * Service d' ajout d' une entitÃ©
 * 
 * @author Nom Prenom (email)
 */
public class MvtProtheseAjouterUC extends MouvementAjouterUC {

	/**
	 * Methode qui execute le Use case d' ajout d' une entite
	 * 
	 * @param entite
	 *            L' objet Ã  ajouter
	 * @param params
	 *            paramatere additionel qu 'on peut passer au Use Case
	 * @throws Exception
	 *             Probleme lors de l' execution du Use case
	 */
	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		// Ici je recupere l' Objet provenant de l' IHM
		MvtProthese lToCreate = (MvtProthese) this.getItem(MvtProthese.class);
		// verifier l'etat du rentier
		Rentier rentier = lToCreate.getRefRentier();
		if (rentier == null){
			throw new EntiteException("Rentier introuvable");
		}
		RentierManager rentierManager = new RentierManager();
		rentier = (Rentier) rentierManager.getEntite(rentier);
		if (rentier == null){
			throw new EntiteException("Rentier introuvable");
		}
		if (!rentier.getValidation()){
			throw new EntiteException(
					"La rente du rentier n'a pas été encore validé");
		}
		MvtProtheseManager mvtProtheseManager = new MvtProtheseManager();
		Integer numProthese = lToCreate.getNumProthese();
		lToCreate.setNumProthese(null);

		// verifier le non redandance du prothese
		List<MvtProthese> list1 = mvtProtheseManager.lookupEntite(lToCreate);
		if (list1 != null && !list1.isEmpty()){
			throw new EntiteException("Mouvement Prothèse dont le numero "
					+ numProthese + " déja existe");
		}

		Prothese prothese = new Prothese();
		prothese.setRefCentreProthese(lToCreate.getRefCentreProthese());
		prothese.setDateProthese(lToCreate.getDatMvtProthese());
		prothese.setRefNatureProthese(lToCreate.getRefNatureProthese());
		prothese.setRefRentier(lToCreate.getRefRentier());
		prothese.setMontantEstime(lToCreate.getVofEstimatif());
		prothese.setMontantProthese(lToCreate.getMntMvtProthese());

		List<Prothese> list2 = (new ProthesesManager())
				.lookupEntite(prothese);
		if (list2 != null && !list2.isEmpty()){
			throw new EntiteException("Prothèse dont le numero " + numProthese
					+ "existe déja");
		}

		lToCreate.setNumProthese(numProthese);
		// Ici Je persiste l' entite MvtProthese au niveau de la base de donnees
		mvtProtheseManager.createEntite(lToCreate);
		this.creatQtc(lToCreate);
		// this.addResultItem(lCreated);
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