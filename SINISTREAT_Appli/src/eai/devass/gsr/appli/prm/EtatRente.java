package eai.devass.gsr.appli.prm;


public enum EtatRente {
	Cree {
		public long getCode() {
			return 1;
		}
		
		
	},
	Modifie {
		public long getCode() {
			return 2;
		}
		
		
	},
	Remise_Pour_Correction {
		public long getCode() {
			return 3;
		}
		
		
	},
	Valide_En_Cours {
		public long getCode() {
			return 4;
		}
		
		
	},
	Echue {
		public long getCode() {
			return 5;
		}
		
		
	},
	Suspendue_Ou_Attente {
		public long getCode() {
			return 6;
		}
		
		
	},
	Supprimee_Par_Jugement {
		public long getCode() {
			return 7;
		}
		
		
	},
	Deces {
		public long getCode() {
			return 8;
		}
		
		
	},
	Rachat {
		public long getCode() {
			return 9;
		}
		
		
	},
	Versee_CNRA {
		public long getCode() {
			return 10;
		}
		
		
	},
	Remariage {
		public long getCode() {
			return 11;
		}
		
		
	},
	Cloture {
		public long getCode() {
			return 12;
		}
		
		
	},
	Supprime {
		public long getCode() {
			return 14;
		}
		
		
	},
	Non_Conforme {
		public long getCode() {
			return 15;
		}
		
		
	},
	En_Attente_De_Creation {
		public long getCode() {
			return 16;
		}
	},
	Sortie {
			public long getCode() {
				return 17;
			}

	}	
	;
	
	public abstract long getCode();
	
	

}
