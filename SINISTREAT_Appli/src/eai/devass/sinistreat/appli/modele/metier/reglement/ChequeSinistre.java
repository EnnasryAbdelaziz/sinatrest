package eai.devass.sinistreat.appli.modele.metier.reglement;

import eai.devass.commun.appli.converter.AConverter;

@AConverter(entiteDist = "com.rmawatanya.moyenpaiement.application.metier.valueobjects.ChequeSinistreVO")
public class ChequeSinistre {
	@AConverter(propertyDist = "code")
	private String code;
	@AConverter(propertyDist = "libelle")
	private String libelle;
	@AConverter(propertyDist = "compteDe")
	private String compteDe;
	@AConverter(propertyDist = "envoyerA")
	private String envoyerA;
	@AConverter(propertyDist = "vosReference")
	private String vosReference;
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
	public String getCompteDe() {
		return compteDe;
	}
	public void setCompteDe(String compteDe) {
		this.compteDe = compteDe;
	}
	public String getEnvoyerA() {
		return envoyerA;
	}
	public void setEnvoyerA(String envoyerA) {
		this.envoyerA = envoyerA;
	}
	public String getVosReference() {
		return vosReference;
	}
	public void setVosReference(String vosReference) {
		this.vosReference = vosReference;
	}
	
}
