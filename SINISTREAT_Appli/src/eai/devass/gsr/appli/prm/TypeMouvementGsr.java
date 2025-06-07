/**
 * 
 */
package eai.devass.gsr.appli.prm;


public enum TypeMouvementGsr {
	
	CONSIGNATION_CNRA {
		public long getId() {
			return 2;
		}
	},
	PRORATA_CNRA {
		public long getId() {
			return 4;
		}
	},
	RACHAT {
		public long getId() {
			return 5;
		}
	}
	,
	CNRA_REGLEMENTAIRE {
		public long getId() {
			return 20;
		}
	};
	public abstract long getId();
	
	

}
