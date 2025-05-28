package eai.devass.gsr.appli.modele.metier.mouvements;
 
import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtRenteEchueFactory;





/**
 MvtRenteEchue:  
@author Nom Prenom (email)
*/
@AConverter(entiteMapping = "eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtRenteEchueVO")
public class MvtRenteEchue extends  Mouvement implements IEntite, ILockable, IMvtSortant         {

private static final long serialVersionUID = 1L;


	/**
	datFinRente   
	*/ 
	@Indexation(label="datFinRente",analyzed=false)
	private Calendar datFinRente;
	/**
	mntProrata   
	*/ 
	@Indexation(label="mntProrata",analyzed=false)
	private Double mntProrata;
	private Double mntTropPercu;
	private transient Double mntARegle;
	private Calendar dateMariage;
	private Calendar dateFinScolarite;
	
	public String toString(){
		return super.toString();
	}

/**
Methode qui retourne l' instance de la factory d'une entité
@returns L' entite Factory
*/
	public EntiteFactory getFactory() {
		return new MvtRenteEchueFactory();
	}



	public Calendar getDatFinRente() {
		return this.datFinRente;
	}

	public void setDatFinRente(Calendar datFinRente) {
		this.datFinRente = datFinRente;
	}
	public Double getMntProrata() {
		return this.mntProrata;
	}

	public Double getMntTropPercu() {
		return mntTropPercu;
	}

	public void setMntTropPercu(Double mntTropPercu) {
		this.mntTropPercu = mntTropPercu;
	}

	public void setMntProrata(Double mntProrata) {
		this.mntProrata = mntProrata;
	}

	public Calendar getDateMariage() {
		return dateMariage;
	}

	public void setDateMariage(Calendar dateMariage) {
		this.dateMariage = dateMariage;
	}

	public Calendar getDateFinScolarite() {
		return dateFinScolarite;
	}

	public void setDateFinScolarite(Calendar dateFinScolarite) {
		this.dateFinScolarite = dateFinScolarite;
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

	/**
	 * @return the mntARegle
	 */
	public Double getMntARegle() {
		return mntARegle;
	}

	/**
	 * @param mntARegle the mntARegle to set
	 */
	public void setMntARegle(Double mntARegle) {
		this.mntARegle = mntARegle;
	}
}
