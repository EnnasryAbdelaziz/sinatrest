package eai.devass.gsr.appli.reglegestion;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.manager.metier.mouvements.MouvementManager;
import eai.devass.gsr.appli.manager.metier.mouvements.RentierMvtManager;
import eai.devass.gsr.appli.manager.metier.reglement.QuittanceGsrManager;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.ModeReglement;
import eai.devass.gsr.appli.modele.parametrage.MotifSituationEtat;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationDossierRentier;
import eai.devass.gsr.appli.modele.parametrage.TypeRgltGsr;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.prm.TypeQuittance;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.reglement.DetailReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRgl;
import eai.devass.sinistreat.appli.modele.parametrage.Prestation;
import eai.devass.sinistreat.appli.modele.parametrage.TypeReglement;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;

@SuppressWarnings("all")
public class BaseRG {

	protected RentierManager rentierManager = (RentierManager) ServicesFactory
			.getService(RentierManager.class);
	protected MouvementManager mouvementManager = (MouvementManager) ServicesFactory
			.getService(MouvementManager.class);
	protected QuittanceGsrManager quittanceManager = (QuittanceGsrManager) ServicesFactory
			.getService(QuittanceGsrManager.class);
	protected TransverseManager transverseManager = (TransverseManager) ServicesFactory
			.getService(TransverseManager.class);
	protected ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory
			.getService(ParametrageManager.class);
	protected RentierMvtManager  rentierMvtManager= (RentierMvtManager) ServicesFactory
			.getService(RentierMvtManager.class);
	protected SinistreManager  sinistreManager= (SinistreManager) ServicesFactory
			.getService(SinistreManager.class);
	
	protected Mouvement mouvementDB = null;
	protected List<Rentier> listREntierActif = null;
	protected MotifSituationEtat motifSituationEtat = null;
	protected Rentier rentierDB = null;
	protected CommonGsrUtils commonGsrUtils = CommonGsrUtils.getInstance();
	private static final String BENEF_AT = "AT";
	protected static final String BENEF_GSR = "GSR";
	private final static String CODE_GARANTIE = "1";
	
	protected Logger logger = Logger.getLogger("loggerSINAT");

	protected Session getSession() throws ExceptionMetier {
		try {
			IPersistenceService dao = (IPersistenceService) ServicesFactory
					.getService(IPersistenceService.class);
			return (Session) dao.getSession();

		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

	}

	protected List<Rentier> getListRentierActif(List<Rentier> listRentier) {

		if (commonGsrUtils.isEmpty(listRentier)) {
			return null;
		}

		List<Rentier> listRentietActif = new ArrayList<Rentier>();
		EtatRentier etatRentier = null;
		for (Rentier curRentier : listRentier) {
			etatRentier = curRentier.getRefEtatRentier();
			if (etatRentier == null) {
				continue;
			}

			if (EtatRente.Valide_En_Cours.getCode() == etatRentier.getId() 
					|| EtatRente.Suspendue_Ou_Attente.getCode() == etatRentier.getId()) {
				curRentier.setMntAncienneRente(curRentier
						.getMontantRenteTrimestrielle());
				curRentier.setTauxAncienneIpp(curRentier.getIppTauxRente());
				listRentietActif.add(curRentier);
			}
		}

		return listRentietActif;

	}

	public void setQuittanceGsr(QuittanceGsr quittanceGsr) throws ExceptionMetier {

		Rentier rentier = mouvementDB.getRefRentier();

		// Recuperer les informations complementaire de la quittance
		Sinistre sinistre = rentier.getRefDossierRente().getRefSinistre();
		quittanceGsr.setNumeroContrat(sinistre.getNumeroPolice());
		quittanceGsr.setNumeroSinistre(sinistre.getNumeroSinistre());
		quittanceGsr.setCodeBranche(IParam.CODE_BRANCHE_AT);
		if (sinistre.getCodeClient() != null) {
			quittanceGsr.setNumeroClient(sinistre.getCodeClient());
		} else {
			quittanceGsr.setNumeroClient("9999");
		}

		if (!commonGsrUtils.isEmpty(sinistre.getCodeIntermediaire())
				&& sinistre.getCodeIntermediaire().length() == 4) {

			quittanceGsr.setCodeIntermediaire(sinistre.getCodeIntermediaire());
			//Evolution création quittance manuelle 09/02/2015
			if (!commonGsrUtils.isEmpty(sinistre.getTypeIntermediaire()))
			{
				quittanceGsr.setTypeIntermediaire(sinistre.getTypeIntermediaire());
			}
			else
			{
				quittanceGsr.setTypeIntermediaire("W");
			}
			//Fin Evo
			
		} else {
			quittanceGsr.setCodeIntermediaire("9999");
			quittanceGsr.setTypeIntermediaire("A");
		}

		if (sinistre.getNomClient() != null) {
			quittanceGsr.setRaisonSociale(rentier.getRefDossierRente()
					.getRefSinistre().getNomClient());
		} else {
			quittanceGsr.setRaisonSociale("9999");
		}

		quittanceGsr.setCodeBeneficiaire("0");
		quittanceGsr.setDatEmission(new GregorianCalendar());
		quittanceGsr.setCodeUtilisateur("1");
		quittanceGsr.setCodeProduit(IParam.CODE_BRANCHE_AT);
		
		//EVO : Code service ordonnateur,seulement pour le mvt prothese, 
		// le code service = celui de l'AT, SOB7AN LAH
		String codeService = IParam.CODE_SERVICE_ORDONNATEUR_GSR;
		if(mouvementDB instanceof MvtProthese) {
			codeService = IParam.CODE_SERVICE_SINISTRE_GRAVE;
		}
		quittanceGsr.setCodeServiceOrdonnateur(codeService);
		
		// Recuperer le code prestataion depuis la nature quittance !!
		if(quittanceGsr.getRefNatureQuittance() == null) {
			throw new ExceptionMetier("La nature quittance est obligatoire !!!");
		}
		
		long natureQtc = quittanceGsr.getRefNatureQuittance().getId();
		String codePrestation = getCodePrestataion(natureQtc);		
		if(CommonGsrUtils.isEmpty(codePrestation)) {
			throw new ExceptionMetier("Le code prestation est obligatoire, il faut le paramétrer " +
					"dans NatureQuittance pour l'id = " + natureQtc);
		}
		quittanceGsr.setCodePrestation(codePrestation);
		
		// Type quittance
		eai.devass.gsr.appli.modele.parametrage.TypeQuittance typeQuittance = quittanceGsr.getRefTypeQuittance();
		if(typeQuittance == null) {
			throw new ExceptionMetier("Le type de la quittance est obligatoire");
		}
		int codeTypeQtc = getCodeTypeQuittance(typeQuittance.getId());
		typeQuittance.setCode(codeTypeQtc);
		
		// Debut et fin trimistre
		//CK : 19112014 (Apres SY) LEs date debut et fin sont ceux du  complement
		if(quittanceGsr.getDateDebutQtc() == null) {
			quittanceGsr.setDateDebutQtc(commonGsrUtils.getDateDebutCurrentTrimestre());
		}
		if(quittanceGsr.getDateFinQtc() == null) {
			quittanceGsr.setDateFinQtc(commonGsrUtils.getDateFinCurrentTrimestre());
		}
		
		// EVO : fixer le code garantie a 1
		quittanceGsr.setCodeCategorie(CODE_GARANTIE);
		
	}

	protected void setQuittanceAT(QuittanceGsr quittanceGsr) {

		Reglement reglement = new Reglement();
		// Recuperer les informations complementaire de la quittance
		if (quittanceGsr.getRefRentier() != null
				&& quittanceGsr.getRefRentier().getRefDossierRente() != null) {
			reglement.setRefSinistre(quittanceGsr.getRefRentier()
					.getRefDossierRente().getRefSinistre());
			
		}
		reglement.setNumeroSinistre(quittanceGsr.getNumeroSinistre());
		quittanceGsr.setCodeBranche(IParam.CODE_BRANCHE_AT);
		reglement.setCodeIntermediaire(quittanceGsr.getCodeIntermediaire());
		reglement.setTypeIntermediaire(quittanceGsr.getTypeIntermediaire());
		reglement.setMontant(quittanceGsr.getMontant());
		reglement.setNomBeneficiaire("GSR");
		reglement.setDateEmission(new Date());
		reglement.setNomUserCreateur(quittanceGsr.getOperateur());
		reglement.setCodeSousTypeQuittance(IParam.CODE_SOUSTYPEQTC_GSR);
		reglement.setTypeBeneficiaire("1");
		reglement.setRefEtatReglement(new EtatRgl(IConstantes.ETAT_REGLEMENT_REGLE));
		reglement.setRefTypeQuittance(new eai.devass.sinistreat.appli.modele.parametrage.TypeQuittance(
						String.valueOf(TypeQuittance.Reglement.getCode())));
		reglement.setDateEtablissement(new Date());
		reglement.setCodeUserCreateur(quittanceGsr.getOperateur());
		reglement.setRefTypeReglement(new TypeReglement(IConstantes.TYPE_REGLEMENT_DIRECT));
		reglement.setModeReglement("9");
		reglement.setDateReglement(new Date());
		reglement.setDateEtat(new Date());
		reglement.setService(IParam.CODE_SERVICE_ORDONNATEUR_AT_GRAVE);
		reglement.setDateCreation(new Date());
		
		// Detail reglement
		DetailReglement detailReglement = new DetailReglement();
		detailReglement.setCodeGarantie(CODE_GARANTIE);
		detailReglement.setMontant(quittanceGsr.getMontant());
		
		// Code prestataion
		NatureQuittance natureQuittance = quittanceGsr.getNatureQuittanceEquilibreAT();
		detailReglement.setRefPrestation(new Prestation(natureQuittance.getCodePrestataionReglementAT()));
		detailReglement.setLibellePrestation(natureQuittance.getLblPrestataionReglementAT());
		detailReglement.setRefReglement(reglement);
		List<DetailReglement> listDetailRgl = new ArrayList<DetailReglement>();
		listDetailRgl.add(detailReglement);	
		reglement.setListDetailReglement(listDetailRgl);
		
		// Sous type quittance
		reglement.setSousTypeQuittance(String.valueOf(natureQuittance.getId()));
		
		quittanceGsr.setQuittanceAT(reglement);

	}
	
	protected boolean addSituation(List<SituationDossierRentier> listSituationsDossier,String codeEtat) {
		if(commonGsrUtils.isEmpty(listSituationsDossier)) {
			return true;
		}
		
		SituationDossierRentier lastSituationDossierRentier = listSituationsDossier.get(0);
		if (lastSituationDossierRentier.getRefMotifSituationEtat() == null) {
			return true;
		}
		
		if (!lastSituationDossierRentier.getRefMotifSituationEtat().getCode()
				.equals(codeEtat)) {
			return true;
		}
		
		return false;
		
//		return listSituationsDossier.get(listSituationsDossier.size()-1).getRefMotifSituationEtat() == null
//				|| !situationsDossier.get(situationsDossier.size()-1).getRefMotifSituationEtat()
//						.getCode()
//						.equals(codeEtat);
	}
	
	private String getCodePrestataion(Long natureQtc) {
		
		NatureQuittance[] natureQuittances = NatureQuittance.class.getEnumConstants();
		for (NatureQuittance curNatQtc : natureQuittances) {
			if(curNatQtc.getId() == natureQtc) {
				return curNatQtc.getRubrique();
			}
		}
		
		return null;
	}
	
	private int getCodeTypeQuittance(long typeQtc) throws ExceptionMetier {
		
		TypeQuittance[] typeQuittances = TypeQuittance.class.getEnumConstants();
		for (TypeQuittance curTypeQtc : typeQuittances) {
			if(curTypeQtc.getId() == typeQtc) {
				return curTypeQtc.getCode();
			}
		}
		
		throw new ExceptionMetier("Le code type quittance ne peut être null ");
	}
	
	
	protected void addInfoMessage(String codeMsg, String msg, Map<Object, Object> params) {
		
		((Map) params.get("outMapMessage")).put(codeMsg, new InfoMessageItem(msg));
	}
	
	protected void setQuittanceEquilibre(QuittanceGsr quittanceGsr, boolean contexteMvt) 
			throws ExceptionMetier  {
		
		Double mntQtcEquilibe = quittanceGsr.getMontant();
		NatureQuittance natureQtcGsr = null;
				
		// NAture quittance
		if (contexteMvt == false)
		{
			if (mntQtcEquilibe > 0) {
				natureQtcGsr = NatureQuittance.Virement_Capital_constitutif;
									
			} else {
				natureQtcGsr = NatureQuittance.Virement_Capital_constitutif;
				
			}
		}
		else
		{
			if (mntQtcEquilibe > 0) {
				natureQtcGsr = NatureQuittance.Augmentation_Capital_constitutif;
									
			} else {
				natureQtcGsr = NatureQuittance.Diminution_Capital_constitutif;
				
			}
		}
		quittanceGsr.setRefNatureQuittance(new NatureQtcGsr(natureQtcGsr.getId()));
		
		// Completer les info de la quittance pour l'emission
		eai.devass.gsr.appli.modele.parametrage.TypeQuittance typeQuittance = 
			new eai.devass.gsr.appli.modele.parametrage.TypeQuittance(TypeQuittance.Reglement.getId());
		quittanceGsr.setRefTypeQuittance(typeQuittance);
		
		// Mode reglement virement
		quittanceGsr.setRefModeReglement(new ModeReglement(
						eai.devass.gsr.appli.prm.ModeReglement.Virement	.getId()));
		quittanceGsr.setExercice(commonGsrUtils.getCurrentTrimestre());
		quittanceGsr.setDateCreation(new GregorianCalendar());
		quittanceGsr.setRefTypeReglement(new TypeRgltGsr(eai.devass.gsr.appli.prm.TypeReglement.Direct.getId()));

		setQuittanceGsr(quittanceGsr);
		// Selon le context, cas lot 1 le contexteMvt=false
		if(contexteMvt) {
			quittanceGsr.setRefMouvement(mouvementDB);
			quittanceGsr.setBeneficiaire(BENEF_AT);
		}
		else
		{
			quittanceGsr.setBeneficiaire(BENEF_AT);
		}
		
		// Dans le cas des quittance d'equilibre pour ts les rentiers
		if(quittanceGsr.getRefRentier() == null) {
			quittanceGsr.setRefRentier(mouvementDB.getRefRentier());
		}
		quittanceGsr.setNumeroRente(String.valueOf(mouvementDB
						.getRefRentier().getRefDossierRente().getNumeroRente()));
		quittanceGsr.setRaisonSociale(BENEF_AT);
		quittanceGsr.setDatEtat(new GregorianCalendar());
		quittanceGsr.setClasse(String.valueOf(mouvementDB.getRefRentier().getLienParente()));
		
		// Quittance AT pour l'emission qtc
		quittanceGsr.setMontant(Math.abs(mntQtcEquilibe));
		
	
		setQuittanceAT(quittanceGsr);

	}

	public Mouvement getMouvementDB() {
		return mouvementDB;
	}

	
	//
	public void setMouvementDB(Mouvement mouvementDB) {
		this.mouvementDB = mouvementDB;
	}

	public void setRentierDB(Rentier rentierDB) {
		this.rentierDB = rentierDB;
	}
	
	
	
	
	
	
}
