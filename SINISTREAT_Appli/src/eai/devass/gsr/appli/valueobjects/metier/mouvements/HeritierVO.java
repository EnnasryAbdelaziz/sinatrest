
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.converter.AConverter;


/**
Value Object de Heritier
@author Nom Prenom (email)
*/
@AConverter(entiteDist="eai.devass.gsr.appli.modele.metier.mouvements.Heritier")
public class HeritierVO extends  RechListeVO implements ITracable{

	@AConverter(propertyDist="id")
	private long id;
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger("loggerSINAT");
/**
 * Nom bénéficiaire   
 */ 
	@Indexation(label="nomBeneficiaire",analyzed=false)
	@AConverter(propertyDist="nomBeneficiaire")
	private String nomBeneficiaire;
	/**
	 * Prénom bénéficiaire
	 */
	@Indexation(label="prenomBeneficiaire",analyzed=false)
	@AConverter(propertyDist="prenomBeneficiaire")
	private String prenomBeneficiaire;


	/**
	 * montant
	 */
	@Indexation(label="montant",analyzed=false)
	@AConverter(propertyDist="montant")
	private String montant;
	/**
	 * @return the montant
	 */
	public String getMontant() {
		return montant;
	}

	/**
	 * @param montant the montant to set
	 */
	public void setMontant(String montant) {
		this.montant = montant;
	}

	/**
	 * @return the nomBeneficiaire
	 */
	public String getNomBeneficiaire() {
		return nomBeneficiaire;
	}

	/**
	 * @param nomBeneficiaire the nomBeneficiaire to set
	 */
	public void setNomBeneficiaire(String nomBeneficiaire) {
		this.nomBeneficiaire = nomBeneficiaire;
	}

	/**
	 * @return the prenomBeneficiaire
	 */
	public String getPrenomBeneficiaire() {
		return prenomBeneficiaire;
	}

	/**
	 * @param prenomBeneficiaire the prenomBeneficiaire to set
	 */
	public void setPrenomBeneficiaire(String prenomBeneficiaire) {
		this.prenomBeneficiaire = prenomBeneficiaire;
	}

	@Indexation(label="beneficiaire",analyzed=false) 
	private String beneficiaire;
	@Indexation(label="numCIN",analyzed=false) 
	@AConverter(propertyDist="numCIN")
	private String numCIN;
	@Indexation(label="quotePart",analyzed=false) 
	@AConverter(propertyDist="quotePart")
	private String quotePart;
	
	@Indexation(label="dateCreation",analyzed=false) 
	@AConverter(propertyDist="dateCreation")
	private String dateCreation;
	@Indexation(label="MvtDecesRentier",analyzed=false)
	@AConverter(propertyDist="refMvtDecesRentier.id")
	private String refMvtDecesRentier;
	@Indexation(label="refQuittanceGSR",analyzed=false)
	@AConverter(propertyDist="refQuittanceGSR.id")
	private String refQuittanceGSR;
	
	@Indexation(label="StatutHeritier",analyzed=false)
	@AConverter(propertyDist="refStatutHeritier.id")
	private String refStatutHeritier;

	/**
	 * @return the refStatutHeritier
	 */
	public String getRefStatutHeritier() {
		return refStatutHeritier;
	}

	/**
	 * @param refStatutHeritier the refStatutHeritier to set
	 */
	public void setRefStatutHeritier(String refStatutHeritier) {
		this.refStatutHeritier = refStatutHeritier;
	}

	/**
	 * @return the refQuittanceGSR
	 */
	public String getRefQuittanceGSR() {
		return refQuittanceGSR;
	}

	/**
	 * @param refQuittanceGSR the refQuittanceGSR to set
	 */
	public void setRefQuittanceGSR(String refQuittanceGSR) {
		this.refQuittanceGSR = refQuittanceGSR;
	}
	
	@AConverter(propertyDist="ordreQtc")
	private String idHer;
	

//	private Object refMvtDecesRentierObj;
//	@AConverter(propertyDist="refMvtDecesRentier")
//	private String refMvtDecesRentierLabel;
	private String typeParentA;
	private String typeParentB;



	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getBeneficiaire() {
		return this.beneficiaire;
	}

	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}
	public String getNumCIN() {
		return this.numCIN;
	}

	public void setNumCIN(String numCIN) {
		this.numCIN = numCIN;
	}
	public String getQuotePart() {
		return this.quotePart;
	}

	public void setQuotePart(String quotePart) {
		this.quotePart = quotePart;
	}
	public String getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getRefMvtDecesRentier() {
		return this.refMvtDecesRentier;
	}

	public void setRefMvtDecesRentier(String refMvtDecesRentier) {
		this.refMvtDecesRentier = refMvtDecesRentier;
	}	
	
//	public Object getRefMvtDecesRentierObj() {
//		return this.refMvtDecesRentierObj;
//	}
//
//	public void setRefMvtDecesRentierObj(Object refMvtDecesRentierObj) {
//		this.refMvtDecesRentierObj = refMvtDecesRentierObj;
//	}	
//	
//	public String getRefMvtDecesRentierLabel() {
//		return this.refMvtDecesRentierLabel;
//	}
//
//	public void setRefMvtDecesRentierLabel(String refMvtDecesRentierLabel) {
//		this.refMvtDecesRentierLabel = refMvtDecesRentierLabel;
//	}	
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
			listAttributes.add("beneficiaire");
			listAttributes.add("numCIN");
			listAttributes.add("quotePart");
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
	 * @return the idHer
	 */
	public String getIdHer() {
		return idHer;
	}

	/**
	 * @param idHer the idHer to set
	 */
	public void setIdHer(String idHer) {
		this.idHer = idHer;
	}


	
	


}

