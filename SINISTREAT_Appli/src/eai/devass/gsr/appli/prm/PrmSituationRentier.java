package eai.devass.gsr.appli.prm;


public enum PrmSituationRentier {
	Scolarise {
		public long getCode() {
			return 1;
		}
		
		
	},
	Formation_Professionnelle {
		public long getCode() {
			return 2;
		}
		
		
	},
	Handicape {
		public long getCode() {
			return 3;
		}
		
		
	};
	
	public abstract long getCode();
	
	

}
