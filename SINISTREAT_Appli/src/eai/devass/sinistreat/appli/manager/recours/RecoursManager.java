package eai.devass.sinistreat.appli.manager.recours;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.Condition;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.impl.SessionImpl;

import eai.devass.recoursentrant.application.services.pub.IServiceGestionConvocation;
import eai.devass.recoursentrant.application.services.pub.IServiceGestionJugement;
import eai.devass.recoursentrant.application.services.pub.IServiceGestionProcedureJudiciaire;
import eai.devass.recoursentrant.application.services.pub.IServiceGestionRecoursEntrant;
import eai.devass.recoursentrant.application.utils.entites.IEtat;
import eai.devass.recoursentrant.application.valueobjects.metier.ConvocationVO;
import eai.devass.recoursentrant.application.valueobjects.metier.JugementVO;
import eai.devass.recoursentrant.application.valueobjects.metier.ProcedureJudiciaireVO;
import eai.devass.recoursentrant.application.valueobjects.metier.RecoursVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.contentieux.converters.AudienceJugementVOConverter;
import eai.devass.sinistreat.appli.manager.contentieux.converters.ProcedureJudiciaireVOConverter;
import eai.devass.sinistreat.appli.manager.contentieux.converters.RecoursVOConverter;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.modele.metier.recours.RecoursAmiable;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRecours;
import eai.devass.sinistreat.appli.utils.Config;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.RmiTools;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

@SuppressWarnings("unchecked")
public class RecoursManager extends EntiteManagerAbst implements IConstantes,
		IMessageException {

	private IPersistenceService dao = (IPersistenceService) ServicesFactory
			.getService(IPersistenceService.class);
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	private AudienceJugementVOConverter audienceConverter = new AudienceJugementVOConverter();
	private ProcedureJudiciaireVOConverter procedureConverter = new ProcedureJudiciaireVOConverter();
	private RecoursVOConverter recoursConverter = new RecoursVOConverter();
	SinistreManager sinistreManager = new SinistreManager();

	private Logger logger = Logger.getLogger("loggerSINAT");

	public Session getSession() throws PersistenceException {
		return (Session) dao.getSession();
	}

	/**
	 * Permet de chercher un recours sortant par numero de sinistre.
	 * 
	 * @param object
	 *            Recours à rechercher
	 * @return List de recours sortants
	 * @throws FonctionnelleException
	 */
	public List rechercheRecours(Object object) throws FonctionnelleException {
		try {
			Recours recoursSortant = (Recours) object;
			if (recoursSortant.getRefSinistre() == null
					|| recoursSortant.getRefSinistre().getId() == 0) {
				return null;
			}
			StringBuffer hql = new StringBuffer("from Recours r ");
			hql.append(" where 1=1 ");
			hql.append("  and r.refSinistre.id="
					+ recoursSortant.getRefSinistre().getId()
					+ " and typeRecours='2'");
			
			Query query = getSession().createQuery(hql.toString())
                    .setMaxResults(
                            Long.valueOf(IParam.MAX_SINISTRE).intValue() + 1);
			
			return  query.list();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_RECOURS, e);
			throw new FonctionnelleException(EXP_RECHERCHE_RECOURS,e);
		}
	}

	// MFBO implementer la RG 6.1.1
	/**
	 * Permet de chercher un recours en cours sortant par numero de sinistre.
	 * 
	 * @param object
	 *            Recours à rechercher
	 * @return List de recours sortants en cours
	 * @throws FonctionnelleException
	 */
	public List<Recours> rechercheRecoursEncours(Recours recoursSortant)
			throws FonctionnelleException {
		try {
			if (recoursSortant.getRefSinistre() == null
					|| recoursSortant.getRefSinistre().getId() == 0) {
				return null;
			}
			StringBuffer hql = new StringBuffer("from Recours r ");
			hql.append(" where 1=1 ");
			hql.append("  and r.refSinistre.id="
					+ recoursSortant.getRefSinistre().getId()
					+ " and typeRecours='2'");
			hql.append(" and r.refEtatRecours.code= '1' ");
			
			Query query = getSession().createQuery(hql.toString())
                    .setMaxResults(
                            Long.valueOf(IParam.MAX_SINISTRE).intValue() + 1);
			
			return (List<Recours>) query.list();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_RECOURS, e);
			throw new FonctionnelleException(EXP_RECHERCHE_RECOURS,e);
		}
	}
	
	//Evolution lot1 2023 chercher procedure judiciaire en cours
	public List<Recours> rechercheProcedureEncours(Recours recoursSortant)
			throws FonctionnelleException {
		try {
			if (recoursSortant.getRefSinistre() == null
					|| recoursSortant.getRefSinistre().getId() == 0) {
				return null;
			}
			StringBuffer hql = new StringBuffer("from Recours r, ProcedureJudiciaire p ");
			hql.append(" where 1=1 ");
			hql.append("  and p.refRecours.id = r.id and r.refSinistre.id="
					+ recoursSortant.getRefSinistre().getId()
			        + " and r.typeRecours='1'");			
			Query query = getSession().createQuery(hql.toString())
                    .setMaxResults(
                            Long.valueOf(IParam.MAX_SINISTRE).intValue() + 1);
			
			return (List<Recours>) query.list();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_RECOURS, e);
			throw new FonctionnelleException(EXP_RECHERCHE_RECOURS,e);
		}
	}

	/**
	 * Permet de connaître si au moins un recours a l'état en cours
	 * 
	 * @param Objet
	 * @return Boolean (True s'il existe)
	 * @throws FonctionnelleException
	 */
	public Boolean IsRecoursEncours(Object object)
			throws FonctionnelleException {

		Boolean result = false;

		Recours recoursSortant = (Recours) object;

		List<Recours> recoursEncours = rechercheRecoursEncours(recoursSortant);

		int nbreRecoursEncous = recoursEncours.size();

		if (nbreRecoursEncous > 0) {
			result = true;
		}

		return result;

	}

	/**
	 * Permet de chercher un recours sortant par id.
	 * 
	 * @param object
	 *            Recours à rechercher
	 * @return recours sortant
	 * @throws Exception
	 */
	public Object consulterRecours(Object object) throws FonctionnelleException {
		Recours recoursSortant = (Recours) object;
		if (recoursSortant.getId() == null) {
			return null;
		}
		Recours recours = (Recours) getRecoursByID(recoursSortant.getId());
		return recours;
	}

	private Object getRecoursByID(Long id) throws FonctionnelleException {
		try {
			if (id == null) {
				return null;
			}
			String HQL = " from Recours r where r.id=" + id;
			return (Recours) getSession().createQuery(HQL).uniqueResult();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_RECOURS, e);
			throw new FonctionnelleException(EXP_RECHERCHE_RECOURS,e);
		}

	}

	private String creerRecoursService(Recours recours, IValueObject recoursVo) {
		String idRecours = "";
		try {
			RecoursVO recoursService = (RecoursVO) recoursConverter
					.sinistreToRecoursEntrant(recoursVo);
			// Champs obligatoires service
			recoursService.setNumeroSinistre(recours.getRefSinistre()
					.getNumeroSinistre());
			recoursService.setTypeProcedure(TYPE_RECOURS_SORTANT);
			recoursService.setCodeBranche(BRANCHE_AT);
			recoursService.setEtatRecours(null);
			if (!StringUtils.isEmpty(recoursService.getNumeroSinistre())
					&& recoursService.getNumeroSinistre().length() > 17) {
				int diff = recoursService.getNumeroSinistre().length() - 17;
				recoursService.setNumeroSinistre(recours
						.getRefSinistre()
						.getNumeroSinistre()
						.substring(diff,
								recoursService.getNumeroSinistre().length()));
			} else if (!StringUtils.isEmpty(recoursService.getNumeroSinistre())
					&& recoursService.getNumeroSinistre().length() < 17) {
				String numSinistre = recoursService.getNumeroSinistre();
				StringBuffer buf = new StringBuffer();
				for (int i = numSinistre.length(); i < 17; i++) {
					buf.append("0");
				}
				String dif = buf.toString();
				recoursService.setNumeroSinistre(dif + numSinistre);
			}
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
					logger.error(EXP_RECOURS_SERVICE + " " + codeError);
				} else if (result.getValueObject() != null) {
					idRecours = ((RecoursVO) result.getValueObject()).getId();
				}
			}
		} catch (Exception e) {
			logger.error(EXP_RECOURS_SERVICE, e);
		}
		return idRecours;
	}

	/**
	 * Permet de créer un nouveau recours.
	 * 
	 * @param object
	 *            recours à créer
	 * @return RecoursVo recours créé
	 * @throws ParseException
	 * @throws Exception
	 */
	public Recours creerRecours(Recours recours, IValueObject recoursVo)
			throws FonctionnelleException, ParseException {
		try {

			String idRecours = creerRecoursService(recours, recoursVo);
			if (StringUtils.isNumeric(idRecours)) {
				recours.setIdRecours(idRecours);
			}
			// MFBO@205 : Ajout du numéro de recours
			// Début
			Date dateAccident = null;
			if (recours.getRefSinistre() != null
					&& recours.getRefSinistre().getRefEvenement() != null) {
				dateAccident = recours.getRefSinistre().getRefEvenement()
						.getDateAccident();
			} else {
				dateAccident = new Date();
			}
			recours.setNumeroRecours(getNumeroRecours(dateAccident));
			// Fin
			recours.setDateCreation(new Date());
			recours.setCodeBranche(BRANCHE_AT);
			dao.createObject(recours);

			// mise a jour du sinistre avec le nouveau Numero de recours
			Sinistre sin = recours.getRefSinistre();
			Sinistre sinistre = sinistreManager.getSinistreById(sin.getId());
			sinistre.setIdRecours(recours.getNumeroRecours());
			sinistre.setRecours(true);
			dao.updateObject(sinistre);

			return recours;
		} catch (PersistenceException e) {
			logger.error(EXP_CREATION_RECOURS, e);
			throw new FonctionnelleException(EXP_CREATION_RECOURS,e);
		}
	}

	private void modifierRecoursService(Recours recours, IValueObject recoursVo) {
		try {
			IServiceGestionRecoursEntrant biRE = (IServiceGestionRecoursEntrant) RmiTools
					.getInstance().callService(
							IServiceGestionRecoursEntrant.class,
							IServiceGestionRecoursEntrant.SERVICE_NAME,
							Config.getAdresseRecours());
			RecoursVO recoursService = (RecoursVO) recoursConverter
					.sinistreToRecoursEntrant(recoursVo);
			if (!StringUtils.isEmpty(recoursService.getNumeroSinistre())
					&& recoursService.getNumeroSinistre().length() > 17) {
				int diff = recoursService.getNumeroSinistre().length() - 17;
				recoursService.setNumeroSinistre(recours
						.getRefSinistre()
						.getNumeroSinistre()
						.substring(diff,
								recoursService.getNumeroSinistre().length()));
			} else if (!StringUtils.isEmpty(recoursService.getNumeroSinistre())
					&& recoursService.getNumeroSinistre().length() < 17) {
				String numSinistre = recoursService.getNumeroSinistre();
				StringBuffer buf = new StringBuffer();
				for (int i = numSinistre.length(); i < 17; i++) {
					buf.append("0");
				}
				String dif = buf.toString();
				recoursService.setNumeroSinistre(dif + numSinistre);
			}
			recoursService.setTypeProcedure(TYPE_RECOURS_SORTANT);
			recoursService.setCodeBranche(IConstantes.BRANCHE_AT);
			IResult result = null;
			if (recours.getRefEtatRecours() != null) {
				if (recours.getRefEtatRecours().getCode()
						.equals(IEtat.CLASSE_SANS_SUITE)) {
					// Changement d'etat
					recoursService.setEtatRecours(IEtat.CLASSE_SANS_SUITE);
					result = biRE.classerRecours(recoursService, "1");
				} else if (recours.getRefEtatRecours().getCode()
						.equals(IEtat.CLOTURE)) {
					// Changement d'etat
					recoursService.setEtatRecours(IEtat.CLOTURE);
					result = biRE.cloturerRecours(recoursService, "1");
					if (result == null) {
						logger.error(EXP_RECOURS_SERVICE);
					}

				} 
				
			}
			// Champs obligatoires service
			recoursService.setEtatRecours(null);
			result = biRE.modifierRecoursEntrant(recoursService, "1");
			if (result == null) {
				logger.error(EXP_RECOURS_SERVICE);
			} else if (result.hasErrorMessages()) {
				ErrorMessageItem errorMessage = ((ErrorMessageItem) result
						.getMessagesItem().get(0));
				String codeError = errorMessage.getCodeMessage();
				if (errorMessage.getValues() != null
						&& errorMessage.getValues().length != 0) {
					codeError += ": " + errorMessage.getValues()[0].toString();
				}
				logger.error(EXP_RECOURS_SERVICE + " " + codeError);
			}
		} catch (Exception e) {
			logger.error(EXP_RECOURS_SERVICE, e);
		}
	}

	/**
	 * Permet de modifier un recours éxistant.
	 * 
	 * @param object
	 *            recours à modifier
	 * @return Recours modifié
	 * @throws Exception
	 */

	public Object modifierRecours(Recours recours, IValueObject recoursVo)
			throws FonctionnelleException {
		try {
			Recours recoursDB = (Recours) getRecoursByID(recours.getId());
			if (recoursDB == null) {
				throw new FonctionnelleException(EXP_RECOURS_INEXISTANT);
			}
			modifierRecoursService(recoursDB, recoursVo);
			recours.setDateCreation(recoursDB.getDateCreation());
			recours.setDateModification(new Date());
			recours.setNumeroRecours(recoursDB.getNumeroRecours());
			dao.updateObject(recours);
			return recours;
		} catch (PersistenceException e) {
			logger.error(EXP_MODIF_RECOURS, e);
			throw new FonctionnelleException(EXP_MODIF_RECOURS,e);
		}
	}

	private String creerProcedureJudiciaireService(IValueObject procVO,
			ProcedureJudiciaire proc, String typeJuridiction) {
		String idProcedure = "";
		try {
			ProcedureJudiciaireVO pRvoservice = (ProcedureJudiciaireVO) procedureConverter
					.sinistreToRecoursEntrant(procVO);
			pRvoservice.setTypeJuridiction(typeJuridiction);
			IServiceGestionProcedureJudiciaire bi = (IServiceGestionProcedureJudiciaire) RmiTools
					.getInstance().callService(
							IServiceGestionProcedureJudiciaire.class,
							IServiceGestionProcedureJudiciaire.SERVICE_NAME,
							Config.getAdresseRecours());
			IResult result = bi.creationProcedureJudiciaire(pRvoservice, "1");
			if (result.hasErrorMessages()) {
				ErrorMessageItem errorMessage = ((ErrorMessageItem) result
						.getMessagesItem().get(0));
				String codeError = errorMessage.getCodeMessage();
				if (errorMessage.getValues() != null
						&& errorMessage.getValues().length != 0) {
					codeError += ": " + errorMessage.getValues()[0].toString();
				}
				logger.error(EXP_RECOURS_SERVICE + " " + codeError);
			} else {
				idProcedure = ((ProcedureJudiciaireVO) result.getValueObject())
						.getId();
			}
		} catch (Exception e) {
			logger.error(EXP_RECOURS_SERVICE, e);
		}
		return idProcedure;
	}

	/**
	 * Permet de créer une nouvelle procédure judiciaire
	 * 
	 * @param object
	 *            procédure à créer
	 * @return ProcedureJudiciaireVo créée
	 * @throws FonctionnelleException
	 */
	public Object creerProcedureJudiciaire(IValueObject procVO,
			ProcedureJudiciaire proc) throws FonctionnelleException {
		try {
			Recours recoursDB = (Recours) getRecoursByID(proc.getRefRecours()
					.getId());
			if (recoursDB == null) {
				throw new FonctionnelleException(EXP_RECOURS_INEXISTANT);
			}
			recoursDB.setDateModification(new Date());
			proc.setRefRecours(recoursDB);
			String typeJuridiction = "";
			if (recoursDB.getListeProcedureJudiciaire() == null
					|| recoursDB.getListeProcedureJudiciaire().isEmpty()) {
				typeJuridiction = JURIDICTION_PREMIERE_INSTANCE;
			} else if (recoursDB.getListeProcedureJudiciaire() != null
					&& recoursDB.getListeProcedureJudiciaire().size() == 1) {
				typeJuridiction = JURIDICTION_COURS_APPEL;
			} else if (recoursDB.getListeProcedureJudiciaire() != null
					&& recoursDB.getListeProcedureJudiciaire().size() == 2) {
				typeJuridiction = JURIDICTION_COURS_SUPREME;
			}
			String idProcedure = creerProcedureJudiciaireService(procVO, proc,
					typeJuridiction);
			if (StringUtils.isNotEmpty(idProcedure)
					&& StringUtils.isNumeric(idProcedure)) {
				proc.setIdProcedure(Long.valueOf(idProcedure));
			}
			proc.setDateCreation(new Date());
			proc.setDateModification(new Date());
			dao.updateObject(recoursDB);
			dao.createObject(proc);
			return proc;
		} catch (PersistenceException e) {
			logger.error(EXP_CREATION_PROCEDURE, e);
			throw new FonctionnelleException(EXP_CREATION_PROCEDURE,e);
		}
	}

	/**
	 * Permet de cherhcer une procedure judiciaire par ID.
	 * 
	 * @param object
	 *            procedureJudiciaireVO à rechercher
	 * @return List<ProcedureJudiciaireVO> resultat de la recherche
	 * @throws FonctionnelleException
	 */

	public ProcedureJudiciaire getProcedureById(Long id)
			throws FonctionnelleException {
		try {
			if (id == null) {
				return null;
			}
			String hql = " from ProcedureJudiciaire proc where proc=" + id;
			return (ProcedureJudiciaire) getSession().createQuery(hql)
					.uniqueResult();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_PROCEDURE, e);
			throw new FonctionnelleException(EXP_RECHERCHE_PROCEDURE,e);
		}

	}

	public List getProcedureByIdRecours(Long idRecours) throws Exception {
		try {
			if (idRecours == null) {
				return null;
			}
			String hql = " from ProcedureJudiciaire proc where  proc.refRecours.id="
					+ idRecours + " order by proc.id desc";
			Query query = getSession().createQuery(hql);
			return query.list();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_RECOURS, e);
			throw new FonctionnelleException(EXP_RECHERCHE_RECOURS,e);
		}
	}

	private ProcedureJudiciaireVO modifierProcedureService(
			ProcedureJudiciaireVO procService, String user) {
		try {
			IServiceGestionProcedureJudiciaire bi = (IServiceGestionProcedureJudiciaire) RmiTools
					.getInstance().callService(
							IServiceGestionProcedureJudiciaire.class,
							IServiceGestionProcedureJudiciaire.SERVICE_NAME,
							Config.getAdresseRecours());
			IResult result = bi.modifierProcedureJudiciaire(procService, user);
			if (result != null) {
				if (result.hasErrorMessages()) {
					String codeError = "";
					ErrorMessageItem errorMessage = ((ErrorMessageItem) result
							.getMessagesItem().get(0));
					if (errorMessage.getCodeMessage() != null) {
						codeError = errorMessage.getCodeMessage();
					}
					if (errorMessage.getValues() != null
							&& errorMessage.getValues().length != 0) {
						codeError += ": "
								+ errorMessage.getValues()[0].toString();
					}
					logger.error(EXP_RECOURS_SERVICE + " " + codeError);
				} else {
					procService = (ProcedureJudiciaireVO) result
							.getValueObject();
				}
			}
		} catch (Exception e) {
			logger.error(EXP_RECOURS_SERVICE, e);
		}
		return procService;
	}

	/**
	 * Permer de modifier une procédure judiciaire éxistante
	 * 
	 * @param object
	 *            procédure judiciaire à modifier
	 * @return ProcedureJudiciaireVo modifiée
	 * @throws Exception
	 */
	public Object modifierProcedureJudiciaire(IValueObject procVO,
			ProcedureJudiciaire proc) throws FonctionnelleException {
		try {
			ProcedureJudiciaire procDB = getProcedureById(proc.getId());
			// Chercher la procedure dans la BD SinistreAT
			if (procDB == null) {
				throw new FonctionnelleException(EXP_PROCEDURE_INEXISTANTE);
			}
			ProcedureJudiciaireVO procService = (ProcedureJudiciaireVO) procedureConverter
					.sinistreToRecoursEntrant(procVO);
			proc.setRefRecours(procDB.getRefRecours());
			modifierProcedureService(procService, "1");
			proc.setDateCreation(procDB.getDateCreation());
			proc.setDateModification(new Date());
			if (proc.getDerniereAudience() != null) {
				proc.getDerniereAudience().setRefProcedureJudiciaire(proc);
				if (proc.getDerniereAudience().getId() != null) {
					// modification derniere audience
					modifierAudience(
							((eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO) procVO)
									.getDerniereAudience(), proc
									.getDerniereAudience());
				} else {
					// creation derniere audience
					creerAudience(
							((eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO) procVO)
									.getDerniereAudience(), proc
									.getDerniereAudience());
				}
			}
			dao.updateObject(proc);
			return proc;
		} catch (PersistenceException e) {
			logger.error(EXP_MODIF_PROCEDURE, e);
			throw new FonctionnelleException(EXP_MODIF_PROCEDURE,e);
		}
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
			throws FonctionnelleException {
		try {
			ConvocationVO audienceVO = (ConvocationVO) audienceConverter
					.sinistreToRecoursEntrant((IValueObject) object);
			ProcedureJudiciaire proc = audience.getRefProcedureJudiciaire();
			// jugement
			if (!StringUtils.isEmpty(audience.getTypeJugement())) {
				JugementVO jugementVO = (JugementVO) audienceConverter
						.sinistreToJugementRecoursEntrant((IValueObject) object);
				String typeJugement = jugementVO.getTypeJugement();
				if (proc != null
						&& proc.getRefTypeJuridiction() != null
						&& !StringUtils.isEmpty(proc.getRefTypeJuridiction()
								.getCode())
						&& !StringUtils.isEmpty(typeJugement)) {
					jugementVO.setTypeJugement(validerTypeJugement(proc
							.getRefTypeJuridiction().getCode()));
					audience.setTypeJugement(jugementVO.getTypeJugement());
					String idJugementVo = creerJugementService(jugementVO, "1");
					if (StringUtils.isNumeric(idJugementVo)) {
						audience.setIdJugement(Long.valueOf(idJugementVo));
					}
				} else {
					logger.error(EXP_CREATION_AUDIENCE);
					throw new FonctionnelleException(EXP_CREATION_AUDIENCE);
				}
			}
			String idAudienceVo = creerAudienceService(audienceVO, "1");
			if (StringUtils.isNotEmpty(idAudienceVo)
					&& StringUtils.isNumeric(idAudienceVo)) {
				audience.setIdAudience(Long.valueOf(idAudienceVo));
			}
			audience.setDateCreation(new Date());
			dao.createObject(audience);
			return audience;
		} catch (PersistenceException e) {
			logger.error(EXP_CREATION_AUDIENCE, e);
			throw new FonctionnelleException(EXP_CREATION_AUDIENCE,e);
		}
	}

	private String creerJugementService(JugementVO jugementVO, String string) {
		String idAudience = "";
		try {
			IServiceGestionJugement bi = (IServiceGestionJugement) RmiTools
					.getInstance().callService(IServiceGestionJugement.class,
							IServiceGestionJugement.SERVICE_NAME,
							Config.getAdresseRecours());
			IResult result = bi.creerJugement(jugementVO, "1");
			if (result.hasErrorMessages()) {
				ErrorMessageItem errorMessage = ((ErrorMessageItem) result
						.getMessagesItem().get(0));
				String codeError = errorMessage.getCodeMessage();
				if (errorMessage.getValues() != null
						&& errorMessage.getValues().length != 0) {
					codeError += ": " + errorMessage.getValues()[0].toString();
				}
				logger.error(EXP_RECOURS_SERVICE + " " + codeError);
			} else {
				InfoMessageItem iM = (InfoMessageItem) result.getMessagesItem()
						.get(0);
				idAudience = iM.getValues()[0].toString();
			}
		} catch (Exception e) {
			logger.error(EXP_RECOURS_SERVICE, e);
		}
		return idAudience;
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

	private String creerAudienceService(ConvocationVO audienceVO, String user) {
		String idAudience = "";
		try {
			if (audienceVO.getDateConvocation() == null
					|| audienceVO.getDateConvocation().equals("")) {
				audienceVO.setDateConvocation(dateFormat.format(new Date()));
			}
			IServiceGestionConvocation bi = (IServiceGestionConvocation) RmiTools
					.getInstance().callService(
							IServiceGestionConvocation.class,
							IServiceGestionConvocation.SERVICE_NAME,
							Config.getAdresseRecours());
			IResult result = bi.creerConvocation(audienceVO, "1");
			if (result.hasErrorMessages()) {
				ErrorMessageItem errorMessage = ((ErrorMessageItem) result
						.getMessagesItem().get(0));
				String codeError = errorMessage.getCodeMessage();
				if (errorMessage.getValues() != null
						&& errorMessage.getValues().length != 0) {
					codeError += ": " + errorMessage.getValues()[0].toString();
				}
				logger.error(EXP_RECOURS_SERVICE + " " + codeError);
			} else {
				InfoMessageItem iM = (InfoMessageItem) result.getMessagesItem()
						.get(0);
				idAudience = iM.getValues()[0].toString();
			}
		} catch (Exception e) {
			logger.error(EXP_RECOURS_SERVICE, e);
		}
		return idAudience;
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
			throws FonctionnelleException {
		try {
			ConvocationVO audienceVO = (ConvocationVO) audienceConverter
					.sinistreToRecoursEntrant((IValueObject) object);
			JugementVO jugementVO = (JugementVO) audienceConverter
					.sinistreToJugementRecoursEntrant((IValueObject) object);
			ProcedureJudiciaire proc = audience.getRefProcedureJudiciaire();
			String typeJugement = jugementVO.getTypeJugement();
			if (!StringUtils.isEmpty(audience.getTypeJugement())) {
				if (proc != null
						&& proc.getRefTypeJuridiction() != null
						&& !StringUtils.isEmpty(proc.getRefTypeJuridiction()
								.getCode())
						&& !StringUtils.isEmpty(typeJugement)) {
					jugementVO.setTypeJugement(validerTypeJugement(proc
							.getRefTypeJuridiction().getCode()));
					audience.setTypeJugement(jugementVO.getTypeJugement());
					if (audience.getIdJugement() != null) {
						modifierJugementService(jugementVO, "1");
					} else {
						String idjugement = null;
						idjugement = creerJugementService(jugementVO, "1");
						if (StringUtils.isNotEmpty(idjugement)
								&& StringUtils.isNumeric(idjugement)) {
							audience.setIdJugement(Long.valueOf(idjugement));
						}
					}
				}
			}
			modifierAudienceService(audienceVO, "1");
			audience.setDateModification(new Date());
			dao.updateObject(audience);
			return audience;
		} catch (PersistenceException e) {
			logger.error(EXP_MODIF_AUDIENCE, e);
			throw new FonctionnelleException(EXP_MODIF_AUDIENCE,e);
		}
	}

	private void modifierJugementService(JugementVO jugementVO, String string) {
		try {
			IServiceGestionJugement bi = (IServiceGestionJugement) RmiTools
					.getInstance().callService(IServiceGestionJugement.class,
							IServiceGestionJugement.SERVICE_NAME,
							Config.getAdresseRecours());
			IResult result = bi.modifierJugement(jugementVO, "1");
			if (result.hasErrorMessages()) {
				ErrorMessageItem errorMessage = ((ErrorMessageItem) result
						.getMessagesItem().get(0));
				String codeError = errorMessage.getCodeMessage();
				if (errorMessage.getValues() != null
						&& errorMessage.getValues().length != 0) {
					codeError += ": " + errorMessage.getValues()[0].toString();
				}
				logger.error(EXP_RECOURS_SERVICE + " " + codeError);
			}
		} catch (Exception e) {
			logger.error(EXP_RECOURS_SERVICE, e);
		}

	}

	private void modifierAudienceService(ConvocationVO audienceVO, String user) {
		try {
			IServiceGestionConvocation bi = (IServiceGestionConvocation) RmiTools
					.getInstance().callService(
							IServiceGestionConvocation.class,
							IServiceGestionConvocation.SERVICE_NAME,
							Config.getAdresseRecours());
			IResult result = bi.modifierConvocation(audienceVO, "1");
			if (result.hasErrorMessages()) {
                ErrorMessageItem errorMessage = (ErrorMessageItem) result
                        .getMessagesItem().get(0);
				String codeError = errorMessage.getCodeMessage();
				if (errorMessage.getValues() != null
						&& errorMessage.getValues().length != 0) {
					codeError += ": " + errorMessage.getValues()[0].toString();
				}
				logger.error(EXP_RECOURS_SERVICE + " " + codeError);
			}
		} catch (Exception e) {
			logger.error(EXP_RECOURS_SERVICE, e);
		}
	}

	public Object rouvrirRecours(Object object) throws Exception {

		IResult result = null;
		try {
			Recours recours = (Recours) object;
			Recours recoursDB = (Recours) getRecoursByID(recours.getId());
			if (recoursDB == null) {
				throw new FonctionnelleException("Recours inexistant.");
			}
			RecoursVO recoursVO = new RecoursVO();
			recoursVO.setId(String.valueOf(recoursDB.getIdRecours()));
			recoursVO.setEtatRecours(ETAT_REOUVERT);
			try {
				IServiceGestionRecoursEntrant bi = (IServiceGestionRecoursEntrant) RmiTools
						.getInstance().callService(
								IServiceGestionRecoursEntrant.class,
								IServiceGestionRecoursEntrant.SERVICE_NAME,
								Config.getAdresseRecours());
				result = bi.reouvrirRecours(recoursVO, "1");
				if (result.hasErrorMessages()) {
					ErrorMessageItem errorMessage = ((ErrorMessageItem) result
							.getMessagesItem().get(0));
					String codeError = errorMessage.getCodeMessage();
					if (errorMessage.getValues() != null
							&& errorMessage.getValues().length != 0) {
						codeError += ": "
								+ errorMessage.getValues()[0].toString();
					}
					logger.error("problème service: " + codeError);
				}
			} catch (Exception e) {
				logger.error("problème service: " + e);
			}
			recoursDB.setRefEtatRecours(new EtatRecours());
			recoursDB.getRefEtatRecours().setCode(IConstantes.ETAT_REOUVERT);
			dao.updateObject(recoursDB);
			return recoursDB;
		} catch (Exception e) {
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

	/**
	 * Permet de cherhcer une audience par ID.
	 * 
	 * @param object
	 *            audienceJugementVO à rechercher
	 * @return List<AudienceJugementVO> resultat de la recherche
	 * @throws Exception
	 */
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

	/**
	 * Permet de cherhcer un recours amibale par ID.
	 * 
	 * @param object
	 *            RecoursAmiableVO à rechercher
	 * @return RecoursAmiableVO resultat de la recherche
	 * @throws Exception
	 */
	public Object consulterRecoursAmiable(Object object) throws Exception {

		try {
			RecoursAmiable recoursAmiable = (RecoursAmiable) object;
			RecoursAmiable rRecoursAmiable = null;
			if (recoursAmiable.getId() != null) {
				Condition condition = (Condition) ServicesFactory
						.getService("persistanceCondition");
				condition.addElement(Condition.EQUALTO, "id",
						recoursAmiable.getId());

				rRecoursAmiable = (RecoursAmiable) dao.getObjectByCondition(
						RecoursAmiable.class, condition);
				return rRecoursAmiable;
			} else {
				throw new FonctionnelleException("EXP_ID_REQUIRED");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Permet de chercher un recours amiable par numero de recours associé.
	 * 
	 * @param object
	 *            recoursAmiableVo à rechercher (attributs de recherche
	 *            initialisés)
	 * @return recoursAmiable resultat de la recherche
	 * @throws Exception
	 */
	public Object rechercheRecoursAmiable(Object object) throws Exception {
		try {
			RecoursAmiable recoursAmiable = (RecoursAmiable) object;
			if (recoursAmiable.getRefRecours() != null
					&& recoursAmiable.getRefRecours().getId() != null) {
				String HQL = " from RecoursAmiable r where r.refRecours.id="
						+ recoursAmiable.getRefRecours().getId();
				return (RecoursAmiable) getSession().createQuery(HQL)
						.uniqueResult();

			} else {
				throw new FonctionnelleException("EXP_ID_REQUIRED");
			}
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Permet de créer un nouveau recours amiable.
	 * 
	 * @param object
	 *            recoursAmiable à créer
	 * @return RecoursAmiableVo recours créé
	 * @throws Exception
	 */
	public Object creerRecoursAmiable(Object object) throws Exception {

		try {
			RecoursAmiable recours = (RecoursAmiable) object;

			Recours recoursAssocie = (Recours) getRecoursByID(recours
					.getRefRecours().getId());

			if (recoursAssocie != null) {
				if (recours.getRefRecours() != null) {
					recoursAssocie.setTypeProcedure(recours.getRefRecours()
							.getTypeProcedure());
					dao.updateObject(recoursAssocie);
				}
				dao.createObject(recours);
				return recours;
			} else {
				throw new FonctionnelleException("Recours inexistant.");
			}

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Permet de modifier un recours amiable éxistant.
	 * 
	 * @param object
	 *            recoursAmiable à modifier
	 * @return RecoursAmiableVo modifié
	 * @throws Exception
	 */
	public Object modifierRecoursAmiable(Object object) throws Exception {

		try {
			RecoursAmiable recours = (RecoursAmiable) object;

			Recours recoursAssocie = (Recours) getRecoursByID(recours
					.getRefRecours().getId());
			if (recoursAssocie != null) {
				if (recours.getRefRecours() != null) {
					recoursAssocie.setTypeProcedure(recours.getRefRecours()
							.getTypeProcedure());
					dao.updateObject(recoursAssocie);
				}
				dao.updateObject(recours);

				return recours;
			} else {
				throw new FonctionnelleException("Recours inexistant.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public List getListRecours(Recours recours, PagerVO pager, HashMap mapDates)
			throws FonctionnelleException {
		try {
			if (recours.getTypeRecours() == null) {
				recours.setTypeRecours(TYPE_RECOURS_SORTANT);
			}
			Query query = getSession().createQuery(getQuery(recours, mapDates));
			if (pager != null) {
				Integer numpage = Integer.valueOf(0);
				if (Integer.valueOf(pager.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pager.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage,
						Integer.valueOf(pager.getPageSize()));
			} else {
				query.setMaxResults(IParam.MAX_RESULT);
				return query.list();
			}
		} catch (Exception e) {
			logger.error(EXP_RECHERCHE_RECOURS, e);
			throw new FonctionnelleException(EXP_RECHERCHE_RECOURS,e);
		}
	}

	private String getQuery(Recours recours, HashMap mapDates)
			throws FonctionnelleException {
		if (recours == null) {
			return null;
		}
		String hql = "";
		if (StringUtils.isEmpty(recours.getTypeRecours())
				|| (!StringUtils.isEmpty(recours.getTypeRecours()) && (
				/* recours.getTypeRecours().equals(TYPE_RECOURS_ENTRANT)|| */recours
						.getTypeRecours().equals(TYPE_RECOURS_SORTANT)))) {
			hql = getListRecoursQuery(recours, mapDates);
		} else if (recours.getTypeRecours().equals(TYPE_RECOURS_AMIABLE)) {
			hql = getListRecoursAmiableQuery(recours, mapDates);
		} else if (recours.getTypeRecours().equals(
				TYPE_RECOURS_SANS_VOIE_ENTAMEE)) {
			hql = getListRecoursSansVoieEntameeQuery(recours);
		} else if (recours.getTypeRecours().equals(TYPE_RECOURS_JUDICIAIRE)) {
			hql = getListRecoursJudiciaire(recours, mapDates);
		}
		return hql;
	}

	private String getListRecoursAmiableQuery(Recours recours, HashMap mapDates)
			throws FonctionnelleException {
		StringBuffer hql = new StringBuffer(
				"Select amiable.refRecours from RecoursAmiable amiable where amiable.refRecours.id in (select recours.id ");
		hql.append(getListRecoursQuery(recours, null));
		hql.append(")");
		if (mapDates != null) {
			hql.append(construireDcDate(
					(String) mapDates.get(IDate.DATE_DEBUT),
					(String) mapDates.get(IDate.DATE_FIN),
					"amiable.dateProposition"));
		}
		return hql.toString();
	}

	private String getListRecoursSansVoieEntameeQuery(Recours recours)
			throws FonctionnelleException {
		String hql = getListRecoursQuery(recours, null)
				+ (" and recours.listeProcedureJudiciaire is empty");
		return hql;
	}

	private String getListRecoursJudiciaire(Recours recours, HashMap mapDates)
			throws FonctionnelleException {
		String hql = getListRecoursQuery(recours, null)
				+ (" and recours.listeProcedureJudiciaire is not empty");
		return hql;
	}

	private String getListRecoursQuery(Recours recours, HashMap mapDates)
			throws FonctionnelleException {
		StringBuffer hql = new StringBuffer("from Recours recours  ");

		hql.append(" where 1=1 ");
		// numero Recours
		if (recours.getNumeroRecours() != null) {
			hql.append(" and recours.numeroRecours='")
					.append(recours.getNumeroRecours()).append("' ");
		}
		// sinistre
		if (recours.getRefSinistre() != null) {

			if (!StringUtils.isEmpty(recours.getRefSinistre()
					.getNumeroSinistre())) {
				hql.append(" and recours.refSinistre.numeroSinistre like '%")
						.append(recours.getRefSinistre().getNumeroSinistre()
								.replaceAll("\\s", "")).append("%' ");
			}
			if (!StringUtils.isEmpty(recours.getRefSinistre().getNumeroGrave())) {
				hql.append(" and recours.refSinistre.numeroGrave='")
						.append(recours.getRefSinistre().getNumeroGrave())
						.append("' ");
			}
			if (!StringUtils.isEmpty(recours.getRefSinistre().getCodeClient())) {
				hql.append(" and recours.refSinistre.codeClient='")
						.append(recours.getRefSinistre().getCodeClient())
						.append("' ");
			}
			if (recours.getRefSinistre().getRefEvenement() != null
					&& recours.getRefSinistre().getRefEvenement()
							.getDateAccident() != null) {
				Date dateAccident = recours.getRefSinistre().getRefEvenement()
						.getDateAccident();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				hql.append(
						" and recours.refSinistre.refEvenement.dateAccident= to_date('")
						.append(sdf.format(dateAccident.getTime()))
						.append("', 'dd/MM/yyyy HH24:MI:SS')");
			}
		}
		if (recours.getRefSinistre().getRefEvenement() != null
				&& !StringUtils.isEmpty(recours.getRefSinistre()
						.getRefEvenement().getNumeroEvenement())) {
			hql.append(
					" and recours.refSinistre.refEvenement.numeroEvenement='")
					.append(recours.getRefSinistre().getRefEvenement()
							.getNumeroEvenement()).append("' ");
		}

		if (!StringUtils.isEmpty(recours.getTypeRecours())) {
			hql.append(" and recours.typeRecours='")
					.append(TYPE_RECOURS_SORTANT).append("' ");
		}
		if (recours.getRefEtatRecours() != null
				&& !StringUtils.isEmpty(recours.getRefEtatRecours().getCode())) {
			hql.append(" and recours.refEtatRecours.code='")
					.append(recours.getRefEtatRecours().getCode()).append("' ");
		}
		if (recours.getRefCompagnie() != null
				&& !StringUtils.isEmpty(recours.getRefCompagnie().getCode())) {
			hql.append(" and recours.refCompagnie.code='")
					.append(recours.getRefCompagnie().getCode()).append("' ");
		}
		if (recours.getDateDeclenchement() != null) {
			hql.append(" and recours.dateDeclenchement='")
					.append(dateFormat.format(recours.getDateDeclenchement()))
					.append("' ");
		}
		if (recours.getTypeProcedure() != null) {
			hql.append(" and recours.typeProcedure()='")
					.append(recours.getTypeProcedure()).append("' ");
		}
		if (mapDates != null && !StringUtils.isEmpty(recours.getTypeRecours())) {
			if (recours.getTypeRecours().equals(TYPE_RECOURS_SORTANT)) {
				hql.append(construireDcDate(
						(String) mapDates.get(IDate.DATE_DEBUT),
						(String) mapDates.get(IDate.DATE_FIN),
						"recours.dateDeclenchement"));
			}
		}
		/*** limiter le nombre de ligne à 50 ***/
		return hql.toString();

	}

	private String construireDcDate(String dateDebut, String dateFin,
			String property) throws FonctionnelleException {
		String whereBetween = "";
		try {
			if (dateDebut != null && !StringUtils.isEmpty(dateDebut)
					&& dateFin != null && !StringUtils.isEmpty(dateFin)) {
				// betwen
				whereBetween += " and " + property + " between to_date('"
						+ dateFormat.format(dateFormat.parse(dateDebut))
						+ "  00:00:00','" + IDate.FORMAT_HHMM_SQL + "') "
						+ "and to_date('"
						+ dateFormat.format(dateFormat.parse(dateFin))
						+ " 23:59:59','" + IDate.FORMAT_HHMM_SQL + "')";
			} else {
				if (dateFin != null && !StringUtils.isEmpty(dateFin)) {
					// inferiere a daateFin
					whereBetween += " and " + property + " between to_date('"
							+ IDate.DATE_MIN + " 00:00:00','"
							+ IDate.FORMAT_HHMM_SQL + "') " + "and to_date('"
							+ dateFormat.format(dateFormat.parse(dateFin))
							+ " 23:59:59','" + IDate.FORMAT_HHMM_SQL + "')";

				} else if (dateDebut != null && !StringUtils.isEmpty(dateDebut)) {
					// superier à dateDebut
					whereBetween += " and " + property + " between to_date('"
							+ dateFormat.format(dateFormat.parse(dateDebut))
							+ " 00:00:00','" + IDate.FORMAT_HHMM_SQL + "') "
							+ "and to_date('" + IDate.DATE_MAX + " 23:59:59','"
							+ IDate.FORMAT_HHMM_SQL + "')";
				}
			}
			return whereBetween;
		} catch (ParseException e) {
			logger.error(EXP_RECHERCHE_RECOURS, e);
			throw new FonctionnelleException(EXP_RECHERCHE_RECOURS,e);
		}
	}

	private List getPartCollectionByCondition(Query query, int page,
			int pageSize) throws PersistenceException {
		if (query != null) {
			query.setFirstResult(page * pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} else {
			return null;
		}
	}

	public Long getNombreRecours(Recours recours, HashMap mapDates)
			throws FonctionnelleException {
		try {

			String hql = getQuery(recours, mapDates);
			if (!hql.contains("Select amiable.refRecours")) {
				Query query = getSession().createQuery(
						"select count(*) " + getQuery(recours, mapDates));
				return (Long) query.uniqueResult();
			} else {
				hql = hql.replace("Select amiable.refRecours",
						"select count(*) ");
				Query query = getSession().createQuery(hql);
				return (Long) query.uniqueResult();
			}
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_RECOURS, e);
			throw new FonctionnelleException(EXP_RECHERCHE_RECOURS,e);
		}
	}

	// MFBO@205 : Ajout de la méthode getNumeroRecours
	/**
	 * Construire le numéro de recours à partir de l'année d'exercice
	 * 
	 * @param dateAccident
	 * @return numéro de recours de type String
	 * @throws FonctionnelleException
	 */
	private String getNumeroRecours(Date dateAccident)
			throws FonctionnelleException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateAccident);
		String numRecours = getSequenceNumRecours(cal.get(Calendar.YEAR));
		return numRecours;
	}

	// MFBO@205 : Ajout de la méthode getSequenceNumRecours
	/**
	 * appel de la procédure stocké : PROC_NUM_RECOURS_AT
	 * 
	 * @param annee
	 * @return numéro de recours de type String
	 * @throws FonctionnelleException
	 */
	private synchronized String getSequenceNumRecours(int annee)
			throws FonctionnelleException {
		Connection connection = null;
		CallableStatement call = null;
		try {

			connection = ((SessionImpl) getSession()).connection();
			String sql = "{call PROC_NUM_RECOURS_AT(?,?)}";

			call = connection.prepareCall(sql);
			call.setString(1, String.valueOf(annee));
			call.registerOutParameter(2, Types.VARCHAR);
			call.execute();
			return String.valueOf(call.getString(2));

		} catch (SQLException e) {
			logger.error(EXP_SQL, e);
			throw new FonctionnelleException(EXP_SQL,e);
		} catch (HibernateException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS,e);
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS,e);
		} finally {
			try {
				if (call != null) {
					call.close();
				}
			} catch (SQLException e) {
				logger.error(EXP_SQL, e);
				throw new FonctionnelleException(EXP_SQL,e);
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnée", e2);
					throw new FonctionnelleException(e2.getMessage(),e2);
				} finally {
					connection = null;
				}
			}
		}
	}
	
	public AudienceJugement getLastAudienceJugementByIdProcedure(long id){
		
		try {
			
			String HQL = " from AudienceJugement a where a.refProcedureJudiciaire.id="
					+ id+"order by id desc";
			Query query = getSession().createQuery(HQL);
			List<AudienceJugement> lst=query.list();
			if(Fonctions.isEmpty(lst)){
				return null;
			}else{
				return (AudienceJugement)lst.get(0);
			}
			
			

		} catch (Exception e) {
			logger.error("Audience inexistante.", e);
		return null;
		}
		
	}
}
