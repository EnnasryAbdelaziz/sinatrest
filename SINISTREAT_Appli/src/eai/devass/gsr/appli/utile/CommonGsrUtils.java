package eai.devass.gsr.appli.utile;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IMessageItem;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.utile.DateUtile;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.rmawatanya.moyenpaiement.application.metier.usecases.commun.pub.IModePaiement;
import com.rmawatanya.reglement.application.metier.valueobjects.MouvementQuittanceVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.prm.Societe;
import eai.devass.services.IAppelService;
import eai.devass.services.ServicesExternes;
import eai.devass.services.impl.AppelServiceManager;
import eai.devass.sinistreat.appli.utils.IConstantes;



@SuppressWarnings("unchecked")
public class CommonGsrUtils extends StringUtils {
	
	protected Logger logger = Logger.getLogger("loggerSINAT");
	private static CommonGsrUtils instance;
	
	
	
	public synchronized static CommonGsrUtils getInstance() {
		if(instance == null) { 
			return new CommonGsrUtils();
		}
		else {
			return instance;
		}
	}
	
	public String getCurrentTrimestre() {
		return getCurrentTrimestre(new Date());
	}
	
	public String getCurrentTrimestre(Date date) {
		Calendar toDay = new GregorianCalendar();
		toDay.setTime(date);
		String str = toDay.get(Calendar.YEAR) + "t";
		int month = toDay.get(Calendar.MONTH) + 1;
		if(month < 4) {
			str += "1";
		} else if(month < 7) {
			str += "2";
		} else if(month < 10) {
			str += "3";
		} else {
			str += "4";
		}
		
		return str;
	}
	
	public String getLastTrimestre() {
		Calendar toDay = DateUtile.dateCalendarCourante();
		int year = toDay.get(Calendar.YEAR);
		//correction sonar Dead store to local variable.
		String str = "";
		int month = toDay.get(Calendar.MONTH) + 1;
		if(month < 4) 
		{ 		
			str = "4";
			year --; 
		} 
		else if(month < 7) {
			str = "1";
		} else if(month < 10) {
			str = "2";
		} else {
			str = "3";
		}
		
		return year + "t" + str;
	}
	
	public Date getDateDebutCurrentTrimestre()  {
		Calendar toDay = DateUtile.dateCalendarCourante();
		String str = null;
		int month = toDay.get(Calendar.MONTH) + 1;
		if(month < 4) {
			str = "01";
		} else if(month < 7) {
			str = "04";
		} else if(month < 10) {
			str = "07";
		} else {
			str = "10";
		}
		
		Date date = null;
		
		try {
			date = (new SimpleDateFormat("ddMMyyyy")).parse("01" + str
					+ toDay.get(Calendar.YEAR));
			
		} catch(Exception e) { }
		
		return date;
	}
	
	public Date getDateFinCurrentTrimestre()  {
		Calendar toDay = DateUtile.dateCalendarCourante();
		String mois = null;
		String day = null;
		int month = toDay.get(Calendar.MONTH) + 1;
		if(month <= 3) {
			mois = "03";
			day = "31";
		}
		else if(month <= 6) {
			mois = "06";
			day = "30";
		}
		else if(month <= 9) {
			mois = "09";
			day = "30";
		}
		else {
			mois = "12";
			day = "31";
		}
		
		Date date = null;
		
		try {
			date = (new SimpleDateFormat("ddMMyyyy")).parse(day + mois
					+ toDay.get(Calendar.YEAR));
			
		} catch(Exception e) { }
		
		return date;
	}
	
	/*
	 * Début:
	 * 
	 * Mossab Add méthode getDateFinCurrentTrimestre avec paramètre de type Date
	 * 
	 */
	public Date getDateFinCurrentTrimestre(Date date)  {
		Calendar toDay = DateUtile.dateCalendarCourante();
		String mois = null;
		String day = null;
		toDay.setTime(date);
		int month = toDay.get(Calendar.MONTH) + 1;
		if(month <= 3) {
			mois = "03";
			day = "31";
		}
		else if(month <= 6) {
			mois = "06";
			day = "30";
		}
		else if(month <= 9) {
			mois = "09";
			day = "30";
		}
		else {
			mois = "12";
			day = "31";
		}
		
		try {
			date = (new SimpleDateFormat("ddMMyyyy")).parse(day + mois
					+ toDay.get(Calendar.YEAR));
			
		} catch(Exception e) { }
		
		return date;
	}
	/*
	 * Fin.
	 */
	
	public Date getDateDebutLastTrimestre()  {
		Calendar toDay = DateUtile.dateCalendarCourante();
		String mois = "";
		int year = toDay.get(Calendar.YEAR);
		int month = toDay.get(Calendar.MONTH) + 1;
		if(month <= 3) 
		{ 		
			mois = "10";
			year --;
		} 
		else if(month <= 6) {
			mois = "01";
		} else if(month <= 9) {
			mois = "03";
		} else {
			mois = "07";
		}
		Date date = null;
		try {
			date = (new SimpleDateFormat("ddMMyyyy")).parse("01" + mois + year);
			
		} catch(Exception e) { }
		return date;
	}
	
	
	public boolean isTrue(Boolean boolean1) {
		if(boolean1 != null && boolean1) {
			return true;
			
		} else {
			return false;
		}
	}
	
	public boolean isEmpty(List list) {
		
		if(list == null || list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public synchronized static boolean isNumeric(String str) {

		if (str == null || "".equals(str)) {
			return false;
		}

		str = str.replaceAll(",", ".");
		str = str.replaceAll(" ", "");
		boolean dejaVu = false;
		for (int i = 0; i < str.length(); i++) {

			if ('.' == str.charAt(i) && dejaVu) {
				return false;
			}

			if ('.' == str.charAt(i)) {
				dejaVu = true;
				continue;
			}

			// Pour les entier négative
			if (i == 0 && '-' == str.charAt(i)) {
				continue;
			}

			// If we find a non-digit character we return false.
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public String getMessageFromResult(IResult result) {

		if (result == null) {
			return "result is null";
		}

		List<IMessageItem> listMessage = result.getMessagesItem();
		if(isEmpty(listMessage)) {
			return "ListMessage errors est vide !!!!";
		}
		
		StringBuilder msgOut = new StringBuilder();
		for (IMessageItem curMessageItem : listMessage) {
			if(curMessageItem == null) {
				continue;
			}
			
			if (!StringUtils.isEmpty(curMessageItem.getCodeMessage())) {
				msgOut.append(curMessageItem.getCodeMessage()
						.replaceAll("java.lang.Exception: ", "")
						.replaceAll("ma.co.omnidata.framwork.services.validation.exception.ValidationException:", ""));
				if(curMessageItem.getLibelleMessage() != null) {				
					msgOut.append(curMessageItem.getLibelleMessage()).append(" : ");
				}
				if (curMessageItem.getValues() != null
						&& curMessageItem.getValues().length > 0) {
					if(curMessageItem.getValues()[0] != null) {
						msgOut.append(curMessageItem.getValues()[0]);
					}
				}
				
				
			}
		}

		return msgOut.toString();
	}
	
	public synchronized void genearteMouvementReglement(QuittanceGsr quittanceGsr) throws ExceptionMetier {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		IAppelService appelService = new AppelServiceManager();
		String date = dateFormat.format(new Date());
		MouvementQuittanceVO mouvementQuittanceVO = new MouvementQuittanceVO();
		mouvementQuittanceVO.setNumQuittance(quittanceGsr.getNumeroQuittance());
		Double mntQuittance = new BigDecimal(quittanceGsr.getMontant()).setScale(2,
				BigDecimal.ROUND_HALF_EVEN).doubleValue();
		mouvementQuittanceVO.setMntReglement(String.valueOf(mntQuittance));
		mouvementQuittanceVO.setRefReglement("000000");
		mouvementQuittanceVO.setModReglement(IModePaiement.VIREMENT);
		mouvementQuittanceVO.setMotifEtat("QUITTANCE EQUILIBRE GSR ==> AT");
		mouvementQuittanceVO.setDatEtat(date);
		
		// Appel de service
		appelService.setConvert(false);
		appelService.appelService(ServicesExternes.REGLER_QUITTANCE,
						mouvementQuittanceVO, IConstantes.PROFIL_ANNULATION);

	}

	
	public synchronized Integer getAgeBetweenTwoDates(Date dateNaissance, Date DateCalcul) {

//		Calendar today = Calendar.getInstance(); 
//		Calendar dateNaissanceCal = new GregorianCalendar();
//		dateNaissanceCal.setTime(dateNaissance);
//		today.setTime(DateCalcul);
//		int ageRentier = today.get(Calendar.YEAR) - dateNaissanceCal.get(Calendar.YEAR);  
//		if (today.get(Calendar.MONTH) < dateNaissanceCal.get(Calendar.MONTH)) {
//			ageRentier--;  
//		} else if (today.get(Calendar.MONTH) == dateNaissanceCal.get(Calendar.MONTH)
//		    && today.get(Calendar.DAY_OF_MONTH) < dateNaissanceCal.get(Calendar.DAY_OF_MONTH)) {
//			ageRentier--;  
//		}
		//Evol 01/10/2020: Arrondir l'age 
		int timeDebut = (int) (dateNaissance.getTime()/ (24 * 60 * 60 * 1000));
		int timeFin = (int) (DateCalcul.getTime()/ (24 * 60 * 60 * 1000));
		int ageRentier = (int) Math.round((timeFin - timeDebut)/365d);
		return ageRentier;

	}
	@SuppressWarnings("deprecation")
	public synchronized int getjoursBetweenTwoDates(Date dateNaissance, Date DateCalcul) throws ParseException {
//		Calendar date1 = new GregorianCalendar();
//		Calendar date2 = new GregorianCalendar();
//		date1.setTime(dateNaissance);
//		Date dateAvant = date1.getTime();
//		date2.setTime(DateCalcul);
//		Date dateApres = date2.getTime();
//    	long diff = dateApres.getTime() - dateAvant.getTime();
//    	int res = (int) (diff / (1000*60*60*24));
		int timeDebut = (int) (dateNaissance.getTime()/ (24 * 60 * 60 * 1000));
		int timeFin = (int) (DateCalcul.getTime()/ (24 * 60 * 60 * 1000));
    	return timeFin-timeDebut;

	}
	/**
	 * 
	 * WMOS: 11/11/2015
	 * EVO - formule de calcul de l’âge uniquement pour le mouvement de consignation cnra:
	 * Partie entière[(Date limite de paiement-date de naissance)/360]
	 * 
	 */
	public synchronized Integer getAgeBetweenTwoDatesCNRA(Date dateNaissance,
			Date DateCalcul) {
		
		int anneeDebut = (int) (dateNaissance.getTime()/ (24 * 60 * 60 * 1000));
		int anneeFin = (int) (DateCalcul.getTime()/ (24 * 60 * 60 * 1000));
		int ageRentier = (anneeFin - anneeDebut) / 360;

		return ageRentier;
	}
	/**
	 * Fin EVO : formule de calcul de l’âge
	 */
	public synchronized String getRefSociter(String idSociete) {

		if (CommonUtils.isEmpty(idSociete)) {
			return "XX";
		}

		Societe[] societes = Societe.class.getEnumConstants();
		for (Societe curSociete : societes) {
			if (idSociete.equals(String.valueOf(curSociete.getId()))) {
				return curSociete.getRefSociete();
			}
		}

		return "XX";
	}
	
	

}
