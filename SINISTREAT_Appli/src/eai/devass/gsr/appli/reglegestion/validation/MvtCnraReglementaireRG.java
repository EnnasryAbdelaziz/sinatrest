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
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtCnraReglementaire;
import eai.devass.gsr.appli.modele.metier.reglement.Prerglt;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.ModeReglement;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationEtatRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.modele.parametrage.TypeCheque;
import eai.devass.gsr.appli.modele.parametrage.TypeQuittance;
import eai.devass.gsr.appli.modele.parametrage.TypeRgltGsr;
import eai.devass.gsr.appli.prm.CnraINfoCheque;
import eai.devass.gsr.appli.prm.EtatMouvement;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.prm.TypeApprobation;
import eai.devass.gsr.appli.prm.TypeReglement;
import eai.devass.gsr.appli.prm.TypeRevision;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.entites.IParam;

@SuppressWarnings("unchecked")
public class MvtCnraReglementaireRG extends BaseRG {

	private List<Rentier> listRentierDB;
	private List<Rentier> listRentierEC = new ArrayList<Rentier>();
	private MouvementRG mouvementRG = new MouvementRG();
	private List<QuittanceGsr> lisQuittanceGsrs;
	private QuittanceGsr quittanceGsrEquilibre;
	private Double mntCnra = 0D;
	private Double mntCalcule = 0D;
	private Double mntRenteDifferentielle = 0D;
	private Double mntRente = 0D;
	
	private String errorRG001 = "Error regle Gestion 001 Etat Rentier";
	DossierRenteManager dossierRenteManager = (DossierRenteManager) ServicesFactory
			.getService(DossierRenteManager.class);
	SinistreManager sinistreManager = (SinistreManager) ServicesFactory
			.getService(SinistreManager.class);
	private Sinistre sinistreBD = null;
	private Long idSinistre = null;
	private SituationEtatRentier situationEtatRentier = null;
	private String typeConsignation = null;
	private String typeRevision = null;


	/**
	 * Mise à jour etat Rentier = Versé
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
		
	public void regleGestion001SetEtatRentier(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
	    ((Mouvement) entiteBO).setPersistObject(false);
	    MvtCnraReglementaire mouvement = null;
	    List<MvtCnraReglementaire> listMvtCnraReglementaire = null;
	    MvtCnraReglementaire curMvtCnraReglementaire = null;
	    
		try {
			mouvement = (MvtCnraReglementaire) getSession().get(Mouvement.class,((MvtCnraReglementaire)entiteBO).getId());			
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de récuperer le mouvement !");
		}
		
		Rentier rentier = mouvement.getRefRentier();
		listRentierDB = transverseManager.getListRentierByRentier(String.valueOf(rentier.getId()));
		
		if(commonGsrUtils.isEmpty(listRentierDB)) {
			throw new ExceptionMetier("La liste des rentier est vide !!");
		}
		
		
		SituationEtatRentier situationEtatRentier = null;
		try {
			idSinistre = dossierRenteManager.doGetIdSinistre(listRentierDB.get(0).getRefDossierRente().getId());
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
			
			// Si etat du rentier est en cours ou suspendue
			if(curRentier.getRefEtatRentier().getId() != EtatRente.Valide_En_Cours.getCode()&& curRentier.getRefEtatRentier().getId() != EtatRente.Suspendue_Ou_Attente.getCode()
					&& curRentier.getRefEtatRentier().getId() != EtatRente.Versee_CNRA.getCode()) {
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
					situationEtatRentier = curRentier.getCurSituationEtatRentier(EtatRente.Versee_CNRA);
					situationEtatRentier.setOperateur(entiteBO.getOperateur());
					getSession().save(situationEtatRentier);
					
			// Diminuer le mnt de la rente le montant de la rente « gsr_rentier » est mis à jour par la valeur 		
			try {
				listMvtCnraReglementaire = rentierManager.getLastMvtCnraReglementaireByRentier(curRentier.getId(),new EtatMvt(EtatMouvement.Cree.getId()));
				if (commonGsrUtils.isEmpty(listMvtCnraReglementaire)) {
					throw new ExceptionMetier("Aucun mouvement CNRA Reglementaire trouvé pour le rentier : "+ curRentier.getId());
				}
				curMvtCnraReglementaire = listMvtCnraReglementaire.get(0);
				mntRente = curRentier.getMontantRenteTrimestrielle();
				mntRenteDifferentielle = curMvtCnraReglementaire.getMntRente();
				typeConsignation = curMvtCnraReglementaire.getRefTypeConsignation();
				typeRevision = curMvtCnraReglementaire.getRefTypeRevision();
				if (typeConsignation.equals(eai.devass.gsr.appli.prm.TypeConsignation.Revision.getId()) && typeRevision != null) {
					if (typeRevision.equals(TypeRevision.Attenuation.getId())
							|| typeRevision.equals(TypeRevision.Revision_B
									.getId())) {

						curRentier.setMontantRenteTrimestrielle(mntRente - mntRenteDifferentielle);
					} else {
						curRentier.setMontantRenteTrimestrielle(mntRente + mntRenteDifferentielle);
					}
				}
						
					} catch (Exception e) {
						throw new ExceptionMetier("Impossible de ventiller le mouvement : "+ e.getMessage());
					}
		}
	}

	/**
	 * Creation quittance : Génerer pour chaque rentier la quittance du versement du capital CNRA
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	public void regleGestion002CreationQuittance(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		try {
			MvtCnraReglementaire curMvtCnraReglementaire = null;
			List<QuittanceGsr> listNewQtc = null;
			lisQuittanceGsrs = new ArrayList<QuittanceGsr>();
			List<MvtCnraReglementaire> listMvtCnraReglementaire = null;
			QuittanceGsr curQuittanceGsr = null;
			SituationMouvement situationMouvement = null;
			
			
			for (Rentier curRentier : listRentierEC) {

				listMvtCnraReglementaire = rentierManager.getLastMvtCnraReglementaireByRentier(curRentier.getId(),new EtatMvt(EtatMouvement.Cree.getId()));
				if (commonGsrUtils.isEmpty(listMvtCnraReglementaire)) {
					throw new ExceptionMetier("Aucun mouvement CNRA Reglementaire trouvé pour le rentier : "+ curRentier.getId());
				}
				curMvtCnraReglementaire = listMvtCnraReglementaire.get(0);
				typeConsignation = curMvtCnraReglementaire.getRefTypeConsignation();
				typeRevision = curMvtCnraReglementaire.getRefTypeRevision();
				
				if(typeConsignation.equals(eai.devass.gsr.appli.prm.TypeConsignation.Nouvelle_Liquidation.getId())
						|| typeRevision.equals(TypeRevision.Aggravation.getId()) 
						|| typeRevision.equals(TypeRevision.Revision_H.getId()))
				{			 	
				curQuittanceGsr = getQuittanceReglementCnra(curRentier,curMvtCnraReglementaire);
				listNewQtc = new ArrayList<QuittanceGsr>();
				listNewQtc.add(curQuittanceGsr);
				// Quittance Mvt
				curMvtCnraReglementaire.setRefsQuittance(listNewQtc);
				this.mouvementDB = curMvtCnraReglementaire;
				setQuittanceGsr(curQuittanceGsr);
				lisQuittanceGsrs.add(curQuittanceGsr);
				
				}
				
				// Total capiteaux (pour le cas de revision la reserve rentier = 0 )
				if(typeConsignation.equals(eai.devass.gsr.appli.prm.TypeConsignation.Revision.getId()))
				{
		           mntCnra += curMvtCnraReglementaire.getMntCNRA();
				}else {					
					mntCnra += curMvtCnraReglementaire.getMntCNRA();
					mntCalcule += curMvtCnraReglementaire.getCapitalCalcule();
				}
				// Montant rente diff
				mntRenteDifferentielle += curMvtCnraReglementaire.getMntRente();
				// Valider le mouvement
				situationMouvement = curMvtCnraReglementaire.getCurSituationMouvement(EtatMouvement.Validee, null);
				situationMouvement.setOperateur(((Mouvement) entiteBO).getOperateur());
				getSession().save(situationMouvement);
			}		
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de ventiller le mouvement : "+ e.getMessage());
		}
		
	}
		/**
		 * Génerer quittance d'équilibre : quittance diminution ou augmentation capital
		 * @param entiteBO
		 * @param params
		 * @throws ExceptionMetier
		 */
	public void regleGestion003GenererQuittanceEquilibre(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		// Recuperer le sinistre
		boolean generateQuittanceEquilibre = false;
		Sinistre sinistre = listRentierEC.get(0).getRefDossierRente().getRefSinistre();
		Double reserveGrave = sinistre.getReserveGrave();
		Double mntQuittance = Double.valueOf(0) ; 
		NatureQuittance natureQtcGsr = NatureQuittance.Augmentation_Capital_constitutif;
		
		if (reserveGrave == null) {
			throw new ExceptionMetier(IMessageException.EXP_RESERVE_GRAVE_NULL);
		}

		if (mntCalcule == null) {
			throw new ExceptionMetier(
					IMessageException.EXP_CAPITAL_CONSTITUF_NULL);
		}
		// mntCnra = capital net    , mntCalcule = reserve Rentier
		Double diffCapital_ = mntCnra - mntCalcule;
		Double diffCapital = mntCalcule- mntCnra;	
		/* Cas 1 : type consignation = Nouvelle liquidation */
		if(typeConsignation.equals(eai.devass.gsr.appli.prm.TypeConsignation.Nouvelle_Liquidation.getId()))
		{

			mntQuittance = diffCapital;
			//	Si Capital CNRA à verser < Capital CNRA calculé alors quittance d’équilibre GSR vers AT avec 
			// le montant Capital CNRA à verser- Capital CNRA calculé		
			if(diffCapital > 0) {
				generateQuittanceEquilibre = true;			
			}
			
			// Si capital CNRA à verser < capital CNRA calculé :
			else if(diffCapital < 0) {
				if(reserveGrave >= diffCapital_) {
					// Si Réserve grave>= capital CNRA à verser – capital CNRA calculé, alors quittance d’équilibre AT vers GSR 
					// avec le montant capital CNRA à verser – capital CNRA calculé. La réserve grave est déduite du même montant
					generateQuittanceEquilibre = true;
					sinistre.setReserveGrave(reserveGrave - diffCapital_);
					mntQuittance = diffCapital;
								
				} else {
					// Si Réserve grave < capital CNRA à verser – capital CNRA calculé, 
					// afficher « Réserve grave insuffisante, Impossible valider le mouvement »
					throw new ExceptionMetier("Réserve grave insuffisante, Impossible valider le mouvement");
				}
			}		
		}else if(typeConsignation.equals(eai.devass.gsr.appli.prm.TypeConsignation.Revision.getId())){
			if(typeRevision.equals(TypeRevision.Aggravation.getId())||typeRevision.equals(TypeRevision.Revision_H.getId())) {
				if(reserveGrave >= mntCnra) {
					// Si Réserve grave>= capital CNRA à verser – capital CNRA calculé, alors quittance d’équilibre AT vers GSR 
					// avec le montant capital CNRA à verser – capital CNRA calculé. La réserve grave est déduite du même montant
					generateQuittanceEquilibre = true;
					sinistre.setReserveGrave(reserveGrave - mntCnra);
					mntQuittance = - Math.abs(mntCnra);
								
				} else {
					// Si Réserve grave < capital CNRA à verser – capital CNRA calculé, 
					// afficher « Réserve grave insuffisante, Impossible valider le mouvement »
					throw new ExceptionMetier("Réserve grave insuffisante, Impossible valider le mouvement");
				}
			} 	
		}
		
		if(generateQuittanceEquilibre) {
			quittanceGsrEquilibre = new QuittanceGsr();
			quittanceGsrEquilibre.setMontant(-mntQuittance);
			setQuittanceEquilibre(quittanceGsrEquilibre, true);
			lisQuittanceGsrs.add(quittanceGsrEquilibre);
			
			
		}
	}	
	@ASkipRG(property="lisQuittanceGsrs", isEmty="true", bean="this")
	public void regleGestion004SetEtatQuittanceEquilibre(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		// Generation des situations quittances !!!
		mouvementRG.setListQuittanceEmission(lisQuittanceGsrs);
	    mouvementRG.regleGestion994SetEtatQuittance(entiteBO, params);
		
	}
	
	@ASkipRG(property="lisQuittanceGsrs", isEmty="true", bean="this")
	public void regleGestion005EmissionQuittanceGSR(EntiteBO entiteBO, 
			Map params) throws ExceptionMetier {
		
		mouvementRG.setListQuittanceEmission(lisQuittanceGsrs);
		mouvementRG.regleGestion995EmissionQuittanceGSR(entiteBO, params);
	}
	
	
	@ASkipRG(property="quittanceGsrEquilibre", isEmty="true", bean="this")
	public void regleGestion996EmissionQuittanceATEquilibre(EntiteBO entiteBO, 
			Map params) throws ExceptionMetier {
		
		try {
			QuittanceGsr quittanceAtForEmission = new QuittanceGsr();
			BeanUtilsBean.getInstance().copyProperties(quittanceAtForEmission, quittanceGsrEquilibre);
			quittanceAtForEmission.setNumeroQuittance(null);
			quittanceAtForEmission.setCodeServiceOrdonnateur(BENEF_GSR);
			quittanceAtForEmission.setBeneficiaire(BENEF_GSR);
			quittanceAtForEmission.setRaisonSociale(BENEF_GSR);
			quittanceAtForEmission.setCodeServiceOrdonnateur(IParam.CODE_SERVICE_ORDONNATEUR_AT);
			quittanceAtForEmission.setRefNatureQuittance(new NatureQtcGsr(quittanceGsrEquilibre.getNatureQuittanceEquilibreAT().getId()));
			
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
			MvtCnraReglementaire mvtCnraReglementaire) throws Exception {
		
		QuittanceGsr quittanceGsr = new QuittanceGsr();
		quittanceGsr.setDateCreation(new GregorianCalendar());
		TypeApprobation typeApprobation = TypeApprobation.Non_approuvee;
		if (curRentier.getApprobationQuittance() == null|| commonGsrUtils.isTrue(curRentier.getApprobationQuittance())) {
			typeApprobation = TypeApprobation.Approuvee;
		}
		quittanceGsr.setRefTypeApprobation(new eai.devass.gsr.appli.modele.parametrage.TypeApprobation(typeApprobation.getId()));
		quittanceGsr.setRefTypeQuittance(new TypeQuittance(eai.devass.gsr.appli.prm.TypeQuittance.Reglement.getId()));
		TypeRgltGsr typeRgltGsr = new TypeRgltGsr();
		typeRgltGsr.setId(TypeReglement.Direct.getId());
		quittanceGsr.setRefTypeReglement(typeRgltGsr);
		quittanceGsr.setRefNatureQuittance(new NatureQtcGsr(NatureQuittance.capital_constitutif_a_la_CNRA.getId()));
		quittanceGsr.setRefModeReglement(new ModeReglement(eai.devass.gsr.appli.prm.ModeReglement.Cheque.getId()));
		quittanceGsr.setBeneficiaire(CnraINfoCheque.POUR_LECOMPTEDE.getInfo());
		quittanceGsr.setRefRentier(curRentier);
		quittanceGsr.setMontant(mvtCnraReglementaire.getMntCNRA());
		quittanceGsr.setRefMouvement(mvtCnraReglementaire);
		quittanceGsr.setNumeroRente(String.valueOf(curRentier.getRefDossierRente().getNumeroRente()));
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
