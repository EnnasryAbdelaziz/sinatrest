package eai.devass.gsr.appli.reglegestion.validation;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteException;

import org.apache.commons.beanutils.BeanUtilsBean;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.commun.appli.util.DateUtils;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtRemariage;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationDossierRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationEtatRentier;
import eai.devass.gsr.appli.modele.parametrage.TypeQuittance;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.MotifEtat;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;

@SuppressWarnings("all")
public class MvtRemariageRG extends BaseRG {

	private Rentier rentierBD = null;
	//private EtatRente etatRentierPrm = EtatRente.Valide_En_Cours;
	private EtatRente etatDossRentePrm = EtatRente.Valide_En_Cours;
	
	
	@ASkipRG(property="vofEnfants", value="true")
	public void regleGestion002QuittanceIndu(EntiteBO entiteBO, Map params)
			throws ExceptionMetier, EntiteException {

		MvtRemariage mouvement = (MvtRemariage) entiteBO;
		
		// Generation de la quittance !
		Double mntARegle = mouvement.getMntARegler();
		if(mntARegle < 0) {
			mouvement.setMntTropPercu(Math.abs(mntARegle));
			addInfoMessage("001", "Veuillez notifier le service recours pour " +
					"procéder à la récupération du trôp percu", params);
			return;
		}
		
		List<QuittanceGsr> listQuittanceGsr = mouvement.getRefsQuittance();
		if(commonGsrUtils.isEmpty(listQuittanceGsr)) {
			throw new ExceptionMetier("Merci de crée une quitatnce de réglement !!");
		}
		
		mouvement.setEmissionQuittance(true);
		
		// Montant indu
		QuittanceGsr quittanceGsr = listQuittanceGsr.get(0);		
		Double mntIndu = mouvement.getMntIndu();
		
		//Double mntTroisAnnuite = mouvement.getMntRachatAnnuitee();
		if(mntIndu == null || mntIndu.equals(0D)) {
			mouvement.setGenererQuittance(true);
			return;
		}
		
		// Generer une 2 quittance avec le montant mntIndu
		QuittanceGsr quittanceGsrIndu = new QuittanceGsr();
		try {
			BeanUtilsBean.getInstance().copyProperties(quittanceGsrIndu, quittanceGsr);
			
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de créer la quitatnce GSR du montant de l'indu !!!");
		}
		quittanceGsrIndu.setMontant(mntIndu);
		quittanceGsrIndu.setRefNatureQuittance(new NatureQtcGsr(
				NatureQuittance.Rembourssement.getId()));
		quittanceGsrIndu.setId(0);
		quittanceGsrIndu.setRefSituationQuittanceGsr(null);
		quittanceGsrIndu.setRefsHeritier(null);
		quittanceGsrIndu.setRefsProthese(null);
		quittanceGsrIndu.setRefTypeQuittance(new TypeQuittance(
				eai.devass.gsr.appli.prm.TypeQuittance.Recuperation.getId()));
		quittanceGsrIndu.setRefModeReglement(null);
		listQuittanceGsr.add(quittanceGsrIndu);
		
	}
	
	public void regleGestion003EtatRentier(EntiteBO entiteBO, Map params)
			throws ExceptionMetier, EntiteException {

		MvtRemariage mouvement = (MvtRemariage) entiteBO;
		
		// Etat de la rente
		Rentier rentier = mouvement.getRefRentier();
		SituationEtatRentier situationEtatRentier = rentier
				.getCurSituationEtatRentier(EtatRente.Remariage);
		situationEtatRentier.setOperateur(mouvement.getOperateur());
		getSession().save(situationEtatRentier);
		
		rentierBD = rentierManager.getRentierByID(rentier.getId());
		
		rentierBD.setDateEtat(mouvement.getDatEtat());

	}
	
	public void regleGestion004EtatDossierRente(EntiteBO entiteBO, Map params)
			throws ExceptionMetier, EntiteException {

		MvtRemariage mouvement = (MvtRemariage) entiteBO;
		Rentier rentier = mouvement.getRefRentier();
		DossierRente dossierRente = rentier.getRefDossierRente();
		if (!commonGsrUtils.isTrue(mouvement.getVofEnfants())) {
			// Il faut verifier si il ya d'autre Ayant Droit (actif) !!
			listREntierActif = getListRentierActif(dossierRente
					.getRefsRentier());
			if(commonGsrUtils.isEmpty(listREntierActif)) {
				etatDossRentePrm =  EtatRente.Cloture;
			} 
		} 

		// Etat de la rente
		SituationDossierRentier situationDossierRentier = dossierRente
				.getCurSituationDossierRentier(etatDossRentePrm, MotifEtat.Remariage);
		situationDossierRentier.setOperateur(mouvement.getOperateur());	
		getSession().save(situationDossierRentier);

	}
	
	public void regleGestion005QuittanceEquilibre(EntiteBO entiteBO, Map params)
		throws ExceptionMetier, EntiteException {
		
		MvtRemariage mouvement = (MvtRemariage) entiteBO;
				
		// Date de calcul de la qtc d'equilibre pour le rentier en cours = date remariage + 3ans
		// Pour la redistribution, c'est la date de remariage !!
		Calendar dateCalculREdistr = new GregorianCalendar();
		dateCalculREdistr.setTime(mouvement.getDatRemariage());		
		dateCalculREdistr.add(Calendar.YEAR, 3);
		Rentier rentier = mouvement.getRefRentier();
		Double coefAge = 0D;
		
		try {
			coefAge = transverseManager.getCoefficientAge(rentier, 
					dateCalculREdistr.getTime());
		
		} catch(ExceptionMetier e) {
			return;
		}
		
		Double mntEquilibre = rentier.getMontantRenteTrimestrielle() * 4 * coefAge;
		QuittanceGsr quittanceGsr = new QuittanceGsr();
		quittanceGsr.setMontant(-mntEquilibre);
		mouvement.addQuittanceEquilibre(quittanceGsr);	
		
		Calendar calRemariage = new GregorianCalendar();
		calRemariage.setTime(mouvement.getDatRemariage());
		mouvement.setDateCalculRedistribution(calRemariage);
		
	}

		
	
	
}
