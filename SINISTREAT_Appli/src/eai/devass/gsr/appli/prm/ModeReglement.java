package eai.devass.gsr.appli.prm;


public enum ModeReglement {
	Cheque {
		public long getId() {
			return 1;
		}
		
		
	},
	
	Virement {
		public long getId() {
			return 2;
		}
		
		
	},
	Mandat {
		public long getId() {
			return 3;
		}
		
		
	},
	Hebdomadaire {
		public long getId() {
			return 4;
		}
		
		
	}
	
	;
	
	public abstract long getId();
	
	

}
