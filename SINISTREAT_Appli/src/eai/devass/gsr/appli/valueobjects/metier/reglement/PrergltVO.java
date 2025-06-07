package eai.devass.gsr.appli.valueobjects.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeChequeVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeVirementVO;

/**
 * Value Object de Prerglt
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.reglement.Prerglt")
public class PrergltVO extends RechListeVO implements ITracable {

	private static final long serialVersionUID = 1L;
	@AConverter(propertyDist = "id")
	private long id;
	private Logger logger = Logger.getLogger("loggerSINAT");
	@Indexation(label = "pourLeCompte", analyzed = false)
	@AConverter(propertyDist = "pourLeCompte")
	private String pourLeCompte;

	@Indexation(label = "adresse", analyzed = false)
	@AConverter(propertyDist = "adresse")
	private String adresse;

	@Indexation(label = "details", analyzed = false)
	@AConverter(propertyDist = "details")
	private String details;

	@Indexation(label = "lblVirement", analyzed = false)
	@AConverter(propertyDist = "lblVirement")
	private String lblVirement;

	@Indexation(label = "numCIN", analyzed = false)
	@AConverter(propertyDist = "numCIN")
	private String numCIN;

	@Indexation(label = "numRIB", analyzed = false)
	@AConverter(propertyDist = "numRIB")
	private String numRIB;

	@Indexation(label = "refBordereau", analyzed = false)
	@AConverter(propertyDist = "refBordereau")
	private String refBordereau;

	@Indexation(label = "refRglt", analyzed = false)
	@AConverter(propertyDist = "refRglt")
	private String refRglt;

	@Indexation(label = "dateCreation", analyzed = false)
	@AConverter(propertyDist = "dateCreation")
	private String dateCreation;

	@Indexation(label = "TypeCheque", analyzed = false)
	@AConverter(propertyDist = "refTypeCheque")
	private TypeChequeVO refTypeCheque;

	@Indexation(label = "TypeVirement", analyzed = false)
	@AConverter(propertyDist = "refTypeVirement")
	private TypeVirementVO refTypeVirement;

	@Indexation(label = "idsIntermediaire", analyzed = false)
	@AConverter(propertyDist = "idsIntermediaire")
	private String idsIntermediaire;

	@Indexation(label = "codeVille", analyzed = false)
	@AConverter(propertyDist = "codeVille")
	private String codeVille;
	private String libelleVille;

	@Indexation(label = "CodePays", analyzed = false)
	@AConverter(propertyDist = "codePays")
	private String codePays;

	@AConverter(propertyDist = "nosReference")
	private String nosReference;
	
	// kch
	private String lblIntermediaiare; 

	private String typeParentA;
	private String typeParentB;
	
	//EVO QC  460
	private transient String dateCreationFin;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPourLeCompte() {
		return this.pourLeCompte;
	}

	public void setPourLeCompte(String pourLeCompte) {
		this.pourLeCompte = pourLeCompte;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getLblVirement() {
		return this.lblVirement;
	}

	public void setLblVirement(String lblVirement) {
		this.lblVirement = lblVirement;
	}

	public String getNumCIN() {
		return this.numCIN;
	}

	public void setNumCIN(String numCIN) {
		this.numCIN = numCIN;
	}

	public String getNumRIB() {
		return this.numRIB;
	}

	public void setNumRIB(String numRIB) {
		this.numRIB = numRIB;
	}

	public String getRefBordereau() {
		return this.refBordereau;
	}

	public void setRefBordereau(String refBordereau) {
		this.refBordereau = refBordereau;
	}

	public String getRefRglt() {
		return this.refRglt;
	}

	public void setRefRglt(String refRglt) {
		this.refRglt = refRglt;
	}

	public TypeChequeVO getRefTypeCheque() {
		return refTypeCheque;
	}

	public void setRefTypeCheque(TypeChequeVO refTypeCheque) {
		this.refTypeCheque = refTypeCheque;
	}

	public TypeVirementVO getRefTypeVirement() {
		return refTypeVirement;
	}

	public void setRefTypeVirement(TypeVirementVO refTypeVirement) {
		this.refTypeVirement = refTypeVirement;
	}

	public void setIdsIntermediaire(String idsIntermediaire) {
		this.idsIntermediaire = idsIntermediaire;
	}

	public String getIdsIntermediaire() {
		return idsIntermediaire;
	}

	public void setCodeVille(String codeVille) {
		this.codeVille = codeVille;
	}

	public String getCodeVille() {
		return codeVille;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public String getCodePays() {
		return codePays;
	}

	public void setNosReference(String nosReference) {
		this.nosReference = nosReference;
	}

	public String getNosReference() {
		return nosReference;
	}

	
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("pourLeCompte");
		listAttributes.add("adresse");
		listAttributes.add("details");
		listAttributes.add("lblVirement");
		listAttributes.add("numCIN");
		listAttributes.add("numRIB");
		listAttributes.add("refBordereau");
		listAttributes.add("refRglt");
		listAttributes.add("dateCreation");
		listAttributes.add("idsIntermediaire");
		listAttributes.add("CodeVille");
		listAttributes.add("CodePays");
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this, listAttributes);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return rslt;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getTypeParentA() {
		return typeParentA;
	}

	public void setTypeParentA(String typeParentA) {
		this.typeParentA = typeParentA;
		this.typeParentB = typeParentA;
	}

	public String getTypeParentB() {
		return typeParentB;
	}

	public void setTypeParentB(String typeParentB) {
		this.typeParentB = typeParentB;
	}
	
	

	public String getLblIntermediaiare() {
		return lblIntermediaiare;
	}

	public void setLblIntermediaiare(String lblIntermediaiare) {
		this.lblIntermediaiare = lblIntermediaiare;
	}
	
	

	public String getLibelleVille() {
		return libelleVille;
	}

	public void setLibelleVille(String libelleVille) {
		this.libelleVille = libelleVille;
	}
	

	public String getDateCreationFin() {
		return dateCreationFin;
	}

	public void setDateCreationFin(String dateCreationFin) {
		this.dateCreationFin = dateCreationFin;
	}

	public String toString() {
		return String.valueOf("id" + this.getId() + "::adresse::"
				+ this.getAdresse() + "::pour::" + this.getPourLeCompte()
				+ "::details::" + this.getDetails() + "::cin::"
				+ this.getNumCIN() + "::rib::" + this.getNumRIB() + "::lbl::"
				+ this.getLblVirement() + "::ref::" + this.getRefBordereau()
				+ "::rglt::" + this.getRefRglt() + "::typechq::"
				+ this.getRefTypeCheque() + "::typevrmt::"
				+ this.getRefTypeVirement() + "::intermediaire::"
				+ this.getIdsIntermediaire());
	}

}
