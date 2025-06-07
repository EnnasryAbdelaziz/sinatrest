package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class RecherchePreQuittanceARisqueAttenteComplementUC extends BaseUC {

	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		List<Reglement> listQuittance = new ArrayList<Reglement>();
		ReglementVO reglementvo = (ReglementVO) entite;
		Reglement reglement = (Reglement) this.getItem(Reglement.class);
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		HashMap mapDates = (HashMap) this.getItem(HashMap.class);
		Boolean editer = Boolean.FALSE;
		logger.info("Debut UC RecherchePreQuittanceARisqueAttenteComplementUC");
		try {

				reglement.setCodeEtatRgl(new String[] {
								//IConstantes.ETAT_PRE_QUITTANCE_EN_INSTANCE,
                    IConstantes.ETAT_PRE_QUITTANCE_VALIDEE });
				reglement.setTypeBeneficiaire(IConstantes.BENEFICIAIRE_VICTIME);
				
				if(pagerVO != null) {
					if (pagerVO.getNumPage() == null) {
						throw new FonctionnelleException(EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
					} else if (pagerVO.getPageSize() == null) {
						throw new FonctionnelleException(EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
					}
					
				if (pagerVO.getNbrLignes() != null && "0".equals(pagerVO.getNbrLignes())) {
					Integer nbreLignes = reglementManager.getNombrePreQuittanceARisqueAttenteComplement(
							reglement, mapDates, editer);
					if (nbreLignes != null && nbreLignes != 0) {
						// Integer nbreObject=nbreLignes.intValue();
						pagerVO.setNbrLignes(nbreLignes.toString());
						Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
						pagerVO.setNbrPages(String.valueOf((nbreLignes / pageSize) + 1));
					}
				}
				Iterator itr = ((List) reglementManager.getListPreQuittanceARisqueAttenteComplement(reglement,
	                    mapDates,pagerVO,editer)).iterator();
				while(itr.hasNext()){
				   Object[] obj = (Object[]) itr.next();
				   Reglement reg = (Reglement) obj[0];
				   listQuittance.add(reg);
				}
				if (listQuittance.size() == 0) {
					throw new FonctionnelleException(EXPF_AUCUN_QUITTANCE_TROUVE);
				}
				}else {
					Iterator itr = ((List) reglementManager.getListPreQuittanceARisqueAttenteComplement(reglement,
		                    mapDates,pagerVO,editer)).iterator();
					while(itr.hasNext()){
					   Object[] obj = (Object[]) itr.next();
					   Reglement reg = (Reglement) obj[0];
					   listQuittance.add(reg);
					}
					if (listQuittance.size() == 0) {
						throw new FonctionnelleException(EXPF_AUCUN_QUITTANCE_TROUVE);
					}
				}
				
				
	
				
		/*		
				
				
				if (pagerVO != null && pagerVO.getNbrLignes() != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
				if ("1".equals(pagerVO.getNbrLignes())) {
					Integer nbreObject = reglementManager.getNombrePreQuittanceARisque(
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
			
            Iterator itr = ((List) reglementManager.getListPreQuittanceARisque(reglement,
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
*/
			addResultItem(listQuittance);

			if (pagerVO != null) {
				this.addResultItem(pagerVO);
			}
			logger.info("Fin UC RecherchePreQuittanceARisqueAttenteComplementUC");

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
