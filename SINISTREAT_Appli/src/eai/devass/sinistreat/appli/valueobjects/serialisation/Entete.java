package eai.devass.sinistreat.appli.valueobjects.serialisation;
  
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class Entete implements IValueObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1008L;
	private String idsAdministrateur;
    private String IdsGrgAdministrateur;
    private String idnAdministrateur;
    private String IdnGrgAdministrateur;
    private String fonction;
    private String environnement;
    private String site;
    private String codErreur;
    private String libErreur;
    private String datetraitement;
    private String libWarning;
    
    
	public String getCodErreur() {
		return codErreur;
	}
	public void setCodErreur(String codErreur) {
		this.codErreur = codErreur;
	}
	public String getDatetraitement() {
		return datetraitement;
	}
	public void setDatetraitement(String datetraitement) {
		this.datetraitement = datetraitement;
	}
	public String getEnvironnement() {
		return environnement;
	}
	public void setEnvironnement(String environnement) {
		this.environnement = environnement;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getIdsAdministrateur() {
		return idsAdministrateur;
	}
	public void setIdsAdministrateur(String idsAdministrateur) {
		this.idsAdministrateur = idsAdministrateur;
	}
	public String getIdsGrgAdministrateur() {
		return IdsGrgAdministrateur;
	}
	public void setIdsGrgAdministrateur(String idsGrgAdministrateur) {
		IdsGrgAdministrateur = idsGrgAdministrateur;
	}
	public String getLibErreur() {
		return libErreur;
	}
	public void setLibErreur(String libErreur) {
		this.libErreur = libErreur;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getIdnAdministrateur() {
		return idnAdministrateur;
	}
	public void setIdnAdministrateur(String idnAdministrateur) {
		this.idnAdministrateur = idnAdministrateur;
	}
	public String getIdnGrgAdministrateur() {
		return IdnGrgAdministrateur;
	}
	public void setIdnGrgAdministrateur(String idnGrgAdministrateur) {
		IdnGrgAdministrateur = idnGrgAdministrateur;
	}
	public void setLibWarning(String libWarning) {
		this.libWarning = libWarning;
	}
	public String getLibWarning() {
		return libWarning;
	}

}
