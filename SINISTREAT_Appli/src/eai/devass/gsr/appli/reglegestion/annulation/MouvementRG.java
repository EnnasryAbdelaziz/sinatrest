package eai.devass.gsr.appli.reglegestion.annulation;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rmawatanya.reglement.application.metier.usecases.services.quittance.IVariableQuittance;
import com.rmawatanya.reglement.application.metier.valueobjects.MouvementQuittanceVO;
import com.rmawatanya.reglement.application.metier.valueobjects.QuittanceVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.RegleGestionUtils;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.AnnulationMvt;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationDossierRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatMouvement;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.MotifEtat;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.services.IAppelService;
import eai.devass.services.ServicesExternes;
import eai.devass.services.impl.AppelServiceManager;
import eai.devass.sinistreat.appli.utils.IConstantes;

/** @author kchakib */

@SuppressWarnings("all")
public class MouvementRG extends BaseRG {

	private boolean breakRG = false;
	private QuittanceGsr quittanceGsr = null;
	private IAppelService appelService = new AppelServiceManager();
	private List<QuittanceGsr> listQuittanceGsr = null;
	
	
	public void regleGestion000RunRegleGestion(EntiteBO entiteBO, Map params)
	throws ExceptionMetier {

		Mouvement mouvement = (Mouvement) entiteBO;
		long idMouvement = mouvement.getId();
		if (idMouvement == 0) {
			throw new ExceptionMetier("L'identifiant du mouvement ne peut pas être null !!");
		}
		
		try {
			mouvementDB = (Mouvement) getSession().get(Mouvement.class,
					idMouvement);
			
			if (mouvementDB == null) {
				throw new ExceptionMetier("Mouvement non trouvé !!");
			}
		
		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}
		
		// Recupere le rentier depuis la BD
		rentierDB = mouvementDB.getRefRentier();
		
		if(rentierDB == null) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}
	}
	
	
	public void regleGestion001RunRegleGestion(EntiteBO entiteBO, Map params)
		throws ExceptionMetier {
	
		Mouvement mouvement = (Mouvement) entiteBO;
		mouvement.setPersistObject(false);
		
		// Dans le cas ou la RG specifique du mouvement est non implementer,
		// risque d'un traitement cyclique!!
		if (mouvementDB.equals(mouvement)) {
			throw new ExceptionMetier(
					"Il faut définir la RG validation associé au mouvement : "
							+ mouvementDB.getClass().getSimpleName());
		}
		
		Map map = new HashMap();
		mouvementDB.setContextRegleGestion(mouvement
				.getContextRegleGestionEnum().getContext());
		map.put(mouvementDB, mouvementDB);
		if (params.get("outMapMessage") != null
				&& map.get("outMapMessage") == null) {
			map.put("outMapMessage", params.get("outMapMessage"));
		}
		try {
			RegleGestionUtils.getInstance().runRegleGestionObjet(map);
		
		
		} catch (InvocationTargetException e) {
			throw new ExceptionMetier(e.getCause().getMessage());
		
		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

	}

	public void regleGestion002getQuittanceGsr(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		try {
			Mouvement mouvement = (Mouvement) entiteBO;
			quittanceGsr = quittanceManager
					.getLastQuittanceByMouvement(mouvement);
			if (quittanceGsr == null) {
				breakRG = true;
				return;
			}

			// Si la quittance est non emise
			if (quittanceGsr.getNumeroQuittance() == null) {
				breakRG = true;
				return;
			}

		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

	}

	public void regleGestion003VerifyEtatQuittance(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		if (breakRG) {
			return;
		}

		appelService.setConvert(false);
		QuittanceVO quittanceVO = new QuittanceVO();
		quittanceVO.setNumQuittance(quittanceGsr.getNumeroQuittance());
		List<QuittanceVO> listQtc = (List<QuittanceVO>) appelService
				.appelService(ServicesExternes.CHERCHER_QUITTANCE, quittanceVO,
						"1");

		if (commonGsrUtils.isEmpty(listQtc)) {
			throw new ExceptionMetier(
					"Aucune quittance trouvée dans le module "
							+ "quittancement dont le num:["
							+ quittanceGsr.getNumeroQuittance() + "]");
		}

		QuittanceVO resQuittanceVO = listQtc.get(0);
		if (IVariableQuittance.ETAT_QTC_REGLEE.equals(resQuittanceVO
				.getEtatQuittance())) {
			throw new ExceptionMetier(
					"Imposible d'annuler le mouvement,la dérniere "
							+ "quittance associée est déja reglée["
							+ quittanceGsr.getNumeroQuittance() + "]");
		}

	}

	public void regleGestion004AnnulerMouvement(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		Mouvement mouvement = (Mouvement) entiteBO;

		// Recuperer le mouvement
		Mouvement mouvementDB = (Mouvement) getSession().get(Mouvement.class,
				mouvement.getId());
		if (mouvementDB == null) {
			throw new ExceptionMetier(
					"Impossible de récupérer le mouvement id["
							+ mouvement.getId() + "]");
		}
		EtatMvt etatMvt = new EtatMvt(EtatMouvement.Annulee.getId());
		etatMvt.setDateCreation(new GregorianCalendar());
		mouvementDB.setRefEtatMvt(etatMvt);
		mouvementDB.setDatEtat(new GregorianCalendar());
		mouvement.setPersistObject(false);
		// Situatrion mouvement
		List<SituationMouvement> list = mouvement.getRefSituationMouvement();
		SituationMouvement situationMouvement = null;
		if (!commonGsrUtils.isEmpty(list)) {
			situationMouvement = list.get(0);

		} else {
			situationMouvement = new SituationMouvement();
		}

		situationMouvement.setOperateur(entiteBO.getOperateur());
		situationMouvement.setRefEtatMvt(etatMvt);
		situationMouvement.setDateEtat(new Date());
		situationMouvement.setRefMouvement(mouvement);
		// situationMouvement.setRefMotifSituationEtat(mouvement.getMotifSituationEtat());
		getSession().save(situationMouvement);
	//	getSession().flush();

	}

	public void regleGestion005AnnulerQuittanceGSR(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		Mouvement mouvement = (Mouvement) entiteBO;
		// Recuperer le mouvement
		Mouvement mouvementDB = (Mouvement) getSession().get(Mouvement.class,
				mouvement.getId());
		listQuittanceGsr = mouvementDB.getRefsQuittance();
		if (commonGsrUtils.isEmpty(listQuittanceGsr)) {
			return;
		}
		for (QuittanceGsr qtc : listQuittanceGsr) {
			SituationQuittanceGsr situationQuittanceGsr = qtc
					.getCurSituationQuittanceGsr(EtatQuittance.Annulee);
			situationQuittanceGsr.setOperateur(entiteBO.getOperateur());
			getSession().save(situationQuittanceGsr);
		}
		//getSession().flush();

	}

	public void regleGestion006Quittance(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		if (breakRG) {
			return;
		}

		// Dans le cas d'un rejet, il faut mettre à jour la quittance via un mvt
		// d'annulation
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date = dateFormat.format(new Date());
		MouvementQuittanceVO mouvementQuittanceVO = new MouvementQuittanceVO();
		mouvementQuittanceVO.setNumQuittance(quittanceGsr.getNumeroQuittance());
		mouvementQuittanceVO.setMotifEtat("Annulation");
		mouvementQuittanceVO.setDatEtat(date);
		// mouvementQuittanceVO.setRefReglement(refReglement);
		// mouvementQuittanceVO.setModReglement(IModePaiement.MISE_A_DISPOSITION);

		// Appel de service
		appelService.setConvert(false);
		appelService.appelService(ServicesExternes.ANNULER_QUITTANCE,
				mouvementQuittanceVO, IConstantes.PROFIL_ANNULATION);

	}

	public void regleGestion007SetEtatRente(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
		Mouvement mouvement = (Mouvement) entiteBO;
		Mouvement mouvementDB = (Mouvement) getSession().get(Mouvement.class,
				mouvement.getId());
		if (mouvementDB == null) {
			throw new ExceptionMetier(
					"Impossible de récupérer le mouvement id["
							+ mouvement.getId() + "]");
		}
		EtatRentier etatRentier = new EtatRentier(
				EtatRente.Valide_En_Cours.getCode());
		// Situation dossier rente
		Long etatDossier = mouvementDB.getRefRentier().getRefDossierRente()
				.getRefEtatRentier().getId();
		if (!etatDossier.equals(EtatRente.Valide_En_Cours.getCode())) {
			mouvementDB.getRefRentier().getRefDossierRente()
					.setRefEtatRentier(new EtatRentier(
							EtatRente.Valide_En_Cours.getCode()));
		}
		AnnulationMvt motif = new AnnulationMvt();
		motif.setCode(MotifEtat.Annulation_Mvt.getCode());
		List<SituationDossierRentier> situationsDossier = mouvementDB
				.getRefRentier().getRefDossierRente()
				.getRefSituationDossierRentier();
		if (situationsDossier == null) {
			situationsDossier = new ArrayList<SituationDossierRentier>();
		}
		if (addSituation(situationsDossier,MotifEtat.Annulation_Mvt.getCode())) {
			SituationDossierRentier situationDossierRente = new SituationDossierRentier();
			situationDossierRente.setRefEtatRentier(etatRentier);
			situationDossierRente.setRefDossierRente(mouvementDB.getRefRentier()
					.getRefDossierRente());
			situationDossierRente.setDateEtat(new Date());
			situationDossierRente.setOperateur(entiteBO.getOperateur());
			situationDossierRente.setRefMotifSituationEtat(motif);
			getSession().save(situationDossierRente);
			situationsDossier.add(situationDossierRente);
		}
		mouvementDB.getRefRentier().getRefDossierRente()
				.setRefSituationDossierRentier(situationsDossier);
	}

//	public void nonregleGestion003AnnulerMvtReglementQuittance(
//			EntiteBO entiteBO, Map params) throws ExceptionMetier {
//
//		if (breakRG) {
//			return;
//		}
//
//		// Dans le cas d'un rejet, il faut mettre à jour la quittance via un mvt
//		// d'annulation
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		String date = dateFormat.format(new Date());
//		MouvementQuittanceVO mouvementQuittanceVO = new MouvementQuittanceVO();
//		mouvementQuittanceVO.setNumQuittance(quittanceGsr.getNumeroQuittance());
//		mouvementQuittanceVO.setMotifEtat("Annulation");
//		mouvementQuittanceVO.setDatEtat(date);
//		// mouvementQuittanceVO.setRefReglement(refReglement);
//		mouvementQuittanceVO.setModReglement(IModePaiement.MISE_A_DISPOSITION);
//
//		// Appel de service
//		appelService.setConvert(false);
//		appelService.appelService(
//				ServicesExternes.GENERATION_MOUVEMENT_QUITTANCE,
//				mouvementQuittanceVO, "1");
//
//	}
}
