package eai.devass.gsr.appli.modele.metier.mouvements;
 
import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtRachatFactory;
import eai.devass.gsr.appli.modele.parametrage.OrigineRachat;





/**
 MvtRachat:  
@author Nom Prenom (email)
*/

@AConverter(entiteMapping="eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtRachatVO")
public class MvtRachat extends  Mouvement implements IEntite, ILockable,IMvtSortant          {

private static final long serialVersionUID = 1L;



/**
capital Calcule   
*/ 
	@Indexation(label="capitalCalcule",analyzed=false)
	private Double capitalCalcule;

/**
capitalJuge   
*/ 
	@Indexation(label="capitalJuge",analyzed=false)
	private Double capitalJuge;

/**
mntPercu   
*/ 
	@Indexation(label="mntPercu",analyzed=false)
	private Double mntPercu;
/**
mntDiff   
*/ 
	@Indexation(label="mntDiff",analyzed=false)
	private Double mntDiff;
///**
//dateCreation   
//*/ 
//	@Indexation(label="dateCreation",analyzed=false)
//	private Calendar dateCreation;

	//MFBO@Evo mouvement Rachat Rente GSR
	/**
	dateCalcul
	*/ 
	
	@Indexation(label="dateCalcul",analyzed=false)
	private Calendar dateCalcul;

	@Indexation(label="nouveauIPP",analyzed=false)
	private Double nouveauIPP;
	@Indexation(label="salaireUtile",analyzed=false)
	private Double salaireUtile;
	
	
	/**
	 * origineRachat
	 */
	@Indexation(label="refOrigineRachat",analyzed=false) 
	private OrigineRachat refOrigineRachat;
	
	
/**
	 * @return the nouveauIPP
	 */
	public Double getNouveauIPP() {
		return nouveauIPP;
	}

	/**
	 * @param nouveauIPP the nouveauIPP to set
	 */
	public void setNouveauIPP(Double nouveauIPP) {
		this.nouveauIPP = nouveauIPP;
	}

	/**
	 * @return the salaireUtile
	 */
	public Double getSalaireUtile() {
		return salaireUtile;
	}

	/**
	 * @param salaireUtile the salaireUtile to set
	 */
	public void setSalaireUtile(Double salaireUtile) {
		this.salaireUtile = salaireUtile;
	}

	/**
	 * @return the capitalCalcule
	 */
	public Double getCapitalCalcule() {
		return capitalCalcule;
	}

	/**
	 * @param capitalCalcule the capitalCalcule to set
	 */
	public void setCapitalCalcule(Double capitalCalcule) {
		this.capitalCalcule = capitalCalcule;
	}



	
	
	/**
 * @return the dateCalcul
 */
public Calendar getDateCalcul() {
	return dateCalcul;
}

/**
 * @param dateCalcul the dateCalcul to set
 */
public void setDateCalcul(Calendar dateCalcul) {
	this.dateCalcul = dateCalcul;
}

	/**
 * @return the refOrigineRachat
 */
public OrigineRachat getRefOrigineRachat() {
	return refOrigineRachat;
}

/**
 * @param refOrigineRachat the refOrigineRachat to set
 */
public void setRefOrigineRachat(OrigineRachat refOrigineRachat) {
	this.refOrigineRachat = refOrigineRachat;
}

	public String toString(){
		return super.toString();
	}

/**
Methode qui retourne l' instance de la factory d'une entité
@returns L' entite Factory
*/
	public EntiteFactory getFactory() {
		return new MvtRachatFactory();
	}



	public Double getCapitalJuge() {
		return this.capitalJuge;
	}

	public void setCapitalJuge(Double capitalJuge) {
		this.capitalJuge = capitalJuge;
	}
	public Double getMntPercu() {
		return this.mntPercu;
	}

	public void setMntPercu(Double mntPercu) {
		this.mntPercu = mntPercu;
	}
	public Double getMntDiff() {
		return this.mntDiff;
	}

	public void setMntDiff(Double mntDiff) {
		this.mntDiff = mntDiff;
	}
//	public Calendar getDateCreation() {
//		return this.dateCreation;
//	}
//
//	public void setDateCreation(Calendar dateCreation) {
//		this.dateCreation = dateCreation;
//	}




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
		return true;
	}

}

