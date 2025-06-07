package eai.devass.sinistreat.appli.utils.mail;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.modele.parametrage.ParamMail;
import ma.co.omnidata.framework.services.dao.PersistenceException;

public class MailUtils {

	final String from = "NotificationsAT@rmaassurance.com";
    Properties props = System.getProperties();
    ParametrageManager parametrageManager =  new ParametrageManager();
    ParamMail mail = new ParamMail();
    
    public Session getSessionMail() throws PersistenceException{
    	mail = parametrageManager.getParamMail();
    	props.put("mail.smtp.host", mail.getHost());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        // correction probleme notif mail
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "Outlook.eurafric.com");
        props.put("mail.smtp.port", mail.getPort());
        final String username = mail.getUsername();
        final String password = mail.getPassword();
        Session session = Session
		.getDefaultInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								username, password);
					}
				});
        return session;
    }
    
    public void sendMail(List<String> tos, List<String> ccs, String subject, String text) throws MessagingException, PersistenceException{
    	MimeMessage message = new MimeMessage(getSessionMail());
    	message.setFrom(new InternetAddress(from));
    	for (String to : tos) {
    		message.addRecipient(Message.RecipientType.TO,  new InternetAddress(to));
		}
    	for (String cc : ccs) {
    		message.addRecipient(Message.RecipientType.CC,  new InternetAddress(cc));
		}
    	message.setSubject(subject);
    	message.setContent(text,"text/html; charset=utf-8");
    	Transport.send( message );
    }
    
    public void sendMailPJ(List<String> tos, List<String> ccs, String subject, String text, File file) throws MessagingException, PersistenceException, IOException{
    	MimeMessage message = new MimeMessage(getSessionMail());
    	message.setFrom(new InternetAddress(from));
    	for (String to : tos) {
    		message.addRecipient(Message.RecipientType.TO,  new InternetAddress(to));
		}
    	for (String cc : ccs) {
    		message.addRecipient(Message.RecipientType.CC,  new InternetAddress(cc));
		}
    	message.setSubject(subject);
    	MimeBodyPart mimeBodyPart = new MimeBodyPart();
    	mimeBodyPart.setContent(text, "text/html; charset=utf-8");

    	Multipart multipart = new MimeMultipart();
    	multipart.addBodyPart(mimeBodyPart);
    	if(file != null) {
    	MimeBodyPart attachmentBodyPart = new MimeBodyPart();
    	attachmentBodyPart.attachFile(file);
    	multipart.addBodyPart(attachmentBodyPart);
    	}
    	message.setContent(multipart);
    	Transport.send( message );
    }
}
