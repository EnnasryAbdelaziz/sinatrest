/**
 * 
 */
package eai.devass.gsr.appli.prm;

/**
 * @author elfaismo
 *
 */
public enum EnumOrigineRachat {
	
	origineCompagnie {
		public long getId() {
			return 1;
		}
	},
	origineVictime {
		public long getId() {
			return 2;
		}
	},
	origineConciliation {
		public long getId() {
			return 3;
		}
	},
	;
	
	public abstract long getId();
	
	

}
