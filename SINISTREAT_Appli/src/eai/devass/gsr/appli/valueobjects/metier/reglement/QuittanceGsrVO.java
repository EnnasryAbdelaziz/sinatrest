package eai.devass.gsr.appli.valueobjects.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.commun.appli.modele.EntiteVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.ProtheseVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.HeritierVO;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatQtcVO;
import eai.devass.gsr.appli.valueobjects.parametrage.ModeReglementVO;
import eai.devass.gsr.appli.valueobjects.parametrage.NatureQtcGsrVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeApprobationVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMouvementVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeQuittanceVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeRgltGsrVO;

/**
 * Value Object de Quittance
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr")
public class QuittanceGsrVO extends EntiteVO implements ITracable {

	private Logger logger = Logger.getLogger("loggerSINAT");
	@AConverter(propertyDist = "id")
	private long id;
	private static final long serialVersionUID = 1L;

	@Indexation(label = "N° Quittance", analyzed = false)
	@AConverter(propertyDist = "numeroQuittance")
	private String numeroQuittance;

	@Indexation(label = "montant", analyzed = false)
	@AConverter(propertyDist = "montant")
	private String montant;

	@Indexation(label = "datEtat", analyzed = false)
	@AConverter(propertyDist = "datEtat", pattern = "yyyyMMdd")
	private String datEtat;

	@Indexation(label = "beneficiaire", analyzed = false)
	@AConverter(propertyDist = "beneficiaire")
	private String beneficiaire;

	@Indexation(label = "numeroRente", analyzed = false)
	@AConverter(propertyDist = "numeroRente")
	private String numeroRente;

	@Indexation(label = "datEmission", analyzed = false)
	@AConverter(propertyDist = "datEmission", pattern = "yyyyMMdd")
	private String datEmission;

	@Indexation(label = "exercice", analyzed = false)
	@AConverter(propertyDist = "exercice")
	private String exercice;

	@Indexation(label = "dateCreation", analyzed = false)
	@AConverter(propertyDist = "dateCreation", pattern = "yyyyMMdd")
	private String dateCreation;

	@Indexation(label = "classe", analyzed = false)
	@AConverter(propertyDist = "refMouvement.id")
	private Long idMouvement;

	@AConverter(propertyDist = "classe")
	private String classe;

	@AConverter(propertyDist = "refRentier.id")
	private String refRentier;

	@Indexation(label = "EtatQtc", analyzed = false)
	@AConverter(propertyDist = "refEtatQtc")
	private EtatQtcVO refEtatQtc;

	@Indexation(label = "TypeQuittance", analyzed = false)
	@AConverter(propertyDist = "refTypeQuittance")
	private TypeQuittanceVO refTypeQtc;

	@Indexation(label = "NatureQuittance", analyzed = false)
	@AConverter(propertyDist = "refNatureQuittance")
	private NatureQtcGsrVO refNatureQuittance;

	@Indexation(label = "TypeApprobation", analyzed = false)
	@AConverter(propertyDist = "refTypeApprobation")
	private TypeApprobationVO refTypeApprobation;

	@Indexation(label = "refPrerglt", analyzed = false)
	@AConverter(propertyDist = "refPrerglt")
	private PrergltVO refPrerglt;

	@Indexation(label = "TypeReglement", analyzed = false)
	@AConverter(propertyDist = "refTypeReglement")
	private TypeRgltGsrVO refTypeReglement;

	@Indexation(label = "ModeReglement", analyzed = false)
	@AConverter(propertyDist = "refModeReglement")
	private ModeReglementVO refModeReglement;
	
	@AConverter(propertyDist="refsHeritier")
	private List<HeritierVO> refsHeritier ;
	
	// kchakib
	@AConverter(propertyDist="numeroSinistre")
	private String numSinistre;
	
	private String codeSociete;
	private String trimestreExercice;
	
	@AConverter(propertyDist="refsProthese")
	private List<ProtheseVO> refsProthese ;
	
	@AConverter(propertyDist="datePositionnement")
	private String datePositionnement;
	
	private String dateEmissionQuittanceAu;
	private String utilisateur;
	private String isModifiable = "false";
	private String isCreationManuel = "false";
	private String isAnnualable = "false";
	
	@AConverter(propertyDist = "refRentier.nomComplet")
	private String nomRentier;
	
	@AConverter(propertyDist = "refMouvement.refTypeMouvement")
	private TypeMouvementVO typeMouvementVO;
	
	@AConverter(propertyDist = "refReglement")
	private String refReglement;
	@AConverter(propertyDist = "numeroCheque")
	private String numeroCheque;
	private List<SituationEtatVO> listSituationEtatVO;
	private String libelleSociete;
	
	//CK : EVO, pour la craetion menuelle
	@Indexation(label = "dateDebutQtc", analyzed = false)
	@AConverter(propertyDist = "dateDebutQtc", pattern = "yyyyMMdd")
	private String dateDebut;
	@Indexation(label = "dateFinQtc", analyzed = false)
	@AConverter(propertyDist = "dateFinQtc", pattern = "yyyyMMdd")
	private String dateFin;
	private String codeMotifCreation;
	private String montantTaxeCommission;
	private String detailMotif;
	private String idComplementQuittance;
	private String conversionLight;
	
	@AConverter(propertyDist = "ordre")
	private String idHer;
	
	// EVO : ck 03122014
	private String etatRentier;
	private String etatRentierNotIn;
	
	//Evo prod 25: wmos 13012015
	private String etatQtcNotIn;
	
	/**
	 * @return the refsHeritier
	 */
	public List<HeritierVO> getRefsHeritier() {
		return refsHeritier;
	}

	/**
	 * @return the refsProthese
	 */
	public List<ProtheseVO> getRefsProthese() {
		return refsProthese;
	}

	/**
     * @param refsProthese
     *            the refsProthese to set
	 */
	public void setRefsProthese(List<ProtheseVO> refsProthese) {
		this.refsProthese = refsProthese;
	}

	/**
     * @param refsHeritier
     *            the refsHeritier to set
	 */
	public void setRefsHeritier(List<HeritierVO> refsHeritier) {
		this.refsHeritier = refsHeritier;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumeroQuittance() {
		return this.numeroQuittance;
	}

	public void setNumeroQuittance(String numeroQuittance) {
		this.numeroQuittance = numeroQuittance;
	}

	public String getMontant() {
		return this.montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

	public String getDatEtat() {
		return this.datEtat;
	}

	public void setDatEtat(String datEtat) {
		this.datEtat = datEtat;
	}

	public String getBeneficiaire() {
		return this.beneficiaire;
	}

	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}

	public String getNumeroRente() {
		return this.numeroRente;
	}

	public void setNumeroRente(String numeroRente) {
		this.numeroRente = numeroRente;
	}

	public String getDatEmission() {
		return this.datEmission;
	}

	public void setDatEmission(String datEmission) {
		this.datEmission = datEmission;
	}

	public String getExercice() {
		return this.exercice;
	}

	public void setExercice(String exercice) {
		this.exercice = exercice;
	}

	public String getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getRefRentier() {
		return this.refRentier;
	}

	public void setRefRentier(String refRentier) {
		this.refRentier = refRentier;
	}

	public EtatQtcVO getRefEtatQtc() {
		return this.refEtatQtc;
	}

	public void setRefEtatQtc(EtatQtcVO refEtatQtc) {
		this.refEtatQtc = refEtatQtc;
	}

	public String getClasse() {
		return this.classe;
	}

	public void setClasse(String refDegreParente) {
		this.classe = refDegreParente;
	}

	public void setRefTypeQtc(TypeQuittanceVO refTypeQtc) {
		this.refTypeQtc = refTypeQtc;
	}

	public TypeQuittanceVO getRefTypeQtc() {
		return refTypeQtc;
	}

	//
	public void setRefNatureQuittance(NatureQtcGsrVO refNatureQtc) {
		this.refNatureQuittance = refNatureQtc;
	}

	public NatureQtcGsrVO getRefNatureQuittance() {
		return refNatureQuittance;
	}

	public void setRefTypeReglement(TypeRgltGsrVO refTypeReglement) {
		this.refTypeReglement = refTypeReglement;
	}

	public TypeRgltGsrVO getRefTypeReglement() {
		return refTypeReglement;
	}

	public void setRefModeReglement(ModeReglementVO refModeReglement) {
		this.refModeReglement = refModeReglement;
	}

	public ModeReglementVO getRefModeReglement() {
		return refModeReglement;
	}

	public void setRefTypeApprobation(TypeApprobationVO refTypeApprobation) {
		this.refTypeApprobation = refTypeApprobation;
	}

	public TypeApprobationVO getRefTypeApprobation() {
		return refTypeApprobation;
	}

	public void setRefPrerglt(PrergltVO refPrerglt) {
		this.refPrerglt = refPrerglt;
	}

	public PrergltVO getRefPrerglt() {
		return refPrerglt;
	}
	
	public String getDatePositionnement() {
		return datePositionnement;
	}

	public void setDatePositionnement(String datePositionnement) {
		this.datePositionnement = datePositionnement;
	}

	public String toString() {
        return "id" + this.getId() + "::" + this.getBeneficiaire() + "::"
                + this.getMontant() + "::" + this.getExercice() + "::"
                + this.getNumeroRente() + "::" + this.getClasse() + "::"
                + this.getDatEtat();
	}

	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("numeroQuittance");
		listAttributes.add("montant");
		listAttributes.add("datEtat");
		listAttributes.add("beneficiaire");
		listAttributes.add("numeroRente");
		listAttributes.add("datEmission");
		listAttributes.add("exercice");
		listAttributes.add("dateCreation");
		listAttributes.add("classe");
		String rslt = "";
		
		try {
			rslt = traceAtt.getStringTraceInfo(this, listAttributes);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return rslt;
	}

	public Long getIdMouvement() {
		return idMouvement;
	}

	public void setIdMouvement(Long idMouvement) {
		this.idMouvement = idMouvement;
	}

	public String getNumSinistre() {
		return numSinistre;
	}

	public void setNumSinistre(String numSinistre) {
		this.numSinistre = numSinistre;
	}

	public String getCodeSociete() {
		return codeSociete;
	}

	public void setCodeSociete(String codeSociete) {
		this.codeSociete = codeSociete;
	}

	public String getTrimestreExercice() {
		return trimestreExercice;
	}

	public void setTrimestreExercice(String trimestreExercice) {
		this.trimestreExercice = trimestreExercice;
	}

	public TypeMouvementVO getTypeMouvementVO() {
		return typeMouvementVO;
	}

	public void setTypeMouvementVO(TypeMouvementVO typeMouvementVO) {
		this.typeMouvementVO = typeMouvementVO;
	}

	public String getDateEmissionQuittanceAu() {
		return dateEmissionQuittanceAu;
	}

	public void setDateEmissionQuittanceAu(String dateEmissionQuittanceAu) {
		this.dateEmissionQuittanceAu = dateEmissionQuittanceAu;
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getRefReglement() {
		return refReglement;
	}

	public void setRefReglement(String refReglement) {
		this.refReglement = refReglement;
	}

	public List<SituationEtatVO> getListSituationEtatVO() {
		return listSituationEtatVO;
	}

	public void setListSituationEtatVO(List<SituationEtatVO> listSituationEtatVO) {
		this.listSituationEtatVO = listSituationEtatVO;
	}

	public String getIsModifiable() {
		return isModifiable;
	}

	public void setIsModifiable(String isModifiable) {
		this.isModifiable = isModifiable;
	}

	public String getNomRentier() {
		return nomRentier;
	}

	public void setNomRentier(String nomRentier) {
		this.nomRentier = nomRentier;
	}

	public String getLibelleSociete() {
		return libelleSociete;
	}

	public void setLibelleSociete(String libelleSociete) {
		this.libelleSociete = libelleSociete;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getCodeMotifCreation() {
		return codeMotifCreation;
	}

	public void setCodeMotifCreation(String codeMotifCreation) {
		this.codeMotifCreation = codeMotifCreation;
	}

	public String getMontantTaxeCommission() {
		return montantTaxeCommission;
	}

	public void setMontantTaxeCommission(String montantTaxeCommission) {
		this.montantTaxeCommission = montantTaxeCommission;
	}

	public String getDetailMotif() {
		return detailMotif;
	}

	public void setDetailMotif(String detailMotif) {
		this.detailMotif = detailMotif;
	}

	public String getIsCreationManuel() {
		return isCreationManuel;
	}

	public void setIsCreationManuel(String isCreationManuel) {
		this.isCreationManuel = isCreationManuel;
	}

	public String getIdComplementQuittance() {
		return idComplementQuittance;
	}

	public void setIdComplementQuittance(String idComplementQuittance) {
		this.idComplementQuittance = idComplementQuittance;
	}

	public String getConversionLight() {
		return conversionLight;
	}

	public void setConversionLight(String conversionLight) {
		this.conversionLight = conversionLight;
	}

	public String getIsAnnualable() {
		return isAnnualable;
	}

	public void setIsAnnualable(String isAnnualable) {
		this.isAnnualable = isAnnualable;
	}

	/**
	 * @return the idHer
	 */
	public String getIdHer() {
		return idHer;
	}

	/**
     * @param idHer
     *            the idHer to set
	 */
	public void setIdHer(String idHer) {
		this.idHer = idHer;
	}

	public String getEtatRentier() {
		return etatRentier;
	}

	public void setEtatRentier(String etatRentier) {
		this.etatRentier = etatRentier;
	}

	public String getEtatRentierNotIn() {
		return etatRentierNotIn;
	}

	public void setEtatRentierNotIn(String etatRentierNotIn) {
		this.etatRentierNotIn = etatRentierNotIn;
	}

	public void setEtatQtcNotIn(String etatQtcNotIn) {
		this.etatQtcNotIn = etatQtcNotIn;
	}

	public String getEtatQtcNotIn() {
		return etatQtcNotIn;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
}
