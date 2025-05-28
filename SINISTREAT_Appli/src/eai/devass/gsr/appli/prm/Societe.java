package eai.devass.gsr.appli.prm;


public enum Societe {
	RMA_WATANYA {
		public long getId() {
			return 1;
		}
		public String getRefSociete() {
			return "A";
		}
		
		
	},
	EL_WATANYA {
		public long getId() {
			return 2;
		}
		public String getRefSociete() {
			return "W";
		}
		
	},
	RMA {
		public long getId() {
			return 3;
		}
		public String getRefSociete() {
			return "R";
		}
		
	},
	MGF {
		public long getId() {
			return 4;
		}
		public String getRefSociete() {
			return "M";
		}
		
	}
	;
	
	public abstract long getId();
	public abstract String getRefSociete();
	
	

}
