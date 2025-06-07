package eai.devass.sinistreat.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class ResultatOffreVO implements IValueObject{

		private String id;
		private String libelle;
		

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getLibelle() {
			return libelle;
		}

		public void setLibelle(String newLibelle) {
			libelle = newLibelle;
		}
	
}
