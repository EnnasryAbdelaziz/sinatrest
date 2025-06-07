package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.bnej.DossierBnej;
import eai.devass.sinistreat.appli.modele.metier.bnej.LotBnej;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class ConsultationLotBnejUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		List<DossierBnej> lst = new ArrayList<DossierBnej>();
		LotBnej bnej = (LotBnej) this.getItem(LotBnej.class);
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);

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
			logger.info("Debut Method bnejManager.getNombreDossierBnej(LotBnej)");
			Long nbreLignes = bnejManager.getNombreDossierBnej(bnej.getId());
			logger.info("Fin Method bnejManager.getNombreDossierBnej(LotBnej)");

			logger.info("Debut Method bnejManager.getListDossierBnej");
			lst = (List)  bnejManager.getlistDossierBnej(bnej.getId(), pagerVO);
			logger.info("Fin Method bnejManager.getListDossierBnej");
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

			
		if (bnej.getId() == 0) {
			throw new FonctionnelleException(EXP_TECHNIQUE_PROBLEME);
		} else {

			this.addResultItem(lst);

		}

	}

}
