package eai.devass.sinistreat.appli.usecase.ihm;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class InitValidationReglementUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		// Récupérer la class de l'objet BO correspondant à l'objet VO
		Reglement reglement = (Reglement) this.getItem(Reglement.class);
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		HashMap map = (HashMap) this.getItem(HashMap.class);
		
		try {
			BigDecimal seuil = BigDecimal.valueOf(999999);
			BigDecimal seuilsub = BigDecimal.valueOf(0);
			
			// Tous les reglement dont l etat est :
			
			if(reglement.getRefTypeReglement()!=null){
			if("1".equals(reglement.getRefTypeReglement().getCode()) || "6".equals(reglement.getRefTypeReglement().getCode()) || "3".equals(reglement.getRefTypeReglement().getCode())  ){
			
			reglement.setCodeEtatRgl(new String[] {IConstantes.ETAT_REGLEMENT_EN_INSTANCE_POSITIONNEMENT
					,IConstantes.ETAT_REGLEMENT_EN_INSTANCE_DE_VALIDATION, IConstantes.ETAT_PRE_QUITTANCE_EN_INSTANCE});
			}
			else {
				reglement.setCodeEtatRgl(new String[] {
						IConstantes.ETAT_REGLEMENT_EN_INSTANCE_DE_VALIDATION, IConstantes.ETAT_PRE_QUITTANCE_EN_INSTANCE});
				
			}
			}

			if (pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
				Integer nbreLignes = reglementManager.getNombreReglementAValider(reglement,map,seuil,seuilsub);
				
				pagerVO.setNbrLignes(nbreLignes.toString());
				Integer pageSize= Integer.valueOf(pagerVO.getPageSize());
				if (pageSize != 0) {

					int rest = nbreLignes % pageSize;
					if (rest != 0) {
						pagerVO.setNbrPages(String
								.valueOf((nbreLignes / pageSize) + 1));
					} else {
						pagerVO.setNbrPages(String
								.valueOf((nbreLignes / pageSize)));
					}
				}
			
			}
			
			List<Reglement> listReglement = (List<Reglement>) reglementManager
			.getListReglementReglementAValider(reglement, map, pagerVO,seuil,seuilsub);
			

			this.addResultItem(listReglement);
			if (pagerVO != null){ 	
				this.addResultItem(pagerVO);
			}
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

	public boolean isTransactionnal() {
		return false;
	}

}
