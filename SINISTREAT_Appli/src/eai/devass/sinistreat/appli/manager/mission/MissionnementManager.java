package eai.devass.sinistreat.appli.manager.mission;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.MessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import eai.devass.missionnement.services.pub.IServiceGestionMission;
import eai.devass.missionnement.services.pub.IServiceNatureMissionReference;
import eai.devass.missionnement.services.pub.IServicesMissionnementFacade;
import eai.devass.missionnement.usecase.IUseCase;
import eai.devass.missionnement.valueobjects.metier.MissionVO;
import eai.devass.missionnement.valueobjects.metier.ReferenceMissionVO;
import eai.devass.missionnement.valueobjects.metier.ReponseMissionVO;
import eai.devass.missionnement.valueobjects.parametrage.NatureMissionReferenceVO;
import eai.devass.missionnement.valueobjects.parametrage.NatureMissionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.mission.MissionAT;
import eai.devass.sinistreat.appli.modele.metier.mission.MissionPresation;
import eai.devass.sinistreat.appli.modele.metier.mission.ReferenceMissionAT;
import eai.devass.sinistreat.appli.utils.Config;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.RmiTools;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

//import eai.devass.sinistreat.appli.valueobjects.metier.mission.SinMissionATVO;

@SuppressWarnings("unchecked")
public class MissionnementManager extends EntiteManagerAbst implements
		IConstantes, IUseCase {

	private Logger logger = Logger.getLogger("loggerSINAT");
	IPersistenceService dao = (IPersistenceService) ServicesFactory
			.getService(IPersistenceService.class);
	RmiTools rmiTools = RmiTools.getInstance();
	SimpleDateFormat dateFormat = new SimpleDateFormat(IDate.FORMAT_SIMPLE);

	/**
	 * Créer une nouvelle mission
	 * 
	 * @param mission
	 * @return MissionAT
	 * @throws Exception
	 */

	public Object creerMission(MissionAT mission) throws Exception {
		mission.setRefSinistre(new SinistreManager().getSinistreById(mission
				.getRefSinistre().getId()));
		if(mission.getDateEtatMission()==null){
			mission.setDateEtatMission(new Date());
		}
		String idMission = creerMissionService(mission);
		mission.setIdMission(idMission);
		if (mission.isReponseRecu()) {
			mission.setIdReponse(creerReponseService(mission));
		}
		if (mission.getListReference() != null) {
			for (ReferenceMissionAT ref : (List<ReferenceMissionAT>) mission
					.getListReference()) {
				ref.setRefMission(mission);
			}
		}
		if (mission.getListPrestation() != null) {
			for (MissionPresation ref : (List<MissionPresation>) mission
					.getListPrestation()) {
				ref.setRefMission(mission);
			}
		}
		dao.createObject(mission);
		return mission;
	}

	/**
	 * Créer une nouvelle mission à travers du service missionnement
	 * 
	 * @param mission
	 * @return String
	 */
	private String creerMissionService(MissionAT mission) {
		IResult result = null;
		MissionVO missionVO = new MissionVO();
		List<ReferenceMissionVO> listRefMission = new ArrayList<ReferenceMissionVO>();
		String numMission = "";
		try {
			for (int i = 0; i < mission.getListReference().size(); i++) {
				ReferenceMissionAT refat = (ReferenceMissionAT) mission
						.getListReference().get(i);
				ReferenceMissionVO ref = new ReferenceMissionVO();

				ref.setLblReference(refat.getLibelleReference());
				ref.setValeur(refat.getValeur());
				ref.setIdNatureMissionReference(refat.getCodeReference());
				listRefMission.add(ref);
			}
			missionVO.setRefReference(listRefMission);
			copyProperties(missionVO, mission);
			IServiceGestionMission bi = (IServiceGestionMission) rmiTools
					.callService(IServiceGestionMission.class,
							IServiceGestionMission.SERVICE_NAME,
							Config.getAdresseMiss());
			result = bi.validateMission(missionVO, "1");
			if (!result.hasErrorMessages()) {
				InfoMessageItem info = (InfoMessageItem) ((List<MessageItem>) result
						.getMessagesItem()).get(0);
				numMission = (String) ((Object[]) info.getValues())[0];
			} else {
				ErrorMessageItem errorMessage = ((ErrorMessageItem) result
						.getMessagesItem().get(0));
				String codeError = errorMessage.getCodeMessage();
				if (errorMessage.getValues() != null
						&& errorMessage.getValues().length != 0) {
					codeError += ": " + errorMessage.getValues()[0].toString();
				}
				//System.err.println("problème service validateMission: "+ codeError);
				logger.error("problème service validateMission: "+ codeError);
			}
		} catch (Exception e) {
			//System.err.print("Erreur service validateMission: "+ e.getMessage());
			logger.error("problème service validateMission: ", e);
		}
		return numMission;

	}

	/**
	 * Créer une nouvelle réponse à travers du service missionnement
	 * 
	 * @param mission
	 *            , idmission
	 * @return String
	 */
	private String creerReponseService(MissionAT mission)
			throws PersistenceException {

		IResult result = null;
		ReponseMissionVO reponsevo = new ReponseMissionVO();
		String reponseID = "";
		try {
			reponsevo.setMontantFacture(String.valueOf(mission
					.getMontantFacture()));
			reponsevo.setReponse(mission.getReponse());
			MissionVO missVO = new MissionVO();
			missVO.setId(mission.getIdMission());
			reponsevo.setRefMission(missVO);
			HashMap param = new HashMap();
			IServicesMissionnementFacade bi = (IServicesMissionnementFacade) rmiTools
					.callService(IServicesMissionnementFacade.class,
							IServicesMissionnementFacade.SERVICE_NAME,
							Config.getAdresseMiss());
			result = bi.invokeService(reponsevo, ReponseMissionUC, param,
					mission.getCodeUser());

			if (!result.hasErrorMessages()) {
				reponsevo = (ReponseMissionVO) result.getValueObject();
				reponseID = reponsevo.getId();
			} else {
				ErrorMessageItem errorMessage = ((ErrorMessageItem) result
						.getMessagesItem().get(0));
				String codeError = errorMessage.getCodeMessage();
				if (errorMessage.getValues() != null
						&& errorMessage.getValues().length != 0) {
					codeError += ": " + errorMessage.getValues()[0].toString();
				}
				//System.err.println("problème service ReponseMission: "+ codeError);
				logger.error("problème service ReponseMission: "+ codeError);
			}
		} catch (Exception e) {
			//System.err.print("Erreur service ReponseMission: " + e.getMessage());
			logger.error("problème service ReponseMission: ", e);
		}
		return reponseID;

	}

	/**
	 * Copie les propriétés de l'entité vers le VO
	 * 
	 * @param mission
	 *            , missionVO
	 */
	private void copyProperties(MissionVO missionVO, MissionAT mission) {
//		if (mission.getRefPrestataire() != null) {
//			missionVO.setAdressePrestataire(mission.getRefPrestataire()
//					.getAdresse());
//			missionVO.setCodeDomaineActivite(mission.getRefPrestataire()
//					.getRefActivite().getRefDomaineActivite().getCode());
//			missionVO.setCodePrestataire(mission.getRefPrestataire().getCode());
//			missionVO.setIdActivitePrestataire(mission.getRefPrestataire()
//					.getRefActivite().getCode());
//			missionVO.setLibelleActivitePrestataire(mission.getRefPrestataire()
//					.getRefActivite().getLibelle());
//			missionVO.setLibellePrestataire(mission.getRefPrestataire()
//					.getNomRaisonSocial());
//			missionVO.setTypePrestataire(mission.getRefPrestataire().getType());
//		}
		// /
		missionVO.setCodeTypeDossier("1");
		missionVO.setCodeEntiteCreatrice("1");
		missionVO.setCodeEntiteDestinatrice("1");
		missionVO.setCodeGarantie(mission.getRefSinistre().getRefTypeGarantie()
				.getCode());
		// missionVO.setCodeGarantie("2");
		// /

		missionVO.setNumDossier(mission.getRefSinistre().getNumeroSinistre());
		missionVO.setCodeBranche(IParam.CODE_BRANCHE_AT);
		missionVO.setCodeEtat(mission.getRefEtatMission().getCode());
		missionVO.setCodeUtilisateurModif(mission.getCodeUser());
		missionVO.setDateDebut(dateFormat.format(new Date()));
		missionVO.setDateEtat(dateFormat.format(mission.getDateEtatMission()));
		missionVO.setIdNatureMission(mission.getCodeNatureMission());
		missionVO.setInstructions(mission.getInstructions());
		missionVO.setLibelleEtat(mission.getRefEtatMission().getLibelle());
		missionVO.setLibelleNatureMission(mission.getLibelleNatureMission());

	}

	/**
	 * Modifier une mission existante
	 * 
	 * @param mission
	 * @return MissionAT
	 * @throws Exception
	 */

	public Object modifierMission(MissionAT mission) throws Exception {
		MissionAT missionDB = getMissionById(mission.getId());
		if (missionDB == null) {
			throw new Exception("Mission inexistante.");
		}
		modifierMissionService(mission);
		if (mission.isReponseRecu() && !missionDB.isReponseRecu()) {
			mission.setIdReponse(creerReponseService(mission));
		}
		if (mission.getListReference() != null) {
			for (ReferenceMissionAT ref : (List<ReferenceMissionAT>) mission
					.getListReference()) {
				ref.setRefMission(mission);
			}
			for (ReferenceMissionAT refDB : (List<ReferenceMissionAT>) missionDB
					.getListReference()) {
				int indexRef = contientRef(mission.getListReference(), refDB);
				if (indexRef == -1) {
					dao.delete(refDB);
				} else {
					((ReferenceMissionAT) mission.getListReference().get(
							indexRef)).setId(refDB.getId());
				}
			}
			for (MissionPresation ref : (List<MissionPresation>) mission
					.getListPrestation()) {
				ref.setRefMission(mission);
			}
			for (MissionPresation prestationDB : (List<MissionPresation>) missionDB
					.getListPrestation()) {
				int indexPrest = contientPrestation(
						mission.getListPrestation(), prestationDB);
				if (indexPrest == -1) {
					dao.delete(prestationDB);
				} else {
					((MissionPresation) mission.getListPrestation().get(
							indexPrest)).setId(prestationDB.getId());
				}
			}
			dao.updateObject(mission);
		}
		return mission;
	}

	private int contientRef(List listReference, ReferenceMissionAT ref) {
		int indexRef = -1;
		boolean contient = false;
		if (listReference != null) {
			int i = 0;
			while (!contient && i < listReference.size()) {
				ReferenceMissionAT reference = (ReferenceMissionAT) listReference
						.get(i);
				contient = reference.getCodeReference().equals(
						ref.getCodeReference());
				if (contient) {
					indexRef = i;
				}
				i++;
			}
		}
		return indexRef;
	}

	private int contientPrestation(List listPrestation,
			MissionPresation prestation) {
		boolean contient = false;
		int indexPrest = -1;
		if (listPrestation != null) {
			int i = 0;
			while (!contient && i < listPrestation.size()) {
				MissionPresation presta = (MissionPresation) listPrestation
						.get(i);
				contient = presta.getRefPrestation().getCode()
						.equals(prestation.getRefPrestation().getCode());
				if (contient) {
					indexPrest = i;
				}
				i++;
			}
		}
		return indexPrest;
	}

	/**
	 * Cherche une mission par id
	 * 
	 * @param id
	 * @return MissionAT
	 * @throws PersistenceException
	 * @throws HibernateException
	 */
	public MissionAT getMissionById(long id) throws HibernateException,
			PersistenceException {
		String HQL = " from MissionAT m where m.id= " + id;
		return (MissionAT) getSession().createQuery(HQL).uniqueResult();
	}

	/**
	 * Modifier une mission à travers du service missionnement
	 * 
	 * @param mission
	 * @return String
	 */
	private void modifierMissionService(MissionAT mission)
			throws PersistenceException {

		IResult result = null;
		MissionVO missionVO = new MissionVO();
		List<ReferenceMissionVO> listRefMission = new ArrayList<ReferenceMissionVO>();
		try {
			for (int i = 0; i < mission.getListReference().size(); i++) {
				ReferenceMissionAT refat = (ReferenceMissionAT) mission
						.getListReference().get(i);
				ReferenceMissionVO ref = new ReferenceMissionVO();

				ref.setLblReference(refat.getLibelleReference());
				ref.setValeur(refat.getValeur());
				ref.setIdNatureMissionReference(refat.getCodeReference());
				listRefMission.add(ref);
			}
			missionVO.setRefReference(listRefMission);
			copyProperties(missionVO, mission);
			missionVO.setId(mission.getIdMission());
			HashMap param = new HashMap();
			IServicesMissionnementFacade bi = (IServicesMissionnementFacade) rmiTools
					.callService(IServicesMissionnementFacade.class,
							IServicesMissionnementFacade.SERVICE_NAME,
							Config.getAdresseMiss());
			result = bi.invokeService(missionVO, ModifierMissionUC, param,
					mission.getCodeUser());
			if (result.hasErrorMessages()) {
				ErrorMessageItem errorMessage = ((ErrorMessageItem) result
						.getMessagesItem().get(0));
				String codeError = errorMessage.getCodeMessage();
				if (errorMessage.getValues() != null
						&& errorMessage.getValues().length != 0) {
					codeError += ": " + errorMessage.getValues()[0].toString();
				}
				//System.err.println("Problème service ModifierMission: "+ codeError);
				logger.error("Problème service ModifierMission: "+ codeError);
			}
		} catch (Exception e) {
			//System.err.print("Erreur service ModifierMission: "+ e.getMessage());
			logger.error("Problème service ModifierMission: ", e);
		}
	}

	public List rechercheNatureMission(Object object)
			throws PersistenceException {

		IResult result = null;
		try {
			NatureMissionVO natureVO = (NatureMissionVO) object;

			IServicesMissionnementFacade bi = (IServicesMissionnementFacade) rmiTools
					.callService(IServicesMissionnementFacade.class,
							IServicesMissionnementFacade.SERVICE_NAME,
							Config.getAdresseMiss());

			result = bi.invokeService(natureVO, "500", null, "1");
			if (!result.hasErrorMessages()) {
				List<NatureMissionVO> listNatureMissionVO = (List<NatureMissionVO>) result
						.getValueObject();
				// List listNatureMissionVO
				// =(List<Object>)list.get(0);//.get("listMissionVO");
				return listNatureMissionVO;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public List rechercheRefNatMission(Object object)
			throws PersistenceException {

		IResult result = null;
		try {

			NatureMissionReferenceVO natureMisionReferenceVO = new NatureMissionReferenceVO();
			NatureMissionVO natureMissionVO = (NatureMissionVO) object;
			// natureMissionVO.setId(INatureMission.DOSSIER_MEDICAL);
			natureMisionReferenceVO.setRefNatureMission(natureMissionVO);

			IServiceNatureMissionReference bi = (IServiceNatureMissionReference) rmiTools
					.callService(IServiceNatureMissionReference.class,
							IServiceNatureMissionReference.SERVICE_NAME,
							Config.getAdresseMiss());
			result = bi.getReference(natureMisionReferenceVO, "1");

			if (!result.hasErrorMessages()) {
				List<NatureMissionReferenceVO> listRefNatMissionVO = (List<NatureMissionReferenceVO>) result
						.getValueObject();
				return listRefNatMissionVO;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	private String getMissionQuery(MissionAT mission) {
		//String query = "";
		StringBuffer HQL = new StringBuffer("from MissionAT mission  ");

		HQL.append(" where 1=1 ");
		// id
		if (mission.getId() != 0) {
			HQL.append(" and mission.id='").append(mission.getId())
					.append("' ");
		}

		// idMission
		if (!StringUtils.isEmpty(mission.getIdMission())) {
			if (!StringUtils.isEmpty(mission.getIdMission())) {
				HQL.append(" and mission.idMission='")
						.append(mission.getIdMission()).append("' ");
			}
		}

//		if (mission.getRefPrestataire() != null) {
//			// nomRaisonSocial
//			if (!StringUtils.isEmpty(mission.getRefPrestataire()
//					.getNomRaisonSocial())) {
//				if (!StringUtils.isEmpty(mission.getRefPrestataire()
//						.getNomRaisonSocial().trim()))
//					HQL.append(" and mission.refPrestataire.nomRaisonSocial='")
//							.append(mission.getRefPrestataire()
//									.getNomRaisonSocial().trim()).append("' ");
//			}
//
//			// adresse
//
//			if (!StringUtils.isEmpty(mission.getRefPrestataire().getAdresse())) {
//				if (!StringUtils.isEmpty(mission.getRefPrestataire()
//						.getAdresse().trim()))
//					HQL.append(
//							" and upper(mission.refPrestataire.adresse) like '")
//							.append(StringUtils.upperCase(mission
//									.getRefPrestataire().getAdresse().trim()))
//							.append("' ");
//			}
//			// telephone
//			if (!StringUtils
//					.isEmpty(mission.getRefPrestataire().getTelephone())) {
//				if (!StringUtils.isEmpty(mission.getRefPrestataire()
//						.getTelephone().trim()))
//					HQL.append(
//							" and upper(mission.refPrestataire.telephone) like '")
//							.append(StringUtils.upperCase(mission
//									.getRefPrestataire().getTelephone().trim()))
//							.append("' ");
//			}
//			// refDomaineActivite
//
//			// refActivite
//			if (mission.getRefPrestataire().getRefActivite() != null) {
//
//				if (!StringUtils.isEmpty(mission.getRefPrestataire()
//						.getRefActivite().getCode())
//						&& !mission.getRefPrestataire().getRefActivite()
//								.getCode().equals("0")) {
//					HQL.append(" and mission.refPrestataire.refActivite.code='")
//							.append(mission.getRefPrestataire()
//									.getRefActivite().getCode()).append("' ");
//				}
//				if (mission.getRefPrestataire().getRefActivite()
//						.getRefDomaineActivite() != null
//						&& !StringUtils.isEmpty(mission.getRefPrestataire()
//								.getRefActivite().getRefDomaineActivite()
//								.getCode())
//						&& !mission.getRefPrestataire().getRefActivite()
//								.getRefDomaineActivite().getCode().equals("0")) {
//
//					HQL.append(
//							" and mission.refPrestataire.refActivite.refDomaineActivite.code='")
//							.append(mission.getRefPrestataire()
//									.getRefActivite().getRefDomaineActivite()
//									.getCode()).append("' ");
//				}
//			}
//			// ville
//			if (mission.getRefPrestataire().getRefVille() != null
//					&& !StringUtils.isEmpty(mission.getRefPrestataire()
//							.getRefVille().getCode())
//					&& !mission.getRefPrestataire().getRefVille().getCode()
//							.equals("0")) {
//				HQL.append(" and mission.refPrestataire.refVille.code='")
//						.append(mission.getRefPrestataire().getRefVille()
//								.getCode()).append("' ");
//			}
//		}
//		if (mission.getRefSinistre() != null) {
//			// idSinistre
//			if (mission.getRefSinistre().getId() != 0) {
//				HQL.append(" and mission.refSinistre.id=").append(
//						mission.getRefSinistre().getId());
//			}
//			if (!StringUtils.isEmpty(mission.getRefSinistre()
//					.getNumeroSinistre())) {
//				HQL.append(" and mission.refSinistre.numeroSinistre='")
//						.append(mission.getRefSinistre().getNumeroSinistre())
//						.append("' ");
//			}
//		}
		HQL.append(" order by mission.id asc");
		return HQL.toString();
	}

	public Integer getNombreMission(MissionAT mission) throws FonctionnelleException {
		String query = "Select count(id) " + getMissionQuery(mission);
		String getNombreMission = "getNombreMission";
		Integer r = null;
		try {
			r = ((Long) getSession().createQuery(query).uniqueResult())
					.intValue();
		} catch (HibernateException e) {
			logger.error(getNombreMission, e);
			throw new HibernateException(getNombreMission,e);
		} catch (PersistenceException e) {
			logger.error(getNombreMission, e);
			throw new FonctionnelleException(getNombreMission,e);
		}
		return r;
	}

	/**
	 * Cherche une mission
	 * 
	 * @param mission
	 *            , pager
	 * @return List
	 */
	public List rechercheMission(MissionAT mission, PagerVO pager)
			throws Exception {

		if (mission == null) {
			throw new Exception("l'objet mission en entrée ne peut être null");
		}
		Query query = getSession().createQuery(getMissionQuery(mission));
		if (query != null && pager != null && pager.getNumPage() != null
				&& pager.getPageSize() != null) {
			int pageSize = Integer.valueOf(pager.getPageSize());
			int numPage = Integer.valueOf(pager.getNumPage());
			query.setFirstResult(pageSize * (numPage - 1));
			query.setMaxResults(pageSize);
			return query.list();
		}
		return null;
	}

	private Session getSession() throws PersistenceException {
		return (Session) dao.getSession();
	}

}
