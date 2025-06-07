package eai.devass.sinistreat.appli.prm;


public enum MotifMouvement {
	
	QUITTANCE_RECUPERATION {
		public String getCode() {
			return "4";
		}
		
		
	}
	;
	
	public abstract String getCode();
	
	

}
