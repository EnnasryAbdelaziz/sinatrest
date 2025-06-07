package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.commun.appli.modele.EntiteVO;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;

/**
 * Value Object de Mouvement
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.mouvements.Mouvement")
public class MouvementVO extends EntiteVO {

	@AConverter(propertyDist = "id")
	private long id;
	private int pageSize;
	private static final long serialVersionUID = 1L;
	protected Logger logger = Logger.getLogger("loggerSINAT");
	
	
	/**
	 * organisme judiciaire
	 * élements dossier judiciaire
	 */	
	@AConverter(propertyDist="organismeJudiciaire")
	private String organismeJudiciaire;


	/**
	 * date décision judiciaire
	 * élements dossier judiciaire
	 */
	@Indexation(label = "dateDecision", analyzed = false)
	@AConverter(propertyDist = "dateDecision", pattern = "yyyyMMdd")
	private String dateDecision;
	
	/**
	 * numéro dossier tribunal
	 * élements dossier judiciaire
	 */
	@Indexation(label = "refJudiciaire", analyzed = false)
	@AConverter(propertyDist = "refJudiciaire")
	private String refJudiciaire;
	

	@Indexation(label = "typeFille", analyzed = false)
	@AConverter(propertyDist = "typeFille", customerConverter = "ConvertTypeFileMouvement")
	private String typeFille;

	@Indexation(label = "capitalCalcule", analyzed = false)
	@AConverter(propertyDist = "capitalCalcule")
	private String capitalCalcule;

	@Indexation(label = "refRecours", analyzed = false)
	@AConverter(propertyDist = "refRecours")
	private String refRecours;


	
	@Indexation(label = "datEtat", analyzed = false)
	@AConverter(propertyDist = "datEtat", pattern = "yyyyMMdd")
	private String datEtat;

	@Indexation(label = "mntRente", analyzed = false)
	@AConverter(propertyDist = "mntRente")
	private String mntRente;

	@Indexation(label = "numOrder", analyzed = false)
	@AConverter(propertyDist = "numOrder")
	private String numOrder;



	@Indexation(label = "echeanceEffective", analyzed = false)
	@AConverter(propertyDist = "echeanceEffective", pattern = "yyyyMMdd")
	private String echeanceEffective;

	@AConverter(propertyDist = "refRentier.id")
	@Indexation(label = "Rentier", analyzed = false)
	private String refRentier;

	@AConverter(propertyDist = "refRentier.nom")
	private String refRentierLabel;

	@Indexation(label = "EtatMvt", analyzed = false)
	@AConverter(propertyDist = "refEtatMvt.id")
	private String refEtatMvt;

	@Indexation(label = "TypeMouvement", analyzed = false)
	@AConverter(propertyDist = "refTypeMouvement.id")
	private String refTypeMouvement;

	@AConverter(propertyDist = "refTypeMouvement.libelle")
	private String refTypeMouvementLabel;

	@AConverter(propertyDist = "refEtatMvt.libelle")
	private String refEtatMvtLabel;

	@AConverter(propertyDist = "refsQuittance")
	private List<QuittanceGsrVO> refsQuittance;

	private String typeParentA;
	private String typeParentB;

	@AConverter(propertyDist = "dateCreation", pattern = "yyyyMMdd")
	private String dateCreation;

	@AConverter(propertyDist = "contextRegleGestion")
	private String contextRegleGestion;

	@AConverter(propertyDist = "genererQuittance")
	private String genererQuittance;

	@AConverter(propertyDist = "emissionQuittance")
	private String emissionQuittance;

	@AConverter(propertyDist = "executeOnce")
	private String executeOnce;
	
	@Indexation(label = "sommeMntQtcEquilibre", analyzed = false)
	@AConverter(propertyDist = "sommeMntQtcEquilibre")
	private String sommeMntQtcEquilibre;
	//Utilisé dans le cas de modification après validation.
	private String modifiable;
	
	
	
	/**
	 * @return the executeOnce
	 */
	public String getExecuteOnce() {
		return executeOnce;
	}

	/**
	 * @param executeOnce the executeOnce to set
	 */
	public void setExecuteOnce(String executeOnce) {
		this.executeOnce = executeOnce;
	}

	@AConverter(propertyDist = "refSituationMouvement", customerConverter = "ConvertListSituationMouvement")
	private String codeSituationMotif;
	@AConverter(propertyDist = "observation")
	private String observation;
	@AConverter(propertyDist = "nouvIPP")
	private String nouvIPP;
	@AConverter(propertyDist = "nouvMntRente")
	private String nouvMntRente;
	@AConverter(propertyDist = "arreragesRente")
	private String arreragesRente;
	@AConverter(propertyDist = "refAudience", customerConverter = "ConvertAudience")
	private AudienceJugementVO refAudience;

	
	@Indexation(label="TypeMvtProthese",analyzed=false)  
	@AConverter(propertyDist="refTypeMvtProthese.id")
	private String refTypeMvtProthese ;
	@AConverter(propertyDist="refTypeMvtProthese.libelle")
	private String refTypeMvtProtheseLabel ;
	

	public long getId() {
		return this.id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getTypeFille() {
		return this.typeFille;
	}

	public void setTypeFille(String typeFille) {
		this.typeFille = typeFille;
	}

	public String getCapitalCalcule() {
		return this.capitalCalcule;
	}

	public void setCapitalCalcule(String capitalCalcule) {
		this.capitalCalcule = capitalCalcule;
	}

	public String getRefRecours() {
		return this.refRecours;
	}

	public void setRefRecours(String refRecours) {
		this.refRecours = refRecours;
	}

	public String getDatEtat() {
		return this.datEtat;
	}

	public void setDatEtat(String datEtat) {
		this.datEtat = datEtat;
	}

	public String getDateDecision() {
		return dateDecision;
	}

	public void setDateDecision(String dateDecision) {
		this.dateDecision = dateDecision;
	}

	public String getMntRente() {
		return this.mntRente;
	}

	public void setMntRente(String mntRente) {
		this.mntRente = mntRente;
	}

	public String getNumOrder() {
		return this.numOrder;
	}

	public void setNumOrder(String numOrder) {
		this.numOrder = numOrder;
	}

	public String getRefJudiciaire() {
		return this.refJudiciaire;
	}

	public void setRefJudiciaire(String refJudiciaire) {
		this.refJudiciaire = refJudiciaire;
	}

	/**
	 * @return the organismeJudiciaire
	 */
	public String getOrganismeJudiciaire() {
		return organismeJudiciaire;
	}

	/**
	 * @param organismeJudiciaire the organismeJudiciaire to set
	 */
	public void setOrganismeJudiciaire(String organismeJudiciaire) {
		this.organismeJudiciaire = organismeJudiciaire;
	}

	public String getEcheanceEffective() {
		return this.echeanceEffective;
	}

	public void setEcheanceEffective(String echeanceEffective) {
		this.echeanceEffective = echeanceEffective;
	}

	public String getRefRentier() {
		return this.refRentier;
	}

	public void setRefRentier(String refRentier) {
		this.refRentier = refRentier;
	}

	public String getRefRentierLabel() {
		return this.refRentierLabel;
	}

	public void setRefRentierLabel(String refRentierLabel) {
		this.refRentierLabel = refRentierLabel;
	}

	public String getRefEtatMvt() {
		return this.refEtatMvt;
	}

	public void setRefEtatMvt(String refEtatMvt) {
		this.refEtatMvt = refEtatMvt;
	}

	public String getRefEtatMvtLabel() {
		return this.refEtatMvtLabel;
	}

	public void setRefEtatMvtLabel(String refEtatMvtLabel) {
		this.refEtatMvtLabel = refEtatMvtLabel;
	}

	public String getRefTypeMouvement() {
		return this.refTypeMouvement;
	}

	public void setRefTypeMouvement(String refTypeMouvement) {
		this.refTypeMouvement = refTypeMouvement;
	}

	public String getRefTypeMouvementLabel() {
		return this.refTypeMouvementLabel;
	}

	public void setRefTypeMouvementLabel(String refTypeMouvementLabel) {
		this.refTypeMouvementLabel = refTypeMouvementLabel;
	}

	public List<QuittanceGsrVO> getRefsQuittance() {
		return this.refsQuittance;
	}

	public void setRefsQuittance(List<QuittanceGsrVO> refsQuittance) {
		this.refsQuittance = refsQuittance;
	}

	public String getContextRegleGestion() {
		return contextRegleGestion;
	}

	public void setContextRegleGestion(String contextRegleGestion) {
		this.contextRegleGestion = contextRegleGestion;
	}

	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("capitalCalcule");
		listAttributes.add("refRecours");
		listAttributes.add("datEtat");
		listAttributes.add("mntRente");
		listAttributes.add("numOrder");
		listAttributes.add("refJudiciaire");
		listAttributes.add("echeanceEffective");
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this, listAttributes);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return rslt;
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

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getGenererQuittance() {
		return genererQuittance;
	}

	public void setGenererQuittance(String genererQuittance) {
		this.genererQuittance = genererQuittance;
	}

	public String getEmissionQuittance() {
		return emissionQuittance;
	}

	public void setEmissionQuittance(String emissionQuittance) {
		this.emissionQuittance = emissionQuittance;
	}

	public String getCodeSituationMotif() {
		return codeSituationMotif;
	}

	public void setCodeSituationMotif(String codeSituationMotif) {
		this.codeSituationMotif = codeSituationMotif;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getObservation() {
		return observation;
	}

	public String getNouvMntRente() {
		return this.nouvMntRente;
	}

	public void setNouvMntRente(String nouvMntRente) {
		this.nouvMntRente = nouvMntRente;
	}

	public String getNouvIPP() {
		return this.nouvIPP;
	}

	public void setNouvIPP(String nouvIPP) {
		this.nouvIPP = nouvIPP;
	}
	public String getArreragesRente() {
		return arreragesRente;
	}
	public void setArreragesRente(String arreragesRente) {
		this.arreragesRente = arreragesRente;
	}

	public void setRefAudience(AudienceJugementVO refAudience) {
		this.refAudience = refAudience;
	}

	public AudienceJugementVO getRefAudience() {
		return refAudience;
	}
	
	/**
	 * @return the refTypeMvtProthese
	 */
	public String getRefTypeMvtProthese() {
		return refTypeMvtProthese;
	}

	/**
	 * @param refTypeMvtProthese the refTypeMvtProthese to set
	 */
	public void setRefTypeMvtProthese(String refTypeMvtProthese) {
		this.refTypeMvtProthese = refTypeMvtProthese;
	}

	/**
	 * @return the refTypeMvtProtheseLabel
	 */
	public String getRefTypeMvtProtheseLabel() {
		return refTypeMvtProtheseLabel;
	}

	/**
	 * @param refTypeMvtProtheseLabel the refTypeMvtProtheseLabel to set
	 */
	public void setRefTypeMvtProtheseLabel(String refTypeMvtProtheseLabel) {
		this.refTypeMvtProtheseLabel = refTypeMvtProtheseLabel;
	}
	
	/**
	 * @return the modifiable
	 */
	public String getModifiable() {
		return modifiable;
	}

	/**
	 * @param modifiable the modifiable to set
	 */
	public void setModifiable(String modifiable) {
		this.modifiable = modifiable;
	}

	public void setSommeMntQtcEquilibre(String sommeMntQtcEquilibre) {
		this.sommeMntQtcEquilibre = sommeMntQtcEquilibre;
	}

	public String getSommeMntQtcEquilibre() {
		return sommeMntQtcEquilibre;
	}
}
