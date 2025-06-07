
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import eai.devass.commun.appli.converter.AConverter;


/**
 * Value Object de MvtAnnulation
 * @author elfaismo
 *
 */
@AConverter(entiteDist="eai.devass.gsr.appli.modele.metier.mouvements.MvtAnnulation")
public class MvtAnnulationVO extends  MouvementVO  implements ITracable{

	private static final long serialVersionUID = 1L;


	@Indexation(label="motifAnnulation",analyzed=false)
	@AConverter(propertyDist="motifAnnulation")
	private String motifAnnulation;

	private MouvementVO refMvtAnnule;
	
	@AConverter(propertyDist="refsRentierMvt")
	private List<RentierMvtVO> refsRentierMvt;
	
	/**
	 * @return the refsRentierMvt
	 */
	public List<RentierMvtVO> getRefsRentierMvt() {
		return refsRentierMvt;
	}



	/**
	 * @param refsRentierMvt the refsRentierMvt to set
	 */
	public void setRefsRentierMvt(List<RentierMvtVO> refsRentierMvt) {
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
	public MouvementVO getRefMvtAnnule() {
		return refMvtAnnule;
	}



	/**
	 * @param refMvtAnnule the refMvtAnnule to set
	 */
	public void setRefMvtAnnule(MouvementVO refMvtAnnule) {
		this.refMvtAnnule = refMvtAnnule;
	}
}

