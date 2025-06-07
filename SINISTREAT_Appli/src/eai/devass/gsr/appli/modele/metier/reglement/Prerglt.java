package eai.devass.gsr.appli.modele.metier.reglement;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.reglement.PrergltFactory;
import eai.devass.gsr.appli.modele.parametrage.TypeCheque;
import eai.devass.gsr.appli.modele.parametrage.TypeVirement;

/**
 * Prerglt:
 * 
 * @author Nom Prenom (email)
 * 
 *         // implements by default ILockable
 */

@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.reglement.PrergltVO")
public class Prerglt implements IEntite {

	private long id;
	private static final long serialVersionUID = 1L;

	/**
	 * pourLeCompte
	 */
	@Indexation(label = "pourLeCompte", analyzed = false)
	private String pourLeCompte;
	/**
	 * adresse
	 */
	@Indexation(label = "adresse", analyzed = false)
	private String adresse;
	/**
	 * details
	 */
	@Indexation(label = "details", analyzed = false)
	private String details;
	/**
	 * lblVirement
	 */
	@Indexation(label = "lblVirement", analyzed = false)
	private String lblVirement;
	/**
	 * numCIN
	 */
	@Indexation(label = "numCIN", analyzed = false)
	private String numCIN;
	/**
	 * numRIB
	 */
	@Indexation(label = "numRIB", analyzed = false)
	private String numRIB;
	/**
	 * refBordereau
	 */
	@Indexation(label = "refBordereau", analyzed = false)
	private String refBordereau;
	/**
	 * refRglt
	 */
	@Indexation(label = "refRglt", analyzed = false)
	private String refRglt;
	/**
	 * dateCreation
	 */
	@Indexation(label = "dateCreation", analyzed = false)
	private Calendar dateCreation;

	@Indexation(label = "idsIntermediaire", analyzed = false)
	private String idsIntermediaire;

	@Indexation(label = "codeVille", analyzed = false)
	private String codeVille;

	@Indexation(label = "codePays", analyzed = false)
	private String codePays;
	
	@Indexation(label = "nosReference", analyzed = false)
	private String nosReference;
	

	private TypeCheque refTypeCheque;
	private TypeVirement refTypeVirement;

	// private TypeReglement refTypeReglement; to be transfered to quittance
	// private ModeReglement refModeReglement;
	// private Pays refPays;
	// private Ville refVille;
	// private Intermediaire refIntermediaire;

	public String toString() {
		return String.valueOf(getId());
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entité
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new PrergltFactory();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPourLeCompte() {
		return this.pourLeCompte;
	}

	public void setPourLeCompte(String pourLeCompte) {
		this.pourLeCompte = pourLeCompte;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getLblVirement() {
		return this.lblVirement;
	}

	public void setLblVirement(String lblVirement) {
		this.lblVirement = lblVirement;
	}

	public String getNumCIN() {
		return this.numCIN;
	}

	public void setNumCIN(String numCIN) {
		this.numCIN = numCIN;
	}

	public String getNumRIB() {
		return this.numRIB;
	}

	public void setNumRIB(String numRIB) {
		this.numRIB = numRIB;
	}

	public String getRefBordereau() {
		return this.refBordereau;
	}

	public void setRefBordereau(String refBordereau) {
		this.refBordereau = refBordereau;
	}

	public String getRefRglt() {
		return this.refRglt;
	}

	public void setRefRglt(String refRglt) {
		this.refRglt = refRglt;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public TypeCheque getRefTypeCheque() {
		return this.refTypeCheque;
	}

	public void setRefTypeCheque(TypeCheque refTypeCheque) {
		this.refTypeCheque = refTypeCheque;
	}

	public TypeVirement getRefTypeVirement() {
		return this.refTypeVirement;
	}

	public void setRefTypeVirement(TypeVirement refTypeVirement) {
		this.refTypeVirement = refTypeVirement;
	}

	// public TypeReglement getRefTypeReglement() {
	// return this.refTypeReglement;
	// }
	//
	// public void setRefTypeReglement(TypeReglement refTypeReglement) {
	// this.refTypeReglement = refTypeReglement;
	// }
	// public ModeReglement getRefModeReglement() {
	// return this.refModeReglement;
	// }
	//
	// public void setRefModeReglement(ModeReglement refModeReglement) {
	// this.refModeReglement = refModeReglement;
	// }
	// public Pays getRefPays() {
	// return this.refPays;
	// }
	//
	// public void setRefPays(Pays refPays) {
	// this.refPays = refPays;
	// }
	// public Ville getRefVille() {
	// return this.refVille;
	// }
	//
	// public void setRefVille(Ville refVille) {
	// this.refVille = refVille;
	// }
	// public Intermediaire getRefIntermediaire() {
	// return this.refIntermediaire;
	// }
	//
	// public void setRefIntermediaire(Intermediaire refIntermediaire) {
	// this.refIntermediaire = refIntermediaire;
	// }

	public void setIdsIntermediaire(String idsIntermediaire) {
		this.idsIntermediaire = idsIntermediaire;
	}

	public String getIdsIntermediaire() {
		return idsIntermediaire;
	}

	public void setCodeVille(String codeVille) {
		this.codeVille = codeVille;
	}

	public String getCodeVille() {
		return codeVille;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public String getCodePays() {
		return codePays;
	}

	public void setNosReference(String nosReference) {
		this.nosReference = nosReference;
	}

	public String getNosReference() {
		return nosReference;
	}

	/**
	 * Methode qui retourne l' Id du lockable
	 * 
	 * @returns id du locakble
	 */
	// public String getIdLockable() {
	// return Long.toString(getId());
	// }
	//
	// /**
	// Methode qui retourne le type du lockable
	// @returns type du locakble
	// */
	// public String getLockableType() {
	// return this.getClass().getName();
	// }

}
