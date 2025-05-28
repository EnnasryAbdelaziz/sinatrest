package eai.devass.sinistreat.appli.usecase.parametrage;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import org.hibernate.exception.ConstraintViolationException;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.Tribunal;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RechercheTribunalUC extends BaseUC {

	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		List<Tribunal> listeTribunal = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		Tribunal tribunal = (Tribunal) this.getItem(Tribunal.class);
		try {
			if (pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}

				listeTribunal = (List) parametrageManager
						.getListTribunal(tribunal, pagerVO);
				Integer nbreObject = parametrageManager
						.getNombreTribunal(tribunal);
				pagerVO.setNbrLignes(nbreObject.toString());
				Integer pageSize= Integer.valueOf(pagerVO.getPageSize());
				if(pageSize!=0) {
					pagerVO.setNbrPages(String.valueOf((nbreObject/pageSize)+1));
				}
			} else {
				listeTribunal = (List) parametrageManager
						.getListTribunal(tribunal, null);
			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		addResultItem(listeTribunal);
		if (pagerVO != null) {
			this.addResultItem(pagerVO);
		}
	}

	public boolean isTransactionnal() {
		return true;
	}
}
