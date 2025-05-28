package eai.devass.gsr.appli.modele.metier.mouvements;
 
import java.util.List;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;




/**
 * MvtAnnulation
 * @author elfaismo
 *
 */
@AConverter(entiteMapping="eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtAnnulationVO")
public class MvtAnnulation extends  Mouvement implements IEntite, ILockable          {

	private static final long serialVersionUID = 1L;

	private String motifAnnulation;
	
	private Mouvement refMvtAnnule;
	
	private List<RentierMvt> refsRentierMvt;
	
	public MvtAnnulation(Long identifiant) {
		super.setId(identifiant);
	
	}
	public MvtAnnulation() {

	
	}

	/**
	 * @return the refsRentierMvt
	 */
	public List<RentierMvt> getRefsRentierMvt() {
		return refsRentierMvt;
	}


	/**
	 * @param refsRentierMvt the refsRentierMvt to set
	 */
	public void setRefsRentierMvt(List<RentierMvt> refsRentierMvt) {
		this.refsRentierMvt = refsRentierMvt;
	}


	/**
	 * @return the motifAnnulation
	 */
	public String getMotifAnnulation() {
		return motifAnnulation;
	}
	
	
	/**
	 * @param motifAnnulation the motifAnnulation to set
	 */
	public void setMotifAnnulation(String motifAnnulation) {
		this.motifAnnulation = motifAnnulation;
	}
	
	/**
	 * @return the refMvtAnnule
	 */
	public Mouvement getRefMvtAnnule() {
		return refMvtAnnule;
	}
	/**
	 * @param refMvtAnnule the refMvtAnnule to set
	 */
	public void setRefMvtAnnule(Mouvement refMvtAnnule) {
		this.refMvtAnnule = refMvtAnnule;
	}

}

