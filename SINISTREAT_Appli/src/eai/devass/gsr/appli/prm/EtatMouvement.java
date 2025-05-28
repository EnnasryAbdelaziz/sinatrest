package eai.devass.gsr.appli.prm;


public enum EtatMouvement {
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
	Supprimer {
		public long getId() {
			return 4;
		}
		
		
	},
	;
	
	public abstract long getId();
	
	

}
