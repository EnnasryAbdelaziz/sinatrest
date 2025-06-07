package eai.devass.sinistreat.appli.valueobjects.metier.bnej;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class LotBnejVO implements IValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String dateReception;
	private String dateCreation;
	private String numero;
	private String nombreDossiers;
	private String operateur;
	private List<DossierBnejVO> listDossierBnej;
	private String valide;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateReception() {
		return dateReception;
	}

	public void setDateReception(String dateReception) {
		this.dateReception = dateReception;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNombreDossiers() {
		return nombreDossiers;
	}

	public void setNombreDossiers(String nombreDossiers) {
		this.nombreDossiers = nombreDossiers;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public List<DossierBnejVO> getListDossierBnej() {
		return listDossierBnej;
	}

	public void setListDossierBnej(List<DossierBnejVO> listDossierBnej) {
		this.listDossierBnej = listDossierBnej;
	}
	

	public String getValide() {
		return valide;
	}

	public void setValide(String valide) {
		this.valide = valide;
	}

	@Override
	public String toString() {
		return "LotBnejVO [id=" + id + ", dateReception=" + dateReception
				+ ", dateCreation=" + dateCreation + ", numero=" + numero
				+ ", nombreDossiers=" + nombreDossiers + ", operateur="
				+ operateur + "]";
	}

}
