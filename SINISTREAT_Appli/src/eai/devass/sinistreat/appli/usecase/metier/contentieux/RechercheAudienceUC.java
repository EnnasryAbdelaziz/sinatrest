package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RechercheAudienceUC extends BaseUC {
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		List<AudienceJugement> audienceList = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		AudienceJugement audience = (AudienceJugement) this
				.getItem(AudienceJugement.class);
		try {
			if (pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
				
				
				if(pagerVO.getNbrLignes()!=null  && "0".equals(pagerVO.getNbrLignes())){
					Long nbreLignes = contentieuxManager.getNombreAudiance(
							audience);
					if(nbreLignes != null && nbreLignes!=0){
						//Integer nbreObject=nbreLignes.intValue();
						pagerVO.setNbrLignes(nbreLignes.toString());
						Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
						pagerVO.setNbrPages(String.valueOf((nbreLignes / pageSize) + 1));
					}
				}
				
				
				
				audienceList = (List<AudienceJugement>) contentieuxManager
						.rechercheAudience(audience, pagerVO);
				if (audienceList.size()== 0)
				{
					throw new FonctionnelleException(EXPF_AUCUN_AUDIENCE_TROUVE);
				}
//				Integer nbreLignes = contentieuxManager.getNombreAudiance(
//						audience);
//				pagerVO.setNbrLignes(nbreLignes.toString());
//				Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
//				if (pageSize != 0) {
//					pagerVO.setNbrPages(String
//							.valueOf((nbreLignes / pageSize) + 1));
//				}
				
			} else {
				audienceList = (List<AudienceJugement>) contentieuxManager
						.rechercheAudience(audience, null);
				if (audienceList.size()== 0)
				{
					throw new FonctionnelleException(EXPF_AUCUN_AUDIENCE_TROUVE);
				}
			}

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		//System.out.println(audienceList.get(0).getRefProcedureJudiciaire().getRefRecours().getRefSinistre().getNumeroSinistre());
		logger.info(audienceList.get(0).getRefProcedureJudiciaire().getRefRecours().getRefSinistre().getNumeroSinistre());
		addResultItem(audienceList);

	}
}
