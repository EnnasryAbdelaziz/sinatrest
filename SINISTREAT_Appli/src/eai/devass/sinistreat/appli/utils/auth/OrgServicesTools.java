package eai.devass.sinistreat.appli.utils.auth;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.impl.MessageItem;

import com.eai.org.accesseur.services.IConsulterOrg;
import com.eai.org.accesseur.valueObject.ConsultationVO;
import com.eai.org.accesseur.valueObject.EntiteVO;
import com.eai.org.accesseur.valueObject.ListeOrgVO;
import com.eai.org.accesseur.valueObject.SimpleOrgVO;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.utils.BundleTools;
import eai.devass.sinistreat.appli.utils.RmiTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EntiteOrgVO;


/* @author kchakib : 26 oct. 10 */
public class OrgServicesTools implements IVariableOrgService,IMessageException {
	
	private static OrgServicesTools instance;
	private IResult result;
	//wmos: 01/10/2014 - correction sonar
	//private static List<EntiteOrgVO> listEntiteOrg = new ArrayList<EntiteOrgVO>();
	
	/** service ORG */
	private volatile static IConsulterOrg consulterOrgService;
	
	public synchronized static OrgServicesTools getInstance(){
		if (instance == null) {
			instance = new OrgServicesTools();
		}
		
		return instance; 
	}
	
	/**
	 * Récupérer le code entité de l'utilisateur via l'appel de service ORG  
	 * @param codeUtilisateur
	 * @return
	 * @throws Exception
	 */
	public String getCodeEntite(String codeUtilisateur) throws Exception {
		
		return "001";
	
	}
	
	//wmos: 01/10/2014 - correction sonar
//	public List<EntiteOrgVO> getListEntite() throws FonctionnelleException {
//		try {
//			List<EntiteOrgVO> listDirs;
//			if (listEntiteOrg.isEmpty()) {
//				listDirs = getAllDirections();
//				listEntiteOrg = listDirs;
//			} else {
//				listDirs = listEntiteOrg;
//			}
//			return listDirs;
//		} catch (FonctionnelleException e) {
//			throw e;
//		}
//		catch (Exception e) {
//			throw new FonctionnelleException(e.getMessage());
//		}	
//	}
	
	/** Chercher toutes les directions de RMA WATANYA
	 * 
	 * @return
	 * @throws FonctionnelleException
	 */
	public List<EntiteOrgVO> getAllDirections() throws FonctionnelleException {
		
		List<EntiteOrgVO> listOut = new ArrayList<EntiteOrgVO>();
		
		try {
			// Récupérer le service ORG
			result = getConsulterOrgService().consulterOrg(
					getConsultationVO(RADICAL_ETABLISSEMENT, RESULT_DIRECTION));
			if (result.hasErrorMessages()) {
				throw new FonctionnelleException(getLibelleErrorMessage());
			}
			// Récupérer la liste des resultats
			List<SimpleOrgVO> listSimpleORG = ((ListeOrgVO) result
					.getValueObject()).getListeSimpleOrg();
			for (SimpleOrgVO simpleOrgVO : listSimpleORG) {

				listOut.add(new EntiteOrgVO(simpleOrgVO.getIdEntite(),
						simpleOrgVO.getLibelleLong()));
			}
		} catch(Exception e) {
			throw new FonctionnelleException(EXP_ORG_SERVICE_INDISPONIBLE, 
					BundleTools.getInstance().getDefaultMessage(HOST_ORG));
		}	
		return listOut;
	}
	
	/** Chercher tous les agents de RMA WATANYA
	 * 
	 * @return
	 * @throws FonctionnelleException
	 */
	public List<EntiteOrgVO> getAllAgents() throws FonctionnelleException {
		
		List<EntiteOrgVO> listOut = new ArrayList<EntiteOrgVO>();
		
		// Récupérer le service ORG
		result = getConsulterOrgService().consulterOrg(
				getConsultationVO(RADICAL_ETABLISSEMENT, RESULT_AGENT));
		
		if(result.hasErrorMessages()) {
			throw new FonctionnelleException(getLibelleErrorMessage());
		}
		
		// Récupérer la liste des resultats
		List<SimpleOrgVO> listSimpleORG = ((ListeOrgVO) result.getValueObject())
				.getListeSimpleOrg();
		
		for (SimpleOrgVO simpleOrgVO : listSimpleORG) {
			
			listOut.add(new EntiteOrgVO(simpleOrgVO.getIdPdv(), simpleOrgVO
					.getLibelleLong()));
		}
		
		return listOut;
	}
	
	/** Chercher tous les courties de RMA WATANYA
	 * 
	 * @return
	 * @throws FonctionnelleException
	 */
	public List<EntiteOrgVO> getAllCourties() throws FonctionnelleException {
		
		List<EntiteOrgVO> listOut = new ArrayList<EntiteOrgVO>();
		
		// Récupérer le service ORG
		result = getConsulterOrgService().consulterOrg(
				getConsultationVO(RADICAL_ETABLISSEMENT, RESULT_COURTIER));
		
		if(result.hasErrorMessages()) {
			throw new FonctionnelleException(getLibelleErrorMessage());
		}
		
		// Récupérer la liste des resultats
		List<SimpleOrgVO> listSimpleORG = ((ListeOrgVO) result.getValueObject())
				.getListeSimpleOrg();
		
		for (SimpleOrgVO simpleOrgVO : listSimpleORG) {
			
			listOut.add(new EntiteOrgVO(simpleOrgVO.getIdPdv(), simpleOrgVO
					.getLibelleLong()));
		}
		
		return listOut;
	}
	
	/** Chercher tous les courties de RMA WATANYA
	 * 
	 * @return
	 * @throws FonctionnelleException
	 */
	public List<EntiteOrgVO> getAllBGDs() throws FonctionnelleException {
		
		List<EntiteOrgVO> listOut = new ArrayList<EntiteOrgVO>();
		
		// Récupérer le service ORG
		result = getConsulterOrgService().consulterOrg(
				getConsultationVO(RADICAL_ETABLISSEMENT, RESULT_BGD));
		
		if(result.hasErrorMessages()) {
			throw new FonctionnelleException(getLibelleErrorMessage());
		}
		
		// Récupérer la liste des resultats
		List<SimpleOrgVO> listSimpleORG = ((ListeOrgVO) result.getValueObject())
				.getListeSimpleOrg();
		
		for (SimpleOrgVO simpleOrgVO : listSimpleORG) {
			
			listOut.add(new EntiteOrgVO(simpleOrgVO.getIdPdv(), simpleOrgVO
					.getLibelleLong()));
		}
		
		return listOut;
	}
	
	/** Chercher tous les intermediaiares de RMA WATANYA
	 * 
	 * @return
	 * @throws FonctionnelleException
	 */
	public List<EntiteOrgVO> getAllIntermediaiares() throws FonctionnelleException {
		
		List<EntiteOrgVO> listOut = new ArrayList<EntiteOrgVO>();
		
		// Construire le 'consultationVO' adéquat pour service ORG
		ConsultationVO consultationVO = getConsultationVO(RADICAL_ETABLISSEMENT, 
				RESULT_AGENT + "/" + RESULT_BGD + "/" + RESULT_COURTIER + "/");
		consultationVO.setResultatMulti("O");
		result = getConsulterOrgService().consulterOrg(consultationVO);
		
		if(result.hasErrorMessages()) {
			throw new FonctionnelleException(getLibelleErrorMessage());
		}
		
		// Récupérer la liste des resultats
		List<SimpleOrgVO> listSimpleORG = ((ListeOrgVO) result.getValueObject())
				.getListeSimpleOrg();
		
		for (SimpleOrgVO simpleOrgVO : listSimpleORG) {
			listOut.add(new EntiteOrgVO(simpleOrgVO.getIdPdv(), simpleOrgVO
					.getLibelleLong()));
		}
		
		return listOut;
	}
	
	/** Chercher l'arbre compléte de l'entité d'entrée [toutes ces filles et petites petites ... filess ...]
	 * 
	 * @return
	 * @throws FonctionnelleException
	 */
	public EntiteOrgVO getArbreEntite(EntiteOrgVO entiteOrgVO) throws FonctionnelleException {
		
		List<EntiteOrgVO> listOut = new ArrayList<EntiteOrgVO>();
		
		// Construire le 'consultationVO' adéquat pour service ORG
		ConsultationVO consultationVO = getConsultationVO(RADICAL_ENT, RESULT_ENT);
		consultationVO.setIdBorneInf(RACINE_RMAWATANYA + entiteOrgVO.getCode());
		consultationVO.setIdNonSignifcatif(null);
		
		// Exécuter la recherche
		try {
			try {
				result = getConsulterOrgService().consulterOrg(consultationVO);
			} catch (RuntimeException e) {
				throw new FonctionnelleException(EXP_ORG_SERVICE_INDISPONIBLE, 
						BundleTools.getInstance().getDefaultMessage(HOST_ORG));
			}			
		} catch (Exception e) {
			throw new FonctionnelleException(EXP_ORG_SERVICE_INDISPONIBLE, 
					BundleTools.getInstance().getDefaultMessage(HOST_ORG));
		}
		if(result.hasErrorMessages()) {
			return entiteOrgVO;
		}
					
		// Récupérer la liste des resultats
		List<SimpleOrgVO> listSimpleORG = ((ListeOrgVO) result.getValueObject())
				.getListeSimpleOrg();
		
		for (SimpleOrgVO simpleOrgVO : listSimpleORG) {
			EntiteOrgVO curEntiteOrgVO = getArbreEntite(new EntiteOrgVO(
					simpleOrgVO.getIdEntite(), simpleOrgVO.getLibelleLong()));
			curEntiteOrgVO.setActivite(simpleOrgVO.getActivite());
			
			listOut.add(curEntiteOrgVO);
		}
		
		entiteOrgVO.setRefEntiteOrgVOs(listOut);
		
		return entiteOrgVO;
	}
	
	/** Récupérer toutes les informations pour une entité de RMA WATANYA
	 * 
	 * @return
	 * @throws FonctionnelleException
	 */
	public EntiteVO getInformationsEntitie(String codeEntite) throws FonctionnelleException {
		
		// Construire le 'consultationVO' adéquat pour le service ORG
		ConsultationVO consultationVO = getConsultationVO(RADICAL_ENT, RESULT_ENT);
		consultationVO.setIdBorneInf(RACINE_RMAWATANYA + codeEntite);
		consultationVO.setIdNonSignifcatif(null);
		consultationVO.setTypeAcces("U");
		result = getConsulterOrgService().consulterOrg(consultationVO);
		
		if(result.hasErrorMessages()) {
			throw new FonctionnelleException(getLibelleErrorMessage());
		}
		
		// Récupérer la liste des resultats
		return (EntiteVO) (result.getValueObject());
		
		
	}
	
	
	private ConsultationVO getConsultationVO(String radObjetInterroge, String result) {
		
		ConsultationVO consultationVO = new ConsultationVO();
        consultationVO.setCodeUtilisateur("test");
        consultationVO.setCodeApplication("test");
        consultationVO.setCodeActivite("O");
        consultationVO.setModeAppel("T");
        consultationVO.setTypeAcces("L");
        consultationVO.setRadObjetInterroge(radObjetInterroge);
        consultationVO.setIdNonSignifcatif("2");
        consultationVO.setResultatMulti("N");
        consultationVO.setResultat(result);
        consultationVO.setBorneInfResultat("");
        consultationVO.setBorneSupResultat("");
        consultationVO.setTri("O");
		
		return consultationVO;
	}
	
	private static IConsulterOrg getConsulterOrgService() throws FonctionnelleException {
		
		// Initialiser le service RMI : IConsulterOrg : ORG
		try {	
			if(consulterOrgService == null) {
				consulterOrgService = (IConsulterOrg) RmiTools.getInstance()
						.callService(IConsulterOrg.class, "consulterOrg",
								BundleTools.getInstance().getDefaultMessage(HOST_ORG));
			}
			
		} catch(Exception e) {
			throw new FonctionnelleException(EXP_ORG_SERVICE_INDISPONIBLE, 
					BundleTools.getInstance().getDefaultMessage(HOST_ORG));
		}
		
		return consulterOrgService;
	}
	
	private String getLibelleErrorMessage() {
		
		List<MessageItem> listMessageItem = result.getMessagesItem();
		for(MessageItem messageItem : listMessageItem) {
			
			// C'est sur que le result conteint un seul MessageErrorItem
			return messageItem.getLibelleMessage();
		}
		
		return null;
	}
	
	

}


