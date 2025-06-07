package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.bnej.LotBnej;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RechercheLotBnejUC extends BaseUC {

	@SuppressWarnings("rawtypes")
	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		logger.info("Debut UC RecherchelotBnejUC");
		List<LotBnej> lst = new ArrayList<LotBnej>();
		LotBnej bnej = (LotBnej) this.getItem(LotBnej.class);
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		if (Fonctions.isBlank(bnej.getNumero())) {
			throw new FonctionnelleException(EXP_SEARCH_BNEJ);

		}

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
			Long nbreLignes = bnejManager.getNombreLotBnej(bnej);

			if (nbreLignes > 100) {
				throw new FonctionnelleException(
						"le nombre de résultat est élevé [" + nbreLignes
								+ "] , veuillez affiné votre recherche");
			}
			lst = (List) bnejManager.getlistLotBnej(bnej, pagerVO);
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
		this.addResultItem(lst);
		this.addResultItem(pagerVO);
		logger.info("Fin UC RecherchelotBnejUC");

	}

}
