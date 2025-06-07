package eai.devass.sinistreat.appli.usecase.ihm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

/**
 * Initialiser IHM réglement utilisateur connecter
 * 
 * @author madraras
 * 
 */

@SuppressWarnings("all")
public class InitValidationReglementPositionnementUC extends BaseUC {

	
	private final static String TRUE_ST = "true";
	private final static String FALSE_ST = "false";
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		logger.info("Debut UC InitValidationReglementPositionnementUC");
		Reglement reglement = (Reglement) this.getItem(Reglement.class);
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		HashMap map = (HashMap) this.getItem(HashMap.class);
		List<Reglement> listOrdon = null;
		List<Reglement> listOrdByMode = new ArrayList();

		BigDecimal seuil = BigDecimal.valueOf(9999999);
		BigDecimal seuilsub = BigDecimal.valueOf(0);
		
		if(Fonctions.isEmpty(reglement.getCodeUserModificateur())) {
			throw new FonctionnelleException("Le code SAS utilisateur est obligatoire !!");
		}
		
		// REcuperer le code SAS SUP
		ReglementVO reglementVO = (ReglementVO) entite;
		Boolean isSup = null;
		if(TRUE_ST.equals(reglementVO.getIsSup())) {
			isSup = true;
		} else if(FALSE_ST.equals(reglementVO.getIsSup())) {
			isSup = false;
		}
		
		try {
			if(isSup == null) {
 				isSup = true;//reglementManager.isSuperieur(reglement);
			}
		
		} catch(Exception e) {
			throw new FonctionnelleException("Impossible de récupérer le code SAS SUP (la délégation), pour : " 
					+ reglement.getCodeUserModificateur());
		}
		

		// Quittances local avec pagination
		if (!isSup) {
			// Tous les reglement dont l etat est :
			reglement.setCodeEtatRgl(new String[] {IConstantes.ETAT_REGLEMENT_EN_INSTANCE_POSITIONNEMENT
					,IConstantes.ETAT_REGLEMENT_EN_INSTANCE_DE_VALIDATION});
			if (pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
					
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
								
				Integer nbreLignes = (Fonctions.isLong(pagerVO.getNbrLignes())) 
					? Integer.valueOf(pagerVO.getNbrLignes()) : null;

				if(nbreLignes == null) {
					logger.info("Debut Method reglementManager.getNombreReglementAValider");
					nbreLignes = reglementManager.getNombreReglementAValider(
							reglement, map, seuil, seuilsub);
					logger.info("Fin Method reglementManager.getNombreReglementAValider");
				}
				
				pagerVO.setNbrLignes(String.valueOf(nbreLignes));
				Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
//				if (pageSize != 0) {
//					pagerVO.setNbrPages(String
//							.valueOf((nbreLignes / pageSize) + 1));
//				}
				if (pageSize != 0) {

					int rest = nbreLignes % pageSize;
					if (rest != 0) {
						pagerVO.setNbrPages(String
								.valueOf((nbreLignes / pageSize) + 1));
					} else {
						pagerVO.setNbrPages(String
								.valueOf((nbreLignes / pageSize)));
					}
				}
			}
			
			logger.info("Debut Method reglementManager.getListReglementReglementAValider");
			listOrdon = (List<Reglement>) reglementManager
					.getListReglementReglementAValider(reglement, map,
							pagerVO, seuil, seuilsub);
			logger.info("Fin Method reglementManager.getListReglementReglementAValider");
		} else {
			// Appel service MP pour quittances Hiérarchiques sans pagination
			logger.info("Debut Method reglementManager.ExtraireOrdonnecement");
			listOrdon = (List<Reglement>) reglementManager
					.ExtraireOrdonnecement(reglement);
			logger.info("Fin Method reglementManager.ExtraireOrdonnecement");
			// Debut filrage Mode Reglement 
			if(listOrdon != null && listOrdon.size() != 0 ) {
                if(reglement.getModeReglement() != null) {
                if(reglement.getModeReglement().equals("6")) {
                for(Reglement reg: listOrdon) {
                       if(reg.getModeReglement().equals("6")){
                            listOrdByMode.add(reg);
                       }
                }
                }
         } else {
                for(Reglement reg: listOrdon) {
                       if(!reg.getModeReglement().equals("6")){
                            listOrdByMode.add(reg);
                       }
                }
         }
  }
         listOrdon = listOrdByMode;

			
			//Fin Filtrage 
			
			
		}
		//List<Reglement> listOrd = reglementManager.getListReglementByModeReglment(listOrdon, reglement.getModeReglement());

		this.addResultItem(listOrdon);
		this.addResultItem(isSup);
		this.addResultItem(pagerVO);
		logger.info("Fin UC InitValidationReglementPositionnementUC");
	}

	public boolean isTransactionnal() {
		return false;
	}

}
