package eai.devass.sinistreat.appli.utils.auth;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.securite.IAutorisationManager;

import com.eai.sibea.asalaccesseur.accesseur.valueobjects.CheckUserExVO;
import com.eai.sibea.asalaccesseur.accesseur.valueobjects.UserItem;
import com.eai.sibea.asalaccesseur.accesseur.valueobjects.UsersListVO;
import com.eai.sibea.sasappel.authentixcopy.valueobjects.SetRessourcesVO;
import com.eai.sibea.sasappel.rmiservices.ICallServices;

import eai.devass.sinistreat.appli.authentification.Fonctionnalite;
import eai.devass.sinistreat.appli.authentification.Profil;
import eai.devass.sinistreat.appli.authentification.Utilisateur;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.utils.BundleTools;
import eai.devass.sinistreat.appli.utils.RmiTools;
import eai.devass.sinistreat.appli.utils.entites.IProfil;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EntiteOrgVO;


/* @author kchakib : 09 nov. 10 */
@SuppressWarnings("unchecked")
public class SasServicesTools implements IMessageException  {
	
	private static SasServicesTools instance;
	private static Map<String, Utilisateur> mapUsers = new HashMap();
	
	private Logger logger= Logger.getLogger("loggerSINAT");
	
		
	/** Service ASAL et SAS */
	private static ICallServices callServicesASal;
	private static ICallServices callServicesSas;
	
	/** Constantes */
	private final static String UC_SASTR02 = "UC-SASTR02";
	private final static String UC_ASALR01 = "UC-ASALR01";
	private final static String HOST_ASAL = "missionnement.asal.host";
	private final static String HOST_SAS = "missionnement.sas.host";
		
	
	public synchronized static SasServicesTools getInstance() {
		if (instance == null) {
			instance = new SasServicesTools();
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
		
		Utilisateur utilisateur =getUtilisateur(codeUtilisateur);
		if (utilisateur!=null) {
			return utilisateur.getRefEntite().getCode();
		} else {
			throw new FonctionnelleException(EXP_SAS_SERVICE_INDISPONIBLE, 
					BundleTools.getInstance().getDefaultMessage(HOST_SAS));
		}
	
	}
	
	/**
	 * Récupérer l'utilisateur (sans la liste des ressources) via le service ASAL  
	 * @param codeUtilisateur
	 * @return
	 * @throws Exception
	 */
	public Utilisateur getUtilisateur(String codeUtilisateur) throws Exception {
		
		// Vérifier si l'utilisateur existe déja dans le mapUsers
		Utilisateur utilisateur = mapUsers.get(codeUtilisateur);
		if(utilisateur != null) {
			return utilisateur;
		}
		
		CheckUserExVO checkUserExVO = new CheckUserExVO();
		checkUserExVO.setIdsIdentifiant(codeUtilisateur);
		checkUserExVO = (CheckUserExVO) getCallServicesASal().invokeServices(checkUserExVO, null);
		utilisateur = convertCheckUserExVOToUtilisateur(checkUserExVO);
		
		// Ajouter l'utilisateur dans le mapUsers
		mapUsers.put(codeUtilisateur, utilisateur);
		
		return utilisateur;
	
	}
	
	/**
	 * Récupérer l'utilisateur (avec la liste des ressources) via le service ASAL et SAS  
	 * @param codeUtilisateur
	 * @return
	 * @throws Exception
	 */
	public Utilisateur getUtilisateurWithResources(String codeUtilisateur) throws Exception {
		
			
		
		// Vérifier si l'utilisateur existe déja dans le mapUsers
		Utilisateur utilisateur = mapUsers.get(codeUtilisateur);
		try {
			if(utilisateur != null) {
				// Vérifier si l'utilisateur à deja ces ressources
				List<Fonctionnalite> listFonctionnalite = utilisateur.getRefFonctionnalite();
				if(listFonctionnalite != null && !listFonctionnalite.isEmpty()) {
					return utilisateur;
				}
				
				// Chercher la liste des ressources de l'utilisateur via le service UC-SASTR01
				//utilisateur.setRefFonctionnalite(getFonctionnalites(codeUtilisateur));
				setFonctionnalitesAndProfil(utilisateur);
				return utilisateur;
			}
			
			utilisateur = getUtilisateur(codeUtilisateur);
			if(utilisateur == null) {
				throw new FonctionnelleException(USER_NOT_FOUND_IN_SAS);
			}
			
			// Chercher la liste des ressources de l'utilisateur via le service UC-SASTR01
			//utilisateur.setRefFonctionnalite(getFonctionnalites(codeUtilisateur));
			setFonctionnalitesAndProfil(utilisateur);
		} catch (FonctionnelleException e) {
			throw e;		
		} catch (Exception e) {
			logger.error("problème technique",e);
			throw new FonctionnelleException(EXP_ASAL_SERVICE_INDISPONIBLE);
		}
		return utilisateur;
		
	}
	
	/**
	 * Récupérer la liste des fonctionnalités via le service SAS : UC-SASTR02 
	 * @param codeUtilisateur
	 * @return
	 * @throws Exception
	 */
	private void setFonctionnalitesAndProfil(Utilisateur utilisateur) throws Exception {
		try{
		// Récupérer la liste des fonctionnalites de l'utilisateur (resources sas)
		if(utilisateur.getCodeUser() == null) {
			return ;
		}
		
		IAutorisationManager manager = (IAutorisationManager) ServicesFactory
				.getService(IAutorisationManager.class);
		List<Fonctionnalite> listFonctionnalite = manager.getActions(new Fonctionnalite());
		
		// Construire l'objet SetRessourcesVO et appel de service SAS
		SetRessourcesVO setRessourcesVO = convertFonctionalitesToSetRessourcesVO(listFonctionnalite);
		setRessourcesVO.setIdsIdentifiant(utilisateur.getLogin());
		setRessourcesVO = (SetRessourcesVO) getCallServicesSas().invokeServices(setRessourcesVO, UC_SASTR02);
		
		// Vérifier la liste des ressources retour : Fonctionnaite
	
		List<Profil> listProfillUser = new ArrayList<Profil>();
		HashMap mapResources = setRessourcesVO.getMapRessourcesAutor();
		//Set entries = mapResources.entrySet();
		for(Object entrie : mapResources.entrySet()){
			Object object = ((Entry)entrie).getValue();
			String key = (String) ((Entry)entrie).getKey();
			if(object == null) {
				continue;
			}
			
			if(((List) object).isEmpty()) {
				continue;
			}
			
			int ok = (Integer) ((List) object).get(0);
			if(ok == 1) {
				// C'est fonctionnalite
				if(key.indexOf("SF") < 0) {
					listProfillUser.add(getProfil(key));
//					for(Fonctionnalite fonctionnalite : listFonctionnalite) {
//						if(fonctionnalite.getCode().equals(getCodeFonctionnalite(key))) {
//							listFctUser.add(fonctionnalite);
//							break;
//						}
//					}
//				} else { // V'est Profil
//					
				}
			}
		}
		
		utilisateur.setRefProfils(listProfillUser);
		
		List<Fonctionnalite>  listFctUser=getUserFonctionnalites(utilisateur);		
		utilisateur.setRefFonctionnalite(listFctUser);
		//	Vérifier la liste des ressources retour : 
		
		} catch (Exception e) {
			throw new FonctionnelleException(EXP_SAS_SERVICE_INDISPONIBLE);
		}
	}
	
	public List getUserFonctionnalites(Utilisateur utilisateur) {
		Fonctionnalite parametrage = new Fonctionnalite();
		parametrage.setCode(1);
		Fonctionnalite missionnement = new Fonctionnalite();
		missionnement.setCode(2);
		Fonctionnalite missionRecherchePrestataire = new Fonctionnalite();
		missionRecherchePrestataire.setCode(3);
		Fonctionnalite createMission = new Fonctionnalite();
		createMission.setCode(4);
		Fonctionnalite rechercherConsulterMission = new Fonctionnalite();
		rechercherConsulterMission.setCode(5);
		Fonctionnalite missionATraiter = new Fonctionnalite();
		missionATraiter.setCode(6);			
		Fonctionnalite missionSoumise = new Fonctionnalite();
		missionSoumise.setCode(7);
		Fonctionnalite missionCloture = new Fonctionnalite();
		missionCloture.setCode(8);
		Fonctionnalite rechercherQuittance = new Fonctionnalite();
		rechercherQuittance.setCode(9);
		List actions = new ArrayList();
		if (!utilisateur.getRefProfils().isEmpty()) {
			Profil profil =utilisateur.getRefProfils().get(0);
			if (profil.getCode().equals(IProfil.RESPONSABLE_PARAMETRAGE)) {				
				actions.add(parametrage);				
			}
			else if (profil.getCode().equals(IProfil.PRESTATAIRE)) {				
				actions.add(missionnement);
				actions.add(rechercherConsulterMission);
				actions.add(missionATraiter);
				actions.add(missionSoumise);
				actions.add(missionCloture);				
			}
			else if (profil.getCode().equals(IProfil.GESTIONNAIRE) || profil.getCode().equals(IProfil.INTERMEDIAIRE)) {				
				actions.add(missionnement);
				actions.add(missionRecherchePrestataire);
				actions.add(createMission);
				actions.add(rechercherConsulterMission);
				actions.add(rechercherQuittance);	
				actions.add(missionATraiter);
				actions.add(missionSoumise);
				actions.add(missionCloture);				
			}
			else {				
				actions.add(missionnement);
				actions.add(missionRecherchePrestataire);				
				actions.add(rechercherConsulterMission);
				actions.add(rechercherQuittance);				
				actions.add(missionATraiter);
				actions.add(missionSoumise);
				actions.add(missionCloture);			
			}
		}
		
		return actions;
		
	}


	private Utilisateur convertCheckUserExVOToUtilisateur(CheckUserExVO checkUserExVO) {
		
		if(checkUserExVO == null) {
			return null;
		}
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNomUser(checkUserExVO.getIdentifiant());
		utilisateur.setLogin(checkUserExVO.getIdsIdentifiant());
		if (checkUserExVO.getIdsIdentifiant().contains("PRT")) {
			utilisateur.setCodeUser(checkUserExVO.getIdsIdentifiant().substring(3));
		}else{
			utilisateur.setCodeUser(checkUserExVO.getIdsIdentifiant());
		}	
		utilisateur.setMail(checkUserExVO.getEmail());
		utilisateur.setGsm(checkUserExVO.getGsm());
		
		// Utilisateur Externe ou Interne
		if(checkUserExVO.getQualite() != null) {
			utilisateur.setInterne(("E".equals(checkUserExVO.getQualite())) ? Boolean.FALSE
							: Boolean.TRUE);
		}
		
		// Entite Utilisateur
		utilisateur.setRefEntite(new EntiteOrgVO(checkUserExVO.getCodeEntite(),
				checkUserExVO.getLibEntite()));
		
		return utilisateur;
	}
	
	private SetRessourcesVO convertFonctionalitesToSetRessourcesVO(
			List<Fonctionnalite> listFonctionnalite) throws Exception {
		
		DecimalFormat decimalFormat = new DecimalFormat("000");
		SetRessourcesVO setRessourcesVO = new SetRessourcesVO();
		HashMap<String, List> params = new HashMap<String, List>();
		for(Fonctionnalite fonctionnalite : listFonctionnalite) {
			// SF310MIS
			params.put("SF" + fonctionnalite.getCode() + "MIS", null);
		}
		
		// Ajouter la liste des profils à la liste des ressources à vérifier
		Field[] fields = IProfil.class.getDeclaredFields();
		for(Field field : fields) {
			// SP001MIS
			params.put("SP"	+ decimalFormat.format(Integer.valueOf((String) field
							.get(""))) + "MIS", null);
		}
		
		setRessourcesVO.setMapRessourcesAutor(params);
		
		return setRessourcesVO;
	}

//	private Integer getCodeFonctionnalite(String nameRessource) {
//		
//		if(nameRessource == null)
//			return null;
//		
//		nameRessource = nameRessource.replaceAll("SP", "").replaceAll("SF", "")
//				.replaceAll("MIS", "");
//		
//		return Integer.valueOf(nameRessource);
//	}
	
	private Profil getProfil(String keyRessource) throws Exception {
		
		Profil profil = new Profil();
		
		// Récupérer le code, String(00x) ===> Integer(x)
		Integer codeProfil = Integer.valueOf(keyRessource.replaceAll("SP", "")
							.replaceAll("MIS", ""));
		profil.setCode(String.valueOf(codeProfil));
		
		// Récuperer le nom du profil
		Field[] fields = IProfil.class.getDeclaredFields();
		for (Field field : fields) {
			if(field.get("").equals(profil.getCode())) {
				profil.setLibelle(field.getName());
				break;
			}
		}
		
		return profil;
	}

	private synchronized static ICallServices getCallServicesSas() throws FonctionnelleException {
		
		try {
			if(callServicesSas == null) {
				// Initialiser le service RMI : ICallServices SAS
				callServicesSas = (ICallServices) RmiTools.getInstance()
						.callService(ICallServices.class, "call-service",
								BundleTools.getInstance().getDefaultMessage(HOST_SAS));
			}
		} catch(Exception e) {
			throw new FonctionnelleException(EXP_SAS_SERVICE_INDISPONIBLE, 
					BundleTools.getInstance().getDefaultMessage(HOST_SAS));
		}
		
		return callServicesSas;
	}

	private synchronized static ICallServices getCallServicesASal() throws FonctionnelleException {
		
		// nitialiser le service RMI : ICallServices : ASAL
		try {	
			if(callServicesASal == null) {
				callServicesASal = (ICallServices) RmiTools.getInstance()
						.callService(ICallServices.class, "call-service",
								BundleTools.getInstance().getDefaultMessage(HOST_ASAL));
			}
			
		} catch(Exception e) {
			throw new FonctionnelleException(EXP_ASAL_SERVICE_INDISPONIBLE, 
					BundleTools.getInstance().getDefaultMessage(HOST_ASAL));
		}
		
		return callServicesASal;
	}
	
	
	/**
	 * Récupérer le code entité de l'utilisateur via l'appel de service ORG  
	 * @param codeUtilisateur
	 * @return
	 * @throws Exception
	 */
	public List<UserItem> getUsersByEntite(String codeEntite) throws Exception {
		
		UsersListVO usersListVO = new UsersListVO();
	
		usersListVO.setCodeEntite(codeEntite);						
		usersListVO = (UsersListVO) getCallServicesASal().invokeServices(usersListVO, UC_ASALR01);
		 List<UserItem> list= new ArrayList<UserItem>();
		if (usersListVO!=null) {
			list= usersListVO.getLstUsers();
		}	
		return list;
	
	}
	
	
	

}


