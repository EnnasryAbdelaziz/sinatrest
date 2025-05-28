package eai.devass.sinistreat.appli.utils.Message;

public interface IMessage {

	public static final String SUCCES__CREATION_REPONSE = "Cr�ation r�ponse mission avec succ�s";
	/** Message validation r�glements. */
	public static final String MESSAGE_REGLEMENT_1 = "Il existe une quittance avec le m�me montant et la m�me rubrique cr��e aujourd'hui. Num�ro quittance: ";
	public static final String MESSAGE_REGLEMENT_2 = "Reglement non authoris�, �tat du sinistre incompatible.";
	public static final String MESSAGE_REGLEMENT_3 = "Le nom et le code du b�n�ficiaire ne correspondent pas.";
	public static final String MESSAGE_REGLEMENT_4 = "Le montant du r�glement d�passe ";
	public static final String MESSAGE_REGLEMENT_5 = "Veuillez ajouter au moins une rubrique.";
	public static final String MESSAGE_REGLEMENT_6 = "Le montant du r�glement d�passe la r�serve.";
	public static final String MESSAGE_REGLEMENT_7 = "Le numero de ch�que doit �tre sur 8 ou 10 positions.";
	public static final String MESSAGE_REGLEMENT_8 = "Le date d'�tablissement doit �tre inf�rieure � la date de r�glement.";
	public static final String MESSAGE_REGLEMENT_9 = "Le nom et le code de l'auxiliaire ne correspondent pas.";
	public static final String INFO = "INFO";
	public static final String MSG_GSR_TROP_PERCU = "Veuillez ex�cuter le mouvement de r�cup�ration du trop-per�u:";
	public static final String MSG_GSR_MNT_VERSEE_ZERO= "Le montant � verser aux h�ritiers est z�ro!!!";
	public static final String EXP_TROP_PRECU_NEGATIF= "Veuillez notifier le service recours pour proc�der � la r�cup�ration de l�indu";
	public static final String MSG_GSR_MNT_INDU = "Indu sup�rieur � 5000 DHS. Merci de notifier le service recours";
	public static final String MSG_AUCUN_REGLEMENT= "Aucun r�glement n'a �t� effectu� pour ce rentier";
	public static final String MSG_RG_INSUFFISANTE= "R�serve grave insuffisante!!!";
	public static final String MESSAGE_REGLEMENT_10 ="R�glement d�j� effectu� avec le m�me montant et la m�me r�f�rence auxiliaire, merci de se r�f�rer � la quittance "; 
	public static final String MESSAGE_REGLEMENT_11 ="L'auxilliaire est assujetti � la RAS sans saisi de la rubrique RAS, merci de se ref�rer au r�f�rentiel pour le prestataire "; 
	public static final String MESSAGE_REGLEMENT_12 ="L'auxilliaire n'est pas assujetti � la RAS avec saisi de la rubrique RAS, merci de se ref�rer au r�f�rentiel pour le prestataire "; 

}
