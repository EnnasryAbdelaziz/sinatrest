package eai.devass.gsr.appli.prm;


public enum TypeQuittance {
	Reglement {
		public long getId() {
			return 1;
		}
		
		public int getCode() {
			return 2;
		}
		
		
	},
	Revision {
		public long getId() {
			return 2;
		}
		public int getCode() {
			return 0;
		}
		
	},
	Virement {
		public long getId() {
			return 3;
		}
		public int getCode() {
			return 0;
		}
		
	} ,
	Recuperation {
		public long getId() {
			return 4;
		}
		public int getCode() {
			return 26;
		}
		
	}
	
	;
	
	public abstract long getId();
	public abstract int getCode();
	
	

}
