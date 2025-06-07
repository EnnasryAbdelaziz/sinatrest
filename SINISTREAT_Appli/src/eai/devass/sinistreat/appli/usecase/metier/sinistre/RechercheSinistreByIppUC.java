package eai.devass.sinistreat.appli.usecase.metier.sinistre;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
 
public class RechercheSinistreByIppUC extends BaseUC  {
	
	
protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		Sinistre sinistre = (Sinistre) this.getItem(Sinistre.class);
		List<Sinistre> listSinistre = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		HashMap mapDates = (HashMap) this.getItem(HashMap.class);
		
		try{
			
			if (pagerVO != null){
				if (pagerVO.getNumPage()== null){
					throw new FonctionnelleException(EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				}else if (pagerVO.getPageSize()== null){
					throw new FonctionnelleException(EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}

				listSinistre=(List)sinistreManager.getListSinistreByIpp(sinistre, mapDates, pagerVO);
				Integer nbreLignes = sinistreManager.getNombreSinistreByIpp(sinistre,mapDates);
				pagerVO.setNbrLignes(nbreLignes.toString());
				Integer pageSize= Integer.valueOf(pagerVO.getPageSize());
				if(pageSize!=0) {
					pagerVO.setNbrPages(String.valueOf(nbreLignes/pageSize)+1);
				}
			}else {

				listSinistre=(List)sinistreManager.getListSinistreByIpp(sinistre, mapDates, null);
			}		
			
//			if(Long.valueOf(pagerVO.getNbrLignes()).intValue()==Long.valueOf(IParam.MAX_SINISTRE).intValue())
//				addMessageItem(new InfoMessageItem("Veuillez affiner la recherche"));

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(listSinistre);
		if (pagerVO != null) {
			this.addResultItem(pagerVO);
		}
	}
	
	
	public boolean isTransactionnal() {
		return false;
	}
	
	


}

