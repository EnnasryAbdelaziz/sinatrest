package eai.devass.gsr.appli.reglegestion.validation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.rmawatanya.reglement.application.metier.valueobjects.QuittanceVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.modele.IHistorisable;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.commun.appli.rg.ContextRegleGestion;
import eai.devass.commun.appli.rg.RegleGestionUtils;
import eai.devass.gsr.appli.manager.metier.transverse.CalculTransverses;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.IMvtSortant;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.TropPercu;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatMouvement;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.prm.TypeQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.services.IAppelService;
import eai.devass.services.ServicesExternes;
import eai.devass.services.impl.AppelServiceManager;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;


@SuppressWarnings("all")
public class MouvementRG extends BaseRG {

	private List<QuittanceGsr> listQuittanceEmission = new ArrayList<QuittanceGsr>();
	private List<QuittanceGsr> listQuittanceEquilibreEmission = null;
	public boolean contexteMvt = true;
	private List<Rentier> ayantsDroitActifs;
	
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
		
		// Verifier l etat du mouvement
		EtatMvt etatMvt = mouvementDB.getRefEtatMvt();
		if(etatMvt == null) {
			throw new ExceptionMetier("L'etat du mouvement ne peut être null !!!");
		}
		if(EtatMouvement.Validee.getId() == etatMvt.getId()) {
			throw new ExceptionMetier("Le mouvement est déja validé !!!");
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
		
		// Si le mouvement est deja valider !!!
		EtatMvt etatMvtDB = mouvementDB.getRefEtatMvt();
		if (etatMvtDB == null) {
			throw new ExceptionMetier("L'etat du mouvement est null !");
		}

		if (EtatMouvement.Validee.getId() == etatMvtDB.getId()) {
			throw new ExceptionMetier("Le mouvement est déja validé !!");
		}

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
		mouvementDB.setExecuteOnce(mouvement.getExecuteOnce());
		mouvementDB.setSommeMntQtcEquilibre(mouvement.getSommeMntQtcEquilibre());
		mouvementDB.setOperateur(mouvement.getOperateur());		
		map.put(mouvementDB, mouvementDB);
		try {
			RegleGestionUtils.getInstance().runRegleGestionObjet(map);
			mouvement.setEmissionQuittance(mouvementDB.getEmissionQuittance());
			mouvement.setDateCalculRedistribution(mouvementDB.getDateCalculRedistribution());
			mouvement.setMntTropPercu(mouvementDB.getMntTropPercu());
			params.put("outMapMessage", map.get("outMapMessage"));
			
		} catch (InvocationTargetException e) {
			throw new ExceptionMetier(e.getCause().getMessage());
			
		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

		// Definir l'etat du mouvement
		SituationMouvement situationMouvement = mouvementDB
				.getCurSituationMouvement(EtatMouvement.Validee, motifSituationEtat);
		situationMouvement.setOperateur(entiteBO.getOperateur());
		getSession().save(situationMouvement);

	}

	
	/**
	 * Mettre à jour les reserves mathématique & prothèse pour les mouvement de sortie
	 * « Echu », « Consignation CNRA», « Décès », « Suppression » , « Remariage » et "Rachat".
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetiers
	 */

	public void regleGestion800MAJReservesGSR(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

		try {
		
			if(mouvementDB instanceof IMvtSortant)
			{
				rentierDB.setReserveMathematique(0D);
				List<Prothese> listProthese = rentierDB.getRefsProtheses();
						if(listProthese != null && listProthese.size()>0){
					for(Prothese prothese:listProthese)
					{	
						prothese.setReserveProthese(0D);
					}
				}

			}
		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

	}
	
	
	@ASkipRG(property="emissionQuittance", value="false", isEmty="true")
	public void regleGestion991PrepareEmissionQuittanceGsr(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

		Mouvement mouvement = (Mouvement) entiteBO;
		
		// Recuperer la quittanceGSR
		try {
			listQuittanceEmission = mouvementDB.getRefsQuittance();
			if (commonGsrUtils.isEmpty(listQuittanceEmission)) {
				throw new ExceptionMetier(
						"Aucune quittances trouvées pour emission,du mouvement[id:"
								+ mouvement.getId() + "]");
			}

			for (QuittanceGsr quittanceGsr : listQuittanceEmission) {
				if (!CommonGsrUtils.isBlank(quittanceGsr.getNumeroQuittance())) {
					throw new ExceptionMetier("La quittance du mouvement["
							+ mouvement.getId() + "] est déja emise");
				}
				
				// Le montant de la quittance ne peut etre <= 0
				if(quittanceGsr.getMontant() == null || quittanceGsr.getMontant() < 0){
					throw new ExceptionMetier("Le montant de la quittance ne peut être inférieure ou égale à 0");
				}

				// Completetr la quittance par les info d'emission
				setQuittanceGsr(quittanceGsr);
			}

		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

	}

	@ASkipRG(property="dateCalculRedistribution", isEmty="true")
	public void regleGestion9920Redistribution(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		Mouvement mouvement = (Mouvement) entiteBO;
		// 25/03/2014 : redistribution seulement dans le cas ou la somme des taux est > 85 !!
		try {
			// Recuperer le rentier
			List<Rentier> listRentier = rentierDB.getRefDossierRente().getRefsRentier();			
			if (commonGsrUtils.isEmpty(listRentier) || listRentier.size() == 1) {
				return;
			}
	
			ayantsDroitActifs = getListRentierActif(listRentier);
			if(ayantsDroitActifs.isEmpty()) {
				return;
			}
			
			// Calculer la nouvelle rente pour les renties 
			transverseManager.calculerNouvelleRente(ayantsDroitActifs);

		} catch (Exception e) {
			throw new ExceptionMetier("Probléme lors du calcul de la redistribution : "
							+ e.getMessage());
		}

		Double mntEquilibreRentier = 0D;
		CalculTransverses calculTransverses = new CalculTransverses();
		for (Rentier ayantDroit : ayantsDroitActifs) {
			mntEquilibreRentier = 0D;
			
			// EVO QC 438 : si le rentier n'a pas de coeff age, ne pas crrer des QTC equiibre!
			try {
				mntEquilibreRentier = calculTransverses.calculerMntEquilibreParAyantDroit(
						ayantDroit, mouvement.getDateCalculRedistribution());
			
			} catch(ExceptionMetier e) {
				continue;
			}

			// Faire appelle quittance d'équilibre
			if(mntEquilibreRentier.equals(0D)) {
				continue;
			}
			
			QuittanceGsr quittanceGsr = new QuittanceGsr();
			quittanceGsr.setRefRentier(ayantDroit);
			quittanceGsr.setMontant(mntEquilibreRentier);
			mouvementDB.addQuittanceEquilibre(quittanceGsr);
		}
	}
	
	// EVO : Si sous type de quittance= diminution ==> diminuer le cumul règlement exercice et cout sinistre du montant de la quittance
	// Si sous type de quittance= virement ou augmentation ==> augmenter le cumul règlement exercice et cout sinistre du montant de la quittance
	@ASkipRG(property="mouvementDB.listQtcEquilibre", isEmty="true", bean="this")
	public void regleGestion9921MAJCumuleSinistre(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

		if (contexteMvt == true){
		Mouvement mouvement = (Mouvement) entiteBO;		
		}
		// Recuperer la quittanceGSR
		try {
			List<QuittanceGsr> listQtcEquilibre = mouvementDB.getListQtcEquilibre();
						
			// Calculer la somme des mnt d'equilibres
			double mntEquilibre = 0;
			NatureQtcGsr natureQuittance = null;
			for(QuittanceGsr curQuittanceGsr : listQtcEquilibre) {
				natureQuittance = curQuittanceGsr.getRefNatureQuittance();
				if(natureQuittance != null && natureQuittance.getId() != 0) {
					if (NatureQuittance.Augmentation_Capital_constitutif
							.getId() == natureQuittance.getId()) {
						mntEquilibre += curQuittanceGsr.getMontant();

					} else if (NatureQuittance.Diminution_Capital_constitutif
							.getId() == natureQuittance.getId()) {
						mntEquilibre -= Math.abs(curQuittanceGsr.getMontant());
						
					} else if (NatureQuittance.Virement_Capital_constitutif
							.getId() == natureQuittance.getId()) {
						mntEquilibre += curQuittanceGsr.getMontant();
					}
				}
			
				else {				
					mntEquilibre += curQuittanceGsr.getMontant();
				}
			}
			
			if(mntEquilibre == 0) {
				return;
			}
			
			//boolean isAugmentationGsr = (mntEquilibre > 0) ? true : false;
			Sinistre sinistre = getSinistre();
			sinistre.setCumulReglementAnne(sinistre.getCumulReglementAnne() + mntEquilibre);
			sinistre.setUserModificateur(entiteBO.getOperateur());
			//WMOS: 12/11/2015 add date modification 
			sinistre.setDateModification(new Date());
			// Mouvement de modification du sinistre
			try {
				sinistreManager.gsrCreerMouvement(sinistre,
						ContextRegleGestion.MODIFICATION.getContext(), 
						IConstantes.MOTIF_GSR_CHANGEMENT_CULULEREGLEMENT);
				
			} catch (FonctionnelleException e) {
				throw new ExceptionMetier("Impossible de mettre à jour le cumule réglement" +
							", Un problème est survenue lors de la journalisation.");
			}
			

		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

	}
	
	@ASkipRG(property="mouvementDB.listQtcEquilibre", isEmty="true", bean="this")
	public void regleGestion993QuittanceEquilibre(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		List<QuittanceGsr> listQtcEquilibre = mouvementDB.getListQtcEquilibre();
		listQuittanceEquilibreEmission = new ArrayList<QuittanceGsr>();
		NatureQuittance natureQtcAT = null;
		QuittanceGsr quittanceAtForEmission = null;
		

		try {
			for (QuittanceGsr quittanceGsr : listQtcEquilibre) {
				if (quittanceGsr == null) {
					continue;
				}

				quittanceGsr.setOperateur(entiteBO.getOperateur());
				setQuittanceEquilibre(quittanceGsr, contexteMvt);				
				natureQtcAT = quittanceGsr.getNatureQuittanceEquilibreAT();
				
				// Seulement pour l'emission de la quittance comptable
				quittanceAtForEmission = new QuittanceGsr();
				BeanUtilsBean.getInstance().copyProperties(
						quittanceAtForEmission, quittanceGsr);
				quittanceAtForEmission.setBeneficiaire(BENEF_GSR);
				quittanceAtForEmission.setRaisonSociale(BENEF_GSR);
				quittanceAtForEmission
						.setCodeServiceOrdonnateur(IParam.CODE_SERVICE_ORDONNATEUR_AT_GRAVE);
				
				// Les codes prestation sont different entre la GSR et AT
				quittanceAtForEmission.setRefNatureQuittance(new NatureQtcGsr(
						natureQtcAT.getId()));
				quittanceAtForEmission.setCodePrestation(natureQtcAT.getRubrique());
				listQuittanceEquilibreEmission.add(quittanceAtForEmission);
				listQuittanceEmission.add(quittanceGsr);
			}

		} catch (InvocationTargetException e) {
			throw new ExceptionMetier(
					"Impossible de constuire les quittance d'équilibres !!");
			
		} catch (IllegalAccessException e) {
				throw new ExceptionMetier(
						"Impossible de constuire les quittance d'équilibres !!");
			}
	}
	
	@ASkipRG(property="mntDiminutionRsvGrave", isEmty="true")
	public void regleGestion9940(EntiteBO entiteBO, Map params) throws ExceptionMetier {

		Mouvement mouvement = (Mouvement) entiteBO;
		Sinistre sinistre = getSinistre();		
		sinistre.setReserveGrave(sinistre.getReserveGrave()
				- mouvement.getMntDiminutionRsvGrave());
		
		try {
			sinistreManager.gsrCreerMouvement(sinistre,
					ContextRegleGestion.MODIFICATION.getContext(), 
					IConstantes.MOTIF_GSR_CHANGEMENT_RESERVEGRAVE);
			
		} catch (FonctionnelleException e) {
			throw new ExceptionMetier("Impossible de mettre à jour la reserve grave" +
						", Un problème est survenue lors de la journalisation.");
		}

	}

	@ASkipRG(property="listQuittanceEmission", isEmty="true", bean="this")
	public void regleGestion994SetEtatQuittance(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		// Generation des situations quittances !!!
		SituationQuittanceGsr situationQuittanceGsr = null;
		EtatQuittance etatQuittance = EtatQuittance.En_instance;
		for (QuittanceGsr quittanceGsr : listQuittanceEmission) {
			etatQuittance = EtatQuittance.En_instance;
			
			// C'est une quittance d'equilibre
			if (quittanceGsr.getQuittanceAT() != null) {
				getSession().save(quittanceGsr.getQuittanceAT());
				etatQuittance = EtatQuittance.Reglee;
			}
			
			// Ou c'est une quittance recuperation
			if(TypeQuittance.Recuperation.getId() == quittanceGsr.getRefTypeQuittance().getId()) {
				etatQuittance = EtatQuittance.Reglee;
			}
			
			situationQuittanceGsr = quittanceGsr
				.getCurSituationQuittanceGsr(etatQuittance);			
			situationQuittanceGsr.setOperateur(entiteBO.getOperateur());
			getSession().saveOrUpdate(quittanceGsr);
			getSession().save(situationQuittanceGsr);
		}
		
		// Flush session
		getSession().flush();
	}

	@ASkipRG(property="listQuittanceEmission", isEmty="true", bean="this")
	public void regleGestion995EmissionQuittanceGSR(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

		// Emission Quittance comptable
		IAppelService appelService = new AppelServiceManager();
		QuittanceVO quittanceRes = null;
		for (QuittanceGsr quittanceGsr : listQuittanceEmission) {
			quittanceRes = (QuittanceVO) appelService.appelService(
					ServicesExternes.EMISSION_QUITTANCE,
					(EntiteBO) quittanceGsr, "1");

			// Set num quittance
			quittanceGsr.setNumeroQuittance(quittanceRes.getNumQuittance());
			
			// Generer le mouvement de reglement pour la quittance d'equilibre
			if(quittanceGsr.getQuittanceAT() != null) {
				commonGsrUtils.genearteMouvementReglement(quittanceGsr);
			}
		}

	}

	@ASkipRG(property="listQuittanceEquilibreEmission", isEmty="true", bean="this")
	public void regleGestion996EmissionQuittanceATEquilibre(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

		// Emission Quittance comptable
		IAppelService appelService = new AppelServiceManager();
		QuittanceVO quittanceRes = null;
		for (QuittanceGsr quittanceGsr : listQuittanceEquilibreEmission) {
			quittanceRes = (QuittanceVO) appelService.appelService(
					ServicesExternes.EMISSION_QUITTANCE,
					(EntiteBO) quittanceGsr, "1");

			// Set num quittance
			try {
				quittanceManager.updateNumQuittanceReglemen(quittanceGsr
						.getQuittanceAT().getId(), quittanceRes.getNumQuittance());
				
			} catch(Exception e) {
				throw new ExceptionMetier("Impossible de mettre à jour le numéro de quittance d'équilibre (AT) : " 
						+ quittanceRes.getNumQuittance() + " pour le réglement : " + quittanceGsr
						.getQuittanceAT().getId());
			}
		}
	}
	
	@ASkipRG(property="mntTropPercu", isEmty="true")
	public void regleGestion9980TropPercu(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

		Mouvement mouvement = (Mouvement) entiteBO;
		TropPercu tropPercu = new TropPercu();
		tropPercu.setMntTropPercu(mouvement.getMntTropPercu());
		tropPercu.setDateOperation(new Date());
		tropPercu.setRefMouvement(mouvement);
		tropPercu.setRefRentier(rentierDB);
		getSession().save(tropPercu);

	}
	

	public void regleGestion9981HistoriserMouvement(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

		Mouvement mouvement = (Mouvement) entiteBO;		
		mouvement.setContextRegleGestion(ContextRegleGestion.VALIDATION.getContext());
		
		try {
			transverseManager.historiser((IHistorisable)mouvement, 1);
			
		} catch (Exception e) {
			throw new ExceptionMetier("Erreur lors de l'historisation du mouvement !!");
		}

	}
	
	@ASkipRG(property="ayantsDroitActifs", isEmty="true", bean="this")
	public void regleGestion9982HistoriserRentier(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

		Rentier rentierClone = null;
		for(Rentier curRentier : ayantsDroitActifs) {
			if (curRentier.getIppTauxRente().equals(
					curRentier.getTauxAncienneIpp())) {
				continue;
			}
			
			// IL faut historiser le rentier
			try {
				rentierClone = (Rentier) BeanUtilsBean.getInstance().cloneBean(curRentier);
				rentierClone.setOperateur(entiteBO.getOperateur());
				rentierClone.setIppTauxRente(curRentier.getTauxAncienneIpp());
				rentierClone.setMontantRenteTrimestrielle(curRentier.getMntAncienneRente());
				rentierClone.setContextRegleGestion(ContextRegleGestion.VALIDATION.getContext());
				transverseManager.historiser(rentierClone, 1);
				
			}  catch(Exception e) {
				throw new ExceptionMetier("Impossible d'historiser le rentier : "
								+ e.getMessage());
			}
		}

	}
	
	
	
	public List<QuittanceGsr> getListQuittanceEmission() {
		return listQuittanceEmission;
	}

	public List<QuittanceGsr> getListQuittanceEquilibreEmission() {
		return listQuittanceEquilibreEmission;
	}

	public void setContexteMvt(boolean contexteMvt) {
		this.contexteMvt = contexteMvt;
	}

	public List<Rentier> getAyantsDroitActifs() {
		return ayantsDroitActifs;
	}

	public void setListQuittanceEmission(List<QuittanceGsr> listQuittanceEmission) {
		this.listQuittanceEmission = listQuittanceEmission;
	}

	public void setListQuittanceEquilibreEmission(
			List<QuittanceGsr> listQuittanceEquilibreEmission) {
		this.listQuittanceEquilibreEmission = listQuittanceEquilibreEmission;
	}
	
	private Sinistre getSinistre() throws ExceptionMetier {
		
		if(mouvementDB.getRefRentier().getRefDossierRente() == null) {
			throw new ExceptionMetier("Impossible de mettre à jour la reserve grave" +
					", le dossier sinistre est obligatoire");
		}
		
		Sinistre sinistre = mouvementDB.getRefRentier().getRefDossierRente()
				.getRefSinistre();
		if(sinistre == null) {
			throw new ExceptionMetier("Impossible de mettre à jour la reserve grave" +
					", le dossier sinistre est obligatoire");
		}
		
		return sinistre;
	}
	
	

}
