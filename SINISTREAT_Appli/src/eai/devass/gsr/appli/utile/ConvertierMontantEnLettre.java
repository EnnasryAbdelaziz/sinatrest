package eai.devass.gsr.appli.utile;


public class ConvertierMontantEnLettre {

    private static String[] group_unit = {"", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix", "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix sept", "dix huit", "dix neuf"};
    private static String[] group_diz = {"", "dix", "vingt", "trente", "quarante", "cinquante", "soixante", "", "quatre vingt"};
    private static String[] group_cent = {"", "cent", "mille", "million", "milliard"};
    private static String[] group_mi = {"", "mille", "million", "milliard"};


    private static String calculer(int val) {
        int r = val % 100;
        int d = val / 100;
        String s = "";
        if (r < 20) {
            s = group_unit[r];
        } else {
            int r1 = r % 10;
            int r2 = r / 10;
            if (r2 < 7 || r2 == 8) {
                s = group_diz[r2];
                if (r2 == 8 && r1 == 0) {
                    s += "s";
                }
                s += " " + group_unit[r1];
            } else {
                s = group_diz[r2 - 1] + " " + group_unit[r1 + 10];
            }
        }
        if (d != 0) {
            s = group_cent[1] + " " + s;
            if (d > 1) {
                s = group_unit[d] + " " + s;
            }
        }
        return s;
    }

    
    public static String montantToLettre(String montant, String unite, String sousUnite) {
         
    	if(montant == null) {
    		return null;
    	}
    	
    	if(montant.indexOf('.') < 0) {
    		montant = montant + ".00";
    	}
    	
    	String[] split = montant.split("\\.");
        
    	long partieEnt = 0;
        int partieFraq = 0;
    	try {
    		partieEnt = Long.parseLong(split[0]);
            partieFraq = Integer.parseInt(split[1]);
    		
    	} catch(Exception e) {
    		return null;
    	}
    	
    	int i = 0;
        int r = 0;
        long nb = partieEnt;
        String s = "";
        String montantLettre = "";
        do {
            r = (int) (nb % 1000);
            nb = nb / 1000;
            if (r != 0) {
                if (r == 1 && i == 1) {
                    s = group_mi[i];
                } else {
                    s = calculer(r) + " " + group_mi[i];
                }
            }
            montantLettre = s + " " + montantLettre;
            i++;
        } while (nb != 0);
        if(montantLettre.substring(montantLettre.length() - 1, montantLettre.length()).equals(" ")){
        	montantLettre = montantLettre.substring(0, montantLettre.length() - 1);
        }
        if (partieFraq != 0) {
            montantLettre = montantLettre + "" + unite + " et " + calculer(partieFraq) + " " + sousUnite;
        }else{
        	montantLettre = montantLettre + "" + unite;
        }
        return montantLettre;
    }
}