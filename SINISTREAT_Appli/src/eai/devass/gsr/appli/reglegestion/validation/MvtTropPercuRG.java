package eai.devass.gsr.appli.reglegestion.validation;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.rmawatanya.reglement.application.metier.valueobjects.QuittanceVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtTropPercu;
import eai.devass.gsr.appli.modele.metier.mouvements.TropPercu;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationEtatRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.prm.TypeQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.services.IAppelService;
import eai.devass.services.ServicesExternes;
import eai.devass.services.impl.AppelServiceManager;

@SuppressWarnings("all")
public class MvtTropPercuRG extends BaseRG {
	
	private String ACTION_ABANDON = "1";
    private String ACTION_RECOURS = "2";
    private String ACTION_ARECUPERER = "3";
	private boolean decison;
	private boolean changerEtatRentier;
	private boolean genererQuittanceRecuperation;
	private boolean genererQuittanceEquilibre;
	private NatureQuittance natureQuittance = NatureQuittance.Rembourssement;
	private Double mntReliquat;
	private Double mntRecuperer;
	
    // Cas Prelevement comptant
   public void regleGestion000VerifierModeRembouresement(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
		
		MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO;
		if(commonGsrUtils.isEmpty(mvtTropPercu.getModeRemboursement())) {
			throw new ExceptionMetier("Le mode de remboursement est obligatoire !!!");
		}
		
	}
    
    @ASkipRG(property="modeRemboursement", value="4")
    public void regleGestion0010MntReliquatNull(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
    	
    	MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO;
    	mntRecuperer = mvtTropPercu.getMntAccord();
		if (mntRecuperer == null || mntRecuperer.equals(0D)) {
			throw new ExceptionMetier("Le montant de l'accord est obligatoire !!!");
		}
    }
    
    
    // Cas Prelevement comptant et vois judiciare
    //  MODE_ETALEMENT = "1" MODE_JUDICAIRE = "3" MODE_ABANDON = "4"  MODE_COMPTANT = "2"
    @ASkipRG(property="modeRemboursement", value="1|4")
    public void regleGestion0011MntReliquatNull(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
		
		MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO;
		mntReliquat = mvtTropPercu.getMntReliquat();
		if(mntReliquat != null && !mntReliquat.equals(0D)) {
			decison = true;
			return;
		}
		
		// Generer une quittance
		genererQuittanceRecuperation = true;
		
		// Cahnger l'etat du rentier
		changerEtatRentier = true;
		
	}
    
    @ASkipRG(property="modeRemboursement", value="2|3|4")
    public void regleGestion002PrelEtalement(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
		
		MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO;
		Double pourcPrelev = mvtTropPercu.getPourcentPrelevement();
		if(pourcPrelev == null || pourcPrelev.equals(0D)) {
			throw new ExceptionMetier("Le pourcentage du prelevement est obligatoire !!!");
		}
		
		natureQuittance = NatureQuittance.Remboursement_par_prelevement;
		if(!pourcPrelev.equals(100D)) {
			return;
		}
		
		// Cahnger l'etat du rentier
		SituationEtatRentier situationEtatRentier = mvtTropPercu.getRefRentier()
				.getCurSituationEtatRentier(EtatRente.Suspendue_Ou_Attente);
		getSession().save(situationEtatRentier);
		
	}
    
    @ASkipRG(property="modeRemboursement", value="2|3|1")
    public void regleGestion003PrelAbandon(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
		
		MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO;
    	if(mvtTropPercu.getMntAbondonner() == null 
    			|| mvtTropPercu.getMntAbondonner().equals(0D)) {
    		throw new ExceptionMetier("Le montant à abondonner est obligatoire !!!");
    	}
		
		// generer quittance equilibre
    	genererQuittanceEquilibre = true;
    	mntRecuperer = mvtTropPercu.getMntAbondonner();
    	mntReliquat = Math.abs(mntRecuperer);
    	
    	// Etat rentier
    	changerEtatRentier = true;
		
	}
	
    // Cas Prelevement comptant et vois judiciare
    @ASkipRG(property="decison", bean="this", value="false")
    public void regleGestion004ActionAbandonPrelevement(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
    	
    	MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO;
		String action = mvtTropPercu.getAction();
		if(!ACTION_ABANDON.equals(action)) {
			return;
		}
		
		// Cas abandon !!!
		Double mntRemiseBancaire = mvtTropPercu.getMntRemiseBancaire();
		if(mntRemiseBancaire == null || mntRemiseBancaire.equals(0D)) {
			throw new ExceptionMetier("Le montant de la remise bancaire est obligatoire !!!");
		}
		
		// generer une quittance
		genererQuittanceRecuperation = true;
		
		// generer une quittance equilibre
		genererQuittanceEquilibre = true;
		
		changerEtatRentier = true;
		
	}
    
    @ASkipRG(property="decison", bean="this", value="false")
    public void regleGestion005ActionRecoursPrelevement(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
    	
    	MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO;
		String action = mvtTropPercu.getAction();
		if(!ACTION_RECOURS.equals(action)) {
			return;
		}
		
		// Cas abandon !!!
		Double mntRemiseBancaire = mvtTropPercu.getMntRemiseBancaire();
		if(mntRemiseBancaire == null || mntRemiseBancaire.equals(0D)) {
			throw new ExceptionMetier("Le montant de la remise bancaire est obligatoire !!!");
		}
		
		// generer une quittance
		genererQuittanceRecuperation = true;
		
		// Cahnger etat rentier
		changerEtatRentier = false;
		
		// Notifer le service recours
		addInfoMessage("001", "Veuillez notifier le service recours pour " +
				"procéder à la récupération du trôp percu", params);
	}
    
    @ASkipRG(property="decison", bean="this", value="false")
    public void regleGestion006ActionARecupererPrelevement(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
    	
    	MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO;
		String action = mvtTropPercu.getAction();
		if(!ACTION_ARECUPERER.equals(action)) {
			return;
		}
		
		// Cas abandon !!!
		Double mntRemiseBancaire = mvtTropPercu.getMntRemiseBancaire();
		if(mntRemiseBancaire == null || mntRemiseBancaire.equals(0D)) {
			throw new ExceptionMetier("Le montant de la remise bancaire est obligatoire !!!");
		}
		
		// generer une quittance
		genererQuittanceRecuperation = true;
		
		// Cahnger etat rentier
		changerEtatRentier = false;
		
		
	}
    
    //GEnerer la quittance de recuperation
    @ASkipRG(property="changerEtatRentier", bean="this", value="false")
    public void regleGestion0070EtatRentier(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
    	
    	// VErifer l'etat du rentier !!
		MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO;
    	EtatRentier etatRentier = mvtTropPercu.getRefRentier().getRefEtatRentier();
		if(EtatRente.Suspendue_Ou_Attente.getCode() == etatRentier.getId()) {
			SituationEtatRentier situationRentier = mvtTropPercu.getRefRentier()
					.getCurSituationEtatRentier(EtatRente.Valide_En_Cours);
			getSession().save(situationRentier);
		}
	}
    
    public void regleGestion0071TropPercuRecuperer(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

    	MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO;    	
		TropPercu tropPercu = new TropPercu();
		tropPercu.setMntRecuperer(mntRecuperer);
		tropPercu.setDateOperation(new Date());
		tropPercu.setRefMouvement(mvtTropPercu);
		tropPercu.setRefRentier(mvtTropPercu.getRefRentier());
		getSession().save(tropPercu);

	}
    
    @ASkipRG(property="modeRemboursement", value="2|3|4")
    public void regleGestion0072GetTrimestre(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

    	MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO;    	
		if(mvtTropPercu.getDateFinPrelevement() == null) {
			throw new ExceptionMetier("La date fin prélevement est obligatoire !!!");
		}
    	
		String trimFinPrelevement = commonGsrUtils
				.getCurrentTrimestre(mvtTropPercu.getDateFinPrelevement());
		mvtTropPercu.setDernierTrimestrePrelevement(trimFinPrelevement);

	}
    
   	@ASkipRG(property="genererQuittanceRecuperation", bean="this", value="false")
    public void regleGestion008GenererQuittanceRecuperation(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
    	
    	MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO;    	
    	QuittanceGsr quittanceGsr = new QuittanceGsr();
    	quittanceGsr.setRefNatureQuittance(new NatureQtcGsr(natureQuittance.getId()));
		eai.devass.gsr.appli.modele.parametrage.TypeQuittance typeQuittance = new eai.devass.gsr.appli.modele.parametrage.TypeQuittance();
		typeQuittance.setId(TypeQuittance.Recuperation.getId());
		typeQuittance.setCode(TypeQuittance.Recuperation.getCode());
		quittanceGsr.setRefTypeQuittance(typeQuittance);
    	quittanceGsr.setBeneficiaire(mvtTropPercu.getRefRentier().getNomComplet());
    	quittanceGsr.setRefRentier(mvtTropPercu.getRefRentier());
    	quittanceGsr.setMontant(mvtTropPercu.getMntAccord());
    	quittanceGsr.setRefMouvement(mvtTropPercu);
		quittanceGsr.setNumeroRente(String.valueOf(mvtTropPercu.getRefRentier()
				.getRefDossierRente().getNumeroRente()));
		quittanceGsr.setExercice(commonGsrUtils.getCurrentTrimestre());
		quittanceGsr.setClasse(String.valueOf(mvtTropPercu.getRefRentier().getLienParente()));
		quittanceGsr.setDateCreation(new GregorianCalendar());
		this.mouvementDB = mvtTropPercu;
    	setQuittanceGsr(quittanceGsr);
		
		// Copy la quittance pour emission, ondoit changer la nature quittance
		//QuittanceGsr quittanceGsrBis = null;
		QuittanceGsr quittanceGsrBis = null;
		try {
			quittanceGsrBis = (QuittanceGsr) BeanUtilsBean.getInstance()
					.cloneBean(quittanceGsr);
		
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de copier la quittance pour emission !!");
		}
		
		// Flush session avant l'apple de service
		SituationQuittanceGsr sitQtc = quittanceGsr
				.getCurSituationQuittanceGsr(EtatQuittance.Reglee);
		sitQtc.setOperateur(mvtTropPercu.getOperateur());
		getSession().save(sitQtc);
		getSession().save(quittanceGsr);
		getSession().flush();
		
		// Emission quittance
		IAppelService appelService = new AppelServiceManager();
		quittanceGsrBis.setRefNatureQuittance(new NatureQtcGsr(NatureQuittance.Rembourssement.getId()));
		QuittanceVO quittanceRes = (QuittanceVO) appelService.appelService(
				ServicesExternes.EMISSION_QUITTANCE,
				(EntiteBO) quittanceGsrBis, "1");
		quittanceGsr.setNumeroQuittance(quittanceRes.getNumQuittance());
		    	
	}
    
    //GEnerer la quittance de recuperation
    @ASkipRG(property="genererQuittanceEquilibre", bean="this", value="false")
    public void regleGestion009GenererQuittanceEquilibre(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
    	
    	// AT vers GSR
    	MvtTropPercu mvtTropPercu = (MvtTropPercu) entiteBO; 
    	QuittanceGsr quittanceGsr = new QuittanceGsr();
    	quittanceGsr.setMontant(mntReliquat);
    	mvtTropPercu.addQuittanceEquilibre(quittanceGsr);
    	
	}


	public boolean getChangerEtatRentier() {
		return changerEtatRentier;
	}


	public boolean getGenererQuittanceRecuperation() {
		return genererQuittanceRecuperation;
	}


	public boolean getGenererQuittanceEquilibre() {
		return genererQuittanceEquilibre;
	}


	public boolean getDecison() {
		return decison;
	}
    
    
    
}
