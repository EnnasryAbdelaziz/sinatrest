/**
 * 
 */
package eai.devass.gsr.appli.prm;

/**
 * @author elfaismo
 *
 */
public enum EnumStatutHeritier {
	
	Cree {
		public long getId() {
			return 1;
		}
	},
	Actif {
		public long getId() {
			return 2;
		}
	},
	Modifiée {
		public long getId() {
			return 3;
		}
	},
	Supprimee {
		public long getId() {
			return 4;
		}
	},
	;
	
	public abstract long getId();
	
	

}
