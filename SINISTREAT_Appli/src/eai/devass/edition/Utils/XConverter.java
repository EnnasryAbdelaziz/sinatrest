package eai.devass.edition.Utils;

public class XConverter {
    public String xCONVERTER(String sMontants) {
	String v_ChaineFinal = "";
	String v_1Chiffre = "";
	String v_2Chiffre = "";
	String v_3Chiffre = "";
	TablesValeurs t_Valuers = new TablesValeurs();

	v_1Chiffre = sMontants.substring(0, 1);
	v_2Chiffre = sMontants.substring(1, 2);
	v_3Chiffre = sMontants.substring(2, 3);

	if (Integer.parseInt(v_1Chiffre) != 0) {
	    if (Integer.parseInt(v_1Chiffre) != 1) {
		v_ChaineFinal = t_Valuers.ValuerUnite(v_1Chiffre);
		v_ChaineFinal = v_ChaineFinal.concat("cents ");
	    } else {
		v_ChaineFinal = "cent ";
	    }

	}

	Integer val = Integer.valueOf(Integer.parseInt(v_2Chiffre) * 10 + Integer.parseInt(v_3Chiffre));

	if (val.intValue() <= 16) {
	    v_ChaineFinal = v_ChaineFinal.concat(t_Valuers.ValuerUnite(val.toString()));
	} else if (Integer.parseInt(v_2Chiffre) <= 6) {
	    v_ChaineFinal = v_ChaineFinal.concat(t_Valuers.ValuerDecimale(v_2Chiffre));
	    v_ChaineFinal = v_ChaineFinal.concat(t_Valuers.ValuerUnite(v_3Chiffre));
	} else if (Integer.parseInt(v_2Chiffre) == 8) {
	    v_ChaineFinal = v_ChaineFinal.concat(t_Valuers.ValuerUnite("4"));
	    v_ChaineFinal = v_ChaineFinal.concat(t_Valuers.ValuerDecimale("2"));
	    v_ChaineFinal = v_ChaineFinal.concat(t_Valuers.ValuerUnite(v_3Chiffre));
	} else {
	    if (Integer.parseInt(v_2Chiffre) == 7) {
		v_ChaineFinal = v_ChaineFinal.concat(t_Valuers.ValuerDecimale("6"));
	    } else {
		v_ChaineFinal = v_ChaineFinal.concat(t_Valuers.ValuerUnite("4"));
		v_ChaineFinal = v_ChaineFinal.concat(t_Valuers.ValuerDecimale("2"));
	    }
	    if (Integer.parseInt(v_3Chiffre) <= 6) {
		val = Integer.valueOf(10 + Integer.parseInt(v_3Chiffre));
		v_ChaineFinal = v_ChaineFinal.concat(t_Valuers.ValuerUnite(val.toString()));
	    } else {
		v_ChaineFinal = v_ChaineFinal.concat(t_Valuers.ValuerUnite("10"));
		v_ChaineFinal = v_ChaineFinal.concat(t_Valuers.ValuerUnite(v_3Chiffre));
	    }

	}

	return v_ChaineFinal;
    }
}
