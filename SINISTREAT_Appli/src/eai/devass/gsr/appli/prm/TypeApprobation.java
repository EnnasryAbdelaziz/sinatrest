package eai.devass.gsr.appli.prm;


public enum TypeApprobation {
	Sans_approbation {
		public long getId() {
			return 1;
		}
		
		
	},
	Approuvee {
		public long getId() {
			return 2;
		}
		
		
	},
	Non_approuvee {
		public long getId() {
			return 3;
		}
		
		
	}
	
	;
	
	public abstract long getId();
	
	

}
