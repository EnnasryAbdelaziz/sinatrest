
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;


/**
Value Object de MvtDecesRentier
@author Nom Prenom (email)
*/
@AConverter(entiteDist="eai.devass.gsr.appli.modele.metier.mouvements.MvtDecesRentier")
public class MvtDecesRentierVO extends  MouvementVO  implements ITracable{


	private static final long serialVersionUID = 1L;
	
	@Indexation(label="dateDeces",analyzed=false) 
	@AConverter(propertyDist="dateDeces" ,pattern="yyyyMMdd")
	private String dateDeces;
	@Indexation(label="mntProrata",analyzed=false) 
	@AConverter(propertyDist="mntProrata")
	private String mntProrata;
	@Indexation(label="dateCreation",analyzed=false) 
	@AConverter(propertyDist="dateCreation" ,pattern="yyyyMMdd")
	private String dateCreation;

		
		
	/**
	 * Trop perçu
	 */
		@Indexation(label="tropPercu",analyzed=false)
		@AConverter(propertyDist="tropPercu")
		private String tropPercu;

	/**
	 * Montant global versé aux héritiers
	 */
		@Indexation(label="mntGlobalVersee",analyzed=false)
		@AConverter(propertyDist="mntGlobalVersee")
		private String mntGlobalVersee;
		
	/**
	dateReceptionCertifDeces   
	*/ 
		@Indexation(label="dateReceptionCertifDeces",analyzed=false)
		@AConverter(propertyDist="dateReceptionCertifDeces" ,pattern="yyyyMMdd")
		private String dateReceptionCertifDeces;
		/**
		 * emissionQuittanceDeces
		 */
		@AConverter(propertyDist="emissionQuittanceDeces")
		private String emissionQuittanceDeces;

	

		/**
		 * @return the tropPercu
		 */
		public String getTropPercu() {
			return tropPercu;
		}

		/**
		 * @param tropPercu the tropPercu to set
		 */
		public void setTropPercu(String tropPercu) {
			this.tropPercu = tropPercu;
		}

		/**
		 * @return the mntGlobalVersee
		 */
		public String getMntGlobalVersee() {
			return mntGlobalVersee;
		}

		/**
		 * @param mntGlobalVersee the mntGlobalVersee to set
		 */
		public void setMntGlobalVersee(String mntGlobalVersee) {
			this.mntGlobalVersee = mntGlobalVersee;
		}

		/**
		 * @return the dateReceptionCertifDeces
		 */
		public String getDateReceptionCertifDeces() {
			return dateReceptionCertifDeces;
		}

		/**
		 * @param dateReceptionCertifDeces the dateReceptionCertifDeces to set
		 */
		public void setDateReceptionCertifDeces(String dateReceptionCertifDeces) {
			this.dateReceptionCertifDeces = dateReceptionCertifDeces;
		}
	@AConverter(propertyDist="refsHeritier")
	private List<HeritierVO> refsHeritier ;
	private String typeParentA;
	private String typeParentB;



	public String getDateDeces() {
		return this.dateDeces;
	}

	public void setDateDeces(String datDeces) {
		this.dateDeces = datDeces;
	}
	public String getMntProrata() {
		return this.mntProrata;
	}

	public void setMntProrata(String mntProrata) {
		this.mntProrata = mntProrata;
	}
	public String getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public List getRefsHeritier() {
		return this.refsHeritier;
	}

	public void setRefsHeritier(List refsHeritier) {
		this.refsHeritier = refsHeritier;
	}
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
			listAttributes.add("dateDeces");
			listAttributes.add("mntProrata");
			//listAttributes.add("arreragesRente");
			listAttributes.add("tropPercu");
			listAttributes.add("mntGlobalVersee");
			listAttributes.add("dateReceptionCertifDeces");
			listAttributes.add("dateCreation");
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this,listAttributes);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}	
		return rslt;
	}

	public String getTypeParentA(){
		return typeParentA; 
	}
	
	public void setTypeParentA (String typeParentA){
		this.typeParentA = typeParentA;
		this.typeParentB = typeParentA;
	}

	public String getTypeParentB(){
		return typeParentB; 
	}
	
	public void setTypeParentB (String typeParentB){
		this.typeParentB = typeParentB;
	}

	/**
	 * @return the emissionQuittanceDeces
	 */
	public String getEmissionQuittanceDeces() {
		return emissionQuittanceDeces;
	}

	/**
	 * @param emissionQuittanceDeces the emissionQuittanceDeces to set
	 */
	public void setEmissionQuittanceDeces(String emissionQuittanceDeces) {
		this.emissionQuittanceDeces = emissionQuittanceDeces;
	}


	


}

