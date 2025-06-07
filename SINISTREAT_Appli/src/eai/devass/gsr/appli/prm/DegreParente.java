package eai.devass.gsr.appli.prm;


public enum DegreParente {
	Victime {
		public String getCode() {
			return "0";
		}
		
		
	},
	Ascendant {
		public String getCode() {
			return "1";
		}
		
		
	},
	Conjoint {
		public String getCode() {
			return "10";
		}
		
		
	},
	Descendant {
		public String getCode() {
			return "20";
		}
		
		
	}
	;
	
	public abstract String getCode();
	
	

}
