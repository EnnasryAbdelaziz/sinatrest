package eai.devass.services;

public interface IServices {
	/**Service quittance*/
	public static final String SERVICE_EMISSION_QUITTANCE = "emissionQuittanceRGL";
	public static final String SERVICE_RECHERCHE_QUITTANCE = "searchQuittanceRGL";
	public static final String SERVICE_ORDONNONCEMENT= "serviceOrdonnoncement";
	public static final String SERVICE_POSITIONNEMENT= "positionnementUC";
	public static final String SERVICE_UPDATEPOSITIONNEMENT= "updatePositionnement";
	public static final String SERVICE_GENERER_MVT= "genererMouvement";
	
	/**Service recours*/
	public static final String SERVICE_CREATION_RECOURS = "serviceCreationContentieux";
	public static final String SERVICE_GESTION_CONVOCATION = "serviceGestionConvocation";
	public static final String SERVICE_GESTION_PROC_JUD = "serviceGestionProcedureJudiciaire";
	public static final String SERVICE_GESTION_JUGEMENT = "serviceGestionJugement";
	
	
	/**Service mission*/
	public static final String SERVICE_GESTION_MISSION = "gestionMission";
	public static final String SERVICE_RECHERCHE_MISSION = "rechercherMission";
	public static final String SERVICE_MISSIONNEMENT_FACADE = "servicesMissionnementFacade";
	public static final String SERVICE_NATURE_MISSION_FACADE = "natureMissionReference";
	public static final String SERVICE_GESTION_PRESTATAIRE = "gestionPrestataire";
	
	/**Service Ordonencement*/
	public static final String SERVICE_RECUPERATIONSEUIL = "recupererSeuil";
	public static final String SERVICE_CONSULTATION_ORG = "consulterOrg";
	
	
}
