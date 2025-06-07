package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideAvisSuspension;

import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class InitRechercheCourrierHybrideAvisSuspensionUC extends BaseUC  {

	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		List<CourrierHybrideAvisSuspension> courrierAvisSuspensionList = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		CourrierHybrideAvisSuspension courrierAvisSuspensionHybride = (CourrierHybrideAvisSuspension) this.getItem(CourrierHybrideAvisSuspension.class);
		logger.info("Debut UC InitRechercheCourrierHybrideAvisSuspensionUC");
		try {			

			if(pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
				
			if (pagerVO.getNbrLignes() != null && "0".equals(pagerVO.getNbrLignes())) {
				Long nbreLignes = parametrageManager.getNombreCourrierAvisSuspensionHybrides(courrierAvisSuspensionHybride);
				if (nbreLignes != null && nbreLignes != 0) {
					// Integer nbreObject=nbreLignes.intValue();
					pagerVO.setNbrLignes(nbreLignes.toString());
					Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
					pagerVO.setNbrPages(String.valueOf((nbreLignes / pageSize) + 1));
				}
			}
			courrierAvisSuspensionList = (List<CourrierHybrideAvisSuspension>) parametrageManager.rechercheCourrierAvisSuspensionHybrides(courrierAvisSuspensionHybride,
					pagerVO);
			if (courrierAvisSuspensionList.size() == 0) {
				throw new FonctionnelleException(EXPF_AUCUN_OPPOSITION_TROUVE);
			}
			}else {
				courrierAvisSuspensionList = (List<CourrierHybrideAvisSuspension>) parametrageManager.rechercheCourrierAvisSuspensionHybrides(courrierAvisSuspensionHybride, null);
				if (courrierAvisSuspensionList.size() == 0) {
					throw new FonctionnelleException(EXPF_AUCUN_OPPOSITION_TROUVE);
				}
			}
		}catch (ConstraintViolationException e) {
				throw new FonctionnelleException(e);
			} catch (Exception e) {
				throw new FonctionnelleException(e.getMessage());
			}
			addResultItem(courrierAvisSuspensionList);
			logger.info("Fin UC InitRechercheCourrierHybrideAvisSuspensionUC");
	}
	
	public boolean isTransactionnal() {
		return false;
	}
}