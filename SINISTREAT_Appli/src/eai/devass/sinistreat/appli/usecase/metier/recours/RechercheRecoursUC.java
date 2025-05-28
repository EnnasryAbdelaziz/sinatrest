package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RechercheRecoursUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		List<Recours> listRecours = new ArrayList<Recours>();
		Recours recours = (Recours) this.getItem(Recours.class);
		RecoursVO recoursVo = (RecoursVO) entite;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);

		HashMap mapDates = (HashMap) this.getItem(HashMap.class);
		try {
			if (recoursVo.getDateDebut() != null
					&& !StringUtils.isEmpty(recoursVo.getDateDebut())) {
				if (mapDates == null) {
					mapDates = new HashMap();
				}
				mapDates.put(IDate.DATE_DEBUT, recoursVo.getDateDebut());
			}
			if (recoursVo.getDateFin() != null
					&& !StringUtils.isEmpty(recoursVo.getDateFin())) {
				if (mapDates == null) {
					mapDates = new HashMap();
				}
				mapDates.put(IDate.DATE_FIN, recoursVo.getDateFin());
			}
			if (pagerVO != null) {
				//int numP;
					if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				}
					if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
				
				if(pagerVO.getNbrLignes()!=null  && "0".equals(pagerVO.getNbrLignes())){
					Long result = recoursManager.getNombreRecours(recours, mapDates);
					if(result!=null){
						Integer nbreObject=result.intValue();
						pagerVO.setNbrLignes(nbreObject.toString());
						Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
						pagerVO.setNbrPages(String.valueOf((nbreObject / pageSize) + 1));
					}
				}
//				if (nbreObject.compareTo(IParam.MAX_RESULT) > 0) {
//					throw new FonctionnelleException(EXP_MAX_RESULT);
//				}
				listRecours = (List) recoursManager.getListRecours(recours, pagerVO, mapDates);
				
			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e);
		}
		addResultItem(listRecours);
		if (pagerVO != null)
		{
			this.addResultItem(pagerVO);
		}
	}

	public boolean isTransactionnal() {
		return true;
	}

	/**
	 * 
	 * @param entite
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Recours> getListRecours(IValueObject entite, Recours recours,
			HashMap params) throws Exception {
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		try {
			return (List<Recours>) recoursManager.getListRecours(recours, pagerVO, null);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e);
		}
	}
}
