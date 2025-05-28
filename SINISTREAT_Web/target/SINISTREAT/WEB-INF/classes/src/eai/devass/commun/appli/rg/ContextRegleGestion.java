package eai.devass.commun.appli.rg;


public enum ContextRegleGestion {
	CREATION {
		public String getContext() {
			return "creation";
		}
		
	},
	SUPPRESSION {
		public String getContext() {
			return "suppression";
		}
		
	},
	MODIFICATION {
		public String getContext() {
			return "modification";
		}
		
	},
	
	VALIDATION {
		public String getContext() {
			return "validation";
		}
		
	},
	ANNULATION {
		public String getContext() {
			return "annulation";
		}
		
	},
	BATCH {
		public String getContext() {
			return "batch";
		}
		
	},
	DEFAULT {
		public String getContext() {
			return "defaullt";
		}
		
	}

	;
	
	public abstract String getContext();
	

}
