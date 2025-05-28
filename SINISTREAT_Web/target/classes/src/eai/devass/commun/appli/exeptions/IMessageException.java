package eai.devass.commun.appli.exeptions;

public interface IMessageException {
	/**Appel Service*/
	public static final String EXP_APPEL_SERVICE = "Une erreur est survenue lors de l'appel service.";
	public static final String EXP_INIT_SERVICE = "Une erreur est survenue lors de l'initialisation du service: ";
	public static final String EXP_NOSUCHMETHOD_SERVICE = "Erreur appel service. Il n'y aucune méthode avec le nom: ";
	public static final String EXP_SECURITY_SERVICE = "Erreur appel service.Pas d'authorisation d'appel.";
	public static final String EXP_RESULT_NULL = "Le resultat retourné par le service est null.";
	public static final String EXP_CONVERT_OBJECT = "Une erreur est survenue lors de la convertiond de l'objet.";
	public static final String EXP_INFO_SERVICE = "Les informations du services sont obligatoires.";
	public static final String EXP_SERVICE_NONRMI = "Le service retourné n'est pas un service d'instance RmiProxyFactoryBean.";
	
}
