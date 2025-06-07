package eai.devass.gsr.appli.utile;

public interface IMessageException {

	/** GSR */
	public static final String EXP_COEF_AGE_NON_TROUVE = "Coefficient d'age non trouvé pour les paramètres en entrée.";
	public static final String EXP_RENTIER_INTROUVALE = "Rentier introuvable";
	public static final String EXP_HERITIER_INTROUVALE = "Heritier introuvable";
	public static final String EXP_QUITTANCE_INTROUVALE = "Quittance introuvable";
	public static final String EXP_STAND_MESS = "Une erreur est survenue lors de l'opération.";
	public static final String EXP_OBJECT_ENTREE = "L'object en entréee ne peut pas être null.";
	public static final String EXP_RENTE_NON_VALIDE = "La rente du rentier n'a pas été encore validé";
	public static final String EXP_CLASSE_RENTIER_INTROUVALE = "La classe du rentier est introuvable";
	public static final String EXP_DATEDEBUT_SUP_DATEFIN = "La date de début ne peut être superieur à date de fin!";
	public static final String EXP_DATE_DECES_NULL= "La date de décès ne peut être null!";
	public static final String EXP_ETAT_RENTIER_NULL= "L'état du rentier ne peut être null!";
	public static final String EXP_TROP_PRECU_NEGATIF= "Veuillez notifier le service recours pour procéder à la récupération de l’indu";
	public static final String EXP_COMPLEMENT_RACHAT_NULL = "Le complément rachat ne peut être null";
	public static final String EXP_QUITTANCE_MNT_PERCU ="Impossible de créer la quitatnce GSR du montant des arrérages !!!";
	public static final String EXP_NBRE_JOURS_REGLES = "Pas de règlement effectué pendant l'année de décès !!";
	public static final String EXP_TROP_PRECU= " Veuillez procéder au mouvement de récupération du trop-perçu ";
	public static final String EXP_RESERVE_GRAVE_INSUFFISSANTE= "Réserve grave insuffisante, Impossible de valider le mouvement ";
	public static final String EXP_RESERVE_GRAVE_NULL= " La réserve grave ne peut être null ";
	public static final String EXP_CAPITAL_CONSTITUF_NULL= " Le capital GSR ne peut être null";
	public static final String EXP_RESERVE_GRAVE_INSUFFISANTE= " Réserve grave insuffisante, Impossible de valider le mouvement ";
	public static final String EXP_PROTHESE_INTROUVALE = "Prothese introuvable";
	public static final String EXP_DATE_CALCUL_NULL= "La date de calcul ne peut être null!";

}
