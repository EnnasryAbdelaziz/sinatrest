package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybride;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class InitRechercheCourrierHybridesRejeteUC extends BaseUC  {
	
	
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		List<CourrierHybride> courrierList = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
        CourrierHybride courrierHybride = (CourrierHybride) this.getItem(CourrierHybride.class);
        logger.info("Debut UC InitRechercheCourrierHybridesRejeteUC");
		try {			

			if(pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
				
			if ("1".equals(pagerVO.getNbrLignes())) {
				Integer nbreLignes = parametrageManager.getNombreCourrierHybridesRejete(courrierHybride);
				if (nbreLignes != null && nbreLignes != 0) {
					// Integer nbreObject=nbreLignes.intValue();
					pagerVO.setNbrLignes(nbreLignes.toString());
					Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
					if (pageSize != 0) {
						pagerVO.setNbrPages(String.valueOf((nbreLignes / pageSize) + 1));
					}
					
				}
			}
			courrierList = (List<CourrierHybride>) parametrageManager.rechercheCourrierHybridesRejete(courrierHybride,
					pagerVO);
			if (courrierList.size() == 0) {
				throw new FonctionnelleException(EXPF_AUCUN_COURRIER_TROUVE);
			}
			}else {
				courrierList = (List<CourrierHybride>) parametrageManager.rechercheCourrierHybridesRejete(courrierHybride, null);
				if (courrierList.size() == 0) {
					throw new FonctionnelleException(EXPF_AUCUN_COURRIER_TROUVE);
				}
			}
		}catch (ConstraintViolationException e) {
				throw new FonctionnelleException(e);
			} catch (Exception e) {
				throw new FonctionnelleException(e.getMessage());
			}
			addResultItem(courrierList);
			this.addResultItem(pagerVO);
			logger.info("Fin UC InitRechercheCourrierHybridesRejeteUC");
	}
	
	public boolean isTransactionnal() {
		return false;
	}
}
