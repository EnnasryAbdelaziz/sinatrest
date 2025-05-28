package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RechercheSinistreUC extends BaseUC {


	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		logger.info("Debut UC RechercheSinistreUC");
		Sinistre sinistre = (Sinistre) this.getItem(Sinistre.class);
		List<Sinistre> listSinistre = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		HashMap mapDates = (HashMap) this.getItem(HashMap.class);
		
		try {

			if (pagerVO == null) {
				throw new FonctionnelleException(
						EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
			}

			if (pagerVO.getNumPage() == null) {
				throw new FonctionnelleException(
						EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);

			} else if (pagerVO.getPageSize() == null) {
				throw new FonctionnelleException(
						EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
			}
			logger.info("Debut Method sinistreManager.getNombreSinistre(sinistre)");
			Long nbreLignes = sinistreManager.getNombreSinistre(sinistre);
			logger.info("Fin Method sinistreManager.getNombreSinistre(sinistre)");
			if (nbreLignes > 100) {
				throw new FonctionnelleException(
						"le nombre de résultat est élevé [" + nbreLignes
								+ "] , veuillez affiné votre recherche");
			}
			logger.info("Debut Method ssinistreManager.getListSinistre");
			listSinistre = (List) sinistreManager.getListSinistre(sinistre,
					mapDates, pagerVO);
			logger.info("Fin Method ssinistreManager.getListSinistre");
			pagerVO.setNbrLignes(nbreLignes.toString());
			Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
			if (pageSize != 0) {
				pagerVO.setNbrPages(String.valueOf((nbreLignes / pageSize) + 1));
			}

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(listSinistre);
		
		this.addResultItem(pagerVO);
		logger.info("Fin UC RechercheSinistreUC");
	}

	public boolean isTransactionnal() {
		return false;
	}

}
