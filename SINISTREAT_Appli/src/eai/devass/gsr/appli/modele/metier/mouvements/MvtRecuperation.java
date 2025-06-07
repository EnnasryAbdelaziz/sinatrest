package eai.devass.gsr.appli.modele.metier.mouvements;
 
import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtRecuperationFactory;
import eai.devass.gsr.appli.modele.parametrage.TypeAction;
import eai.devass.gsr.appli.modele.parametrage.TypeRecuperation;





/**
 MvtRecupeartion:  
@author Nom Prenom (email)
*/
public class MvtRecuperation extends  Mouvement implements IEntite, ILockable          {

private static final long serialVersionUID = 1L;


/**
pourcentage   
*/ 
	@Indexation(label="pourcentage",analyzed=false)
	private Double pourcentage;
/**
datDebut   
*/ 
	@Indexation(label="datDebut",analyzed=false)
	private Calendar datDebut;
/**
datFin   
*/ 
	@Indexation(label="datFin",analyzed=false)
	private Calendar datFin;
/**
datRemiseBancaire   
*/ 
	@Indexation(label="datRemiseBancaire",analyzed=false)
	private Calendar datRemiseBancaire;
/**
mntPreleveRente   
*/ 
	@Indexation(label="mntPreleveRente",analyzed=false)
	private Double mntPreleveRente;
/**
mntProrataRecuperation   
*/ 
	@Indexation(label="mntProrataRecuperation",analyzed=false)
	private Double mntProrataRecuperation;
/**
mntRecupere   
*/ 
	@Indexation(label="mntRecupere",analyzed=false)
	private Double mntRecupere;
/**
mntReliquat   
*/ 
	@Indexation(label="mntReliquat",analyzed=false)
	private Double mntReliquat;
/**
mntTropPercu   
*/ 
	@Indexation(label="mntTropPercu",analyzed=false)
	private Double mntTropPercu;
/**
numRemiseBancaire   
*/ 
	@Indexation(label="numRemiseBancaire",analyzed=false)
	private Integer numRemiseBancaire;
/**
dateCreation   
*/ 
//	@Indexation(label="dateCreation",analyzed=false)
//	private Calendar dateCreation;


	private TypeAction refTypeAction;
	private TypeRecuperation refTypeRecuperation;



	public String toString(){
		return super.toString();
	}

/**
Methode qui retourne l' instance de la factory d'une entité
@returns L' entite Factory
*/
	public EntiteFactory getFactory() {
		return new MvtRecuperationFactory();
	}



	public Double getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}
	public Calendar getDatDebut() {
		return this.datDebut;
	}

	public void setDatDebut(Calendar datDebut) {
		this.datDebut = datDebut;
	}
	public Calendar getDatFin() {
		return this.datFin;
	}

	public void setDatFin(Calendar datFin) {
		this.datFin = datFin;
	}
	public Calendar getDatRemiseBancaire() {
		return this.datRemiseBancaire;
	}

	public void setDatRemiseBancaire(Calendar datRemiseBancaire) {
		this.datRemiseBancaire = datRemiseBancaire;
	}
	public Double getMntPreleveRente() {
		return this.mntPreleveRente;
	}

	public void setMntPreleveRente(Double mntPreleveRente) {
		this.mntPreleveRente = mntPreleveRente;
	}
	public Double getMntProrataRecuperation() {
		return this.mntProrataRecuperation;
	}

	public void setMntProrataRecuperation(Double mntProrataRecuperation) {
		this.mntProrataRecuperation = mntProrataRecuperation;
	}
	public Double getMntRecupere() {
		return this.mntRecupere;
	}

	public void setMntRecupere(Double mntRecupere) {
		this.mntRecupere = mntRecupere;
	}
	public Double getMntReliquat() {
		return this.mntReliquat;
	}

	public void setMntReliquat(Double mntReliquat) {
		this.mntReliquat = mntReliquat;
	}
	public Double getMntTropPercu() {
		return this.mntTropPercu;
	}

	public void setMntTropPercu(Double mntTropPercu) {
		this.mntTropPercu = mntTropPercu;
	}
	public Integer getNumRemiseBancaire() {
		return this.numRemiseBancaire;
	}

	public void setNumRemiseBancaire(Integer numRemiseBancaire) {
		this.numRemiseBancaire = numRemiseBancaire;
	}
//	public Calendar getDateCreation() {
//		return this.dateCreation;
//	}
//
//	public void setDateCreation(Calendar dateCreation) {
//		this.dateCreation = dateCreation;
//	}

	public TypeAction getRefTypeAction() {
		return this.refTypeAction;
	}

	public void setRefTypeAction(TypeAction refTypeAction) {
		this.refTypeAction = refTypeAction;
	}
	public TypeRecuperation getRefTypeRecuperation() {
		return this.refTypeRecuperation;
	}

	public void setRefTypeRecuperation(TypeRecuperation refTypeRecuperation) {
		this.refTypeRecuperation = refTypeRecuperation;
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
