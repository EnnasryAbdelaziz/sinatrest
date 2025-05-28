package eai.devass.sinistreat.appli.modele.metier.conciliation;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.sinistreat.appli.modele.parametrage.MotifOffre;
import eai.devass.sinistreat.appli.modele.parametrage.TypeProcedure;

@AConverter(entiteDist = "eai.devass.sinistreat.appli.valueobjects.metier.conciliation.OffreVO")
public class Offre implements Serializable {
	
	@AConverter(propertyDist = "id")
	private long id;
	@AConverter(propertyDist = "salaireAnnuel")
	private Double salaireAnnuel;
	@AConverter(propertyDist = "salaireUtile")
	private Double salaireUtile;
	@AConverter(propertyDist = "ipp")
	private Double ipp;
	@AConverter(propertyDist = "autreTauxIpp")
	private Double autreTauxIpp;
	@AConverter(propertyDist = "ippOffre")
	private Double ippOffre;
	@AConverter(propertyDist = "dateOffre")
	private Date dateOffre;	
	@AConverter(propertyDist = "dateMotif")
	private Date dateMotif;
	@AConverter(propertyDist = "capitalRachat")
	private Double capitalRachat;
	@AConverter(propertyDist = "dateDepartRente")
	private Date dateDepartRente;
	@AConverter(propertyDist = "dateCreation")
	private Date dateCreation;
	private Date dateModification;
	@AConverter(propertyDist = "renteTrimistriel")
	private Double renteTrimistriel;
	@AConverter(propertyDist = "montantRente")
	private Double montantRente;
	@AConverter(propertyDist = "montantReserve")
	private Double montantReserve;
	@AConverter(propertyDist = "montantArrerageAVC")
	private Double montantArrerageAVC; 
	@AConverter(propertyDist = "observation")
	private String observation;
	@AConverter(propertyDist = "observationOffre")
	private String observationOffre;
	@AConverter(propertyDist = "listAyantDroit")
	private List<AyantDroitOffre> listAyantDroit;
	@AConverter(propertyDist = "reserveOffre")
	private Double reserveOffre;
	@AConverter(propertyDist = "sommeReserve")
	private Double sommeReserve;
	private Conciliation refConciliation;
	private MotifOffre refMotifOffre;
	private TypeProcedure refTypeProcedure;
	@AConverter(propertyDist = "listSinMotifOffre")
	private List<SinMotifOffre> listSinMotifOffre;
	private SinResultatOffre refResultatOffre;
	// Pour la convertion (VO BO)
	private transient String[] propertiesToConvert;
	private transient Date dateCalcul;	
	@AConverter(propertyDist = "codeSas")
	private String codeSas;
	@AConverter(propertyDist = "dateSignaturePv")
	private Date dateSignaturePv;	
	
	//24-04-2019: Début  Evol Conciliation	
	@AConverter(propertyDist = "listSinistreAnterieur")
	private List<SinAnterieurOffre> listSinistreAnterieur;
	private Double montantARegler ;
	private Boolean ittRegle;
	private Boolean avp ;
	//Debut evol lot 1 30/11/2021
	@AConverter(propertyDist = "protheseRegle")
	private Boolean protheseRegle;
	@AConverter(propertyDist = "avpProthese")
	private Boolean avpProthese ;
	@AConverter(propertyDist = "montantAReglerProthese")
	private Double montantAReglerProthese;
	// Fin lot 1
	private String codeMedecinControleur;
	private String nomMedecinControleur;
	private Double coefficientAge;	
	private Date dateCompletudeDossier;
	//Eol lot 1 - 07/12/2021
	private String etatOffreConciliation;
	
	//Fin evol
	
	//24-04-2019: Fin  Evol Conciliation	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Double getSalaireAnnuel() {
		return salaireAnnuel;
	}
	public void setSalaireAnnuel(Double salaireAnnuel) {
		this.salaireAnnuel = salaireAnnuel;
	}
	public Double getSalaireUtile() {
		return salaireUtile;
	}
	public void setSalaireUtile(Double salaireUtile) {
		this.salaireUtile = salaireUtile;
	}
	public Double getIpp() {
		return ipp;
	}
	public void setIpp(Double ipp) {
		this.ipp = ipp;
	}
	public Double getAutreTauxIpp() {
		return autreTauxIpp;
	}
	public void setAutreTauxIpp(Double autreTauxIpp) {
		this.autreTauxIpp = autreTauxIpp;
	}
	public Date getDateOffre() {
		return dateOffre;
	}
	public void setDateOffre(Date dateOffre) {
		this.dateOffre = dateOffre;
	}
	public Date getDateMotif() {
		return dateMotif;
	}
	public void setDateMotif(Date dateMotif) {
		this.dateMotif = dateMotif;
	}
	public Double getCapitalRachat() {
		return capitalRachat;
	}
	public void setCapitalRachat(Double capitalRachat) {
		this.capitalRachat = capitalRachat;
	}
	public Date getDateDepartRente() {
		return dateDepartRente;
	}
	public void setDateDepartRente(Date dateDepartRente) {
		this.dateDepartRente = dateDepartRente;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Double getRenteTrimistriel() {
		return renteTrimistriel;
	}
	public void setRenteTrimistriel(Double renteTrimistriel) {
		this.renteTrimistriel = renteTrimistriel;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public String getObservationOffre() {
		return observationOffre;
	}
	public void setObservationOffre(String observationOffre) {
		this.observationOffre = observationOffre;
	}
	public Conciliation getRefConciliation() {
		return refConciliation;
	}
	public void setRefConciliation(Conciliation refConciliation) {
		this.refConciliation = refConciliation;
	}
	public MotifOffre getRefMotifOffre() {
		return refMotifOffre;
	}
	public void setRefMotifOffre(MotifOffre refMotifOffre) {
		this.refMotifOffre = refMotifOffre;
	}
	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}

	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	public Double getMontantRente() {
		return montantRente;
	}
	public void setMontantRente(Double montantRente) {
		this.montantRente = montantRente;
	}
	public Double getMontantReserve() {
		return montantReserve;
	}
	public void setMontantReserve(Double montantReserve) {
		this.montantReserve = montantReserve;
	}
	public Double getMontantArrerageAVC() {
		return montantArrerageAVC;
	}
	public void setMontantArrerageAVC(Double montantArrerageAVC) {
		this.montantArrerageAVC = montantArrerageAVC;
	}
	public List<AyantDroitOffre> getListAyantDroit() {
		return listAyantDroit;
	}
	public void setListAyantDroit(List<AyantDroitOffre> listAyantDroit) {
		this.listAyantDroit = listAyantDroit;
	}
	public void setListSinMotifOffre(List<SinMotifOffre> listSinMotifOffre) {
		this.listSinMotifOffre = listSinMotifOffre;
	}
	public List<SinMotifOffre> getListSinMotifOffre() {
		return listSinMotifOffre;
	}
	public Double getReserveOffre() {
		return reserveOffre;
	}
	public void setReserveOffre(Double reserveOffre) {
		this.reserveOffre = reserveOffre;
	}
	public Date getDateModification() {
		return dateModification;
	}
	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}
	public SinResultatOffre getRefResultatOffre() {
		return refResultatOffre;
	}
	public void setRefResultatOffre(SinResultatOffre refResultatOffre) {
		this.refResultatOffre = refResultatOffre;
	}
	public Double getIppOffre() {
		return ippOffre;
	}
	public void setIppOffre(Double ippOffre) {
		this.ippOffre = ippOffre;
	}
	public Double getSommeReserve() {
		return sommeReserve;
	}
	public void setSommeReserve(Double sommeReserve) {
		this.sommeReserve = sommeReserve;
	}
	public void setCodeSas(String codeSas) {
		this.codeSas = codeSas;
	}
	public String getCodeSas() {
		return codeSas;
	}
	public Date getDateCalcul() {
		return dateCalcul;
	}
	public void setDateCalcul(Date dateCalcul) {
		this.dateCalcul = dateCalcul;
	}
	public Date getDateSignaturePv() {
		return dateSignaturePv;
	}
	public void setDateSignaturePv(Date dateSignaturePv) {
		this.dateSignaturePv = dateSignaturePv;
	}

	public List<SinAnterieurOffre> getListSinistreAnterieur() {
		return listSinistreAnterieur;
	}
	public void setListSinistreAnterieur(
			List<SinAnterieurOffre> listSinistreAnterieur) {
		this.listSinistreAnterieur = listSinistreAnterieur;
	}
	public Double getMontantARegler() {
		return montantARegler;
	}
	public void setMontantARegler(Double montantARegler) {
		this.montantARegler = montantARegler;
	}
	public Boolean getIttRegle() {
		return ittRegle;
	}
	public void setIttRegle(Boolean ittRegle) {
		this.ittRegle = ittRegle;
	}
	public Boolean getAvp() {
		return avp;
	}
	public void setAvp(Boolean avp) {
		this.avp = avp;
	}
	public String getCodeMedecinControleur() {
		return codeMedecinControleur;
	}
	public void setCodeMedecinControleur(String codeMedecinControleur) {
		this.codeMedecinControleur = codeMedecinControleur;
	}
	public String getNomMedecinControleur() {
		return nomMedecinControleur;
	}
	public void setNomMedecinControleur(String nomMedecinControleur) {
		this.nomMedecinControleur = nomMedecinControleur;
	}
	public Double getCoefficientAge() {
		return coefficientAge;
	}
	public void setCoefficientAge(Double coefficientAge) {
		this.coefficientAge = coefficientAge;
	}
	public Date getDateCompletudeDossier() {
		return dateCompletudeDossier;
	}
	public void setDateCompletudeDossier(Date dateCompletudeDossier) {
		this.dateCompletudeDossier = dateCompletudeDossier;
	}
	
	public Boolean getProtheseRegle() {
		return protheseRegle;
	}
	public void setProtheseRegle(Boolean protheseRegle) {
		this.protheseRegle = protheseRegle;
	}
	public Boolean getAvpProthese() {
		return avpProthese;
	}
	public void setAvpProthese(Boolean avpProthese) {
		this.avpProthese = avpProthese;
	}
	public Double getMontantAReglerProthese() {
		return montantAReglerProthese;
	}
	public void setMontantAReglerProthese(Double montantAReglerProthese) {
		this.montantAReglerProthese = montantAReglerProthese;
	}
	public TypeProcedure getRefTypeProcedure() {
		return refTypeProcedure;
	}
	public void setRefTypeProcedure(TypeProcedure refTypeProcedure) {
		this.refTypeProcedure = refTypeProcedure;
	}
	public String getEtatOffreConciliation() {
		return etatOffreConciliation;
	}
	public void setEtatOffreConciliation(String etatOffreConciliation) {
		this.etatOffreConciliation = etatOffreConciliation;
	}
	
	
}