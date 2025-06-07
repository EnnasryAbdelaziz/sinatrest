package eai.devass.gsr.appli.modele.metier.mouvements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.commun.appli.historisation.ALazy;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.modele.IHistorisable;
import eai.devass.gsr.appli.manager.metier.mouvements.MouvementFactory;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.MotifSituationEtat;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.modele.parametrage.TypeMouvement;
import eai.devass.gsr.appli.modele.parametrage.TypeMvtProthese;
import eai.devass.gsr.appli.prm.EtatMouvement;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;

/**
 * Mouvement:
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVO")
public class Mouvement extends EntiteBO implements IEntite, IHistorisable {

	private long id;
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * organisme judiciaire
	 */	
	private String organismeJudiciaire;

	/**
	 * dateDecision
	 */
	@Indexation(label = "dateDecision", analyzed = false)
	private Calendar dateDecision;
	
	/**
	 * refJudiciaire
	 */
	@Indexation(label = "refJudiciaire", analyzed = false)
	private String refJudiciaire;

	/**
	 * capitalCalcule
	 */
	@Indexation(label = "capitalCalcule", analyzed = false)
	private Double capitalCalcule;
	/**
	 * refRecours
	 */
	@Indexation(label = "refRecours", analyzed = false)
	private String refRecours;
	/**
	 * datEtat
	 */
	@Indexation(label = "datEtat", analyzed = false)
	private Calendar datEtat;

	/**
	 * mntRente
	 */
	@Indexation(label = "mntRente", analyzed = false)
	private Double mntRente;
	/**
	 * numOrder
	 */
	@Indexation(label = "numOrder", analyzed = false)
	private Integer numOrder;
	/**
	 * echeanceEffective
	 */
	@Indexation(label = "echeanceEffective", analyzed = false)
	private Calendar echeanceEffective;

	@ALazy(lazy=false)
	private Rentier refRentier;
	@ALazy(lazy=false)
	private EtatMvt refEtatMvt;
	private TypeMouvement refTypeMouvement;

	@Indexation(label = "sommeMntQtcEquilibre", analyzed = false)
	private  transient Double sommeMntQtcEquilibre;
	/**
	 *Pour l'affichage du sous type Mouvement
	 */
	private TypeMvtProthese refTypeMvtProthese;

	/**
	 * @return the refTypeMvtProthese
	 */
	public TypeMvtProthese getRefTypeMvtProthese() {
		return refTypeMvtProthese;
	}

	/**
	 * @param refTypeMvtProthese the refTypeMvtProthese to set
	 */
	public void setRefTypeMvtProthese(TypeMvtProthese refTypeMvtProthese) {
		this.refTypeMvtProthese = refTypeMvtProthese;
	}
	@ALazy(lazy=false)
	private List<QuittanceGsr> refsQuittance;
	private List<SituationMouvement> refSituationMouvement;
	/** AZAS **/


	private Calendar dateCreation;
	
	private transient QuittanceGsr quittanceGsr;
	private transient Boolean genererQuittance;
	private transient Boolean emissionQuittance;
	/**
	 * Boolean : attribut pour usage purement technique
	 */
	private transient Boolean executeOnce;	
	private transient MotifSituationEtat motifSituationEtat;
	private String observation;
	private transient List<QuittanceGsr> listQtcEquilibre = null;
	private transient Calendar dateCalculRedistribution;
	private transient Double mntTropPercu;
	
	//Mvt complement CNRA et Rachat
	private AudienceJugement refAudience;
	/**
	 * nouvIPP
	 */
	@Indexation(label = "nouvIPP", analyzed = false)
	private Double nouvIPP;
	/**
	 * nouvMntRente
	 */
	@Indexation(label = "nouvMntRente", analyzed = false)
	private Double nouvMntRente;
	/**
	 * Arrérages Rente
	 */
	@Indexation(label="arreragesRente",analyzed=false)
	private Double arreragesRente;

	/**
	 * mntDiminutionRsvGrave
	 */
	private transient Double mntDiminutionRsvGrave;
	
	/**
	 * Methode qui retourne l' instance de la factory d'une entitÃ©
	 * 
	 * @returns L' entite Factory
	 */
	
	
	public EntiteFactory getFactory() {
		return new MouvementFactory();
	}

	public Mouvement() {
		
	}

	public Mouvement(Long identifiant) {
		this.id = identifiant;
	
	}

	/**
	 * @return the executeOnce
	 */
	public Boolean getExecuteOnce() {
		return executeOnce;
	}

	/**
	 * @param executeOnce the executeOnce to set
	 */
	public void setExecuteOnce(Boolean executeOnce) {
		this.executeOnce = executeOnce;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	
	public Double getCapitalCalcule() {
		return this.capitalCalcule;
	}

	public void setCapitalCalcule(Double capitalCalcule) {
		this.capitalCalcule = capitalCalcule;
	}

	public String getRefRecours() {
		return this.refRecours;
	}

	public void setRefRecours(String refRecours) {
		this.refRecours = refRecours;
	}

	public Calendar getDatEtat() {
		return this.datEtat;
	}

	public void setDatEtat(Calendar datEtat) {
		this.datEtat = datEtat;
	}

	public Calendar getDateDecision() {
		return dateDecision;
	}

	public void setDateDecision(Calendar dateDecision) {
		this.dateDecision = dateDecision;
	}

	public Double getMntRente() {
		return this.mntRente;
	}

	public void setMntRente(Double mntRente) {
		this.mntRente = mntRente;
	}

	public Integer getNumOrder() {
		return this.numOrder;
	}

	public void setNumOrder(Integer numOrder) {
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

	public Calendar getEcheanceEffective() {
		return this.echeanceEffective;
	}

	public void setEcheanceEffective(Calendar echeanceEffective) {
		this.echeanceEffective = echeanceEffective;
	}

	public Rentier getRefRentier() {
		return this.refRentier;
	}

	public void setRefRentier(Rentier refRentier) {
		this.refRentier = refRentier;
	}

	public EtatMvt getRefEtatMvt() {
		return this.refEtatMvt;
	}

	public void setRefEtatMvt(EtatMvt refEtatMvt) {
		this.refEtatMvt = refEtatMvt;
	}

	public TypeMouvement getRefTypeMouvement() {
		return this.refTypeMouvement;
	}

	public void setRefTypeMouvement(TypeMouvement refTypeMouvement) {
		this.refTypeMouvement = refTypeMouvement;
	}

	public List<QuittanceGsr> getRefsQuittance() {
		return this.refsQuittance;
	}

	public void setRefsQuittance(List refsQuittance) {
		this.refsQuittance = refsQuittance;
	}

	/**
	 * Methode qui retourne l' Id du lockable
	 * 
	 * @returns id du locakble
	 */
	public String getIdLockable() {
		return Long.toString(getId());
	}

	/**
	 * Methode qui retourne le type du lockable
	 * 
	 * @returns type du locakble
	 */
	public String getLockableType() {
		return this.getClass().getName();
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	

	public QuittanceGsr getQuittanceGsr() {
		return quittanceGsr;
	}

	public void setQuittanceGsr(QuittanceGsr quittanceGsr) {
		this.quittanceGsr = quittanceGsr;
	}

	public Boolean getGenererQuittance() {
		return genererQuittance;
	}

	public void setGenererQuittance(Boolean genererQuittance) {
		this.genererQuittance = genererQuittance;
	}

	public Boolean getEmissionQuittance() {
		return emissionQuittance;
	}

	public void setEmissionQuittance(Boolean emissionQuittance) {
		this.emissionQuittance = emissionQuittance;
	}

	public MotifSituationEtat getMotifSituationEtat() {
		return motifSituationEtat;
	}

	public void setMotifSituationEtat(MotifSituationEtat motifSituationEtat) {
		this.motifSituationEtat = motifSituationEtat;
	}

	public List<SituationMouvement> getRefSituationMouvement() {
		return refSituationMouvement;
	}

	public void setRefSituationMouvement(
			List<SituationMouvement> refSituationMouvement) {
		this.refSituationMouvement = refSituationMouvement;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getObservation() {
		return observation;
	}

	public List<QuittanceGsr> getListQtcEquilibre() {
		return listQtcEquilibre;
	}
	
	public Calendar getDateCalculRedistribution() {
		return dateCalculRedistribution;
	}

	public void setDateCalculRedistribution(Calendar dateCalculRedistribution) {
		this.dateCalculRedistribution = dateCalculRedistribution;
	}

	public void addQuittanceEquilibre(QuittanceGsr quittanceGsr) {
		if(quittanceGsr != null) {
			if(listQtcEquilibre == null) {
				listQtcEquilibre = new ArrayList<QuittanceGsr>();
			}
			
			if (quittanceGsr.getMontant() != null
					&& !quittanceGsr.getMontant().equals(0D)) {
				listQtcEquilibre.add(quittanceGsr);
			}
		}
		
	}
	
	public SituationMouvement getCurSituationMouvement(EtatMouvement etatMouvement, 
			MotifSituationEtat motifSituationEtat) {
		
		EtatMvt etatMvt = new EtatMvt(etatMouvement.getId());
		this.setRefEtatMvt(etatMvt);
		this.setDatEtat(new GregorianCalendar());
		
		// Situation mouvement
		SituationMouvement situationMouvement =  new SituationMouvement();
		if(refSituationMouvement != null && !refSituationMouvement.isEmpty()) {
			situationMouvement.setRefMotifSituationEtat(refSituationMouvement
					.get(0).getRefMotifSituationEtat());
			
		} 
		
		situationMouvement.setRefEtatMvt(etatMvt);
		situationMouvement.setDateEtat(new Date());
		situationMouvement.setRefMouvement(this);
		
		// Motif etat
		if(motifSituationEtat != null) {
			situationMouvement.setRefMotifSituationEtat(motifSituationEtat);
		}
		
		return situationMouvement;
	}

	public AudienceJugement getRefAudience() {
		return refAudience;
	}

	public void setRefAudience(AudienceJugement refAudience) {
		this.refAudience = refAudience;
	}

	public Double getNouvIPP() {
		return nouvIPP;
	}

	public void setNouvIPP(Double nouvIPP) {
		this.nouvIPP = nouvIPP;
	}

	public Double getNouvMntRente() {
		return nouvMntRente;
	}

	public void setNouvMntRente(Double nouvMntRente) {
		this.nouvMntRente = nouvMntRente;
	}

	public Double getArreragesRente() {
		return arreragesRente;
	}

	public void setArreragesRente(Double arreragesRente) {
		this.arreragesRente = arreragesRente;
	}

	public Double getMntDiminutionRsvGrave() {
		return mntDiminutionRsvGrave;
	}

	public void setMntDiminutionRsvGrave(Double mntDiminutionRsvGrave) {
		this.mntDiminutionRsvGrave = mntDiminutionRsvGrave;
	}

	public Double getMntTropPercu() {
		return mntTropPercu;
	}

	public void setMntTropPercu(Double mntTropPercu) {
		this.mntTropPercu = mntTropPercu;
	}

	public void setSommeMntQtcEquilibre(Double sommeMntQtcEquilibre) {
		this.sommeMntQtcEquilibre = sommeMntQtcEquilibre;
	}

	public Double getSommeMntQtcEquilibre() {
		return sommeMntQtcEquilibre;
	}
	
	
	
}
