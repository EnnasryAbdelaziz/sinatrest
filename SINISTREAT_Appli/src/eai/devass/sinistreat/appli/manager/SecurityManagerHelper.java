package eai.devass.sinistreat.appli.manager;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.securite.AuthentificationException;
import ma.co.omnidata.framework.services.securite.AutorisationException;
import ma.co.omnidata.framework.services.securite.IActionSecurise;
import ma.co.omnidata.framework.services.securite.IPaginatedList;
import ma.co.omnidata.framework.services.securite.IRole;
import ma.co.omnidata.framework.services.securite.IUtilisateur;
import ma.co.omnidata.framework.services.securite.impl.ISecurityManagerHelper;
import eai.devass.sinistreat.appli.authentification.Fonctionnalite;

public class SecurityManagerHelper implements ISecurityManagerHelper {

	private Logger logger= Logger.getLogger("loggerSINAT");	
	public void addEntities(String id, String[] selectedId) throws AuthentificationException, SQLException {
		// TODO Auto-generated method stub
		
	}

	public Object changeUserPassword(IUtilisateur user, String newPassword, boolean automatic) throws AuthentificationException {
		// TODO Auto-generated method stub
		return null;
	}

	public IUtilisateur connectUser(IUtilisateur user, String sessionId, Map params) throws AuthentificationException {
		// TODO Auto-generated method stub
		return null;
	}

	public IActionSecurise createAction(IActionSecurise action) {
		// TODO Auto-generated method stub
		return null;
	}

	public IRole createRole(IRole role) {
		// TODO Auto-generated method stub
		return null;
	}

	public IUtilisateur createUser(IUtilisateur user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAction(IActionSecurise action) {
		// TODO Auto-generated method stub
		
	}

	public void deleteRole(IRole role) {
		// TODO Auto-generated method stub
		
	}

	public void deleteUser(IUtilisateur user) {
		// TODO Auto-generated method stub
		
	}

	public void disconnectUser(IUtilisateur user) throws AuthentificationException {
		// TODO Auto-generated method stub
		
	}

	public String encryptPassword(String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getActions(IActionSecurise action) {
		
		IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
		
		Fonctionnalite fonctionnalite = (Fonctionnalite) action;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM HAB_FONCTIONNALITE WHERE 1=1 ");
		if(action == null) {
			return null;
		}
		
		if (fonctionnalite.getId() != 0) {
			sb.append(" AND ID=").append(fonctionnalite.getId());
		}
		if (fonctionnalite.getType() != null && fonctionnalite.getType().length() > 0) {
			sb.append(" AND TYPE='").append(fonctionnalite.getType()).append("'");
		}
		if (fonctionnalite.getActionClassName() != null && fonctionnalite.getActionClassName().length() > 0) {
			sb.append(" AND ACTIONCLASSNAME='").append(fonctionnalite.getActionClassName()).append("'");
		}
		
		try {
			return (List) dao.executeSqlQuery(sb.toString());
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return null;
		
	}

	public List getConnexionsByUser(IUtilisateur user) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getRefUtilisateurs(double idProfil) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getRoles(IRole role) {
		// TODO Auto-generated method stub
		return null;
	}

	public IUtilisateur getUser(IUtilisateur user) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getUserById(long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getUserList(IUtilisateur user) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List getUsers(IUtilisateur user) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isActionAllowedToUser(IActionSecurise action, IUtilisateur user) throws AutorisationException {
		// TODO Auto-generated method stub
		return false;
	}

	public IActionSecurise readAction(IActionSecurise action) {
		
		return (IActionSecurise)ServicesFactory.getService(action.getActionId());
		
//		IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
//		Fonctionnalite fonctionnalite = new Fonctionnalite();
//		if(action.getActionId() == null || action.getActionId().length() < 0)
//			return null;
//		
//		if (action.getActionId() != null) 
//			fonctionnalite.setCode(Integer.valueOf(action.getActionId()));
//		
//		StringBuffer sb = new StringBuffer();
//		sb.append("SELECT * FROM HAB_FONCTIONNALITE WHERE CODE=").append(fonctionnalite.getCode());
//		List list = null;
//		try {
//			list = (List) dao.executeSqlQuery(sb.toString(), Fonctionnalite.class);
//			if(list != null && !list.isEmpty())
//				return (Fonctionnalite) list.get(0);
//				
//		} catch (PersistenceException e) {
//			logger.error("problème technique",e);
//		}
//		
//		return null;
		
	}

	public IRole readRole(IRole role) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeEntities(String id, String[] selectedId) throws AuthentificationException, SQLException {
		// TODO Auto-generated method stub
		
	}

	public IActionSecurise updateAction(IActionSecurise action) {
		// TODO Auto-generated method stub
		return null;
	}

	public IRole updateRole(IRole role) {
		// TODO Auto-generated method stub
		return null;
	}

	public IUtilisateur updateUser(IUtilisateur user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateUserProfil(String id, String idProfil) throws AuthentificationException, SQLException {
		// TODO Auto-generated method stub
		
	}

	public void envoiMailCreationUser(IUtilisateur arg0) {
		// TODO Auto-generated method stub
		
	}

	public String generatePass(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getActionsChildByTimbre(IActionSecurise arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public IPaginatedList getPaginatedActions(IActionSecurise arg0, int arg1,
			int arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public IPaginatedList getPaginatedRoles(IRole arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public IPaginatedList getPaginatedUsers(IUtilisateur arg0, int arg1,
			int arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public IUtilisateur getUserByMdp(IUtilisateur arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
