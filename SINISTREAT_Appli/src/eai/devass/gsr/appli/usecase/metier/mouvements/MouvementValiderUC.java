package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.utile.DateUtile;
import eai.devass.gsr.appli.businessrule.MvtComplementCNRARg;
import eai.devass.gsr.appli.businessrule.MvtConsignCNRARg;
import eai.devass.gsr.appli.businessrule.MvtDecesRentierRg;
import eai.devass.gsr.appli.businessrule.MvtDefautRemiseRg;
import eai.devass.gsr.appli.businessrule.MvtEnInstanceRg;
import eai.devass.gsr.appli.businessrule.MvtProrataCNRARg;
import eai.devass.gsr.appli.businessrule.MvtProtheseRg;
import eai.devass.gsr.appli.businessrule.MvtRachatRg;
import eai.devass.gsr.appli.businessrule.MvtRcptCertifRg;
import eai.devass.gsr.appli.businessrule.MvtRecuperationRg;
import eai.devass.gsr.appli.businessrule.MvtRemiseEnCoursRg;
import eai.devass.gsr.appli.businessrule.MvtRenteEchueRg;
import eai.devass.gsr.appli.businessrule.MvtSuppressionRg;
import eai.devass.gsr.appli.businessrule.MvtSuspensionRg;
import eai.devass.gsr.appli.manager.metier.mouvements.MouvementManager;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;

/**
 * Service d'annulation d' une entitÃ©
 * 
 * @author Nom Prenom (email)
 */
public class MouvementValiderUC extends FacadeServiceUseCase {

	/**
	 * Methode qui execute le use case d'annulation d' un objet
	 * 
	 * @param entite
	 *            l' objet à  annuler
	 * @param params
	 *            paramatere additionel qu 'on peut passer au Use Case
	 * @throws Exception
	 *             Probleme lors de l' execution du Use case
	 **/
	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {
		// récupération de l'objet Mouvement provenant de l'HIM
		Mouvement lToUpdate = (Mouvement) this.getItem(Mouvement.class);
		MouvementManager MvtManager = new MouvementManager();
		// verifier l'existence de l'entité au niveau de la Db
		lToUpdate = (Mouvement) MvtManager.getEntite(lToUpdate);
		if (lToUpdate == null) {
			throw new EntiteException("Mouvement introuvable");
		}
		// verifier l'etat initial du mouvement
		EtatMvt etatMvt = lToUpdate.getRefEtatMvt();
		if (etatMvt == null || etatMvt.getId() != 1) {
			throw new EntiteException("Impossible de Modifier l'état de l'entité Mouvement");
		}
		
		// verifier l'état du rentier
		
		String codMvt = lToUpdate.getRefTypeMouvement().getCode().trim();

		if ("GSR_MVTDEFAUTREMISE".equals(codMvt)) {
			( new MvtDefautRemiseRg()).validerMvt(lToUpdate);
		} else if ("GSR_MVTRCPTCERTIF".equals(codMvt)) {
			(new MvtRcptCertifRg()).validerMvt(lToUpdate);
		} else if ("GSR_MVTCONSIGNCNRA".equals(codMvt)) {
			(new MvtConsignCNRARg()).validerMvt(lToUpdate);
		} else if ("GSR_MVTRACHAT".equals(codMvt)) {
			(new MvtRachatRg()).validerMvt(lToUpdate);
		} else if ("GSR_MVTDECESRENTIER".equals(codMvt)) {
			(new MvtDecesRentierRg()).validerMvt( lToUpdate);
		} else if ("GSR_MVTREMARIAGE".equals(codMvt)) {
			(new MvtDecesRentierRg()).validerMvt(lToUpdate);
		} else if ("GSR_MVTRENTEECHUE".equals(codMvt)) {
			( new MvtRenteEchueRg()).validerMvt(lToUpdate);
		} else if ("GSR_MVTREMISEENCOURS".equals(codMvt)) {
			(new MvtRemiseEnCoursRg()).validerMvt( lToUpdate);
		} else if ("GSR_MVTRECUPERATION".equals(codMvt)) {
			(new MvtRecuperationRg()).validerMvt( lToUpdate);
		} else if ("GSR_MVTSUSPENSION".equals(codMvt)) {
			(new MvtSuspensionRg()).validerMvt( lToUpdate);
		} else if ("GSR_MVTSUPPRESSION".equals(codMvt)) {
			(new MvtSuppressionRg()).validerMvt( lToUpdate);
		} else if ("GSR_MAJCAPITAL".equals(codMvt)) {
			(new MvtSuppressionRg()).validerMvt( lToUpdate);
		} else if ("GSR_MVTPROTHESE".equals(codMvt)) {
			(new MvtProtheseRg()).validerMvt( lToUpdate);
		} else if ("GSR_MVTENINSTANCE".equals(codMvt)) {
			(new MvtEnInstanceRg()).validerMvt( lToUpdate);
		} else if ("GSR_MVTCOMPLEMENTCNRA".equals(codMvt)) {
			(new MvtComplementCNRARg()).validerMvt( lToUpdate);
		} else if ("GSR_MVTPRORATACNRA".equals(codMvt)) {
			(new MvtProrataCNRARg()).validerMvt( lToUpdate);
		}
		
		// mettre l'etat de l'entité en etat validé ainsi ajouter la dates de la
		// validation
		etatMvt = new EtatMvt();
		etatMvt.setId(2);
		lToUpdate.setRefEtatMvt(etatMvt);
		lToUpdate.setDatEtat(DateUtile.dateCalendarCourante());
		Mouvement lUpdated = (Mouvement) MvtManager.modifyEntite(lToUpdate);
		this.addResultItem(lUpdated);
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
	/**
	 * Methode qui retourne le lock mode du Use Case
	 * 
	 * @returns le lock mode
	 */
	// public int getLockMode() {
	// return LOCKMODE_UNLOCK;
	// }
}
