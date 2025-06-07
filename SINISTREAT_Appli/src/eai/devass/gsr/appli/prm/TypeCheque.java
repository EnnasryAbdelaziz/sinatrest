package eai.devass.gsr.appli.prm;


public enum TypeCheque {
	Normale {
		public long getId() {
			return 1;
		}
		
		
	},
	CNRA {
		public long getId() {
			return 2;
		}
		
		
	}
	;
	
	public abstract long getId();
	
	

}
