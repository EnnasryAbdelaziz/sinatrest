package eai.devass.sinistreat.appli.utils.Message;

public interface IMessage {

	public static final String SUCCES__CREATION_REPONSE = "Création réponse mission avec succés";
	/** Message validation règlements. */
	public static final String MESSAGE_REGLEMENT_1 = "Il existe une quittance avec le même montant et la même rubrique créée aujourd'hui. Numéro quittance: ";
	public static final String MESSAGE_REGLEMENT_2 = "Reglement non authorisé, état du sinistre incompatible.";
	public static final String MESSAGE_REGLEMENT_3 = "Le nom et le code du bénéficiaire ne correspondent pas.";
	public static final String MESSAGE_REGLEMENT_4 = "Le montant du règlement dépasse ";
	public static final String MESSAGE_REGLEMENT_5 = "Veuillez ajouter au moins une rubrique.";
	public static final String MESSAGE_REGLEMENT_6 = "Le montant du règlement dépasse la réserve.";
	public static final String MESSAGE_REGLEMENT_7 = "Le numero de chéque doit être sur 8 ou 10 positions.";
	public static final String MESSAGE_REGLEMENT_8 = "Le date d'établissement doit être inférieure à la date de règlement.";
	public static final String MESSAGE_REGLEMENT_9 = "Le nom et le code de l'auxiliaire ne correspondent pas.";
	public static final String INFO = "INFO";
	public static final String MSG_GSR_TROP_PERCU = "Veuillez exécuter le mouvement de récupération du trop-perçu:";
	public static final String MSG_GSR_MNT_VERSEE_ZERO= "Le montant à verser aux héritiers est zéro!!!";
	public static final String EXP_TROP_PRECU_NEGATIF= "Veuillez notifier le service recours pour procéder à la récupération de l’indu";
	public static final String MSG_GSR_MNT_INDU = "Indu supérieur à 5000 DHS. Merci de notifier le service recours";
	public static final String MSG_AUCUN_REGLEMENT= "Aucun réglement n'a été effectué pour ce rentier";
	public static final String MSG_RG_INSUFFISANTE= "Réserve grave insuffisante!!!";
	public static final String MESSAGE_REGLEMENT_10 ="Règlement déjà effectué avec le même montant et la même référence auxiliaire, merci de se référer à la quittance "; 
	public static final String MESSAGE_REGLEMENT_11 ="L'auxilliaire est assujetti à la RAS sans saisi de la rubrique RAS, merci de se refèrer au référentiel pour le prestataire "; 
	public static final String MESSAGE_REGLEMENT_12 ="L'auxilliaire n'est pas assujetti à la RAS avec saisi de la rubrique RAS, merci de se refèrer au référentiel pour le prestataire "; 

}
