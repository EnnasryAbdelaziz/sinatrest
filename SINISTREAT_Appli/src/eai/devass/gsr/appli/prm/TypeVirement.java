package eai.devass.gsr.appli.prm;


public enum TypeVirement {
	INTERN {
		public long getId() {
			return 1;
		}
		
		
	},
	
	OMC {
		public long getId() {
			return 2;
		}
		
		
	}
	;
	
	public abstract long getId();
	
	

}
