package eai.devass.sinistreat.appli.utils.entites;

public interface IEtat {
	
	public final static String NULL = "0"; // etat non reel mais qui permet d'identifier que la mission n'a pas encore d'état 
	public final static String CREE = "1";
	public final static String EN_ATTENTE_TRAITEMENT = "2";
	public final static String EN_VERIFICATION_REPONSE_HONORAIRE = "3";
	public final static String COMPLEMENT_DEMANDE = "6";
	public final static String COMPLEMENT_FOURNIE = "7";
	public final static String EN_VERIFICATION_REPONSE = "4";
	public final static String EN_VERIFICATION_HONORAIRE = "8";
	public final static String POURSUIVIE = "9";
	public final static String REFUSEE = "5";
	public final static String ANNULE = "13";
	public final static String CLOTURE = "10";
	
	public final static String EN_ATTENTE_REPONSE_ET_VERIFICATION = "14";	
	public final static String EN_ATTENTE_DECISION ="";
	
	public final static String DECISION_VERIFICATION_N1 = "11";
	public final static String DECISION_VERIFICATION_N2 = "12";
	
	
}


