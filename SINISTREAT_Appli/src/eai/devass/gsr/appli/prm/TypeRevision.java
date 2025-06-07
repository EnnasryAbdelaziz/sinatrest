package eai.devass.gsr.appli.prm;

public enum TypeRevision {
	Revision_H {
		public String getId() {
			return "1";
		}

	},
	Revision_B {
		public String getId() {
			return "2";
		}
	},
	Aggravation {
		public String getId() {
			return "3";
		}
	},
	Attenuation {
		public String getId() {
			return "4";
		}
	};
	public abstract String getId();
}
