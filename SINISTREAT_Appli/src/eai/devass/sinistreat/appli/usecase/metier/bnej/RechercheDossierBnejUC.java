package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.bnej.DossierBnej;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RechercheDossierBnejUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		
		logger.info("Debut UC RechercheDossierBnejUC");
		DossierBnej dossierBnej = (DossierBnej) this.getItem(DossierBnej.class);
		List<DossierBnej> listDossierBnej = null;
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
			logger.info("Debut Method bnejManager.getNombreDossierBnej(dossierBnej)");
			Long nbreLignes = bnejManager.getNombreDossierBnej(dossierBnej);
			logger.info("Fin Method bnejManager.getNombreDossierBnej(dossierBnej)");
			if (nbreLignes > 300) {
				throw new FonctionnelleException(
						"le nombre de résultat est élevé [" + nbreLignes
								+ "] , veuillez affiné votre recherche");
			}
			logger.info("Debut Method bnejManager.getListDossierBnej");
			listDossierBnej = (List) bnejManager.getListDossierBnej(dossierBnej,
					mapDates, pagerVO);
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

		addResultItem(listDossierBnej);
		
		this.addResultItem(pagerVO);
		logger.info("Fin UC RechercheDossierBnejUC");

	}

}
