package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideOpposition;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class InitRechercheCourrierHybrideOppositionUC extends BaseUC  {

	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		List<CourrierHybrideOpposition> courrierOppositionList = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		CourrierHybrideOpposition courrierOppositionHybride = (CourrierHybrideOpposition) this.getItem(CourrierHybrideOpposition.class);
		logger.info("Debut UC InitRechercheCourrierHybrideOppositionUC");
		try {			

			if(pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
				
			if (pagerVO.getNbrLignes() != null && "0".equals(pagerVO.getNbrLignes())) {
				Long nbreLignes = parametrageManager.getNombreCourrierOppositionHybrides(courrierOppositionHybride);
				if (nbreLignes != null && nbreLignes != 0) {
					// Integer nbreObject=nbreLignes.intValue();
					pagerVO.setNbrLignes(nbreLignes.toString());
					Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
					pagerVO.setNbrPages(String.valueOf((nbreLignes / pageSize) + 1));
				}
			}
			courrierOppositionList = (List<CourrierHybrideOpposition>) parametrageManager.rechercheCourrierOppositionHybrides(courrierOppositionHybride,
					pagerVO);
			if (courrierOppositionList.size() == 0) {
				throw new FonctionnelleException(EXPF_AUCUN_OPPOSITION_TROUVE);
			}
			}else {
				courrierOppositionList = (List<CourrierHybrideOpposition>) parametrageManager.rechercheCourrierOppositionHybrides(courrierOppositionHybride, null);
				if (courrierOppositionList.size() == 0) {
					throw new FonctionnelleException(EXPF_AUCUN_OPPOSITION_TROUVE);
				}
			}
		}catch (ConstraintViolationException e) {
				throw new FonctionnelleException(e);
			} catch (Exception e) {
				throw new FonctionnelleException(e.getMessage());
			}
			addResultItem(courrierOppositionList);
			logger.info("Fin UC InitRechercheCourrierHybrideOppositionUC");
	}
	
	public boolean isTransactionnal() {
		return false;
	}
}