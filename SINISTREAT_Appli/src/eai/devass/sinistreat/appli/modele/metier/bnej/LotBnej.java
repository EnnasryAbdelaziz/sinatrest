package eai.devass.sinistreat.appli.modele.metier.bnej;

import java.util.Date;
import java.util.List;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;

public class LotBnej implements IEntite {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Date dateReception;
	private Date dateCreation;
	private String numero;
	private volatile  long nombreDossiers;
	private String operateur;
	private List<DossierBnej> listDossierBnej;
	private Boolean valide= Boolean.FALSE;
	
	

	public Date getDateReception() {
		return dateReception;
	}

	public void setDateReception(Date dateReception) {
		this.dateReception = dateReception;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public long getNombreDossiers() {
		return nombreDossiers;
	}

	public void setNombreDossiers(long nombreDossiers) {
		this.nombreDossiers =nombreDossiers;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	

	
	public synchronized void increment(){
			++this.nombreDossiers;	
				
	}
	
	
	public IEntiteFactory getFactory() {
		
		return null;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long arg0) {
		this.id=arg0;
		
	}

	public List<DossierBnej> getListDossierBnej() {
		return listDossierBnej;
	}

	public void setListDossierBnej(List<DossierBnej> listDossierBnej) {
		this.listDossierBnej = listDossierBnej;
	}
	
	public Boolean getValide() {
		return valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	@Override
	public String toString() {
		return "LotBnej [id=" + id + ", dateReception=" + dateReception
				+ ", dateCreation=" + dateCreation + ", numero=" + numero
				+ ", nombreDossiers=" + nombreDossiers + ", operateur="
				+ operateur + "]";
	}
	

}
