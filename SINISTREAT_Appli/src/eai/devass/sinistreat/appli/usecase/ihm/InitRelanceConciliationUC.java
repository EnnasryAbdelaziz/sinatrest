package eai.devass.sinistreat.appli.usecase.ihm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.conciliation.ConciliationManager;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.RelanceConciliation;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.parametrage.GestionnaireRelance;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.RelanceConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class InitRelanceConciliationUC extends SimpleBaseUC {

	protected ConciliationManager conciliationManager = (ConciliationManager) ServicesFactory.getService(ConciliationManager.class);
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	protected void execute(IValueObject entite, HashMap params)
			throws Exception {
		Conciliation conciliation = (Conciliation) this.getItem(Conciliation.class);
		
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
					if("1".equals(conciliation.getTypeRelance())){
						nbreObject = conciliationManager.getNombreConciliation(conciliation);
					}
					if("2".equals(conciliation.getTypeRelance())){
						nbreObject = conciliationManager.getNombreConciliationSignaturePV(conciliation);
					}
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
					
					List<Conciliation> listConciliation = new ArrayList<Conciliation>();
					if("1".equals(conciliation.getTypeRelance())){
						listConciliation =  conciliationManager.getListConciliation(pagerVO, conciliation);
					}
					if("2".equals(conciliation.getTypeRelance())){
						listConciliation =  conciliationManager.getListConciliationSignaturePV(pagerVO, conciliation);
					}
					List<GestionnaireRelance> listGestionnaireRelance = conciliationManager.getListGestionnaireRelance();
					
//					List<RelanceConciliation> listRelance = new ArrayList<RelanceConciliation>();
//					for (Conciliation c : listConciliation) {
//						RelanceConciliation r = new RelanceConciliation();
////						r.setRefConciliation(c);
////						r.setDateRelance(c.)
////						r.setNumeroRelance();
////						r.setDateDerniereRelance(dateDerniereRelance);
//						listRelance.add(r);
//					}
//					
//					this.addResultItem(listRelance);
					
					this.addResultItem(listConciliation);
					this.addResultItem(listGestionnaireRelance);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
	}

	public boolean isTransactionnal() {
		return false;
	}

}
