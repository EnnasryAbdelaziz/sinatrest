package eai.devass.gsr.appli.reglegestion.validation;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.HibernateException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.gsr.appli.manager.metier.dossierRente.DossierRenteManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtConsignCNRA;
import eai.devass.gsr.appli.modele.metier.reglement.Prerglt;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.ModeReglement;
import eai.devass.gsr.appli.modele.parametrage.MotifConsignationCNRA;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationDossierRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationEtatRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.modele.parametrage.TypeCheque;
import eai.devass.gsr.appli.modele.parametrage.TypeQuittance;
import eai.devass.gsr.appli.modele.parametrage.TypeRgltGsr;
import eai.devass.gsr.appli.prm.CnraINfoCheque;
import eai.devass.gsr.appli.prm.EtatMouvement;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.MotifEtat;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.prm.TypeApprobation;
import eai.devass.gsr.appli.prm.TypeReglement;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.entites.IParam;

@SuppressWarnings("unchecked")
public class MvtConsignCNRARG extends BaseRG {
		
	private List<Rentier> listRentierDB;
	private List<Rentier> listRentierEC = new ArrayList<Rentier>();
	private MouvementRG mouvementRG = new MouvementRG();
	private List<QuittanceGsr> lisQuittanceGsrs;
	private QuittanceGsr quittanceGsrEquilibre;
	private Double mntCnra = 0D;
	private Double mntCalcule = 0D;
	private String errorRG001 = "Error regle Gestion 001 Etat Rentier";
	DossierRenteManager dossierRenteManager = (DossierRenteManager) ServicesFactory
			.getService(DossierRenteManager.class);
	SinistreManager sinistreManager = (SinistreManager) ServicesFactory
			.getService(SinistreManager.class);
	private Sinistre sinistreBD = null;
	private Long idSinistre = null;
	
	public void regleGestion001SetEtatRentier(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
	    ((Mouvement) entiteBO).setPersistObject(false);
		MvtConsignCNRA mouvement = null;
		try {
			mouvement = (MvtConsignCNRA) getSession().get(Mouvement.class,
					((MvtConsignCNRA)entiteBO).getId());
			
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de récuperer le mouvement !");
		}
		
		Rentier rentier = mouvement.getRefRentier();
		listRentierDB = transverseManager
				.getListRentierByRentier(String.valueOf(rentier.getId()));
		
		if(commonGsrUtils.isEmpty(listRentierDB)) {
			throw new ExceptionMetier("La liste des rentier est vide !!");
		}
		
		
		SituationEtatRentier situationEtatRentier = null;
		try {
			idSinistre = dossierRenteManager.doGetIdSinistre(listRentierDB
					.get(0).getRefDossierRente().getId());
		} catch (HibernateException e) {
			logger.error(errorRG001, e);
		} catch (ProcessEntiteException e) {
			logger.error(errorRG001, e);
		} catch (EntiteException e) {
			logger.error(errorRG001, e);
		} catch (PersistenceException e) {
			logger.error(errorRG001, e);
		}
		try {
			sinistreBD = sinistreManager.getSinistreById(idSinistre);
		} catch (FonctionnelleException e) {
			logger.error("Error sinistre BD", e);
		}
		
		for (Rentier curRentier : listRentierDB) {

			// Recuperer le rentier depuis la DB
			if(curRentier.getRefEtatRentier().getId() == 0) {
				continue;
			}
			
			if(curRentier.getRefEtatRentier().getId() != EtatRente.Valide_En_Cours.getCode()
								&& curRentier.getRefEtatRentier().getId() != EtatRente.Suspendue_Ou_Attente
								.getCode()) {
				continue;
			}
			
			if (!commonGsrUtils.isTrue(sinistreBD.getRefVictime().getDeces()) ) {
				if (Long.valueOf(0).equals(curRentier.getLienParente())) {
					listRentierEC.add(curRentier);
					break;

				}
			}
			else if (!Long.valueOf(0).equals(curRentier.getLienParente())) {
				listRentierEC.add(curRentier);
			}
			
		}
		
		for (Rentier curRentier : listRentierEC) {

			// Recuperer le rentier depuis la DB

					situationEtatRentier = curRentier
							.getCurSituationEtatRentier(EtatRente.Versee_CNRA);
					situationEtatRentier.setOperateur(entiteBO.getOperateur());
					getSession().save(situationEtatRentier);

		}
	}

	public void regleGestion002SetEtatRente(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		// Situation dossier rente
		Rentier rentier = listRentierEC.get(0);
		DossierRente dossierRente = rentier.getRefDossierRente();
		Long etatDossier = dossierRente.getRefEtatRentier().getId();
		
		if (EtatRente.Valide_En_Cours.getCode() != etatDossier) {
			dossierRente.setRefEtatRentier(new EtatRentier(
					EtatRente.Valide_En_Cours.getCode()));
		}
		
		MotifConsignationCNRA motif = new MotifConsignationCNRA();
		motif.setCode(MotifEtat.Attente_date_PEC_CNRA.getCode());
		List<SituationDossierRentier> situationsDossier = dossierRente
				.getRefSituationDossierRentier();
		
		if (situationsDossier == null) {
			situationsDossier = new ArrayList<SituationDossierRentier>();
		}
		
		if (addSituation(situationsDossier, MotifEtat.Attente_date_PEC_CNRA.getCode())) {
			SituationDossierRentier situationDossierRente = dossierRente.getCurSituationDossierRentier(
					EtatRente.Valide_En_Cours, MotifEtat.Attente_date_PEC_CNRA);
			
			situationDossierRente.setOperateur(entiteBO.getOperateur());
			getSession().save(situationDossierRente);
		}
	}

	
	public void regleGestion003CreationQuittance(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		try {
			MvtConsignCNRA curMvtConsignCNRA = null;
			List<QuittanceGsr> listNewQtc = null;
			lisQuittanceGsrs = new ArrayList<QuittanceGsr>();
			List<MvtConsignCNRA> listMvtConsignCNRA = null;
			QuittanceGsr curQuittanceGsr = null;
			SituationMouvement situationMouvement = null;
			for (Rentier curRentier : listRentierEC) {

				listMvtConsignCNRA = rentierManager
						.getLastMvtConsignationCnraByRentier(
								curRentier.getId(), new EtatMvt(
										EtatMouvement.Cree.getId()));
				if (commonGsrUtils.isEmpty(listMvtConsignCNRA)) {
					throw new ExceptionMetier(
							"Aucun mouvement ConsignCNRA trouvé pour le rentier : "
									+ curRentier.getId());
				}
				curMvtConsignCNRA = listMvtConsignCNRA.get(0);
				curQuittanceGsr = getQuittanceReglementCnra(curRentier,
						curMvtConsignCNRA);
				listNewQtc = new ArrayList<QuittanceGsr>();
				listNewQtc.add(curQuittanceGsr);
				curMvtConsignCNRA.setRefsQuittance(listNewQtc);

				// Quittance Mvt
				this.mouvementDB = curMvtConsignCNRA;
				setQuittanceGsr(curQuittanceGsr);
				lisQuittanceGsrs.add(curQuittanceGsr);

				// Total capiteaux
				mntCnra += curMvtConsignCNRA.getMntCNRA();
				mntCalcule += curMvtConsignCNRA.getCapitalCalcule();

				// Valider le mouvement
				situationMouvement = curMvtConsignCNRA
						.getCurSituationMouvement(EtatMouvement.Validee, null);
				situationMouvement.setOperateur(((Mouvement) entiteBO)
						.getOperateur());
				getSession().save(situationMouvement);

			}
		
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de ventiller le mouvement : "
					+ e.getMessage());
		}
		
	}
	
	
	public void regleGestion004GenererQuittanceEquilibre(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		// Recuperer le sinistre
		
		Sinistre sinistre = listRentierEC.get(0).getRefDossierRente()
				.getRefSinistre();
		Double reserveGrave = sinistre.getReserveGrave();
		if (reserveGrave == null) {
			throw new ExceptionMetier(IMessageException.EXP_RESERVE_GRAVE_NULL);
		}

		if (mntCalcule == null) {
			throw new ExceptionMetier(
					IMessageException.EXP_CAPITAL_CONSTITUF_NULL);
		}

		//	Si Capital CNRA à verser < Capital CNRA calculé alors quittance d’équilibre GSR vers AT avec 
		// le montant Capital CNRA à verser- Capital CNRA calculé
		Double diffCapital = mntCnra - mntCalcule;
		boolean generateQuittanceEquilibre = false;
		if(diffCapital < 0) {
			generateQuittanceEquilibre = true;			
		}
		
		// Si capital CNRA à verser > capital CNRA calculé :
		else if(diffCapital > 0) {
			if(reserveGrave >= diffCapital) {
				// Si Réserve grave>= capital CNRA à verser – capital CNRA calculé, alors quittance d’équilibre AT vers GSR 
				// avec le montant capital CNRA à verser – capital CNRA calculé. La réserve grave est déduite du même montant
				generateQuittanceEquilibre = true;
				sinistre.setReserveGrave(reserveGrave - diffCapital);
							
			} else {
				// Si Réserve grave < capital CNRA à verser – capital CNRA calculé, 
				// afficher « Réserve grave insuffisante, Impossible valider le mouvement »
				throw new ExceptionMetier("Réserve grave insuffisante, Impossible valider le mouvement");
			}
		}
		
		if(generateQuittanceEquilibre) {
			quittanceGsrEquilibre = new QuittanceGsr();
			quittanceGsrEquilibre.setMontant(diffCapital);//MFBO@changement augmentation --> dimunition(correction ano 333)
			setQuittanceEquilibre(quittanceGsrEquilibre, true);
			lisQuittanceGsrs.add(quittanceGsrEquilibre);
			
			
		}
	}
	
	@ASkipRG(property="lisQuittanceGsrs", isEmty="true", bean="this")
	public void regleGestion005SetEtatQuittanceEquilibre(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		// Generation des situations quittances !!!
		mouvementRG.setListQuittanceEmission(lisQuittanceGsrs);
		mouvementRG.regleGestion994SetEtatQuittance(entiteBO, params);
		
	}
	
	@ASkipRG(property="lisQuittanceGsrs", isEmty="true", bean="this")
	public void regleGestion006EmissionQuittanceGSR(EntiteBO entiteBO, 
			Map params) throws ExceptionMetier {
		
		mouvementRG.setListQuittanceEmission(lisQuittanceGsrs);
		mouvementRG.regleGestion995EmissionQuittanceGSR(entiteBO, params);
	}
	
	@ASkipRG(property="quittanceGsrEquilibre", isEmty="true", bean="this")
	public void regleGestion996EmissionQuittanceATEquilibre(EntiteBO entiteBO, 
			Map params) throws ExceptionMetier {
		
		try {
			QuittanceGsr quittanceAtForEmission = new QuittanceGsr();
			BeanUtilsBean.getInstance().copyProperties(
					quittanceAtForEmission, quittanceGsrEquilibre);
			quittanceAtForEmission.setNumeroQuittance(null);
			quittanceAtForEmission.setCodeServiceOrdonnateur(BENEF_GSR);
			quittanceAtForEmission.setBeneficiaire(BENEF_GSR);
			quittanceAtForEmission.setRaisonSociale(BENEF_GSR);
			quittanceAtForEmission
					.setCodeServiceOrdonnateur(IParam.CODE_SERVICE_ORDONNATEUR_AT);
			quittanceAtForEmission.setRefNatureQuittance(new NatureQtcGsr(
					quittanceGsrEquilibre.getNatureQuittanceEquilibreAT().getId()));
			
			List<QuittanceGsr> listQtcForEmission = new ArrayList<QuittanceGsr>();
			listQtcForEmission.add(quittanceAtForEmission);
			
			for (Rentier curRentier : listRentierDB) {
				if (mouvementDB.getRefRentier().getId() == curRentier.getId())
				{
					mouvementRG.setRentierDB(curRentier);
				}
			}
			
			mouvementDB.addQuittanceEquilibre(quittanceGsrEquilibre);
			mouvementRG.setMouvementDB(mouvementDB);
			
			for (Rentier curRentier : listRentierDB) {
				for (QuittanceGsr qtc : mouvementRG.getListQuittanceEmission()) {
					
					if(qtc.getRefMouvement().getId() == 0) {
						continue;
					}
					
					if(qtc.getBeneficiaire() == "AT") {
						continue;
					}
					
					if (qtc.getRefRentier().getId() == curRentier.getId()) {
						curRentier.setReserveMathematique(0D);
						break;
					}
				}
			}
			

			
			
			mouvementRG.setListQuittanceEquilibreEmission(listQtcForEmission) ;
			mouvementRG.regleGestion9921MAJCumuleSinistre(entiteBO, params);
			mouvementRG.regleGestion996EmissionQuittanceATEquilibre(entiteBO, params);
			
			//ajout regle de cumul prorata cnra
			
			
		
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de réemettre la quittance d'équilibre ");
		}
	}
	    
	private QuittanceGsr getQuittanceReglementCnra(Rentier curRentier, 
			MvtConsignCNRA mvtConsignCNRA) throws Exception {
		
		QuittanceGsr quittanceGsr = new QuittanceGsr();
		quittanceGsr.setDateCreation(new GregorianCalendar());
		TypeApprobation typeApprobation = TypeApprobation.Non_approuvee;
		if (curRentier.getApprobationQuittance() == null
				|| commonGsrUtils.isTrue(curRentier.getApprobationQuittance())) {
			typeApprobation = TypeApprobation.Approuvee;
		}
		quittanceGsr
				.setRefTypeApprobation(new eai.devass.gsr.appli.modele.parametrage.TypeApprobation(
						typeApprobation.getId()));
		quittanceGsr.setRefTypeQuittance(new TypeQuittance(
				eai.devass.gsr.appli.prm.TypeQuittance.Reglement.getId()));
		TypeRgltGsr typeRgltGsr = new TypeRgltGsr();
		typeRgltGsr.setId(TypeReglement.Direct.getId());
		quittanceGsr.setRefTypeReglement(typeRgltGsr);
		quittanceGsr.setRefNatureQuittance(new NatureQtcGsr(
				NatureQuittance.capital_constitutif_a_la_CNRA.getId()));
		quittanceGsr.setRefModeReglement(new ModeReglement(
				eai.devass.gsr.appli.prm.ModeReglement.Cheque.getId()));
		quittanceGsr.setBeneficiaire(CnraINfoCheque.POUR_LECOMPTEDE.getInfo());
		quittanceGsr.setRefRentier(curRentier);
		quittanceGsr.setMontant(mvtConsignCNRA.getMntCNRA());
		quittanceGsr.setRefMouvement(mvtConsignCNRA);
		quittanceGsr.setNumeroRente(String.valueOf(curRentier
				.getRefDossierRente().getNumeroRente()));
		quittanceGsr.setExercice(commonGsrUtils.getCurrentTrimestre());
		quittanceGsr.setClasse(String.valueOf(curRentier.getLienParente()));
		
		Prerglt prerglt = new Prerglt();
		prerglt.setAdresse(CnraINfoCheque.ADRESSE.getInfo());
		prerglt.setCodeVille(CnraINfoCheque.VILLE.getInfo());
		prerglt.setDateCreation(new GregorianCalendar());
		prerglt.setDetails(CnraINfoCheque.DETAIL_REGLEMENT.getInfo());
		prerglt.setPourLeCompte(CnraINfoCheque.POUR_LECOMPTEDE.getInfo());
		prerglt.setRefTypeCheque(new TypeCheque(eai.devass.gsr.appli.prm.TypeCheque.CNRA.getId()));
		quittanceGsr.setRefPrerglt(prerglt);
		getSession().save(prerglt);

		return quittanceGsr;
	}

	public QuittanceGsr getQuittanceGsrEquilibre() {
		return quittanceGsrEquilibre;
	}

	public List<QuittanceGsr> getLisQuittanceGsrs() {
		return lisQuittanceGsrs;
	}

	
	
	
	
	
	
}
