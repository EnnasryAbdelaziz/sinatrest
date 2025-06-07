package eai.devass.sinistreat.appli.authentification;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.utils.entites.IProfil;


public class Profil implements IValueObject{
	
	private String code;
	private String libelle;
	private List<Fonctionnalite> refFonctionnalites;
	
	
	
	public Profil() {
		
	}
	
	public Profil(String code) {
		this.code = code;
	}
	

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<Fonctionnalite> getRefFonctionnalites() {
		return refFonctionnalites;
	}
	public void setRefFonctionnalites(List<Fonctionnalite> refFonctionnalites) {
		this.refFonctionnalites = refFonctionnalites;
	}
	
	
	public Boolean isResponsable() {
		if(IProfil.RESPONSABLE_VERIFICATION.equals(getCode()) 
				|| IProfil.RESPONSABLE_VERIFICATION_N1.equals(getCode())
				|| IProfil.RESPONSABLE_VERIFICATION_N2.equals(getCode())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean canConvertVOtoBO(){
		return false;
	}
	public boolean canConvertBOtoVO(){
		return false;
	}
	

}


