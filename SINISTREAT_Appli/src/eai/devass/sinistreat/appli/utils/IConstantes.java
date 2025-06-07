package eai.devass.sinistreat.appli.utils;

import java.math.BigDecimal;

public interface IConstantes {

	/***/
	public final static String REPONSE_MISSION = "ReponseMission";
	public final static String MISSION = "Mission";
	public final static String CONSTANTE_USER_MODIFICATEUR="CONSTANTE_USER_MODIFICATEUR";
	public static final String REPORT_OBJECT = "objetReport";
	public static final String REPORT_PATH = "C:\\DATA_PROJECT_MISSION\\MISSIONNEMENTPRESTATAIRE_DEV\\MISSIONNEMENTPRESTATAIRE_EDITION";

	public final static String PATH_REPORT_AP = "/edition/LettreInstruction.jasper";
	public final static String PATH_EDITION = "/edition";

	public final static String EDITION1 = "edition1";
	public final static String EDITION2 = "edition2";

	public final static String TYPE_CERTIFICAT_CONSTATATION = "1";
	public final static String TYPE_CERTIFICAT_GUERISON = "2";
	public final static String TYPE_CERTIFICAT_AGGRAVATION = "3";
	public final static String TYPE_CERTIFICAT_RECHUTE = "4";
	public final static String TYPE_CERTIFICAT_CONTREVISITE = "5";
	public final static String TYPE_CERTIFICAT_EXPERTISEJUDICIAIRE = "6";
	public final static String TYPE_CERTIFICAT_EXPERTISECONSEIL = "7";

	public final static String MOTIFCREATION = "Creation du certificat.";
	public final static String MOTIFMODIFICATION = "Modification du certificat.";
	public final static String MOTIFVALIDATION = "Validation du certificat.";
	public final static String MOTIFINVALIDATION = "Invalidation du certificat.";
	public final static String MODIF_PROCEDURE_JUDIDCIAIRE = "Modification Procédure Judiciaire";
	public final static String ETAT_SINISTRE_JUSTICE = "2";
	public final static int AGE_MIN = 12;
	public final static int AGE_MAX = 65;
	public final static String SINISTRE_EXAAA = "3";
	public final static String RUBRIQUE_RACHAT = "22";
	
	
	public final static String RUBRIQUE_ARRERAGE_AVANT_CONSTITUTION = "21";
	public final static String RUBRIQUE_ITT = "20";
	public final static String RUBRIQUE_FRAIS_MEDICAUX = "24";
	public final static String RUBRIQUE_FRAIS_FUNERAIRE = "27";
	

	
	/** Règlement */
	public final static String TYPE_REGLEMENT_DIRECT = "1";
	public final static String TYPE_REGLEMENT_AUXILIAIRE = "2";
	public final static String TYPE_REGLEMENT_INTERMEDIAIRE = "3";
	public final static String TYPE_REGLEMENT_BGD = "6";
	public final static String TYPE_REGLEMENT_RECUPERATION = "4";
	public final static String ETAT_REGLEMENT_EN_INSTANCE_DE_VALIDATION = "1";
	public final static String ETAT_REGLEMENT_EN_INSTANCE_DE_REGLEMENT = "2";
	public final static String ETAT_REGLEMENT_EN_INSTANCE_VALIDATION_SUPERIEURE = "6";
	public final static String ETAT_REGLEMENT_EN_INSTANCE_POSITIONNEMENT = "7";
	public final static String ETAT_REGLEMENT_REGLE = "10";
	public final static String ETAT_REGLEMENT_SUPPRIME = "4";
	public final static String ETAT_REGLEMENT_QTC_ANNULEE = "3";
    public final static BigDecimal REGLEMENT_HIERARCHIQUE = BigDecimal
            .valueOf(5);
	public final static String NATURE_DEBOURS_LOI = "1";
	public final static String NATURE_TROP_PERCU = "2";
	public final static String NATURE_ANNULATION = "3";
	
	public final static String ETAT_PRE_QUITTANCE_EN_INSTANCE = "8";
	public final static String ETAT_PRE_QUITTANCE_VALIDEE= "9";
	public final static String ETAT_PRE_QUITTANCE_EMISSION_BATCH_ITT= "11";

	public final static String TYPE_RECOURS_SORTANT = "2";
	public final static String TYPE_RECOURS_JUDICIAIRE = "3";
	public final static String TYPE_RECOURS_AMIABLE = "1";
	public final static String TYPE_RECOURS_SANS_VOIE_ENTAMEE = "4";

	public final static String MODE_REGLEMENT_CHEQUE = "1";
	public final static String MODE_REGLEMENT_COMPENSATION = "2";
	public final static String MODE_REGLEMENT_CHEQUEBGD = "3";
	public final static String MODE_REGLEMENT_CHEQUEPRIME = "4";

	public final static String CODE_RESERVE_GRAVE = "1";
	public final static String CODE_RESERVE_ORDINAIRE = "2";
	public final static String CODE_RESERVE_PROTHESE = "3";

	public final static String BENEFICIAIRE_VICTIME = "1";
	public final static String BENEFICIAIRE_AYANT_DROIT = "2";
	public final static String BENEFICIAIRE_AVOCAT = "3";
	public final static String BENEFICIAIRE_BARREAU = "4";
	public final static String BENEFICIAIRE_BNEJ = "5";
	public final static String BENEFICIAIRE_CHEF_GREFFIER = "6";
	public final static String BENEFICIAIRE_INTERMEDIAIRE = "7";
	public final static String BENEFICIAIRE_CNRA = "8";
	public final static String BENEFICIAIRE_ASSURE = "9";
	public final static String BENEFICIAIRE_AUTRE = "10";
	public final static String BENEFICIAIRE_TUTEUR = "11";
	public final static String BENEFICIAIRE_AUXILIAIRE = "12";

	public static final String BRANCHE_AT = "3";
	public static final String JURIDICTION_PREMIERE_INSTANCE = "1";
	public static final String JURIDICTION_COURS_APPEL = "2";
	public static final String JURIDICTION_COURS_SUPREME = "3";
	public static final String JUGEMENT_DE_FOND_PREMIERE_INSTANCE = "3";
	public static final String JUGEMENT_ARRET_ADD = "4";
	public static final String JUGEMENT_ARRET_DE_FOND = "5";
	public static final String JUGEMENT_COUR_SUPREME = "6";
	public static final String ETAT_REOUVERT = "4";
	public static final String CODE_POINT_VENTE_RMA = "00054/02282/";
	/** Motif Mouvement*/

	public static final long MOTIF_CREATION_SINISTRE = 1;
	public static final long MOTIF_SINISTRE_MIGRER = 2;  
	public static final long MOTIF_AUDIENCE = 3;  
	
	public static final long MOTIF_SUPPRESSION_AUDIENCE=13;
	public static final long MOTIF_REGLEMENT = 4;
	public static final long MOTIF_PREQUITTANCE =24;
	public static final long MOTIF_ANNULATION_REGLEMENT_AVANT_VALIDATION = 5;   
	public static final long MOTIF_GSR_CHANGEMENT_RESERVEGRAVE =6;
	public static final long MOTIF_GSR_CHANGEMENT_CULULEREGLEMENT =14;
	public static final long MOTIF_CHANGEMENT_RESERVEGRAVE_CETIFICAT = 7;
	public static final long MOTIF_MODIFICATION_SINISTRE = 8;
	public static final long MOTIF_MODIFICATION_CIN_VICTIME = 39;
	public static final long MOTIF_MODIFICATION_NOMPRENOM_VICTIME = 37;
	public static final long MOTIF_MODIFICATION_SALAIREJOURNALIER_VICTIME = 38;
	public static final long MOTIF_MODIFICATION_ETAT_SINISTRE=10;
	public static final long MOTIF_MOUVEMENT_SIN_REG_SUPP = 9;
	//WMOS :add Motif "Changement de la réserve prothèse"
	public static final long MOTIF_GSR_CHANGEMENT_RESERVEPROTHESE = 11;
	public static final long MOTIF_CREATION_RENTE_GSR = 12;
	
	/**Motif Etat Reglement*/
	public static final String MOTIF_CREATION_REGLEMENT ="Création Réglement";
	public static final String MOTIF_VALIDATION_REGLEMENT = "Validation Réglement";
	public static final String MOTIF_ANNULATION_REGLEMENT = "Annulation Réglement";
	public static final String MOTIF_COMPLEMENT_REGLEMENT = "Complement Réglement Zone a risque";
	public static final String MOTIF_NEPASREGLER_REGLEMENT = "Ne Pas Regler Réglement Zone a risque";
	public static final String MOTIF_REGLER_PREQUITTANCE = "Regler Prequittance Zone a risque";

	
	// 
	public static final String TYPE_QUITTANCE_RECUPERATION_DEBOURSLOIS = "12";
	public static final String TYPE_QUITTANCE_RECUPERATION_TROPPERCU = "14";
	public static final String TYPE_QUITTANCE_RECUPERATION_ANNULATION = "26";
      
	//
	public static final String SOUS_TYPE_QUITTANCE_RECUPERATION_DEBOURSLOIS = "6";
	public static final String SOUS_TYPE_QUITTANCE_RECUPERATION_TROPPERCU = "6";
	public static final String SOUS_TYPE_QUITTANCE_RECUPERATION_ANNULATION = "15";
	 
	// Profil annulation
	public static final String PROFIL_ANNULATION = "14";
	// Pré Quittance
	public static final String MOTIF_VALIDATION_PREQUITTANCE = "Validation Pre_Quittance";
	public static final String MOTIF_CREATION_PREQUITTANCE ="Création Pre_Quittance";
	
	// Instruction
	public static final String ETAT_INSTRUCTION_CREATION ="1";
	public static final String ETAT_INSTRUCTION_ANNULATION ="2";
	
	public static final String BNEJ_NUMERO_SEPARATOR="/";
	
	public static final String RELANCE_COMPLETUDE_DOSSIER="Complétude dossier";
	public static final String RELANCE_SIGNATURE_PV="Signature PV";
	public static final String MOTIF_RECYCLAGE_REGLEMENT="Recyclage MAD";
	
	//Evol Lot 1 02/12/2021 : duplicata offre
	public static final String ETAT_OFFRE_ORGINALE="OFFRE ORGINALE / EN ATTENTE REGULARISATION";
	public static final String ETAT_OFFRE_DUPLICATA="OFFRE DUPLICATA / DEMANDE AVOCAT";
	
	
	
}
