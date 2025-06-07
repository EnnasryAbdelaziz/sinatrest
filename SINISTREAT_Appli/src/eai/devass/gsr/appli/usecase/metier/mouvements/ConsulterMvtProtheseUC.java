/**
 * 
 */
package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.sql.Blob;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;

import org.hibernate.HibernateException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.mouvements.MouvementManager;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

/**
 * @author ELFAISMO
 *
 */
public class ConsulterMvtProtheseUC  extends FacadeServiceUseCase {
	
	
	/* (non-Javadoc)
	 * @see eai.devass.commun.appli.uc.MouvementBaseUC#execute(java.util.Map)
	 */
	@Override
	protected void doExecuter(IValueObject valueObject, HashMap params) throws Exception {
		
		MouvementManager mouvementManager = new MouvementManager();
	
		
	//	Mouvement lToConsult = new Mouvement();
		MouvementVO voMouvemet =(MouvementVO) valueObject;
		Long idMvt = voMouvemet.getId();
	//	lToConsult.setId(idMvt);

		Blob objetBlob = null;
		try {
			
		objetBlob = (Blob)mouvementManager.getMvtArchiveByID(idMvt.toString());
		}
		catch(HibernateException e){
			throw new ExceptionMetier("Un problème est survenu lors de la récupération du mouvement archivé. ");	
		}
		if(objetBlob==null){
			throw new ExceptionMetier("le mouvement à annuler n'a pas été archivé correctement.");	
		}
		
		MvtProthese mouvementDB = (MvtProthese)CommonUtils.deserialise(objetBlob.getBinaryStream());
		
		
		if(mouvementDB == null) {
			throw new FonctionnelleException("Le mouvement recherché est introuvable");
		}

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
