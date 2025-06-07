package eai.devass.gsr.appli.reglegestion.creation;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ContextRegleGestion;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtConsignCNRA;
import eai.devass.gsr.appli.reglegestion.BaseRG;



public class MvtConsignCNRARG extends BaseRG {
	
	private MouvementRG mouvementRG = new MouvementRG();
	private List<Rentier> listRentier;
		
	public void regleGestion000VerifierEtatMouvement(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtConsignCNRA mouvement = (MvtConsignCNRA) entiteBO;
		mouvement.setPersistObject(false);
		listRentier = mouvement.getListRentier();
		if(commonGsrUtils.isEmpty(listRentier)) {
			throw new ExceptionMetier("La liste des renties est vide !!");
		}
		
		try {
			for (Rentier curRentier : listRentier) {
				mouvementRG.setRentierDB(curRentier);
				mouvementRG.regleGestion001VerifyMouvementEnCours(entiteBO, params);
			}
			
		} catch (Exception e) {
			throw new ExceptionMetier("Impossible de récuperer le rentier : "
					+ e.getMessage());
		}
	}
	
	public void regleGestion001VentillerMouvement(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtConsignCNRA mouvement = (MvtConsignCNRA) entiteBO;
				
		try {
			MvtConsignCNRA curMvtConsignCNRA = null;
			for(Rentier curRentier : listRentier) {
				curMvtConsignCNRA = (MvtConsignCNRA) BeanUtilsBean.getInstance()
						.cloneBean(mouvement);
				curMvtConsignCNRA.setRefRentier(curRentier);

				// Mnt cnra
				curMvtConsignCNRA.setMntCNRA(curRentier.getMntCapitalCnra());
				curMvtConsignCNRA.setCapitalCalcule(curRentier.getMntCapitalCnraCalcule());				
								
				// Etat mouvement
				curMvtConsignCNRA.setContextRegleGestion(ContextRegleGestion.CREATION.toString());
				mouvementRG.regleGestion992SetEtatMouvement(curMvtConsignCNRA, params);
				getSession().save(curMvtConsignCNRA);
			}
		
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de ventiller le mouvement : "
					+ e.getMessage());
		}
	}
	
	
	
}
