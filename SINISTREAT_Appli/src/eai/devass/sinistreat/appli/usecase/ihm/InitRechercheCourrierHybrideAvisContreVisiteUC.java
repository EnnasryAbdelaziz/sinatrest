package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybride;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideAvisContreVisite;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideOuvertureRente;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideOuvertureRente;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class InitRechercheCourrierHybrideAvisContreVisiteUC extends BaseUC  {

	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// R�cup�rer la class de l'objet BO correspondant � l'objet VO
		List<CourrierHybrideAvisContreVisite> courrierAvisContreVisiteList = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		CourrierHybrideAvisContreVisite courrierAvisContreVisiteHybride = (CourrierHybrideAvisContreVisite) this.getItem(CourrierHybrideAvisContreVisite.class);
		logger.info("Debut UC InitRechercheCourrierHybrideAvisContreVisiteUC");
		try {			

			if(pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
				
			if (pagerVO.getNbrLignes() != null && "0".equals(pagerVO.getNbrLignes())) {
				Long nbreLignes = parametrageManager.getNombreCourrierAvisContreVisiteHybrides(courrierAvisContreVisiteHybride);
				if (nbreLignes != null && nbreLignes != 0) {
					// Integer nbreObject=nbreLignes.intValue();
					pagerVO.setNbrLignes(nbreLignes.toString());
					Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
					pagerVO.setNbrPages(String.valueOf((nbreLignes / pageSize) + 1));
				}
			}
			courrierAvisContreVisiteList = (List<CourrierHybrideAvisContreVisite>) parametrageManager.rechercheCourrierAvisContreVisiteHybrides(courrierAvisContreVisiteHybride,
					pagerVO);
			if (courrierAvisContreVisiteList.size() == 0) {
				throw new FonctionnelleException(EXPF_AUCUN_OPPOSITION_TROUVE);
			}
			}else {
				courrierAvisContreVisiteList = (List<CourrierHybrideAvisContreVisite>) parametrageManager.rechercheCourrierAvisContreVisiteHybrides(courrierAvisContreVisiteHybride, null);
				if (courrierAvisContreVisiteList.size() == 0) {
					throw new FonctionnelleException(EXPF_AUCUN_OPPOSITION_TROUVE);
				}
			}
		}catch (ConstraintViolationException e) {
				throw new FonctionnelleException(e);
			} catch (Exception e) {
				throw new FonctionnelleException(e.getMessage());
			}
			addResultItem(courrierAvisContreVisiteList);
			logger.info("Fin UC InitRechercheCourrierHybrideAvisContreVisiteUC");
	}
	
	public boolean isTransactionnal() {
		return false;
	}
}