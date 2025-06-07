/**
 * 
 */
package eai.devass.gsr.appli.prm;

/**
 * @author elfaismo
 *
 */
public enum EtatProthese {
	
	Cree {
		public long getId() {
			return 1;
		}
	},
	Modifiee {
		public long getId() {
			return 2;
		}
	},
	Validee {
		public long getId() {
			return 3;
		}
	},
	Annulee {
		public long getId() {
			return 4;
		}
	},
	Supprimee {
		public long getId() {
			return 5;
		}
	},
	Renouvele {
		public long getId() {
			return 6;
		}
	},
	;
	
	public abstract long getId();
	
	

}
