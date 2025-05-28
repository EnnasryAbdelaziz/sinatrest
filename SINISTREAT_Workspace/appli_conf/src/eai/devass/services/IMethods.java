package eai.devass.services;

public interface IMethods {
	/**Service quittance*/
	public static final String GENERER_QUITTANCE = "generateQuittanceRR";
	public static final String RECHERCHER_QUITTANCE = "searchQuittanceReglement";
	public static final String POSITIONNEMENT= "positionnement";
	public static final String UPDATEPOSITIONNEMENT= "updatePositionnement";
	public static final String ANNULER_MOUVEMENT_RGL = "annulerMouvementReglement";
	public static final String ANNULER_QUITTANCE_RGL = "annulerQuittance";
	public static final String REGLERER_QUITTANCE_RGL = "generateMouvementReglement";
	public static final String POSITIONNEMENT_SANSORD = "positionnementSansOrd";
	
	
	/**Service recours*/
	public static final String CREER_RECOURS = "creationRecoursEntrant";
	public static final String MODIFIER_RECOURS = "modifierRecoursEntrant";
	public static final String CLASSER_RECOURS = "classerRecours";
	public static final String CLOTURER_RECOURS = "cloturerRecours";
	public static final String CREER_PROCEDURE_JUDICIAIRE = "creationProcedureJudiciaire";
	public static final String MODIFIER_PROCEDURE_JUDICIAIRE = "modifierProcedureJudiciaire";
	public static final String CREER_JUGEMENT = "creerJugement";
	public static final String CREER_AUDIENCE = "creerConvocation";
	public static final String MODIFIER_JUGEMENT = "modifierJugement";
	public static final String MODIFIER_AUDIENCE = "modifierConvocation";
	public static final String REOUVRIR_RECOURS = "reouvrirRecours";
	
	/**Service mission*/
	public static final String CREER_MISSION = "validateMission";
	public static final String MODIFIER_MISSION = "modifierMission";
	public static final String CHERCHER_PRESTATAIRE = "chercherPrestataire";
	
	/**Service Ordonnoncement*/
	public static final String RECUPERATIONSEUIL = "recupererSeuil";
	public static final String RECUPERERSEUILSUBORDONNE ="recupererSeuilSubordonne";
	public static final String EXTRAIREORDONNONCEMENTSUPERIEUR = "ExtraireOrdonnoncementSuperieur";
	//public static final String EXTRAIREORDONNONCEMENTSUPERIEUR = "soumettreOrdonnoncement";
	public static final String CONSULTER_ORG = "consulterOrg";
	public static final String EXTRAIREORDONNONCEMENTAVALIDER = "ExtraireOrdonnoncementAValider";
	public static final String SOUMETTREPOSITINNEMENT = "soumettreOrdonnoncement";

}
