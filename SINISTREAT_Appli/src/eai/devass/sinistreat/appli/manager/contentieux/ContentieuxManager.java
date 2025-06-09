package eai.devass.sinistreat.appli.manager.contentieux;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import eai.devass.recoursentrant.application.services.pub.IServiceGestionConvocation;
import eai.devass.recoursentrant.application.services.pub.IServiceGestionJugement;
import eai.devass.recoursentrant.application.services.pub.IServiceGestionProcedureJudiciaire;
import eai.devass.recoursentrant.application.services.pub.IServiceGestionRecoursEntrant;
import eai.devass.recoursentrant.application.utils.entites.IConstantes;
import eai.devass.recoursentrant.application.valueobjects.metier.ConvocationVO;
import eai.devass.recoursentrant.application.valueobjects.metier.JugementVO;
import eai.devass.recoursentrant.application.valueobjects.metier.PartieAdverseJudVO;
import eai.devass.recoursentrant.application.valueobjects.metier.ProcedureJudiciaireVO;
import eai.devass.recoursentrant.application.valueobjects.metier.RecoursVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.manager.contentieux.converters.AudienceJugementVOConverter;
import eai.devass.sinistreat.appli.manager.contentieux.converters.ProcedureJudiciaireVOConverter;
import eai.devass.sinistreat.appli.manager.contentieux.converters.RecoursVOConverter;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.modele.metier.contentieux.PartieAdverseJud;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.modele.parametrage.CoefficientAge;
import eai.devass.sinistreat.appli.utils.Config;
import eai.devass.sinistreat.appli.utils.RmiTools;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;

@SuppressWarnings("unchecked")
public class ContentieuxManager extends EntiteManagerAbst implements
		IConstantes {
	AudienceJugementVOConverter audienceConverter = new AudienceJugementVOConverter();
	ProcedureJudiciaireVOConverter procedureConverter = new ProcedureJudiciaireVOConverter();
	RecoursVOConverter recoursConverter = new RecoursVOConverter();
	SinistreManager sinistreManager = new SinistreManager();
	ParametrageManager parametrageManager = new ParametrageManager();
	private Logger logger = Logger.getLogger("loggerSINAT");
	private static final String FORMAT_DATE ="dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
	String problemeService = "problème service: ";
	public Session getSession() throws PersistenceException {
		return (Session) dao.getSession();
	}

	IPersistenceService dao = (IPersistenceService) ServicesFactory
			.getService(IPersistenceService.class);
	SimpleDateFormat dateFormat = new SimpleDateFormat(IDate.FORMAT_SIMPLE);

	@SuppressWarnings("rawtypes")
	/**
	 * Permet de cherhcer un recours selon différents critères.
	 * 
	 * @param object
	 *            recoursVo à rechercher (attributs de recherche initialisés)
	 * @return recoursVO resultat de la recherche
	 * @throws Exception
	 */
	public List rechercheRecours(Object object) throws PersistenceException {
		Recours recoursSortant = (Recours) object;
		if (recoursSortant.getRefSinistre() != null
				&& recoursSortant.getRefSinistre().getId() != 0) {
			String HQL = " from Recours r where r.refSinistre.id="
					+ recoursSortant.getRefSinistre().getId()
					+ " and typeRecours='1'";
			List<Recours> list = (List) getSession().createQuery(HQL).list();
			return list;
		}
		return null;
	}

	/**
	 * Permet de cherhcer une procedure judiciaire.
	 * 
	 * @param object
	 *            procedureJudiciaireVO à rechercher
	 * @return List<ProcedureJudiciaireVO> resultat de la recherche
	 * @throws Exception
	 */

	public ProcedureJudiciaire getProcedureById(Long id) throws Exception {

		if (id == null) {
			return null;
		}

		String HQL = " from ProcedureJudiciaire proc where proc.id=" + id;
		return (ProcedureJudiciaire) getSession().createQuery(HQL)
				.uniqueResult();

	}

	public List getProcedureByIdRecours(Long idRecours) throws Exception {
		String hql = null;
		if (idRecours == null) {
			return null;
		}

		hql = " from ProcedureJudiciaire proc where  proc.refRecours.id="
				+ idRecours + " order by proc.id desc";
		Query query = getSession().createQuery(hql);

		return query.list();
	}

	/**
	 * Permet de cherhcer une procedure judiciaire par ID.
	 * 
	 * @param object
	 *            procedureJudiciaireVO à rechercher
	 * @return List<ProcedureJudiciaireVO> resultat de la recherche
	 * @throws Exception
	 */
	public Object consulterProcedureJudiciaire(Object object) throws Exception {

		return getProcedureById(((ProcedureJudiciaire) object).getId());
	}

	/**
	 * Permet de créer un nouveau recours.
	 * 
	 * @param object
	 *            recours à créer
	 * @return RecoursVo recours créé
	 * @throws Exception
	 */
	public Recours creerRecours(Recours refRecours, RecoursVO recoursService)
			throws Exception {
		try {
			// Champs obligatoires service
			recoursService.setNumeroSinistre(refRecours.getRefSinistre()
					.getNumeroSinistre());
			recoursService.setCodeBranche(IConstantes.BRANCHE_AT);
			IServiceGestionRecoursEntrant biRE = (IServiceGestionRecoursEntrant) RmiTools
					.getInstance().callService(
							IServiceGestionRecoursEntrant.class,
							IServiceGestionRecoursEntrant.SERVICE_NAME,
							Config.getAdresseRecours());
			IResult result = biRE.creationRecoursEntrant(recoursService, "1");
			if (result != null) {
				if (result.hasErrorMessages()) {
					ErrorMessageItem errorMessage = (ErrorMessageItem) result
							.getMessagesItem().get(0);
					String codeError = errorMessage.getCodeMessage();
					if (errorMessage.getValues() != null
							&& errorMessage.getValues().length != 0) {
						codeError += ": "
								+ errorMessage.getValues()[0].toString();
					}
					logger.error(problemeService + codeError);
				} else if (result.getValueObject() != null) {
					recoursService = (RecoursVO) result.getValueObject();
					if (recoursService != null) {
						refRecours.setIdRecours(recoursService.getId());
					}
				}
			}
		} catch (Exception e) {
			logger.info(problemeService,e);
		}
		/***************/
		refRecours.setDateCreation(new Date());
		refRecours.setCodeBranche(IConstantes.BRANCHE_AT);
		dao.createObject(refRecours);
		return refRecours;
	}

	/**
	 * Permet de modifier un recours éxistant.
	 * 
	 * @param object
	 *            recours à modifier
	 * @return RecoursVo modifié
	 * @throws Exception
	 */
	public Object modifierRecours(Object object, Recours recours)
			throws Exception {
		try {
			RecoursVO rRecours = (RecoursVO) object;
			IServiceGestionRecoursEntrant bi = (IServiceGestionRecoursEntrant) RmiTools
					.getInstance().callService(
							IServiceGestionRecoursEntrant.class,
							IServiceGestionRecoursEntrant.SERVICE_NAME,
							Config.getAdresseRecours());
			IResult result = bi.modifierRecoursEntrant(rRecours, "1");
			if (result != null && result.hasErrorMessages()) {
				ErrorMessageItem errorMessage = (ErrorMessageItem) result
						.getMessagesItem().get(0);
				String codeError = errorMessage.getCodeMessage();
				if (errorMessage.getValues() != null
						&& errorMessage.getValues().length != 0) {
					codeError += ": " + errorMessage.getValues()[0].toString();
				}
				logger.error(problemeService + codeError);
			}
		} catch (Exception e) {
			logger.info(problemeService,e);
		}
		dao.updateObject(recours);
		return recours;

	}

	/**
	 * Permet de créer une nouvelle procédure judiciaire
	 * 
	 * @param object
	 *            procédure à créer
	 * @return ProcedureJudiciaireVo créée
	 * @throws Exception
	 */
	public Object creerProcedureJudiciaire(Object object,
			ProcedureJudiciaire proc) throws Exception {

		IResult result = null;
		try {
			Recours refRecours = proc.getRefRecours();
			List list = rechercheRecours(refRecours);
			if (list == null || list.size() > 1) {
				throw new FonctionnelleException(
						"Erreur: ",
						new String[] { IMessageException.EXP_RECHERCHE_RECOURS });
			}
			ProcedureJudiciaireVO pRvoservice = (ProcedureJudiciaireVO) procedureConverter
					.sinistreToRecoursEntrant((IValueObject) object);
			if (list.isEmpty()) {
				// Le recours n'existe pas
				/* Creation recours service */
				RecoursVO recoursService = (RecoursVO) new RecoursVOConverter()
						.sinistreToRecoursEntrant(((eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO) object)
								.getRefRecours());
				recoursService.setEtatRecours(null);
				refRecours = creerRecours(refRecours, recoursService);
				pRvoservice.setIdRecours(String.valueOf(refRecours
						.getIdRecours()));
				pRvoservice.setTypeJuridiction(JURIDICTION_PREMIERE_INSTANCE);
				// proc.getRefTypeJuridiction().setCode(
			} else {
				// Le recours existe déjà
				refRecours = (Recours) list.get(0);
				pRvoservice.setIdRecours(String.valueOf(refRecours
						.getIdRecours()));
				if (refRecours.getListeProcedureJudiciaire() != null
						&& refRecours.getListeProcedureJudiciaire().size() == 1) {
					pRvoservice.setTypeJuridiction(JURIDICTION_COURS_APPEL);
				} else if (refRecours.getListeProcedureJudiciaire() != null
						&& refRecours.getListeProcedureJudiciaire().size() == 2) {
					pRvoservice.setTypeJuridiction(JURIDICTION_COURS_SUPREME);
				}
			}
			
			try {
				// Appel service
				IServiceGestionProcedureJudiciaire bi = (IServiceGestionProcedureJudiciaire) RmiTools
						.getInstance()
						.callService(
								IServiceGestionProcedureJudiciaire.class,
								IServiceGestionProcedureJudiciaire.SERVICE_NAME,
								Config.getAdresseRecours());
				result = bi.creationProcedureJudiciaire(pRvoservice, "1");
				if (result.hasErrorMessages()) {
					ErrorMessageItem errorMessage = (ErrorMessageItem) result
							.getMessagesItem().get(0);
					String codeError = errorMessage.getCodeMessage();
					if (errorMessage.getValues() != null
							&& errorMessage.getValues().length != 0) {
						codeError += ": "
								+ errorMessage.getValues()[0].toString();
					}
					logger.error(problemeService + codeError);
				} else {
					pRvoservice = (ProcedureJudiciaireVO) result
							.getValueObject();
					proc.setIdProcedure(Long.valueOf(pRvoservice.getId()));
				}
			} catch (Exception e) {
				logger.error(problemeService,e);
			}
			proc.setRefRecours(refRecours);
			proc.setDateCreation(new Date());
			proc.setDateModification(new Date());
			if (proc.getListePartieAdverses() != null) {
				for (PartieAdverseJud partie : (List<PartieAdverseJud>) proc
						.getListePartieAdverses()) {
					partie.setRefProcJudiciaire(proc);
					if (pRvoservice != null
							&& pRvoservice.getListeParties() != null) {
						for (PartieAdverseJudVO partieVO : (List<PartieAdverseJudVO>) pRvoservice
								.getListeParties()) {
							if (memePartie(partie, partieVO)) {
								if (!StringUtils.isEmpty(partieVO.getId())) {
									if (StringUtils.isNumeric(partieVO.getId())) {
										partie.setIdPartieAdverse(Long
												.valueOf(partieVO.getId()));
									}
								}
								break;
							}
						}
					}
				}
			}
			dao.createObject(proc);
			return proc;
		} catch (Exception e) {
		    logger.error("Erreur : Erreur", e);
			throw new FonctionnelleException("Erreur : Erreur",
					new String[] { e.getMessage() });
		}

	}

	private boolean memePartie(PartieAdverseJud partie,
			PartieAdverseJudVO partieVO) {
		return StringUtils.equals(partie.getNom(), partieVO.getNom())
				&& StringUtils.equals(partie.getPrenom(), partieVO.getPrenom())
				&& StringUtils.equals(partie.getNumeroCIN(),
						partieVO.getNumeroCIN());
	}

	/**
	 * Permer de modifier une procédure judiciaire éxistante
	 * 
	 * @param object
	 *            procédure judiciaire à odifier
	 * @return ProcedureJudiciaireVo modifiée
	 * @throws Exception
	 */
	public Object modifierProcedureJudiciaire(IValueObject procVO,
			ProcedureJudiciaire proc) throws Exception {
		try {
			ProcedureJudiciaire procDB = getProcedureById(proc.getId());
			// Chercher la procedure dans la BD SinistreAT
			if (procDB == null) {
				throw new FonctionnelleException("Procedure inexistante.");
			}
			if (procDB.getListePartieAdverses() != null) {
				for (PartieAdverseJud partie : (List<PartieAdverseJud>) procDB
						.getListePartieAdverses()) {
					dao.delete(partie);
				}
			}
			ProcedureJudiciaireVO procService = (ProcedureJudiciaireVO) procedureConverter
					.sinistreToRecoursEntrant(procVO);
			proc.setRefRecours(procDB.getRefRecours());
			try {
				procService = modifierProcedureService(procService, "1");

			} catch (Exception e) {
				logger.error(problemeService ,e);
			}
			proc.setDateCreation(procDB.getDateCreation());
			proc.setDateModification(new Date());
			if (proc.getListePartieAdverses() != null) {
				for (PartieAdverseJud partie : (List<PartieAdverseJud>) proc
						.getListePartieAdverses()) {
					partie.setRefProcJudiciaire(proc);
					if (procService != null
							&& procService.getListeParties() != null) {
						for (PartieAdverseJudVO partieVO : (List<PartieAdverseJudVO>) procService
								.getListeParties()) {
							if (memePartie(partie, partieVO)) {
								if (!StringUtils.isEmpty(partieVO.getId())) {
									if (StringUtils.isNumeric(partieVO.getId())) {
										partie.setIdPartieAdverse(Long
												.valueOf(partieVO.getId()));
										break;
									}
								}
							}
						}
					}
				}
			}
			if(proc.getRefGestionnaire()!=null  && proc.getRefGestionnaire().getCode()==null)
				proc.setRefGestionnaire(null);
				
			dao.updateObject(proc);
			return proc;
		} catch (Exception e) {
			throw e;

		}
	}

	private ProcedureJudiciaireVO modifierProcedureService(
			ProcedureJudiciaireVO procService, String user) throws Exception {
		IResult result = null;
		IServiceGestionProcedureJudiciaire bi = (IServiceGestionProcedureJudiciaire) RmiTools
				.getInstance().callService(
						IServiceGestionProcedureJudiciaire.class,
						IServiceGestionProcedureJudiciaire.SERVICE_NAME,
						Config.getAdresseRecours());
		result = bi.modifierProcedureJudiciaire(procService, user);
		if (result.hasErrorMessages()) {
			String codeError = "";
			ErrorMessageItem errorMessage = (ErrorMessageItem) result
					.getMessagesItem().get(0);
			if (errorMessage.getCodeMessage() != null) {
				codeError = errorMessage.getCodeMessage();
			}
			if (errorMessage.getValues() != null
					&& errorMessage.getValues().length != 0) {
				codeError += ": " + errorMessage.getValues()[0].toString();
			}
			throw new FonctionnelleException(codeError,
					new String[] { IMessageException.EXP_MODIF_PROCEDURE });
		} else {
			procService = (ProcedureJudiciaireVO) result.getValueObject();
		}
		return procService;
	}

	/**
	 * Permet de créer une nouvelle audience
	 * 
	 * @param object
	 *            audience à créer
	 * @return AudienceJugement créée
	 * @throws Exception
	 */
	public Object creerAudience(Object object, AudienceJugement audience)
			throws Exception {
		ConvocationVO audienceVO = (ConvocationVO) audienceConverter
				.sinistreToRecoursEntrant((IValueObject) object);
		String idAudienceVo = "";
		String idJugementVo = "";
		ProcedureJudiciaire proc = audience.getRefProcedureJudiciaire();
		// jugement
		if (!StringUtils.isEmpty(audience.getTypeJugement())) {
			JugementVO jugementVO = (JugementVO) audienceConverter
					.sinistreToJugementRecoursEntrant((IValueObject) object);
			String typeJugement = jugementVO.getTypeJugement();
			if (proc != null
					&& proc.getRefTypeJuridiction() != null
					&& !StringUtils.isEmpty(proc.getRefTypeJuridiction()
							.getCode()) && !StringUtils.isEmpty(typeJugement)) {
				try {
					jugementVO.setTypeJugement(validerTypeJugement(proc
							.getRefTypeJuridiction().getCode()));
					audience.setTypeJugement(jugementVO.getTypeJugement());
					idJugementVo = creerJugementService(jugementVO, "1");
					audience.setIdJugement(Long.valueOf(idJugementVo));
				} catch (Exception e) {
					logger.error(problemeService, e);
				}
			} else {
				throw new FonctionnelleException("Type jugement invalide.");
			}
		}
		// audience
		try {
			idAudienceVo = creerAudienceService(audienceVO, "1");
			audience.setIdAudience(Long.valueOf(idAudienceVo));
		} catch (Exception e) {
			logger.error(problemeService, e);
		}
		audience.setDateCreation(new Date());
		audience.setDateModification(new Date());
		dao.createObject(audience);
		return audience;
	}

	private String creerJugementService(JugementVO jugementVO, String string)
			throws Exception {
		IResult result = null;
		IServiceGestionJugement bi = (IServiceGestionJugement) RmiTools
				.getInstance().callService(IServiceGestionJugement.class,
						IServiceGestionJugement.SERVICE_NAME,
						Config.getAdresseRecours());
		result = bi.creerJugement(jugementVO, "1");
		if (result.hasErrorMessages()) {
			ErrorMessageItem errorMessage = (ErrorMessageItem) result
					.getMessagesItem().get(0);
			String codeError = errorMessage.getCodeMessage();
			if (errorMessage.getValues() != null
					&& errorMessage.getValues().length != 0) {
				codeError += ": " + errorMessage.getValues()[0].toString();
			}
			throw new FonctionnelleException(codeError,
					new String[] { IMessageException.EXP_CREATION_AUDIENCE });
		} else {
			InfoMessageItem iM = (InfoMessageItem) result.getMessagesItem()
					.get(0);
			return iM.getValues()[0].toString();
		}

	}

	private String validerTypeJugement(String typeJuridiction) {
		if (JURIDICTION_PREMIERE_INSTANCE.equals(typeJuridiction)) {
			return JUGEMENT_DE_FOND_PREMIERE_INSTANCE;
		} else if (JURIDICTION_COURS_APPEL.equals(typeJuridiction)) {
			return JUGEMENT_ARRET_ADD;
		} else if (JURIDICTION_COURS_SUPREME.equals(typeJuridiction)) {
			return JUGEMENT_COUR_SUPREME;
		}
		return null;
	}

	private String creerAudienceService(ConvocationVO audienceVO, String user)
			throws Exception {

		IResult result = null;
		IServiceGestionConvocation bi = (IServiceGestionConvocation) RmiTools
				.getInstance().callService(IServiceGestionConvocation.class,
						IServiceGestionConvocation.SERVICE_NAME,
						Config.getAdresseRecours());
		result = bi.creerConvocation(audienceVO, "1");
		if (result.hasErrorMessages()) {
			ErrorMessageItem errorMessage = (ErrorMessageItem) result
					.getMessagesItem().get(0);
			String codeError = errorMessage.getCodeMessage();
			if (errorMessage.getValues() != null
					&& errorMessage.getValues().length != 0) {
				codeError += ": " + errorMessage.getValues()[0].toString();
			}
			throw new FonctionnelleException(codeError,
					new String[] { IMessageException.EXP_CREATION_AUDIENCE });
		} else {
			InfoMessageItem iM = (InfoMessageItem) result.getMessagesItem()
					.get(0);
			return iM.getValues()[0].toString();
		}
	}

	/**
	 * Permet de modifier une audience éxistante
	 * 
	 * @param object
	 *            audience à modifier
	 * @return AudienceJugementVO modifiée
	 * @throws Exception
	 */
	public Object modifierAudience(Object object, AudienceJugement audience)
			throws Exception {

		ConvocationVO audienceVO = (ConvocationVO) audienceConverter
				.sinistreToRecoursEntrant((IValueObject) object);
		JugementVO jugementVO = (JugementVO) audienceConverter
				.sinistreToJugementRecoursEntrant((IValueObject) object);
		ProcedureJudiciaire proc = audience.getRefProcedureJudiciaire();
		
		
		//supprimer audience 
		if(audience.getSuiteMode()!=null && "1".equals(audience.getSuiteMode()) 
			&& audience.getEtatEnCoursAUdience()!=null && "1".equals(audience.getEtatEnCoursAUdience())){
			AudienceJugement audienceN_1=getLastAudienceByIdProcedure(audience);
			if(audienceN_1!=null){
			audienceN_1.setEtatEnCoursAUdience("0");
			dao.updateObject(audienceN_1);
			}
		}
			
		String typeJugement = jugementVO.getTypeJugement();
		if (!StringUtils.isEmpty(audience.getTypeJugement())) {
			if (proc != null
					&& proc.getRefTypeJuridiction() != null
					&& !StringUtils.isEmpty(proc.getRefTypeJuridiction()
							.getCode()) && !StringUtils.isEmpty(typeJugement)) {
				jugementVO.setTypeJugement(validerTypeJugement(proc
						.getRefTypeJuridiction().getCode()));
				audience.setTypeJugement(jugementVO.getTypeJugement());
				try {
					modifierAudienceService(audienceVO, "1");
				} catch (Exception e) {
					logger.error(problemeService, e);
				}
				if (audience.getIdJugement() != null) {
					try {
						modifierJugementService(jugementVO, "1");
					} catch (Exception e) {
						logger.error(problemeService, e);
					}
				} else {
					String idjugement = null;
					try {
						idjugement = creerJugementService(jugementVO, "1");
					} catch (Exception e) {
						logger.error(problemeService, e);
					}
					if (idjugement != null && !"".equals(idjugement)) {
						if (StringUtils.isNumeric(idjugement)) {
							audience.setIdJugement(Long.valueOf(idjugement));
						}
					}
				}
			}
		}
		try {
			modifierAudienceService(audienceVO, "1");
		} catch (Exception e) {
			logger.error(problemeService, e);
		}
		audience.setDateModification(new Date());
		
		dao.updateObject(audience);
		
		
		// mise a jour de la fiche sinistre avec les : IPP jugement, salaire
		// jugement, RG, RO, RP, Date depart rente

		return audience;

	}

	private String modifierJugementService(JugementVO jugementVO, String string)
			throws Exception {
		IResult result = null;
		IServiceGestionJugement bi = (IServiceGestionJugement) RmiTools
				.getInstance().callService(IServiceGestionJugement.class,
						IServiceGestionJugement.SERVICE_NAME,
						Config.getAdresseRecours());
		result = bi.modifierJugement(jugementVO, "1");
		if (result.hasErrorMessages()) {
			ErrorMessageItem errorMessage = (ErrorMessageItem) result
					.getMessagesItem().get(0);
			String codeError = errorMessage.getCodeMessage();
			if (errorMessage.getValues() != null
					&& errorMessage.getValues().length != 0) {
				codeError += ": " + errorMessage.getValues()[0].toString();
			}
			throw new FonctionnelleException(codeError,
					new String[] { IMessageException.EXP_CREATION_AUDIENCE });
		} else {
			InfoMessageItem iM = (InfoMessageItem) result.getMessagesItem()
					.get(0);
			return iM.getValues()[0].toString();
		}

	}

	private String modifierAudienceService(ConvocationVO audienceVO, String user)
			throws Exception {

		
		IResult result = null;
		IServiceGestionConvocation bi = (IServiceGestionConvocation) RmiTools
				.getInstance().callService(IServiceGestionConvocation.class,
						IServiceGestionConvocation.SERVICE_NAME,
						Config.getAdresseRecours());
		result = bi.modifierConvocation(audienceVO, "1");
		if (result.hasErrorMessages()) {
			ErrorMessageItem errorMessage = (ErrorMessageItem) result
					.getMessagesItem().get(0);
			String codeError = errorMessage.getCodeMessage();
			if (errorMessage.getValues() != null
					&& errorMessage.getValues().length != 0) {
				codeError += ": " + errorMessage.getValues()[0].toString();
			}
			throw new FonctionnelleException(codeError,
					new String[] { IMessageException.EXP_CREATION_AUDIENCE });
		} else {
			InfoMessageItem iM = (InfoMessageItem) result.getMessagesItem()
					.get(0);
			return iM.getValues()[0].toString();
		}
	}

	public List getAudienceByIdProcedure(Object object) throws Exception {
		try {
			AudienceJugement audience = (AudienceJugement) object;
			if (audience == null) {
				return null;
			}
			String HQL = " from AudienceJugement a where a.refProcedureJudiciaire.id="
					+ audience.getRefProcedureJudiciaire().getId();
			Query query = getSession().createQuery(HQL);
			return query.list();

		} catch (Exception e) {
			throw e;
		}

	}
	
	public Object getAudienceByID(Long id) throws Exception {
		try {
			if (id == null) {
				return null;
			}
			String HQL = " from AudienceJugement a where a.id=" + id;
			return (Recours) getSession().createQuery(HQL).uniqueResult();
		}  catch (Exception e) {
			throw e;
		}
	}
	
	public AudienceJugement getLastAudienceByIdProcedure(Object object) throws Exception {
		try {
			AudienceJugement audience = (AudienceJugement) object;
			if (audience == null) {
				return null;
			}
			String HQL = " from AudienceJugement a where a.refProcedureJudiciaire.id="
					+ audience.getRefProcedureJudiciaire().getId()+" and a.id!="+audience.getId()+" and a.etatEnCoursAUdience='1'";
			Query query = getSession().createQuery(HQL);
			
			if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
			return (AudienceJugement)query.list().get(0);
			}
			else{
				return null;
				
			}

		} catch (Exception e) {
			throw e;
		}

	}
	
	

	public List rechercheAudience(Object object, PagerVO pagerVO)
			throws Exception {
		try {
			if (object == null) {
				return null;
			}

			Query query = getSession().createQuery(
					getHqlAudience((AudienceJugement) object).toString());
			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage,
						Integer.valueOf(pagerVO.getPageSize()));
			} else {
				return query.list();
			}

		} catch (Exception e) {
			throw e;
		}

	}
	
	public List rechercheAlertAudience(Object object, PagerVO pagerVO)
			throws Exception {
		try {
			if (object == null) {
				return null;
			}

			Query query = getSession().createQuery(
					getHqlAlertAudience((AudienceJugement) object).toString());
			
			/*Date dtUp = ((AudienceJugement) object).getDateCreation();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dtUp); 
			c.add(Calendar.DATE, 10);
			dtUp = c.getTime();
			
			query.setDate("currdateCreation",((AudienceJugement) object).getDateCreation());
			query.setDate("currdateCreationUp",dtUp);*/

			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage,
						Integer.valueOf(pagerVO.getPageSize()));
			} else {
				return query.list();
			}

		} catch (Exception e) {
			throw e;
		}

	}
	
	private StringBuilder getHqlAudience(AudienceJugement audience) {
		
		StringBuilder hql = new StringBuilder(
				" select a from AudienceJugement a,ProcedureJudiciaire p, " +
				" Tribunal t,Recours r,Sinistre s ");

		hql.append(" where 1=1 ");
		hql.append(" and a.refProcedureJudiciaire.id=p.id ");
		hql.append(" and p.refRecours.id=r.id");
		hql.append(" and p.refJuridiction.id=t.id");
		hql.append(" and r.refSinistre.id=s.id");
		if (audience.getDateConvocation() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
			hql.append(" and a.dateConvocation = to_date('")
					.append(sdf.format(audience.getDateConvocation()
							.getTime()))
					.append("', 'dd/MM/yyyy HH24:MI:SS')");
		}
		if (audience.getDateNotification() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
			hql.append(" and dateNotification = to_date('")
					.append(sdf.format(audience.getDateNotification()
							.getTime()))
					.append("', 'dd/MM/yyyy HH24:MI:SS')");
		}
		if (audience.getRefProcedureJudiciaire() != null) {
			if (audience.getRefProcedureJudiciaire()
					.getNumeroDossierTribunal() != null) {
				hql.append(" and p.numeroDossierTribunal ='")
						.append(audience.getRefProcedureJudiciaire()
								.getNumeroDossierTribunal()).append("' ");
			}
			if (audience.getRefProcedureJudiciaire().getRefJuridiction() != null
					&& audience.getRefProcedureJudiciaire()
							.getRefJuridiction().getId() != 0) {
				hql.append(" and t.id ='")
						.append(audience.getRefProcedureJudiciaire()
								.getRefJuridiction().getId()).append("' ");
			}
		}
		if (audience.getRefProcedureJudiciaire() != null
				&& audience.getRefProcedureJudiciaire().getRefRecours() != null
				&& audience.getRefProcedureJudiciaire().getRefRecours()
						.getRefSinistre() != null) {
			String numSinistre = audience.getRefProcedureJudiciaire()
					.getRefRecours().getRefSinistre().getNumeroSinistre();
			if (numSinistre != null) {
				hql.append(" and s.numeroSinistre like'%")
						.append(numSinistre.replaceAll(" ", ""))
						.append("%' ");
			}
		}
		
		return hql;
	}
	
private StringBuilder getHqlAlertAudience(AudienceJugement audience) {

		StringBuilder hql = new StringBuilder(
				" select DISTINCT a from AudienceJugement a,ProcedureJudiciaire p, " +
				" Tribunal t,Recours r,Sinistre s , Reglement rg, Victime v");
		hql.append(" where 1=1 ");
		//hql.append(" and a.dateCreation between :currdateCreation and :currdateCreationUp");
		hql.append(" and TO_DATE(a.dateModification,'dd/mm/yyyy') >= TO_DATE(sysdate-10,'dd/mm/yyyy') ");
		hql.append(" and a.refSortJugement.code in (1,2,3,4) ");
		hql.append(" and a.refProcedureJudiciaire.id=p.id ");
		hql.append(" and p.refRecours.id=r.id");
		hql.append(" and p.refJuridiction.id=t.id");
		hql.append(" and r.refSinistre.id=s.id");
		hql.append(" and s.refVictime.id =v.id");
		hql.append(" and p.id not in (select rgl.refProcedure from Reglement rgl where rgl.refProcedure = p.id)");
		hql.append(" and s.id= rg.refSinistre.id");
		hql.append(" and v.deces = '0'");
		hql.append(" and p.refTypeJuridiction.code = 1");
		
		hql.append(" and s.refTypeGarantie.code in (1,3)");
		hql.append(" order by a.dateDecision");
		
		return hql;
	}
	
	public List getPartCollectionByCondition(Query query, int page, int pageSize) {
		if (query != null) {
			query.setFirstResult(page * pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} else {
			return null;
		}
	}

	public Object getAudienceById(Object object) throws Exception {
		try {
			AudienceJugement audience = (AudienceJugement) object;
			if (audience == null) {
				return null;
			}
			String HQL = " from AudienceJugement a where a.id="
					+ audience.getId();
			return (AudienceJugement) getSession().createQuery(HQL)
					.uniqueResult();

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Permet de récupérer la dernière audience avec jugement d'un sinistre
	 * 
	 * @param object
	 *            Sinistre
	 * @return Procedure judiciaire
	 * @throws Exception
	 */
	public Object getDerniereAudienceProcedure(Object object) throws Exception {
		try {
			Sinistre s = (Sinistre) object;
			if (s == null) {
				return null;
			}
			String hql = "select p from ProcedureJudiciaire p where p.id = "
					+ "(select max(p1.id) from ProcedureJudiciaire p1 where p1.refRecours.refSinistre.id ="
					+ s.getId() + " and p1.listeAudiences is not empty)";

			ProcedureJudiciaire proc = (ProcedureJudiciaire) getSession()
					.createQuery(hql).uniqueResult();
			if (proc != null && proc.getId() != null) {
				hql = "select a from AudienceJugement a where a.id = "
						+ "(select max(a1.id) from AudienceJugement a1 where a1.refProcedureJudiciaire.id ="
						+ proc.getId() + ")";
				proc.setDerniereAudience((AudienceJugement) getSession()
						.createQuery(hql).uniqueResult());
			}
			return proc;

		} catch (Exception e) {
		    logger.error("Erreur : ", e);
			return null;
		}

	}

	/**
	 * Permet de récupérer la dernière audience avec jugement d'un sinistre
	 * 
	 * @param object
	 *            Sinistre
	 * @return Procedure judiciaire
	 * @throws Exception
	 */
	public Object getDerniereAudienceProcedure(Object object,
			String numeroDossierTribunale) throws Exception {
		try {
			Sinistre s = (Sinistre) object;
			if (s == null || numeroDossierTribunale == null) {
				return null;
			}
			String numTribunal = numeroDossierTribunale.toLowerCase();
			String hql = "select p from ProcedureJudiciaire p where p.id = "
					+ "(select max(p1.id) from ProcedureJudiciaire p1 where p1.refRecours.refSinistre.id ="
					+ s.getId() + " and p1.numeroDossierTribunal = '"
					+ numTribunal + "'"
					+ " and p1.listeAudiences is not empty)";

			ProcedureJudiciaire proc = (ProcedureJudiciaire) getSession()
					.createQuery(hql).uniqueResult();
			if (proc != null && proc.getId() != null) {
				hql = "select a from AudienceJugement a where a.id = "
						+ "(select max(a1.id) from AudienceJugement a1 where a1.refProcedureJudiciaire.id ="
						+ proc.getId() + ")";
				proc.setDerniereAudience((AudienceJugement) getSession()
						.createQuery(hql).uniqueResult());
			}

			if (proc == null) {
				throw new FonctionnelleException(
						IMessageException.EXP_PROCEDURE_INEXISTANTE);
			}
			return proc;

		} catch (Exception e) {
			logger.error(IMessageException.EXP_PROCEDURE_INEXISTANTE, e);
			throw e;
		}

	}

	/**
	 * Permet de supprimer une audience en la cherchant par ID
	 * 
	 * @param object
	 *            audienceJugementVO à supprimer
	 * @return boolean
	 * @throws Exception
	 */
	public boolean supprimerAudienceJugement(Object object,
			AudienceJugement audience) throws Exception {
		dao.delete(audience);
		
		return true;

	}

	public Long getNombreAudiance(AudienceJugement audience)
			throws Exception {

		String hql = getHqlAudience(audience).toString();
		hql = hql.replaceAll("select a", "select count(a)");
		return(Long) getSession().createQuery(hql).uniqueResult();
	}
	
	public Long getNombreAlertAudiance(AudienceJugement audience)
			throws Exception {
		String hql = getHqlAlertAudience(audience).toString();
		hql = hql.replaceAll("select DISTINCT a", "select COUNT(DISTINCT a)");
		Query query = getSession().createQuery(hql);

		/*Date dtUp = audience.getDateCreation();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dtUp); 
		c.add(Calendar.DATE, 10);
		dtUp = c.getTime();
		
		query.setDate("currdateCreation", audience.getDateCreation());
		query.setDate("currdateCreationUp", dtUp);*/
		return(Long) query.uniqueResult();
	}

	public Boolean validerDoubleProcedure(ProcedureJudiciaire proc) {
		Boolean trouver = false;
		try {
			String hql = "from ProcedureJudiciaire pr ,Recours r ,Sinistre s where pr.refRecours.id=r.id and "
					+ "r.refSinistre.id=s.id and pr.numeroDossierTribunal like :numeroDossierTribunal " +
							"and pr.refJuridiction.id like :codeTribunal and s.numeroSinistre like :numeroSinistre  ";
			Query q = getSession().createQuery(hql);
			q.setParameter("numeroDossierTribunal",
					proc.getNumeroDossierTribunal());
			q.setParameter("codeTribunal", proc.getRefJuridiction().getId());
			q.setParameter("numeroSinistre", proc.getRefRecours()
					.getRefSinistre().getNumeroSinistre());
			List listProcedure = q.list();
			if (listProcedure != null && !listProcedure.isEmpty()) {
				trouver = true;
			}
		} catch (HibernateException e) {
			logger.error("Error: hibernate: validation double procedure", e);
		} catch (PersistenceException e) {
			logger.error("Error: persistence: validation double procedure", e);
		}
		return trouver;

	}

	public Boolean validerDoubleAudiance(AudienceJugement audience) {
		Boolean numeroAudiance = false;
		try {
			String hql = "from AudienceJugement aj , ProcedureJudiciaire pr where aj.refProcedureJudiciaire.id= pr.id"
					+ " and aj.dateConvocation = to_date(:dateConvocation , 'dd/MM/yyyy HH24:MI:SS')" 
					+ " and aj.dateNotification = to_date(:dateNotification , 'dd/MM/yyyy HH24:MI:SS')"
					+ " and pr.id =:id";
			Query q = getSession().createQuery(hql);
			q.setParameter("dateConvocation",
					sdf.format(audience.getDateConvocation()));
			q.setParameter("dateNotification",
					sdf.format(audience.getDateNotification()));
			q.setParameter("id", audience.getRefProcedureJudiciaire().getId());
			List listAudiance = q.list();
			if (listAudiance != null && !listAudiance.isEmpty()) {
				numeroAudiance = true;

			}
		} catch (HibernateException e) {
			logger.error("Error: Hibernate: validation double audiance", e);
		} catch (PersistenceException e) {
			logger.error("Error: Persistence: validation double audiance", e);
		}
		return numeroAudiance;
	}

	public List rechercheRecoursByNumeroSinistre(String numeroSinistre) throws FonctionnelleException, HibernateException, PersistenceException {
		Long idSin = sinistreManager.getSinistreByNum(numeroSinistre).getId();
		if (idSin != null) {
			String HQL = " from Recours r where r.refSinistre.id="
					+ idSin
					+ " and typeRecours='1'";
			List<Recours> list = (List) getSession().createQuery(HQL).list();
			return list;
		}
		return null;
	}
	
	
	
	public Object calculerRenteAudience(Object object, AudienceJugement audience)
			throws Exception {

		Double reserve = 0.0;
		CoefficientAge cofAge = null;
		ProcedureJudiciaire proc = audience.getRefProcedureJudiciaire();
		Recours recours = proc.getRefRecours();
		Sinistre sinistre = recours.getRefSinistre();
		sinistre.setIpp(audience.getIppJugement());
		sinistre.setDateCalculReserve(audience.getDateDepartRente());
		if (sinistre.getRefVictime() != null) {
			Victime vic = sinistre.getRefVictime();
			// si dï¿½cï¿½s ajout des ayant droit s'ils existent
			
			if(!vic.getDeces()) {
				cofAge = parametrageManager.getCoefParSin(sinistre);
				reserve = cofAge.getCoefficient()*Double.valueOf(audience.getMontantRente());
				reserve = new BigDecimal(reserve).setScale(2,
						BigDecimal.ROUND_HALF_EVEN)
						.doubleValue();
				audience.setReserveGrave(reserve);
			} else if(vic.getDeces()) {
			
			List<AyantDroit> listAY = vic.getListAyantDroit();
			if (listAY != null) {
			
				List<AyantDroit> listAyantDroit = new ArrayList<AyantDroit>();
				for (AyantDroit ay : listAY) {
					reserve = getReserveGraveAY(ay, sinistre);
					reserve = new BigDecimal(reserve).setScale(2,
							BigDecimal.ROUND_HALF_EVEN)
							.doubleValue();
					ay.setMontantReserveJugement(reserve);
					listAyantDroit.add(ay);
				}
				vic.setListAyantDroit(listAyantDroit);
				dao.updateObject(vic);
			}
			
		
		}
		}
		// mise a jour de la fiche sinistre avec les : IPP jugement, salaire
		// jugement, RG, RO, RP, Date depart rente

		return audience;

	}
	public Double getReserveGraveAY(AyantDroit ayant, Sinistre sin)
			throws Exception {
		
		if (ayant == null) {
			return Double.valueOf(0);
		}
		Double coefAge = getCoefAgeAY(ayant, sin);
		Double reserve = ayant.getMontantRenteJugement() * coefAge;
		reserve = new BigDecimal(reserve).setScale(2,
				BigDecimal.ROUND_HALF_EVEN).doubleValue();

		return reserve;
	}
	
	public Double getCoefAgeAY(AyantDroit ay, Sinistre sin) throws Exception {
		if (sin == null) {
			return Double.valueOf(0);
		}
		if (ay == null) {
			return Double.valueOf(0);
		}

		CoefficientAge cofage = parametrageManager.getCoefParAY(sin, ay);
		if (cofage != null) {
			return cofage.getCoefficient();
		} else {
			return Double.valueOf(19.691);
		}
	}
	
	
	
}
