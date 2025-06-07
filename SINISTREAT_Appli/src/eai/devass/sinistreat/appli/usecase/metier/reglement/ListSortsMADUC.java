package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.manager.reglement.ReglementManager;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.parametrage.PlafondMAD;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

@SuppressWarnings("all") 
public class ListSortsMADUC extends SimpleBaseUC {

	
	//protected ReglementManager reglementManager = (ReglementManager) ServicesFactory.getService(ReglementManager.class);
	/*
	protected void executerUC(IValueObject entite,HashMap params)
			throws Exception {

		List<Reglement> listSortsMAD = null;

		try {
			listSortsMAD = reglementManager.getListSortsMAD();
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(listSortsMAD);

	}
*/
	
	

	protected ReglementManager reglementManager = (ReglementManager) ServicesFactory.getService(ReglementManager.class);
	protected ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory.getService(ParametrageManager.class);
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	protected void execute(IValueObject entite, HashMap params)
			throws Exception {
		

		Reglement reglement = (Reglement) this.getItem(Reglement.class);
		
		HashMap map = (HashMap) this.getItem(HashMap.class);
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		try {

			if (pagerVO != null && pagerVO.getNbrLignes() != null) {
				
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(
							"EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE");
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(
							"EXP_PAGER_PAGE_SIZE_OBLIGATOIRE");
				}
				
				if ("1".equals(pagerVO.getNbrLignes())) {
					logger.info("Get Number Of Objects");
					Integer nbreObject = null;
						nbreObject = reglementManager.getNombreSortsMAD(reglement);
					logger.info("The Number Of Objects is "+ nbreObject);
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
			
					
					List<Reglement> listSortsMAD = new ArrayList<Reglement>();
					listSortsMAD =  reglementManager.getListSortsMAD(pagerVO, reglement);
					List<PlafondMAD> listPlafondMAD = parametrageManager.getlistParamPlafondMAD();
					
					
					this.addResultItem(listSortsMAD);
					this.addResultItem(listPlafondMAD);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
	
	}

	public boolean isTransactionnal() {
		return false;
	}


	
	
}
