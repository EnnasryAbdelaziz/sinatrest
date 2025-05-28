/**
 * 
 */
package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.manager.metier.mouvements.RentierMvtManager;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtAnnulation;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtCnraReglementaire;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtConsignCNRA;
import eai.devass.gsr.appli.modele.metier.mouvements.RentierMvt;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.NatureDecision;
import eai.devass.gsr.appli.modele.parametrage.TypeConsignation;
import eai.devass.gsr.appli.modele.parametrage.TypeRevision;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

/**
 * @author ELFAISMO
 *
 */
public class ConsulterMouvementUC  extends FacadeServiceUseCase {

	/* (non-Javadoc)
	 * @see eai.devass.commun.appli.uc.MouvementBaseUC#execute(java.util.Map)
	 */
	@Override
	protected void doExecuter(IValueObject valueObject, HashMap params) throws Exception {
	
		TransverseManager transverseManager = (TransverseManager) ServicesFactory.getService(TransverseManager.class);
		Mouvement lToConsult = new Mouvement();
		MouvementVO voMouvemet =(MouvementVO) valueObject;
		lToConsult.setId(voMouvemet.getId());
		Mouvement mouvement = (Mouvement) lToConsult.getFactory().newEntiteManager().getEntite(lToConsult);
		if(mouvement == null) {
			throw new FonctionnelleException("Le mouvement recherché est introuvable");
		}
		
		if(mouvement instanceof MvtAnnulation){
			
			RentierMvtManager rentierMvtManager = new RentierMvtManager();
			List<RentierMvt> listeRentierMvt = rentierMvtManager.getListRentierMvt(voMouvemet.getId());
			this.addResultItem(listeRentierMvt);
		}
		
		if(mouvement instanceof MvtConsignCNRA) {
			
			traiMvtConsignCNRA((MvtConsignCNRA) mouvement);
			long idRentier = mouvement.getRefRentier().getId();
			RentierManager rentierManager = new RentierManager();
			Rentier rentierBD = rentierManager.getRentierByID(idRentier);
			EtatRentier etatRentier = new EtatRentier();
			//etat rentier
			etatRentier.setId(rentierBD.getRefEtatRentier().getId());
			etatRentier.setLibelle(rentierBD.getRefEtatRentier().getLibelle());
			((MvtConsignCNRA) mouvement).getListRentier().get(0).setRefEtatRentier(etatRentier);
			//
			
			
		}
	if(mouvement instanceof MvtCnraReglementaire) {
			
			traiMvtCnraReglementaire((MvtCnraReglementaire) mouvement);
			long idRentier = mouvement.getRefRentier().getId();
			RentierManager rentierManager = new RentierManager();
			Rentier rentierBD = rentierManager.getRentierByID(idRentier);
			EtatRentier etatRentier = new EtatRentier();
			//etat rentier
			etatRentier.setId(rentierBD.getRefEtatRentier().getId());
			etatRentier.setLibelle(rentierBD.getRefEtatRentier().getLibelle());
			((MvtCnraReglementaire) mouvement).getListRentier().get(0).setRefEtatRentier(etatRentier);
	}
		
		this.addResultItem(mouvement);


	}
	/**
	Methode pour activer le service de transaction
	@returns soit true pour activer le service de transaction ou false pour le desactiver
	*/
		public boolean isTransactionnal() {
			return false;
		}
		
	/**
	Methode pour activer le service de trace
	@returns soit true pour activer le service de trace ou false pour le desactiver
	*/
		public boolean isTracable() {
			return false;
		}
		
		private void traiMvtConsignCNRA(MvtConsignCNRA consignCNRA) throws Exception {
			
			Rentier rentier = new Rentier();
			rentier.setId(consignCNRA.getRefRentier().getId());
			rentier.setNom(consignCNRA.getRefRentier().getNom());
			rentier.setPrenom(consignCNRA.getRefRentier().getPrenom());
			rentier.setDateNaissance(consignCNRA.getRefRentier().getDateNaissance());
			rentier.setLienParente(consignCNRA.getRefRentier().getLienParente());
			rentier.setMntCapitalCnra(consignCNRA.getMntCNRA());
			rentier.setMntCapitalCnraCalcule(consignCNRA.getCapitalCalcule());
			rentier.setMontantRenteTrimestrielle(consignCNRA.getRefRentier()
					.getMontantRenteTrimestrielle());
			if (rentier.getMntCapitalCnra()== null)
			{
				rentier.setMntCapitalCnra(Double.valueOf(0));
			}
			if (rentier.getMntCapitalCnraCalcule()== null)
			{
				rentier.setMntCapitalCnraCalcule(Double.valueOf(0));
			}
			rentier.setMntCapitalCnraDiff(rentier.getMntCapitalCnra()
					- rentier.getMntCapitalCnraCalcule());
			
			List<Rentier> list = new ArrayList<Rentier>();
			list.add(rentier);
			consignCNRA.setListRentier(list);
			
			
		}
		
		private void traiMvtCnraReglementaire(MvtCnraReglementaire CnraReglementaire) throws Exception {
			
			Rentier rentier = new Rentier();
			rentier.setId(CnraReglementaire.getRefRentier().getId());
			rentier.setNom(CnraReglementaire.getRefRentier().getNom());
			rentier.setPrenom(CnraReglementaire.getRefRentier().getPrenom());
			rentier.setDateNaissance(CnraReglementaire.getRefRentier().getDateNaissance());
			rentier.setLienParente(CnraReglementaire.getRefRentier().getLienParente());
			rentier.setMntCapitalCnra(CnraReglementaire.getMntCNRA());
			rentier.setMntCapitalCnraCalcule(CnraReglementaire.getCapitalCalcule());
			rentier.setMontantRenteTrimestrielle(CnraReglementaire.getRefRentier().getMontantRenteTrimestrielle());
			if (rentier.getMntCapitalCnra()== null)
			{
				rentier.setMntCapitalCnra(Double.valueOf(0));
			}
			if (rentier.getMntCapitalCnraCalcule()== null)
			{
				rentier.setMntCapitalCnraCalcule(Double.valueOf(0));
			}
			rentier.setQuotePart(CnraReglementaire.getQuotePart());
			rentier.setMntCapitalDu(CnraReglementaire.getMntCapitalDu());
			rentier.setNbrTrimestreAregler(CnraReglementaire.getNbrTrimestreAregler());
			rentier.setMntArrerage(CnraReglementaire.getMntArrerage());
			rentier.setMntReliquat(CnraReglementaire.getMntReliquat());
			rentier.setProrataCNRA(CnraReglementaire.getProrataCNRA());
			List<Rentier> list = new ArrayList<Rentier>();
			list.add(rentier);
			CnraReglementaire.setListRentier(list);
			
			
		}
		
		
		
		
}
