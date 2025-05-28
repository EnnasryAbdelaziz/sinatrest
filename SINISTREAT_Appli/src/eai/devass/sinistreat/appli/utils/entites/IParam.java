package eai.devass.sinistreat.appli.utils.entites;

public interface IParam {
	
	public final static String UTILISATEUR = "user";
	public final static String CODESAS = "codeSas";
	public final static String CODEUSER = "codeUser";
	public final static String USER_CONTAINER_KEY  = "utilisateur";
	public final static String MISSION  = "mission";
	public final static String ETAT_CIBLE  = "etatCibleMission";
	public final static String ETAT_ACTUEL  = "etatActuelMission";
	public final static String LIST_REGLEMENT  = "listReglementBO";
	public final static String VALORISATION_MAX  = "valorisationMax";
	public final static String PREFIX_NUM_SINISTRE  = "0022";
	public final static String CODE_DECENTRALISATION_NUM_SINISTRE  = "1000";
	
	public final static String PAGER = "pager";
	public final static String OBJECT = "object";
	
	public static final String ID = "id";
	public static final String ID_MISSION = "idMission";
	public static final String NUM_SINISTRE = "numSinistre";
	public static final String INDEX = "index";
	public static final String MAD = "MAD";
	public static final String CHAINE_VIDE = "";
	public static final String MODIFIER = "modifier";
	
	
	// DAtes debut et fin modification : recherche la liste des missions historisées
	public final static String DATE_DEBUT = "DATE_DEBUT";
	public final static String DATE_FIN = "DATE_FIN";
	
	//public final static String APP_MODE = "application.mode.rmi";
	public final static String EAI_AUTHENTIFICATION_MODE = "application.eai.auth";	
	public final static String APP_MAQ = "application.mode.maquette";
	public final static String APP_AUTH_LOCAL = "application.local.auth";
	public final static String APP_TRANSACTION = "application.mode.transaction";
	
	public final static String SANS_REMPLACEMENT = "0";
	public final static String AVEC_REMPLACEMENT = "1";
	public final static String RECUPERATION_ANNULATION = "Recuperation annulation";
	public final static String CODE_SERVICE_ORDONNATEUR_AT = "W5116";
	public final static String CODE_SERVICE_ORDONNATEUR_AT_GRAVE = "W5137";
	public final static String CODE_SERVICE_ORDONNATEUR_GSR = "W5118";
	public final static String CODE_BRANCHE_AT = "1";
	public final static String CODE_SOUSTYPEQTC_GSR = "25";
	public final static String CODE_SOCIETE_AT = "002";
	public final static String CODE_SOCIETE_GESTION_AT = "21";
	
	// Pour le positionnement
	public static final String SOCIETESINISTRE = "002";
	public static final String TYPESINISTRE = "2";
	
	public final static String ETAT_SIN_ACTUEL = "etatSinActuel";
	public final static String ETAT_SIN_CIBLE = "etatSinCible";
	
	
	public final static String MAX_SINISTRE = "300";
	
	public final static Integer MAX_RESULT = Integer.valueOf(10);
	public final static Integer MAX_RESULT_REGLEMENT = Integer.valueOf(50);

	
	public final static String MODE_REGLEMENT_CHEQUE = "1";
	public final static String MODE_REGLEMENT_COMPENSATION = "2";
	
	public final static String ADRESSE_SERVICE_REF = "referentiel.host";//"10.100.162.61:1200";
	public final static String ADRESSE_SERVICE_RECOURS = "recoursentrant.host";//"localhost:1108";
	public final static String ADRESSE_SERVICE_REGLEMENT = "reglement.host";
	public final static String ADRESSE_SERVICE_MOYPAI = "moyenpaiement.host";
	public final static String ADRESSE_SERVICE_MISSION = "missionnement.host";
	public final static String ADRESSE_SERVICE_AS400_SINAT_HOST = "as400.sinistreat.host";
	public final static String ADRESSE_SERVICE_AS400_SINAT_LOG = "as400.sinistreat.host.login";
	public final static String ADRESSE_SERVICE_AS400_SINAT_PAS = "as400.sinistreat.host.pass";
	public final static String useZone = "true";
	
	public final static Double MONTANTMAXREGLEMENT = Double.valueOf(10000000);
	public final static String TYPE_QTC_REGLEMENT = "REGLEMENT";
	public final static String CODE_SERVICE_SINISTRE_SIMPLE ="W5116";
	public final static String CODE_SERVICE_SINISTRE_GRAVE ="W5137";
	
}


