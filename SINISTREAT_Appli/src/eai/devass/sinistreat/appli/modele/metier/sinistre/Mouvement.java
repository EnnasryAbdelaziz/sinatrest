/***********************************************************************
 * Module:  EtatMission.java
 * Author:  Administrateur
 * Purpose: Defines the Class EtatMission
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.metier.sinistre;

import java.io.Serializable;
import java.util.Date;

import eai.devass.sinistreat.appli.modele.parametrage.EtatSin;
import eai.devass.sinistreat.appli.modele.parametrage.MotifMouvement;

/**
 * Entité fonctionnelle « Etat d’une mission »
 * 
 * @pdOid 204668b2-76b9-48a3-86cf-2e675c00d751
 */
public class Mouvement implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Long idAudience;

	public Long getIdAudience() {
		return idAudience;
	}

	public void setIdAudience(Long idAudience) {
		this.idAudience = idAudience;
	}

	private String motifEtat;
	private Date dateEtat;
	private EtatSin refEtat;
	private Sinistre refSinistre;
	private Date dateNaissanceNew;
	private Date dateNaissanceOld;
	private Double salaireUtileNew;
	private Double salaireUtileOld;
	private Double ippNew;
	private Double ippOld;
	private Long ittNew;
	private Long ittOld;
	private String evenementDec;
	// private String motif;
	private MotifMouvement refMotif;
	private Double reserveGrave;
	private Double reserveGraveOld;
	private Double reserveOrd;
	private Double reserveOrdOld;
	private Double reserveProthese;
	private Double reserveProtheseOld;
	private String userCreateur;
	private Date dateCreation;
	private Date dateAccidentNew;
	private Date dateAccidentOld;
	private Double salaireAnnuelNew;
	private Double salaireAnnuelOld;
	private Long idReglement;
	private String idCertificat;
	private EtatSinistre refEtatSinistre;
	private Long codeEtatNew;
	private Long codeEtatOld;

	public Long getCodeEtatNew() {
		return codeEtatNew;
	}

	public void setCodeEtatNew(Long codeEtatNew) {
		this.codeEtatNew = codeEtatNew;
	}

	public Long getCodeEtatOld() {
		return codeEtatOld;
	}

	public void setCodeEtatOld(Long codeEtatOld) {
		this.codeEtatOld = codeEtatOld;
	}

	// POur la convertion (VO BO)
	private transient String[] propertiesToConvert;

	public Mouvement() {

	}

	public Mouvement(EtatSin refEtat) {
		this.refEtat = refEtat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMotifEtat() {
		return motifEtat;
	}

	public void setMotifEtat(String motifEtat) {
		this.motifEtat = motifEtat;
	}

	public Date getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(Date dateEtat) {
		this.dateEtat = dateEtat;
	}

	public EtatSin getRefEtat() {
		return refEtat;
	}

	public void setRefEtat(EtatSin refEtat) {
		this.refEtat = refEtat;
	}

	public Sinistre getRefSinistre() {
		return refSinistre;
	}

	public void setRefSinistre(Sinistre refSinistre) {
		this.refSinistre = refSinistre;
	}

	public Date getDateNaissanceNew() {
		return dateNaissanceNew;
	}

	public void setDateNaissanceNew(Date dateNaissanceNew) {
		this.dateNaissanceNew = dateNaissanceNew;
	}

	public Date getDateNaissanceOld() {
		return dateNaissanceOld;
	}

	public void setDateNaissanceOld(Date dateNaissanceOld) {
		this.dateNaissanceOld = dateNaissanceOld;
	}

	public Double getSalaireUtileNew() {
		return salaireUtileNew;
	}

	public void setSalaireUtileNew(Double salaireUtileNew) {
		this.salaireUtileNew = salaireUtileNew;
	}

	public Double getSalaireUtileOld() {
		return salaireUtileOld;
	}

	public void setSalaireUtileOld(Double salaireUtileOld) {
		this.salaireUtileOld = salaireUtileOld;
	}

	public Double getIppNew() {
		return ippNew;
	}

	public void setIppNew(Double ippNew) {
		this.ippNew = ippNew;
	}

	public Double getIppOld() {
		return ippOld;
	}

	public void setIppOld(Double ippOld) {
		this.ippOld = ippOld;
	}

	public String getEvenementDec() {
		return evenementDec;
	}

	public void setEvenementDec(String evenementDec) {
		this.evenementDec = evenementDec;
	}

	// public MotifMouvement getMotif() {
	// return motif;
	// }
	//
	// public void setMotif(MotifMouvement motif) {
	// this.motif = motif;
	// }

	public Double getReserveGrave() {
		return reserveGrave;
	}

	public MotifMouvement getRefMotif() {
		return refMotif;
	}

	public void setRefMotif(MotifMouvement refMotif) {
		this.refMotif = refMotif;
	}

	public void setReserveGrave(Double reserveGrave) {
		this.reserveGrave = reserveGrave;
	}

	public Double getReserveGraveOld() {
		return reserveGraveOld;
	}

	public void setReserveGraveOld(Double reserveGraveOld) {
		this.reserveGraveOld = reserveGraveOld;
	}

	public Double getReserveOrd() {
		return reserveOrd;
	}

	public void setReserveOrd(Double reserveOrd) {
		this.reserveOrd = reserveOrd;
	}

	public Double getReserveOrdOld() {
		return reserveOrdOld;
	}

	public void setReserveOrdOld(Double reserveOrdOld) {
		this.reserveOrdOld = reserveOrdOld;
	}

	public Double getReserveProthese() {
		return reserveProthese;
	}

	public void setReserveProthese(Double reserveProthese) {
		this.reserveProthese = reserveProthese;
	}

	public Double getReserveProtheseOld() {
		return reserveProtheseOld;
	}

	public void setReserveProtheseOld(Double reserveProtheseOld) {
		this.reserveProtheseOld = reserveProtheseOld;
	}

	public String getUserCreateur() {
		return userCreateur;
	}

	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Long getIttNew() {
		return ittNew;
	}

	public void setIttNew(Long ittNew) {
		this.ittNew = ittNew;
	}

	public Long getIttOld() {
		return ittOld;
	}

	public void setIttOld(Long ittOld) {
		this.ittOld = ittOld;
	}

	public Date getDateAccidentNew() {
		return dateAccidentNew;
	}

	public void setDateAccidentNew(Date dateAccidentNew) {
		this.dateAccidentNew = dateAccidentNew;
	}

	public Date getDateAccidentOld() {
		return dateAccidentOld;
	}

	public void setDateAccidentOld(Date dateAccidentOld) {
		this.dateAccidentOld = dateAccidentOld;
	}

	public Double getSalaireAnnuelNew() {
		return salaireAnnuelNew;
	}

	public void setSalaireAnnuelNew(Double salaireAnnuelNew) {
		this.salaireAnnuelNew = salaireAnnuelNew;
	}

	public Double getSalaireAnnuelOld() {
		return salaireAnnuelOld;
	}

	public void setSalaireAnnuelOld(Double salaireAnnuelOld) {
		this.salaireAnnuelOld = salaireAnnuelOld;
	}

	public Long getIdReglement() {
		return idReglement;
	}

	public void setIdReglement(Long idReglement) {
		this.idReglement = idReglement;
	}

	public void setIdCertificat(String idCertificat) {
		this.idCertificat = idCertificat;
	}

	public String getIdCertificat() {
		return idCertificat;
	}

	public EtatSinistre getRefEtatSinistre() {
		return refEtatSinistre;
	}

	public void setRefEtatSinistre(EtatSinistre refEtatSinistre) {
		this.refEtatSinistre = refEtatSinistre;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}

	
	public void setByOld(Mouvement mvtOld) {

		if (mvtOld != null) {
			this.setIppOld(mvtOld.getIppNew());
			this.setIttOld(mvtOld.getIttNew());
			this.setDateNaissanceOld(mvtOld.getDateNaissanceNew());
			this.setDateAccidentOld(mvtOld.getDateAccidentNew());
			this.setSalaireAnnuelOld(mvtOld.getSalaireAnnuelNew());
			this.setSalaireUtileOld(mvtOld.getSalaireUtileNew());
			this.setReserveGraveOld(mvtOld.getReserveGrave());
			this.setReserveOrdOld(mvtOld.getReserveOrd());
			this.setCodeEtatOld(mvtOld.getCodeEtatNew());
			this.setReserveProtheseOld(mvtOld.getReserveProthese());
		}
		
	}
	
	
	
	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;

	}

}