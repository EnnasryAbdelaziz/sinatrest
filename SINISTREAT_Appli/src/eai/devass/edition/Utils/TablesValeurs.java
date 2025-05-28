package eai.devass.edition.Utils;

public class TablesValeurs {
    private String[] v_TableUinte;
    private String[] v_TableDecimale;

    public TablesValeurs() {
	TableDecimale();
	TableUinte();
    }

    private String[] TableUinte() {
	this.v_TableUinte = new String[17];

	this.v_TableUinte[0] = "";
	this.v_TableUinte[1] = "un ";
	this.v_TableUinte[2] = "deux ";
	this.v_TableUinte[3] = "trois ";
	this.v_TableUinte[4] = "quatre ";
	this.v_TableUinte[5] = "cinq ";
	this.v_TableUinte[6] = "six ";
	this.v_TableUinte[7] = "sept ";
	this.v_TableUinte[8] = "huit ";
	this.v_TableUinte[9] = "neuf ";
	this.v_TableUinte[10] = "dix ";
	this.v_TableUinte[11] = "onze ";
	this.v_TableUinte[12] = "douze ";
	this.v_TableUinte[13] = "treize ";
	this.v_TableUinte[14] = "quatorze ";
	this.v_TableUinte[15] = "quinze ";
	this.v_TableUinte[16] = "seize ";

	return this.v_TableUinte;
    }

    private String[] TableDecimale() {
	this.v_TableDecimale = new String[7];

	this.v_TableDecimale[1] = "dix ";
	this.v_TableDecimale[2] = "vingt ";
	this.v_TableDecimale[3] = "trente ";
	this.v_TableDecimale[4] = "quarante ";
	this.v_TableDecimale[5] = "cinquante ";
	this.v_TableDecimale[6] = "soixante ";

	return this.v_TableDecimale;
    }

    public String ValuerDecimale(String i_index) {
	int i_val = Integer.parseInt(i_index);
	return this.v_TableDecimale[i_val];
    }

    public String ValuerUnite(String i_index) {
	int i_val = Integer.parseInt(i_index);
	return this.v_TableUinte[i_val];
    }

}