package eai.devass.gsr.appli.prm;


public enum CnraINfoCheque {
	POUR_LECOMPTEDE {
		public String getInfo() {
			return "CNRA";
		}
		
		
	},
	ADRESSE {
		public String getInfo() {
			return "Riad Business Center Avenue Annakhil Hay Riad B.P 2173";
		}
		
		
	},
	VILLE {
		public String getInfo() {
			return "025";
		}
		
		
	},
	DETAIL_REGLEMENT {
		public String getInfo() {
			return "Versement CNRA";
		}
		
		
	}
	;
	
	public abstract String getInfo();
	
	

}
