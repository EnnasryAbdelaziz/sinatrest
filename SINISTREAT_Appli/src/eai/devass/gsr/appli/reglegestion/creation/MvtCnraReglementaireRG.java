package eai.devass.gsr.appli.reglegestion.creation;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ContextRegleGestion;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtCnraReglementaire;
import eai.devass.gsr.appli.reglegestion.BaseRG;



public class MvtCnraReglementaireRG extends BaseRG {
	
	private MouvementRG mouvementRG = new MouvementRG();
	private List<Rentier> listRentier;
		
	public void regleGestion000VerifierEtatMouvement(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtCnraReglementaire mouvement = (MvtCnraReglementaire) entiteBO;
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
		
		MvtCnraReglementaire mouvement = (MvtCnraReglementaire) entiteBO;
				
		try {
			MvtCnraReglementaire curMvtCnraReglementaire = null;
			for(Rentier curRentier : listRentier) {
				curMvtCnraReglementaire = (MvtCnraReglementaire) BeanUtilsBean.getInstance().cloneBean(mouvement);
				curMvtCnraReglementaire.setRefRentier(curRentier);

				// Mnt cnra
				curMvtCnraReglementaire.setMntCNRA(curRentier.getMntCapitalCnra());
				curMvtCnraReglementaire.setCapitalCalcule(curRentier.getMntCapitalCnraCalcule());	
				curMvtCnraReglementaire.setQuotePart(curRentier.getQuotePart());
				curMvtCnraReglementaire.setMntCapitalDu(curRentier.getMntCapitalDu());
				curMvtCnraReglementaire.setNbrTrimestreAregler(curRentier.getNbrTrimestreAregler());
				curMvtCnraReglementaire.setMntArrerage(curRentier.getMntArrerage());
				curMvtCnraReglementaire.setMntReliquat(curRentier.getMntReliquat());
				curMvtCnraReglementaire.setProrataCNRA(curRentier.getProrataCNRA());
				curMvtCnraReglementaire.setMntRente(curRentier.getMontantRenteTrimestrielle());
				// Etat mouvement
				curMvtCnraReglementaire.setContextRegleGestion(ContextRegleGestion.CREATION.toString());
				mouvementRG.regleGestion992SetEtatMouvement(curMvtCnraReglementaire, params);
				getSession().save(curMvtCnraReglementaire);
			}
		
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de ventiller le mouvement : "
					+ e.getMessage());
		}
	}
	
	
	
}
