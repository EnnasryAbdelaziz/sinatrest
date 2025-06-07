// 
// Decompiled by Procyon v0.5.36
// 

package eai.devass.sinistreat.appli.businessrule;

import java.text.ParseException;
import java.util.Iterator;
import eai.devass.sinistreat.appli.modele.metier.reglement.DetailReglement;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.MessageItem;
import java.math.BigDecimal;
import eai.devass.sinistreat.appli.modele.metier.reglement.ChequePrime;
import eai.devass.sinistreat.appli.modele.metier.reglement.ChequeSinistreBGD;
import eai.devass.sinistreat.appli.modele.metier.reglement.CompensationSinistre;
import org.apache.commons.lang.StringUtils;
import eai.devass.sinistreat.appli.modele.metier.reglement.ChequeSinistre;
import eai.devass.sinistreat.appli.modele.metier.reglement.MiseAdispositionSinistre;
import com.rmawatanya.moyenpaiement.application.metier.usecases.commun.pub.INatureQuittance;
import eai.devass.sinistreat.appli.modele.metier.reglement.QuittanceMoyenPaiement;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Positionnement;
import eai.devass.sinistreat.appli.utils.IConstantes;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.OrdOrdonnoncementVO;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.services.ServicesExternes;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import com.rmawatanya.moyenpaiement.application.metier.services.ordonnoncement.pub.IServiceOrdonnoncement;
import java.util.HashMap;
import eai.devass.sinistreat.appli.modele.parametrage.Banque;
import eai.devass.sinistreat.appli.modele.parametrage.TypeQuittance;
import eai.devass.sinistreat.appli.modele.parametrage.TypeReglement;
import java.util.Date;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRgl;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.Fonctions;
import java.io.Serializable;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import java.util.ArrayList;
import eai.devass.sinistreat.appli.manager.reglement.ReglementManager;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.services.IAppelService;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.hibernate.Session;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;

public class ReglementRG {
	private Reglement reglement;
	private Reglement reglementDB;
	private Session session;
	private Transaction tx;
	private final Logger logger;
	private List<String> listErrors;
	private final SimpleDateFormat dateFormat;
	private final IAppelService servicesExternes;
	static final String ORDONNANCEMENT_SOUMIS_DELEGATION = "ORDONNANCEMENT.SOUMIS.DELEGATION";
	static final String UTILISATEUR_NON_ELIGIBLE = "UTILISATEUR.NON.ELIGIBLE";
	static final String ORD_ANNULE_OK = "ORD.ANNULE.OK";
	private final SinistreManager sinistreManager;
	protected ReglementManager reglementManager;

	public ReglementRG(final Reglement reglement, final Session session) {
		this.logger = Logger.getLogger("loggerGSR");
		this.listErrors = new ArrayList<String>();
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.servicesExternes = (IAppelService) ServicesFactory.getService((Class) IAppelService.class);
		this.sinistreManager = (SinistreManager) ServicesFactory.getService((Class) SinistreManager.class);
		this.reglementManager = (ReglementManager) ServicesFactory.getService((Class) ReglementManager.class);
		this.reglement = reglement;
		this.session = session;
	}

	public Transaction beginTransaction() {
		return this.session.beginTransaction();
	}

	public void getReglementDataBase() throws Exception {
		this.reglementDB = (Reglement) this.session.get((Class) Reglement.class, (Serializable) this.reglement.getId());
		if (this.reglementDB == null) {
			this.listErrors.add("R\u00e8glement inexistant. : " + this.reglement.getId());
			this.session.clear();
			throw new Exception("R\u00e8glement inexistant.");
		}
	}

	public void validateReglement() throws Exception {
		final Sinistre sinistre = this.reglementDB.getRefSinistre();
		try {
			if (sinistre == null) {
				throw new Exception(
						"Le dossier sinistre est obligatoire [" + this.reglementDB.getNumeroQuittance() + "]");
			}
			if (Fonctions.isEmpty(sinistre.getCodeClient())) {
				throw new Exception("Le NÂ° de client est obligatoire [" + this.reglementDB.getNumeroQuittance() + "]");
			}
			if (Fonctions.isEmpty(sinistre.getNomClient())) {
				throw new Exception(
						"La raison sociale du client est obligatoire [" + this.reglementDB.getNumeroQuittance() + "]");
			}
			if (Fonctions.isEmpty(sinistre.getNumeroPolice())) {
				throw new Exception("Le NÂ° de police est obligatoire [" + this.reglementDB.getNumeroQuittance() + "]");
			}
			if (sinistre.getNumeroPolice().length() != 15) {
				throw new Exception(
						"Le NÂ° de police est sur 15 positions [" + this.reglementDB.getNumeroQuittance() + "]");
			}
		} catch (Exception e) {
			this.logger.error((Object) "Error lors de la validation du reglement", (Throwable) e);
			this.listErrors.add(e.getMessage());
			this.session.clear();
			throw e;
		}
	}

	public void setEtatReglementDB() throws Exception {
		final EtatRgl etatRgl = new EtatRgl();
		final TypeReglement typeReg = this.reglementDB.getRefTypeReglement();
		if (typeReg.getCode().equals("4")) {
			etatRgl.setCode("10");
			this.reglementDB.setRefEtatReglement(etatRgl);
		} else if ("2".equals(this.reglement.getModeReglement())) {
			etatRgl.setCode("10");
			this.reglementDB.setRefEtatReglement(etatRgl);
		} else {
			this.reglementDB.setRefEtatReglement(new EtatRgl("2"));
		}
		this.reglementDB.setDateEtat(new Date());
		this.reglementDB.setDateEmission(this.dateFormat.parse(this.dateFormat.format(new Date())));
	}

	public void setInfoReglement() throws Exception {
		try {
			this.reglement.setListDetailReglement(this.reglementDB.getListDetailReglement());
			if (this.reglement.getRefTypeQuittance() == null) {
				final TypeQuittance typequit = new TypeQuittance();
				typequit.setCode("2");
				this.reglement.setRefTypeQuittance(typequit);
			}
			if (this.reglement.getService() == null) {
				this.reglement.setCodeServiceOrdonnateur("W5116");
			} else {
				this.reglement.setCodeServiceOrdonnateur(this.reglement.getService());
			}
			this.reglement.setCodeBranche("1");
			if (this.reglement.getCodeBeneficiaire() == null) {
				this.reglement.setCodeBeneficiaire("0");
			}
			if (this.reglement.getCodeIntermediaireRgl() == null) {
				this.reglement.setCodeIntermediaireRgl("7");
			}
			if (this.reglement.getNomBeneficiaire() == null) {
				this.reglement.setNomBeneficiaire("9");
			}
			if (this.reglement.getNomPartieAdverse() == null) {
				this.reglement.setNomPartieAdverse("partie Adverse");
			}
			if (this.reglement.getNumeroQuittance().length() > 15) {
				final String numQuittance = this.reglement.getNumeroQuittance().replaceAll(" ", "");
				this.reglement.setNumeroQuittance(numQuittance);
			}
			this.reglement.setDateModification(this.getDate());
			this.reglement.setDateEmission(this.getDate());
			if (this.reglement.getRefBanque() != null && this.reglement.getRefBanque().getCode() != null) {
				final Banque refBanque = new Banque();
				refBanque.setCode(this.reglement.getRefBanque().getCode());
				this.reglementDB.setRefBanque(refBanque);
			}
			if (this.reglement.getReference() != null) {
				this.reglementDB.setReference(this.reglement.getReference());
			}
			this.reglement.setRefSinistre(this.reglementDB.getRefSinistre());
			try {
				final String codeIntermediaire = this.reglement.getCodeIntermediaire();
				final String typeIntermediaure = this.reglement.getTypeIntermediaire();
				if (!Fonctions.isLong(codeIntermediaire) || codeIntermediaire.length() != 4) {
					throw new Exception("Le code Intermediaire est invalide : " + codeIntermediaire);
				}
				if (Fonctions.isEmpty(typeIntermediaure) || typeIntermediaure.length() != 1) {
					throw new Exception("Le type Intermediaire est invalide : " + typeIntermediaure);
				}
			} catch (Exception e) {
				throw new Exception(e.getMessage() + " : " + this.reglement.getNumeroQuittance(), e);
			}
		} catch (Exception e) {
			this.listErrors.add(e.getMessage());
			this.session.clear();
			throw e;
		}
	}

	public void emissionPositionnement() throws Exception {
		EtatRgl etatRgl2 = null;
		final boolean positionnement = this.reglement.getRefTypeReglement().getCode().equals("1");
		final boolean positionnementBGD = this.reglement.getRefTypeReglement().getCode().equals("6");
		try {
			if (this.reglement.isInstanceValidationSup()) {
				etatRgl2 = this.validerpositionnementService();
				this.reglementDB.setRefEtatReglement(etatRgl2);
				return;
			}
		} catch (Exception e) {
			this.logger.error((Object) "Positionnement Hi\u00e9rarchique Error", (Throwable) e);
			this.listErrors.add(e.getMessage());
			throw new Exception("Positionnement Hi\u00e9rarchique Error [" + this.reglement.getNumeroQuittance() + "] :"
					+ e.getMessage());
		}
		try {
			if (!this.reglement.isInstancePositionnement()) {
				this.emissionQuittanceService(this.reglement);
				if (positionnement || positionnementBGD || this.reglement.getModeReglement().equals("2")) {
					this.reglementDB.setRefEtatReglement(new EtatRgl("7"));
				}
			}
		} catch (Exception e) {
			this.listErrors.add("Error emission quittance : " + e.getMessage());
			throw new Exception(e.getMessage() + " [" + this.reglement.getNumeroQuittance() + "]", e);
		}
		try {
			if (positionnement || positionnementBGD || this.reglement.getModeReglement().equals("2")
					|| this.reglement.getModeReglement().equals("4")) {
				this.positionnementService();
			}
		} catch (Exception e) {
			this.listErrors.add("Error Positionnement : " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void emissionPositionnementSansOrd() throws Exception {
		final EtatRgl etatRgl2 = null;
		/* Positionnement BGD */
		boolean positionnementBGD = (reglement).getRefTypeReglement().getCode()
				.equals(IConstantes.TYPE_REGLEMENT_BGD);
		/*Positionnement Recuperation 12/2021 ENNASRY EVOL LOT1*/
		boolean positionnementRecup = (reglement).getRefTypeReglement().getCode()
				.equals(IConstantes.TYPE_REGLEMENT_RECUPERATION);
		
		/* Emission du réglement */
		try {
			// Appel service emission
			if (!reglement.isInstancePositionnement()) {
				emissionQuittanceService(reglement);

				// Cas reglment direct
				if (positionnementBGD) {
					reglementDB
							.setRefEtatReglement(new EtatRgl(
									IConstantes.ETAT_REGLEMENT_EN_INSTANCE_POSITIONNEMENT));
				}
			}
		} catch (Exception e) {
			this.listErrors.add("Error lors de l'emission quittance : " + e.getMessage());
			throw new Exception(e.getMessage() + " [" + this.reglement.getNumeroQuittance() + "]", e);
		}
		try {
			// Cas reglement direct et reglement Recuperation  12/2021 ENNASRY EVOL LOT1
						if (positionnementBGD || positionnementRecup) {
							// Appel service positionnement
							positionnementSansOrdService();
						}
		} catch (Exception e) {
			this.listErrors.add("Error Positionnement : " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	private EtatRgl validerpositionnementService() throws Exception {
		boolean isValide = false;
		IResult result = null;
		final HashMap params = new HashMap();
		final String codeSas = this.reglement.getCodeUserModificateur();
		params.put("userId", codeSas);
		params.put("idOrdonnoncement", this.reglement.getIdOrdonnancement());
		final IServiceOrdonnoncement serviceOrdonnoncement = (IServiceOrdonnoncement) ServicesFactory
				.getService((Class) IServiceOrdonnoncement.class);
		result = serviceOrdonnoncement.soumettreOrdonnoncement(params, 2L);
		isValide = this.ordonnancementValider(result);
		if (isValide) {
			return new EtatRgl("2");
		}
		return new EtatRgl("6");
	}

	private void emissionQuittanceService(final Reglement reglement) throws ExceptionMetier {
		this.servicesExternes.setConvert(true);
		this.servicesExternes.appelService(ServicesExternes.EMISSION_QUITTANCE, (Object) reglement, "1");
	}

	private void positionnementService() throws ExceptionMetier, FonctionnelleException {

		Positionnement positionnement = getOrdonnancement();
		servicesExternes.setConvert(true);
		List resultat = (List) servicesExternes.appelService(ServicesExternes.POSITIONNEMENT_REGLEMENT, positionnement,
				reglement.getCodeUserModificateur());

		if (resultat == null || resultat.isEmpty()) {
			throw new ExceptionMetier("Erreur lors du positionnement," + " la liste des résultats est null !!");
		}
		if (!reglement.getModeReglement().equals("2")) {
			OrdOrdonnoncementVO posit = (OrdOrdonnoncementVO) resultat.get(0);
			if (posit.getEtatOrd().getEtat().equals(IConstantes.REGLEMENT_HIERARCHIQUE)) {

				reglementDB
						.setRefEtatReglement(new EtatRgl(IConstantes.ETAT_REGLEMENT_EN_INSTANCE_VALIDATION_SUPERIEURE));

				if (posit.getId() != null) {
					reglementDB.setIdOrdonnancement(posit.getId());
				}

			} else {
				reglementDB.setRefEtatReglement(new EtatRgl(IConstantes.ETAT_REGLEMENT_EN_INSTANCE_DE_REGLEMENT));
			}
		} else {

			reglementDB.setRefEtatReglement(new EtatRgl(IConstantes.ETAT_REGLEMENT_EN_INSTANCE_DE_REGLEMENT));
		}
	}

	private void positionnementSansOrdService() throws ExceptionMetier, FonctionnelleException {
		final Positionnement positionnement = this.getOrdonnancement();
		this.servicesExternes.setConvert(true);
		final HashMap map = new HashMap();
		map.put("contextModification", true);
		final List resultat = (List) this.servicesExternes.appelService(
				ServicesExternes.POSITIONNEMENT_REGLEMENT_SANSORD, (Object) positionnement,
				this.reglement.getCodeUserModificateur());
		if (resultat == null || resultat.isEmpty()) {
			throw new ExceptionMetier("Erreur lors du positionnement, la liste des r\u00e9sultats est null !!");
		}
		this.reglementDB.setRefEtatReglement(new EtatRgl(IConstantes.ETAT_REGLEMENT_EN_INSTANCE_DE_REGLEMENT));
	}

	private Positionnement getOrdonnancement() throws ExceptionMetier {
		final Positionnement positionnement = new Positionnement();
		positionnement.setUtilisateur(this.reglement.getCodeUserModificateur());
		positionnement.setNomBeneficiaire(this.reglement.getNomBeneficiaire());
		positionnement.setCodeService(this.reglement.getService());
		if (this.reglement.getTypeBeneficiaire() != null) {
			if (this.reglement.getTypeBeneficiaire().equals("7")) {
				positionnement.setTypeDestinataire(Positionnement.TypeDestinataire.INTERMEDIAIRE);
			} else if (this.reglement.getTypeBeneficiaire().equals("9")) {
				positionnement.setTypeDestinataire(Positionnement.TypeDestinataire.ASSURE);
			} else if (this.reglement.getTypeBeneficiaire().equals("12")) {
				positionnement.setTypeDestinataire(Positionnement.TypeDestinataire.AUXILLIAIRE);
			} else {
				positionnement.setTypeDestinataire(Positionnement.TypeDestinataire.AUTRE);
			}
		}
		positionnement.setMntTotalQuittance(String.valueOf(this.reglement.getMontant()));
		final List<QuittanceMoyenPaiement> listQuittances = new ArrayList<QuittanceMoyenPaiement>();
		final QuittanceMoyenPaiement quittanceMP = new QuittanceMoyenPaiement();
		quittanceMP.setNumQuittance(this.reglement.getNumeroQuittance());
		quittanceMP.setNumDossier(this.reglement.getRefSinistre().getNumeroSinistre());
		quittanceMP.setCodeBranche("1");
		if (this.reglement.getRefTypeReglement().getCode().equals("4")) {
			quittanceMP.setNatureQuittance(INatureQuittance.NatureQuittance.RECUPERATION);
		} else {
			quittanceMP.setNatureQuittance(INatureQuittance.NatureQuittance.REGLEMENT);
		}
		quittanceMP.setListDetailReglement(this.reglement.getListDetailReglement());
		listQuittances.add(quittanceMP);
		positionnement.setRefQuittance((List) listQuittances);
		if (this.reglement.getModeReglement().equals("6")) {
			final List<MiseAdispositionSinistre> listMoyenPaiement_MAD = new ArrayList<MiseAdispositionSinistre>();
			final MiseAdispositionSinistre miseAD = new MiseAdispositionSinistre();
			miseAD.setCinBeneficiaire(this.reglement.getRefSinistre().getRefVictime().getNumeroCIN().replace(" ", ""));
			miseAD.setMntRegle((double) this.reglement.getMontant());
			miseAD.setNomBeneficiaireMAD(this.reglement.getNomBeneficiaire());
			listMoyenPaiement_MAD.add(miseAD);
			positionnement.setRefMoyenPaiement((List) listMoyenPaiement_MAD);
		} else if (this.reglement.getModeReglement().equals("1")) {
			final List<ChequeSinistre> listMoyenPaiement_cheque = new ArrayList<ChequeSinistre>();
			final ChequeSinistre chequeSinistre = new ChequeSinistre();
			chequeSinistre.setCode("1");
			chequeSinistre.setLibelle("Ch\u00e8que");
			chequeSinistre.setCompteDe(this.reglement.getNomBeneficiaire());
			chequeSinistre.setEnvoyerA(this.reglement.getNomBeneficiaire());
			if (this.reglement.getVosReference() == null || StringUtils.isEmpty(this.reglement.getVosReference())) {
				chequeSinistre.setVosReference("0");
			} else if (this.reglement.getTypeBeneficiaire() != null
					&& this.reglement.getTypeBeneficiaire().equals("5")) {
				chequeSinistre.setVosReference(this.reglement.getVosReference());
			} else if (this.reglement.getNomBeneficiaire() != null) {
				if (this.reglement.getNomBeneficiaire().length() <= 40) {
					chequeSinistre.setVosReference(this.reglement.getNomBeneficiaire());
				} else {
					chequeSinistre.setVosReference(this.reglement.getNomBeneficiaire().substring(0, 40));
				}
			}
			listMoyenPaiement_cheque.add(chequeSinistre);
			positionnement.setRefMoyenPaiement((List) listMoyenPaiement_cheque);
		} else if (this.reglement.getModeReglement().equals("2")) {
			final List<CompensationSinistre> listMoyenPaiement_compensation = new ArrayList<CompensationSinistre>();
			final CompensationSinistre compensationSinistre = new CompensationSinistre();
			compensationSinistre.setNumeroRemise(this.reglement.getNumeroRemise());
			compensationSinistre.setMntRegle((double) this.reglement.getMontant());
			listMoyenPaiement_compensation.add(compensationSinistre);
			positionnement.setRefMoyenPaiement((List) listMoyenPaiement_compensation);
		} else if (this.reglement.getModeReglement().equals("3")) {
			final List<ChequeSinistreBGD> listMoyenPaiement_ChBgd = new ArrayList<ChequeSinistreBGD>();
			final ChequeSinistreBGD chequeSinistreBGD = new ChequeSinistreBGD();
			if (this.reglement.getRefBanque() == null) {
				throw new ExceptionMetier("le code banque est obligatoire pour un r\u00e9glement BGD");
			}
			chequeSinistreBGD.setCodeBanque(this.reglement.getRefBanque().getCode());
			chequeSinistreBGD.setDateReglement(this.reglement.getDateReglement());
			chequeSinistreBGD.setNumCheque(this.reglement.getReference());
			listMoyenPaiement_ChBgd.add(chequeSinistreBGD);
			positionnement.setRefMoyenPaiement((List) listMoyenPaiement_ChBgd);
		} else if (this.reglement.getModeReglement().equals("4")) {
			final List<ChequePrime> listMoyenPaiement_ChPrime = new ArrayList<ChequePrime>();
			final ChequePrime chequePrime = new ChequePrime();
			chequePrime.setRibClient("999999999999999999999999");
			if (this.reglement.getDateEmission() == null) {
				throw new ExceptionMetier(
						"la date d'emission est obligatoire pour la validation d'une r\u00e9cup\u00e9ration");
			}
			chequePrime.setDateEmission(this.dateFormat.format(this.reglement.getDateEmission()));
			if (this.reglement.getNumCheque() != null && this.reglement.getNumCheque().length() == 7) {
				chequePrime.setNumCheque(this.reglement.getNumCheque());
			} else {
				chequePrime.setNumCheque("1111111");
			}
			chequePrime.setNumRemise(this.reglement.getNumeroRemise());
			listMoyenPaiement_ChPrime.add(chequePrime);
			positionnement.setRefMoyenPaiement((List) listMoyenPaiement_ChPrime);
		}
		return positionnement;
	}

	public void updateCumulRegValidation() throws Exception {
		try {
			final Sinistre sindb = this.reglementDB.getRefSinistre();
			if (this.reglementDB.getRefEtatReglement() == null) {
				throw new FonctionnelleException("Etat r\u00e9glement ne peut \u00eatre null", new String[0]);
			}
			if (this.reglementDB.getRefTypeReglement() == null) {
				throw new FonctionnelleException("Type r\u00e9glement ne peut \u00eatre null", new String[0]);
			}
			if (this.reglementDB.isInstanceValidationSup()) {
				return;
			}
			if (this.reglementDB.isInstancePositionnement()
					&& "1".equals(this.reglementDB.getRefTypeReglement().getCode())) {
				return;
			}
			if ("4".equals(this.reglementDB.getRefTypeReglement().getCode())) {
				sindb.setCumulReglementAnne(
						Double.valueOf(new BigDecimal(sindb.getCumulReglementAnne() - this.reglementDB.getMontant())
								.setScale(2, 6).doubleValue()));
				return;
			}
			sindb.setCumulReglementAnne(
					Double.valueOf(new BigDecimal(sindb.getCumulReglementAnne() + this.reglementDB.getMontant())
							.setScale(2, 6).doubleValue()));
		} catch (Exception e) {
			this.listErrors.add("Error lors de la mise \u00e0 jour des cumules rglements : " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	private boolean ordonnancementValider(final IResult result) throws Exception {
		final MessageItem messageItem = (MessageItem) result.getMessagesItem().get(0);
		if (!(messageItem instanceof InfoMessageItem)) {
			throw new Exception(
					"Impossible de r\u00e9cup\u00e9rer l'etat de la validation : " + messageItem.getLibelleMessage());
		}
		return "ORDONNANCEMENT.SOUMIS.DELEGATION".equals(messageItem.getCodeMessage())
				|| (!"UTILISATEUR.NON.ELIGIBLE".equals(messageItem.getCodeMessage())
						&& "ORD.ANNULE.OK".equals(messageItem.getCodeMessage()));
	}

	public void miseAjourReserve() throws Exception {
		final Sinistre sinistre = this.reglement.getRefSinistre();
		final Sinistre sinBD = this.reglementDB.getRefSinistre();
		if (sinBD == null) {
			throw new FonctionnelleException("Ce sinistre est inexistant.", new String[0]);
		}
		Double montantRachat = 0.0;
		final List<DetailReglement> listPrestation = (List<DetailReglement>) this.reglement.getListDetailReglement();
		for (final DetailReglement prestation : listPrestation) {
			if (prestation.getCodePrestation().equals("22")) {
				montantRachat = prestation.getMntPrestation();
			}
		}
		if (montantRachat.equals(0.0)) {
			return;
		}
		final double reserveGrave = 0.0;
		sinBD.setReserveGrave(Double.valueOf(new BigDecimal(reserveGrave).setScale(2, 6).doubleValue()));
		this.reglement.setRefSinistre(sinBD);
		this.sinistreManager.creerMouvementReglement(new Object[] { this.reglement, 4L, this.session });
	}

	public void miseAjourReserveRegBGD() throws Exception {
		final Sinistre sinBD = this.reglementDB.getRefSinistre();
		if (sinBD == null) {
			throw new FonctionnelleException("Ce sinistre est inexistant.", new String[0]);
		}
		Double montantRachat = 0.0;
		final List<DetailReglement> listPrestation = (List<DetailReglement>) this.reglement.getListDetailReglement();
		for (final DetailReglement prestation : listPrestation) {
			if (prestation.getCodePrestation().equals("22")) {
				montantRachat = prestation.getMntPrestation();
				final double reserveGrave = 0.0;
				sinBD.setReserveGrave(Double.valueOf(new BigDecimal(reserveGrave).setScale(2, 6).doubleValue()));
				this.reglement.setRefSinistre(sinBD);
				this.sinistreManager.creerMouvementReglement(new Object[] { this.reglement, 4L, this.session });
			}
		}
		if (montantRachat.equals(0.0)) {
			return;
		}
	}

	public void addHistoriqueEtat(final String motifReglement) throws Exception {
		try {
			this.reglementManager
					.addHistoriqueEtat(new Object[] { this.reglement, "Validation R\u00e9glement", this.session });
		} catch (Exception e) {
			this.listErrors.add("Error lors de l'historisation de l'\u00e9tat r\u00e9glement : " + e.getMessage());
			throw new ExceptionMetier("Error lors de l'historisation de l'\u00e9tat r\u00e9glement");
		}
	}

	private Date getDate() throws ParseException {
		return this.dateFormat.parse(this.dateFormat.format(new Date()));
	}

	public Reglement getReglementDB() {
		return this.reglementDB;
	}

	public List<String> getListErrors() {
		return this.listErrors;
	}

	public void emissionNewPositionnement() throws Exception {
		try {
			this.updatePositionnementService();
		} catch (Exception e) {
			this.listErrors.add("Error repositionnement quittance : " + e.getMessage());
			throw new Exception(e.getMessage() + " [" + this.reglement.getNumeroQuittance() + "]", e);
		}
	}

	private void updatePositionnementService() throws ExceptionMetier, FonctionnelleException {
		final Positionnement positionnement = this.getNewPostionnement();
		this.servicesExternes.setConvert(true);
		final List resultat = (List) this.servicesExternes.appelService(
				ServicesExternes.POSITIONNEMENT_REGLEMENT_SANSORD, (Object) positionnement,
				this.reglement.getCodeUserModificateur());
		if (resultat == null || resultat.isEmpty()) {
			throw new ExceptionMetier("Erreur lors du repositionnement, la liste des r\u00e9sultats est null !!");
		}
	}

	private Positionnement getNewPostionnement() throws ExceptionMetier {
		final Positionnement positionnement = new Positionnement();
		positionnement.setUtilisateur(this.reglement.getCodeUserModificateur());
		positionnement.setNomBeneficiaire(this.reglement.getNomBeneficiaire());
		positionnement.setCodeService(this.reglement.getService());
		if (this.reglement.getTypeBeneficiaire() != null) {
			if (this.reglement.getTypeBeneficiaire().equals("7")) {
				positionnement.setTypeDestinataire(Positionnement.TypeDestinataire.INTERMEDIAIRE);
			} else if (this.reglement.getTypeBeneficiaire().equals("9")) {
				positionnement.setTypeDestinataire(Positionnement.TypeDestinataire.ASSURE);
			} else if (this.reglement.getTypeBeneficiaire().equals("12")) {
				positionnement.setTypeDestinataire(Positionnement.TypeDestinataire.AUXILLIAIRE);
			} else {
				positionnement.setTypeDestinataire(Positionnement.TypeDestinataire.AUTRE);
			}
		}
		positionnement.setMntTotalQuittance(String.valueOf(this.reglement.getMontant()));
		final List<QuittanceMoyenPaiement> listQuittances = new ArrayList<QuittanceMoyenPaiement>();
		final QuittanceMoyenPaiement quittanceMP = new QuittanceMoyenPaiement();
		quittanceMP.setNumQuittance(this.reglement.getNumeroQuittance());
		quittanceMP.setNumDossier(this.reglement.getRefSinistre().getNumeroSinistre());
		quittanceMP.setCodeBranche("1");
		if (this.reglement.getRefTypeReglement().getCode().equals("4")) {
			quittanceMP.setNatureQuittance(INatureQuittance.NatureQuittance.RECUPERATION);
		} else {
			quittanceMP.setNatureQuittance(INatureQuittance.NatureQuittance.REGLEMENT);
		}
		quittanceMP.setListDetailReglement(this.reglement.getListDetailReglement());
		listQuittances.add(quittanceMP);
		positionnement.setRefQuittance((List) listQuittances);
		if (this.reglement.getModeReglement().equals("6")) {
			final List<MiseAdispositionSinistre> listMoyenPaiement_MAD = new ArrayList<MiseAdispositionSinistre>();
			final MiseAdispositionSinistre miseAD = new MiseAdispositionSinistre();
			miseAD.setCinBeneficiaire(this.reglement.getRefSinistre().getRefVictime().getNumeroCIN().replace(" ", ""));
			miseAD.setMntRegle((double) this.reglement.getMontant());
			miseAD.setNomBeneficiaireMAD(this.reglement.getNomBeneficiaire());
			listMoyenPaiement_MAD.add(miseAD);
			positionnement.setRefMoyenPaiement((List) listMoyenPaiement_MAD);
		} else if (this.reglement.getModeReglement().equals("1")) {
			final List<ChequeSinistre> listMoyenPaiement_cheque = new ArrayList<ChequeSinistre>();
			final ChequeSinistre chequeSinistre = new ChequeSinistre();
			chequeSinistre.setCode("1");
			chequeSinistre.setLibelle("Ch\u00e8que");
			chequeSinistre.setCompteDe(this.reglement.getNomBeneficiaire());
			chequeSinistre.setEnvoyerA(this.reglement.getNomBeneficiaire());
			if (this.reglement.getVosReference() == null || StringUtils.isEmpty(this.reglement.getVosReference())) {
				chequeSinistre.setVosReference("0");
			} else if (this.reglement.getTypeBeneficiaire() != null
					&& this.reglement.getTypeBeneficiaire().equals("5")) {
				chequeSinistre.setVosReference(this.reglement.getVosReference());
			} else if (this.reglement.getNomBeneficiaire() != null) {
				if (this.reglement.getNomBeneficiaire().length() <= 40) {
					chequeSinistre.setVosReference(this.reglement.getNomBeneficiaire());
				} else {
					chequeSinistre.setVosReference(this.reglement.getNomBeneficiaire().substring(0, 40));
				}
			}
			listMoyenPaiement_cheque.add(chequeSinistre);
			positionnement.setRefMoyenPaiement((List) listMoyenPaiement_cheque);
		}
		return positionnement;
	}

	public void addHistoriqueEtatRecyclage(final String motifReglement) throws Exception {
		try {
			this.reglementManager.addHistoriqueEtat(new Object[] { this.reglement, motifReglement, this.session });
		} catch (Exception e) {
			this.listErrors.add("Error lors de l'historisation de l'\u00e9tat r\u00e9glement : " + e.getMessage());
			throw new ExceptionMetier("Error lors de l'historisation de l'\u00e9tat r\u00e9glement");
		}
	}
}
