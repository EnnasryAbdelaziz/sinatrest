package eai.devass.gsr.appli.modele.metier.mouvements;
 
import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.gsr.appli.manager.metier.mouvements.ConsomReserveRenteFactory;





/**
 ConsomReserveRente:  
@author Nom Prenom (email)
*/
public class ConsomReserveRente implements IEntite, ILockable          {

	private long id;
	private static final long serialVersionUID = 1L;


/**
numeroQuittance   
*/ 
	@Indexation(label="numeroQuittance",analyzed=false)
	private String numeroQuittance;
/**
reserveApres   
*/ 
	@Indexation(label="reserveApres",analyzed=false)
	private Double reserveApres;
/**
reserveAvant   
*/ 
	@Indexation(label="reserveAvant",analyzed=false)
	private Double reserveAvant;
/**
dateCreation   
*/ 
	@Indexation(label="dateCreation",analyzed=false)
	private Calendar dateCreation;




	public String toString(){
		return String.valueOf(getId()) ;
	}

/**
Methode qui retourne l' instance de la factory d'une entité
@returns L' entite Factory
*/
	public EntiteFactory getFactory() {
		return new ConsomReserveRenteFactory();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getNumeroQuittance() {
		return this.numeroQuittance;
	}

	public void setNumeroQuittance(String numeroQuittance) {
		this.numeroQuittance = numeroQuittance;
	}
	public Double getReserveApres() {
		return this.reserveApres;
	}

	public void setReserveApres(Double reserveApres) {
		this.reserveApres = reserveApres;
	}
	public Double getReserveAvant() {
		return this.reserveAvant;
	}

	public void setReserveAvant(Double reserveAvant) {
		this.reserveAvant = reserveAvant;
	}
	public Calendar getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}




/**
Methode qui retourne l' Id du lockable
@returns id du locakble
*/
	public String getIdLockable() {
		return Long.toString(getId());
	}

/**
Methode qui retourne le type du lockable
@returns type du locakble
*/
	public String getLockableType() {
		return this.getClass().getName();
	}
	

}

