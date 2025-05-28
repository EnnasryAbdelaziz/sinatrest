package eai.devass.services;


public enum ServicesExternes {
	EMISSION_QUITTANCE {
		public String getMethodeName() {
			return IMethods.GENERER_QUITTANCE;
		}
		public String getServiceName() {
			return IServices.SERVICE_EMISSION_QUITTANCE;
		}
		
	},
	CHERCHER_QUITTANCE {
		public String getMethodeName() {
			return IMethods.RECHERCHER_QUITTANCE;
		}
		public String getServiceName() {
			return IServices.SERVICE_RECHERCHE_QUITTANCE;
		}
		
	},
	POSITIONNEMENT_REGLEMENT {
		public String getMethodeName() {
			return IMethods.POSITIONNEMENT;
		}
		public String getServiceName() {
			return IServices.SERVICE_POSITIONNEMENT;
		}
		
	},
	
	UPDATEPOSITIONNEMENT_REGLEMENT {
		public String getMethodeName() {
			return IMethods.UPDATEPOSITIONNEMENT;
		}
		public String getServiceName() {
			return IServices.SERVICE_UPDATEPOSITIONNEMENT;
		}
		
	},
	
	POSITIONNEMENT_REGLEMENT_SANSORD {
		public String getMethodeName() {
			return IMethods.POSITIONNEMENT_SANSORD;
		}
		public String getServiceName() {
			return IServices.SERVICE_POSITIONNEMENT;
		}
		
	},
	
	RECUPERER_SEUIL{
		public String getMethodeName() {
			return IMethods.RECUPERATIONSEUIL;
		}
		public String getServiceName() {
			return IServices.SERVICE_RECUPERATIONSEUIL;
		}
	},
	
	RECUPERER_SEUIL_SUB{
	public String getMethodeName() {
		return IMethods.RECUPERERSEUILSUBORDONNE;
	}
	public String getServiceName() {
		return IServices.SERVICE_RECUPERATIONSEUIL;
	}
},
	
	GENERATION_MOUVEMENT_QUITTANCE {
		public String getMethodeName() {
			return IMethods.ANNULER_MOUVEMENT_RGL;
		}
		public String getServiceName() {
			return IServices.SERVICE_GENERER_MVT;
		}
		
	},
	ANNULER_QUITTANCE {
		public String getMethodeName() {
			return IMethods.ANNULER_QUITTANCE_RGL;
		}
		public String getServiceName() {
			return IServices.SERVICE_GENERER_MVT;
		}
		
	},
	REGLER_QUITTANCE {
		public String getMethodeName() {
			return IMethods.REGLERER_QUITTANCE_RGL;
		}
		public String getServiceName() {
			return IServices.SERVICE_GENERER_MVT;
		}
		
	},
	ORDONNONCEMENT_QUITTANCE {
		public String getMethodeName() {
			return IMethods.EXTRAIREORDONNONCEMENTSUPERIEUR;
		}
		public String getServiceName() {
			return IServices.SERVICE_ORDONNONCEMENT;
		}
		
	},
	
	
	SOUMETTRE_ORD {
		public String getMethodeName() {
			return IMethods.SOUMETTREPOSITINNEMENT;
		}
		public String getServiceName() {
			return IServices.SERVICE_ORDONNONCEMENT;
		}
		
	},
	


	
	ORDONNONCEMENT_AVALIDER{
		public String getMethodeName() {
			return IMethods.EXTRAIREORDONNONCEMENTAVALIDER;
		}
		public String getServiceName() {
			return IServices.SERVICE_ORDONNONCEMENT;
		}
		
	},
	CONSULTATION_ORG{
		public String getMethodeName() {
			return IMethods.CONSULTER_ORG;
		}
		public String getServiceName() {
			return IServices.SERVICE_CONSULTATION_ORG;
		}

	},
	CHERCHER_PRESTATAIRE {
		public String getMethodeName() {
			return IMethods.CHERCHER_PRESTATAIRE;
		}
		public String getServiceName() {
			return IServices.SERVICE_GESTION_PRESTATAIRE;
		}

	}
	;
	
	public abstract String getMethodeName();
	public abstract String getServiceName();
	

}
