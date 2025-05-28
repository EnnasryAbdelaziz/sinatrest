package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;

import eai.devass.sinistreat.appli.valueobjects.parametrage.CauseVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeAccidentVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.VilleVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.ZoneVO;

public class EvenementVO implements IValueObject  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;                   
	private String numeroEvenement="";      
	private String firstEvenement;     
	private String dateAccident;         
	private TypeAccidentVO refTypeAccident;       
	private String lieuAccident;         
	private ZoneVO refZone;              
	private CauseVO refCause;
	private VilleVO refVille;
	private String dateCreation;
	private String dateAccidentDebut; 
	private String dateAccidentFin;
	private String heureAccident; 
	
	/*MFBO@Evolution:456   */
	private String detailsCause;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumeroEvenement() {
		if(StringUtils.isEmpty(this.numeroEvenement)) {
			this.numeroEvenement="";
		}
		return this.numeroEvenement;
	}
	public void setNumeroEvenement(String numeroEvenement) {
		if(StringUtils.isEmpty(numeroEvenement)) {
			numeroEvenement="";
		}
		this.numeroEvenement = numeroEvenement;
	}

	public String getFirstEvenement() {
		return firstEvenement;
	}
	public void setFirstEvenement(String firstEvenement) {
		if(StringUtils.isEmpty(firstEvenement)) {
			firstEvenement="false";
		}			
		this.firstEvenement = firstEvenement;
	}
	public String getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}


	public TypeAccidentVO getRefTypeAccident() {
		return refTypeAccident;
	}
	public void setRefTypeAccident(TypeAccidentVO refTypeAccident) {
		this.refTypeAccident = refTypeAccident;
	}
	public String getLieuAccident() {
		return lieuAccident;
	}
	public void setLieuAccident(String lieuAccident) {
		this.lieuAccident = lieuAccident;
	}
	public ZoneVO getRefZone() {
		return refZone;
	}
	public void setRefZone(ZoneVO refZone) {
		this.refZone = refZone;
	}
	public CauseVO getRefCause() {
		return refCause;
	}
	public void setRefCause(CauseVO refCause) {
		this.refCause = refCause;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public VilleVO getRefVille() {
		return refVille;
	}
	public void setRefVille(VilleVO refVille) {
		this.refVille = refVille;
	}
	public String getDateAccidentDebut() {
		return dateAccidentDebut;
	}
	public void setDateAccidentDebut(String dateAccidentDebut) {
		this.dateAccidentDebut = dateAccidentDebut;
	}
	public String getDateAccidentFin() {
		return dateAccidentFin;
	}
	public void setDateAccidentFin(String dateAccidentFin) {
		this.dateAccidentFin = dateAccidentFin;
	}
	public String getHeureAccident() {
		return heureAccident;
	}
	public void setHeureAccident(String heureAccident) {
		this.heureAccident = heureAccident;
	}
	/**
	 * @return the detailsCause
	 */
	public String getDetailsCause() {
		return detailsCause;
	}
	/**
	 * @param detailsCause the detailsCause to set
	 */
	public void setDetailsCause(String detailsCause) {
		this.detailsCause = detailsCause;
	}

      
	
	
}