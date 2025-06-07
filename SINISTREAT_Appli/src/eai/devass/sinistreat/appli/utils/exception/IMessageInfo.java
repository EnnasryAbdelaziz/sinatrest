package eai.devass.sinistreat.appli.utils.exception;

public interface IMessageInfo {

	/** CreateMissionUC */
	public static final String INF_MISSION_CREATE_OK = "INF_MISSION_CREATE_OK";

	/** ValidateMissionUC */
	public static final String INF_MISSION_VALIDATE_OK = "INF_MISSION_VALIDATE_OK";
	public static final String INF_OBJECT_MAIL_AFFECTATION = "INF_OBJECT_MAIL_AFFECTATION";

	/** ModifierMissionUC */
	public static final String INF_OBJECT_MAIL_NOTIFICATION = "INF_OBJECT_MAIL_NOTIFICATION";

	/** CreateEntiteUC */
	public static final String INF_CREATE_ENTITE = "INF_CREATE_ENTITE";
	public static final String INF_CREATE_PRESTATAIRE = "INF_CREATE_PRESTATAIRE";
	public static final String INF_CREATE_CONVENTION = "INF_CREATE_CONVENTION";

	/** UpdateEntiteUC */
	public static final String INF_UPDATE_ENTITE = "INF_UPDATE_ENTITE";

	/** CreateListRelationBridgeEntiteUC */
	public static final String INF_CREATE_ENTITE_BRIDGE = "INF_CREATE_ENTITE_BRIDGE";

	/** UpdateListRelationBridgeEntiteUC */
	public static final String INF_UPDATE_ENTITE_BRIDGE = "INF_UPDATE_ENTITE_BRIDGE";

	/** ReaffectationPrestataireUC */
	public static final String PRESTATAIRE_AFFECTER_OK = "PRESTATAIRE_AFFECTER_OK";
	public static final String SUCCES_CREATION_REPONSE = "SUCCES_CREATION_REPONSE";
	public static final String SUCCES_CREATION_COMPLEMENT = "SUCCES_CREATION_COMPLEMENT";
	public static final String SUCCES_CREATION_REFUS = "SUCCES_CREATION_REFUS";
	public static final String SUCCES_CREATION_REPONSE_COMPLEMENT = "SUCCES_CREATION_REPONSE_COMPLEMENT";
	public static final String SUCCES_CREATION_POURSUITE = "SUCCES_CREATION_POURSUITE";
	public static final String SUCCES_MODIFICATION = "SUCCES_MODIFICATION";
	public static final String SUCCES_CREATION_PRECISION = "SUCCES_CREATION_PRECISION";
	public static final String SUCCES_ANNULATION = "SUCCES_ANNULATION";
	public static final String SUCCES_ANNULATION_QUITTANCE = "SUCCES_ANNULATION_QUITTANCE";
	public static final String SUCCES_CLOTURE = "SUCCES_CLOTURE";
	public static final String SUCCES_CREATION_REGLEMENT = "SUCCES_CREATION_REGLEMENT";
	public static final String SUCCES_AFFECTATION_REGLEMENT_N1 = "SUCCES_AFFECTATION_REGLEMENT_N1";
	public static final String SUCCES_AFFECTATION_REGLEMENT_N2 = "SUCCES_AFFECTATION_REGLEMENT_N2";

	/** ValiderReponseUC */
	public static final String VALIDER_REPONSE_MISSON = "VALIDER_REPONSE_MISSON";

	/** #### GenAutoQuittanceForfaitUC */
	public static final String QUITTANCE_FORFAIT_GENERE = "QUITTANCE_FORFAIT_GENERE";
	/** Message sinistre **/
	public static final String ETAT_CREATION_SINISTRE = "Creation Sinistre En Instance";
	public static final String MOTIF_CREATION_SINISTRE1 = "Creation sinistre reprise en instance";
	public static final String MOTIF_MOUVEMENT_SIN_REG = "CREATION REGLEMENT";
	public static final String MOTIF_MOUVEMENT_SIN_REG_MODIF = "MODIFICATION REGLEMENT";
	public static final String MOTIF_MOUVEMENT_SIN_REG_SUPP = "ANNULATION REGLEMENT AVANT VALIDATION";
	public static final String MOTIF_ETAT1 = "Validation quotidienne";
	public static final String MOTIF_ETAT5 = "Sinistre en instance de validation siège";
	public static final String MOTIF_MOUVEMENT_SIN_PROC_AUD = "CREATION Audiance";
	public static final String ETAT_MODIFICATION_ETAT_SINISTRE ="Modification Etat Sinistre";
}
