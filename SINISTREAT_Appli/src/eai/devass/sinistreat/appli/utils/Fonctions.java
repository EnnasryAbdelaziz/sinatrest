package eai.devass.sinistreat.appli.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.utile.DateUtile;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;

/**
 * 
 * @author
 * 
 */
public class Fonctions {
	
	public static final String errorPostMail = "Error post mail";
	public static final Logger logger = Logger.getLogger("loggerSINAT");
    public static final String RETOUR_LIGNE = System
            .getProperty("line.separator");

	public static Object StringToLong(String data, String string) {
		try {
			return Long.valueOf(string);
		} catch (NumberFormatException e) {
			return null;
		}

	}

	public static boolean isEmpty(String data) {
		return StringUtils.isEmpty(data);
	}

	public static boolean isNotEmpty(String data) {
		return !isEmpty(data);
	}

	public static boolean isEmpty(List list) {
		return list == null || list.size() == 0;
	}

	public static boolean isNotEmpty(List list) {
		return !isEmpty(list);
	}

	public static boolean isLong(String data) {
		if (isEmpty(data)) {
			return false;
		}
		try {
			Long.parseLong(data);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean isDouble(String data) {
		if (isEmpty(data)) {
			return true;
		}
		try {
			Double.parseDouble(data);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean isInt(String data) {
		if (isEmpty(data)) {
			return true;
		}
		try {
			Integer.parseInt(data);
			return true;
		} catch (Exception e) {
            logger.log(Level.ERROR, "Exception", e);
			return false;
		}
	}

	public static boolean isPositive(String data) {
		if (isEmpty(data)) {
			return true;
		}
		try {
			int intData = Integer.parseInt(data);
			if (intData >= 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
            logger.log(Level.ERROR, "Exception", e);
			return false;
		}
	}

	public static int StringToInt(String data) {
		if (isEmpty(data)) {
			return 0;
		}
		try {
			return Integer.parseInt(data);
		} catch (Exception e) {
            logger.log(Level.ERROR, "Exception", e);
			return 0;
		}
	}

	public static Object StringToInteger(String data) {
		if (isEmpty(data)) {
			return null;
		} else {
			return Integer.valueOf(data);
		}
	}

	public static long StringToLong(String data) {
		if (isEmpty(data)) {
			return 0;
		} else {
			return Long.valueOf(data);
		}
	}

	public static Object StringToDouble(String data) {
		if (isEmpty(data)) {
			return null;
		} else {
			return Double.valueOf(data);
		}
	}

	public static boolean isBetween(String s, int j, int k) {
		if (StringUtils.isNumeric(s)) {
			int i = Integer.parseInt(s);
			return i <= k && i >= j;
		}
		return false;
	}

	public static String completeWithBlanks(String s, int j) {
		int k = j - s.length();
		if (k > 0) {
			StringBuffer sb = new StringBuffer(s);
			for (int i = 0; i < k; i++) {
				sb.append(" ");
			}
			return sb.toString();
		}
		return s;
	}

	public static boolean isExist(String str, String[] tabStr) {
		if (tabStr != null) {
			for (int i = 0; i < tabStr.length; i++) {
				if (tabStr[i].equalsIgnoreCase(str)) {
					return true;
				}

			}
		}

		return false;
	}

	public static boolean dateTestBeforeInsert(int annee2, int annee1) {
		boolean dateIsOk = false;
		if (annee2 <= annee1) {
			dateIsOk = true;
		}
		return dateIsOk;
	}

	public static Integer diff(Integer one, Integer two) {
		return Integer.valueOf(one.intValue() - two.intValue() + 1);
	}

	public static Integer sum(Integer one, Integer two) {
		return Integer.valueOf(one.intValue() + two.intValue() - 1);
	}

	public static boolean equalsToCurrentDate(Calendar date) {
		if (DateUtile.calendarToString(DateUtile.DEFAULT_FORMAT,
				Calendar.getInstance()).equals(
				DateUtile.calendarToString(DateUtile.DEFAULT_FORMAT, date))) {
			return true;
		}
		return false;
	}

	public static boolean isASameDate(Calendar date1, Calendar date2) {
		if (DateUtile.calendarToString(DateUtile.DEFAULT_FORMAT, date1).equals(
				DateUtile.calendarToString(DateUtile.DEFAULT_FORMAT, date2))) {
			return true;
		}
		return false;
	}

	public static double getDifferenceOfYears(Calendar date1, Calendar date2) {
		if (date1 == null) {
			return 0;
		}
		Calendar cal1 = (Calendar) date1.clone();
		Calendar cal2 = (Calendar) date2.clone();
		if (cal1.after(cal2)) {
			return 0;
		}
		cal1.add(Calendar.YEAR, 1);
		double d = 0;
		while (cal1.before(cal2) || isASameDate(cal1, cal2)) {
			d++;
			cal1.add(Calendar.YEAR, 1);
		}

		Calendar cal = (Calendar) cal1.clone();
		cal.add(Calendar.YEAR, -1);

		double di = DateUtile.differenceCalendar(cal, cal2);
		double dt = DateUtile.differenceCalendar(cal, cal1);

		return d + (di / dt);
	}

	public static boolean contains(Collection c, String id) throws Exception {
		boolean b = false;
		if (c != null) {
			for (Iterator iter = c.iterator(); iter.hasNext();) {
				String value = BeanUtils.getProperty(iter.next(), "id");
				if ((value != null) && (value.equals(id))) {
					b = true;
				}
			}
		}
		return b;
	}

	public static String getDate(Calendar date1) {
		String date;
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				DateUtile.DEFAULT_FORMAT);
		date = dateFormat.format(date1.getTime());
		return date;
	}

	public static String getSqlDate(Calendar date1) {
		String date;
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtile.SQL_FORMAT);
		date = "CAST(" + "'" + dateFormat.format(date1.getTime()) + "'"
				+ " AS DATETIME)";
		return date;
	}

	public static String replace(String source, String pattern, String replace) {
		if (source != null) {
			final int len = pattern.length();
			StringBuffer sb = new StringBuffer();
			int found = -1;
			int start = 0;

			while ((found = source.indexOf(pattern, start)) != -1) {
				sb.append(source.substring(start, found));
				sb.append(replace);
				start = found + len;
			}

			sb.append(source.substring(start));

			return sb.toString();
		} else {
			return "";
		}
	}

	public static String leftPad(String chaine, int length) {

		for (int i = chaine.length(); i < length; i++) {
			chaine = "0" + chaine;
		}
		return chaine;
	}

	public static void reset(ActionForm object) {

		Field[] fields = object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.getType().equals(String.class)) {
				try {
					BeanUtilsBean.getInstance().setProperty(object,
							field.getName(), "");
				} catch (Exception e) {
                    logger.log(Level.ERROR, "Exception", e);
				}
			}

			if (field.getType().equals(Collection.class)) {
				try {
					BeanUtilsBean.getInstance().setProperty(object,
							field.getName(), new ArrayList());
				} catch (Exception e) {
                    logger.log(Level.ERROR, "Exception", e);
				}
			} else {
				try {
					BeanUtilsBean.getInstance().setProperty(object,
							field.getName(), null);
				} catch (Exception e) {
                    logger.log(Level.ERROR, "Exception", e);
				}
			}
		}
	}

	public static double stringToDouble(String chaine) {
		try {
			double nombre;
            nombre = Double.parseDouble(replace(replace(chaine, " ", ""), " ",
                    ""));
			
			return nombre;
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}
	
	public static String formatNumber(double nombre) {
		try {			
			String formatNumber="0.0";
            java.text.NumberFormat nf = java.text.NumberFormat
                    .getInstance(java.util.Locale.FRENCH);
			formatNumber=nf.format(nombre);
			return formatNumber;
		} catch (NumberFormatException nfe) {
			return "0.0";
		}
	}

	public static String formatNumber(long nombre) {
		try {			
			String formatNumber="0.0";
            java.text.NumberFormat nf = java.text.NumberFormat
                    .getInstance(java.util.Locale.FRENCH);
			formatNumber=nf.format(nombre);
			return formatNumber;
		} catch (NumberFormatException nfe) {
			return "0.0";
		}
	}
	
	public static String formatNumber2(double nombre) {
		return replace(formatNumber(nombre),",",".");
	}
	
	public static String calendarToStringCompleteFormat(Calendar fDate) {
        return DateUtile.calendarToString(DateUtile.FORMAT_COMPLET2, fDate);
	}
	
	public static String calendarToStringFormatHeure(Calendar fDate) {
		String FORMAT_HEURE = "HH:mm";
		return DateUtile.calendarToString(FORMAT_HEURE, fDate);
	}
	
    public static void postmail(String from, String to, String host,
            String filename, String msgText1, String subject) {

		// create some properties and get the default Session
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);

		Session session = Session.getInstance(props, null);

		try {
			// create a message
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));

			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);

			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(msgText1);

			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message
			FileDataSource fds = new FileDataSource(filename);
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);

			// add the Multipart to the message
			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());

			// send the message
			Transport.send(msg);

		} catch (MessagingException mex) {
			logger.error(errorPostMail, mex);
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				logger.error(errorPostMail, ex);
			}
		}

	}
	
    public static void postmail(String from, String recipients[], String host,
            String[] filename, String msgText1, String subject) {

		// create some properties and get the default Session
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);

		Session session = Session.getInstance(props, null);

		try {
			// create a message
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] addressTo = new InternetAddress[recipients.length];

			for (int i = 0; i < recipients.length; i++)

			{
				addressTo[i] = new InternetAddress(recipients[i]);

			}
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setSubject(subject);

			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(msgText1);

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			for (int i = 0; i < filename.length; i++) {
				// create the second message part
				MimeBodyPart mbp2 = new MimeBodyPart();
				
				// attach the file to the message
				FileDataSource fds = new FileDataSource(filename[i]);
				mbp2.setDataHandler(new DataHandler(fds));
				mbp2.setFileName(fds.getName());
				mp.addBodyPart(mbp2);
			}
			
			// add the Multipart to the message
			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());

			// send the message
			Transport.send(msg);

		} catch (MessagingException mex) {
			logger.error(errorPostMail, mex);
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				logger.error(errorPostMail, ex);
			}
		}

	}
	
    public static void postmailNoFile(String from, String recipients[],
            String host, String msgText1, String subject) {

		// create some properties and get the default Session
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);

		Session session = Session.getInstance(props, null);

		try {
			// create a message
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] addressTo = new InternetAddress[recipients.length];

			for (int i = 0; i < recipients.length; i++)

			{
				addressTo[i] = new InternetAddress(recipients[i]);

			}
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setSubject(subject);

			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(msgText1);

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			
			// add the Multipart to the message
			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());

			// send the message
			Transport.send(msg);

		} catch (MessagingException mex) {
			logger.error("Error post mail No File", mex);
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				logger.error("Error post mail No File", ex);
			}
		}

	}

	/** kchakib E:12/02/2010 */
	private static String getMailContent(IValueObject entite) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("Bonjour").append(RETOUR_LIGNE);
        buffer.append("Ce message est une notification automatique du serveur")
                .append(RETOUR_LIGNE);
		
		/** recuperer les valeur dans message pour chaque champs**/
		buffer.append(" ]").append(RETOUR_LIGNE);
		buffer.append("Cordialement").append(RETOUR_LIGNE);
		buffer.append("Administrateur").append(RETOUR_LIGNE);

		return buffer.toString();
	}
		
	public static void main(String args[]) {
	}

	public String usePatternSpace(String chaine){

		Pattern pattern = Pattern.compile("\\s{1,}");
		Matcher matcher=pattern.matcher(chaine);
		
		StringBuffer sb= new StringBuffer("");
		while(matcher.find()){
			matcher.appendReplacement(sb, " ");
		}
		matcher.appendTail(sb);
		logger.info("La chaine entrée est :"+ chaine);
		logger.info("La chaine sortie est :"+sb.toString());
		
		return sb.toString();
	}
	
	public String usePatternEspace(String chaine){
		
		Pattern pattern=Pattern.compile(" {2,}");
		Matcher matcher = pattern.matcher(chaine);
		
		if(isPatternTrouve("2 Espace ou plus", matcher)){
			chaine = matcher.replaceAll(" ");
		}
		
		if(isPatternTrouve("2 Espace ou plus", matcher)) {
			logger.info("Erreur");
		}
		return chaine;
	}
	
	public String usePatternTabulation(String chaine){
		
		Pattern pattern=Pattern.compile("	{1,}");
		Matcher matcher = pattern.matcher(chaine);

		String chaineResult=chaine;
		if(isPatternTrouve("Tabulation", matcher)){
			chaineResult = matcher.replaceAll(" ");
			
		}
		
		if(isPatternTrouve("Tabulation", matcher)) {
			logger.info("Erreur");
		}
		
		return chaineResult;
	}
	
	public boolean isPatternTrouve(String msg,Matcher matcher){
		
		boolean b;
		if(b=matcher.find()){
			logger.info(msg+" Trouvé");
			matcher.reset();
		}else{
			logger.info(msg+" Non Trouvé");
		}
		
		while(matcher.find()){
			
            logger.info("le Group  : " + matcher.group() + " commence à "
                    + matcher.start() + ":" + matcher.end());
		}
		
		return b;
	}	
	
	public static Sinistre BuiltnumSinistre(Sinistre sin ){
		
		try{
			
			String s=sin.getNumeroSinistre();
			String z=sin.getNumeroPolice();
			if(StringUtils.isEmpty(s) || StringUtils.isEmpty(s)) {
				return sin;
			}
			s=s.substring(0,8).concat(z).concat(s.substring(11));
			sin.setNumeroSinistre(s);
		}catch (Exception e) {
			return sin;
		}
		return sin;
	}
	
	
	public static boolean isBlank(String s){
		
		return StringUtils.isEmpty(s) || StringUtils.isBlank(s);
		
		
	}
	
	
}