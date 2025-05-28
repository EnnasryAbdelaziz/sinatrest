package eai.devass.gsr.appli.utile;

public interface IMessageException {

	/** GSR */
	public static final String EXP_COEF_AGE_NON_TROUVE = "Coefficient d'age non trouv� pour les param�tres en entr�e.";
	public static final String EXP_RENTIER_INTROUVALE = "Rentier introuvable";
	public static final String EXP_HERITIER_INTROUVALE = "Heritier introuvable";
	public static final String EXP_QUITTANCE_INTROUVALE = "Quittance introuvable";
	public static final String EXP_STAND_MESS = "Une erreur est survenue lors de l'op�ration.";
	public static final String EXP_OBJECT_ENTREE = "L'object en entr�ee ne peut pas �tre null.";
	public static final String EXP_RENTE_NON_VALIDE = "La rente du rentier n'a pas �t� encore valid�";
	public static final String EXP_CLASSE_RENTIER_INTROUVALE = "La classe du rentier est introuvable";
	public static final String EXP_DATEDEBUT_SUP_DATEFIN = "La date de d�but ne peut �tre superieur � date de fin!";
	public static final String EXP_DATE_DECES_NULL= "La date de d�c�s ne peut �tre null!";
	public static final String EXP_ETAT_RENTIER_NULL= "L'�tat du rentier ne peut �tre null!";
	public static final String EXP_TROP_PRECU_NEGATIF= "Veuillez notifier le service recours pour proc�der � la r�cup�ration de l�indu";
	public static final String EXP_COMPLEMENT_RACHAT_NULL = "Le compl�ment rachat ne peut �tre null";
	public static final String EXP_QUITTANCE_MNT_PERCU ="Impossible de cr�er la quitatnce GSR du montant des arr�rages !!!";
	public static final String EXP_NBRE_JOURS_REGLES = "Pas de r�glement effectu� pendant l'ann�e de d�c�s !!";
	public static final String EXP_TROP_PRECU= " Veuillez proc�der au mouvement de r�cup�ration du trop-per�u ";
	public static final String EXP_RESERVE_GRAVE_INSUFFISSANTE= "R�serve grave insuffisante, Impossible de valider le mouvement ";
	public static final String EXP_RESERVE_GRAVE_NULL= " La r�serve grave ne peut �tre null ";
	public static final String EXP_CAPITAL_CONSTITUF_NULL= " Le capital GSR ne peut �tre null";
	public static final String EXP_RESERVE_GRAVE_INSUFFISANTE= " R�serve grave insuffisante, Impossible de valider le mouvement ";
	public static final String EXP_PROTHESE_INTROUVALE = "Prothese introuvable";
	public static final String EXP_DATE_CALCUL_NULL= "La date de calcul ne peut �tre null!";

}
