package eai.devass.gsr.appli.prm;

public enum MotifEtat {
	Defaut_remise {
		public String getCode() {
			return "3";
		}
	},
	Rachat_rente {
		public String getCode() {
			return "18";
		}
	},
	Deces_rentier {
		public String getCode() {
			return "22";
		}
	},
	Remariage {
		public String getCode() {
			return "23";
		}
	},
	Attente_date_PEC_CNRA {
		public String getCode() {
			return "24";
		}
	},
	Annulation_Mvt {
		public String getCode() {
			return "25";
		}
	},
	Decision_directoire {
		public String getCode() {
			return "20";
		}
	},
	Versee_CNRA {
		public String getCode() {
			return "30";
		}
	}
	
	
	
;

	public abstract String getCode();

}
