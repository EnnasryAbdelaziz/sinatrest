/**
 * 
 */
package eai.devass.gsr.appli.reglegestion.suppression;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.modele.IHistorisable;
import eai.devass.commun.appli.rg.ContextRegleGestion;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese;
import eai.devass.gsr.appli.modele.parametrage.EtatProthese;
import eai.devass.gsr.appli.reglegestion.BaseRG;

/**
 * @author elfaismo
 *
 */
public class MvtProtheseRG extends BaseRG {
	
public void regleGestion900SupprimerProtheses(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtProthese mvtProthese = (MvtProthese) entiteBO;

		List<Prothese> listProtheses = mvtProthese.getRefsProthese();

		if(commonGsrUtils.isEmpty(listProtheses)) {
			return;
		}

		for (int j = 0; j < listProtheses.size(); j++) {

				Prothese prothese = listProtheses.get(j);
				
				EtatProthese etatProthese = new EtatProthese(eai.devass.gsr.appli.prm.EtatProthese.Supprimee.getId()); 
				
				prothese.setRefEtatProthese(etatProthese);

				prothese.setDateEtat(new GregorianCalendar());
				
				prothese.setContextRegleGestion(ContextRegleGestion.SUPPRESSION.getContext());
				prothese.setOperateur(mvtProthese.getOperateur());
				
				getSession().saveOrUpdate(prothese);
				//			Historisation de prothese
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
