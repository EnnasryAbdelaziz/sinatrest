package eai.devass.sinistreat.appli.utils.mail;

/* @author kchakib : 19 oct. 10 */
import java.util.List;
import java.util.Map;
import ma.co.omnidata.framework.services.mail.MailException;
import ma.co.omnidata.framework.services.mail.MessageObjet;
import ma.co.omnidata.framework.services.mail.impl.smtp.SmtpMailService;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.ui.velocity.VelocityEngineUtils;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;

@SuppressWarnings("unchecked")
public class MailTools {
	
	private SmtpMailService smtpMail = new SmtpMailService();
	private static VelocityEngine ve;
	private static MailTools instance;
	//private final static String PATH_VELOCITY = "eai/devass/missionnement/utils/mail/template/";
	private final static String PATH_VELOCITY = "templateVM/";
	
	
	public synchronized static MailTools getInstance(){
		if (instance == null) { 
			instance = new MailTools();
			initVelocityEngine();
		}
		
		return instance; 
	}
	
	
	/**
	 * Envoie de mail simple
	 * @param msg
	 * @throws FonctionnelleException
	 */public void sendMail(MessageObjet msg) throws FonctionnelleException {
		try {
		    
			List<String> dest = msg.getDestinataires();
			for(String mail : dest) {
				if(StringUtils.isEmpty(mail)) {
					return;
				}
				
			}
			
			smtpMail.envoyer(msg);
		
		} catch (MailException e) {
		      throw new FonctionnelleException(e.getMessage());
		}
	}
	
	/**
	 * Envoie de mail en utilisant une template velocity
	 * @param msg
	 * @param model
	 * @param fileNameVelocity
	 * @throws FonctionnelleException
	 */public void sendMail(MessageObjet msg, Map model, String fileNameVelocity)
			throws FonctionnelleException,VelocityException {
		try {
            
			List<String> dest = msg.getDestinataires();
			for(String mail : dest) {
				if(StringUtils.isEmpty(mail)) {
					return;
				}
			}
			
			// Récupérer le body du mail
			String body = VelocityEngineUtils.mergeTemplateIntoString(ve,
					PATH_VELOCITY + fileNameVelocity, model);
			msg.setBody(body);
			
			smtpMail.envoyer(msg);

        } catch (MailException e) {
              throw new FonctionnelleException(e.getMessage());
        }
	}
	 public String getMailText(Map model, String fileNameVelocity)
		throws Exception {
		try {
	     			
			
			// Récupérer le body du mail
			String body = VelocityEngineUtils.mergeTemplateIntoString(ve,
					PATH_VELOCITY + fileNameVelocity, model);
			return body;
	
		} catch (Exception e) {
		
	       throw e;
		}
	}
	
	private static void initVelocityEngine() {
		
		ve = new VelocityEngine();
		ve.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
        ve.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());       
        
        try {
			ve.init();
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	

}


