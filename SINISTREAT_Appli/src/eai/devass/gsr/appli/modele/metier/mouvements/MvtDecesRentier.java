package eai.devass.gsr.appli.modele.metier.mouvements;

import java.util.Calendar;
import java.util.List;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtDecesRentierFactory;

/**
 * MvtDecesRentier:
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtDecesRentierVO")
public class MvtDecesRentier extends Mouvement implements IMvtSortant {

	private static final long serialVersionUID = 1L;

	/**
	 * dateDeces
	 */
	@Indexation(label = "dateDeces", analyzed = false)
	private Calendar dateDeces;
	/**
	 * mntProrata
	 */
	@Indexation(label = "mntProrata", analyzed = false)
	private Double mntProrata;
	/**
	 * dateCreation
	 */
	@Indexation(label = "dateCreation", analyzed = false)
	private Calendar dateCreation;

	/**
	 * Trop perçu
	 */
	@Indexation(label = "tropPercu", analyzed = false)
	private Double tropPercu;

	/**
	 * Montant global versé aux héritiers
	 */
	@Indexation(label = "mntGlobalVersee", analyzed = false)
	private Double mntGlobalVersee;

	/**
	 * @return the tropPercu
	 */
	public Double getTropPercu() {
		return tropPercu;
	}

	/**
	 * @param tropPercu
	 *            the tropPercu to set
	 */
	public void setTropPercu(Double tropPercu) {
		this.tropPercu = tropPercu;
	}

	/**
	 * @return the mntGlobalVersee
	 */
	public Double getMntGlobalVersee() {
		return mntGlobalVersee;
	}

	/**
	 * @param mntGlobalVersee
	 *            the mntGlobalVersee to set
	 */
	public void setMntGlobalVersee(Double mntGlobalVersee) {
		this.mntGlobalVersee = mntGlobalVersee;
	}

	/**
	 * dateReceptionCertifDeces
	 */
	@Indexation(label = "dateReceptionCertifDeces", analyzed = false)
	private Calendar dateReceptionCertifDeces;

	private List<Heritier> refsHeritier;
	
	
	private Boolean emissionQuittanceDeces;
	

	public String toString() {
		return super.toString();
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entitÃ©
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new MvtDecesRentierFactory();
	}

	public Calendar getDateDeces() {
		return this.dateDeces;
	}

	public void setDateDeces(Calendar datDeces) {
		this.dateDeces = datDeces;
	}

	public Double getMntProrata() {
		return this.mntProrata;
	}

	public void setMntProrata(Double mntProrata) {
		this.mntProrata = mntProrata;
	}

	public Calendar getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public List getRefsHeritier() {
		return this.refsHeritier;
	}

	public void setRefsHeritier(List refsHeritier) {
		this.refsHeritier = refsHeritier;
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

	/**
	 * @param dateReceptionCertifDeces
	 *            the dateReceptionCertifDeces to set
	 */
	public void setDateReceptionCertifDeces(Calendar dateReceptionCertifDeces) {
		this.dateReceptionCertifDeces = dateReceptionCertifDeces;
	}

	/**
	 * @return the dateReceptionCertifDeces
	 */
	public Calendar getDateReceptionCertifDeces() {
		return dateReceptionCertifDeces;
	}
	/**
	 * @return the emissionQuittanceDeces
	 */
	public Boolean getEmissionQuittanceDeces() {
		return emissionQuittanceDeces;
	}

	/**
	 * @param emissionQuittanceDeces the emissionQuittanceDeces to set
	 */
	public void setEmissionQuittanceDeces(Boolean emissionQuittanceDeces) {
		this.emissionQuittanceDeces = emissionQuittanceDeces;
	}
}
