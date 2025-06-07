/**
 * 
 */
package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import eai.devass.gsr.appli.manager.metier.mouvements.MouvementManager;
import eai.devass.gsr.appli.manager.metier.mouvements.RentierMvtManager;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtAnnulation;
import eai.devass.gsr.appli.modele.metier.mouvements.RentierMvt;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

/**
 * @author ELFAISMO
 *
 */
public class ConsulterMvtAnnulationUC  extends FacadeServiceUseCase {

	/* (non-Javadoc)
	 * @see eai.devass.commun.appli.uc.MouvementBaseUC#execute(java.util.Map)
	 */
	@Override
	protected void doExecuter(IValueObject valueObject, HashMap params) throws Exception {
	
		
		Mouvement lToConsult = new Mouvement();
		MouvementVO voMouvemet =(MouvementVO) valueObject;
		lToConsult.setId(voMouvemet.getId());
		MvtAnnulation mouvementDB = (MvtAnnulation) lToConsult.getFactory().newEntiteManager().getEntite(lToConsult);
		if(mouvementDB == null) {
			throw new FonctionnelleException("Le mouvement recherché est introuvable");
		}

	
		
		
		
		RentierMvtManager rentierMvtManager = new RentierMvtManager();
		
		List<RentierMvt> listeRentierMvt = rentierMvtManager.getListRentierMvt(voMouvemet.getId());
		
		this.addResultItem(listeRentierMvt);

		
		MouvementManager mouvementManager = new MouvementManager();
		
		Mouvement mvtAnnule = mouvementManager.getMouvementByID(mouvementDB.getRefMvtAnnule().getId());
		
		List<SituationMouvement> listSitMvt = mvtAnnule.getRefSituationMouvement();
		String operateur = null;
		if( listSitMvt!=null && listSitMvt.size()>0) {
			operateur = listSitMvt.get(0).getOperateur();
		}
		
		mvtAnnule.setOperateur(operateur);
		this.addResultItem(mvtAnnule);
		
		
//		mouvementDB.setRefTypeMouvement(mouvement.getRefTypeMouvement());
//		mouvementDB.setDatEtat(mouvement.getDatEtat());
//		mouvementDB.setOperateur(mouvement.getOperateur());
//		mouvementDB.setEcheanceEffective(mouvement.getEcheanceEffective());
		

		this.addResultItem(mouvementDB);


	}
	/**
	Methode pour activer le service de transaction
	@returns soit true pour activer le service de transaction ou false pour le desactiver
	*/
		public boolean isTransactionnal() {
			return true;
		}
		
	/**
	Methode pour activer le service de trace
	@returns soit true pour activer le service de trace ou false pour le desactiver
	*/
		public boolean isTracable() {
			return false;
		}
}
