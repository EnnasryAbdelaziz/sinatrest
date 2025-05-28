package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;

import java.util.List;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.sinistreat.appli.valueobjects.parametrage.MotifOffreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeProcedureVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

@AConverter(entiteDist = "eai.devass.sinistreat.appli.modele.metier.conciliation.Offre")
public class OffreVO implements IValueObject {
	
	@AConverter(propertyDist = "id")
	private long id;
	@AConverter(propertyDist = "salaireAnnuel")
	private String salaireAnnuel;
	@AConverter(propertyDist = "salaireUtile")
	private String salaireUtile;	
	@AConverter(propertyDist = "ipp")
	private String ipp;
	@AConverter(propertyDist = "autreTauxIpp")
	private String autreTauxIpp;
	@AConverter(propertyDist = "ippOffre")
	private String ippOffre;
	@AConverter(propertyDist = "dateOffre")
	private String dateOffre;
	@AConverter(propertyDist = "dateMotif")
	private String dateMotif;
	@AConverter(propertyDist = "capitalRachat")
	private String capitalRachat;
	@AConverter(propertyDist = "dateDepartRente")
	private String dateDepartRente;
	@AConverter(propertyDist = "dateCreation")
	private String dateCreation;
	@AConverter(propertyDist = "montantRente")
	private String montantRente;
	@AConverter(propertyDist = "montantReserve")
	private String montantReserve;
	@AConverter(propertyDist = "montantArrerageAVC")
	private String montantArrerageAVC; 	
	@AConverter(propertyDist = "observation")
	private String observation;
	@AConverter(propertyDist = "observationOffre")
	private String observationOffre;
	@AConverter(propertyDist = "listAyantDroit")
	private List<AyantDroitOffreVO> listAyantDroit;
	@AConverter(propertyDist = "reserveOffre")
	private String reserveOffre;
	@AConverter(propertyDist = "sommeReserve")
	private String sommeReserve;
	private String age;
	private String dateAccident;
	private ConciliationVO refConciliation;
	private MotifOffreVO refMotifOffre;
	private TypeProcedureVO refTypeProcedure;
	@AConverter(propertyDist = "listSinMotifOffre")
	private List<SinMotifOffreVO> listSinMotifOffre;
	private SinResultatOffreVO refResultatOffre;
	@AConverter(propertyDist = "dateCalcul")
	private String dateCalcul;
	@AConverter(propertyDist = "dateSignaturePv")
	private String dateSignaturePv;
	@AConverter(propertyDist = "listSinistreAnterieur")
	private List<SinAnterieurOffreVO> listSinistreAnterieur;
	private String montantARegler ;
	private String ittRegle;
	private String avp ;
	@AConverter(propertyDist = "montantAReglerProthese")
	private String montantAReglerProthese ;
	@AConverter(propertyDist = "protheseRegle")
	private String protheseRegle;
	@AConverter(propertyDist = "avpProthese")
	private String avpProthese ;
	private String codeMedecinControleur;
	private String nomMedecinControleur;
	private String coefficientAge;
	private String dateCompletudeDossier;
	@AConverter(propertyDist = "etatOffreConciliation")
	private String etatOffreConciliation;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSalaireAnnuel() {
		return salaireAnnuel;
	}
	public void setSalaireAnnuel(String salaireAnnuel) {
		this.salaireAnnuel = salaireAnnuel;
	}
	public String getSalaireUtile() {
		return salaireUtile;
	}
	public void setSalaireUtile(String salaireUtile) {
		this.salaireUtile = salaireUtile;
	}
	public String getIpp() {
		return ipp;
	}
	public void setIpp(String ipp) {
		this.ipp = ipp;
	}
	public String getAutreTauxIpp() {
		return autreTauxIpp;
	}
	public void setAutreTauxIpp(String autreTauxIpp) {
		this.autreTauxIpp = autreTauxIpp;
	}
	public String getDateOffre() {
		return dateOffre;
	}
	public void setDateOffre(String dateOffre) {
		this.dateOffre = dateOffre;
	}
	public String getDateMotif() {
		return dateMotif;
	}
	public void setDateMotif(String dateMotif) {
		this.dateMotif = dateMotif;
	}
	public String getCapitalRachat() {
		return capitalRachat;
	}
	public void setCapitalRachat(String capitalRachat) {
		this.capitalRachat = capitalRachat;
	}
	public String getDateDepartRente() {
		return dateDepartRente;
	}
	public void setDateDepartRente(String dateDepartRente) {
		this.dateDepartRente = dateDepartRente;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
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
	public ConciliationVO getRefConciliation() {
		return refConciliation;
	}
	public void setRefConciliation(ConciliationVO refConciliation) {
		this.refConciliation = refConciliation;
	}
	public MotifOffreVO getRefMotifOffre() {
		return refMotifOffre;
	}
	public void setRefMotifOffre(MotifOffreVO refMotifOffre) {
		this.refMotifOffre = refMotifOffre;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getMontantRente() {
		return montantRente;
	}
	public void setMontantRente(String montantRente) {
		this.montantRente = montantRente;
	}
	public String getMontantReserve() {
		return montantReserve;
	}
	public void setMontantReserve(String montantReserve) {
		this.montantReserve = montantReserve;
	}
	public String getMontantArrerageAVC() {
		return montantArrerageAVC;
	}
	public void setMontantArrerageAVC(String montantArrerageAVC) {
		this.montantArrerageAVC = montantArrerageAVC;
	}
	public List<AyantDroitOffreVO> getListAyantDroit() {
		return listAyantDroit;
	}
	public void setListAyantDroit(List<AyantDroitOffreVO> listAyantDroit) {
		this.listAyantDroit = listAyantDroit;
	}
	public String getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}
	public void setListSinMotifOffre(List<SinMotifOffreVO> listSinMotifOffre) {
		this.listSinMotifOffre = listSinMotifOffre;
	}
	public List<SinMotifOffreVO> getListSinMotifOffre() {
		return listSinMotifOffre;
	}
	public String getReserveOffre() {
		return reserveOffre;
	}
	public void setReserveOffre(String reserveOffre) {
		this.reserveOffre = reserveOffre;
	}
	public SinResultatOffreVO getRefResultatOffre() {
		return refResultatOffre;
	}
	public void setRefResultatOffre(SinResultatOffreVO refResultatOffre) {
		this.refResultatOffre = refResultatOffre;
	}
	public String getIppOffre() {
		return ippOffre;
	}
	public void setIppOffre(String ippOffre) {
		this.ippOffre = ippOffre;
	}
	public String getSommeReserve() {
		return sommeReserve;
	}
	public void setSommeReserve(String sommeReserve) {
		this.sommeReserve = sommeReserve;
	}
	public String getDateCalcul() {
		return dateCalcul;
	}
	public void setDateCalcul(String dateCalcul) {
		this.dateCalcul = dateCalcul;
	}
	public String getDateSignaturePv() {
		return dateSignaturePv;
	}
	public void setDateSignaturePv(String dateSignaturePv) {
		this.dateSignaturePv = dateSignaturePv;
	}

	public List<SinAnterieurOffreVO> getListSinistreAnterieur() {
		return listSinistreAnterieur;
	}
	public void setListSinistreAnterieur(
			List<SinAnterieurOffreVO> listSinistreAnterieur) {
		this.listSinistreAnterieur = listSinistreAnterieur;
	}
	public String getMontantARegler() {
		return montantARegler;
	}
	public void setMontantARegler(String montantARegler) {
		this.montantARegler = montantARegler;
	}
	public String getIttRegle() {
		return ittRegle;
	}
	public void setIttRegle(String ittRegle) {
		this.ittRegle = ittRegle;
	}
	public String getAvp() {
		return avp;
	}
	public void setAvp(String avp) {
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
	public String getCoefficientAge() {
		return coefficientAge;
	}
	public void setCoefficientAge(String coefficientAge) {
		this.coefficientAge = coefficientAge;
	}
	public String getDateCompletudeDossier() {
		return dateCompletudeDossier;
	}
	public void setDateCompletudeDossier(String dateCompletudeDossier) {
		this.dateCompletudeDossier = dateCompletudeDossier;
	}
	public String getMontantAReglerProthese() {
		return montantAReglerProthese;
	}
	public void setMontantAReglerProthese(String montantAReglerProthese) {
		this.montantAReglerProthese = montantAReglerProthese;
	}
	
	public String getProtheseRegle() {
		return protheseRegle;
	}
	public void setProtheseRegle(String protheseRegle) {
		this.protheseRegle = protheseRegle;
	}
	public String getAvpProthese() {
		return avpProthese;
	}
	public void setAvpProthese(String avpProthese) {
		this.avpProthese = avpProthese;
	}
	public TypeProcedureVO getRefTypeProcedure() {
		return refTypeProcedure;
	}
	public void setRefTypeProcedure(TypeProcedureVO refTypeProcedure) {
		this.refTypeProcedure = refTypeProcedure;
	}
	public String getEtatOffreConciliation() {
		return etatOffreConciliation;
	}
	public void setEtatOffreConciliation(String etatOffreConciliation) {
		this.etatOffreConciliation = etatOffreConciliation;
	}
	
}