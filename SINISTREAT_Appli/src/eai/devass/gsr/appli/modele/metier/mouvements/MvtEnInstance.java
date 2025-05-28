package eai.devass.gsr.appli.modele.metier.mouvements;
 
import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtEnInstanceFactory;





/**
 MvtEnInstance:  
@author Nom Prenom (email)
*/
public class MvtEnInstance extends  Mouvement implements IEntite, ILockable          {

private static final long serialVersionUID = 1L;


/**
vofEnInstance   
*/ 
	@Indexation(label="vofEnInstance",analyzed=false)
	private Boolean vofEnInstance;
/**
datVofEnInstance   
*/ 
	@Indexation(label="datVofEnInstance",analyzed=false)
	private Calendar datVofEnInstance;
/**
motif   
*/ 
	@Indexation(label="motif",analyzed=false)
	private String motif;
/**
dateCreation   
*/ 
	@Indexation(label="dateCreation",analyzed=false)
	private Calendar dateCreation;





	public String toString(){
		return super.toString();
	}

/**
Methode qui retourne l' instance de la factory d'une entité
@returns L' entite Factory
*/
	public EntiteFactory getFactory() {
		return new MvtEnInstanceFactory();
	}



	public Boolean getVofEnInstance() {
		return this.vofEnInstance;
	}

	public void setVofEnInstance(Boolean vofEnInstance) {
		this.vofEnInstance = vofEnInstance;
	}
	public Calendar getDatVofEnInstance() {
		return this.datVofEnInstance;
	}

	public void setDatVofEnInstance(Calendar datVofEnInstance) {
		this.datVofEnInstance = datVofEnInstance;
	}
	public String getMotif() {
		return this.motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
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

