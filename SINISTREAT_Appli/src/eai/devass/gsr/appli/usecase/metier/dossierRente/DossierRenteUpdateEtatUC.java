package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import eai.devass.gsr.appli.manager.metier.dossierRente.DossierRenteManager;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

/**
 * Service de update d' une entitÃ©
 * 
 * @author Nom Prenom (email)
 */
public class DossierRenteUpdateEtatUC extends FacadeServiceUseCase {

	/**
	 * Methode qui execute le use case de update
	 * 
	 * @param entite
	 *            l' objet Ã  update
	 * @param params
	 *            paramatere additionel qu 'on peut passer au Use Case
	 * @throws Exception
	 *             Probleme lors de l' execution du Use case
	 */

	@Override
	protected void doExecuter(IValueObject vo, HashMap param) throws Exception {
		RentierManager rentierManager = new RentierManager();
		DossierRente dossierToUpdate = (DossierRente) this
				.getItem(DossierRente.class);
		List<Rentier> rentierToUpdate = (List<Rentier>) dossierToUpdate
				.getRefsRentier();

		// traitement date du jour
		Format forma = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date d = Calendar.getInstance().getTime();
		String datjour = forma.format(d);
		int i = 0;
		for (Rentier cur : rentierToUpdate) {
			Rentier tmp = (Rentier) rentierManager.getEntite(cur);
			// verifier si le rentier est toujour en état "crée"
			if (tmp == null) {
				throw new FonctionnelleException("Rentier introuvable");
			}
			
			if (cur.getRefEtatRentier().getId() == 1 || cur.getRefEtatRentier().getId() == 4 || cur.getValidation() == true) {
				i++;
			}

		}

		dossierToUpdate = (DossierRente) new DossierRenteManager()
				.getEntite(dossierToUpdate);
		// MAJ etat dossier rente
		if (i == rentierToUpdate.size()) {
			dossierToUpdate.setRefEtatRentier(new EtatRentier(EtatRente.Cree.getCode()));
			dossierToUpdate.setValidation(false);
			dossierToUpdate.setDateEtat((TypeConverter.getInstance()
					.stringToCalendar("dd/MM/yyyy HH:mm:ss", datjour)));
			dossierToUpdate.getFactory().newEntiteManager()
					.modifyEntite(dossierToUpdate);
		}
		this.addResultItem(dossierToUpdate.getRefEtatRentier().getId());
		this.addResultItem(dossierToUpdate.getValidation());
		this.addResultItem(dossierToUpdate.getDateEtat());
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
