package eai.devass.sinistreat.appli.utils.entites;

public interface IDate {

	public final static String FORMAT_SIMPLE = "dd/MM/yyyy";
	public final static String FORMAT_IHM = "yyyyMMdd";
	public final static String FORMAT_ANNEE = "yyyy";
	public final static String FORMAT_MOIS = "MM";
	public final static String FORMAT_HHMM = "dd/MM/yyyy HH:mm:ss";
	public final static String FORMAT_HHMM_SQL = "dd/MM/yyyy HH24:MI:SS";
	public final static String FORMAT_LONG = "E, dd MMMM yyyy";

	public final static String DATE_MAX = "01/01/5000";
	public final static String DATE_MIN = "01/01/1600";

	public final static String DATE_DEBUT = "DATE_DEBUT";
	public final static String DATE_FIN = "DATE_FIN";
	public final static String DATE_MODIFICATION = "DATE_MODIFICATION";

}
