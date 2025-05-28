/**
 * 
 */
package eai.devass.gsr.appli.reglegestion.creation;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.modele.IHistorisable;
import eai.devass.commun.appli.rg.ContextRegleGestion;
import eai.devass.gsr.appli.manager.metier.dossierRente.ProthesesManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatProthese;
import eai.devass.gsr.appli.prm.TypeMvtProthese;

/**
 * @author elfaismo
 *
 */
public class MvtProtheseRG extends MouvementRG {
	
	
	ProthesesManager prothesesManager = new ProthesesManager();

	public void regleGestion000MajRenouvellement(EntiteBO entiteBO, Map params) throws ExceptionMetier {
			MvtProthese mvtProthese = (MvtProthese) entiteBO;
			List<Prothese> listProtheses = mvtProthese.getRefsProthese();
			if(mvtProthese.getRefTypeMvtProthese().getId()==TypeMvtProthese.Renouvellement.getId()){
				for(Prothese prothese:listProtheses){
					Long idProthese = null; 
					idProthese = prothese.getNumeroProthese();
					if(idProthese!=null){
						Prothese protheseRenouvele = prothesesManager.getProtheseByID(idProthese);
						EtatProthese etatProtheseRenouvele = new EtatProthese(eai.devass.gsr.appli.prm.EtatProthese.Renouvele.getId());
						protheseRenouvele.setRefEtatProthese(etatProtheseRenouvele);
						
					}
				}
			}	
	}	
	
	
	public void regleGestion993AlimenterProthese(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		MvtProthese mvtProthese = (MvtProthese) entiteBO;
		List<Prothese> listProtheses = mvtProthese.getRefsProthese();
		if(mvtProthese.getRefTypeMvtProthese().getId()==TypeMvtProthese.Renouvellement.getId()){
			for(Prothese prothese:listProtheses){
				Long idProthese = null; 
				idProthese = prothese.getNumeroProthese();
				if(idProthese!=null){
					Prothese protheseRenouvele = prothesesManager.getProtheseByID(idProthese);
					EtatProthese etatProtheseRenouvele = new EtatProthese(eai.devass.gsr.appli.prm.EtatProthese.Renouvele.getId());
					protheseRenouvele.setRefEtatProthese(etatProtheseRenouvele);
				}
			}
		}	

		if(commonGsrUtils.isEmpty(listProtheses)) {
			return;
		}
		for (int i = 0; i < listQtc.size(); i++) {
			QuittanceGsr quittanceGsr = listQtc.get(i);
			for (int j = 0; j < listProtheses.size(); j++) {
				if(i==j){
					Prothese prothese = listProtheses.get(i);
					prothese.setDateEtat(new GregorianCalendar());
					prothese.setDateCreation(new GregorianCalendar());
					prothese.setRefQuittanceGSR(quittanceGsr);
					prothese.setRefRentier(rentierDB);
					prothese.setContextRegleGestion(ContextRegleGestion.CREATION.getContext());
					prothese.setOperateur(mvtProthese.getOperateur());
					getSession().saveOrUpdate(prothese);
//					NatureQtcGsr natureQtc = new NatureQtcGsr(NatureQuittance.Prothese.getId());
//					quittanceGsr.setRefNatureQuittance(natureQtc);
//					quittanceGsr.setCodePrestation(NatureQuittance.Prothese.getRubrique());
					getSession().saveOrUpdate(quittanceGsr);
					quittanceGsr = new QuittanceGsr();					
					try {
						transverseManager.historiser((IHistorisable)prothese, 1);
					} catch (Exception e) {
						logger.warn("L'historisation de la prothese identifiant ["
								+ prothese.getId() + "] n'a pas pu aboutir" );
						logger.error("problème technique",e);
					}
				}
			}
		}
	}
}
