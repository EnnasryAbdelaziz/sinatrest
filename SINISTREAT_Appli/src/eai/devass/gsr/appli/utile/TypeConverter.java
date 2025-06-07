package eai.devass.gsr.appli.utile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

import ma.co.omnidata.framework.utile.TypeConverterAbst;

import org.apache.commons.lang.StringUtils;

public class TypeConverter extends TypeConverterAbst {

	private static TypeConverter instance;

	public Calendar stringToCalendarObject(String date) {
		return super.stringToCalendar(date);
	}

	public Date calendarToDate(Calendar calendar) {// gwt
		if (calendar != null) {
			return calendar.getTime();
		} else {
			return null;
		}
	}

	public Calendar dateToCalendar(Date date) {// gwt
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} else {
			return null;
		}
	}

	public Float stringToFloatObject(String f) {
		if (StringUtils.isEmpty(f)){
			return null;
		}
		return Float.valueOf(f);
	}

	public String integerToString(Integer integerValue) {
		if (integerValue == null) {
			return null;
		}
		return String.valueOf(integerValue);
	}

	public Boolean stringToBooleanObject(String s) {
		if (StringUtils.isEmpty(s)) {
			return null;
		}
		return Boolean.valueOf("on".equals(s));
	}

	public String calendarToString(String format, Calendar date) {
		return super.calendarToString(format, date);
	}

	public String booleanToString(Boolean b) {
		if (b != null){
			return b.toString();
		}
		return "false";
	}

	public Integer stringToIntegerObject(String integer) {
		return super.stringToInteger(integer);
	}

	public Boolean stringToNonNullBooleanObject(String s) {
		Boolean res = Boolean.FALSE;
		if (!StringUtils.isEmpty(s)){
			res = Boolean.valueOf(Boolean.valueOf("on".equals(s))
					.booleanValue()
					|| Boolean.valueOf("true".equals(s)).booleanValue());
		}

		return res;
	}
	
	public double stringToDouble(String st)  {

		st = st.replaceAll(",", ".").replaceAll(" ", "");
		try {
			st = URLEncoder.encode(st, "ISO-8859-1").replaceAll("%A0", "");
			
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException();
		}
		
		return super.stringToDouble(st);
	}

	public synchronized static TypeConverterAbst getInstance() {
		if (instance == null) {
			instance = new TypeConverter();
		}
		return instance;
	}
	
}