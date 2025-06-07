package eai.devass.gsr.appli.prm;

public enum NatureQuittance {
	Rente_periodique {
		public long getId() {
			return 25;
		}
		public String getRubrique() {
			return "01";
		}

	},
	Prorata_rente {
		public long getId() {
			return 26;
		}
		public String getRubrique() {
			return "02";
		}

	},
	Complement_rente {
		public long getId() {
			return 27;
		}
		public String getRubrique() {
			return "15";
		}

	},
	rachat_apres_constitution_recours_compagnie {
		public long getId() {
			return 28;
		}
		public String getRubrique() {
			return "03";
		}

	},
	rachat_apres_constitution_recours_victime {
		public long getId() {
			return 29;
		}
		public String getRubrique() {
			return "04";
		}
	},
	
	capital_constitutif_a_la_CNRA {
		public long getId() {
			return 30;
		}
		public String getRubrique() {
			return "05";
		}
	},
	
	Versement_prorata_capital_constitutif_a_la_CNRA {
		public long getId() {
			return 32;
		}
		public String getRubrique() {
			return "08";
		}
	},

	Capital_remariage {
		public long getId() {
			return 33;
		}
		public String getRubrique() {
			return "01";
		}
	},
	Augmentation_Capital_constitutif {
		public long getId() {
			return 35;
		}
		public String getRubrique() {
			return "44";
		}
		
		
	} ,
	Diminution_Capital_constitutif {
		public long getId() {
			return 36;
		}
		public String getRubrique() {
			return "45";
		}
		
	},
	
	Augmentation_Capital_constitutif_AT {
		public long getId() {
			return 4;
		}
		public String getRubrique() {
			return "44";
		}
		public String getCodePrestataionReglementAT() {
			return "29";
		}
		public String getLblPrestataionReglementAT() {
			return "Augm.capita constit.";
		}
		
	} ,
	Diminution_Capital_constitutif_AT {
		public long getId() {
			return 4;
		}
		public String getRubrique() {
			return "45";
		}
		public String getCodePrestataionReglementAT() {
			return "30";
		}
		public String getLblPrestataionReglementAT() {
			return "Dimi.capita constit.";
		}
		
	},

	Prothese {
		public long getId() {
			return 40;
		}
		public String getRubrique() {
			return "54";
		}
	},
	


	Complement_Capital_constitutif_CNRA {
		public long getId() {
			return 31;
		}
		public String getRubrique() {
			return "06";
		}
	},
	Remboursement_par_prelevement {
		public long getId() {
			return 49;
		}
		public String getRubrique() {
			return "13";
		}
	},
	
	Rembourssement {
		public long getId() {
			return 15;
		}
		public String getRubrique() {
			return "12";
		}
	},
	Virement_Capital_constitutif {
		public long getId() {
			return 34;
		}
		public String getRubrique() {
			return "43";
		}
		
	},
	Virement_Capital_constitutif_AT {
		public long getId() {
			return 34;
		}
		public String getRubrique() {
			return "43";
		}
		public String getCodePrestataionReglementAT() {
			return "28";
		}
		public String getLblPrestataionReglementAT() {
			return "Virt.capita constit.";
		}
		
	},
	Droits_Taxes {
		public long getId() {
			return 43;
		}
		public String getRubrique() {
			return "48";
		}
	}
	
	;

	public abstract long getId();
	public abstract String getRubrique();
	
	// Seulement dans le cas de generation des quittances d'equilibre
	public String getCodePrestataionReglementAT() {
		return null;
	}
	public String getLblPrestataionReglementAT() {
		return null;
	}

}
