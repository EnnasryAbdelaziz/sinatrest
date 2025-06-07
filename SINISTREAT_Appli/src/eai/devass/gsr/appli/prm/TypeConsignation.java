package eai.devass.gsr.appli.prm;

public enum TypeConsignation {
	Nouvelle_Liquidation {
		public String getId() {
			return "1";
		}
		
		
	},
	Revision {
		public String getId() {
			return "2";
		}
		
		
	}
	;	
	public abstract String getId();
}
