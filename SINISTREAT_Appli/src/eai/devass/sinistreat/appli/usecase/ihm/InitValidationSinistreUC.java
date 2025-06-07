package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.EtatSinistreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatSinVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class InitValidationSinistreUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		// Récupérer la class de l'objet BO correspondant à l'objet VO
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		SinistreVO sinistreVO = (SinistreVO) entite;
		String intermediaire = sinistreVO.getCodeIntermediaire();
		String typeCreation = sinistreVO.getTypeCreation();
		List<Sinistre> listSin = new ArrayList<Sinistre>();
		long nbreObject = 1;
		try {
			if (pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
			}
			EtatSinistreVO refEtat = sinistreVO.getRefEtatSinistre();
			if (refEtat != null) {
				EtatSinVO etat = refEtat.getRefEtat();
				if (etat != null) {
					String codeEtat = etat.getCode();
					if (codeEtat != null && !codeEtat.equals("")) {
						List nbreObj = sinistreManager.getNombreSinistre(
								codeEtat, intermediaire, typeCreation);
						nbreObject = (Long) nbreObj.get(0);
						listSin = (List<Sinistre>) sinistreManager
								.getListSinistreByEtat(codeEtat, intermediaire,
										typeCreation, pagerVO);
					}
				}
			}
			if (listSin != null) {
				for (Sinistre sinistre : listSin) {
					sinistre.setCoutSinistre(sinistreManager
							.calculCoutSinistre(sinistre));
				}
			}
			if (pagerVO != null) {
				Integer pageSize = Integer.valueOf(pagerVO.getPageSize());

				if (pageSize != 0) {
					if (String.valueOf((nbreObject / pageSize)).equals("1")) {
						pagerVO.setNbrPages(String
								.valueOf((nbreObject / pageSize)));
					} else {
						pagerVO.setNbrPages(String
								.valueOf((nbreObject / pageSize) + 1));
					}
				}
			}
			this.addResultItem(listSin);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

	public boolean isTransactionnal() {
		return false;
	}

}
