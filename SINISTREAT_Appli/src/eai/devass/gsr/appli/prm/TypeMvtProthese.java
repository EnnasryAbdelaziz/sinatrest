/**
 * 
 */
package eai.devass.gsr.appli.prm;

/**
 * @author elfaismo
 *
 */
public enum TypeMvtProthese {
	
	Creation_Prothese {
		public long getId() {
			return 1;
		}
	},
	Renouvellement {
		public long getId() {
			return 2;
		}
	},
	Frais_Appareillage {
		public long getId() {
			return 3;
		}
	},
	;
	
	public abstract long getId();
	
	

}
