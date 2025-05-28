/**
 * 
 */
package eai.devass.gsr.appli.reglegestion.validation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.rmawatanya.reglement.application.metier.valueobjects.MouvementQuittanceVO;
import com.rmawatanya.reglement.application.metier.valueobjects.QuittanceVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtAnnulation;
import eai.devass.gsr.appli.modele.metier.mouvements.RentierMvt;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatMouvement;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.prm.TypeQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.services.IAppelService;
import eai.devass.services.ServicesExternes;
import eai.devass.services.impl.AppelServiceManager;
import eai.devass.sinistreat.appli.utils.IConstantes;

/**
 * @author elfaismo
 *
 */
public class MvtAnnulationRG extends BaseRG{
	
	//private MvtAnnulation mvtAnnulationDB=null;
	
	private List<RentierMvt> listRentiersMvt = null;
	
	//private List<Rentier> listRentiers = null;
	
	private Rentier rentier = null;
	
	//private boolean breakRG = false;
	private QuittanceGsr quittanceEquilibre = null;
	private IAppelService appelService = new AppelServiceManager();
	private List<QuittanceGsr> listQuittanceGsr = null;
	
	private List<QuittanceGsr> listQuittanceRecuperation = null;
	

	
	public void regleGestion000MajEtatRente(EntiteBO entiteBO, Map params) throws ExceptionMetier {

		
		MvtAnnulation mouvement = (MvtAnnulation)entiteBO;
		try {

//			Class clazz = Class.forName("eai.devass.gsr.appli.modele.metier.mouvements.MvtAnnulation");
//	
//			mvtAnnulationDB = (MvtAnnulation)getSession().get(clazz, mouvement.getId());
			
			listRentiersMvt =  rentierMvtManager.getListRentierMvt(mouvement.getId());
	
			for(RentierMvt rentierMvt:listRentiersMvt){
				
				rentierManager.updateEtatRentier(rentierMvt.getRefRentier().getId(), rentierMvt.getRefAncienEtatRentier());
			}
		} catch (Exception e) {
				throw new ExceptionMetier("erreur de mise à jour de l'état rentier",e);
		}
	
	}
	
	
	public void regleGestion001MajEtatDossierRente(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		try {
				RentierMvt rentierMvt = listRentiersMvt.get(0);
				rentierManager.updateEtatDossierRente(rentierMvt.getRefRentier().getId(),rentierMvt.getAncienEtatDossierRente() );

		} catch (Exception e) {
			throw new ExceptionMetier("erreur de mise à jour de l'état du dossier rente",e);
		}
		
		
	}
	
	public void regleGestion002MajReserveMathematique(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		try {

	
			for(RentierMvt rentierMvt:listRentiersMvt){
				
				rentierManager.updateReserveMathematique(rentierMvt.getRefRentier().getId(), rentierMvt.getAncienneReserveMathematique());
			}
		} catch (Exception e) {
				throw new ExceptionMetier("erreur de mise à jour de la reserve mathématique",e);
		}
		

		}

	public void regleGestion003MajRente(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		

		try {
			
			for(RentierMvt rentierMvt:listRentiersMvt){
				
				rentierManager.updateRenteTrimestrielle(rentierMvt.getRefRentier().getId(), rentierMvt.getMntAncienneRente());
			}
		} catch (Exception e) {
				throw new ExceptionMetier("erreur de mise à jour de la rente trimestrielle",e);
		}

	}
	
	/**
	 * Annulation du mouvement
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	public void regleGestion004AnnulerMouvement(EntiteBO entiteBO, Map params)
	throws ExceptionMetier {

		MvtAnnulation mouvement = (MvtAnnulation)entiteBO;
		
		// Recuperer le mouvement
		Mouvement mouvementDB = (Mouvement) getSession().get(Mouvement.class,
				mouvement.getRefMvtAnnule().getId());
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
	
	
	public void regleGestion005GenerationQtcTropPercu(EntiteBO entiteBO, Map params) throws ExceptionMetier {

		MvtAnnulation mouvement = (MvtAnnulation)entiteBO;
		try {
			
			for(RentierMvt rentierMvt:listRentiersMvt){

				if(rentierMvt.getMntTropMoinsPercu()>0){
					
					//Générer quittance trop perçu
					
					QuittanceGsr quittanceGsr = genererQuittanceRecuperation(rentierMvt,mouvement);
					
					if(listQuittanceRecuperation!=null) {
						listQuittanceRecuperation.add(quittanceGsr);
					}else{
						
						listQuittanceRecuperation = new ArrayList<QuittanceGsr>();
						listQuittanceRecuperation.add(quittanceGsr);
						
					}
					
					SituationQuittanceGsr sitQtc = quittanceGsr.getCurSituationQuittanceGsr(EtatQuittance.Reglee);
					sitQtc.setOperateur(mouvement.getOperateur());
					getSession().save(sitQtc);
					getSession().save(quittanceGsr);

				}
			}
		} catch (Exception e) {
				throw new ExceptionMetier("erreur lors de la génération de quittance de trop perçu",e);
		}
	}
	
	public void regleGestion800RecuperationQuittancesMvt(EntiteBO entiteBO, Map params) throws ExceptionMetier {

		MvtAnnulation mouvement = (MvtAnnulation)entiteBO;
		
		rentier = mouvement.getRefRentier();
		
		try {
			listQuittanceGsr = quittanceManager.getListQuittanceByMouvement(mouvement.getRefMvtAnnule()) ;
		} catch (Exception e) {
			throw new ExceptionMetier("Un problème est survenue lors de la récupération des quittances ",e);
		}

	}
	
	
	public void regleGestion801AjoutQuittancesTrimestrielle(EntiteBO entiteBO, Map params) throws ExceptionMetier {

		MvtAnnulation mouvement = (MvtAnnulation)entiteBO;
		Calendar dateValidation = mouvement.getRefMvtAnnule().getDatEtat();
		List<QuittanceGsr> listQtcTrimestrielle = null;
		try {
			listQtcTrimestrielle = quittanceManager.getQuittancesTrimestrielleNonRegles(rentier, dateValidation);
		} catch (Exception e) {
				throw new ExceptionMetier("erreur lors de la récupération des quittances trimestrielles emises",e);
		}

		if(listQtcTrimestrielle!=null && listQtcTrimestrielle.size()>0){
			listQuittanceGsr.addAll(listQtcTrimestrielle);
		}
	}
	/**
	 * Vérifier l'état des quittances
	 * Si une quittance est déjà réglée générer une exception et arrêter le traitement.
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	public void regleGestion802VerifierEtatQuittances(EntiteBO entiteBO, Map params) throws ExceptionMetier {

		for(QuittanceGsr quittanceGsr:listQuittanceGsr){
			
			if((quittanceGsr.getRefNatureQuittance().getId()==NatureQuittance.Augmentation_Capital_constitutif.getId()
					&& quittanceGsr.getBeneficiaire().equalsIgnoreCase("GSR"))					
			|| (quittanceGsr.getRefNatureQuittance().getId()==NatureQuittance.Diminution_Capital_constitutif.getId()
					&& quittanceGsr.getBeneficiaire().equalsIgnoreCase("AT"))){
				
				
				quittanceEquilibre = quittanceGsr;
		
				//faire qlq choses
				
			}else if(quittanceGsr.getRefEtatQtc().getId()==EtatQuittance.Reglee.getId()){
				
				throw new ExceptionMetier("Impossible de continuer l'annulation, une quittance est déjà réglée. ");
				
				
			}else if(quittanceGsr.getRefEtatQtc().getId()==EtatQuittance.Annulee.getId() ||		
						quittanceGsr.getRefEtatQtc().getId()==EtatQuittance.Attente_validation_supp.getId() ||
							quittanceGsr.getRefEtatQtc().getId()==EtatQuittance.Supprimee.getId() ||
								quittanceGsr.getRefEtatQtc().getId()==EtatQuittance.Validee.getId()){
				
				
				listQuittanceGsr.remove(quittanceGsr);
				
			}
			
		}
		
		

	}
	public void regleGestion803AnnulationQuittances(EntiteBO entiteBO, Map params) throws ExceptionMetier {

		if (commonGsrUtils.isEmpty(listQuittanceGsr)) {
			return;
		}
		for (QuittanceGsr qtc : listQuittanceGsr) {
			EtatQtc etatQtc = new EtatQtc(EtatQuittance.Annulee.getId());
			etatQtc.setDateCreation(new GregorianCalendar());
			qtc.setRefEtatQtc(etatQtc);
			SituationQuittanceGsr situationQuittanceGsr = new SituationQuittanceGsr();
			situationQuittanceGsr.setOperateur(entiteBO.getOperateur());
			situationQuittanceGsr.setRefEtatQtc(etatQtc);
			situationQuittanceGsr.setDateEtat(new Date());
			situationQuittanceGsr.setRefQuittanceGsr(qtc);
			getSession().save(situationQuittanceGsr);
		}
		getSession().flush();
		

	}
	/**
	 * Appel au service d'annulation
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	public void regleGestion804AnnulationQuittances(EntiteBO entiteBO, Map params) throws ExceptionMetier {


		// Dans le cas d'un rejet, il faut mettre à jour la quittance via un mvt
		// d'annulation
		if(listQuittanceGsr!=null&&listQuittanceGsr.size()>0){
			for (QuittanceGsr qtc : listQuittanceGsr) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String date = dateFormat.format(new Date());
				MouvementQuittanceVO mouvementQuittanceVO = new MouvementQuittanceVO();
				mouvementQuittanceVO.setNumQuittance(qtc.getNumeroQuittance());
				mouvementQuittanceVO.setMotifEtat("Annulation");
				mouvementQuittanceVO.setDatEtat(date);
				// mouvementQuittanceVO.setRefReglement(refReglement);
				// mouvementQuittanceVO.setModReglement(IModePaiement.MISE_A_DISPOSITION);
		
				// Appel de service
				appelService.setConvert(false);
				appelService.appelService(ServicesExternes.ANNULER_QUITTANCE,
						mouvementQuittanceVO, IConstantes.PROFIL_ANNULATION);
				
			}
		}
	}
	/**
	 * Génération de quittance de récupération en local
	 * sans appel service
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	 public void regleGestion008GenererQuittanceRecuperation(EntiteBO entiteBO, Map params)
		throws ExceptionMetier {
		//correction sonar Dead store to local variable.
		 if(listQuittanceRecuperation!=null&&listQuittanceRecuperation.size()>0){

		    for(QuittanceGsr quittanceGsr:listQuittanceRecuperation ){
			    QuittanceGsr quittanceGsrBis = null;
				try {
					// Copy la quittance pour emission, on doit changer la nature quittance
					quittanceGsrBis = (QuittanceGsr) BeanUtilsBean.getInstance()
							.cloneBean(quittanceGsr);
				
				} catch(Exception e) {
					throw new ExceptionMetier("Impossible de copier la quittance pour emission !!",e);
				}
		//		getSession().flush();
				
				// Emission quittance
				IAppelService appelService = new AppelServiceManager();
				quittanceGsrBis.setRefNatureQuittance(new NatureQtcGsr(NatureQuittance.Rembourssement.getId()));
				QuittanceVO quittanceRes = (QuittanceVO) appelService.appelService(
						ServicesExternes.EMISSION_QUITTANCE,
						(EntiteBO) quittanceGsrBis, "1");
				quittanceGsr.setNumeroQuittance(quittanceRes.getNumQuittance());
			 }
		 
		 }
	
}
	
	 
	 public QuittanceGsr genererQuittanceRecuperation(RentierMvt rentierMvt,MvtAnnulation mvtAnnulation)
		throws ExceptionMetier {
		
		QuittanceGsr quittanceGsr = new QuittanceGsr();
		
		quittanceGsr.setRefNatureQuittance(new NatureQtcGsr(NatureQuittance.Rembourssement.getId()));
		eai.devass.gsr.appli.modele.parametrage.TypeQuittance typeQuittance = new eai.devass.gsr.appli.modele.parametrage.TypeQuittance();
		typeQuittance.setId(TypeQuittance.Recuperation.getId());
		typeQuittance.setCode(TypeQuittance.Recuperation.getCode());
		quittanceGsr.setRefTypeQuittance(typeQuittance);
		quittanceGsr.setBeneficiaire(rentierMvt.getRefRentier().getNomComplet());
		quittanceGsr.setRefRentier(rentierMvt.getRefRentier());
		quittanceGsr.setMontant(rentierMvt.getMntTropMoinsPercu());
		quittanceGsr.setRefMouvement(mvtAnnulation);
		quittanceGsr.setNumeroRente(String.valueOf(rentierMvt.getRefRentier()
				.getRefDossierRente().getNumeroRente()));
		quittanceGsr.setExercice(commonGsrUtils.getCurrentTrimestre());
		quittanceGsr.setClasse(String.valueOf(rentierMvt.getRefRentier().getLienParente()));
		this.mouvementDB = mvtAnnulation;
		setQuittanceGsr(quittanceGsr);
		
	
		
		return quittanceGsr;

	 }
	 

	
	
	public void regleGestion805AnnulationQuittancesEquilibre(EntiteBO entiteBO, Map params) throws ExceptionMetier {


		MvtAnnulation mouvement = (MvtAnnulation)entiteBO;
		QuittanceGsr quittanceGsr = new QuittanceGsr();
		if(quittanceEquilibre!=null){
		quittanceGsr.setMontant(-quittanceEquilibre.getMontant());
		mouvement.addQuittanceEquilibre(quittanceGsr);
		}
		
		
	}
	
	

	
}
