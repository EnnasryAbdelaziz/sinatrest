package eai.devass.sinistreat.appli.usecase;


public interface IUseCase 
{
	/**Paramétrage*/
	public static final String RecherchePrestationUC = "5000";
	
	/** Contentieux */
	public static final String CreateRecoursReUC = "401";
	public static final String ModifierRecoursReUC = "402";
	public static final String CreateProcedureJudiciaireReUC = "403";
	public static final String ModifierProcedureJudiciaireReUC = "404";
	public static final String ConsulterProcedureJudiciaireReUC = "405";
	public static final String RechercherProcedureJudiciaireReUC = "406";
	public static final String CreateAudienceReUC = "407";
	public static final String ModifierAudienceReUC = "408";
	public static final String ConsulterAudienceJugementReUC = "409";
	public static final String RechercherAudienceJugementReUC = "410";
	public static final String SupprimerAudienceJugementReUC = "411";
	public static final String RechercheAudienceUC = "4334";
	public static final String AlertAudienceUC = "4335";

	/** Recours */
	public static final String CreateRecoursRsUC = "412";
	public static final String ModifierRecoursRsUC = "413";
	public static final String RechercherRecoursSortantUC = "425";
	public static final String ConsulterRecoursSortantUC = "426";
	public static final String CreateProcedureJudiciaireRsUC = "414";
	public static final String ModifierProcedureJudiciaireRsUC = "415";
	public static final String ConsulterProcedureJudiciaireRsUC = "428";
	public static final String RechercherProcedureJudiciaireRsUC = "427";
	public static final String CreateAudienceRsUC = "416";
	public static final String ModifierAudienceRsUC = "417";
	public static final String ConsulterAudienceJugementRsUC = "419";
	public static final String RechercherAudienceJugementRsUC = "429";
	public static final String SupprimerAudienceJugementRsUC = "420";
	public static final String RecherchePartieAdverseUC = "432";
	public static final String RouvrirRecoursRsUC = "418";
	public static final String CreateRecoursAmiableUC = "421";
	public static final String ModifierRecoursAmiableUC = "422";
	public static final String ConsulterRecoursAmiableUC = "423";
	public static final String RechercherRecoursAmiableUC = "424";
	public static final String RechercheRecoursUC = "431";
	public static final String RecupererRecoursEncoursUC = "4332";
	public static final String RechercheSinistreUC = "101";
	public static final String CreateSinistreUC = "102";
	public static final String modifierSinistre = "103";
	public static final String creerAyantDroit = "104";
	public static final String modifierAyantDroit = "105";
	public static final String supprimerAyantDroit = "106";
	public static final String RechercheSinistreByNameUC = "107";
	public static final String RechercheAyantDroitUC = "108";
	public static final String GestionEtatSinistreUC = "109";
	public static final String ValidationSinistreUC = "110";
	public static final String RecherchePoliceUC = "111";
	public static final String ConsulterSinistreUC = "112";
	public static final String ConsulterPoliceUC = "113";
	public static final String CalculReserveUC = "114";
	public static final String CalculCumulReglementUC = "115";
	public static final String CreateMouvementUC = "116";
	public static final String CreateMouvementAYUC = "117";
	public static final String CreateSinistreRepriseUC = "118";
	public static final String CalculReserveRepriseUC = "119";
	public static final String GestionEtatResSinistreUC = "120";
	public static final String ListEtatSinistreUC="123";
	public static final String ListMouvementSinistreUC="124";
	
	/** Reglement */
	public static final String RechercheReglementBisUC = "300";
	public static final String RechercheReglementUC = "301";
	public static final String CreateReglementUC = "302";
	public static final String CreateRecuperationUC = "315";
	public static final String ValiderReglementAvtCreationUC = "323";
	public static final String ModifierReglementUC = "324";
	public static final String SupprimerReglementUC = "325";
	public static final String ValidateReglementUC = "320";
	public static final String RechercheQuittanceExAAAUC = "330";
	public static final String ListEtatReglementUC = "331";
	
	/** Mission */
	public static final String CreateMissionUC = "701";
	public static final String ModifierMissionUC = "702";
	public static final String RechercheMissionUC = "703";
	public static final String RechercheNatureMissionUC = "704";
	public static final String RechercheRefNatMissionUC = "705";
	public static final String CreateCertificatMedicalUC = "201";
	public static final String ModifierCertificatMedicalUC = "202";
	public static final String SupprimerCertificatMedicalUC = "203";
	public static final String RechercheCertificatMedicalUC = "204";
	public static final String RechercheDernierCertificatMedicalUC = "206";
	public static final String RecherchePrestataireUC = "205";
	public static final String RechercheMedecinTraitantUC = "250";
	public static final String InvaliderListeCertifConstatationUC = "207";
	public static final String InvaliderListeCertifguerisonUC = "208";
	public static final String InvaliderListeCertifContreVisiteUC = "209";
	public static final String InvaliderListeCertifExpertiseUC = "210";
	public static final String InvaliderListeCertifAggravationUC = "211";
	public static final String InvaliderListeCertifRechuteUC = "212";
	public static final String RechercheCertificatMedicalHistoriqueUC = "213";
	public static final String ModifierReserveCertificatUC = "214";
	public static final String AjouterListeCertificatUC = "215";
	public static final String InvaliderListeCertificatUC = "216";
	public static final String InitCreationSinistreUC = "1000";
	public static final String InitModificationSinistreUC = "1001";
	public static final String InitCreationAyantDroitSinistreUC = "1002";
	public static final String InitGestionEtatSinistreUC = "1003";
	public static final String InitSinistreUC = "1004";
	public static final String InitValidationSinistreUC = "1005";
	public static final String InitCreateReglementUC = "1006";
	public static final String InitModificationAyantDroitUC = "1007";
	public static final String InitCreateReglementIntermediaireUC = "1009";
	public static final String InitGestionEtatReouvertureUC = "1008";
	public static final String InitCreationRecoursEntrantUC = "1010";
	public static final String InitCreationRecoursSortantUC = "1012";
	public static final String InitCreationAudienceUC = "1011";
	public static final String InitModificationAudienceUC = "1018";
	public static final String InitModificationRecoursSortantUC = "1013";
	public static final String InitCreationProcJudRSUC = "1014";
	public static final String InitCreationAudienceRsUC = "1015";
	public static final String InitModificationrocJudRSUC = "1016";
	public static final String InitModificationAudienceRsUC = "1017";
	public static final String InitRecoursAmiableUC = "1019";
	public static final String InitCreationRecuperationUC = "1022";
	public static final String InitConsultationSinistreUC = "1023";
	public static final String InitRechercheRecoursSortantUC = "1024";
	public static final String InitCreateMissionUC = "1026";
	public static final String InitModifierMissionUC = "1027";
	public static final String InitZoneUC = "1025";
	public static final String InitValidationReglementUC = "1021";
	public static final String CreerTransactionUC = "801";
	public static final String ModifierTransactionUC = "802";
	public static final String RechercheListeAffectationSinistreUC = "803";
	public static final String RechercheListeDecisionSinistreUC = "804";
	public static final String RechercheListeTraitementSinistreUC = "805";
	public static final String CreerConvocationUC = "806";
	public static final String ModifierConvocationUC = "807";
	public static final String CalculCapitalOffreUC = "808";
	public static final String CalculCapitalManuelUC = "809";
	public static final String InitCreateTransactionUC = "1080";
	public static final String InitCreateConvocationTransactionUC = "1081";
	public static final String InitRecherchePrestataireUC = "1020";
	public static final String InitValidationReglementPositionnementUC = "1100";
    public static final String InitValidationReglementSubordonneUC="1200";
    public static final String InitValidationReglementSuperieurUC="1300";

		
		
	/** Ref */
	public static final String RechercheIntermediaireUC = "310";
	public static final String RechercheTribunalUC = "311";
	public static final String RechercheChefGreffierUC = "321";
	public static final String RechercheBarreauUC = "322";
    public static final String RechercheUtilisateurUC = "901" ;
    
    
    /** Instruction **/
    public static final String  CreateInstructionUC = "10";
    public static final String  EditerInstructionUC = "11";
    
    /** Edition **/
    public static final String EditerLettreQuittanceIJUC ="1";
     public static final String EditerLettreQuittancesAutomatiqueUC ="7777777";
     
         /**BNEJ**/
         public static final String InitCreationLotBNEJUC="500";
        public static final String CreationLotBNEJUC="501";
       public static final String RechercheLotBNEJUC="502";
        public static final String CreationDossierBNEJUC="503";
     
        
        public static final String InitCreationDossierBnejUC="504";     
        public static final String InitModificationDossierBnejUC="505";     
        public static final String ModificationDossierBnejUC="506";     
        public static final String RechercheDossierBnejUC="507";  
        public static final String ConsultationLotBnejUC="508";  
        
        
        //Edition
        public static final String EditerListBnejUC="509"; 
        public static final String EditerSuiviTraitementBnejUC="510"; 
        
       
        
}
