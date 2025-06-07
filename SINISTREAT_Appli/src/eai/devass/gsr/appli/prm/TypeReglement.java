package eai.devass.gsr.appli.prm;


public enum TypeReglement {
	Direct {
		public long getId() {
			return 1;
		}
		
		
	},
	
	Intermediare {
		public long getId() {
			return 2;
		}
		
		
	},
	Mondat {
		public long getId() {
			return 3;
		}
		
		
	}	
	;
	
	public abstract long getId();
	
	

}
