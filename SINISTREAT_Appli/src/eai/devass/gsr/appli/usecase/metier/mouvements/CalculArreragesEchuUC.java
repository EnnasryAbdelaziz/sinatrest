package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.Calendar;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.manager.metier.transverse.CalculTransverses;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtRenteEchue;
import eai.devass.gsr.appli.utile.IMessageException;

/**
 * 
 * @author elfaismo
 *
 */
public class CalculArreragesEchuUC  extends FacadeServiceUseCase {

	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);

		RentierManager rentierManager = (RentierManager) ServicesFactory
				.getService(RentierManager.class);

		CalculTransverses calculTransverse = new CalculTransverses();

		MvtRenteEchue mouvement = (MvtRenteEchue) this
				.getItem(MvtRenteEchue.class);

		if (mouvement != null) {
			Rentier rentier = mouvement.getRefRentier();

			rentier = rentierManager.getRentierByID(rentier.getId());
			
			
			Calendar dateCalcul = null;
			if(mouvement.getDateFinScolarite() != null){
				dateCalcul = mouvement.getDateFinScolarite();
			}else{
				dateCalcul = mouvement.getDateMariage();
			}
		
			Calendar dateDerniereEcheance = mouvement.getEcheanceEffective();
			if (rentier == null || rentier.getId() <= 0) {
				throw new ExceptionMetier(
						IMessageException.EXP_RENTIER_INTROUVALE);
			}

			if (dateCalcul == null) {
				throw new ExceptionMetier(IMessageException.EXP_DATE_CALCUL_NULL);
			}

			Double tropPercu = 0.0D;
			Double prorata = 0.0D;
			
			tropPercu = (Double) transverseManager.sommeQuittancesReglees(
					rentier.getId(), dateCalcul);

			prorata = calculTransverse.calculerProrata(
					rentier.getMontantRenteTrimestrielle(),
					dateDerniereEcheance, dateCalcul);

			Double mntARegle = prorata - tropPercu;

			mouvement.setMntTropPercu(tropPercu);
			mouvement.setMntProrata(prorata);
			mouvement.setMntARegle(mntARegle);
		}

		this.addResultItem(mouvement);
	}

}
