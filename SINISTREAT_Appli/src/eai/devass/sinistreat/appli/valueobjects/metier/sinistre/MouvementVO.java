package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatSinVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.MotifMouvementVO;

public class MouvementVO implements IValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String motifEtat;
	private String dateEtat;
	private EtatSinVO refEtat;
	private SinistreVO refSinistre;	
	private String dateNaissanceNew; 
	private String dateNaissanceOld; 
	private String salaireUtileNew;
	private String salaireUtileOld;  
	private String ippNew;
	private String ippOld;
	private String ittNew;
	private String ittOld;	
	private String evenementDec;
	//private String motif;
	private MotifMouvementVO refMotif;
	private String reserveGrave;
	private String reserveGraveOld;
	private String reserveOrd;
	private String reserveOrdOld;
	private String reserveProthese;
	private String reserveProtheseOld;
	private String userCreateur  ;           
	private String dateCreation  ;   
	private String dateAccidentNew; 
	private String dateAccidentOld;
	private String salaireAnnuelNew;
	private String salaireAnnuelOld;
	private String idReglement;
	private String idCertificat;
	private EtatSinistreVO refEtatSinistre;
	
	public MouvementVO() {
		
	}
	public MouvementVO(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMotifEtat() {
		return motifEtat;
	}
	public void setMotifEtat(String motifEtat) {
		this.motifEtat = motifEtat;
	}
	public String getDateEtat() {
		return dateEtat;
	}
	public void setDateEtat(String dateEtat) {
		this.dateEtat = dateEtat;
	}
	public EtatSinVO getRefEtat() {
		return refEtat;
	}
	public void setRefEtat(EtatSinVO refEtat) {
		this.refEtat = refEtat;
	}
	public SinistreVO getRefSinistre() {
		return refSinistre;
	}
	public void setRefSinistre(SinistreVO refSinistre) {
		this.refSinistre = refSinistre;
	}
	public String getDateNaissanceNew() {
		return dateNaissanceNew;
	}
	public void setDateNaissanceNew(String dateNaissanceNew) {
		this.dateNaissanceNew = dateNaissanceNew;
	}
	public String getDateNaissanceOld() {
		return dateNaissanceOld;
	}
	public void setDateNaissanceOld(String dateNaissanceOld) {
		this.dateNaissanceOld = dateNaissanceOld;
	}
	public String getSalaireUtileNew() {
		return salaireUtileNew;
	}
	public void setSalaireUtileNew(String salaireUtileNew) {
		this.salaireUtileNew = salaireUtileNew;
	}
	public String getSalaireUtileOld() {
		return salaireUtileOld;
	}
	public void setSalaireUtileOld(String salaireUtileOld) {
		this.salaireUtileOld = salaireUtileOld;
	}
	public String getIppNew() {
		return ippNew;
	}
	public void setIppNew(String ippNew) {
		this.ippNew = ippNew;
	}
	public String getIppOld() {
		return ippOld;
	}
	public void setIppOld(String ippOld) {
		this.ippOld = ippOld;
	}
	public String getIttNew() {
		return ittNew;
	}
	public void setIttNew(String ittNew) {
		this.ittNew = ittNew;
	}
	public String getIttOld() {
		return ittOld;
	}
	public void setIttOld(String ittOld) {
		this.ittOld = ittOld;
	}
	public String getEvenementDec() {
		return evenementDec;
	}
	public void setEvenementDec(String evenementDec) {
		this.evenementDec = evenementDec;
	}
	public MotifMouvementVO getRefMotif() {
		return refMotif;
	}
	public void setRefMotif(MotifMouvementVO refMotif) {
		this.refMotif = refMotif;
	}
	public String getReserveGrave() {
		return reserveGrave;
	}
	public void setReserveGrave(String reserveGrave) {
		this.reserveGrave = reserveGrave;
	}
	public String getReserveGraveOld() {
		return reserveGraveOld;
	}
	public void setReserveGraveOld(String reserveGraveOld) {
		this.reserveGraveOld = reserveGraveOld;
	}
	public String getReserveOrd() {
		return reserveOrd;
	}
	public void setReserveOrd(String reserveOrd) {
		this.reserveOrd = reserveOrd;
	}
	public String getReserveOrdOld() {
		return reserveOrdOld;
	}
	public void setReserveOrdOld(String reserveOrdOld) {
		this.reserveOrdOld = reserveOrdOld;
	}
	public String getReserveProthese() {
		return reserveProthese;
	}
	public void setReserveProthese(String reserveProthese) {
		this.reserveProthese = reserveProthese;
	}
	public String getReserveProtheseOld() {
		return reserveProtheseOld;
	}
	public void setReserveProtheseOld(String reserveProtheseOld) {
		this.reserveProtheseOld = reserveProtheseOld;
	}
	public String getUserCreateur() {
		return userCreateur;
	}
	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getDateAccidentNew() {
		return dateAccidentNew;
	}
	public void setDateAccidentNew(String dateAccidentNew) {
		this.dateAccidentNew = dateAccidentNew;
	}
	public String getDateAccidentOld() {
		return dateAccidentOld;
	}
	public void setDateAccidentOld(String dateAccidentOld) {
		this.dateAccidentOld = dateAccidentOld;
	}
	public String getSalaireAnnuelNew() {
		return salaireAnnuelNew;
	}
	public void setSalaireAnnuelNew(String salaireAnnuelNew) {
		this.salaireAnnuelNew = salaireAnnuelNew;
	}
	public String getSalaireAnnuelOld() {
		return salaireAnnuelOld;
	}
	public void setSalaireAnnuelOld(String salaireAnnuelOld) {
		this.salaireAnnuelOld = salaireAnnuelOld;
	}
	public String getIdReglement() {
		return idReglement;
	}
	public void setIdReglement(String idReglement) {
		this.idReglement = idReglement;
	}
	public String getIdCertificat() {
		return idCertificat;
	}
	public void setIdCertificat(String idCertificat) {
		this.idCertificat = idCertificat;
	}
	public EtatSinistreVO getRefEtatSinistre() {
		return refEtatSinistre;
	}
	public void setRefEtatSinistre(EtatSinistreVO refEtatSinistre) {
		this.refEtatSinistre = refEtatSinistre;
	}
	
	

}
