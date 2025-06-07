package eai.devass.gsr.appli.modele.metier.reglement;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;

import com.rmawatanya.reglement.application.metier.usecases.services.quittance.IEmissionQuittance;
import com.rmawatanya.reglement.application.metier.usecases.services.quittance.IVariableQuittance;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.manager.metier.reglement.QuittanceGsrFactory;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Heritier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.modele.parametrage.ModeReglement;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.TypeApprobation;
import eai.devass.gsr.appli.modele.parametrage.TypeQuittance;
import eai.devass.gsr.appli.modele.parametrage.TypeRgltGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.entites.IParam;

// ILockable 

/**
 * Quittance: Reglement;Reglement
 * 
 * @author Nom Prenom (email)
 */
/**
 * SuppressWarnings("unused"): champs avec valeurs fixes necessaires pour la
 * conversion au type quittance du service de quittancement
 */
@SuppressWarnings("unused")
@AConverter(entiteDist = "com.rmawatanya.reglement.application.metier.valueobjects.QuittanceRRVO", 
		entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO")
public class QuittanceGsr extends EntiteBO implements IEntite,Comparable<QuittanceGsr> {

	private long id;
	private static final long serialVersionUID = 1L;

	/**
	 * Quittance numeroQuittance
	 */
	@AConverter(propertyDist = "numeroQuittance")
	private String numeroQuittance;
	/**
	 * montant
	 */
	@AConverter(propertyDist = "valeurTresorerie")
	private Double montant;
	/**
	 * datEtat
	 */
	private Calendar datEtat;
	/**
	 * beneficiaire
	 */
	@AConverter(propertyDist = "nomBeneficiaire")
	private String beneficiaire;
	/**
	 * numeroRente
	 */
	private String numeroRente;
	/**
	 * datEmission
	 */
	@AConverter(propertyDist = "dateEmission", pattern = "dd/MM/yyyy HH:mm:ss")
	private Calendar datEmission;
	/**
	 * exercice
	 */
	private String exercice;
	/**
	 * dateCreation
	 */
	private Calendar dateCreation;

	private Rentier refRentier;
	private List<SituationQuittanceGsr> refSituationQuittanceGsr;
	/**
	 * type quittance
	 */
	@AConverter(propertyDist = "typeQuittance", propertyOrig = "refTypeQuittance.code")
	private TypeQuittance refTypeQuittance;

	@AConverter(propertyDist = "codeSousTypeQuittance", propertyOrig = "refNatureQuittance.id")
	private NatureQtcGsr refNatureQuittance;
	
	private String classe;
	private TypeApprobation refTypeApprobation;
	private Prerglt refPrerglt;
	private TypeRgltGsr refTypeReglement;
	private ModeReglement refModeReglement;
	private Boolean defautRemise;
	private String refReglement;
	private String numeroCheque;
	private Boolean impaye;
	private Mouvement refMouvement;
	private EtatQtc refEtatQtc;
	
	@Indexation(label = "dateDebut", analyzed = false)
	private Date dateDebutQtc;
	@Indexation(label = "dateFin", analyzed = false)
	private Date dateFinQtc;
	
	private List<Heritier> refsHeritier;
	
	private List<Prothese> refsProthese;
	/**
	 * @return the refsProthese
	 */
	public List<Prothese> getRefsProthese() {
		return refsProthese;
	}

	/**
	 * @param refsProthese the refsProthese to set
	 */
	public void setRefsProthese(List<Prothese> refsProthese) {
		this.refsProthese = refsProthese;
	}

	/**
	 * @return the refsHeritier
	 */
	public List<Heritier> getRefsHeritier() {
		return refsHeritier;
	}

	/**
	 * @param refsHeritier the refsHeritier to set
	 */
	public void setRefsHeritier(List<Heritier> refsHeritier) {
		this.refsHeritier = refsHeritier;
	}

	// Champs necessaire pour le mapping avec la quittance du service
	// Ces champs sont disponible dans refSinistre
	@AConverter(propertyDist = "numeroSinistre")
	private String numeroSinistre;

	@AConverter(propertyDist = "numeroContrat")
	private transient String numeroContrat;// refSinistre.numeroPolice

	@AConverter(propertyDist = "numeroClient")
	private transient String numeroClient;// refSinistre.codeClient

	@AConverter(propertyDist = "raisonSociale")
	private transient String raisonSociale;// refSinistre.nomClient
	// Champs a recuperer de cette façon

	@AConverter(propertyDist = "codeIntermediaire")
	private transient String codeIntermediaire;

	@AConverter(propertyDist = "typeIntermediaire")
	private transient String typeIntermediaire;

	private transient String codeSousTypeQuittance;

	@AConverter(propertyDist = "codeBranche")
	private transient String codeBranche;

	@AConverter(propertyDist = "codeCategorie")
	private transient String codeCategorie;

	@AConverter(propertyDist = "codeUtilisateur")
	private transient String codeUtilisateur;

	@AConverter(propertyDist = "codeServiceOrdonnateur")
	private transient String codeServiceOrdonnateur;

	@AConverter(propertyDist = "codeBeneficiaire")
	private transient String codeBeneficiaire;

	// Champs à valeurs fixes
	@AConverter(propertyDist = "codeCoassurance")
	private transient String codeCoassurance;

	@AConverter(propertyDist = "codeDevisePenalite")
	private transient String codeDevisePenalite;

	@AConverter(propertyDist = "codeDevisePrestation")
	private transient String codeDevisePrestation;

	@AConverter(propertyDist = "codeDeviseTaxe")
	private transient String codeDeviseTaxe;

	@AConverter(propertyDist = "codeSociete")
	private transient String codeSociete;

	@AConverter(propertyDist = "codeSocieteGestion")
	private transient String codeSocieteGestion;

	@AConverter(propertyDist = "typeBeneficiare")
	private transient String typeBeneficiare;// typeBeneficiare fixé à "0"??A
												// Verifier

	@AConverter(propertyDist = "codePays")
	private transient String codePays;

	@AConverter(propertyDist = "listDetailPrestationVO", customerConverter = "ConvertListDetailPrestation")
	private transient String codePrestation;

	@AConverter(propertyDist = "codeProduit")
	private transient String codeProduit;

	private transient String codeGarantie;
	private transient Reglement quittanceAT;
	private Date datePositionnement;
	
	// Besoin bacth
	private transient String datePositionnementSt;
	
	// Creation manuelle de la quittance
	private ComplementQuitatnce refComplementQuitatnce;
	
	// besoin heritier
	private transient Long ordre;
	


	public String toString() {
		return String.valueOf(getId());
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entitÃ©
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new QuittanceGsrFactory();
	}

	public long getId() {
		return id;
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

	public Double getMontant() {
		if(montant != null) {
			return new BigDecimal(montant).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
		}
		
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Calendar getDatEtat() {
		return this.datEtat;
	}

	public void setDatEtat(Calendar datEtat) {
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

	public Calendar getDatEmission() {
		return this.datEmission;
	}

	public void setDatEmission(Calendar datEmission) {
		this.datEmission = datEmission;
	}

	public String getExercice() {
		return this.exercice;
	}

	public void setExercice(String exercice) {
		this.exercice = exercice;
	}

	public Calendar getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Rentier getRefRentier() {
		return this.refRentier;
	}

	public void setRefRentier(Rentier refRentier) {
		this.refRentier = refRentier;
	}

	public List<SituationQuittanceGsr> getRefSituationQuittanceGsr() {
		return refSituationQuittanceGsr;
	}

	public void setRefSituationQuittanceGsr(
			List<SituationQuittanceGsr> refSituationQuittanceGsr) {
		this.refSituationQuittanceGsr = refSituationQuittanceGsr;
	}

	public Prerglt getRefPrerglt() {
		return this.refPrerglt;
	}

	public void setRefPrerglt(Prerglt refPrerglt) {
		this.refPrerglt = refPrerglt;
	}

	public void setRefTypeQuittance(TypeQuittance refTypeQtc) {
		this.refTypeQuittance = refTypeQtc;
	}

	public TypeQuittance getRefTypeQuittance() {
		return refTypeQuittance;
	}

	//
	public void setRefNatureQuittance(NatureQtcGsr refNatureQtc) {
		this.refNatureQuittance = refNatureQtc;
	}

	public NatureQtcGsr getRefNatureQuittance() {
		return refNatureQuittance;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getClasse() {
		return classe;
	}

	public void setRefTypeReglement(TypeRgltGsr refTypeReglement) {
		this.refTypeReglement = refTypeReglement;
	}

	public TypeRgltGsr getRefTypeReglement() {
		return refTypeReglement;
	}

	public void setRefModeReglement(ModeReglement refModeReglement) {
		this.refModeReglement = refModeReglement;
	}

	public ModeReglement getRefModeReglement() {
		return refModeReglement;
	}

	public void setRefTypeApprobation(TypeApprobation refTypeApprobation) {
		this.refTypeApprobation = refTypeApprobation;
	}

	public TypeApprobation getRefTypeApprobation() {
		return refTypeApprobation;
	}

	public String getNumeroSinistre() {
		return numeroSinistre;
	}

	public void setNumeroSinistre(String numeroSinistre) {
		this.numeroSinistre = numeroSinistre;
	}

	public String getNumeroContrat() {
		return numeroContrat;
	}

	public void setNumeroContrat(String numeroContrat) {
		this.numeroContrat = numeroContrat;
	}

	public String getNumeroClient() {
		return numeroClient;
	}

	public void setNumeroClient(String numeroClient) {
		this.numeroClient = numeroClient;
	}

	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public String getCodeIntermediaire() {
		return codeIntermediaire;
	}

	public void setCodeIntermediaire(String codeIntermediaire) {
		this.codeIntermediaire = codeIntermediaire;
	}

	public String getTypeIntermediaire() {
		return typeIntermediaire;
	}

	public void setTypeIntermediaire(String typeIntermediaire) {
		this.typeIntermediaire = typeIntermediaire;
	}

	public String getCodeSousTypeQuittance() {
		return codeSousTypeQuittance;
	}

	public void setCodeSousTypeQuittance(String codeSousTypeQuittance) {
		this.codeSousTypeQuittance = codeSousTypeQuittance;
	}

	public String getCodeBranche() {
		return codeBranche;
	}

	public void setCodeBranche(String codeBranche) {
		this.codeBranche = codeBranche;
	}

	public String getCodeCategorie() {
		return codeCategorie;
	}

	public void setCodeCategorie(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public String getCodeCoassurance() {
		return String.valueOf(IVariableQuittance.CONTRAT_AFFAIRE_100);
	}

	public void setCodeCoassurance(String codeCoassurance) {
		this.codeCoassurance = codeCoassurance;
	}

	public String getCodeDevisePenalite() {
		return IEmissionQuittance.CODE_DEVISE_MAD;
	}

	public void setCodeDevisePenalite(String codeDevisePenalite) {
		this.codeDevisePenalite = codeDevisePenalite;
	}

	public String getCodeDevisePrestation() {
		return IEmissionQuittance.CODE_DEVISE_MAD;
	}

	public void setCodeDevisePrestation(String codeDevisePrestation) {
		this.codeDevisePrestation = codeDevisePrestation;
	}

	public String getCodeDeviseTaxe() {
		return IEmissionQuittance.CODE_DEVISE_MAD;
	}

	public void setCodeDeviseTaxe(String codeDeviseTaxe) {
		this.codeDeviseTaxe = codeDeviseTaxe;
	}

	public String getCodeServiceOrdonnateur() {
		return codeServiceOrdonnateur;
	}

	public void setCodeServiceOrdonnateur(String codeServiceOrdonnateur) {
		this.codeServiceOrdonnateur = codeServiceOrdonnateur;
	}

	public String getCodeBeneficiaire() {
		return codeBeneficiaire;
	}

	public void setCodeBeneficiaire(String codeBeneficiaire) {
		this.codeBeneficiaire = codeBeneficiaire;
	}

	public String getCodeSociete() {
		return IParam.CODE_SOCIETE_AT;
	}

	public void setCodeSociete(String codeSociete) {
		this.codeSociete = codeSociete;
	}

	public String getCodeSocieteGestion() {
		return IParam.CODE_SOCIETE_GESTION_AT;
	}

	public void setCodeSocieteGestion(String codeSocieteGestion) {
		this.codeSocieteGestion = codeSocieteGestion;
	}

	public String getCodeUtilisateur() {
		return codeUtilisateur;
	}

	public void setCodeUtilisateur(String codeUtilisateur) {
		this.codeUtilisateur = codeUtilisateur;
	}

	public String getTypeBeneficiare() {
		return "0";
	}

	public void setTypeBeneficiare(String typeBeneficiare) {
		this.typeBeneficiare = typeBeneficiare;
	}

	public String getCodePays() {
		return IEmissionQuittance.CODE_PAYS_MA;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public Boolean getDefautRemise() {
		return defautRemise;
	}

	public void setDefautRemise(Boolean defautRemise) {
		this.defautRemise = defautRemise;
	}

	public String getRefReglement() {
		return refReglement;
	}

	public void setRefReglement(String refReglement) {
		this.refReglement = refReglement;
	}

	public Boolean getImpaye() {
		return impaye;
	}

	public void setImpaye(Boolean impaye) {
		this.impaye = impaye;
	}

	public Mouvement getRefMouvement() {
		return refMouvement;
	}

	public void setRefMouvement(Mouvement refMouvement) {
		this.refMouvement = refMouvement;
	}

	public String getCodeProduit() {
		return codeProduit;
	}

	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}

	public String getCodePrestation() {
		return codePrestation;
	}

	public void setCodePrestation(String codePrestation) {
		this.codePrestation = codePrestation;
	}

	public String getCodeGarantie() {
		return codeGarantie;
	}

	public void setCodeGarantie(String codeGarantie) {
		this.codeGarantie = codeGarantie;
	}

	public EtatQtc getRefEtatQtc() {
		return refEtatQtc;
	}

	public void setRefEtatQtc(EtatQtc refEtatQtc) {
		this.refEtatQtc = refEtatQtc;
	}

	public Reglement getQuittanceAT() {
		return quittanceAT;
	}

	public void setQuittanceAT(Reglement quittanceAT) {
		this.quittanceAT = quittanceAT;
	}
		
	public Date getDatePositionnement() {
		return datePositionnement;
	}

	public void setDatePositionnement(Date datePositionnement) {
		this.datePositionnement = datePositionnement;
	}
	
	

	public String getDatePositionnementSt() {
		return datePositionnementSt;
	}

	public void setDatePositionnementSt(String datePositionnementSt) {
		this.datePositionnementSt = datePositionnementSt;
	}
	
	public ComplementQuitatnce getRefComplementQuitatnce() {
		return refComplementQuitatnce;
	}

	public void setRefComplementQuitatnce(ComplementQuitatnce refComplementQuitatnce) {
		this.refComplementQuitatnce = refComplementQuitatnce;
	}
	
	public Date getDateDebutQtc() {
		return dateDebutQtc;
	}

	public void setDateDebutQtc(Date dateDebutQtc) {
		this.dateDebutQtc = dateDebutQtc;
	}

	public Date getDateFinQtc() {
		return dateFinQtc;
	}

	public void setDateFinQtc(Date dateFinQtc) {
		this.dateFinQtc = dateFinQtc;
	}

	public SituationQuittanceGsr getCurSituationQuittanceGsr(EtatQuittance etatQuittance) {
		EtatQtc etatQtc = new EtatQtc(etatQuittance.getId());
		etatQtc.setDateCreation(new GregorianCalendar());
		this.setRefEtatQtc(etatQtc);
		this.setDatEtat(new GregorianCalendar());
		SituationQuittanceGsr situationQuittanceGsr = new SituationQuittanceGsr();
		situationQuittanceGsr.setRefEtatQtc(etatQtc);
		situationQuittanceGsr.setDateEtat(new Date());
		situationQuittanceGsr.setRefQuittanceGsr(this);
		return situationQuittanceGsr;
	}
	
	public NatureQuittance getNatureQuittanceEquilibreAT() {
		if(refNatureQuittance == null || refNatureQuittance.getId() == 0) {
			return null;
		}
		
		if (NatureQuittance.Diminution_Capital_constitutif.getId() == refNatureQuittance
				.getId()) {
			return NatureQuittance.Diminution_Capital_constitutif_AT;
			
		} else if (NatureQuittance.Augmentation_Capital_constitutif.getId() == refNatureQuittance
				.getId()) {
			return NatureQuittance.Augmentation_Capital_constitutif_AT;
			
		} else if (NatureQuittance.Virement_Capital_constitutif.getId() == refNatureQuittance
				.getId()) {
			return NatureQuittance.Virement_Capital_constitutif_AT;
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		boolean resultat = false;
		if(obj instanceof QuittanceGsr){
			QuittanceGsr qtc = (QuittanceGsr)obj;
			if(qtc.getId()==this.id){	
				resultat = true;
			}
		}
		return resultat;
	}

	public int compareTo(QuittanceGsr compareQtc) {
		 
		//ascending order
		return (int)(this.id - compareQtc.getId());

	}	
	
	/**
	 * @return the ordre
	 */
	public Long getOrdre() {
		return ordre;
	}

	/**
	 * @param ordre the ordre to set
	 */
	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	
}
