package eai.devass.gsr.appli.modele.metier.mouvements;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtSuspensionFactory;
import eai.devass.gsr.appli.modele.parametrage.TypeCertificat;

/**
 * MvtSuspension:
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtSuspensionVO")
public class MvtSuspension extends Mouvement implements IEntite, ILockable {

	private static final long serialVersionUID = 1L;

	/**
	 * datSuspension
	 */
	@Indexation(label = "datSuspension", analyzed = false)
	private Calendar datSuspension;
	/**
	 * mntRegle
	 */
	@Indexation(label = "mntRegle", analyzed = false)
	private Double mntRegle;
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

	private TypeCertificat refTypeCertificat;

	@Indexation(label="mntTropPercu",analyzed=false)
	private Double mntTropPercu;
	
	public String toString() {
		return super.toString();
	}

	/**
	 * Methode qui retourne l' instance de la factory d'une entité
	 * 
	 * @returns L' entite Factory
	 */
	public EntiteFactory getFactory() {
		return new MvtSuspensionFactory();
	}

	public Calendar getDatSuspension() {
		return this.datSuspension;
	}

	public void setDatSuspension(Calendar datSuspension) {
		this.datSuspension = datSuspension;
	}

	public Double getMntRegle() {
		return this.mntRegle;
	}

	public void setMntRegle(Double mntRegle) {
		this.mntRegle = mntRegle;
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

	// public void setRefMotifManuelSuspensionRente(
	// MotifManuelSuspensionRente refMotifManuelSuspensionRente) {
	// this.refMotifManuelSuspensionRente = refMotifManuelSuspensionRente;
	// }
	//
	// public MotifManuelSuspensionRente getRefMotifManuelSuspensionRente() {
	// return refMotifManuelSuspensionRente;
	// }
	public Boolean genererQuittance() {
		return false;
	}

	public TypeCertificat getRefTypeCertificat() {
		return refTypeCertificat;
	}

	public void setRefTypeCertificat(TypeCertificat refTypeCertificat) {
		this.refTypeCertificat = refTypeCertificat;
	}

	public Double getMntTropPercu() {
		return mntTropPercu;
	}

	public void setMntTropPercu(Double mntTropPercu) {
		this.mntTropPercu = mntTropPercu;
	}
}
