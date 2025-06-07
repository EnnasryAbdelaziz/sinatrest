package eai.devass.gsr.appli.modele.metier.mouvements;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtMajCapitalFactory;
import eai.devass.gsr.appli.modele.parametrage.TypeMajCapital;

/**
 * MvtMajCapital:
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtMajCapitalVO")
public class MvtMajCapital extends Mouvement implements IEntite {

	private static final long serialVersionUID = 1L;

	/**
	 * nouvTaux
	 */
	@Indexation(label = "nouvTaux", analyzed = false)
	private Double nouvTaux;
	// /**
	// * datSuspension
	// */
	// @Indexation(label = "datSuspension", analyzed = false)
	// private Calendar datSuspension;
	// /**
	// * datRemise
	// */
	// @Indexation(label = "datRemise", analyzed = false)
	// private Calendar datRemise;
	/**
	 * arreragesDus
	 */
	@Indexation(label = "arreragesDus", analyzed = false)
	private Double arreragesDus;
	/**
	 * arreragesPercus
	 */
	@Indexation(label = "arreragesPercus", analyzed = false)
	private Double arreragesPercus;
	
	/**
	 * nouvSalaire
	 */
	@Indexation(label = "nouvSalaire", analyzed = false)
	private Double nouvSalaire;
	
	// /**
	// * datDebutArrerage
	// */
	// @Indexation(label = "datDebutArrerage", analyzed = false)
	// private Calendar datDebutArrerage;
	// /**
	// * datFinArrerage
	// */
	// @Indexation(label = "datFinArrerage", analyzed = false)
	// private Calendar datFinArrerage;
	// /**
	// * mntProrata
	// */
	// @Indexation(label = "mntProrata", analyzed = false)
	// private Double mntProrata;

	/**
	 * motif
	 */
	@Indexation(label = "motif", analyzed = false)
	private String motif;
	/**
	 * mntTropPercu
	 */
	@Indexation(label = "mntTropPercu", analyzed = false)
	private Double mntTropPercu;
	/**
	 * mntDiff
	 */
	@Indexation(label = "mntDiff", analyzed = false)
	private Double mntDiff;
	/**
	 * nouvCapitalConstitutif
	 */
	@Indexation(label = "nouvCapitalConstitutif", analyzed = false)
	private Double nouvCapitalConstitutif;
	/**
	 * nouvDatNaissance
	 */
	@Indexation(label = "nouvDatNaissance", analyzed = false)
	private Calendar nouvDatNaissance;

	@Indexation(label = "nouvDateDepartRente", analyzed = false)
	private Calendar nouvDateDepartRente;
	// /**
	// dateCreation
	// */
	// @Indexation(label="dateCreation",analyzed=false)
	// private Calendar dateCreation;

	private TypeMajCapital refTypeMajCapital;
	
	private Boolean renteJugement;
	private Double complementCC;

	public String toString() {
		return super.toString();
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entité
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new MvtMajCapitalFactory();
	}

	public Double getNouvTaux() {
		return this.nouvTaux;
	}

	public void setNouvTaux(Double nouvTaux) {
		this.nouvTaux = nouvTaux;
	}

	// public Calendar getDatSuspension() {
	// return this.datSuspension;
	// }
	//
	// public void setDatSuspension(Calendar datSuspension) {
	// this.datSuspension = datSuspension;
	// }
	//
	// public Calendar getDatRemise() {
	// return this.datRemise;
	// }
	//
	// public void setDatRemise(Calendar datRemise) {
	// this.datRemise = datRemise;
	// }

	public Double getArreragesDus() {
		return this.arreragesDus;
	}

	public void setArreragesDus(Double arreragesDus) {
		this.arreragesDus = arreragesDus;
	}

	public Double getArreragesPercus() {
		return this.arreragesPercus;
	}

	public void setArreragesPercus(Double arreragesPercus) {
		this.arreragesPercus = arreragesPercus;
	}

	

	public Double getNouvSalaire() {
		return this.nouvSalaire;
	}

	public void setNouvSalaire(Double nouvSalaire) {
		this.nouvSalaire = nouvSalaire;
	}
	// public Calendar getDatDebutArrerage() {
	// return this.datDebutArrerage;
	// }
	//
	// public void setDatDebutArrerage(Calendar datDebutArrerage) {
	// this.datDebutArrerage = datDebutArrerage;
	// }
	//
	// public Calendar getDatFinArrerage() {
	// return this.datFinArrerage;
	// }
	//
	// public void setDatFinArrerage(Calendar datFinArrerage) {
	// this.datFinArrerage = datFinArrerage;
	// }

	// public Double getMntProrata() {
	// return this.mntProrata;
	// }
	//
	// public void setMntProrata(Double mntProrata) {
	// this.mntProrata = mntProrata;
	// }

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getMotif() {
		return motif;
	}

	public void setMntTropPercu(Double mntTropPercu) {
		this.mntTropPercu = mntTropPercu;
	}

	public Double getMntTropPercu() {
		return mntTropPercu;
	}

	public Double getMntDiff() {
		return this.mntDiff;
	}

	public void setMntDiff(Double mntDiff) {
		this.mntDiff = mntDiff;
	}

	public Double getNouvCapitalConstitutif() {
		return this.nouvCapitalConstitutif;
	}

	public void setNouvCapitalConstitutif(Double nouvCapitalConstitutif) {
		this.nouvCapitalConstitutif = nouvCapitalConstitutif;
	}

	public Calendar getNouvDatNaissance() {
		return this.nouvDatNaissance;
	}

	public void setNouvDatNaissance(Calendar nouvDatNaissance) {
		this.nouvDatNaissance = nouvDatNaissance;
	}

	// public Calendar getDateCreation() {
	// return this.dateCreation;
	// }
	//
	// public void setDateCreation(Calendar dateCreation) {
	// this.dateCreation = dateCreation;
	// }

	public void setNouvDateDepartRente(Calendar nouvDateDepartRente) {
		this.nouvDateDepartRente = nouvDateDepartRente;
	}

	public Calendar getNouvDateDepartRente() {
		return nouvDateDepartRente;
	}

	public TypeMajCapital getRefTypeMajCapital() {
		return this.refTypeMajCapital;
	}

	public void setRefTypeMajCapital(TypeMajCapital refTypeMajCapital) {
		this.refTypeMajCapital = refTypeMajCapital;
	}

	/**
	 * Methode qui retourne l' Id du lockable
	 * 
	 * @returns id du locakble
	 */
	public String getIdLockable() {
		return Long.toString(getId());
	}

	/**
	 * Methode qui retourne le type du lockable
	 * 
	 * @returns type du locakble
	 */
	public String getLockableType() {
		return this.getClass().getName();
	}

	public Boolean genererQuittance() {
		return true;
	}

	public void setRenteJugement(Boolean renteJugement) {
		this.renteJugement = renteJugement;
	}

	public Boolean getRenteJugement() {
		return renteJugement;
	}

	public void setComplementCC(Double complementCC) {
		this.complementCC = complementCC;
	}

	public Double getComplementCC() {
		return complementCC;
	}

}
