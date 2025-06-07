package eai.devass.sinistreat.appli.utils.auth;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.utils.BundleTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;

/* @author kchakib : 09 nov. 10 */
@SuppressWarnings("unchecked")
public class LDAPServicesTools implements IMessageException {

	private static LDAPServicesTools instance;

	/** Constantes */
	private static Logger logger = Logger.getLogger("loggerSINAT");
	private final static String LDAP_PROVIDER = "com.sun.jndi.ldap.LdapCtxFactory";
	private final static String AUTHENTIFICATION_TYPE = "simple";
	private final static String PATH_LDAP = "PATH_LDAP";
	private final static String PATH_ADMIN_USER = "PATH_ADMIN_USER";
	private final static String PASSWORD_ADMIN = "PASSWORD_ADMIN";
	private final static String URL_LDAP = "URL_LDAP";
	
	public synchronized static LDAPServicesTools getInstance() {
		if (instance == null) {
			instance = new LDAPServicesTools();
		}

		return instance;
	}

	public boolean checkLDAPUser(String loginUser, String passWord)
			throws FonctionnelleException {
		DirContext context = null;

		try {
			String contextPath = getLDAPUserContextPath(loginUser);
			context = ldapContext(LDAP_PROVIDER, contextPath, passWord,
					AUTHENTIFICATION_TYPE);
			if (contextPath == null || contextPath.equals("")) {
				throw new FonctionnelleException(
						"AUTHENIFICATION.FAILURE.LOGIN.PWD");
			}
			if (context == null) {
				throw new FonctionnelleException(
						"AUTHENIFICATION.FAILURE.LOGIN.PWD");
			}
		} catch (FonctionnelleException e) {
			throw e;
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		return true;
	}

	private String getLDAPUserContextPath(String loginUser) {
		String account = BundleTools.getInstance().getDefaultMessage(
				PATH_ADMIN_USER);
		String path = "";

		DirContext ctx = null;
		SearchControls constraints = new SearchControls();
		SearchResult entry = null;
		Attributes attrs = null;
		NamingEnumeration e = null;
		try {

			ctx = ldapContext(LDAP_PROVIDER, account, BundleTools.getInstance()
					.getDefaultMessage(PASSWORD_ADMIN), AUTHENTIFICATION_TYPE);
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			constraints.setCountLimit(0);
			String formule = "(&(objectClass=person)(objectClass=user)(sAMAccountName="
					+ loginUser + ")))";
			// retrive information person
			e = ctx.search(
					BundleTools.getInstance().getDefaultMessage(PATH_LDAP),
					formule, constraints);

			while (e != null && e.hasMoreElements()) {
				entry = (SearchResult) e.next();

				attrs = entry.getAttributes();
				if (attrs != null) {

					// for (NamingEnumeration attEnum = attrs.getAll();
					// attEnum.hasMoreElements();) {
					// attr = (Attribute) attEnum.next();
					// if(attr.getID().equals("sn")){
					// dnUser = (String) attr.get(attr.size()-1);
					// user.setSn(dnUser);
					// }
					// if(attr.getID().equals("givenName")){
					// dnUser = (String) attr.get(attr.size()-1);
					// user.setGivenName(dnUser);
					// }
					// if(attr.getID().equals("sAMAccountName")){
					// dnUser = (String) attr.get(attr.size()-1);
					// user.setsAMAccountName(dnUser);
					// }
					// if(attr.getID().equals("mail")){
					// dnUser = (String) attr.get(attr.size()-1);
					// user.setMail(dnUser);
					// }
					// if(attr.getID().equals("physicalDeliveryOfficeName")){
					// dnUser = (String) attr.get(attr.size()-1);
					// user.setPhysicalDeliveryOfficeName(dnUser);
					// }
					//
					//
					// }
					path = entry.getNameInNamespace();
				}
			}
			if (e != null) {
				e.close();
			}
			ctx.close();

		} catch (NamingException ex) {
			logger.error("Error: Naming: LDAP User Context Path", ex);
		} catch (Exception ex) {
			logger.error("Error: LDAP User Context Path", ex);
		}
		return path;
	}

	public DirContext ldapContext(String context, String login, String passwd,
			String type) {

		// String
		// security_principal="CN="+login+",OU=DEVASS,OU=Personnel,DC=eurafric-information,DC=com";

		DirContext ctx = null;
		try {
			Hashtable Hashenv = new Hashtable();
			Hashenv.put(Context.INITIAL_CONTEXT_FACTORY, context);
			Hashenv.put(Context.PROVIDER_URL, BundleTools.getInstance()
					.getDefaultMessage(URL_LDAP));
			Hashenv.put(Context.SECURITY_AUTHENTICATION, type);
			Hashenv.put(Context.SECURITY_PRINCIPAL, login);
			Hashenv.put(Context.SECURITY_CREDENTIALS, passwd);
			//System.out.print("trying to connect to ldap \n");
			logger.error("trying to connect to ldap \n");
			ctx = new InitialDirContext(Hashenv);
		} catch (NamingException ex) {
			logger.error("Error: Naming: ldap Context", ex);
		} catch (Exception ex) {
			logger.error("Error ldap Context", ex);
		}
		return ctx;
	}

}
