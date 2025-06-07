package eai.devass.sinistreat.appli.usecase.parametrage;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import org.hibernate.exception.ConstraintViolationException;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.Intermediaire;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RechercheIntermediaireUC extends BaseUC {

	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {

		List<Intermediaire> listeIntermediaire = null;
		String emailInterm = "";
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		Intermediaire intermediaire = (Intermediaire) this.getItem(Intermediaire.class);
		try {
			if (pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}

				listeIntermediaire = (List) parametrageManager.getListIntermediaire(intermediaire, pagerVO);
				Integer nbreObject = parametrageManager.getNombreIntermediaire(intermediaire);
				pagerVO.setNbrLignes(nbreObject.toString());
				Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
				if (pageSize != 0) {
					pagerVO.setNbrPages(String.valueOf((nbreObject / pageSize) + 1));
				}
			} else {
				listeIntermediaire = (List) parametrageManager.getListIntermediaire(intermediaire, null);
			}

			//recuperer email intermediaire et voir s il existe sur emailIntermediaire table ENNASRY 12/2021 Lot1
			emailInterm = parametrageManager.getEmailIntermediaireByCode(intermediaire.getCode());
			if (listeIntermediaire != null) {
				if (listeIntermediaire.size() > 0 && !emailInterm.equals("")) {
					for(Intermediaire inter : listeIntermediaire) {
						if(inter != null) {
						inter.setEmailInterm(emailInterm);
						}
					}
				}
			}

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		addResultItem(listeIntermediaire);
		if (pagerVO != null) {
			this.addResultItem(pagerVO);
		}
	}

	public boolean isTransactionnal() {
		return true;
	}
}
