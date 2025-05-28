package eai.devass.gsr.appli.modele.metier.mouvements;
 
import java.util.Calendar;
import java.util.List;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtProtheseFactory;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.parametrage.CentreProthese;
import eai.devass.gsr.appli.modele.parametrage.NatureProthese;





/**
 MvtProthese:  
@author Nom Prenom (email)
*/
@AConverter(entiteMapping="eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtProtheseVO")
public class MvtProthese extends  Mouvement implements IEntite, ILockable          {

private static final long serialVersionUID = 1L;


/**
datMvtProthese   
*/ 
	@Indexation(label="datMvtProthese",analyzed=false)
	private Calendar datMvtProthese;
/**
detailsMvt   
*/ 
	@Indexation(label="detailsMvt",analyzed=false)
	private String detailsMvt;
/**
mntFraisAppareil   
*/ 
	@Indexation(label="mntFraisAppareil",analyzed=false)
	private Double mntFraisAppareil;
/**
mntMvtProthese   
*/ 
	@Indexation(label="mntMvtProthese",analyzed=false)
	private Double mntMvtProthese;
	
	
	/**
	* cumul réserve prothèse 
	*/ 
	@Indexation(label="cumulReserveProthese",analyzed=false)
	private Double cumulReserveProthese;
/**
	 * @return the cumulReserveProthese
	 */
	public Double getCumulReserveProthese() {
		return cumulReserveProthese;
	}

	/**
	 * @param cumulReserveProthese the cumulReserveProthese to set
	 */
	public void setCumulReserveProthese(Double cumulReserveProthese) {
		this.cumulReserveProthese = cumulReserveProthese;
	}

/**
numProthese   
*/ 
	@Indexation(label="numProthese",analyzed=false)
	private Integer numProthese;
/**
vofEstimatif   
*/ 
	@Indexation(label="vofEstimatif",analyzed=false)
	private Boolean vofEstimatif;
/**
dateCreation   
*/ 
//	@Indexation(label="dateCreation",analyzed=false)
//	private Calendar dateCreation;


	private CentreProthese refCentreProthese;

	private NatureProthese refNatureProthese;

	private List<Prothese> refsProthese;


	/**
	 * @return the refsProthese
	 */
	public List<Prothese> getRefsProthese() {
		return refsProthese;
	}

	/**
	 * @param refsProthese the refsProthese to set
	 */
	public void setRefsProthese(List<Prothese> refsProthese) {
		this.refsProthese = refsProthese;
	}

	public String toString(){
		return super.toString();
	}

/**
Methode qui retourne l' instance de la factory d'une entitÃ©
@returns L' entite Factory
*/
	public EntiteFactory getFactory() {
		return new MvtProtheseFactory();
	}



	public Calendar getDatMvtProthese() {
		return this.datMvtProthese;
	}

	public void setDatMvtProthese(Calendar datMvtProthese) {
		this.datMvtProthese = datMvtProthese;
	}
	public String getDetailsMvt() {
		return this.detailsMvt;
	}

	public void setDetailsMvt(String detailsMvt) {
		this.detailsMvt = detailsMvt;
	}
	public Double getMntFraisAppareil() {
		return this.mntFraisAppareil;
	}

	public void setMntFraisAppareil(Double mntFraisAppareil) {
		this.mntFraisAppareil = mntFraisAppareil;
	}
	public Double getMntMvtProthese() {
		return this.mntMvtProthese;
	}

	public void setMntMvtProthese(Double mntMvtProthese) {
		this.mntMvtProthese = mntMvtProthese;
	}
	public Integer getNumProthese() {
		return this.numProthese;
	}

	public void setNumProthese(Integer numProthese) {
		this.numProthese = numProthese;
	}
	public Boolean getVofEstimatif() {
		return this.vofEstimatif;
	}

	public void setVofEstimatif(Boolean vofEstimatif) {
		this.vofEstimatif = vofEstimatif;
	}
//	public Calendar getDateCreation() {
//		return this.dateCreation;
//	}
//
//	public void setDateCreation(Calendar dateCreation) {
//		this.dateCreation = dateCreation;
//	}

	public CentreProthese getRefCentreProthese() {
		return this.refCentreProthese;
	}

	public void setRefCentreProthese(CentreProthese refCentreProthese) {
		this.refCentreProthese = refCentreProthese;
	}

	public NatureProthese getRefNatureProthese() {
		return this.refNatureProthese;
	}

	public void setRefNatureProthese(NatureProthese refNatureProthese) {
		this.refNatureProthese = refNatureProthese;
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

