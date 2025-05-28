package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.gsr.appli.manager.metier.dossierRente.DossierRenteManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.HistReserveMath;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.parametrage.GestionnaireRelance;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

public class InitHistReserveMathUC extends SimpleBaseUC {

	//protected DossierRenteManager dossierRenteManager = (DossierRenteManager) ServicesFactory.getService(DossierRenteManager.class);
	protected DossierRenteManager dossierRenteManager = new DossierRenteManager();
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	protected void execute(IValueObject entite, HashMap params)
			throws Exception {
		HistReserveMath histReserveMath = (HistReserveMath) this.getItem(HistReserveMath.class);
		
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
						nbreObject = dossierRenteManager.getNombreHistReserve(histReserveMath);
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
					
					List<HistReserveMath> listHistReserveMath = new ArrayList<HistReserveMath>();
					listHistReserveMath =  dossierRenteManager.getListHistReserveMath(pagerVO, histReserveMath);
					
					this.addResultItem(listHistReserveMath);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
	}

	public boolean isTransactionnal() {
		return false;
	}

}
