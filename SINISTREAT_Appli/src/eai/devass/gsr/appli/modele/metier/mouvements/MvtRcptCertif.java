package eai.devass.gsr.appli.modele.metier.mouvements;
 
import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtRcptCertifFactory;
import eai.devass.gsr.appli.modele.parametrage.TypeCertificat;





/**
 MvtRcptCertif:  
@author Nom Prenom (email)
*/

@AConverter(entiteMapping="eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtRcptCertifVO")
public class MvtRcptCertif extends  Mouvement implements IEntite, ILockable          {

private static final long serialVersionUID = 1L;


/**
datAu   
*/ 
	@Indexation(label="datAu",analyzed=false)
	private Calendar datAu;
/**
datDu   
*/ 
	@Indexation(label="datDu",analyzed=false)
	private Calendar datDu;
/**
datRcpt   
*/ 
	@Indexation(label="datRcpt",analyzed=false)
	private Calendar datRcpt;
/**
numCertificat   
*/ 
//	@Indexation(label="numCertificat",analyzed=false)
//	private Integer numCertificat;
/**
observation   
*/ 
//	@Indexation(label="observation",analyzed=false)
//	private String observation;
///**
//dateCreation   
//*/ 
//	@Indexation(label="dateCreation",analyzed=false)
//	private Calendar dateCreation;


	private TypeCertificat refTypeCertificat;



	public String toString(){
		return super.toString();
	}

/**
Methode qui retourne l' instance de la factory d'une entité
@returns L' entite Factory
*/
	public EntiteFactory getFactory() {
		return new MvtRcptCertifFactory();
	}



	public Calendar getDatAu() {
		return this.datAu;
	}

	public void setDatAu(Calendar datAu) {
		this.datAu = datAu;
	}
	public Calendar getDatDu() {
		return this.datDu;
	}

	public void setDatDu(Calendar datDu) {
		this.datDu = datDu;
	}
	public Calendar getDatRcpt() {
		return this.datRcpt;
	}

	public void setDatRcpt(Calendar datRcpt) {
		this.datRcpt = datRcpt;
	}
//	public Integer getNumCertificat() {
//		return this.numCertificat;
//	}
//
//	public void setNumCertificat(Integer numCertificat) {
//		this.numCertificat = numCertificat;
//	}
//	public String getObservation() {
//		return this.observation;
//	}
//
//	public void setObservation(String observation) {
//		this.observation = observation;
//	}
//	public Calendar getDateCreation() {
//		return this.dateCreation;
//	}
//
//	public void setDateCreation(Calendar dateCreation) {
//		this.dateCreation = dateCreation;
//	}

	public TypeCertificat getRefTypeCertificat() {
		return this.refTypeCertificat;
	}

	public void setRefTypeCertificat(TypeCertificat refTypeCertificat) {
		this.refTypeCertificat = refTypeCertificat;
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
	
	public Boolean genererQuittance() {
		return false;
	}

}

