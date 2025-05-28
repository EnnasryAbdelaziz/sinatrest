package eai.devass.gsr.appli.modele.parametrage;
 
import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.parametrage.EtatMvtFactory;





/**
 EtatMvt:  
@author Nom Prenom (email)
*/

@AConverter(entiteMapping="eai.devass.gsr.appli.valueobjects.parametrage.EtatMvtVO")
public class EtatMvt implements IEntite, ILockable          {

	private long id;
private static final long serialVersionUID = 1L;


/**
code   
*/ 
	@Indexation(label="code",analyzed=false)
	private Integer code;
/**
libelle   
*/ 
	@Indexation(label="libelle",analyzed=false)
	private String libelle;
/**
dateCreation   
*/ 
	@Indexation(label="dateCreation",analyzed=false)
	private Calendar dateCreation;




	public EtatMvt() {
	super();
	
	}
	
	

	public EtatMvt(long id) {
		super();
		this.id = id;
	}



	public String toString(){
		return String.valueOf(getLibelle()) ;
	}

/**
Methode qui retourne l' instance de la factory d'une entité
@returns L' entite Factory
*/
	public EntiteFactory getFactory() {
		return new EtatMvtFactory();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
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

