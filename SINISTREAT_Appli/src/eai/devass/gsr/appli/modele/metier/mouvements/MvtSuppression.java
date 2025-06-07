package eai.devass.gsr.appli.modele.metier.mouvements;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtSuppressionFactory;
import eai.devass.gsr.appli.modele.parametrage.TypeAction;

/**
 * MvtSuppression:
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtSuppressionVO")
public class MvtSuppression extends Mouvement implements IEntite, ILockable,IMvtSortant {

	private static final long serialVersionUID = 1L;

	/**
	 * mntIndu
	 */
	@Indexation(label = "mntIndu", analyzed = false)
	private Double mntIndu;
	/**
	 * datSuppression
	 */
	@Indexation(label = "datSuppression", analyzed = false)
	private Calendar datSuppression;
	/**
	 * motif
	 */
	@Indexation(label = "motif", analyzed = false)
	private String motif;
	/**
	 * dateCreation
	 */
	@Indexation(label = "dateCreation", analyzed = false)
	private Calendar dateCreation;

	private TypeAction refTypeAction;

	public String toString() {
		return super.toString();
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entité
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new MvtSuppressionFactory();
	}

	public Double getMntIndu() {
		return this.mntIndu;
	}

	public void setMntIndu(Double mntIndu) {
		this.mntIndu = mntIndu;
	}

	public Calendar getDatSuppression() {
		return this.datSuppression;
	}

	public void setDatSuppression(Calendar datSuppression) {
		this.datSuppression = datSuppression;
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

	public TypeAction getRefTypeAction() {
		return this.refTypeAction;
	}

	public void setRefTypeAction(TypeAction refTypeAction) {
		this.refTypeAction = refTypeAction;
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

}
