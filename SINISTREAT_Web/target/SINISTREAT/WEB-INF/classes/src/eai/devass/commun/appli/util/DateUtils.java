package eai.devass.commun.appli.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ma.co.omnidata.framework.utile.DateUtile;

public class DateUtils extends DateUtile {

	private static String FORMAT_DATE_VALIDE = "((0[1-9]|[1-2][0-9]|30|31)/(0[1-9]|1[0-2])/([0-9]{4}))";
	private static String FORMAT_DATE_VALIDE_YYYYMMDD = "(([0-9]{4})(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|30|31))";
	private static String FORMAT_DATE_VALIDE_2 = "(([0-9]{4})[- / ]?(0[1-9]|1[0-2])[- / ]?(0[1-9]|[1-2][0-9]|30|31))";
	private static String FORMAT_HEURE_VALIDE = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
	  
	
	public final static String FORMAT_DATE_yyyyMMdd = "yyyyMMdd";
	public final static String FORMAT_DATE_yyyy_MM_dd = "yyyy-MM-dd";
	public final static String FORMAT_DATE_HH_MM = "HH:mm";
	public final static int DAYS_IN_YEAR = 360;

	public synchronized static boolean isDate(String sDate) {
		if(sDate == null) {
			return false;
		}
		
		Pattern pattern = Pattern.compile(FORMAT_DATE_VALIDE + "|" + FORMAT_DATE_VALIDE_2);
		Matcher matcher = pattern.matcher(sDate);
		return matcher.matches();
	}
	
	public synchronized static String getPattern(String sDate) {
		if(sDate == null) {
			return null;
		}
		
		Pattern pattern = Pattern.compile(FORMAT_DATE_VALIDE);
		Matcher matcher = pattern.matcher(sDate);
		if(matcher.matches()) {
			return DEFAULT_FORMAT;
			
		} else {
			pattern = Pattern.compile(FORMAT_DATE_VALIDE_YYYYMMDD);
			matcher = pattern.matcher(sDate);
			if(matcher.matches()) {
				return FORMAT_DATE_yyyyMMdd;
			}
		}
		
		return null;
	}

	public synchronized static boolean isHeure(String heure) {
		if(heure == null) {
			return false;
		}
		Pattern pattern = Pattern.compile(FORMAT_HEURE_VALIDE);
		Matcher matcher = pattern.matcher(heure);
		return matcher.matches();
	}
	
	public synchronized static Date getDateDefaultFormat(Date date) {
		return DateUtils.getDate(DateUtils.DEFAULT_FORMAT, DateUtils
				.dateToString(DateUtils.DEFAULT_FORMAT, date));
	}
	
	public static Date getDateDefaultFormat(String dateSt) {
		return DateUtils.getDate(DateUtils.DEFAULT_FORMAT, dateSt);
	}
	
	
	public synchronized static String dateToString(String format, Date fDate) {
		if(fDate == null) {
			return null;
		}
		
		return (new SimpleDateFormat(format)).format(fDate);
		
	}
	
	public synchronized static String dateToString( Date fDate) {
		if(fDate == null) {
			return null;
		}
		
		return (new SimpleDateFormat(DEFAULT_FORMAT)).format(fDate);
		
	}
	
	public synchronized static String dateSqlServer(String str)
	{
		StringTokenizer stringTokenizer=new StringTokenizer(str,"/");
		String j=stringTokenizer.nextToken();
		String m=stringTokenizer.nextToken();
		String a=stringTokenizer.nextToken();
		return a+"-"+m+"-"+j;
		
	}
	public synchronized static Date getDate(String format, String fDate)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date lDate = null;
		try {
			lDate = formatter.parse(fDate);
		} catch(Exception e) {
			return null;
		}
				 
		return lDate;
	}
	public synchronized static int compareDate(String date1, String date2)
	{
		// Avant d'utiliser cette methode
		// vous devez etre sur que les date sont valide

		long diff = getDate(DEFAULT_FORMAT, date1).getTime()
				- getDate(DEFAULT_FORMAT, date2).getTime();

		if (diff > 0)
		 {
			return 1; // date1 > date2
		}
		if (diff < 0)
		 {
			return -1; // date1 < date2
		}
		return 0; // date1 = date2
	}
	/**
	 * Comparaison entre deux dates
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public synchronized static int compareDate(String date1, String date2, String format)
	{
		// Avant d'utiliser cette methode
		// vous devez etre sur que les date sont valide

		long diff = getDate(format, date1).getTime() - getDate(format, date2).getTime();

		if (diff > 0)
		 {
			return 1; // date1 > date2
		}
		if (diff < 0)
		 {
			return -1; // date1 < date2
		}
		return 0; // date1 = date2
		
	}
	
	public synchronized static Date getDateWithAllFormat(String dateSt) {
		if(dateSt == null) {
			return null;
		}
		
		if(dateSt.indexOf("/") > 0) {
			return DateUtils.getDate(DEFAULT_FORMAT, dateSt);
		} else if(dateSt.indexOf("-") > 0) {
			return DateUtils.getDate(FORMAT_DATE_yyyy_MM_dd, dateSt);
		}
		
		return null;
	}
	
	public synchronized static long diffDateByDay(Date date1, Date date2) {
		
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);
		long cal1InMilS = cal1.getTimeInMillis();
		GregorianCalendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);
		long cal2InMilS = cal2.getTimeInMillis();
		
		return (cal2InMilS - cal1InMilS) / 86400000;
	}
	
	public synchronized static Date parseDate(String dateSt) throws Exception {
		
		String pattern = getPattern(dateSt);
		if(pattern == null) {
			pattern = FORMAT_DATE_yyyy_MM_dd;
		}
		
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(dateSt);
		
	}
	
	public synchronized static Calendar addDaysToDate(Date date, int days) {
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.setLenient(true);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + days);
		return cal;
		
	}
	
}
