package eai.devass.edition.Utils;

public class ChiffresConverter {
    static XConverter xc = new XConverter();

    public static String convert(String sMontant) {

	String sChaineZero = "";
	String MontantVersChaine = "";

	int iLonguerMontant = 12;
	
	if (sMontant != null && sMontant.length() > 0 && sMontant.split("\\.")[0].length() <= 9) {
	    int pos = sMontant.indexOf(".");
	    if (pos == -1) {
		sMontant = sMontant.concat(".00");
		pos = sMontant.indexOf(".");
	    }else{
		while(sMontant.length() - pos < 3){
		    sMontant = sMontant.concat("0");
		    pos = sMontant.indexOf(".");
		}
	    }
	    
	    if (sMontant.length() - pos <= 3) {
		iLonguerMontant = 12 - sMontant.length();
		
	    } else {
		sMontant = sMontant.substring(0, pos + 3);
		iLonguerMontant = 12 - sMontant.length();
	    }

	    for (int i = 1; i <= iLonguerMontant; i++) {
		sChaineZero = sChaineZero.concat("0");
	    }
	    sChaineZero = sChaineZero.concat(sMontant);
	    sMontant = sChaineZero;
	    
	} else {
	    MontantVersChaine = "";
	    return MontantVersChaine;
	}

	String v_1Chiffre = sMontant.substring(0, 3);
	String v_2Chiffre = sMontant.substring(3, 6);
	String v_3Chiffre = sMontant.substring(6, 9);
	String v_4Chiffre = sMontant.substring(10, 12);

	boolean dirhamExiste = false;
	if (Integer.parseInt(v_1Chiffre) != 0) {
	    MontantVersChaine = MontantVersChaine.concat(xc.xCONVERTER(v_1Chiffre));
	    MontantVersChaine = MontantVersChaine.concat("million ");
	    dirhamExiste = true;
	}
	if (Integer.parseInt(v_2Chiffre) != 0) {
	    if (Integer.parseInt(v_2Chiffre) == 1) {
		MontantVersChaine = MontantVersChaine.concat("mille ");
		dirhamExiste = true;
	    } else {
		MontantVersChaine = MontantVersChaine.concat(xc.xCONVERTER(v_2Chiffre));
		MontantVersChaine = MontantVersChaine.concat("mille ");
		dirhamExiste = true;
	    }
	}
	if (Integer.parseInt(v_3Chiffre) != 0) {
	    MontantVersChaine = MontantVersChaine.concat(xc.xCONVERTER(v_3Chiffre));
	    dirhamExiste = true;
	}
	
	if(dirhamExiste) {
		MontantVersChaine = MontantVersChaine.concat("Dhs, ");
	}

	if (Integer.parseInt(v_4Chiffre) != 0) {
	    MontantVersChaine = MontantVersChaine.concat(xc.xCONVERTER("0".concat(v_4Chiffre)));
	    MontantVersChaine = MontantVersChaine.concat("Cts.");
	}

	return MontantVersChaine;
    }        

    /**
    * Renvoie le nombre en lettre, <BR>
    * ex: 1234567890 devient : un milliard deux cent trente quatre million cinq
    * cent soixante sept mille huit cent quatre vingt dix
    */
    public String nombreEnLettre(String nombreString) {
    	long nombre=Long.valueOf(nombreString);
	    // Initilisation de la réponse
	    String reponse = "";
	    // Rang du paquet actuel, on va parcourir le nombre de gauche à droite,
	    // le premier paquet de 2013 sera donc : 013
	    int rang = 0;
	    while (nombre > 0) {
	    if ((nombre % 1000)!=0) {
	    	String n=String.valueOf(nombre % 1000);
	    	if(n.length()==2){
	    		n="0"+n;
	    	}
	    	if(n.length()==1){
	    		n="00"+n;
	    	}
		    // on ajoute le paquet devant la réponse
	    	reponse = xc.xCONVERTER(n) + " " + tab[rang]+" " + reponse;
	    }
	    if (tab[rang].equals("mille") && (nombre % 1000 == 1)) {
	    reponse = reponse.substring(3);
	    }
	
	    // on passe au paquet suivant
	    nombre = nombre / 1000;
	    rang++;
	    }
	    return reponse.trim();
    }
 // Nom des différents types de paquet de nombre
    private static final String[] tab = { "", "MILLE"};
}
