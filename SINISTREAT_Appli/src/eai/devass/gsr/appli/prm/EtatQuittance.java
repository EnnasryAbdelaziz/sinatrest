package eai.devass.gsr.appli.prm;


public enum EtatQuittance {
	Cree {
		public long getId() {
			return 1;
		}
		
		
	},
	Validee {
		public long getId() {
			return 2;
		}
		
		
	},
	Annulee {
		public long getId() {
			return 3;
		}
		
		
	},
	En_instance {
		public long getId() {
			return 4;
		}
		
		
	},
	Reglee {
		public long getId() {
			return 5;
		}
		
		
	},
	Supprimee {
		public long getId() {
			return 6;
		}
	},
	Attente_validation_supp {
		public long getId() {
			return 7;
		}
	}
	;
	
	public abstract long getId();
	
	

}
