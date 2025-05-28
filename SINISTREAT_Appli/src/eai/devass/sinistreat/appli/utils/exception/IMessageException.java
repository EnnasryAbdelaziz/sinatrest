package eai.devass.sinistreat.appli.utils.exception;

public interface IMessageException {

	/** Code errors messagse de la class : DefaultBusinessRule */
	public static final String ERR_DefaultBusinessRule_001 = "ERR_DefaultBusinessRule_001";
	public static final String ERR_DefaultBusinessRule_002 = "ERR_DefaultBusinessRule_002";
	public static final String ERR_DefaultBusinessRule_003 = "ERR_DefaultBusinessRule_003";

	/** Code exception de la class : BaseUC */
	public static final String EXP_JOURNAL_ACTION_REQUIRED = "EXP_JOURNAL_ACTION_REQUIRED";
	public static final String EXP_JOURNAL_MISSION_REQUIRED = "EXP_JOURNAL_MISSION_REQUIRED";
	public static final String EXP_JOURNAL_ETAT_REQUIRED = "EXP_JOURNAL_ETAT_REQUIRED";
	public static final String EXP_JOURNAL_CODEUSER_REQUIRED = "EXP_JOURNAL_CODEUSER_REQUIRED";
	public static final String EXP_JOURNAL_NAMEUSER_REQUIRED = "EXP_JOURNAL_NAMEUSER_REQUIRED";
	public static final String EXP_JOURNAL_USER_REQUIRED = "EXP_JOURNAL_USER_REQUIRED";
	public static final String EXP_JOURNAL_CHOIX_NOT_VALIDE = "EXP_JOURNAL_CHOIX_NOT_VALIDE";
	public static final String EXP_CODE_FONCTIONNALITE_REQUIRED = "EXP_CODE_FONCTIONNALITE_REQUIRED";

	/** Codes Exception de la class : CreateListRelationBridgeEntiteUCConverter */
	public static final String EXP_LIST_ENTITE_RELATION_REQUIRED = "EXP_LIST_ENTITE_RELATION_REQUIRED";
	public static final String EXP_CLASS_ENTITE_RELATION_REQUIRED = "EXP_CLASS_ENTITE_RELATION_REQUIRED";
	public static final String EXP_LIST_ENTITE_VO_REQUIRED = "EXP_LIST_ENTITE_VO_REQUIRED";
	public static final String EXP_TYPE_CLASS_NOT_VALIDE = "EXP_TYPE_CLASS_NOT_VALIDE";

	/** Codes Exception de la class : DefaultBusinessRule */
	public static final String EXP_DATE_FIN_VALIDITE_REQUIRED = "EXP_DATE_FIN_VALIDITE_REQUIRED";
	public static final String EXP_DATE_DEBUT_VALIDITE_REQUIRED = "EXP_DATE_DEBUT_VALIDITE_REQUIRED";
	public static final String EXP_DATES_VALIDITE_NOT_VALIDE = "EXP_DATES_VALIDITE_NOT_VALIDE";
	public static final String EXP_CREATE_RELATION_FAILED = "EXP_CREATE_RELATION_FAILED";
	public static final String EXP_UPDATE_RELATION_FAILED = "EXP_UPDATE_RELATION_FAILED";
	public static final String EXP_FIND_SIMILAR_ENTITE = "EXP_FIND_SIMILAR_ENTITE";
	public static final String EXP_FIND_SIMILAR_RELATION_BRIDGE = "EXP_FIND_SIMILAR_RELATION_BRIDGE";
	public static final String EXP_CRUD_EXCEPTION_ERROR = "EXP_CRUD_EXCEPTION_ERROR";

	/** Codes Exception de la class : ConventionPrestationBR */
	public static final String EXP_DATES_CONVETION_NOT_VALIDE = "EXP_DATES_CONVETION_NOT_VALIDE";
	public static final String EXP_DELETE_NOT_VALIDE = "EXP_DELETE_NOT_VALIDE";
	public static final String EXP_CONVENTION_CODE_ENTITE_REQUIRED = "EXP_CONVENTION_CODE_ENTITE_REQUIRED";

	/** Codes Exception de la class : PrestataireBR */
	public static final String EXP_CODE_DOMAINE_ACT_NOT_VALIDE = "EXP_CODE_DOMAINE_ACT_NOT_VALIDE";
	public static final String EXP_LIST_DOC_IDENTITY_REQUIRED = "EXP_LIST_DOC_IDENTITY_REQUIRED";
	public static final String EXP_LIST_TYPE_DOC_ISEMPTY = "EXP_LIST_TYPE_DOC_ISEMPTY";
	public static final String EXP_NOM_DOC_REQUIRED = "EXP_NOM_DOC_REQUIRED";
	public static final String EXP_LIST_DOC_IDENTITY_NOT_VALIDE = "EXP_LIST_DOC_IDENTITY_NOT_VALIDE";
	public static final String EXP_DOC_IDENTITY_REQUIRED = "EXP_DOC_IDENTITY_REQUIRED";
	public static final String EXP_FIND_SIMILAR_PRESTATAIRE = "EXP_FIND_SIMILAR_PRESTATAIRE";
	public static final String EXP_CODE_PRESTATAIRE_NOT_GENERATE = "EXP_CODE_PRESTATAIRE_NOT_GENERATE";
	public static final String EXP_RIB_MOYPAI_REQUIRED = "EXP_RIB_MOYPAI_REQUIRED";
	public static final String EXP_RIB_MOYPAI_NOT_VALIDE = "EXP_RIB_MOYPAI_NOT_VALIDE";
	public static final String EXP_LIST_MOYPAI_NOT_VALIDE = "EXP_LIST_MOYPAI_NOT_VALIDE";
	public static final String EXP_MANDATAIRE_NULL = "EXP_MANDATAIRE_NULL";

	/** Codes Exception de la class : ValidateMissionUC */
	public static final String EXP_ENTITE_CREATRICE_NOT_VALIDE = "EXP_ENTITE_CREATRICE_NOT_VALIDE";
	public static final String EXP_ENTITE_DESTINATRICE_NOT_VALIDE = "EXP_ENTITE_DESTINATRICE_NOT_VALIDE";
	public static final String EXP_LIST_REF_NOT_VALIDE = "EXP_LIST_REF_NOT_VALIDE";

	public static final String EXP_PRESTATAIRE_NULL = "EXP_PRESTATAIRE_NULL";
	public static final String EXP_MISSION_INCHANGE = "EXP_MISSION_INCHANGE";

	/** UC de création */
	public static final String EXP_REFERENCE_INNEXISTANTE = "EXP_REFERENCE_INNEXISTANTE";

	/** Code Exceptions de la class : MissionVOConverter */
	public static final String EXP_NUM_MISSION_RATTACHE_NOT_VALIDE = "EXP_NUM_MISSION_RATTACHE_NOT_VALIDE";
	public static final String EXP_CODE_NATURE_MISSION_NOT_VALIDE = "EXP_CODE_NATURE_MISSION_NOT_VALIDE";
	public static final String EXP_CODE_TYPE_DOSSIER_NOT_VALIDE = "EXP_CODE_TYPE_DOSSIER_NOT_VALIDE";
	public static final String EXP_CODE_BRANCHE_NOT_VALIDE = "EXP_CODE_BRANCHE_NOT_VALIDE";
	public static final String EXP_CODE_NAT_MISSION_REF_NOT_VALIDE = "EXP_CODE_NAT_MISSION_REF_NOT_VALIDE";
	public static final String EXP_TYPE_PIECE_JOINTE_NOT_VALIDE = "EXP_TYPE_PIECE_JOINTE_NOT_VALIDE";

	/** Changment d'état */
	public static final String EXP_ETAT_MISSION_REQUIRED = "EXP_ETAT_MISSION_REQUIRED";
	public static final String EXP_PLUSIEURS_ETATS_DESTINATION_POSSIBLES = "EXP_PLUSIEURS_ETATS_DESTINATION_POSSIBLES";
	public static final String EXP_AUCUN_ETAT_DESTINATION_NE_CORRESPOND_AU_CRITERE = "EXP_AUCUN_ETAT_DESTINATION_NE_CORRESPOND_AU_CRITERE";
	public static final String EXP_AUCUN_ETAT_N_EST_POSSIBLE = "EXP_AUCUN_ETAT_N_EST_POSSIBLE";
	public static final String EXP_MISSION_DEJA_ANNULE = "EXP_MISSION_DEJA_ANNULE";
	public static final String EXP_MISSION_DEJA_CLOTURE = "EXP_MISSION_DEJA_CLOTURE";
	public static final String EXP_ACTION_NON_AUTORISE = "EXP_ACTION_NON_AUTORISE";

	/** Code Exceptions de la class : ValidateMissionUCConverter */
	public static final String EXP_LIST_REFERENCE_REQUIRED = "EXP_LIST_REFERENCE_REQUIRED";
	public static final String EXP_VALEUR_REFERENCE_REQUIRED = "EXP_VALEUR_REFERENCE_REQUIRED";
	public static final String EXP_CODE_PRESTATAIRE_REQUIRED = "EXP_CODE_PRESTATAIRE_REQUIRED";
	public static final String EXP_CODE_TYPE_DOSSIER_REQUIRED = "EXP_CODE_TYPE_DOSSIER_REQUIRED";
	public static final String EXP_NUM_DOSSIER_REQUIRED = "EXP_NUM_DOSSIER_REQUIRED";
	public static final String EXP_CODE_BRANCHE_REQUIRED = "EXP_CODE_BRANCHE_REQUIRED";
	public static final String EXP_INSTRUCTIONS_REQUIRED = "EXP_INSTRUCTIONS_REQUIRED";
	public static final String EXP_CODE_GARANTIE_REQUIRED = "EXP_CODE_GARANTIE_REQUIRED";
	public static final String EXP_CODE_ENTITE_CREATRICE_REQUIRED = "EXP_CODE_ENTITE_CREATRICE_REQUIRED";
	public static final String EXP_CODE_ENTITE_DESTINATRICE_REQUIRED = "EXP_CODE_ENTITE_DESTINATRICE_REQUIRED";

	/** WorkFlow autorisation */
	public static final String EXP_NOT_ALLOWED_TO_EXECUTE_ACTION = "EXP_NOT_ALLOWED_TO_EXECUTE_ACTION";
	public static final String EXP_AUCUN_PROFILS_N_EST_ATTRIBUE_AU_USER = "EXP_AUCUN_PROFILS_N_EST_ATTRIBUE_AU_USER";

	/** CreateMissionUC */
	public static final String EXP_PRESTATAITE_NOT_VALIDE = "EXP_PRESTATAITE_NOT_VALIDE";

	/** AnnulerMissionUC */
	public static final String EXP_REPONSE_MISSION_DEJE_FOURNIE = "EXP_REPONSE_MISSION_DEJE_FOURNIE";
	public static final String EXP_EXISTE_MISSION_RATTACHEE = "EXP_EXISTE_MISSION_RATTACHEE";
	public static final String EXP_PROFIL_REQUIRED = "EXP_PROFIL_REQUIRED";
	public static final String EXP_PROFIL_NOT_AUTHORIZED = "EXP_PROFIL_NOT_AUTHORIZED";

	public static final String EXP_LIST_PRESTATION_REQUIRED = "EXP_LIST_PRESTATION_REQUIRED";
	/** AccepterMissionUC */
	public static final String EXP_MISSION_ACCEPTER_ETAT_NOT_FIND = "EXP_MISSION_ACCEPTER_ETAT_NOT_FIND";
	public static final String EXP_MISSION_DEJA_ACCEPTER = "EXP_MISSION_DEJA_ACCEPTER";

	/** Code Exceptions de la class : ValidateHonoraireUC */
	public static final String EXP_MISSION_INNEXISTANTE = "EXP_MISSION_INNEXISTANTE";
	public static final String EXP_MISSION_PRESTATAIRE_NON_AFFECTE = "EXP_MISSION_PRESTATAIRE_NON_AFFECTE";
	public static final String EXP_REPONSE_MISSION_REQUIRED = "EXP_REPONSE_MISSION_REQUIRED";
	public static final String EXP_MISSION_ACTIVITE_INNEXISTANTE = "EXP_MISSION_ACTIVITE_INNEXISTANTE";
	public static final String EXP_SEUIL_VALIDATION_INNEXSITANT = "EXP_SEUIL_VALIDATION_INNEXSITANT";
	public static final String EXP_SEUIL_VALIDATION_DEPASSER = "EXP_SEUIL_VALIDATION_DEPASSER";
	public static final String EXP_ETAT_CIBLE_NON_VALIDE = "EXP_ETAT_CIBLE_NON_VALIDE";

	/** #### ReglementHonoraireBS */
	/** Generation Quittance Exceptions */
	public static final String EXP_QUITTANCE_EMISSION = "Un problème est survenue los de l'émission de la quittance.";
	public static final String EXP_NUM_QUITTANCE_NOT_EXIST = "EXP_NUM_QUITTANCE_NOT_EXIST";
	public static final String EXP_FISCALITE_EMISSION = "EXP_FISCALITE_EMISSION";
	public static final String EXP_ORDONNANCEMENT_CREATION = "EXP_ORDONNANCEMENT_CREATION";
	/** Pager */
	public static final String EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE = "EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE";
	public static final String EXP_PAGER_PAGE_SIZE_OBLIGATOIRE = "EXP_PAGER_PAGE_SIZE_OBLIGATOIRE";
	public static final String EXP_MAX_RESULT = "Le nombre de résultat est important. Veuillez affiner votre recherche.";

	/** ModifierMissionUCConverter */
	public static final String EXP_COUPLE_BRANCHE_NUMDOSSIER_NOTVALIDE = "EXP_COUPLE_BRANCHE_NUMDOSSIER_NOTVALIDE";
	public static final String EXP_UPDATE_MISSION_FAILED = "EXP_UPDATE_MISSION_FAILED";

	/** ConsulterHistoriqueMissionUC */
	public static final String EXP_PARMS_INPUT_REQUIRED = "EXP_PARMS_INPUT_REQUIRED";

	/** ValiderReponseUC */
	public static final String EXP_MISSION_NON_RATACHEE = "EXP_MISSION_NON_RATACHEE";
	public static final String EXP_REPONSE_TEXT_REQUIRED = "EXP_REPONSE_TEXT_REQUIRED";
	public static final String EXP_CHAMP_MONTANT_REQUIRED = "EXP_CHAMP_MONTANT_REQUIRED";
	public static final String EXP_CHAMP_MONTANT_NOTVALIDE = "EXP_CHAMP_MONTANT_NOTVALIDE";
	/** Mail */
	public static final String EXP_MAIL_DESTINATAIRES_REQUIRED = "EXP_MAIL_DESTINATAIRES_REQUIRED";

	/** #### ValidateTools messages */
	public static final String EXP_TECHNIQUE_PROBLEME = "EXP_TECHNIQUE_PROBLEME";
	public static final String EXP_CHAMP_REQUIRED = "Champ obligatoire: ";
	public static final String EXP_CHAMP_AUTO_GENERATE = "EXP_CHAMP_AUTO_GENERATE";
	public static final String EXP_LIST_OBJECT_REQUIRED = "EXP_LIST_OBJECT_REQUIRED";
	public static final String EXP_CHAMP_LENGTH_EQAL_NOTVALIDE = "EXP_CHAMP_LENGTH_EQAL_NOTVALIDE";
	public static final String EXP_CHAMP_LENGTH_INF_NOTVALIDE = "EXP_CHAMP_LENGTH_INF_NOTVALIDE";
	public static final String EXP_CHAMP_LENGTH_SUP_NOTVALIDE = "EXP_CHAMP_LENGTH_SUP_NOTVALIDE";
	public static final String EXP_CHAMP_TYPE_NOTVALIDE = "EXP_CHAMP_TYPE_NOTVALIDE";
	public static final String EXP_CHAMP_TYPE_DATE_NOTVALIDE = "EXP_CHAMP_TYPE_DATE_NOTVALIDE";

	/** OrgServicesTools */
	public static final String EXP_ORG_SERVICE_INDISPONIBLE = "EXP_ORG_SERVICE_INDISPONIBLE";

	/** SasServicesTools */
	public static final String EXP_ASAL_SERVICE_INDISPONIBLE = "EXP_ASAL_SERVICE_INDISPONIBLE";
	public static final String EXP_SAS_SERVICE_INDISPONIBLE = "EXP_SAS_SERVICE_INDISPONIBLE";
	public static final String USER_NOT_FOUND_IN_SAS = "USER_NOT_FOUND_IN_SAS";
	/** #### Globale messages */
	public static final String EXP_MISSION_REQUIRED = "EXP_MISSION_REQUIRED";
	public static final String EXP_NUMERO_MISSION_NOT_VALIDE = "EXP_NUMERO_MISSION_NOT_VALIDE";
	public static final String EXP_MISSION_NOT_FIND = "EXP_MISSION_NOT_FIND";
	public static final String EXP_REPONSE_REQUIRED = "EXP_REPONSE_REQUIRED";
	public static final String EXP_TYPE_ECHANGE_REQUIRED = "EXP_TYPE_ECHANGE_REQUIRED";
	public static final String EXP_USER_REQUIRED = "EXP_USER_REQUIRED";
	public static final String EXP_CODE_USER_REQUIRED = "EXP_CODE_USER_REQUIRED";
	public static final String EXP_ENT_DEST_NOT_NULL_AFFECTATION = "EXP_ENT_DEST_NOT_NULL_AFFECTATION";
	public static final String EXP_CODE_USER_EXISTE = "EXP_CODE_USER_EXISTE";
	public static final String EXP_CODE_USER_NOT_EXISTE = "EXP_CODE_USER_NOT_EXISTE";
	public static final String EXP_LOGIN_EXISTE = "EXP_LOGIN_EXISTE";
	public static final String EXP_LOGIN_CODEUSER_DIFF = "EXP_LOGIN_CODEUSER_DIFF";
	/** #### MoyenPaiement service RMI */
	public static final String EXP_SERVICE_MOYEN_PAIE_INDISPONIBLE = "EXP_SERVICE_MOYEN_PAIE_INDISPONIBLE";

	/** #### ReaffectationPrestataireUC */
	public static final String MISSION_PAS_UNE_MISSION_RATTACHE = "MISSION_PAS_UNE_MISSION_RATTACHE";
	public static final String PRESTATAIRE_ETAIT_DEJA_AFFECTE = "PRESTATAIRE_ETAIT_DEJA_AFFECTE";
	public static final String NUMERO_MISSION_OBLIGATOIRE = "NUMERO_MISSION_OBLIGATOIRE";
	public static final String PRESTATAIRE_AFFECTE_OBLIGATOIRE = "PRESTATAIRE_AFFECTE_OBLIGATOIRE";

	/** #### Chercher Mission Converter */
	public static final String NATURE_MISSION_INVALIDLE = "NATURE_MISSION_INVALIDLE";
	public static final String REFERENCE_NON_RENSEIGNE = "REFERENCE_NON_RENSEIGNE";
	public static final String CODE_ETAT_MISSION_INVALIDE = "CODE_ETAT_MISSION_INVALIDE";

	/** #### reglement */
	public static final String PRESTATAIRE_NOT_EXISTE = "PRESTATAIRE_NOT_EXISTE";
	public static final String PRESTATAIRE_SANS_CONVENTION = "PRESTATAIRE_SANS_CONVENTION";
	public static final String TARIF_NON_RENSEIGNE = "TARIF_NON_RENSEIGNE";
	public static final String MONTANT_CREANCE_NOT_VALIDE = "MONTANT_CREANCE_NOT_VALIDE";
	public static final String MONTANT_CREANCE_NOT_IN_INTERVALLE = "MONTANT_CREANCE_NOT_IN_INTERVALLE";
	public static final String EXP_COMMENTAIRE_REQUIRED = "EXP_COMMENTAIRE_REQUIRED";
	public static final String EXP_MOTIF_ANNULATION_REQUIRED = "EXP_MOTIF_ANNULATION_REQUIRED";
	public static final String EXP_RECHERCHE_ORDONNANCEMENT = "Erreur recherche ordonnancement service.";
	public static final String EXP_SOUMETTRE_ORDONNANCEMENT = "Erreur soumission ordonnancement service.";
	public static final String EXP_NOMBRE_RECOURS_ENCOURS = "Plusieurs recours en cours existants";
	public static final String RECOURS_ENCOURS_INEXISTANT = "Une erreur est survenue lors de la récupération du recours en cours";
	public static final String EXP_DATEREG_BEFORE_DATEJOUR = "Date du réglement chéque doit être inférieur ou égale a la date du jour";
	public static final String EXP_DATEETAB_BEFORE_DATEJOUR = "Date d'établissement du chéque doit être inférieur ou égale a la date du jour";
	public static final String EXP_DATEREG_BEFORE_DATEETAB = "Date d'établissement chéque doit être inférieur ou égale a la date du réglement du chéque ";
	public static final String EXP_DATEACC_BEFORE_DATENOTE = "La date note du réglement auxiliaire doit être supérieure ou égale à la date d'accident ";
	/** #### RechercheAutomatiquePrestataireUC */
	public static final String AUCUN_PRESTATAIRE_NE_REPOND_AU_CRITERE = "AUCUN_PRESTATAIRE_NE_REPOND_AU_CRITERE";

	/** #### ChercherMissionUCConverter */
	public static final String NUMERO_MISSION_RATTACHE_INVALIDE = "NUMERO_MISSION_RATTACHE_INVALIDE";

	/** #### GenAutoQuittanceForfaitUC */
	public static final String QUITTANCE_FORFAIT_NON_GENERE_PAR_SERVICE_EXTERNE = "QUITTANCE_FORFAIT_NON_GENERE_PAR_SERVICE_EXTERNE";
	public static final String QUITTANCE_FORFAIT_NON_GENERE_PAR_INTERNE = "QUITTANCE_FORFAIT_NON_GENERE_PAR_INTERNE";
	public static final String EXCEPTION_TECHNIQUE = "EXCEPTION_TECHNIQUE";
	public static final String NUMERO_QUITTANCE_PAS_RECUPERER = "NUMERO_QUITTANCE_PAS_RECUPERER";
	public static final String MONTANT_FORFAIT_NULL = "MONTANT_FORFAIT_NULL";

	/** Codes Exception de la class : ReponseMissionBR */
	public static final String IMPOSSIBLE_AJOUTER_REPONSE_REFERENCE_NULL = "IMPOSSIBLE_AJOUTER_REPONSE_REFERENCE_NULL";
	public static final String REPONSE_MISSION_OBLIGATOIRE = "REPONSE_MISSION_OBLIGATOIRE";
	public static final String MONTANT_FACTURE_OBLIGATOIRE = "MONTANT_FACTURE_OBLIGATOIRE";
	public static final String MISSION_A_MISSIONS_RATTCHE_SANS_REPONSE = "MISSION_A_MISSIONS_RATTCHE_SANS_REPONSE";

	/** Codes Exception de la class : AuthentificationUserUC */
	public static final String LOGIN_PASSWORD_INVALIDE = "AUTHENIFICATION.FAILURE.LOGIN.PWD";
	public static final String AUTHENIFICATION_REQUIRED_LOGIN_PWD = "AUTHENIFICATION.REQUIRED.LOGIN.PWD";

	public static final String EXP_GARANTIE_OBLIGATOIRE = "Garantie obligatoire";
	public static final String EXP_POLICE_OBLIGATOIRE = "Numéro police obligatoire";
	public static final String EXP_NOM_OBLIGATOIRE = "Nom obligatoire";
	public static final String EXP_DATE_DECLARATION = "Date déclaration obligatoire.";
	public static final String EXP_DATE_DECES_OBLIGATOIRE = "Date décés obligatoire";
	public static final String EXP_AGE_MAXIMUM_65 = "L'age maximum est 65 ans";
	public static final String EXP_AGE_MINIMUM_12 = "L'age minimum est 12 ans";
	public static final String EXP_DATENAIS_BEFORE_DATEDEC = "Date naissance avant la date décés";
	public static final String EXP_DATEACC_BEFORE_DATEDEC = "Date accident avant date décés";
	public static final String EXP_DATEEMB_BEFORE_DATEDEC = "Date embauche avant date décés";
	public static final String EXP_DATENAIS_BEFORE_DATEACC = "date naissance avant date accident";
	public static final String EXP_DATEEMB_BEFORE_DATEACC = "date embauche avant date accident";
	public static final String EXP_DATENAIS_BEFORE_DATEEMB = "Date naissance avant date embauche";
	public static final String EXP_DATEEMB_BEFORE_DATESYS = "Date embauche avant date du jour";
	public static final String EXP_DATENAIS_BEFORE_DATESYS = "Date naissance avant date du jour";
	public static final String EXP_DATEACC_BEFORE_DATESYS = "Date accident avant date du jour";
	public static final String EXP_DATEDEC_BEFORE_DATESYS = "Date décés avant date du jour";
	public static final String EXP_IPP_SUPERIEUR_100 = "Taux ipp supérieur à 100";
	public static final String EXP_ETAT_CIBLE_OBLIGATOIRE = "Etat cible obligatoire";
	public static final String EXP_MOTIF_OBLIGATOIRE = "Motif obligatoire";
	public static final String EXP_DATE_ACCIDENT = "Date accident obligatoire.";
	/** Exception sinistre */
	public static final String EXP_RECHERCHE_SINISTRE = "Une erreur est survenue lors de la récupération du sinistre.";
	public static final String EXP_RECHERCHE_QTC_ITT = "Une erreur est survenue lors de la récupération de la liste";
	public static final String EXP_CREATION_SINISTRE = "Une erreur est survenue lors de la création du sinistre.";
	public static final String EXP_SUPPRESSION_AYANT_DROIT = "Une erreur est survenue lors de la suppression de l 'ayant Droit  .";
	public static final String EXP_SINISTRE_INEXISTANT = "Ce sinistre est inexistant.";
	public static final String EXP_NUM_SIN_REQUIRED = "Numero de sinistre obligatoire.";
	public static final String EXP_NUM_SIN_EXISTANT = "Ce numero de sinistre existe déjà, vous ne pouvez pas le recréer";
	public static final String EXP_MODIF_SINISTRE = "Une erreur est survenue lors de la modification du sinistre.";
	public static final String EXP_AY_NOT_EXIST = "Cet ayant droit est inexistant.";
	public static final String EXP_NUM_EVEN_REQUIRED = "Numero d'événement obligatoire.";
	public static final String EXP_CREER_MOUVEMENT = "Une erreur est survenue lors de la création du mouvement.";
	public static final String EXP_CALCUL_RESERVE = "Une erreur est survenue lors du calcul de la résèrve.";
	public static final String EXP_CALCUL_CUMUL_REG = "Une erreur est survenue lors du calcul du cumul des réglements.";
	public static final String EXP_CALCUL_CUMUL_RESERVE = "Une erreur est survenue lors du calcul du cumul des résèrves.";
	public static final String EXP_ETAT_REQUIRED = "Létat est obligatoire.";
	public static final String CHANGEMENT_ETAT_NON_AUTHORISE = "Ce changement d'état n'est pas authorisé.";
	public static final String EXP_MODIF_ETAT = "Une erreur est survenue lors du changement d'état du sinistre.";
	public static final String EXP_RESERVES_GRAVE_PRO_NON_ZERO = "La valeur des résèrves grave et prothèses doit être égale à 0.";
	public static final String EXP_RESERVE_ORD_CERO = "La valeur de la résèrve ordinaire ne peut être égale à 0.";
	public static final String EXP_RESERVES_NON_ZERO = "La valeur des résèrves doit être égale à 0.";
	public static final String EXP_RESERVE_NON_ZERO_REQUIRED = "La valeur D'au moins une des résèrves doit être égale différente de 0.";
	public static final String EXP_MODIF_ETAT_VERIF_RESERVES = "Une erreur est survenue lors de la modification de l'état du sinistre: impossible de vérifier les résèrves.";
	public static final String EXP_MODIF_SIN_REG_RES = "Une erreur est survenue lors de la création du réglement: Reglement non authorisé, la reserve ordinaire est égale à 0.";
	public static final String EXP_MODIF_ETAT_SIN_REG = "Une erreur est survenue lors de la création du réglement: Reglement non authorisé, état du sinistre incompatible.";
	public static final String EXP_MODIF_SIN_REG = "Une erreur est survenue lors de la création du réglement: Echec modification sinistre.";
	public static final String EXP_MODIF_LIST_ETAT_SIN = "Une erreur est survenue lors de la validation des sinistres.";
	public static final String EXP_SIN_NULL = "L'objet sinistre en entrée ne peut être null.";
	public static final String EXP_AD_NULL = "L'objet AD en entrée ne peut être null.";
	public static final String EXP_MAJ_SINISTRE = "Une erreur est survenue lors de la mise a jour du sinistre.";
	public static final String EXP_NUMEVEN_EXISTE = "Cet évenement n'existe pas";
	/** Exception transaction */
	public static final String EXP_CREATE_TRANSACTION = "Une erreur est survenue lors de la création de la transaction.";

	/** Exception Reglement */
	public static final String EXP_RECHERCHE_REGLEMENT = "Une erreur est survenue lors de la recherche des réglements.";
	public static final String EXP_VALIDATION_REGLEMENTS = "Une erreur est survenue lors de la validation des réglements.";
	public static final String EXP_VALIDATION_REGLEMENT = "Une erreur est survenue lors de la validation du réglement.";
	public static final String EXP_GENERATION_QUITTANCE = "Une erreur est survenue lors de la génération de la quittance";
	public static final String EXP_RECUPERATION_CODEINTERMEDIAIRE = "Une erreur est survenue lors de la recupérartion du code intermédiaire.";
	public static final String EXP_NUM_GENERATION_QUITTANCE = "Une erreur est survenue lors de la génération du numero de quittance.";
	public static final String EXP_CREATION_REGLEMENT = "Une erreur est survenue lors de la création du réglement.";
	public static final String EXP_CREATION_ORD = "Une erreur est survenue lors de la création de l'ordonnancement.";
	public static final String EXP_GENERATION_ORD = "Une erreur est survenue lors de la génération de l'ordonnancement.";
	public static final String EXP_CREATION_RECUP = "Une erreur est survenue lors de la création de la récupération.";
	public static final String EXP_RECHERCHE_RECUP = "Une erreur est survenue lors de la recherche récupération.";
	public static final String EXP_SINISTRE_NULL = "Le sinistre en référence ne peut être null.";
	public static final String EXP_POLICE_UNIVERSELLE = "Impossible de valider un reglement d'une POLICE UNIVERSELLE";
	public static final String EXP_REGLEMENT_INEXISTANT = "Règlement inexistant.";
    public static final String EXP_MODIFICATION_REGLEMENT = "Une erreur est survenue lors de la modification du règlement.";
    public static final String EXP_ANNULATION_REGLEMENT = "Une erreur est survenue lors de l'annulation du règlement.";
    public static final String EXP_NUM_QUITTANCE = "Une erreur est survenue lors de la récupération du numéro de quittance.";
    public static final String EXP_MAJ_REGLEMENT = "Une erreur est survenue lors de la mise a jour du règlement.";
    public static final String EXP_PARSE_DATE = "une erreur du parse Date.";
    public static final String EXP_UPDATE_REGLEMENT = "Une erreur est survenue lors de la mise a jour BD du règlement.";
    public static final String EXP_DATE_REGLEMENT = "La date du réglement doit être inférieur ou égale a la date du jour.";
    public static final String EXP_DATE_ETABLISSEMENT = "La date du réglement doit être inférieur ou égale a la date d'établissement.";
    public static final String EXP_CREATION_LETTRE_REGLEMENT = "Une erreur est survenue lors de la création de la lettre règlement.";
    public static final String EXP_VALIDATION_PRE_QUITTANCE = "Une erreur est survenue lors de la validation de la pré-quittance.";
    public static final String EXP_VALIDATION_QUITTANCE_ITT = "Une erreur est survenue lors de la validation de la quittance ITT.";
    public static final String EXP_DATE_DEBUT_REGLEMENT = "La date début ne doit pas être incluse dans une période déjà réglée.";
    
	/** Exception Date dernière écheance règlée */
	public static final String EXP_DATE_REGELEMENT_NULL = "La date du dernier réglement est null.";

	/** Exception standards */
	public static final String EXP_RECHERCHE = "Une erreur est survenue lors de la recherche.";
	public static final String EXP_OBJECT_ENTREE = "L'object en entréee ne peut pas être null.";
	public static final String EXP_OBJECT_SORTIE = "L'objet retourné est null.";
	public static final String EXP_STAND_MESS = "Une erreur est survenue lors de l'opération.";
	public static final String EXP_DATE_FORMAT = "Format de date incorrect.";
	public static final String EXP_SQL = "Exception sql.";
	public static final String EXP_NUM_DOSSIER_NOT_VALIDE = "Le numero de dossier n'est pas valide.";
	public static final String EXP_RECUP_UTILISATEUR = "Une erreur est survenue lors de la récuperation de l'utilisateur: ";
	/** Exception Certificat */

	public static final String EXP_CREATION_CERTIFICAT = "Une erreur est survenue lors de la création du certificat.";
	public static final String EXP_RECHERCHE_CERTIFICAT = "Une erreur est survenue lors de la recherche certificats.";
	public static final String EXP_MODIF_CERTIFICAT = "Une erreur est survenue lors de la modification du certificat.";
	public static final String EXP_CERTIFICAT_NOT_VALIDE = "Une erreur est survenue. Certificat non valide.";
	public static final String EXP_CERTIFICAT_INEXISTANT = "Une erreur est survenue. Certificat inexistant.";
	public static final String EXP_SUPPRIMER_CERTIFICAT = "Une erreur est survenue lors de la suppression du certificat.";
	public static final String EXP_INVALIDATION_CERTIFICAT = "Une erreur est survenue lors de l'invalidation des certificats.";
	/** Exception mission */
	public static final String EXP_CREATION_MISSION = "Une erreur est survenue lors de la création de la mission.";
	public static final String EXP_MISSION_SERVICE = "Une erreur est survenue lors de l'appel du service missionnement";
	public static final String EXP_RECHERCHE_MISSION = "Une erreur est survenue lors de la recherche missions.";
	public static final String EXP_MODIF_MISSION = "Une erreur est survenue lors de la modification de la mission.";
	public static final String EXP_MISSION_NOT_VALIDE = "Une erreur est survenue. Mission non valide.";
	public static final String EXP_MISSION_INEXISTANT = "Une erreur est survenue. Mission inexistante.";
	/** Exception Recours */

	public static final String MSG_CREATION_RECOURS_ENCOURS = "Impossible de créer un recours.Un autre recours est déjà en cours";
	public static final String EXP_CREATION_RECOURS = "Une erreur est survenue lors de la création du recours.";
	public static final String EXP_RECOURS_SERVICE = "Une erreur est survenue lors de l'appel du service recours.";
	public static final String EXP_RECHERCHE_RECOURS = "Une erreur est survenue lors de la recherche recours.";
	public static final String EXP_MODIF_RECOURS = "Une erreur est survenue lors de la modification du recours.";
	public static final String EXP_RECOURS_NOT_VALIDE = "Une erreur est survenue. Recours non valide.";
	public static final String EXP_RECOURS_INEXISTANT = "Une erreur est survenue. Recours inexistant.";
	public static final String EXP_CREATION_PROCEDURE = "Une erreur est survenue lors de la création de la procédure judiciaire.";
	public static final String EXP_RECHERCHE_PROCEDURE = "Une erreur est survenue lors de la recherche des procédures.";
	public static final String EXP_MODIF_PROCEDURE = "Une erreur est survenue lors de la modification de la procédure.";
	public static final String EXP_PROCEDURE_INEXISTANTE = "Une erreur est survenue. Procédure judiciaire inexistante.";
	public static final String EXP_CREATION_AUDIENCE = "Une erreur est survenue lors de la création de l'audience.";
	public static final String EXP_MODIF_AUDIENCE = "Une erreur est survenue lors de la modification de l'audience.";
	public static final String EXP_RECHERCHE_AUDIENCE = "Une erreur est survenue lors de la recherche des audiences.";
	public static final String EXP_EXPORT_RECOURS = "Une erreur est survenue lors de l'export des recours.";
	public static final String EXPF_AUCUN_AUDIENCE_TROUVE = "Aucun Audience trouvé!";
	
	public static final String EXP_MONTANT_RENTE_NULL = "Le montant de la rente ne peut être null";

	public static final String EXP_COEFICIENT_AGE_NULL = "Le coefficient d'âge ne peut être null";
	
	public static final String EXP_COEFICIENT_AGE_INTROUVABLE = "Le coefficient d'âge est introuvable";
	
	public static final String EXP_DATE_NAISSANCE_NULL= "La date de naissance ne peut être null ou vide";
	public static final String EXP_PROCEDURE_JUDICIAIRE_EXISTANTE= "Une procédure judiciaire avec le même Numéro Dossier Tribunal et le même Code Trubunal est déjà créée.";
	public static final String EXP_AUDIANCE_EXISTANTE= "Une Audiance avec la même date Audiance et la même date Notification est déjà créée.";
	/** Exception synchronisation interface*/
	public static final String EXP_CODEMVT = "Une erreur est survenue lors de la récupération du Code Mouvement : CodMvt.";
	public static final String EXP_DATEMVT = "Une erreur est survenue lors de la récupération de la Date Mouvement : DatMvt.";
	public static final String EXP_VICTIME = "Une erreur est survenue lors de la récupération des informations de la Victime";
	public static final String EXP_EVENEMENT = "Une erreur est survenue lors de la récupération des informations de l'Evénement";
	public static final String EXP_VILLE ="Une erreur est survenue lors de la récupération des informations de la Ville";
	public static final String EXP_ETAT ="Une erreur est survenue lors de la récupération de l'Etat di Sinistre";
	
	/** Conciliation*/ 
	public static final String EXP_DATE_CREATION_SINISTRE_INFERIEUR_22012015 = "Création Conciliation imposible : la date survenance sinistre est inférieur au 22/01/2015.";
	public static final String CONCILIATION_ENCOURS_EXISTE = "Une Conciliation à l'état en cours existe au niveau du dossier sinistre.";
	public static final String CONCILIATION_EXISTE = "Une Conciliation existe déjà au niveau du dossier sinistre.";
	public static final String AUCUN_IPP_CERTIFICAT_VALIDE = "Aucun certificat de contre visite ou de guérison n'est disponible.";
	public static final String OFFRE_CREER_EN_COURS = "Une Offre à l'état Crée existe déjà";
	public static final String OFFRE_VALIDE_EXISTE = "Une Offre à l'état Validée avec resultat Accepter existe déjà";
	//Evol lot1 control conciliation
	public static final String CONCILIATION_EXISTE_AUCUNE_OFFRE = "Une Conciliation existe déjà au niveau du dossier sinistre/Aucune offre établie";
    public static final String CONCILIATION_EXISTE_UNE_OFFRE_ETABLIE = "Une Conciliation existe déjà au niveau du dossier sinistre/Une offre établie";
    public static final String CONCILIATION_EXISTE_UNE_OFFRE_REFUSEE = "Une Conciliation existe déjà au niveau du dossier sinistre/Une offre refusée";
    public static final String CONCILIATION_EXISTE_UNE_OFFRE_ACCEPTEE = "Une Conciliation existe déjà au niveau du dossier sinistre/Une offre acceptée";

	/** Instruction **/
	public static final String EXP_INSTRUCTION_ANNULATION = "Une erreur est survenue lors de l'annulation de l'instruction.";
	public static final String EXP_INSTRUCTION_EDITION = "Une erreur est survenue lors de l'édition de l'instruction.";
	public static final String EXP_INSTRUCTION_INEXISTANTE = "Cette instruction est inexistante.";
	public static final String EXP_PROC_JUD_INEXISTANTE = "Cette procédure judiciaire est inexistante.";
	public static final String EXP_SUPPRESSION_QUITTANCE_ITT = "Une erreur est survenue lors de la suppression du Quittance ITT.";
	
	public static final String EXP_LETTRE_QUITTANCE_EDITION = "Une erreur est survenue lors de l'édition de l'instruction.";

	public static final String EXP_RECOURS_EN_COURS = "Une procédure judiciaire est en cours pour ce dossier";

	/** BNEJ **/
	public static final String EXP_INIT_BNEJ = "Une erreur est survenue lors de l'attribution du numéro de lot.";
	public static final String EXP_SEARCH_BNEJ = "Merci de saisir le numéro du Lot.";
	public static final String EXP_NORESULT_BNEJ = "Aucun résultat trouvé.";
	public static final String EXP_DOSSIER_BNEJ_NULL = "L'objet dossier bnej en entrée ne peut être null.";
	public static final String EXP_LOT_BNEJ_NULL = "L'objet lot bnej en entrée ne peut être null.";
	
	
	/***Courier LRH**/
	public static final String EXPF_AUCUN_QUITTANCE_TROUVE = "Aucun Quittance trouvé!";
	public static final String EXPF_AUCUN_OPPOSITION_TROUVE = "Aucun courrier Opposition trouvé!";
	public static final String EXPF_AUCUN_RECLAMATION_TROUVE = "Aucun courrier Reclamation trouvé!";
	public static final String EXPF_AUCUN_COURRIER_TROUVE = "Aucun courrier trouvé!";

}
