package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybride;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideReclamation;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideReclamation;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class InitRechercheCourrierHybrideReclamationUC extends BaseUC  {
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		List<CourrierHybrideReclamation> courrierReclamationList = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		CourrierHybrideReclamation courrierReclamationHybride = (CourrierHybrideReclamation) this.getItem(CourrierHybrideReclamation.class);
		logger.info("Debut UC InitRechercheCourrierHybrideReclamationUC");
		try {			

			if(pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
				
			if (pagerVO.getNbrLignes() != null && "0".equals(pagerVO.getNbrLignes())) {
				Long nbreLignes = parametrageManager.getNombreCourrierReclamationHybrides(courrierReclamationHybride);
				if (nbreLignes != null && nbreLignes != 0) {
					// Integer nbreObject=nbreLignes.intValue();
					pagerVO.setNbrLignes(nbreLignes.toString());
					Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
					pagerVO.setNbrPages(String.valueOf((nbreLignes / pageSize) + 1));
				}
			}
			courrierReclamationList = (List<CourrierHybrideReclamation>) parametrageManager.rechercheCourrierReclamationHybrides(courrierReclamationHybride,
					pagerVO);
			if (courrierReclamationList.size() == 0) {
				throw new FonctionnelleException(EXPF_AUCUN_RECLAMATION_TROUVE);
			}
			}else {
				courrierReclamationList = (List<CourrierHybrideReclamation>) parametrageManager.rechercheCourrierReclamationHybrides(courrierReclamationHybride, null);
				if (courrierReclamationList.size() == 0) {
					throw new FonctionnelleException(EXPF_AUCUN_RECLAMATION_TROUVE);
				}
			}
		}catch (ConstraintViolationException e) {
				throw new FonctionnelleException(e);
			} catch (Exception e) {
				throw new FonctionnelleException(e.getMessage());
			}
			addResultItem(courrierReclamationList);
			logger.info("Fin UC InitRechercheCourrierHybrideReclamationUC");
	}
	
	public boolean isTransactionnal() {
		return false;
	}
}