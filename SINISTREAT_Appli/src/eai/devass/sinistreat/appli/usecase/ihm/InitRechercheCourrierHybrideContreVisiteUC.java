package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybride;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideContreVisite;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideQuittance;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class InitRechercheCourrierHybrideContreVisiteUC extends BaseUC  {
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		List<CourrierHybrideContreVisite> courrieContreVisiteList = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		CourrierHybrideContreVisite courrierContreVisiteHybride = (CourrierHybrideContreVisite) this.getItem(CourrierHybrideContreVisite.class);

		try {			

			if(pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
				
			if (pagerVO.getNbrLignes() != null && "0".equals(pagerVO.getNbrLignes())) {
				Long nbreLignes = parametrageManager.getNombreCourrierContreVisiteHybrides(courrierContreVisiteHybride);
				if (nbreLignes != null && nbreLignes != 0) {
					// Integer nbreObject=nbreLignes.intValue();
					pagerVO.setNbrLignes(nbreLignes.toString());
					Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
					pagerVO.setNbrPages(String.valueOf((nbreLignes / pageSize) + 1));
				}
			}
			courrieContreVisiteList = (List<CourrierHybrideContreVisite>) parametrageManager.rechercheCourrierContreVisiteHybrides(courrierContreVisiteHybride,
					pagerVO);
			if (courrieContreVisiteList.size() == 0) {
				throw new FonctionnelleException(EXPF_AUCUN_AUDIENCE_TROUVE);
			}
			}else {
				courrieContreVisiteList = (List<CourrierHybrideContreVisite>) parametrageManager.rechercheCourrierContreVisiteHybrides(courrierContreVisiteHybride, null);
				if (courrieContreVisiteList.size() == 0) {
					throw new FonctionnelleException(EXPF_AUCUN_AUDIENCE_TROUVE);
				}
			}
		}catch (ConstraintViolationException e) {
				throw new FonctionnelleException(e);
			} catch (Exception e) {
				throw new FonctionnelleException(e.getMessage());
			}
			addResultItem(courrieContreVisiteList);
	}
	
	public boolean isTransactionnal() {
		return false;
	}
}