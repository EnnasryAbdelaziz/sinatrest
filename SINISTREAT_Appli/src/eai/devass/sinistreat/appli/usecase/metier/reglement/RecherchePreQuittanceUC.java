package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RecherchePreQuittanceUC extends BaseUC {

	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		ReglementVO reglementvo = (ReglementVO) entite;
		Reglement reglement = (Reglement) this.getItem(Reglement.class);
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		HashMap mapDates = (HashMap) this.getItem(HashMap.class);
		Boolean editer = Boolean.FALSE;
		try {

				reglement.setCodeEtatRgl(new String[] {
								//IConstantes.ETAT_PRE_QUITTANCE_EN_INSTANCE,
                    IConstantes.ETAT_PRE_QUITTANCE_VALIDEE });
				if (pagerVO != null && pagerVO.getNbrLignes() != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
				if ("1".equals(pagerVO.getNbrLignes())) {
					Integer nbreObject = reglementManager.getNombreReglement(
							reglement, mapDates, editer);
					pagerVO.setNbrLignes(nbreObject.toString());
					Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
					if(nbreObject.compareTo(pageSize)>=0){
					if ((nbreObject % pageSize) == 0) {
						pagerVO.setNbrPages(String.valueOf(nbreObject
								/ pageSize));
					} else {
						pagerVO.setNbrPages(String
								.valueOf((nbreObject / pageSize) + 1));

					}
                    } else {
						pagerVO.setNbrPages("1");
					}
				}

			}
			
			List<Reglement> listQuittance = new ArrayList<Reglement>();
			
            Iterator itr = ((List) reglementManager.getListQuittance(reglement,
                    mapDates, pagerVO,editer)).iterator();
			while(itr.hasNext()){
			   Object[] obj = (Object[]) itr.next();
			   Reglement reg = (Reglement) obj[0];
			   listQuittance.add(reg);
			}
			
			for (Reglement reg : listQuittance) {
				if (reg.getRefSinistre() != null) {
					reg.getRefSinistre().setListReglement(null);
				}
			}

			addResultItem(listQuittance);

			if (pagerVO != null) {
				this.addResultItem(pagerVO);
			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e);
		}

	}

	public boolean isTransactionnal() {
		return true;
	}

}
