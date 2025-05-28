package eai.devass.gsr.appli.modele.metier.reglement;
 
import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.gsr.appli.manager.metier.reglement.OrderesVirementsFactory;





/**
 Ordres de virement:  OrderesVirements;OrderesVirements
@author Nom Prenom (email)
*/
public class OrderesVirements implements IEntite, ILockable          {

	private long id;
private static final long serialVersionUID = 1L;


/**
Année   annee;annee
*/ 
	@Indexation(label="Année",analyzed=false)
	private Double annee;
/**
Montant commission   commission;commission
*/ 
	@Indexation(label="Montant commission",analyzed=false)
	private Double commission;
/**
commissionH   commissionH;commissionh
*/ 
	@Indexation(label="commissionH",analyzed=false)
	private Double commissionH;
/**
Date edition   dateEdition;dateedition
*/ 
	@Indexation(label="Date edition",analyzed=false)
	private Calendar dateEdition;
/**
edit   edit;edit
*/ 
	@Indexation(label="edit",analyzed=false)
	private Boolean edit;
/**
Montant   montant;montant
*/ 
	@Indexation(label="Montant",analyzed=false)
	private Double montant;
/**
Nombre   nombre;nombre
*/ 
	@Indexation(label="Nombre",analyzed=false)
	private Double nombre;
/**
Nom fichier   nomFichier;nomfichier
*/ 
	@Indexation(label="Nom fichier",analyzed=false)
	private String nomFichier;
/**
N° Virement   numeroVirement;numerovirement
*/ 
	@Indexation(label="N° Virement",analyzed=false)
	private String numeroVirement;
/**
Trimestre   trimestre;trimestre
*/ 
	@Indexation(label="Trimestre",analyzed=false)
	private String trimestre;
/**
Type rente   tyeRente;tyerente
*/ 
	@Indexation(label="Type rente",analyzed=false)
	private String typeRente;
/**
Type virement   typeVirement;typevirement
*/ 
	@Indexation(label="Type virement",analyzed=false)
	private String typeVirement;
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
		return new OrderesVirementsFactory();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Double getAnnee() {
		return this.annee;
	}

	public void setAnnee(Double annee) {
		this.annee = annee;
	}
	public Double getCommission() {
		return this.commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public Double getCommissionH() {
		return this.commissionH;
	}

	public void setCommissionH(Double commissionH) {
		this.commissionH = commissionH;
	}
	public Calendar getDateEdition() {
		return this.dateEdition;
	}

	public void setDateEdition(Calendar dateEdition) {
		this.dateEdition = dateEdition;
	}
	public Boolean getEdit() {
		return this.edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}
	public Double getMontant() {
		return this.montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public Double getNombre() {
		return this.nombre;
	}

	public void setNombre(Double nombre) {
		this.nombre = nombre;
	}
	public String getNomFichier() {
		return this.nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}
	public String getNumeroVirement() {
		return this.numeroVirement;
	}

	public void setNumeroVirement(String numeroVirement) {
		this.numeroVirement = numeroVirement;
	}
	public String getTrimestre() {
		return this.trimestre;
	}

	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}
	public String getTypeRente() {
		return this.typeRente;
	}

	public void setTypeRente(String typeRente) {
		this.typeRente = typeRente;
	}
	public String getTypeVirement() {
		return this.typeVirement;
	}

	public void setTypeVirement(String typeVirement) {
		this.typeVirement = typeVirement;
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

