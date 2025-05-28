package eai.devass.sinistreat.appli.utils;

import eai.devass.sinistreat.appli.utils.entites.IParam;


public class Config {
	   private static Config instance = null;
	   //private static boolean rmiMode =false;
	   private static boolean maquetteMode =false;
	   private static boolean eaiAuthMode =false;
	   private static boolean localAuthMode =true;
	   private static boolean withTransactionMode =false;
	   private static String editionPath;
	   private static String adresseRef;
	   private static String adresseMiss;
	   private static String adresseRecours;
	   private static String adresseRgl;
	   private static String adresseMoyPai;
	   private static String adresseAs00SinAT;
	   private static String adresseAs00SinATLog;
	   private static String adresseAs00SinATPass;
	   
	   private Config() {
	      // Exists only to defeat instantiation.
		   setConfigFromBundle();
	   }
	   public final synchronized  static Config getInstance() {
	      if(instance == null) {	    	  
	    	  instance = new Config();
	      }
	      return instance;
	   }
	   
	   
		public static void setConfigFromBundle() {
			//String rmiMode = BundleTools.getInstance().getDefaultMessage(IParam.APP_MODE);
//			Config.adresseRef = BundleTools.getInstance().getDefaultMessage(IParam.ADRESSE_SERVICE_REF);
//			Config.adresseMiss = BundleTools.getInstance().getDefaultMessage(IParam.ADRESSE_SERVICE_MISSION);
//			Config.adresseRgl = BundleTools.getInstance().getDefaultMessage(IParam.ADRESSE_SERVICE_REGLEMENT);
//			Config.adresseMoyPai = BundleTools.getInstance().getDefaultMessage(IParam.ADRESSE_SERVICE_MOYPAI);
			Config.adresseRecours = BundleTools.getInstance().getDefaultMessage(IParam.ADRESSE_SERVICE_RECOURS);
			Config.adresseAs00SinAT =BundleTools.getInstance().getDefaultMessage(IParam.ADRESSE_SERVICE_AS400_SINAT_HOST);
			Config.adresseAs00SinATLog =BundleTools.getInstance().getDefaultMessage(IParam.ADRESSE_SERVICE_AS400_SINAT_LOG);
			Config.adresseAs00SinATPass =BundleTools.getInstance().getDefaultMessage(IParam.ADRESSE_SERVICE_AS400_SINAT_PAS);

//			if (rmiMode.equals("true")){
//				Config.rmiMode=true;
//			}else{
//				Config.rmiMode=false;
//			}
//			String maquetteMode = BundleTools.getInstance().getDefaultMessage(IParam.APP_MAQ);
//			String authLocal = BundleTools.getInstance().getDefaultMessage(IParam.APP_AUTH_LOCAL);
//			String withTransaction = BundleTools.getInstance().getDefaultMessage(IParam.APP_TRANSACTION);
//			String eaiAuthMode = BundleTools.getInstance().getDefaultMessage(IParam.EAI_AUTHENTIFICATION_MODE);
//			String editionPath= this.getServletContext().getRealPath(IConstantes.PATH_EDITION);
			
//			if (maquetteMode.equals("true")) 
//				Config.maquetteMode=true;
//			else
//				Config.maquetteMode=false;
//			
//			if (authLocal.equals("true")) 
//				Config.localAuthMode=true;
//			else
//				Config.localAuthMode=false;
//			
//			if (withTransaction.equals("true")) 
//				Config.withTransactionMode=true;
//			else
//				Config.withTransactionMode=false;
			

			
//			if (eaiAuthMode.equals("true"))
//				Config.eaiAuthMode=true;
//			else
//				Config.eaiAuthMode=false;
			
		}
		
	   
	public static boolean isMaquetteMode() {
		return maquetteMode;
	}
	public static void setMaquetteMode(boolean maquetteMode) {
		Config.maquetteMode = maquetteMode;
	}
	public static boolean isEaiAuthMode() {
		return eaiAuthMode;
	}
	public static void setEaiAuthMode(boolean eaiAuthMode) {
		Config.eaiAuthMode = eaiAuthMode;
	}
	public static boolean isLocalAuthMode() {
		return localAuthMode;
	}
	public static void setLocalAuthMode(boolean localAuthMode) {
		Config.localAuthMode = localAuthMode;
	}
	public static boolean isWithTransactionMode() {
		return withTransactionMode;
	}
	public static void setWithTransactionMode(boolean withTransactionMode) {
		Config.withTransactionMode = withTransactionMode;
	}
	public static String getEditionPath() {
		return editionPath;
	}
	public static void setEditionPath(String editionPath) {
		Config.editionPath = editionPath;
	}
	public static String getAdresseRef() {
		return adresseRef;
	}
	public static void setAdresseRef(String adresseRef) {
		Config.adresseRef = adresseRef;
	}
	public static String getAdresseMiss() {
		return adresseMiss;
	}
	public static void setAdresseMiss(String adresseMiss) {
		Config.adresseMiss = adresseMiss;
	}
	public static String getAdresseRgl() {
		return adresseRgl;
	}
	public static void setAdresseRgl(String adresseRgl) {
		Config.adresseRgl = adresseRgl;
	}
	public static String getAdresseMoyPai() {
		return adresseMoyPai;
	}
	public static void setAdresseMoyPai(String adresseMoyPai) {
		Config.adresseMoyPai = adresseMoyPai;
	}
	public static void setInstance(Config instance) {
		Config.instance = instance;
	}
	public static String getAdresseRecours() {
		return adresseRecours;
	}
	public static void setAdresseRecours(String adresseRecours) {
		Config.adresseRecours = adresseRecours;
	}
	public static String getAdresseAs00SinAT() {
		return adresseAs00SinAT;
	}
	public static void setAdresseAs00SinAT(String adresseAs00SinAT) {
		Config.adresseAs00SinAT = adresseAs00SinAT;
	}
	public static String getAdresseAs00SinATLog() {
		return adresseAs00SinATLog;
	}
	public static void setAdresseAs00SinATLog(String adresseAs00SinATLog) {
		Config.adresseAs00SinATLog = adresseAs00SinATLog;
	}
	public static String getAdresseAs00SinATPass() {
		return adresseAs00SinATPass;
	}
	public static void setAdresseAs00SinATPass(String adresseAs00SinATPass) {
		Config.adresseAs00SinATPass = adresseAs00SinATPass;
	}


}
